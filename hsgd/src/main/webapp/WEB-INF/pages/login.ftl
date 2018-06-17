<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mr.bad的个人系统</title>
    <link rel="shortcut icon" href="${basePath}/img/favicon.ico">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker3.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker3.standalone.css"
          rel="stylesheet">
    <style>
        div.col-6 {
            padding: 0;
        }

        div.col-6 img {
            height: 100%;
        }
    </style>
</head>
<body class="container-fluid">
<div class="row justify-content-center" style="margin-top: 150px">
    <div class="col-8 row">

        <div class="col-6 m-auto">
            <form action="${basePath}/login" method="post">
                <h4 class="text-primary">Mr.bad的个人系统</h4>
                <#if commonMessage?exists>
                    <p class="text-danger">${commonMessage.message}</p>
                </#if>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><i class="fa fa-user"></i></div>
                        </div>
                        <input type="text" class="form-control" id="loginName" name="loginName"
                               placeholder="帐号">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text"><i class="fa fa-lock"></i></div>
                        </div>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="密码">
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary form-control">登录</button>
                </div>
            </form>
        </div>
        <div class="col-1 m-auto"></div>
        <div class="col-5  m-auto">
            <img src="${basePath}/img/login_hsgd.jpg" class="img-fluid">
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
<script src="https://cdn.bootcss.com/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datepicker/1.8.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script>
    // 禁用后退按钮
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function (ev) {
        history.pushState(null, null, document.URL);
    });
</script>
</html>