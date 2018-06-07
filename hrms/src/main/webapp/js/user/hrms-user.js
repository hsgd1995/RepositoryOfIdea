$(function () {
    //定义全局变量
    basePath = $('#basePath').val();

    //初始化日期插件
    $('#createDate').datepicker({
        language:'zh-CN',
        format:'yyyy-mm-dd',
        autoclose:1
    });



    //第一次加载页面，需要首页数据
    $.get(basePath + '/user/list', {user: null, pageIndex: 1, pageSize: 4}, function (data) {
        dataHandler(data);
        console.log(data);
    }, 'json');
});

/**
 * 分页数据处理
 * @param data
 */
function dataHandler(data) {
    //总记录数
    var recordCount = data.recordCount;
    //当前页面
    var pageIndex = data.pageIndex;
    //当前分页数
    var pageSize = data.pageSize;
    //列表数据
    var pageList = data.pageList;

    //列表数据处理
    listHandler(pageList);

    //分页信息处理
    /*if (pageList)
        pageHandler(recordCount, pageIndex, pageSize);
    else
        pageHandler(0, 1, 4);*/
    pageHandler(recordCount, pageIndex, pageSize);
}

/**
 * 列表数据处理
 * @param list
 */
function listHandler(list) {
    //tbody在追加列表之前必须是空的
    $('tbody').html('');

    //每行数据对应6列，分别是序号，用户ID，账户，用户名，创建时间和操作
    //动态加载列表数据，通过js方式创建元素并附加到tbody
    //数据行数取决于list的元素个数，遍历list
    if (!list){
        return;
    }
    for (var index = 0; index < list.length; index++) {
        //定义每行记录对应的user对象
        //定义行
        var tr = $('<tr></tr>');
        var id = list[index].id;
        //定义列
        var ck = $('<th scope="row"><input type="checkbox"></th>');
        var th = $('<th scope="row">' + (index + 1) + '</th>');
        var userId = $('<td>' + list[index].id + '</td>');
        var loginName = $('<td>' + list[index].loginName + '</td>');
        var username = $('<td>' + list[index].username + '</td>');
        var createDate = $('<td>' + list[index].createDate + '</td>');
        var operation = '<td><button type="button" class="btn-sm btn-outline-primary" onclick="lookItem('+id+')">查看</button>';
        if (list[index].status == 0) {
            operation = operation + '<button type="button" class="btn-sm btn-outline-secondary" onclick="frozeItem(this,1);">解冻</button>';
        } else {
            operation = operation + '<button type="button" class="btn-sm btn-outline-secondary" onclick="frozeItem(this,0);">冻结</button>';
        }
        operation = operation + '<button type="button" class="btn-sm btn-outline-success" onclick="updateItem(this)"> 更新</button>' +
            '<button type="button" class="btn-sm btn-outline-danger" onclick="deleteItem(this);">删除</button></td>';
        // 组行和列并附加到tbody
        $('tbody').append(tr.append(ck).append(th).append(userId).append(loginName).append(username).append(createDate).append(operation));
    }
}

/**
 * 分页信息处理
 * @param recordCount
 * @param pageIndex
 * @param pageSize
 */
function pageHandler(recordCount, pageIndex, pageSize) {
    //清除旧的分页栏
    $('.page-link').each(function () {
        if (!$(this).attr('aria-label')) {
            $(this).parent().remove();
        }
    });

    //获得总页数，备用
    var totalPage = getTotalPage(recordCount, pageSize);
    console.log('总页数', totalPage);
    //处理左侧分页栏
    //分页最多显示3页
    if (totalPage <= 3) {
        for (var i = 1; i <= totalPage; i++) {
            $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + i + '</a></li>'));
        }
    } else {
        // 页数超过3，显示当前页以及它的前一页和后一页
        if (pageIndex == 1) {// 如果当前页是第一页
            for (var i = 1; i <= 3; i++) {
                $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + i + '</a></li>'));
            }
        } else if (pageIndex == totalPage) {// 如果当前页是最后一页
            for (var i = totalPage - 2; i <= totalPage; i++) {
                $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + i + '</a></li>'));
            }
        } else {
            $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + (pageIndex - 1) + '</a></li>'));
            $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + pageIndex + '</a></li>'));
            $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + (pageIndex + 1) + '</a></li>'));
        }
    }


    //当前页设置背景色
    $('.page-link').each(function () {
        if ($(this).text() == pageIndex) {
            $(this).css('color', '#e9ecef');
            $(this).css('background', '#0056b3');
        } else {
            $(this).css('background', '#e9ecef');
            $(this).css('color', '#0056b3');
        }
    });

    //为分页栏绑定事件
    //上一页
    $('.page-link:first').unbind().click(function () {
        prePage(pageIndex, pageSize);
    });
    //下一页
    $('.page-link:last').unbind().click(function () {
        nextPage(pageIndex, pageSize, totalPage);
    });

    //处理右侧打印信息
    $('#displaying').text('Displaying ' + pageIndex + ' to ' + totalPage + ' page, total ' + recordCount + 'item.');
}

