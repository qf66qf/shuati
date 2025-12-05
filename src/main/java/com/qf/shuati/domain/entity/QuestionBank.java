package com.qf.shuati.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 题库
 * @TableName question_bank
 */
@TableName(value = "question_bank") // 显式指定表名（与数据库表名一致）
@Data
public class QuestionBank implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id") // 主键字段，显式映射表字段名
    private Long id;

    /**
     * 标题
     */
    @TableField(value = "title") // 映射表字段 title
    private String title;

    /**
     * 描述
     */
    @TableField(value = "description") // 映射表字段 description
    private String description;

    /**
     * 图片
     */
    @TableField(value = "picture") // 映射表字段 picture
    private String picture;

    /**
     * 创建用户 id
     */
    @TableField(value = "userId") // 映射表字段 user_id（与之前 SQL 中的字段名一致）
    private Long userId;

    /**
     * 编辑时间
     */
    @TableField(value = "editTime") // 映射表字段 edit_time（驼峰转下划线）
    private Date editTime;

    /**
     * 创建时间
     */
    @TableField(value = "createTime") // 映射表字段 create_time
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "updateTime") // 映射表字段 update_time
    private Date updateTime;

    /**
     * 是否删除（逻辑删除）
     */
    @TableLogic
    @TableField(value = "isDelete") // 映射表字段 is_delete（与之前 SQL 中的字段名一致）
    private Integer isDelete;

    /**
     * 序列化版本号（表中无该字段，忽略映射）
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}