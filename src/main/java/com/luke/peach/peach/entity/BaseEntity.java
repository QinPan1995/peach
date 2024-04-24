package com.luke.peach.peach.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseEntity {

    @TableId(value = "id" , type = IdType.AUTO)
    private Long id;

    /**
     * {是否删除}
     *
     **/
    @TableLogic
    private Boolean deleted;

    /**
     * {创建时间}
     *
     **/
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * {修改时间}
     *
     **/
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