/**
 * 计算总页数
 * @param recordCount
 * @param pageSize
 */
function getTotalPage(recordCount, pageSize) {
    return recordCount % pageSize == 0 ? recordCount / pageSize : parseInt(recordCount / pageSize + 1);
}

/**
 * 上一页
 * @param pageIndex
 * @param pageSize
 */
function prePage(pageIndex, pageSize) {
    //1.获取查询关键字
    var username = $('#username').val();
    var createDate = $('#createDate').val();
    var status = $('#status').val();

    //当前页为第二页及以上
    if (pageIndex - 1 >= 1) {
        $.get(basePath + '/user/list',
            {
                username: username,
                createDate: createDate,
                status: status,
                pageIndex: pageIndex - 1,
                pageSize: pageSize
            }, function (data) {
                dataHandler(data);
            }, 'json');
    }
}

/**
 * 下一页
 * @param pageIndex
 * @param pageSize
 * @param totalPage
 */
function nextPage(pageIndex, pageSize, totalPage) {
    //1.获取查询关键字
    var username = $('#username').val();
    var createDate = $('#createDate').val();
    var status = $('#status').val();

    //如果不是最后一页
    if (pageIndex + 1 <= totalPage) {
        $.get(basePath + '/user/list', {
            username: username,
            createDate: createDate,
            status: status,
            pageIndex: pageIndex + 1,
            pageSize: pageSize
        }, function (data) {
            dataHandler(data);
        }, 'json');
    }
}

/**
 * 当前页
 * @param that
 * @param pageSize
 */
function currPage(that, pageSize) {
    //1.获取查询关键字
    var username = $('#username').val();
    var createDate = $('#createDate').val();
    var status = $('#status').val();
    $.get(basePath + '/user/list', {
        username: username,
        createDate: createDate,
        status: status,
        pageIndex: that.text,
        pageSize: pageSize
    }, function (data) {
        dataHandler(data);
    }, 'json');
}

function query() {
    //1.获取查询关键字
    var username = $('#username').val();
    var createDate = $('#createDate').val();
    var status = $('#status').val();
    //2.请求服务器
    $.getJSON(basePath + '/user/list', {username: username, createDate: createDate, status: status}, function (data) {
        dataHandler(data);
    });
}


/**
 * 新增记录
 */
