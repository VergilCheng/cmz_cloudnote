/*script/login,js编码为utf8*/
$(function () {
    console.log('打桩：Hello World');
    $('#login').click(loginAction);
    $('#count').blur(checkName);
    $('#password').blur(checkPassword);
})

function checkName () {
    var name = $('#count').val()
    var rule = /^\w{4,10}$/;
    if(!rule.test(name)){
        $('#count').next().html('4~10个字符串');
        return false;
    }
    $('#count').next().empty();
    return true;
}

function checkPassword() {
    var password = $('#password').val();
    var rule = /^\w{1~20}$/;
    if(!rule.test(password)){
        $('#password').next().html('1~20个字符');
        return false;
    }
    $('#password').next().empty();
    return true;
}

function loginAction() {
    //console.log("loginAction")

    var n = checkPassword()+checkName();
    if(n!=2){
        return;
    }
    //data对象中的属性名与服务器控制器的参数名一致！
    var data = {"name":name,"password":password}
    $.ajax({
        url:'user/login.do',
        data:data,
        type:'post',
        //前三个属性是用于向服务器发送请求的参数，dateType为接收服务器的返回类型
        dataType:'json',
        success:function (result) {
            //console.log(result);打桩
            if(result.state==0){
                var user = result.data;
                console.log(user);
                //跳转
                location.href = 'edit.html';
            }else{
                var message = result.message;
                if(result.state==2){
                    $('#count').next().html(message);
                }else if(result.state==3){
                    $('#password').next().html(message);
                }else{
                    alert(message);
                }


            }
        },
        error:function (e) {
            alert("通信失败！");
        }

    })
}