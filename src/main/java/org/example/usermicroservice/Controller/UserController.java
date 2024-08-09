package org.example.usermicroservice.Controller;

import org.example.usermicroservice.component.User;
import org.example.usermicroservice.dto.Comment;
import org.example.usermicroservice.dto.Group;
import org.example.usermicroservice.dto.Post;
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
    @Cacheable(key = "current",value = "currentUser")
    @GetMapping("current")
    public @ResponseBody User getCurrentUser(){
        return userService.getCurrentUser();
    }
    @PutMapping("{id}")
    public @ResponseBody User updateUser( @RequestBody User user){
        userService.updateUser(user);
        return user;
    }
    @DeleteMapping("{id}")
    public void deleteUser( @PathVariable("id") int id){
        userService.deleteUser(id);
    }
    @Cacheable(key = "#id",value = "user")
    @GetMapping("{id}")
    public @ResponseBody User getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("profile/image/{id}")
    public @ResponseBody byte[] setProfileImage(@RequestBody MultipartFile file, @PathVariable Integer id){
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
        return userService.getUserName(id);
    }
    @DeleteMapping("userName/{id}")
    public void deleteUserName(@PathVariable Integer id){
        userService.deleteUserName(id);
    }
    @PostMapping("userName/{id}")
    public String setUserName(@PathVariable Integer id, @RequestParam("userName") String userName){
        return userService.setUserName(id,userName);
    }
    @Cacheable(key = "#id",value = "nickName")
    @GetMapping("nickName/{id}")
    public String getNickName(@PathVariable Integer id){
        return userService.getNickName(id);
    }
    @DeleteMapping("nickName/{id}")
    public void deleteNickName(@PathVariable Integer id){
        userService.deleteNickName(id);
    }
    @PostMapping("nickName/{id}")
    public String setNickName(@PathVariable Integer id, @RequestParam("nickName") String nickName){
        return userService.setNickName(id,nickName);
    }
    @Cacheable(key = "#id",value = "login")
    @GetMapping("login/{id}")
    public String getLogin(@PathVariable Integer id){
        return userService.getLogin(id);
    }

    @PutMapping("login/{id}")
    public void changeLogin(@PathVariable Integer id, @RequestParam("login") String login , @RequestParam("password") String password){
        userService.changeLogin(id,login,password);
    }
    @Cacheable(key = "#id",value = "password")
    @GetMapping("password/{id}")
    public String getFollowers(@PathVariable Integer id){
        return userService.getLogin(id);
    }
    @DeleteMapping("login/{id}")
    public void deleteLogin(@PathVariable Integer id){
        userService.deleteLogin(id);
    }
    @PutMapping("password/{id}")
    public void changePassword(@PathVariable Integer id, @RequestParam("oldPassword") String oldPassword , @RequestParam("newPassword") String newPassword){
        userService.changePassword(id,oldPassword,newPassword);
    }
    @Cacheable(key = "#id",value = "followers")
    @GetMapping("followers/{id}")
    public @ResponseBody List<User> getFollowers(@PathVariable Integer id, @RequestParam("count") int count){
        return userService.getFollowers(id,count);
    }
    @PostMapping("followers/follow/{idWho}")
    public @ResponseBody User followOn(@PathVariable Integer idWho,@RequestParam("idOn") Integer idOn){
        return userService.followOnAndGetOnFollowed(idWho,idOn);
    }
    @DeleteMapping("followers/unfollow/{idWho}")
    public @ResponseBody User unfollowOn(@PathVariable Integer idWho,@RequestParam("idOn") Integer idOn){
        return userService.unfollowOn(idWho,idOn);
    }
    @Cacheable(key = "#id",value = "subscribes")
    @GetMapping("subscribes/{id}")
    public @ResponseBody List<User> getSubscribes(@PathVariable Integer id, @RequestParam("count") int count){
        return userService.getSubscribedUsers(id,count);
    }
    @Cacheable(key = "#id",value = "groups")
    @GetMapping("groups/{id}")
    public @ResponseBody List<Group> getGroups(@PathVariable Integer id, @RequestParam("count") int count){
        return userService.getSubscribedGroups(id,count);
    }
    @Cacheable(key = "#id",value = "groupsCreated")
    @GetMapping("groups/created/{id}")
    public @ResponseBody List<Group> getCreatedGroups(@PathVariable Integer id, @RequestParam("count") int count){
        return userService.getCreatedGroups(id,count);
    }
    @PostMapping("groups/join/{userId}")
    public @ResponseBody User getCreatedGroups(@PathVariable Integer userId, @RequestParam("groupId") Integer groupId){
        return userService.joinGroup(groupId,userId);
    }
    @Cacheable(key = "#groupId",value = "members")
    @GetMapping("groups/members/{groupId}")
    public @ResponseBody List<User> getMembers(@PathVariable Integer groupId){
        return userService.getGroupMembers(groupId);
    }
    @DeleteMapping("groups/leave/{userId}")
    public User leaveFromGroup(@PathVariable Integer userId, @RequestParam("groupId") Integer groupId){
        return userService.leaveGroup(groupId, userId);
    }
    @DeleteMapping("groups/removeUser/{idWho}")
    public boolean removeUserFromGroup(@PathVariable Integer idWho,@RequestParam("userId") Integer userId , @RequestParam("groupId") Integer groupId){
        return userService.removeUserFromGroup(idWho,userId,groupId);
    }
    @Cacheable(key = "#id",value = "posts")
    @GetMapping("posts/{id}")
    public @ResponseBody List<Post> getPosts(@PathVariable Integer id, @RequestParam("count") int count){
        return userService.getPostOfUser(id,count);
    }
    @Cacheable(key = "#id" , value = "likedPosts")
    @GetMapping("posts/liked/{id}")
    public List<Post> likePost(@PathVariable Integer id, @RequestParam("count") int count){
        return userService.getLikedPosts(id,count);
    }
    @PostMapping("posts/like/{id}")
    public void likePost(@PathVariable Integer id, @RequestParam("postId") Integer postId){
        userService.likePost(postId,id);
    }
    @DeleteMapping("posts/delete/{id}")
    public void deletePost(@PathVariable Integer id, @RequestParam("postId") Integer postId){
        userService.deletePost(postId,id);
    }
    @DeleteMapping("posts/unlike/{id}")
    public void unlikePost(@PathVariable Integer id, @RequestParam("postId") Integer postId){
        userService.unlikePost(postId,id);
    }
    @Cacheable(key = "#id", value = "reposts")
    @GetMapping("reposts/{id}")
    public @ResponseBody List<Post> getReposts(@PathVariable Integer id, @RequestParam("count") int count){
        return userService.getReposts(id,count);
    }
    @PostMapping("reposts/repost/{id}")
    public void repost(@PathVariable Integer id, @RequestParam("postId") Integer postId){
        userService.repost(postId,id);
    }
    @DeleteMapping("reposts/unRepost/{id}")
    public void unRepost(@PathVariable Integer id, @RequestParam("postId") Integer postId){
        userService.unRepost(postId,id);
    }
    @Cacheable(key = "#id",value = "comments")
    @GetMapping("comments/{id}")
    public @ResponseBody List<Comment> getComments(@PathVariable Integer id, @RequestParam("count") int count){
        return userService.getCommentsOfUser(id,count);
    }
    @PostMapping("comments/comment/{id}")
    public void comment(@PathVariable Integer id, @RequestParam("postId") Integer postId,@RequestParam("comment") String comment){
        userService.commentPost(postId,id,comment);
    }
    @DeleteMapping("comments/delete/{id}")
    public void deleteComment(@PathVariable Integer id, @RequestParam("commentId") Integer commentId){
        userService.deleteComment(commentId,id);
    }
    @Cacheable(key = "#id",value = "reports")
    @GetMapping("reports/{id}")
    public void reports(@PathVariable Integer id){
        userService.getReports(id);
    }
    @PostMapping("reports/report/{id}")
    public void report(@PathVariable Integer id){
        userService.setReports(id,userService.getReports(id)+1);
    }
    @PostMapping("ban/{id}")
    public void ban(@PathVariable Integer id){
        userService.banUser(id);
    }
    @DeleteMapping("unBan/{id}")
    public void unBan(Integer id){
        userService.unBanUser(id);
    }
    @Cacheable(key = "#id",value = "followersCount")
    @GetMapping("followers/count/{id}")
    public Integer getFollowersCount(@PathVariable Integer id){
        return userService.getFollowersCount(id);
    }
    @Cacheable(key = "#id",value = "likesCount")
    @GetMapping("likes/count/{id}")
    public Integer getLikesCount(@PathVariable Integer id){
        return userService.getLikesCount(id);
    }
    @Cacheable(key = "#id",value = "subscribesCount")
    @GetMapping("subscribes/count/{id}")
    public Integer getSubscribesCount(@PathVariable Integer id){
        return userService.getLikesCount(id);
    }
    @PostMapping("email/change/{id}")
    public String changeEmail(@PathVariable Integer id,@RequestParam("password") String password ,  @RequestParam("email") String email){
        return userService.changeEmail(id,password,email);
    }
}
