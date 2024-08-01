package org.example.usermicroservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.usermicroservice.Clients.GroupsClient;
import org.example.usermicroservice.Repository.UserRepository;
import org.example.usermicroservice.component.User;
import org.example.usermicroservice.dto.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StorageService s3;
    @Autowired
    private User user;
    @Autowired
    private GroupsClient groupsClient;

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

    public List<Integer> getFollowersIds(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getFollowersIds();
    }

    public List<Integer> setFollowersIds(Integer id, List<Integer> followersIds) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFollowersIds(followersIds);
        userRepository.save(user);
        return user.getFollowersIds();
    }

    public List<Integer> deleteFollowersIds(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFollowersIds(new ArrayList<>());
        userRepository.save(user);
        return user.getFollowersIds();
    }

    public List<Integer> getSubscribesIds(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getSubscribesIds();
    }

    public List<Integer> setSubscribesIds(Integer id, List<Integer> subscribesIds) {
        User user = userRepository.findById(id).orElseThrow();
        user.setSubscribesIds(subscribesIds);
        userRepository.save(user);
        return user.getSubscribesIds();
    }

    public List<Integer> deleteSubscribesIds(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setSubscribesIds(new ArrayList<>());
        userRepository.save(user);
        return user.getSubscribesIds();
    }

    public List<Integer> getGroupsIds(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getGroupsIds();
    }

    public List<Integer> setGroupsIds(Integer id, List<Integer> groupIds) {
        User user = userRepository.findById(id).orElseThrow();
        user.setGroupsIds(groupIds);
        userRepository.save(user);
        return user.getGroupsIds();
    }

    public List<Integer> deleteGroupsIds(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setGroupsIds(new ArrayList<>());
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
    public List<User> getFollowers(Integer id,int count) {
        User user = userRepository.findById(id).orElseThrow();
        List<Integer> followers_ids = user.getFollowersIds();
        List<User> followers = new ArrayList<User>();
        return getUsers(count, followers_ids, followers);
    }
    public User followOnAndGetWhoFollowed(Integer idWho , Integer idOn) {
        User userOnFollowed = userRepository.findById(idOn).orElseThrow();
        userOnFollowed.getFollowersIds().add(idWho);
        setFollowersIds(idOn, userOnFollowed.getFollowersIds());
        setFollowersCount(idOn, userOnFollowed.getFollowersCount() + 1);
        User userWhoFollowed = userRepository.findById(idWho).orElseThrow();
        userWhoFollowed.getSubscribesIds().add(idOn);
        setSubscribesIds(idWho, userWhoFollowed.getSubscribesIds());
        setSubscribesCount(idWho, userWhoFollowed.getSubscribesCount() + 1);
        return userRepository.findById(idWho).orElseThrow();
    }
    public User unfollowOn(Integer idWho, Integer idOn){
        User userOn = userRepository.findById(idOn).orElseThrow();
        user.getFollowersIds().remove(idWho);
        setFollowersIds(idOn, user.getFollowersIds());
        setFollowersCount(idOn, userOn.getFollowersCount() - 1);
        User userWhoFollowed = userRepository.findById(idWho).orElseThrow();
        userWhoFollowed.getSubscribesIds().remove(idOn);
        setSubscribesIds(idWho, userWhoFollowed.getSubscribesIds());
        setSubscribesCount(idWho, userWhoFollowed.getSubscribesCount() - 1);
        return userRepository.findById(idWho).orElseThrow();
    }
    public List<User> getSubscribedUsers(Integer id,int count) {
        User user = userRepository.findById(id).orElseThrow();
        List<Integer> subscribesIds = user.getSubscribesIds();
        List<User> subscribes = new ArrayList<>();
        return getUsers(count, subscribesIds, subscribes);
    }
    public List<Group> getSubscribedGroups(Integer id,int count) {
        User user = userRepository.findById(id).orElseThrow();
        List<Integer> groupsIds = user.getSubscribesIds();
        return groupsClient.getGroupsByIds(groupsIds,count);
    }
    public List<Group> getCreatedGroups(Integer id,int count) {
        User user = userRepository.findById(id).orElseThrow();
        return groupsClient.getGroupsByOwner(id,count);
    }
    public User joinGroup(Integer groupId, Integer userId){
        groupsClient.addMember(userId,groupId);
        User user = userRepository.findById(userId).orElseThrow();
        user.getGroupsIds().add(groupId);
        return user;
    }
    public User leaveGroup(Integer groupId, Integer userId){
        User user = userRepository.findById(userId).orElseThrow();
        user.getGroupsIds().remove(groupId);
        groupsClient.deleteMemberById(userId,groupId);
        return user;
    }
    public boolean removeUserFromGroup(Integer idWho,Integer userId, Integer groupId){
        if(Objects.equals(idWho, userId))return false;
        if(isOwner(userId,groupId))return false;
        else if(isOwner(idWho,groupId)){
            leaveGroup(groupId,userId);
            groupsClient.deleteMemberById(userId,groupId);
            return true;
        }else if(isAdmin(idWho,groupId)){
            if(isAdmin(userId,groupId)){
                return false;
            } else{
                leaveGroup(groupId,userId);
                groupsClient.deleteMemberById(userId,groupId);
            }
        }
        return false;
    }
    private boolean isAdmin(Integer id,Integer groupId){
        return groupsClient.getAdminsIds(groupId).contains(id);
    }
    private boolean isOwner(Integer id,Integer groupId){
        return groupsClient.getOwnerId(groupId).equals(id);
    }
    private List<User> getUsers(int count, List<Integer> subscribesIds, List<User> result) {
        int current = 0;
        for (Integer subscribesId : subscribesIds) {
            User subscribe = userRepository.findById(subscribesId).orElseThrow();
            result.add(subscribe);
            current++;
            if(current == count) {
                return result;
            }
        }
        return result;
    }


}
