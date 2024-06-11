package com.luke.peach.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;import com.luke.peach.model.entity.SysDict;
import com.luke.peach.model.form.DictForm;
import com.luke.peach.model.vo.DictPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * 字典数据项对象转换器
 *
 * @author luke
 * @since 2024/6/11
 */
@Mapper(componentModel = "spring")
public interface DictConverter {

    Page<DictPageVO> entity2Page(Page<SysDict> page);

    DictForm entity2Form(SysDict entity);

    @InheritInverseConfiguration(name="entity2Form")
    SysDict form2Entity(DictForm entity);
}
