<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/font-awesome.css">
    <style>
        .container-fluid {
            background: #f7f7f7;
        }

        h4.page-title {
            padding-bottom: 9px;
            margin: 10px 0 45px;
        }
    </style>
</head>
<body class="container-fluid">
<div class="row">
    <div class="col-12">
        <h4 class="border-bottom page-title">员工管理</h4>
        <table class="table table-sm table-bordered">
            <thead class="table-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">工号</th>
                <th scope="col">姓名</th>
                <th scope="col">性别</th>
                <th scope="col">年龄</th>
                <th scope="col">生日</th>
                <th scope="col">民族</th>
                <th scope="col">学历</th>
                <th scope="col">政治面貌</th>
                <th scope="col">备注</th>
                <th scope="col">操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
                <td>@mdo</td>
                <td>@mdo</td>
                <td>@mdo</td>
                <td>@mdo</td>
                <td>@mdo</td>
                <td>@mdo</td>
                <td>
                    <button type="button" class="btn btn-sm btn-outline-primary btn-small">查看</button>
                    <button type="button" class="btn btn-sm btn-outline-secondary">冻结</button>
                    <button type="button" class="btn btn-sm btn-outline-success">更新</button>
                    <button type="button" class="btn btn-sm btn-outline-danger">删除</button>
                </td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
                <td>Thornton</td>
                <td>@fat</td>
                <td>@mdo</td>
                <td>@mdo</td>
                <td>@mdo</td>
                <td>@mdo</td>
                <td>
                    <button type="button" class="btn btn-sm btn-outline-primary">查看</button>
                    <button type="button" class="btn btn-sm btn-outline-secondary">冻结</button>
                    <button type="button" class="btn btn-sm btn-outline-success">更新</button>
                    <button type="button" class="btn btn-sm btn-outline-danger">删除</button>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="col-12">
            <div class="row">
                <ul class="col-6 pagination">
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                    <li class="page-item"><a class="page-link" href="javacript:void(0);">1</a></li>
                    <li class="page-item"><a class="page-link" href="javacript:void(0);">2</a></li>
                    <li class="page-item"><a class="page-link" href="javacript:void(0);">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </ul>
                <p class="col-6 text-right">Displaying 1 to 10 14 items.</p>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script>
    $(function () {
    });
</script>
</html>