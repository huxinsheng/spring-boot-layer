layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['jquery', 'router', 'table'], function () {

    var router = layui.router;

    var urls = {
        getResourceData: "/roleSupport/getResourceData",
        addData: "/menuSupport/addData"
    };

    form.on("submit(addData)", function (data) {
        router.post({
            url: urls.addData,
            data: data.field,
            success: function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            }
        });
        return false;
    });


});