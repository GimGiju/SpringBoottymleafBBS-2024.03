package com.example.abbs.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.abbs.entity.Like;
import com.example.abbs.entity.Reply;
import com.example.abbs.service.LikeService;
import com.example.abbs.service.ReplyService;
import com.example.abbs.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.abbs.entity.Board;
import com.example.abbs.service.BoardService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired private BoardService boardService;
    @Autowired private ReplyService replyService;
    @Autowired private LikeService likeService;
    @Autowired private JsonUtil jsonUtil;
    @Value("${spring.servlet.multipart.location}")private String uploadDir;

    @GetMapping("/list")
    public String list(@RequestParam(name="p", defaultValue="1") int page,
                       @RequestParam(name="f", defaultValue="title") String field,
                       @RequestParam(name="q", defaultValue="") String query,
                       HttpSession session, Model model) {
        List<Board> boardList = boardService.getBoardList(page, field, query);

        int totalBoardCount = boardService.getBoardCount(field, query);
        int totalPages = (int) Math.ceil(totalBoardCount / (double)BoardService.COUNT_PER_PAGE);
        int startPage = (int) Math.ceil((page-0.5)/BoardService.PAGE_PER_SCREEN - 1) * BoardService.PAGE_PER_SCREEN + 1;
        int endPage = Math.min(totalPages, startPage + BoardService.PAGE_PER_SCREEN - 1);
        List<Integer> pageList = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++)
            pageList.add(i);

        session.setAttribute("currentBoardPage", page);
        model.addAttribute("boardList", boardList);
        model.addAttribute("field", field);
        model.addAttribute("query", query);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("pageList", pageList);

        return "board/list";
    }

    @GetMapping("/insert")
    public String insertForm(){
        return "board/insert";
    }

    @PostMapping("/insert")
    public String insertProc(String title, String content,
                     MultipartHttpServletRequest req, HttpSession session){
        String sessUid = (String) session.getAttribute("sessUid");  // 로그인한 아이디로 하기위한 부분
        List<MultipartFile> uploadFileList = req.getFiles("files");

        List<String> fileList = new ArrayList<>();      // 파일 리스트
        for (MultipartFile part: uploadFileList){
            // 첨부 파일이 없는 경우 - application/octet-stream
            if (part.getContentType().contains("octet-stream"))
                continue;
            // 첨부 파일 있는 경우
            String filename = part.getOriginalFilename();
            String uploadPath = uploadDir + "upload/" + filename;
            try {
                part.transferTo(new File(uploadPath));
            }catch (Exception e){
                e.printStackTrace();
            }
            fileList.add(filename);
        }
//        fileList.forEach(x -> System.out.println(x));       // 파일 이름이 들어왔는데 출력해보기
        String files = jsonUtil.list2Json(fileList);

        Board board = new Board(title, content, sessUid, files);
        boardService.insertBoard(board);
        return "redirect:/board/list";
    }

    @GetMapping("/detail/{bid}/{uid}")
    public String detail(@PathVariable int bid, @PathVariable String uid, String option,
                         HttpSession session, Model model){
        // 본인이 조회한 경우 조회수 증가 안하게
        String sessUid = (String) session.getAttribute("sessUid");
        if (!uid.equals(sessUid) && (option==null || option.equals(""))) // option 두개의 조건을 만족하고 sessUid가 아니면 보드 조회 카운트 하나 증가하라는 뜻
            boardService.increaseViewCount(bid);

        Board board = boardService.getBoard(bid);           // board 값 가져오기
        String jsonFiles = board.getFiles();
        if (!(jsonFiles == null || jsonFiles.equals(""))){
            List<String> fileList = jsonUtil.json2List(jsonFiles);          // 파일 리스트로 만들어서 내려보내줌
            model.addAttribute("fileList", fileList);           // 파일 리스트로 만들어서 내려보내줌
        }
        model.addAttribute("board", board);

        Like like = likeService.getLike(bid, sessUid);                    // 좋아요 처리 하는 부분
        if (like == null) {
            session.setAttribute("likeClicked", 0);
        }else{
            session.setAttribute("likeClicked", like.getValue());
        }
        model.addAttribute("count", board.getLikeCount());  // 좋아요 카운트

        List<Reply> replyList = replyService.getReplyList(bid);           // 댓글 리스트를 만들어줌
        model.addAttribute("replyList", replyList);
        return "board/detail";
    }

    @GetMapping("/delete/{bid}")
    public String delete(@PathVariable int bid, HttpSession session){
        boardService.deleteBoard(bid);
        return "redirect:/board/list?p=" + session.getAttribute("currentBoardPage");
    }

    @PostMapping("/reply")          // 댓글 insert 부분
    public String reply(int bid, String uid, String comment, HttpSession session){  // HttpSession 은 로그인 한 사람이 누군지 알기위해서 사용함
        String sessUid = (String) session.getAttribute("sessUid");
        int isMine = (sessUid.equals(uid)) ? 1 : 0;
        Reply reply = new Reply(comment, sessUid, bid, isMine);     // 글 쓰는 사람이 나이기 때문에 sessUid 사용

        replyService.insertReply(reply);
        boardService.increaseReplyCount(bid);           // 댓글 카운트 올려주는 부분

        return "redirect:/board/detail/" + bid + "/" + uid + "?option=DNI";
    }

    // AJAX 처리
    @GetMapping("/like/{bid}")
    public String like(@PathVariable int bid, HttpSession session, Model model) {
        String sessUid = (String) session.getAttribute("sessUid");
        Like like = likeService.getLike(bid, sessUid);
        if (like == null) {
            likeService.insertLike(new Like(sessUid, bid, 1));    // 내가 like를 누른적이 없으면 like 값이 1(진한 좋아요색)이 되고 누른적이 없으면 toggle이됨
            session.setAttribute("likeClicked", 1);
        } else {
            int value = likeService.toggleLike(like);
            session.setAttribute("likeClicked", value);
        }
        int count = likeService.getLikeCount(bid);
        boardService.updateLikeCount(bid, count);
//        System.out.println(count);
        model.addAttribute("count", count);
        return "board/detail::#likeCount";
    }
}