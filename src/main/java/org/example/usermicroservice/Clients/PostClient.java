package org.example.usermicroservice.Clients;

import org.example.usermicroservice.dto.Group;
import org.example.usermicroservice.dto.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="posts",url="http://localhost:8080/posts")
public interface PostClient {
    @GetMapping("")
    public List<Post> getPosts();
    @GetMapping("/user")
    public List<Post> getPostsByIds(@RequestParam("ids") List<Integer> ids, int count);
    @GetMapping("/user/{id}")
    List<Post> getPostsByOwner(@PathVariable("id") Integer id, int count);
    @GetMapping("/{id}")
    Post getPostById(@PathVariable("id") Integer id);
    @PostMapping("/user/like")
    void likePost(@RequestParam("postId") Integer postId);
    @PutMapping("/user/comment")
    void commentPost(@RequestParam("postId") Integer postId, @RequestParam("commentId") Integer commentId);
}
