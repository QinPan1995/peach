package com.luke.peach.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luke.peach.model.entity.SysDictType;
import com.luke.peach.model.form.DictTypeForm;
import com.luke.peach.model.vo.DictTypePageVO;
import org.mapstruct.Mapper;

/**
 * 字典类型对象转换器
 *
 * @author luke
 * @since 2024/6/11
 */
@Mapper(componentModel = "spring")
public interface DictTypeConverter {

    Page<DictTypePageVO> entity2Page(Page<SysDictType> page);

    DictTypeForm entity2Form(SysDictType entity);

    SysDictType form2Entity(DictTypeForm entity);
}
