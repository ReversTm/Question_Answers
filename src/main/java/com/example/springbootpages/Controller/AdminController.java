package com.example.springbootpages.Controller;

import com.example.springbootpages.Entity.User;
import com.example.springbootpages.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showAdminPage(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "admin";
    }
    @RequestMapping("/deleteEmployee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String  deleteUser(@RequestParam int userId){
//        System.out.println(userId);
        userService.deleteUser(userId);
        return "redirect:/admin";
    }
}