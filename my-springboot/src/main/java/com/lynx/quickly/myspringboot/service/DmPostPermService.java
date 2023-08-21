package com.lynx.quickly.myspringboot.service;

import com.lynx.quickly.myspringboot.entity.DmPostPerm;

import java.util.List;

/**
 * @author wubaocheng1
 * @date 2022/11/23 16:40
 */
public interface DmPostPermService {

    int deleteByPrimaryKey(Long id);

    int insert(DmPostPerm record);

    int insertSelective(DmPostPerm record);

    DmPostPerm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DmPostPerm record);

    int updateByPrimaryKey(DmPostPerm record);

    List<DmPostPerm> list();

}
