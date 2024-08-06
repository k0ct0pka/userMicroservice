package org.example.usermicroservice.Controller;

import org.example.usermicroservice.component.User;
import org.example.usermicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users/")
@EnableCaching
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("")
    public String createUser(@RequestBody User user){
        String code = userService.createUser(user);
        return code;
    }
    @PutMapping("{id}")
    public User updateUser( @RequestBody User user){
        userService.updateUser(user);
        return user;
    }
    @DeleteMapping("{id}")
    public void deleteUser( @PathVariable("id") int id){
        userService.deleteUser(id);
    }
    @Cacheable(key = "#id",value = "user")
    @GetMapping("{id}")
    public User getUserById(@PathVariable Integer id){
        userService.getUserById(id);
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("profile/image/{id}")
    public @ResponseBody byte[] setProfileImage(@RequestParam("file") MultipartFile file, @PathVariable Integer id){
        return userService.uploadProfileImage(file,id);
    }
    @Cacheable(key = "#id",value = "image")
    @GetMapping("profile/image/{id}")
    public @ResponseBody byte[] getProfileImage(@PathVariable Integer id){
        return userService.getProfileImage(id);
    }
    @DeleteMapping("profile/image/{id}")
    public void deleteProfileImage(@PathVariable Integer id){
        userService.deleteProfileImage(id);
    }
    @PutMapping("profile/image/{id}")
    public @ResponseBody byte[] updateProfileImage(@PathVariable Integer id, @RequestBody MultipartFile file){
        return userService.updateProfileImage(file,id);
    }
    @Cacheable(key = "#id",value = "userName")
    @GetMapping("userName/{id}")
    public String getUserName(@PathVariable Integer id){
        User userById = userService.getUserById(id);
        return userById.getUserName();
    }
    @DeleteMapping("userName/{id}")
    public void deleteUserName(@PathVariable Integer id){
        userService.deleteUserName(id);
    }
    @PostMapping("userName/{id}")
    public String setUserName(@PathVariable Integer id, @RequestParam("userName") String userName){
        User userById = userService.getUserById(id);
        userById.setUserName(userName);
        userService.updateUser(userById);
        return userById.getUserName();
    }
}
