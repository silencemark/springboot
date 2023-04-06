package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "系统用户接口", description = "系统用户接口")
@RestController
@RequestMapping(value = "/test", produces = "application/json; charset=UTF-8")
public class MapperController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String testGet() {
        return "success";
    }

    @GetMapping("/user/{id}")
    public String testPathVariable(@PathVariable Integer id) {
        System.out.println("获取到的id为：" + id);
        return "success";
    }

    @RequestMapping("/userid/{idd}")
    public String testPathVariable(@PathVariable(value = "idd") String id) {
        System.out.println("获取到的id为：" + id);
        return "success";
    }

    @RequestMapping("/user/{idd}/{name}")
    public String testPathVariable(@PathVariable(value = "idd") Integer id, @PathVariable String name) {
        System.out.println("获取到的id为：" + id);
        System.out.println("获取到的name为：" + name);
        return "success";
    }
}