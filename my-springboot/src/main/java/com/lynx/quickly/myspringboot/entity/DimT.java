package com.lynx.quickly.myspringboot.entity;

import org.springframework.beans.BeanUtils;

/**
 * @author wubaocheng1
 * @date 2023/5/16 19:02
 */
public class DimT {

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Dim dim = new Dim(1L, "123");
        DimT target = new DimT();
        BeanUtils.copyProperties(dim, target);
        System.out.println(target);
    }
}
