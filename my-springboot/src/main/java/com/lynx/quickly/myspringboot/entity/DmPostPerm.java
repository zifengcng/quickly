package com.lynx.quickly.myspringboot.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 岗位权限
 *
 * @author wubaocheng1
 * @date 2022/11/23 16:40
 */
@Data
public class DmPostPerm implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 岗位英文名
     */
    private String postEname;

    /**
     * 岗位中文名
     */
    private String postName;

    /**
     * 岗位类型：1、3.0岗，2、2.0岗
     */
    private Integer postType;

    /**
     * 数据角色，逗号分割
     */
    private String dataRoleId;

    /**
     * 应用角色，逗号分割
     */
    private String appRoleId;

    /**
     * 创建人erp
     */
    private String createdErp;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改人erp
     */
    private String modifiedErp;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    private static final long serialVersionUID = 1L;
}