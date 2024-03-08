/*
 *  Aside menu control
 */
$(document).ready(function(){       // 이벤트 등록
    $('#stateMsgBtn').click(function(e){
        $('#stateMsgInput').attr({'class': 'mt-2'});    // 입력창이 보이게
        $('#stateInput').val($('#stateMsg').text());    // 입력창에 stateMsg 내용이 보이게 만들어준 부분
    });
    $('#stateMsgSubmit').click(changeStateMsg);         // stateMsg를 클릭하면 이벤트 등록
});

function changeStateMsg(){
    let stateInputVal = $('#stateInput').val();          // 사용자가 수정한 글 읽기
    $('#stateMsgInput').attr({'class': 'mt-2 d-none'});        // 일력창이 안보이게 만들기 d-none 사용
    $.ajax({       // Asynchronous Javascript and XML 의 약자 = ajax, ajax는 화면의 일부분만 바꿀 때 주로 사용함.
        type: 'GET',
        url: '/abbs/aside/stateMsg',
        data: {stateMsg: stateInputVal},
        success: function(result){
            console.log('state message:', stateInputVal, result);           // 메시지가 제대로 왔는지 안왔는지 확인 콜솔로 하는법
            $('#stateMsg').html(stateInputVal);
        }
    });
}