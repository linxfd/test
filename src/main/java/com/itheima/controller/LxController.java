package com.itheima.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version 1.0
 */
@Cacheable()
public class LxController {
    @RequestMapping("in")
    public String getLx() {
        return "lx";
    }
}
