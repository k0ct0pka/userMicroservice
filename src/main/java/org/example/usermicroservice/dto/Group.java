package org.example.usermicroservice.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Group {
    Integer id;
    String name;
    String tag;
    Integer ownerId;
    List<Integer> adminsIds;
    List<Integer> memberIds;
    Integer reports;
}
