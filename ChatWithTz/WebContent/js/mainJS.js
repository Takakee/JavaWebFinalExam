
function jqAjaxSendMessage() {
    //获取输入框的值
    var userMessage = $("textarea").val();
    $("textarea").val(""); //清空文本框
    //增加自己的气泡
    msgFromMe(userMessage);

    scrollToBottom();

    $.ajax({
        type: "POST",
        url: "APIController",
        data: {key:"free",appid:0,msg:userMessage},
        dataType:"json",

        success: function (response) {
            console.log(response.msg);
            //增加对方的气泡
            msg(response.msg);
            scrollToBottom();
        }
    });
}

/* 函数：增加自己的气泡 */
function msgFromMe(userMessage){
    var str = "<div class='message fromme'>";
    str = str + "<div class='user-head'><img src='img/myself.jpg'></div>";
    str = str + "<div class='content'>"+userMessage+"</div>";
    str = str+"</div>"
    $("#messages").append(str);
}

/* 函数：增加对方的气泡 */
function msg(userMessage){
    var str = "<div class='message'>";
    str = str + "<div class='user-head'><img src='img/currentUser.jpg'></div>";
    str = str + "<div class='content'>"+userMessage+"</div>";
    str = str+"</div>"
    $("#messages").append(str);
}

/* textarea监听回车键 */
function listenEnterKey()
{
    var keycode; 
    if(event.which)
    {
        keycode = event.which; //按键代码
    }
    //按键名称：var keychar = String.fromCharCode(x);
    //alert(keycode);
    if(keycode == 13)
    {
        jqAjaxSendMessage();
        event.returnValue = false; 
        return false;
    }
}

/* 自动滚动到底部 */
function scrollToBottom()
{
    //var now = new Date();
    var div = document.getElementById('messages');
    //div.innerHTML = div.innerHTML + 'time_' + now.getTime() + '<br />';
    div.scrollTop = div.scrollHeight;
}



