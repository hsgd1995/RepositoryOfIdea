<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet">
    <style>
        .container-fluid {
            background: #f7f7f7;
            padding: 20px 30px;
        }

        .page-title {
            padding-bottom: 9px;
            margin: 10px 0 45px;
            border-bottom: 5px solid darkorange;
        }

        .modal-dialog {
            width: 300px;
            margin: 0 auto;
            margin-top: -260px;
            font-size: 14px;
        }
    </style>
</head>
<body class="container-fluid">
<div class="row">
    <div class="col-12">
        <h4 class="page-title">个人中心</h4>
        <h6 class="border-bottom page-title">个人信息</h6>
         <#if commonMessage?exists>
            <p>${commonMessage.message}</p>
         </#if>
        <div class="form-group form-row">
            <label for="id" class="col-1 col-form-label">用户ID</label>
            <div class="col-4">
                <input type="text" class="form-control" readonly id="id" value="${user.id}">
            </div>
            <label for="loginName" class="col-1 col-form-label">帐号</label>
            <div class="col-4">
                <input type="text" class="form-control" readonly id="loginName" value="${user.loginName}">
            </div>
        </div>
        <div class="form-group form-row">
            <label for="password" class="col-1 col-form-label">密码</label>
            <div class="col-4">
                <input type="text" class="form-control" readonly id="password" value="${user.password}">
            </div>
            <label for="username" class="col-1 col-form-label">用户名</label>
            <div class="col-4">
                <input type="text" class="form-control" readonly id="username" value="${user.username}">
            </div>
        </div>
        <div class="form-group form-row">
            <div class="col-10 text-right">
                <button class="btn btn-primary" onclick="alterpass();">修改密码</button>
            </div>
        </div>
    </div>
    <!-- 模态框 -->
    <div class="modal fade" id="userModalCenter" tabindex="-1" role="dialog" aria-labelledby="userModalCenterTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="userModalLongTitle">修改密码</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="${basePath}/personel/edit" method="post" id="SubmitForm">
                        <input type="hidden" name="id" value="${user.id}">
                        <div class="form-group form-row">
                            <label for="newpass" class="col-3 col-form-label">新密码</label>
                            <div class="col-9">
                                <input type="password" class="form-control" id="newpass" name="password">
                            </div>
                        </div>
                        <div class="form-group form-row">
                            <label for="repass" class="col-3 col-form-label">再次输入</label>
                            <div class="col-9">
                                <input type="password" class="form-control" id="repass">
                            </div>
                        </div>
                        <div class="form-group form-row">
                            <div class="col-12 text-right">
                                <button type="submit" class="btn btn-primary" onclick="suerPassword();">保存修改</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="${basePath}/js/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script>
    /**
     * 修改密码
     */
    function alterpass() {
        // 打开模态框
        $('#userModalCenter').modal('show');
    }

    function suerPassword() {
        $('#SubmitForm').submit(function () {
            var newpass = $('#newpass').val();
            var repass = $('#repass').val();
            console.log('newpass:'+newpass+',repass:'+repass);
            if (newpass == null || newpass == '' || newpass == undefined){
                alert('请输入新密码!');
                return false;
            }
            if (repass == null || repass == '' || repass == undefined){
                alert('请再次输入密码！');
                return false;
            }
            if(newpass !=repass){
                alert('两次密码不一致！');
                return false;
            }
            return true;
        });
        return;
    }
</script>
</html>