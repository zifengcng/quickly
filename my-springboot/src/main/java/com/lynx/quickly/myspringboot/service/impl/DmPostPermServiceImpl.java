package com.lynx.quickly.myspringboot.service.impl;

import com.lynx.quickly.myspringboot.annotations.Cacheable;
import com.lynx.quickly.myspringboot.entity.DmPostPerm;
import com.lynx.quickly.myspringboot.mapper.DmPostPermMapper;
import com.lynx.quickly.myspringboot.service.DmPostPermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

/**
 * @author wubaocheng1
 * @date 2022/11/23 16:40
 */
//@Cacheable(userTimingWheel = true)
@Service
public class DmPostPermServiceImpl implements DmPostPermService {

    @Resource
    private DmPostPermMapper dmPostPermMapper;

    @Autowired
    private DmPostPermService selfService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dmPostPermMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DmPostPerm record) {
        return dmPostPermMapper.insert(record);
    }

    @Cacheable(userTimingWheel = false)
    @Override
    public int insertSelective(DmPostPerm record) {
        int i = dmPostPermMapper.insertSelective(record);
        System.out.println(record.getId());
        return i;
    }

    @Cacheable(userTimingWheel = true)
    @Override
    public DmPostPerm selectByPrimaryKey(Long id) {
        return dmPostPermMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DmPostPerm record) {
        return dmPostPermMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DmPostPerm record) {
        return dmPostPermMapper.updateByPrimaryKey(record);
    }

    //    @Cacheable(userTimingWheel = true)
//    @Transactional
    @Override
    public List<DmPostPerm> list() {
        List<DmPostPerm> list = dmPostPermMapper.list();
        List<DmPostPerm> list1 = dmPostPermMapper.list();
        DmPostPerm record = new DmPostPerm();
        record.setPostEname("testAop");
        record.setPostName("testAop");
        selfService.insertSelective(record);
        return list1;
    }

}
