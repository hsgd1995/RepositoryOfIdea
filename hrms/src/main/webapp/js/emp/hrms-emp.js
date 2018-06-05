$(function () {
    // 定义全局变量
    basePath = $('#basePath').val();



    //1.第一次加载页面，需要首页数据
    $.get(basePath + '/emp/list', function (data) {
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
        // 定义每行记录对应的emp对象
        // 定义行
        console.log("拼接表格标签");
        var tr = $('<tr></tr>');
        var id = list[index].id;
        // 定义列
        var ck = $('<th scope="row"><input type="checkbox"></th>')
        var th = $('<th scope="row">' + (index + 1) + '</th>');
        var empId = $('<td>' + list[index].id + '</td>');
        var cardId = $('<td>' + list[index].cardId + '</td>');
        var name = $('<td>' + list[index].name + '</td>');
        var age = $('<td>' + list[index].age + '</td>');
        var sex = $('<td>' + list[index].sex + '</td>');
        var tel = $('<td>' + list[index].tel + '</td>');
        var birthday = $('<td>' + list[index].birthday + '</td>');
        var job = $('<td>' + list[index].job.name + '</td>');
        var dept = $('<td>' + list[index].department.name + '</td>');
        var operation = '<td><button type="button" class="btn-sm btn-outline-primary" onclick="lookItem('+id+');">查看</button>';
        operation = operation + '<button type="button" class="btn-sm btn-outline-success" onclick="updateItem('+id+');"> 更新</button>' +
            '<button type="button" class="btn-sm btn-outline-danger" onclick="deleteItem('+id+');">删除</button></td>';
        // 组行和列并附加到tbody
        $('tbody').append(tr.append(ck).append(th).append(empId).append(cardId).append(name).append(age).append(sex).append(tel).append(birthday).append(job).append(dept).append(operation));
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
        $.get(basePath + '/emp/list', {
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
        $.get(basePath + '/emp/list', {
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

    $.get(basePath + '/emp/list', {
        title: title,
        createDate: createDate,
        content: content,
        pageIndex: that.text,
        pageSize: pageSize
    }, function (data) {
        dataHandler(data);
    }, 'json');
}

function deleteItem(id) {
    if(confirm('确认删除吗？')){
        $(parent.document).find('.page-wrapper').html("<iframe src='" + basePath + "/emp/del?id=" + id + "' width='100%' height='100%' frameborder='0' name='_blank' id='_blank'></iframe>");
    }
}