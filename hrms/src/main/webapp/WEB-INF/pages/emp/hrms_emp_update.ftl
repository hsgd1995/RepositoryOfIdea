<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="${basePath}/css/font-awesome.css">
    <link href="https://cdn.bootcss.com/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker3.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker3.standalone.css"
          rel="stylesheet">
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
        <h4 class="page-title">员工管理</h4>
        <h6 class="border-bottom page-title">更新员工</h6>
        <form action="${basePath}/emp/update" method="post">

            <div class="form-group form-row">
                <label for="name" class="col-1 col-form-label">员工ID</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.id}" id="id" name="id">
                </div>
                <label for="name" class="col-1 col-form-label">姓名</label>
                <div class="col-4">
                    <input type="text" class="form-control" value="${employee.name}" id="name" name="name">
                </div>
            </div>

            <div class="form-group form-row">
                <label for="cardId" class="col-1 col-form-label">工号</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.cardId}" id="cardId"
                           name="cardId">
                </div>
                <label for="age" class="col-1 col-form-label">年龄</label>
                <div class="col-4">
                    <input type="text" class="form-control" value="${employee.age}" id="age" name="age">
                </div>
            </div>

            <div class="form-group form-row">
                <label for="cardId" class="col-1 col-form-label">创建日期</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly
                           value="${employee.createDate?string('yyyy-MM-dd HH:mm:ss')}" id="createDate"
                           name="createDate">
                </div>
                <label for="address" class="col-1 col-form-label">地址</label>
                <div class="col-4">
                    <input type="text" class="form-control" value="${employee.address}" id="address" name="address">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="jobId" class="col-1 col-form-label">职位</label>
                <div class="col-4">
                    <select class="col-12 form-control" id="job.id" name="job.id">
                    </select>
                </div>
                <label for="deptId" class="col-1 col-form-label">部门</label>
                <div class="col-4">
                    <select class="col-12 form-control" id="department.id" name="department.id">
                    </select>
                </div>
            </div>
            <div class="form-group form-row">
                <label for="postCode" class="col-1 col-form-label">邮编</label>
                <div class="col-4">
                    <input type="text" class="form-control" value="${employee.postCode}" id="postCode" name="postCode">
                </div>
                <label for="tel" class="col-1 col-form-label">电话号码</label>
                <div class="col-4">
                    <input type="text" class="form-control" value="${employee.tel}" id="tel" name="tel">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="phone" class="col-1 col-form-label">手机号码</label>
                <div class="col-4">
                    <input type="text" class="form-control" value="${employee.phone}" id="phone" name="phone">
                </div>
                <label for="qqNum" class="col-1 col-form-label">qq</label>
                <div class="col-4">
                    <input type="text" class="form-control" value="${employee.qqNum}" id="qqNum" name="qqNum">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="email" class="col-1 col-form-label">email</label>
                <div class="col-4">
                    <input type="text" class="form-control" value="${employee.email}" id="email" name="email">
                </div>
                <label for="sex" class="col-1 col-form-label">性别</label>

                <div class="col-4">
                    <select class="col-12 form-control" id="sex" name="sex">
                        <option value="0">男</option>
                        <option value="1">女</option>
                    </select>
                </div>
            </div>

            <div class="form-group form-row">
                <label for="party" class="col-1 col-form-label">政治面貌</label>
                <div class="col-4">
                    <input type="text" class="form-control" value="${employee.party}" id="party" name="party">
                </div>
                <label for="birthday" class="col-1 col-form-label">出生日期</label>
                <div class="col-4">
                    <input type="text" class="form-control" value="${employee.birthday?string('yyyy-MM-dd')}"
                           id="birthday" name="birthday">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="race" class="col-1 col-form-label">民族</label>
                <div class="col-4">
                    <input type="text" class="form-control" value="${employee.race}" id="race" name="race">
                </div>
                <label for="education" class="col-1 col-form-label">学历</label>
                <div class="col-4">
                    <input type="text" class="form-control" value="${employee.education}" id="education"
                           name="education">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="speciality" class="col-1 col-form-label">特长</label>
                <div class="col-4">
                    <input type="text" class="form-control" value="${employee.speciality}" id="speciality"
                           name="speciality">
                </div>
                <label for="hobby" class="col-1 col-form-label">爱好</label>
                <div class="col-4">
                    <input type="text" class="form-control" value="${employee.hobby}" id="hobby" name="hobby">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="remark" class="col-1 col-form-label">描述</label>
                <div class="col-9">
                    <input type="hidden" id="rr" name="remark">
                    <div class="form-control" contenteditable="true" id="remark"></div>
                </div>
            </div>

            <div class="form-group form-row">
                <div class="col-1 text-left">

                </div>
                <div class="col-10 text-left">
                    <button type="submit" class="btn btn-primary mb-2">提交保存</button>
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
<script src="https://cdn.bootcss.com/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datepicker/1.8.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script>

    //初始化日期插件
    $('#birthday').datepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: 1
    });

    //将remark填充到div
    $('#remark').text('${employee.remark}');


    $(function () {
        $('form:first').submit(function () {
            // 将通知内容填充到hidden元素中
            $('#rr').val($('#remark').text());
        });
    });

    //性别：将数字转换成中文
    $('#sex').val('${employee.sex}');
    //基本路径
    var basePath = '${basePath}';

    //加载部门列表
    $.get(basePath + '/dept/list', {pageIndex: 1, pageSize: 100}, function (data) {

        //获取标签元素
        var obj = document.getElementById("department.id");

        var list = data.pageList;
        for (var index = 0; index < list.length; index++) {
            //添加一个选项
            obj.add(new Option(list[index].name, list[index].id));
        }
        $(document.getElementById("department.id")).val('${employee.department.id}');
    }, 'json');

    //加载职位列表
    $.get(basePath + '/job/list', {pageIndex: 1, pageSize: 100}, function (data) {

        //获取标签元素
        var obj = document.getElementById("job.id");

        var list = data.pageList;
        for (var index = 0; index < list.length; index++) {
            //添加一个选项
            obj.add(new Option(list[index].name, list[index].id));
        }
        $(document.getElementById("job.id")).val('${employee.job.id}');
    }, 'json');

</script>
</html>