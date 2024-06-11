package com.luke.peach.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 菜单查询对象
 *
 * @author luke
 * @since 2024/6/11
 */
@Schema(description ="部门分页查询对象")
@Data
public class MenuQuery {

    @Schema(description="关键字(菜单名称)")
    private String keywords;

    @Schema(description="状态(1->显示；0->隐藏)")
    private Integer status;

}
