package org.example.usermicroservice.Controller;

import org.example.usermicroservice.component.User;
import org.example.usermicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication/")
public class AuthorizationController {
    @Autowired
    private UserService userService;
    @PostMapping("registration")
    public String createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @PostMapping("registration/confirm")
    public @ResponseBody User confirmUser(@RequestParam("code") String code){
        return userService.confirmCreation(code);
    }
    @GetMapping("logIn")
    public @ResponseBody User login(@RequestParam("login") String login, @RequestParam("password") String password){
        return userService.logIn(login,password);
    }
}
