var ztree = "";
layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'router'], function () {

    var $ = layui.jquery, form = layui.form, router = layui.router,
        layer = parent.layer === undefined ? layui.layer : top.layer;


    var urls = {
        getResourceData: "/roleSupport/getResourceData",
        addData: "/roleSupport/addData"
    };

    var xtree;

    router.get({
        url: urls.getResourceData,
        success: function (res) {
            //创建tree
            xtree = new layuiXtree({
                elem: 'permissions',             //放xtree的容器（必填，只能为id，注意不带#号）
                form: form,                //layui form对象 （必填）
                data: res.dataList,        //数据，结构请参照下面 （必填）
                isopen: true,              //初次加载时全部展开，默认true （选填）
                color: "#000",             //图标颜色 （选填）
                icon: {                    //图标样式 （选填）
                    open: "",            //节点打开的图标（使用layui的图标，这里只填入图标代号即可）
                    close: "&#xe654;",     //节点关闭的图标
                    end: ""                //末尾节点的图标
                }
            });
        }
    });

    form.on("submit(addData)", function (data) {
        var oCks = xtree.GetChecked();
        var auths = new Array();
        for (var i = 0; i < oCks.length; i++) {
            auths.push(oCks[i].value);
        }
        router.post({
            url: urls.addData,
            data: {"roleName": data.field.roleName, "permissions": auths.join(",")},
            success: function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            }
        });
        return false;
    })
});