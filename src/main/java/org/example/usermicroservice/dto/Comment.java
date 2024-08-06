package org.example.usermicroservice.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    Integer id;
    Integer userId;
    String text;
    Integer likes;
    Integer postId;
}
