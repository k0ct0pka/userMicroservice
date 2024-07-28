package org.example.usermicroservice.Repository;

import org.example.usermicroservice.component.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    
}
