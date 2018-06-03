$(function () {
    // 定义全局变量
    basePath = $('#basePath').val();

    // 获得最近通知
    $.getJSON(basePath + '/notice/recent', function (data) {
        recentNoticesHander(data);
    });
});

/**
 * 近期通知处理
 * @param data
 */
function recentNoticesHander(data) {
    if (data){
        for (var index = 0; index <data.length; index++){
            $('.recent').before('<li class="list-group-item"><div class="row"><div class="col-2">' + data[index].createDate.split(' ')[0] + '</div>'
                + '<div class="col-2">' + data[index].title + '</div>'
                + '<div class="col-4" style="overflow:hidden; white-space:nowrap; text-overflow:ellipsis">' + data[index].content + '</div>'
                + '<div class="col-2"><a href="' + basePath + '/notice/look/' + data[index].id + '">详情</a></div></div></li>'
            );
        }
    }else {
        $('.recent').append('<li>最近没有通知</li>');
    }
}