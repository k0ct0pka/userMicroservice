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
    private String followersIds;
    private String subscribesIds;
    private String groupsIds;
    private String repostsIds;
    private String postsLiked;
    private Integer reports;
    private Integer followersCount;
    private Integer subscribesCount;
    private Integer likesCount;
    private Boolean banned;
    private String email;
}
