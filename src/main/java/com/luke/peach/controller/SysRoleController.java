package com.luke.peach.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luke.peach.common.model.Option;
import com.luke.peach.common.result.PageResult;
import com.luke.peach.common.result.Result;
import com.luke.peach.model.form.RoleForm;
import com.luke.peach.model.query.RolePageQuery;
import com.luke.peach.model.vo.RolePageVO;
import com.luke.peach.plugin.dupsubmit.annotation.PreventDuplicateSubmit;
import com.luke.peach.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "03.角色接口")
@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class SysRoleController {

    private final SysRoleService roleService;

    @Operation(summary = "角色分页列表")
    @GetMapping("/page")
    public PageResult<RolePageVO> getRolePage(
            @ParameterObject RolePageQuery queryParams
    ) {
        Page<RolePageVO> result = roleService.getRolePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "角色下拉列表")
    @GetMapping("/options")
    public Result<List<Option>> listRoleOptions() {
        List<Option> list = roleService.listRoleOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增角色")
    @PostMapping
    @PreAuthorize("@permissionService.hasPerm('sys:role:add')")
    @PreventDuplicateSubmit
    public Result addRole(@Valid @RequestBody RoleForm roleForm) {
        boolean result = roleService.saveRole(roleForm);
        return Result.judge(result);
    }

    @Operation(summary = "角色表单数据")
    @GetMapping("/{roleId}/form")
    public Result<RoleForm> getRoleForm(
            @Parameter(description = "角色ID") @PathVariable Long roleId
    ) {
        RoleForm roleForm = roleService.getRoleForm(roleId);
        return Result.success(roleForm);
    }

    @Operation(summary = "修改角色")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@permissionService.hasPerm('sys:role:edit')")
    public Result updateRole(@Valid @RequestBody RoleForm roleForm) {
        boolean result = roleService.saveRole(roleForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@permissionService.hasPerm('sys:role:delete')")
    public Result deleteRoles(
            @Parameter(description = "删除角色，多个以英文逗号(,)拼接") @PathVariable String ids
    ) {
        boolean result = roleService.deleteRoles(ids);
        return Result.judge(result);
    }

    @Operation(summary = "修改角色状态")
    @PutMapping(value = "/{roleId}/status")
    public Result updateRoleStatus(
            @Parameter(description = "角色ID") @PathVariable Long roleId,
            @Parameter(description = "状态(1:启用;0:禁用)") @RequestParam Integer status
    ) {
        boolean result = roleService.updateRoleStatus(roleId, status);
        return Result.judge(result);
    }

    @Operation(summary = "获取角色的菜单ID集合")
    @GetMapping("/{roleId}/menuIds")
    public Result<List<Long>> getRoleMenuIds(
            @Parameter(description = "角色ID") @PathVariable Long roleId
    ) {
        List<Long> menuIds = roleService.getRoleMenuIds(roleId);
        return Result.success(menuIds);
    }

    @Operation(summary = "分配菜单(包括按钮权限)给角色")
    @PutMapping("/{roleId}/menus")
    public Result assignMenusToRole(
            @PathVariable Long roleId,
            @RequestBody List<Long> menuIds
    ) {
        boolean result = roleService.assignMenusToRole(roleId, menuIds);
        return Result.judge(result);
    }
}
