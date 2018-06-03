<!DOCTYPE html>
<html lang="en">
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
            border-bottom: 5px solid darkgreen;
        }
    </style>
</head>
<body class="container-fluid">
<div class="row">
    <div class="col-12">
        <h4 class="page-title">通知管理</h4>
        <h6 class="border-bottom page-title">新增通知</h6>
        <form action="${basePath}/notice/add" method="post">
            <div class="form-group form-row">
                <label for="title" class="col-1 col-form-label">标题</label>
                <div class="col-4">
                    <input type="text" class="form-control" id="title" name="title">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="content" class="col-1 col-form-label">内容</label>
                <div class="col-4">
                <#-- 因为div的内容无法作为表单提交，所以，在提交之前将div的内容作为hidden的value就可以提交 -->
                    <input type="hidden" name="content">
                    <div class="form-control" contenteditable="true" id="content"></div>
                </div>
            </div>
            <div class="form-group form-row">
                <div class="col-5 text-right">
                    <button type="submit" class="btn btn-success mb-2">保存</button>
                </div>
            </div>
        </form>
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
    $(function () {
        $('form:first').submit(function () {
            // 将通知内容填充到hidden元素中
            $(':hidden').val($('#content').text());
        });
    });
</script>
</html>