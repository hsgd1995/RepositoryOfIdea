<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mr.bad的个人系统</title>

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/css/font-awesome.css">

    <script src="${basePath}/js/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <style>
        a {
            color: white;
        }

        a:hover {
            color: orangered;
        }

        ul {
            color: #80bdff;
        }

        .north {
            background: #09192A;
            color: white;
            height: 100px;
        }

        .menu {
            background: #09192A;
            padding: 0;
            height: 0px;
            padding-bottom: 100%;
        }

        .menu li {
            height: 50px;
            line-height: 50px;
            text-indent: 1em;
            color: white;
        }

        .menu li:hover {
            background: #0c5460;
            cursor: pointer;
        }

        .page-wrapper {
            padding: 0;
        }
    </style>

</head>
<body class="container-fluid">
<div class="row north">
    <div class="col-6">
        <p class="h2">Mr.bad的个人系统
            <small>v2018</small>
        </p>
    </div>
    <div class="col-6 align-self-end text-right">
        <ul class="list-inline">
            <li class="list-inline-item dropdown">
                <button class="btn btn-sm btn-dark dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="icon-user">${user.loginName}</span>
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="javascript:void(0);" onclick="personel(${user.id})">个人中心</a>
                    <div class="dropdown-driver"></div>
                    <a class="dropdown-item" href="javascript:void(0);" onclick="logout();">安全退出</a>
                </div>
            </li>
            <li class="list-inline-item"><span id="systime">系统时间</span></li>
        </ul>
    </div>
</div>
<div class="col-12 row" style="height: 50px">
    <div class="col-3">cccc</div>
    <div class="col-9">
        导航栏
    </div>
</div>
<div class="row main">
    <div class="col-3 menu">
        <ul class="list-unstyled">
            <li id="wel" value="/user/test1"><span class="icon-home"></span>欢迎使用</li>
            <li id="use" value="/user/test2"><span class="icon-user"></span>用户管理</li>
            <li><span class="icon-sitemap"></span>部门管理</li>
            <li><span class="icon-suitcase"></span>职位管理</li>
            <li><span class="icon-group"></span>员工管理</li>
            <li><span class="icon-volume-up"></span>公告管理</li>
            <li><span class="icon-download-alt"></span>下载中心</li>
        </ul>
        <p style="font-size: small;color: white;text-align: center;">技术支持：Mr.bad<a href=""></a>
        </p>
    </div>

    <div class="col-9 container-fluid" style="padding-left: 0px">
        <ul id="myTab" class="nav nav-tabs">


        </ul>
        <div  class="tab-content page-wrapper" style="height: 100%">

        </div>

    </div>
</div>
</body>

<script>

    // 禁用后退按钮
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function (ev) {
        history.pushState(null, null, document.URL);
    });

    $(function () {
        // 菜单点击事件
        $('.menu li').click(function (event) {
            // 1.调整样式：让被点击的li改变背景色为浅色，其它变为深色
            $('.menu li').each(function (i) {
                $(this).css('background', '#09192A');
            });
            $(this).css('background', '#0c5460');

            console.log('li value ->',$(this).attr('value'));


            // 2.点击了某个菜单，在中心区域加载指定的页面
            addTab(this,$(this).attr('value'));
        });
    });

    /**
     * 创建tab
     * that li对象
     * url 要访问的路径
     **/
    function addTab(that,url) {
        var id = $(that).attr('id');
        console.log('id',id);
        var tabId = 'tabId'+id;
        console.log('tabId',tabId);
        var pageId = 'pageId'+id;

        console.log('tabIduse -->',$('#'+tabId).length);
        //如果标签页不存在则创建
        if($('#'+tabId).length==0){
            console.log('不存在');
            $('#myTab').append($('<li class="active"><a id="'+tabId+'" href="#'+pageId+'" data-toggle="tab" style="color: #005cbf">'+$(that).text()+'</a></li>'));
            var con = "<iframe src='${basePath}"+url+"' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>";
            $('.page-wrapper').append($('<div id="'+pageId+'" class="tab-pane fade in active" style="height: 100% ">'+con+'</div>'));
        }else{

        }

        $('#'+tabId).parent().attr('class','active');


        //显示标签页
        $('#'+tabId).tab('show');

        $('#'+pageId).attr('class','tab-pane fade in active show');


        //取消原先选中的标签
        //console.log('选择的a-》》',$('#myTab li[class=active] a').attr('id'));

        $('#myTab li a').each(function (i) {
            console.log('所有a的id',$(this).attr('id'));
            var tabIdOfA = $(this).attr('id');
            if(tabIdOfA != tabId){
                $(this).parent().attr('class','');
                var pageIdOfA = 'pageId'+tabIdOfA.split('tabId')[1];
                console.log('pageIdOfA',pageIdOfA);
                $('#'+pageIdOfA).attr('calss','tab-pane fade');
            }
        });


    }


    /**
     * 设置时间
     **/
    systime = '当前时间: ' + dateFormat(new Date(), 'yyyy-MM-dd HH:mm:ss');
    $('#systime').text(systime);
    setInterval(function () {
        systime = '当前时间: ' + dateFormat(new Date(), 'yyyy-MM-dd HH:mm:ss');
        $('#systime').text(systime);
    }, 1000);

    /**
     * 日期格式化
     *
     * @param date
     * @param pattern
     * @returns
     */
    function dateFormat(date, pattern) {
        if ('yyyy-MM-dd HH:mm:ss' === pattern) {

            var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1)
                    : (date.getMonth() + 1);
            var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
            return date.getFullYear() + '-' + month + '-' + day + ' '
                    + date.getHours() + ':' + date.getMinutes() + ':'
                    + date.getSeconds();
        }
    }

    function logout() {
        if (confirm('确认退出吗？')) {
            location.href = "${basePath}/logout";
        }
    }

    function personel(id) {
        $('.page-wrapper').html("<iframe src='${basePath}/personel/" + id + "' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
    }
</script>
</html>