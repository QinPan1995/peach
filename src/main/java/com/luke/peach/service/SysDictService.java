package com.luke.peach.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luke.peach.common.model.Option;
import com.luke.peach.model.entity.SysDict;
import com.luke.peach.model.form.DictForm;
import com.luke.peach.model.query.DictPageQuery;
import com.luke.peach.model.vo.DictPageVO;

import java.util.List;

/**
 * 字典接口
 *
 * @author luke
 * @since 2024/6/11
 */
public interface SysDictService extends IService<SysDict> {
    /**
     * 字典数据项分页列表
     *
     * @param queryParams
     * @return
     */
    Page<DictPageVO> getDictPage(DictPageQuery queryParams);

    /**
     * 字典数据项表单
     *
     * @param id 字典数据项ID
     * @return
     */
    DictForm getDictForm(Long id);

    /**
     * 新增字典数据项
     *
     * @param dictForm 字典数据项表单
     * @return
     */
    boolean saveDict(DictForm dictForm);

    /**
     * 修改字典数据项
     *
     * @param id       字典数据项ID
     * @param dictForm 字典数据项表单
     * @return
     */
    boolean updateDict(Long id, DictForm dictForm);

    /**
     * 删除字典数据项
     *
     * @param idsStr 字典数据项ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteDict(String idsStr);

    /**
     * 获取字典下拉列表
     *
     * @param typeCode
     * @return
     */
    List<Option> listDictOptions(String typeCode);

}
