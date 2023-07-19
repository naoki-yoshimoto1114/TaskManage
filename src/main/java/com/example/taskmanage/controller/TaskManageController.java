package com.example.taskmanage.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class TaskManageController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * タスクの一覧表示
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {
        String sql = "SELECT * FROM tasks";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        model.addAttribute("tasks", result);
        return "index";
    }

    @GetMapping("/regist")
    public String regist() {
        return "regist";
    }

    /**
     * タスクの新規登録
     * @param name
     * @param dead_line
     * @param planned_time
     * @param status
     * @return
     */
    @PostMapping("/create")
    public String create(@RequestParam("name") String name,
                         @RequestParam("dead_line") String dead_line,
                         @RequestParam("planned_time") String planned_time,
                         @RequestParam("status") String status) {

        LocalDate deadLineDate = LocalDate.parse(dead_line);
        int plannedTime = Integer.parseInt(planned_time);
        int statusCode = Integer.parseInt(status);

        String sql = "INSERT INTO tasks (name, dead_line, planned_time, status) "
                    + " VALUES(?, ?, ?, ?)";

        jdbcTemplate.update(sql, name, deadLineDate, plannedTime, statusCode);

        return "index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id) {
        return "redirect: /index";
    }

    /**
     * タスクの削除
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "redirect: /index";
    }
}
