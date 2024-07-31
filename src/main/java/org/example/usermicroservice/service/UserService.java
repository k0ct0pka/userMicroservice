package org.example.usermicroservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.usermicroservice.Repository.UserRepository;
import org.example.usermicroservice.component.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StorageService s3;
    @Autowired
    private User user;

    public UserService() throws InstantiationException, IllegalAccessException {
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public byte[] uploadProfileImage(MultipartFile file, Integer userId) {

        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(user1 -> {
            user1.setProfileImageId(UUID.randomUUID().toString());
            userRepository.save(user1);
            s3.uploadFile(file, Math.toIntExact(user1.getId()), user1.getProfileImageId());
        });
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getProfileImage(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        if (user.getProfileImageId().equals("")) return null;
        return s3.downloadFile(userId, user.getProfileImageId());
    }

    public byte[] updateProfileImage(MultipartFile file, Integer userId) {
        Optional<User> user = userRepository.findById(userId);

        user.ifPresent(user1 ->
        {
            s3.deleteFile(Math.toIntExact(user1.getId()), user1.getProfileImageId());
            uploadProfileImage(file, userId);
        });
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteProfileImage(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        s3.deleteFile(id, user.getProfileImageId());
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public String getUserName(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getUserName();
    }

    public String setUserName(Integer id, String userName) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUserName(userName);
        userRepository.save(user);
        return user.getUserName();
    }

    public String deleteUserName(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUserName("");
        userRepository.save(user);
        return user.getUserName();
    }

    public String getNickName(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getNickname();
    }

    public String setNickName(Integer id, String nickName) {
        User user = userRepository.findById(id).orElseThrow();
        user.setNickname(nickName);
        userRepository.save(user);
        return user.getNickname();
    }

    public String deleteNickName(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setNickname("");
        userRepository.save(user);
        return user.getNickname();
    }

    public String getLogin(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getLogin();
    }

    public String setLogin(Integer id, String login) {
        User user = userRepository.findById(id).orElseThrow();
        user.setLogin(login);
        userRepository.save(user);
        return user.getLogin();
    }

    public String deleteLogin(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setLogin("");
        userRepository.save(user);
        return user.getLogin();
    }

    public String getPassword(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getPassword();
    }

    public String setPassword(Integer id, String password) {
        User user = userRepository.findById(id).orElseThrow();
        user.setPassword(password);
        userRepository.save(user);
        return user.getPassword();
    }

    public String deletePassword(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setPassword("");
        userRepository.save(user);
        return user.getPassword();
    }

    public String getFollowersIds(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getFollowersIds();
    }

    public String setFollowersIds(Integer id, String followersIds) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFollowersIds(followersIds);
        userRepository.save(user);
        return user.getFollowersIds();
    }

    public String deleteFollowersIds(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFollowersIds("");
        userRepository.save(user);
        return user.getFollowersIds();
    }

    public String getSubscribesIds(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getSubscribesIds();
    }

    public String setSubscribesIds(Integer id, String subscribesIds) {
        User user = userRepository.findById(id).orElseThrow();
        user.setSubscribesIds(subscribesIds);
        userRepository.save(user);
        return user.getSubscribesIds();
    }

    public String deleteSubscribesIds(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setSubscribesIds("");
        userRepository.save(user);
        return user.getSubscribesIds();
    }

    public String getGroupsIds(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getGroupsIds();
    }

    public String setGroupsIds(Integer id, String groupIds) {
        User user = userRepository.findById(id).orElseThrow();
        user.setGroupsIds(groupIds);
        userRepository.save(user);
        return user.getGroupsIds();
    }

    public String deleteGroupsIds(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setGroupsIds("");
        userRepository.save(user);
        return user.getGroupsIds();
    }

    public Integer getReports(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getReports();
    }

    public Integer setReports(Integer id, Integer reports) {
        User user = userRepository.findById(id).orElseThrow();
        user.setReports(reports);
        userRepository.save(user);
        return user.getReports();
    }

    public Integer deleteReports(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setReports(0);
        userRepository.save(user);
        return 0;
    }

    public Boolean getBanned(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getBanned();
    }

    public Boolean banUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setBanned(true);
        userRepository.save(user);
        return true;
    }

    public Boolean unBanUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setBanned(false);
        return false;
    }

    public Integer getFollowersCount(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getFollowersCount();
    }

    public Integer setFollowersCount(Integer id, Integer followersCount) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFollowersCount(followersCount);
        userRepository.save(user);
        return user.getFollowersCount();
    }

    public Integer deleteFollowersCount(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFollowersCount(0);
        userRepository.save(user);
        return user.getFollowersCount();
    }

    public Integer getLikesCount(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getLikesCount();
    }

    public Integer setLikesCount(Integer id, Integer likesCount) {
        User user = userRepository.findById(id).orElseThrow();
        user.setLikesCount(likesCount);
        userRepository.save(user);
        return user.getLikesCount();
    }

    public Integer deleteLikesCount(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setLikesCount(0);
        userRepository.save(user);
        return user.getLikesCount();
    }

    public Integer getSubscribesCount(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getSubscribesCount();
    }

    public Integer setSubscribesCount(Integer id, Integer subscribesCount) {
        User user = userRepository.findById(id).orElseThrow();
        user.setSubscribesCount(subscribesCount);
        userRepository.save(user);
        return user.getSubscribesCount();
    }

    public Integer deleteSubscribesCount(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setSubscribesCount(0);
        userRepository.save(user);
        return user.getSubscribesCount();
    }

    public String getEmail(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getEmail();
    }

    public String setEmail(Integer id, String email) {
        User user = userRepository.findById(id).orElseThrow();
        user.setEmail(email);
        userRepository.save(user);
        return user.getEmail();
    }

    public String deleteEmail(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setEmail("");
        userRepository.save(user);
        return "";
    }
    public List<User> getFollowers(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        String followers_ids = user.getFollowersIds();
        List<User> followers = new ArrayList<User>();
        for (String followers_id : followers_ids.split(",")) {
            User follower = userRepository.findById(Integer.parseInt(followers_id)).orElseThrow();
            followers.add(follower);
        }
        return followers;
    }
    public User followOnAndGetWhoFollowed(Integer idWho , Integer idOn) {
        User user = userRepository.findById(idOn).orElseThrow();
        setFollowersIds(idOn, user.getFollowersIds() + "," + idWho);
        setFollowersCount(idOn, user.getFollowersCount() + 1);
        return userRepository.findById(idWho).orElseThrow();
    }

}
