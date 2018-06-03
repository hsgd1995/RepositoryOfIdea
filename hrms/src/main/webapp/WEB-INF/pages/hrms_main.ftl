<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台·人力管理系统</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="${basePath}/css/font-awesome.css">
    <style>
        a {
            color: white;
        }

        a:hover {
            color: orangered;
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
        <p class="h2">Entor人力管理系统
            <small>v2018</small>
        </p>
    </div>
    <div class="col-6 align-self-end text-right">
        <ul class="list-inline">
            <li class="list-inline-item">当前用户:<a href="javascript:alert('${user.username}')">${user.username}</a></li>
            <li class="list-inline-item"><span id="systime">系统时间</span></li>
            <li class="list-inline-item"><a href="javascript:alert('安全退出')">安全退出</a></li>
        </ul>
    </div>
</div>
<div class="row main">
    <div class="col-3 menu">
        <ul class="list-unstyled">
            <li><span class="icon-home"></span>欢迎使用</li>
            <li><span class="icon-user"></span>用户管理</li>
            <li><span class="icon-sitemap"></span>部门管理</li>
            <li><span class="icon-suitcase"></span>职位管理</li>
            <li><span class="icon-group"></span>员工管理</li>
            <li><span class="icon-volume-up"></span>公告管理</li>
            <li><span class="icon-download-alt"></span>下载中心</li>
        </ul>
        <p style="font-size: small;color: white;text-align: center;">技术支持：广西南宁易唐科技<a href="http://www.entor.com.cn/">http://www.entor.com.cn/</a>
        </p>
    </div>
    <div class="col-9 page-wrapper">
        <iframe src="${basePath}/notice/welcome" width="100%" height="100%" FRAMEBORDER="0" NAME="_blank" id="_blank"></iframe>
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
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
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

            // 2.点击了某个菜单，在中心区域加载指定的页面
            switch ($(this).text()) {
                case '欢迎使用':
                    $('.page-wrapper').html("<iframe src='${basePath}/notice/welcome' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
                    break;
                case '用户管理':
                    $('.page-wrapper').html("<iframe src='user' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
                    break;
                case '部门管理':
                    $('.page-wrapper').html("<iframe src='dept' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
                    break;
                case '职位管理':
                    $('.page-wrapper').html("<iframe src='job' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
                    break;
                case '员工管理':
                    $('.page-wrapper').html("<iframe src='emp' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
                    break;
                case '公告管理':
                    $('.page-wrapper').html("<iframe src='notice' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
                    break;
                case '下载中心':
                    alert('6')
                    break;
            }
        });
    });

    /**
     * 设置时间
     **/
    systime = '当前时间: ' + dateFormat(new Date(), 'yyyy-MM-dd HH:mm:ss');
    $('#systime').text(systime);
    setInterval(function() {
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
</script>
</html>