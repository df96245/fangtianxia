function login(){
    var username = $("#username").val();
    var password = $("#password").val();
    $.ajax({
        url:"http://localhost:8888/user/login",
        type:"POST",
        dataType:'json',
        data:{
            username:username,
            password:password
        },
        success: function (data)
        {
            if(0==data.status){
                 window.location.href="http://localhost:8888";
            }else if (10==data.status){
                alert("需要登录："+data.message);
            }else if (1==data.status){
                alert("请求失败："+data.message);
            }
        },
        error: function (XHR) {
            alert(XHR.status)
            alert(XHR)
        }
    });
}
