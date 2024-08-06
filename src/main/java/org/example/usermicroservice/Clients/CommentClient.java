package org.example.usermicroservice.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import javax.xml.stream.events.Comment;
import java.util.List;

@FeignClient(name = "comments",url = "http://localhost:8080/comments")
public interface CommentClient {
    @GetMapping("/{id}")
    List<Comment> getComments(@PathVariable Integer id);
}
