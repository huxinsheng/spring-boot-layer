layui.define(['layer'], function (exports) {
  //do something
  var $ = layui.$ //重点处
    , layer = layui.layer;
  var http = {
    param: function (data) {
      if (!data) return null;
      var array = new Array();
      for (var name in data) {
        var key = name;
        var value = data[name];
        if (value === null) {
          continue;
        }
        if (Object.prototype.toString.call(value) === '[object Array]') {
          var parent = key;
          var children = value;
          for (var i = 0; i < children.length; i++) {
            var json = children[i];
            if (Object.prototype.toString.call(json) === '[object Object]') {
              for (var name in json) {
                key = parent + '[' + i + '].' + name;
                value = json[name];
                if (value == null) {
                  continue;
                }
                key = encodeURIComponent(key);
                value = encodeURIComponent(value);
                array.push(key + '=' + value);
              }
            } else {
              key = parent + '[' + i + ']';
              key = encodeURIComponent(key);
              value = encodeURIComponent(json);
              array.push(key + '=' + value);
            }
          }
        } else if (Object.prototype.toString.call(value) === '[object Object]') {
          var parent = key;
          $.each(value, function (name, value) {
            var tempKey = encodeURIComponent(parent + '.' + name);
            var tempValue = encodeURIComponent(value);
            array.push(tempKey + '=' + tempValue);
          })
        } else {
          key = encodeURIComponent(key);
          value = encodeURIComponent(value);
          array.push(key + '=' + value);
        }
      }
      return array.join('&');
    },
    post: function (url, param, cb, error) {
      if (typeof(param) == 'function') {
        cb = param;
        error = cb;
        param = null;
      }
      
      $.ajax({
        type: "POST",
        url: url,
        data: this.param(param),
        success: function (r) {
          if (r) {
            r = $.parseJSON(r);
            if (!r.code || r.code == '00000') {
              if (cb) {
                cb(r);
              }
            } else {
              if (error) {
                error(r);
              }
              layer.msg(r.errmsg, {icon: 2});
              
            }
          } else {
            cb();
          }
        },
        error: function (data, status) {
          if (error) {
            error(data);
          }
          layer.msg('系统忙,请稍后再试', {icon: 2});
        }
      });
    },
    get: function (url, param, callback) {
      if ($.isFunction(param)) {
        callback = param;
        param = undefined;
      }
      $.getJSON(url, this.param(param), function (r) {
        if (!r.code) {
          if (callback) {
            callback(r);
          }
        } else {
          layer.msg(r.errmsg, {icon: 2});
        }
      });
    }
  };
  exports('http', http);
});
