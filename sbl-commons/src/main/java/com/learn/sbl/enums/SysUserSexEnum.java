package com.learn.sbl.enums;

/**
 * @author HuXinsheng
 */
public enum SysUserSexEnum {
    MAN(1, "男"),
    WOMAN(2, "女");

    private Integer status;
    private String remark;

    SysUserSexEnum(Integer status, String remark) {
        this.status = status;
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    public static SysUserSexEnum getByStatus(int status) {
        for (SysUserSexEnum ruse : SysUserSexEnum.values()) {
            if (ruse.getStatus() == status) {
                return ruse;
            }
        }
        return null;
    }
}
