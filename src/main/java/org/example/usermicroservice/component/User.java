package org.example.usermicroservice.component;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Component
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @SequenceGenerator(name = "generator1", allocationSize = 1)
    @GeneratedValue(generator = "generator1")
    private Long id;
    @ColumnDefault("")
    private String profileImageId;
    private String userName;
    private String nickname;
    private String login;
    private String password;
    @ElementCollection
    private List<Integer> followersIds;
    @ElementCollection
    private List<Integer> subscribesIds;
    @ElementCollection
    private List<Integer> groupsIds;
    @ElementCollection
    private List<Integer> repostsIds;
    @ElementCollection
    private List<Integer> postsLiked;
    @ElementCollection
    private List<Integer> posts;
    private Integer reports;
    private Integer followersCount;
    private Integer subscribesCount;
    private Integer likesCount;
    private Boolean banned;
    private String email;
}
