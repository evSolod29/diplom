package com.zlin.task.Repositories;

import java.util.Date;
import java.util.Optional;

import com.zlin.task.Models.Repair;

import org.springframework.data.repository.CrudRepository;

public interface RepairRepo extends CrudRepository<Repair, Long> {
    Iterable<Repair> findAll();
    Optional<Repair> findByStartDate(Date startDate);
}