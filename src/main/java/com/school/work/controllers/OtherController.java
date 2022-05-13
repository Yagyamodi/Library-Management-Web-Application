package com.school.work.controllers;

import com.school.work.dao.EmployeeDao;
import com.school.work.dao.SubjectDao;
import com.school.work.dao.UserinfoDao;
import com.school.work.models.Userinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OtherController{

    @Autowired
    EmployeeDao empDao;

    @Autowired
    UserinfoDao userDao;


    @PostMapping("/credentials/{employeeId}/edit")
    public String update_userinfo(@RequestParam String role,@PathVariable int employeeId, Model m){
        Userinfo user = userDao.getUserinfoByEmployeeId(employeeId);
        user.setRole(role);
        userDao.update(user,user.getUserName());
        return "redirect:/employees/"+employeeId;
    }

    @GetMapping("/credentials/{employeeId}/new")
    public String new_userinfo(@PathVariable int employeeId, Model m){
        m.addAttribute("employeeId", employeeId);
        return "user_register";
    }
    
    @PostMapping("/credentials/{employeeId}/new")
    public String save_userinfo(@RequestParam String role,@RequestParam String username,@RequestParam String password ,@PathVariable int employeeId, Model m){
        if(userDao.isEmployeeWithUsernameExists(username))
        {
            String msg = "!!!username already exists.!!!";
            m.addAttribute("message", msg);
            m.addAttribute("employeeId", employeeId);
            return "user_register";
        }
        Userinfo user = new Userinfo();
        user.setUserName(username);
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        user.setRole(role);
        user.setEmployeeId(employeeId);
        userDao.save(user);
        return "redirect:/employees/"+employeeId;
    }

    @GetMapping("/credentials/change")
    public String new_password(Model m){
        return "new_pass";
    }

    @PostMapping("/credentials/{username}/change")
    public String save_userinfo(@RequestParam String oldPassword,@RequestParam String newPassword,@RequestParam String confirmPassword ,@PathVariable String username, Model m){
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        Userinfo user = userDao.getUserinfoByUserName(username);
        if(!(encoder.matches(oldPassword, user.getPassword()))){
            // System.out.println(encoder.encode(old_password));
            // System.out.println(e.getPassword());
            // System.out.print(old_password);
            String msg = "Old Password does not match!!!";
            m.addAttribute("msg", msg);
            return "new_pass";
        }
        if(!newPassword.matches(confirmPassword))
        {
            String msg = "Password differ on second occurence!!!";
            m.addAttribute("msg", msg);
            return "new_pass";
        }
        user.setPassword(encoder.encode(newPassword));
        userDao.update(user,user.getUserName());
        return "redirect:/employees/"+user.getEmployeeId();
    }

}