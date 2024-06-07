package com.luke.peach.controller;


import com.luke.peach.service.SysUserService;
import com.luke.peach.vo.Result;
import com.luke.peach.vo.UserInfoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户控制器
 *
 * @author luke
 * @since 2024/6/6
 */
@Tag(name = "02.用户接口")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService userService;

//    @Operation(summary = "用户分页列表")
//    @GetMapping("/page")
//    public PageResult<UserPageVO> listPagedUsers(
//            @ParameterObject UserPageQuery queryParams
//    ) {
//        IPage<UserPageVO> result = userService.listPagedUsers(queryParams);
//        return PageResult.success(result);
//    }
//
//    @Operation(summary = "新增用户")
//    @PostMapping
//    @PreAuthorize("@ss.hasPerm('sys:user:add')")
//    @PreventDuplicateSubmit
//    public Result saveUser(
//            @RequestBody @Valid UserForm userForm
//    ) {
//        boolean result = userService.saveUser(userForm);
//        return Result.judge(result);
//    }
//
//    @Operation(summary = "用户表单数据")
//    @GetMapping("/{userId}/form")
//    public Result<UserForm> getUserForm(
//            @Parameter(description = "用户ID") @PathVariable Long userId
//    ) {
//        UserForm formData = userService.getUserFormData(userId);
//        return Result.success(formData);
//    }
//
//    @Operation(summary = "修改用户")
//    @PutMapping(value = "/{userId}")
//    @PreAuthorize("@ss.hasPerm('sys:user:edit')")
//    public Result updateUser(
//            @Parameter(description = "用户ID") @PathVariable Long userId,
//            @RequestBody @Validated UserForm userForm) {
//        boolean result = userService.updateUser(userId, userForm);
//        return Result.judge(result);
//    }
//
//    @Operation(summary = "删除用户")
//    @DeleteMapping("/{ids}")
//    @PreAuthorize("@ss.hasPerm('sys:user:delete')")
//    public Result deleteUsers(
//            @Parameter(description = "用户ID，多个以英文逗号(,)分割") @PathVariable String ids
//    ) {
//        boolean result = userService.deleteUsers(ids);
//        return Result.judge(result);
//    }
//
//    @Operation(summary = "修改用户密码")
//    @PatchMapping(value = "/{userId}/password")
//    @PreAuthorize("@ss.hasPerm('sys:user:password:reset')")
//    public Result updatePassword(
//            @Parameter(description = "用户ID") @PathVariable Long userId,
//            @RequestParam String password
//    ) {
//        boolean result = userService.updatePassword(userId, password);
//        return Result.judge(result);
//    }
//
//    @Operation(summary = "修改用户状态")
//    @PatchMapping(value = "/{userId}/status")
//    public Result updateUserStatus(
//            @Parameter(description = "用户ID") @PathVariable Long userId,
//            @Parameter(description = "用户状态(1:启用;0:禁用)") @RequestParam Integer status
//    ) {
//        boolean result = userService.update(new LambdaUpdateWrapper<SysUser>()
//                .eq(SysUser::getId, userId)
//                .set(SysUser::getStatus, status)
//        );
//        return Result.judge(result);
//    }

    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/me")
    public Result<UserInfoVO> getCurrentUserInfo() {
        UserInfoVO userInfoVO = new UserInfoVO();
        Set<String> roles = new HashSet<>();
        roles.add("ADMIN");
        userInfoVO.setRoles(roles);
        // 创建一个Set集合来存储权限
        Set<String> permissionsSet = new HashSet<>();

        // 添加权限到Set中
        permissionsSet.add("sys:menu:delete");
        permissionsSet.add("sys:dept:edit");
        permissionsSet.add("sys:dict_type:add");
        permissionsSet.add("sys:dict:edit");
        permissionsSet.add("sys:dict:delete");
        permissionsSet.add("sys:user:query");
        permissionsSet.add("sys:dict_type:edit");
        permissionsSet.add("sys:menu:add");
        permissionsSet.add("sys:user:add");
        permissionsSet.add("sys:user:export");
        permissionsSet.add("sys:role:edit");
        permissionsSet.add("sys:dept:delete");
        permissionsSet.add("sys:user:password:reset");
        permissionsSet.add("sys:user:edit");
        permissionsSet.add("sys:user:import");
        permissionsSet.add("sys:user:delete");
        permissionsSet.add("sys:dept:add");
        permissionsSet.add("sys:role:delete");
        permissionsSet.add("sys:dict_type:delete");
        permissionsSet.add("sys:menu:edit");
        permissionsSet.add("sys:dict:add");
        permissionsSet.add("sys:role:add");
        userInfoVO.setPerms(permissionsSet);
        return Result.success(userInfoVO);
    }

//    @Operation(summary = "用户导入模板下载")
//    @GetMapping("/template")
//    public void downloadTemplate(HttpServletResponse response) throws IOException {
//        String fileName = "用户导入模板.xlsx";
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
//
//        String fileClassPath = "excel-templates" + File.separator + fileName;
//        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileClassPath);
//
//        ServletOutputStream outputStream = response.getOutputStream();
//        ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build();
//
//        excelWriter.finish();
//    }
//
//    @Operation(summary = "导入用户")
//    @PostMapping("/import")
//    public Result importUsers(@Parameter(description = "部门ID") Long deptId, MultipartFile file) throws IOException {
//        UserImportListener listener = new UserImportListener(deptId);
//        String msg = ExcelUtils.importExcel(file.getInputStream(), UserImportVO.class, listener);
//        return Result.success(msg);
//    }
//
//    @Operation(summary = "导出用户")
//    @GetMapping("/export")
//    public void exportUsers(UserPageQuery queryParams, HttpServletResponse response) throws IOException {
//        String fileName = "用户列表.xlsx";
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
//
//        List<UserExportVO> exportUserList = userService.listExportUsers(queryParams);
//        EasyExcel.write(response.getOutputStream(), UserExportVO.class).sheet("用户列表")
//                .doWrite(exportUserList);
//    }
}
