<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 第一个被装饰(目标)页面  -->
<head>
    <title>
        <sitemesh:write property='title'/>
    </title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/static/assets/css/font/font.css" media="all"/>
    <link rel="stylesheet" href="/static/assets/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/static/assets/css/public.css" media="all"/>
    <script type="text/javascript" src="/static/assets/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="/static/assets/js/decorator.js"></script>
</head>
<body class="childrenBody">
<sitemesh:write property='body'/>
</body>
</html>