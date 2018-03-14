layui.config({
  base: "/static/assets/plugins/lib/"
}).use(['jquery', 'router', 'table', 'layer'], function () {
  var router = layui.router, $ = layui.jquery, table = layui.table,
    layer = parent.layer === undefined ? layui.layer : top.layer;

  var urls = {
    queryPageData: "/menu/queryPageData",
    addDataPage: "/menu/addDataDialog",
    deleteData: "/menu/deleteData"
  };
  queryPageData();

  var tableIns;

  function queryPageData() {
    tableIns = router.table({
      elem: "#dataList",
      url: urls.queryPageData,
      type: 'post',
      where: {"menuName": $("#menuName").val()},
      height: "full-104",
      cols: [[
        {
          field: 'name',
          title: '菜单名称',
          width: '30%'
        },
        {
          field: 'url',
          title: '菜单地址',
          width: '30%',
        },
        {
          field: 'level',
          title: '菜单级别',
          width: '10%',
          templet: function (d) {
            return d.level == 0 ? '一级菜单' : d.level == 1 ? '二级菜单' : '三级菜单';
          }
        },
        {
          field: 'seq',
          title: '菜单顺序',
          sort: true,
          width: '10%',
        },
        {
          title: '图标',
          width: '10%',
          templet: function (d) {
            return "<i class=\"layui-icon\">" + d.icon + "</i>";
          }
        },
        {
          title: '操作',
          fixed: 'right',
          align: "center",
          width: '10%',
          templet: function () {
            var s = "<a class=\"layui-btn layui-btn-sm\" lay-event=\"edit\">编辑</a>" ;
            s += "<a class=\"layui-btn layui-btn-sm layui-btn-danger\" lay-event=\"del\">删除</a>";
            return s;

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
    if (layEvent == 'edit') {
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