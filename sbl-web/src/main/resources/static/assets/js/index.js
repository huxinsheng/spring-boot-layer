var $, tab, menus, layer,form,element;
layui.config({
    base: "/assets/iartisan/plugins/lib/"
}).use(['form', 'element', 'layer', 'jquery', 'bodyTab'], function () {
    $ = layui.$, element = layui.element,
    layer = parent.layer === undefined ? layui.layer : top.layer;
    tab = layui.bodyTab({
        openTabNum: "10",  //最大可打开窗口数量
        url: "/getMenus" //获取菜单json地址
    }),
    form=layui.form;
    //切换导航栏按钮点击事件
    $("#switchNav").click(function () {
        switchNav();
    });

    //折叠显示导航栏
    function switchNav() {
        var sideNavExpand = $('body').hasClass('nav-mini');
        if (!sideNavExpand) {
            $('body').addClass('nav-mini');
        } else {
            $('body').removeClass('nav-mini');
        }
        $('.nav-mini .layui-side .layui-nav .layui-nav-item').hover(function () {
            var that = $(this),
                text = that.children("a:eq(0)").find("cite").text();
            if ($('body').hasClass('nav-mini') && document.body.clientWidth > 750) {
                layer.tips(text, this);
            }
        }, function () {
            layer.closeAll('tips');
        });
    }

    $(".navBar").on("click", function () {
        if ($('body').hasClass('nav-mini')) {
            $('body').removeClass('nav-mini');
        }
    });

    //加载左侧菜单

    function getLeftMenus() {
        $.get(tab.tabConfig.url, function (data) {
            menus = data.dataList;
            tab.render();
        })
    }

    getLeftMenus();

    // 添加新窗口
    $("body").on("click", ".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')", function () {
        //如果不存在子级
        if ($(this).siblings().length == 0) {
            addTab($(this));
            $('body').removeClass('site-mobile');  //移动端点击菜单关闭菜单层
        }
        $(this).parent("li").siblings().removeClass("layui-nav-itemed");
    })
});

//打开新窗口
function addTab(_this) {
    tab.tabAdd(_this);
}