package org.example.usermicroservice.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {
    Integer id;
    Integer userId;
    String postText;
    String contentLinks;
    Integer likesCount;
    List<Integer> comments;
    String tag;
    Integer reportsCount;
    Integer reports;
}
