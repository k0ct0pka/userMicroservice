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

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }

    public void deleteProfileImage(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        s3.deleteFile(id,user.getProfileImageId());
    }
}
