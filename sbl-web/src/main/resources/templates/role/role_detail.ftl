<form class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">角色名称</label>
        <div class="layui-form-mid layui-word-aux">
           ${data.roleName!''}
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-form-mid layui-word-aux">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">授权</label>
        <div class="layui-input-block">
            <div id="auths" style="border:1px solid block;"></div>
        </div>
    </div>
    <input type="hidden" id="roleId" value="${data.roleId}" >
</form>
<script type="text/javascript" src="/assets/iartisan/plugins/xtree/layui-xtree.js"></script>
<script type="text/javascript" src="/assets/iartisan/js/role/role_detail.js"></script>