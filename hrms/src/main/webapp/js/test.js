$(function () {
    $.ajax({
        url : 'user/list',
        method : 'GET',
        dataType : 'json',
        data : {
            pageIndex : 1,
            pageSize : 2
        },
        success : function (data) {
            console.log(data);
            $('#table').bootstrapTable({
                columns:
                    [{
                        field:
                            '#',
                        title:
                            ''
                    },{
                        field:
                            'id',
                        title:
                            '用户ID'
                    }, {
                        field:
                            'loginName',
                        title:
                            '帐号'
                    },
                        {
                            field:
                                'username',
                            title:
                                '用户名'
                        },
                        {
                            field:
                                'createDate',
                            title:
                                '创建时间'
                        },{
                        title : '操作',
                        field : 'action',
                        formatter : function(value, row, index) {
                            var look = '<button type="button" class="btn btn-outline-primary" onclick="look(this)">查看</button>';
                            var update = '<button type="button" class="btn btn-outline-success" onclick="update(this)">更新</button>';
                            var froze;
                            if (row.status == 0)
                                froze = '<button type="button" class="btn btn-outline-secondary" onclick="forzz(this)">解冻</button>';
                            else
                                froze = '<button type="button" class="btn btn-outline-secondary" onclick="forzz(this)">冻结</button>';

                            var del = '<button type="button" class="btn btn-outline-danger" onclick="del(this)">删除</button>';
                            return look + update + froze + del;
                        }
                    }],
                data: formatData(data),
            });
        }
    });
});

var formatData = function (data) {
    var l = [];
    for (var i = 0; i < data.pageList.length; i++) {
        var m = data.pageList[i]
        var d = {
            'id': m.id,
            'loginName': m.loginName,
            'username': m.username,
            'createDate' : m.createDate
        }
        l.push(d)
    }
    return l
};

/**
 * 根据操作获取相应的用户id
 *
 * @param that
 * @returns
 */
function getActionUid(that) {
    return $(that).parent().parent().siblings().eq(1);
}

function look(that) {
    console.log(getActionUid(that));
}
function froze(that) {
    console.log(that);
}
function update() {
    console.log(that);
}
function del() {
    console.log(that);
}