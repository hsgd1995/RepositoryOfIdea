$(function () {
    // 定义全局变量
    basePath = $('#basePath').val();



    //1.第一次加载页面，需要首页数据
    $.get(basePath + '/dept/list', function (data) {
        dataHandler(data);
    }, 'json');
});

/**
 * 分页数据处理
 * @param data
 */
function dataHandler(data) {
    //总记录数
    var recordCount = data.recordCount;
    // 当前页码
    var pageIndex = data.pageIndex;
    // 当前分页数
    var pageSize = data.pageSize;
    // 列表数据
    var pageList = data.pageList;

    // 列表数据处理
    listHandler(pageList);

    // 分页信息处理
    if (pageList)
        pageHandler(recordCount, pageIndex, pageSize);
    else
        pageHandler(0, 1, 4);
}

/**
 * 列表数据处理
 */
function listHandler(list) {
    //  tbody在追加列表之前必须是空的
    $('tbody').html('');

    // 列表不存在
    if (!list)
        return;

    // 动态加载列表数据，通过js方式创建元素并附加到tbody
    // 数据行数取决于list的元素个数，遍历list
    for (var index = 0; index < list.length; index++) {
        // 定义每行记录对应的department对象
        // 定义行
        var tr = $('<tr></tr>');
        var id = list[index].id;
        // 定义列
        var ck = $('<th scope="row"><input type="checkbox"></th>')
        var th = $('<th scope="row">' + (index + 1) + '</th>');
        var departmentId = $('<td>' + list[index].id + '</td>');
        var name = $('<td>' + list[index].name + '</td>');
        var remark = $('<td>' + list[index].remark + '</td>');
        var operation = '<td><button type="button" class="btn-sm btn-outline-primary" onclick="lookItem('+id+');">查看</button>';
        operation = operation + '<button type="button" class="btn-sm btn-outline-success" onclick="updateItem('+id+');"> 更新</button>' +
            '<button type="button" class="btn-sm btn-outline-danger" onclick="deleteItem('+id+');">删除</button></td>';
        // 组行和列并附加到tbody
        $('tbody').append(tr.append(ck).append(th).append(departmentId).append(name).append(remark).append(operation));
    }
}

/**
 * 分页信息处理
 * @param recordCount
 * @param pageIndex
 * @param pageSize
 */
function pageHandler(recordCount, pageIndex, pageSize) {
    // 清除旧的分页栏
    $('.page-link').each(function () {
        if (!$(this).attr('aria-label')) {
            $(this).parent().remove();
        }
    });

    // 获得总页数，备用
    var totalPage = getTotalPage(recordCount, pageSize);

    //  处理左侧分页栏
    //  分页最多显示3页
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
        } else if (pageIndex == totalPage) {// 如果当前页是第一页
            for (var i = totalPage - 2; i <= totalPage; i++) {
                $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + i + '</a></li>'));
            }
        } else {
            $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + (pageIndex - 1) + '</a></li>'));
            $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + pageIndex + '</a></li>'));
            $('.page-item:last').before($('<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="currPage(this,' + pageSize + ');">' + (pageIndex + 1) + '</a></li>'));
        }
    }

    // 当前页设置背景色
    $('.page-link').each(function () {
        if ($(this).text() == pageIndex) {
            $(this).css('background', '#0056b3');
            $(this).css('color', '#e9ecef');
        } else {
            $(this).css('background', '#e9ecef');
            $(this).css('color', '#0056b3');
        }
    });

    // 为分页栏绑定事件
    // 上一页
    $('.page-link:first').unbind().click(function () {
        prePage(pageIndex, pageSize);
    });
    // 下一页
    $('.page-link:last').unbind().click(function () {
        nextPage(pageIndex, pageSize, totalPage);
    });

    // 处理右侧打印信息如：Displaying 1 to 10 14 items.
    $('#displaying').text('Displaying ' + pageIndex + ' to ' + totalPage + ' page,  total ' + recordCount + " items.");
}

/**
 * 计算总页数
 * @param recordCount
 * @param pageSize
 * @returns {number}
 */
function getTotalPage(recordCount, pageSize) {
    return recordCount % pageSize == 0 ? recordCount / pageSize : parseInt(recordCount / pageSize + 1);
}

/**
 * 上一页
 */
function prePage(pageIndex, pageSize) {
    // 1.获取查询关键字
    var title = $('#title').val();
    var createDate = $('#createDate').val();
    var content = $('#content').val();

    if (pageIndex - 1 >= 1) {
        $.get(basePath + '/dept/list', {
            title: title,
            createDate: createDate,
            content: content, pageIndex: pageIndex - 1, pageSize: pageSize
        }, function (data) {
            dataHandler(data);
        }, 'json');
    }
}

/**
 * 下一页
 */
function nextPage(pageIndex, pageSize, totalPage) {

    // 1.获取查询关键字
    var title = $('#title').val();
    var createDate = $('#createDate').val();
    var content = $('#content').val();

    if (pageIndex + 1 <= totalPage) {
        $.get(basePath + '/dept/list', {
            title: title,
            createDate: createDate,
            content: content,
            pageIndex: pageIndex + 1,
            pageSize: pageSize
        }, function (data) {
            dataHandler(data);
        }, 'json');
    }
}

/**
 * 当前页
 */
function currPage(that, pageSize) {
    // 1.获取查询关键字
    var title = $('#title').val();
    var createDate = $('#createDate').val();
    var content = $('#content').val();

    $.get(basePath + '/dept/list', {
        title: title,
        createDate: createDate,
        content: content,
        pageIndex: that.text,
        pageSize: pageSize
    }, function (data) {
        dataHandler(data);
    }, 'json');
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
 * 查看记录
 * @param id
 */
function lookItem(id) {
    //1.获得查看行id
    console.log('部门id', id);
    //2.加载查看页面
    //2-1.查看页面是请求服务器得到的
    $(parent.document).find('.page-wrapper').html("<iframe src='" + basePath + "/dept/look/" + id + "' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
}

/**
 * 更新
 * @param id
 */
function updateItem(id) {
    //1.获得查看行id
    console.log('部门id', id);
    $(parent.document).find('.page-wrapper').html("<iframe src='" + basePath + "/dept/update?id=" + id + "' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");

}

/**
 * 删除
 * @param id
 */
function deleteItem(id) {
    if(confirm('确认删除吗？')) {
        console.log('部门id', id);
        $(parent.document).find('.page-wrapper').html("<iframe src='" + basePath + "/dept/del?id=" + id + "' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
    }
}

/**
 * 新增
 */
function addItem() {
    $(parent.document).find('.page-wrapper').html("<iframe src='" + basePath + "/dept/add' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
}

function query() {
    var name = $('#name').val();
    $.get(basePath + '/dept/list',{name:name}, function (data) {
        dataHandler(data);
    }, 'json');
}

function reset() {
    $.get(basePath + '/dept/list', function (data) {
        dataHandler(data);
    }, 'json');
}