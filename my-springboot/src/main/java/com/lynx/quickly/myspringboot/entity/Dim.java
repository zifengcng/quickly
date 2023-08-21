package com.lynx.quickly.myspringboot.entity;

/**
 * @author wubaocheng1
 * @date 2023/5/16 19:02
 */
public class Dim {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dim(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
