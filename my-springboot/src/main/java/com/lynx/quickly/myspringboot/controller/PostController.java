package com.lynx.quickly.myspringboot.controller;

import com.alibaba.fastjson.JSON;
import com.lynx.quickly.myspringboot.entity.DmPostPerm;
import com.lynx.quickly.myspringboot.service.DmPostPermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author wubaocheng1
 * @date 2022/11/23 16:44
 */
@RestControllerAdvice
@RequestMapping("/post")
public class PostController {

    @Autowired
    private DmPostPermService dmPostPermService;

    @GetMapping("/list")
    public String list() {
        List<DmPostPerm> list = dmPostPermService.list();
        return JSON.toJSONString(list);
    }
}
