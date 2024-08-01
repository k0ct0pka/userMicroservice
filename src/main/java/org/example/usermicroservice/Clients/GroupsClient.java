package org.example.usermicroservice.Clients;

import org.example.usermicroservice.component.User;
import org.example.usermicroservice.dto.Group;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="groups",url="http://localhost:8080/groups")
public interface GroupsClient {
    @GetMapping("")
    public List<Group> getGroups();
    @GetMapping("/user")
    public List<Group> getGroupsByIds(@RequestParam("ids") List<Integer> ids,int count);
    @GetMapping("/owner/{id}")
    List<Group> getGroupsByOwner(@PathVariable("id") Integer id,int count);
    @GetMapping("/{id}")
    Group getGroupById(@PathVariable("id") Integer id);
    @DeleteMapping("/user/{id}")
    void deleteMemberById(@PathVariable("id") Integer id,@RequestParam("groupId") Integer groupId);
    @PostMapping("/user/{id}")
    void addMember(@PathVariable("id") Integer userId,@RequestParam("groupId") Integer groupId);
    @GetMapping("/owner")
    Integer getOwnerId(@RequestParam("groupId") Integer groupId);
    @GetMapping("/admins")
    List<Integer> getAdminsIds(@RequestParam("groupId") Integer groupId);
}
