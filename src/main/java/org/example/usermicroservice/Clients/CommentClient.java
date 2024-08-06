package org.example.usermicroservice.Clients;

import org.example.usermicroservice.dto.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "comments",url = "http://localhost:8080/comments")
public interface CommentClient {
    @GetMapping("/{commentId}")
    List<Comment> getCommentById(@PathVariable Integer commentId);
    @PostMapping("/create")
    Comment createComment(@RequestBody Comment comment);
    @GetMapping("/user")
    List<Comment> getCommentsByUser(@RequestParam Integer userId,@RequestParam("count") int count);
    @DeleteMapping("/user")
    void deleteCommentsByUser(@RequestParam Integer userId);
    @DeleteMapping("/user/{id}")
    void deleteComment(@PathVariable Integer id,@RequestParam("userId") Integer userId);
}
