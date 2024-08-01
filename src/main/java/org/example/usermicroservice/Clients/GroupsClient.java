package org.example.usermicroservice.Clients;

import org.example.usermicroservice.dto.Group;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="groups",url="http://localhost:8080/groups")
public interface GroupsClient {
    @GetMapping("")
    public List<Group> getGroups();
}
