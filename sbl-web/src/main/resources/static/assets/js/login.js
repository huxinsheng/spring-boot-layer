layui.config({
  base: "/static/assets/plugins/lib/"
}).use(['form', 'layer', 'jquery', 'router', 'md5'], function () {
  var form = layui.form,
    router = layui.router,
    md = layui.md5,
    $ = layui.jquery;
  
  
  //登录按钮
  form.on("submit(login)", function (data) {
    var _that = $(this);
    _that.text("登录中...").attr("disabled", "disabled").addClass("layui-disabled");
    //密码md5
    var pwd = $.md5(data.field['password']);
    data.field['password'] = pwd;
    router.post({
        url: "/login",
        data: data.field,
        success: function (res) {
          if (res.code === '00000') {
            //请求成功后，写入 access_token
            layui.data('user_access_token', {
              key: 'user_info'
              , value: JSON.stringify(res.data)
            });
            location.href = '/';
          }
        },
        error: function () {
          $('#captcha').attr('src', '/captcha' + '?' + Math.random());
          _that.text("登录").removeAttr("disabled").removeClass("layui-disabled");
        }
      }
    );
    return false;
  });
});

function refreshCaptcha () {

}
