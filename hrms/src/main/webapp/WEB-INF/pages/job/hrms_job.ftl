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

        h4.page-title {
            padding-bottom: 9px;
            margin: 10px 0 45px;
        }
    </style>
</head>
<body class="container-fluid">
<div class="row">
    <div class="col-12">
        <h4 class="border-bottom page-title">职位管理</h4>
        <input type="hidden" id="basePath" value="${basePath}">
          <#if commonMessage?exists>
            <p>${commonMessage.message}</p>
          </#if>
        <div class="row">
            <div class="col-3">
                <div class="form-group">
                    <button class="btn btn-sm btn-primary" onclick="addItem();">新增</button>
                </div>
            </div>
            <div class="col-9">
                <div class="form-group form-row">
                    <label for="name" class="col-form-label text-right">职位名称:</label>
                    <div class="col">
                        <input type="text" class="form-control" id="name" name="name" autocomplete="off">
                    </div>

                    <div class="col">
                        <button class="btn btn-sm btn-primary" onclick="query();">查询</button>
                        <button class="btn btn-sm btn-primary" onclick="reset();">重置</button>
                    </div>
                </div>
            </div>
        </div>
        <table class="table table-bordered">
            <thead class="table-dark">
            <tr>
                <th scope="col"></th>
                <th scope="col">#</th>
                <th scope="col">职位ID</th>
                <th scope="col">职位名称</th>
                <th scope="col">备注</th>
                <th scope="col">操作</th>
            </tr>
            </thead>
            <tbody>

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
                <p class="col-6 text-right" id="displaying">Displaying 0 to 0 page 0 items.</p>
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
<script src="${basePath}/js/job/hrms-job.js"></script>
<script>
    $(function () {
    });
</script>
</html>