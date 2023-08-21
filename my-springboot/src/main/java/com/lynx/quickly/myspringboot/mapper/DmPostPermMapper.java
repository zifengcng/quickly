package com.lynx.quickly.myspringboot.mapper;

import com.lynx.quickly.myspringboot.entity.DmPostPerm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wubaocheng1
 * @date 2022/11/23 16:40
 */
@Mapper
public interface DmPostPermMapper {

    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(DmPostPerm record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(DmPostPerm record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    DmPostPerm selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(DmPostPerm record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(DmPostPerm record);

    List<DmPostPerm> list();
}