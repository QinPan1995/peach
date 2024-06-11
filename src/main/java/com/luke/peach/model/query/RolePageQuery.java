package com.luke.peach.model.query;

import com.luke.peach.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色分页查询实体
 *
 * @author luke
 * @since 2024/6/11
 *
 */
@Data
public class RolePageQuery extends BasePageQuery {

    @Schema(description="关键字(角色名称/角色编码)")
    private String keywords;
}
