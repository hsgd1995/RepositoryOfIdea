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
        <h6 class="border-bottom page-title">查看员工</h6>
        <form action="${basePath}/emp/add" method="post">

            <div class="form-group form-row">
                <label for="name" class="col-1 col-form-label">员工ID</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.id}" id="id" name="id">
                </div>
                <label for="name" class="col-1 col-form-label">姓名</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.name}" id="name" name="name">
                </div>
            </div>

            <div class="form-group form-row">
                <label for="cardId" class="col-1 col-form-label">工号</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.cardId}" id="cardId" name="cardId">
                </div>
                <label for="age" class="col-1 col-form-label">年龄</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.age}" id="age" name="age">
                </div>
            </div>

            <div class="form-group form-row">
                <label for="cardId" class="col-1 col-form-label">创建日期</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.createDate?string('yyyy-MM-dd HH:mm:ss')}" id="createDate" name="createDate">
                </div>
                <label for="address" class="col-1 col-form-label">地址</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.address}" id="address" name="address">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="jobId" class="col-1 col-form-label">职位</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.job.name}" id="job.id" name="job.id">
                </div>
                <label for="deptId" class="col-1 col-form-label">部门</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.department.name}" id="department.id" name="department.id">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="postCode" class="col-1 col-form-label">邮编</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.postCode}" id="postCode" name="postCode">
                </div>
                <label for="tel" class="col-1 col-form-label">电话号码</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.tel}" id="tel" name="tel">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="phone" class="col-1 col-form-label">手机号码</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.phone}" id="phone" name="phone">
                </div>
                <label for="qqNum" class="col-1 col-form-label">qq</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.qqNum}" id="qqNum" name="qqNum">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="email" class="col-1 col-form-label">email</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.email}" id="email" name="email">
                </div>
                <label for="sex" class="col-1 col-form-label">性别</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly  value="${(employee.sex == 0 )?string('男','女')}" id="sex" name="sex">
                </div>
            </div>

            <div class="form-group form-row">
                <label for="party" class="col-1 col-form-label">政治面貌</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.party}" id="party" name="party">
                </div>
                <label for="birthday" class="col-1 col-form-label">出生日期</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.birthday?string('yyyy-MM-dd')}" id="birthday" name="birthday">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="race" class="col-1 col-form-label">民族</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.race}" id="race" name="race">
                </div>
                <label for="education" class="col-1 col-form-label">学历</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.education}" id="education" name="education">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="speciality" class="col-1 col-form-label">特长</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.speciality}" id="speciality" name="speciality">
                </div>
                <label for="hobby" class="col-1 col-form-label">爱好</label>
                <div class="col-4">
                    <input type="text" class="form-control" readonly value="${employee.hobby}" id="hobby" name="hobby">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="remark" class="col-1 col-form-label">描述</label>
                <div class="col-9">
                    <input type="hidden" name="remark">
                    <div class="form-control" readonly contenteditable="true" id="remark"></div>
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


    //将remark填充到div
    $('#remark').text('${employee.remark}');


    $(function () {
        $('form:first').submit(function () {
            // 将通知内容填充到hidden元素中
            $(':hidden').val($('#remark').text());
        });
    });


</script>
</html>