package com.zlin.task.Repositories;

import java.util.Optional;

import com.zlin.task.Models.Computer;

import org.springframework.data.repository.CrudRepository;

public interface ComputersRepo extends CrudRepository<Computer, Long>, CustomizedComputersRepo<Computer> {
    Iterable<Computer> findAll();
    Optional<Computer> findByName(String name);
}