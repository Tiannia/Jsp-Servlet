function change(img) {
    img.src = "getcode?" + new Date().getTime();
}

var flag = true;//标记位


function FocusItem(obj) {

    if ($(obj).attr('name') == 'code') {
        $(obj).next().next().html('').removeClass('error');
    } else {
        $(obj).next('span').html('').removeClass('error');
    }
}

function CheckItem(obj) {
    var msgBox = $(obj).next('span');
    switch ($(obj).attr('name')) {
        case "userName":
            var namevalue = obj.value;
            var reg = /^[a-zA-z]\w{3,15}$/;  //定义正则表达式
            if (namevalue == "") {
                msgBox.html('用户ID不能为空');
                msgBox.removeClass('correct').addClass('error');//移除原有样式再添加新样式
                flag = false;
            } else if (!reg.test(namevalue)) {
                msgBox.html('用户ID格式有误');
                msgBox.removeClass('correct').addClass('error');
                flag = false;
            } else {
                var url = "usernamecheck?name=" + encodeURI($(obj).val()) + "&" + new Date().getTime();
                //"false" "true"
                $.get(url, function (data) {
                    if (data == "false") {
                        msgBox.html('用户ID已被注册');
                        msgBox.removeClass('correct').addClass('error');
                        flag = false;
                    } else {
                        //msgBox.html().removeClass('error');  移除样式,注意html里面为空,则不显示信息
                        msgBox.html('Correct[√]');
                        msgBox.removeClass('error').addClass('correct');
                        flag = true;
                    }
                });
            }
            break;

        case "passWord":
            const passvalue = obj.value;
            if (passvalue == "") {
                msgBox.html('密码不能为空');
                msgBox.removeClass('correct').addClass('error');
                flag = false;
            } else if (passvalue.length < 6 || passvalue.length > 20) {
                msgBox.html('密码长度必须介于6到20之间');
                msgBox.removeClass('correct').addClass('error');
                flag = false;
            } else {
                msgBox.html('Correct[√]');
                msgBox.removeClass('error').addClass('correct');
                flag = true;
            }
            break;

        case "rePassWord":
            if (obj.value == "") {
                msgBox.html('确认密码不能为空');
                msgBox.removeClass('correct').addClass('error');
                flag = false;
            } else if ($(obj).val() != $('input[name="passWord"]').val()) {
                msgBox.html('密码不一致');
                msgBox.removeClass('correct').addClass('error');
                flag = false;
            } else {
                msgBox.html('Correct[√]');
                msgBox.removeClass('error').addClass('correct');
                flag = true;
            }
            break;

        case "code":
            var numshow = $(obj).next().next();
            if (obj.value == "") {
                numshow.html('验证码不能为空');
                numshow.removeClass('correct').addClass('error');
                flag = false;
            } else {
                var url = "checkusernum?num=" + encodeURI($(obj).val()) + "&" + new Date().getTime();
                $.get(url, function (numdata) {
                    if (numdata == 'false') {
                        numshow.html('验证码输入有误');
                        numshow.removeClass('correct').addClass('error');
                        flag = false;
                    } else {
                        //numshow.html().removeClass('error');
                        numshow.html('Correct[√]');
                        numshow.removeClass('error').addClass('correct');
                        flag = true;
                    }
                })
            }
            break;

        case "email":
            var emailvalue = obj.value;
            if (emailvalue != "") {
                var reg = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
                if (!reg.test(emailvalue)) {
                    msgBox.html('邮箱格式有误');
                    msgBox.removeClass('correct').addClass('error');
                    flag = false;
                } else {
                    msgBox.html('Correct[√]');
                    msgBox.removeClass('error').addClass('correct');
                    flag = true;
                }
            } else {
                flag = true;
            }
            break;

        case "mobile":
            var mobvalue = obj.value;
            if (mobvalue != "") {
                var reg = /^1\d{10}$/;
                if (!reg.test(mobvalue)) {
                    msgBox.html('手机号格式有误');
                    msgBox.removeClass('correct').addClass('error');
                    flag = false;
                } else {
                    msgBox.html('Correct[√]');
                    msgBox.removeClass('error').addClass('correct');
                    flag = true;
                }
            } else {
                flag = true;
            }
            break;
    }
}


function checkForm(frm) {
    var els = frm.getElementsByTagName('input');
    for (var i = 0; i < els.length; i++) {

        if (els[i] != null) {

            if (els[i].getAttribute("onblur")) {
                //onblur 这个属性才需要验证
                CheckItem(els[i]);
            }
        }
    }
    return flag;
}