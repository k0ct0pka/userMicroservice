package org.example.usermicroservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.usermicroservice.Repository.UserRepository;
import org.example.usermicroservice.component.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public UserService() throws InstantiationException, IllegalAccessException {
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
    public byte[] uploadProfileImage(MultipartFile file,Integer userId) {

        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(user1 -> {
            user1.setProfileImageId(UUID.randomUUID().toString());
            userRepository.save(user1);
            s3.uploadFile(file, Math.toIntExact(user1.getId()),user1.getProfileImageId());
        });
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public byte[] getProfileImage(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        if(user.getProfileImageId().equals(""))return null;
        return s3.downloadFile(userId, user.getProfileImageId());
    }
    public byte[] updateProfileImage(MultipartFile file,Integer userId) {
        Optional<User> user = userRepository.findById(userId);

        user.ifPresent(user1 ->
        {
            s3.deleteFile( Math.toIntExact(user1.getId()),user1.getProfileImageId());
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
        s3.deleteFile(id,user.getProfileImageId());
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    public String  getUserName(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getUserName();
    }
    public String  setUserName(Integer id, String userName) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUserName(userName);
        userRepository.save(user);
        return user.getUserName();
    }
    public String updateUserName(Integer id, String userName) {
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
    public String updateNickName(Integer id, String nickName) {
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
}
