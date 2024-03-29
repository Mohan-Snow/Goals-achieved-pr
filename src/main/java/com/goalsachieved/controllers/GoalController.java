package com.goalsachieved.controllers;

import com.goalsachieved.models.Goal;
import com.goalsachieved.models.GoalType;
import com.goalsachieved.models.User;
import com.goalsachieved.services.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GoalController {

    private BasicService goalService;
    private BasicService userService;

    @Autowired
    public GoalController(@Qualifier("goalService") BasicService goalService,
                          @Qualifier("userService") BasicService userService) {
        this.goalService = goalService;
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test() {
        return "demo";
    }

    @PostMapping("/test")
    public String addGoal(@RequestParam String description,
                          @RequestParam String goalType,
                          @RequestParam String userName,
                          Map<String, Object> model) {

        // for setting the state
        GoalType gType;
        if (goalType.equals("sub")) {
            gType = GoalType.SUB_GOAL;
        } else if (goalType.equals("main")) {
            gType = GoalType.MAIN_GOAL;
        } else {
            return "error_page";
        }
        User user = new User(userName);
        userService.add(user);

        goalService.add(new Goal(description, gType, user));

        // for mapping
        Iterable<Goal> goals = goalService.getAll();
        model.put("goals", goals);
//        return "redirect:/";
        return "demo";
    }
}
