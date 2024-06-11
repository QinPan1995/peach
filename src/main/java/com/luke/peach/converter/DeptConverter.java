package com.luke.peach.converter;

import com.luke.peach.model.entity.SysDept;
import com.luke.peach.model.form.DeptForm;
import com.luke.peach.model.vo.DeptVO;
import org.mapstruct.Mapper;

/**
 * 部门对象转换器
 *
 * @author luke
 * @since 2024/6/11
 */
@Mapper(componentModel = "spring")
public interface DeptConverter {

    DeptForm entity2Form(SysDept entity);
    DeptVO entity2Vo(SysDept entity);

    SysDept form2Entity(DeptForm deptForm);

}