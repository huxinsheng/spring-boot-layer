package com.learn.sbl.enums;

/**
 * @author HuXinsheng
 */
public enum SysUserStatusEnum {
    OPEN(1, "启用"),
    FREEZE(2, "冻结"),
    DELETE(3, "删除");

    private Integer status;
    private String remark;

    SysUserStatusEnum(Integer status, String remark) {
        this.status = status;
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    public static SysUserStatusEnum getByStatus(int status) {
        for (SysUserStatusEnum ruse : SysUserStatusEnum.values()) {
            if (ruse.getStatus() == status) {
                return ruse;
            }
        }
        return null;
    }
}
