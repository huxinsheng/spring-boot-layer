package com.learn.sbl.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * @author HuXinsheng
 */
@Data
public abstract class BasePojo {

    @TableField("createTime")
    private Date createTime;

    @TableField("updateTime")
    @NotNull(message = "更新时间不能为空")
    private Date updateTime;

    @TableField("creator")
    private String creator;

    @TableField("updateBy")
    @NotNull(message = "更新者不能为空")
    private String updateBy;

    public static String newId() {
        return DigestUtils.md5Hex(UUID.randomUUID().toString());
    }

    public abstract String toJsonstring();
}
