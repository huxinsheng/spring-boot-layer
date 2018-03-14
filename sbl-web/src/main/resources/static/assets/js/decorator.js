var $,layer, form, element;
layui.config({
    dir: '/static/assets/plugins/layui/'
}).use(['form','layer', 'jquery'], function () {
    $ = layui.$,
    layer = parent.layer === undefined ? layui.layer : top.layer;
    form = layui.form;
});