function addItem() {
    console.log('添加用户');
    $(parent.document).find('.page-wrapper').html("<iframe src='" + basePath + "/user/add' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
}

/**
 * 查看记录
 * @param id
 */
function lookItem(id) {
    //1.获得查看行id
    console.log('用户id', id);
    //2.加载查看页面
    //2-1.查看页面是请求服务器得到的
    $(parent.document).find('.page-wrapper').html("<iframe src='" + basePath + "/user/look/" + id + "' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
}

/**
 * 更新记录
 * @param that
 */
function updateItem(that) {
    console.log('更新用户');
    //1.获得查看行id
    var userId = getIdOfRow(that);
    console.log('用户id', userId);
    //2.加载更新页面
    //2-1.更新页面是请求服务器得到的

    $(parent.document).find('.page-wrapper').html("<iframe src='" + basePath + "/user/update?id=" + userId + "' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
}

/**
 * 删除记录
 * @param that
 */
function deleteItem(that) {
    console.log('删除记录');
    //1.设置模态框
    initModal('删除', '确认删除吗？', '取消', '提交');
    //2.显示模态框
    $('#userModalCenter').modal('show');
    //3.按钮事件
    $('#userModalCenter .modal-footer .btn-primary').unbind().click(function () {
        //1.关闭之前的模态框
        $('#userModalCenter').modal('hide');
        //2.获得删除行的userId
        var userId = $(that).parent().siblings().eq(1).text();
        //3.请求服务器删除该记录
        $.getJSON(
            basePath + '/user/del/' + userId, function (data) {
                alert(data.message);
                //3-1刷新列表
                $.getJSON(basePath + '/user/list', {user: null, pageIndex: 1, pageSize: 4}, function (data) {
                    dataHandler(data);
                });
            }
        );
    });
}

/**
 * 批量删除
 */
function batchDeleteItems() {
    if($(":checkbox:checked").length==0){
        alert('请先选择要删除的用户...');
        return;
    }
    //1.设置模态框
    initModal('删除','确认删除'+$(":checkbox:checked").length+'条记录吗？','取消','确认')
    //2.显示模态框
    $('#userModalCenter').modal('show');
    //3.按钮事件
    $('#userModalCenter .modal-footer .btn-primary').unbind().click(function () {
        //4.关闭模态框
        $('#userModalCenter').modal('hide');
        //5.获取id
        var ids = new Array();
        for (var index = 0;index<$(':checkbox:checked').length;index++){
            $(':checkbox:checked').each(function () {
                ids.push(getIdOfRow($(this)));
            });
        }
        //6.请求服务器删除记录
        //此处传递参数可以加入查询条件和分页数据
        console.log("ids:",ids);
        $.get(basePath+'/user/batchDel',{ids:ids},function (data) {
            //7.显示提示信息
            alert(data.message);
            //8.刷新记录
            $.get(basePath+'/user/list',{notice:null,pageIndex:1,pageSize:4},function (listData) {
                dataHandler(listData);
            });
        },'json');
    });


}

/**
 * 冻结/解冻记录
 * @param that
 * @param status
 */
function frozeItem(that, status) {
    console.log('冻结/解冻记录');
    //1.设置模态框
    if (status == 0)
        initModal('冻结确认', '确认冻结吗？', '取消', '提交');
    else
        initModal('解冻确认', '确认解冻吗？', '取消', '提交');
    //2.显示模态框
    $('#userModalCenter').modal('show');
    //3.按钮事件
    $('#userModalCenter .modal-footer .btn-primary').unbind().click(function () {
        //1.关闭之前的模态框
        $('#userModalCenter').modal('hide');
        //2.获得删除的行的userId
        var userId = $(that).parent().siblings().eq(1).text();
        //3.请求服务器删除该记录
        $.post(basePath + '/user/froze', {id: userId, status: status}, function (data) {
            alert(data.message);
            //3-1.刷新列表
            $.getJSON(basePath + '/user/list', {user: null, pageIndex: 1, pageSize: 4}, function (data) {
                dataHandler(data);
            });
        }, 'json');
    });
}

/**
 * 导出用户记录到Excel文档
 */
function exportItems() {
    if($(':checkbox:checked').length==0){
        alert('请先选择要导出的用户...');
        return;
    }
    //1.设置模态框
    initModal('导出','确认导出'+$(":checkbox:checked").length+'条记录吗？','取消','确认')
    //2.显示模态框
    $('#userModalCenter').modal('show');
    //3.按钮事件
    $('#userModalCenter .modal-footer .btn-primary').unbind().click(function () {
        //4.关闭模态框
        $('#userModalCenter').modal('hide');
        //5.获取id
        var ids = new Array();
        for (var index = 0;index<$(':checkbox:checked').length;index++){
            $(':checkbox:checked').each(function () {
                ids.push(getIdOfRow($(this)));
            });
        }
        //6.请求服务器导出
        location.href = basePath + '/user/export?ids[]='+ids;
    });
}


/**
 * 获取行的id
 * @param that
 * @returns {jQuery}
 */
function getIdOfRow(that) {
    return $(that).parent().parent().children().eq(2).text();
}


/**
 * 初始化模态框
 * @param title
 * @param body
 * @param btn1
 * @param btn2
 */
function initModal(title, body, btn1, btn2) {
    //标题
    $('#userModalCenter .modal-title').text(title);
    //内容
    $('#userModalCenter .modal-body').text(body);
    //按钮
    if (btn1)
        $('#userModalCenter .modal-footer .btn-secondary').text(btn1);
    if (btn2)
        $('#userModalCenter .modal-footer .btn-primary').text(btn2);
    if (!btn1 && btn2)
        $('userModalCenter .modal-footer').hide();
    else
        $('#userModalCenter .modal-footer').show();
}