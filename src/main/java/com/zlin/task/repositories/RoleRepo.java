package com.zlin.task.repositories;

import com.zlin.task.models.Role;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Long> {
}