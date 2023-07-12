package com.example.taskmanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class TaskManageController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String index(Model model) {
        String sql = "SELECT * FROM tasks";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        model.addAttribute("tasks", result);
        return "index";
    }

    @GetMapping("/regist")
    public String task_regist() {
        return "regist";
    }
}
