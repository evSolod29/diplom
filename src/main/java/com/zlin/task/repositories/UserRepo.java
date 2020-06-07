package com.zlin.task.repositories;

import com.zlin.task.models.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
