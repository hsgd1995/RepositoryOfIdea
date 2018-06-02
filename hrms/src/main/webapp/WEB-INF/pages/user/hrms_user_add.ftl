<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="${basePath}/css/font-awesome.css">
    <style>
        .container-fluid {
            background: #f7f7f7;
            padding: 20px 30px;
        }

        .page-title {
            padding-bottom: 9px;
            margin: 10px 0 45px;
        }
    </style>
</head>
<body class="container-fluid">
<div class="row">
    <div class="col-12">
        <h4 class="border-bottom page-title">用户管理</h4>
        <h6 class="border-bottom page-title">新增用户</h6>
        <form action="${basePath}/user/add" method="post">
            <div class="form-group form-row">
                <label for="loginName" class="col-1 col-form-label">账号</label>
                <div class="col-4">
                    <input type="text" class="form-control" id="loginName" name="loginName">
                </div>
                <label for="password" class="col-1 col-form-label">密码</label>
                <div class="col-4">
                    <input type="text" class="form-control" id="password" name="password">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="username" class="col-1 col-form-label">用户名</label>
                <div class="col-4">
                    <input type="text" class="form-control" id="username" name="username">
                </div>
                <label for="status" class="col-1 col-form-label">状态</label>
                <select class="col-4 form-control" id="status"name="status">
                    <option value="0">冻结</option>
                    <option value="1">普通用户</option>
                    <option value="2">管理员</option>
                </select>
            </div>
            <div class="form-group form-row">
                <div class="col-10 text-right">
                    <button type="submit" class="btn btn-success mb-2">保存</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>