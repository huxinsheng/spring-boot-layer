layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'router', 'table', 'layer'], function () {
    var router = layui.router, $ = layui.jquery, table = layui.table,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var urls = {
        queryPageData: "/menuSupport/queryPageData",
        addDataPage: "/menuSupport/addDataDialog",
        deleteData: "/menuSupport/deleteData"
    };
    queryPageData();

    var tableIns;

    function queryPageData() {
        tableIns = router.table({
            elem: "#dataList",
            url: urls.queryPageData,
            type: 'post',
            where: {"menuName": $("#menuName").val()},
            cols: [[
                {
                    field: 'title',
                    title: '菜单名称',
                },
                {
                    field: 'href',
                    title: '跳转链接',
                },
                {
                    title: '图标',
                    templet: function (d) {
                        return "<i class=\"layui-icon\">" + d.icon + "</i>";
                    }
                },
                {
                    title: '操作',
                    fixed: "right",
                    align: "center",
                    templet: function () {
                        return "<a class=\"layui-btn layui-btn-xs layui-btn-danger\" lay-event=\"del\">删除</a>";
                        /*"<a class=\"layui-btn layui-btn-xs\" lay-event=\"edit\">编辑</a>" +*/

                    }
                }
            ]]
        });
    }

    $("#btnQuery").on("click", function () {
        queryPageData();
    });
    //列表操作
    table.on('tool(dataList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent == 'del') {
            layer.confirm('确定删除该菜单吗？', {icon: 3, title: '提示信息'}, function (index) {
                layer.close(index);
                router.post({
                    url: urls.deleteData, data: {menuId: data.id}, success: function () {
                        tableIns.reload();
                    }
                });
                tableIns.reload();
            });
        }
    });

    $("#btnAddPage").click(function () {
        var index = layui.layer.open({
            title: "添加菜单",
            type: 2,
            content: urls.addDataPage,
            area: ['600px', '500px'],
            maxmin: true,
            btn: ['添加', '关闭'],
            btnAlign: 'c',
            skin: 'layui-layer-molv',
            yes: function (index, layero) {
                layero.find('iframe').contents().find("#formAdd").find("#btnAdd").click();
                //tableIns.reload();
            },
            btn2: function (index) {
                layui.layer.close(index);
            }
        });
    });
});