<form class="layui-form layui-row layui-col-space10" id="formAdd">
    <div class="layui-col-md9 layui-col-xs12">
        <div class="layui-row layui-col-space12">
            <div class="layui-col-md12 layui-col-xs12">
                <div class="layui-form-item magt3">
                    <label class="layui-form-label">角色名称</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input newsName" placeholder="请输入角色名称" lay-verify="required"
                               name="roleName">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入备注" class="layui-textarea abstract" lay-verify="required"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">授权</label>
                    <div class="layui-input-block">
                        <div id="permissions" name="permissions" style="border:1px solid block;"></div>
                    </div>
                </div>
                <input type="hidden" lay-submit="" lay-filter="addData" id="btnAdd"/>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript" src="/assets/iartisan/plugins/xtree/layui-xtree.js"></script>
<script type="text/javascript" src="/assets/iartisan/js/role/role_add.js"></script>