package com.zlin.task.repositories;

import java.util.Date;
import java.util.Optional;

import com.querydsl.core.types.Predicate;
import com.zlin.task.models.Repair;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RepairRepo
        extends CrudRepository<Repair, Long>
        , PagingAndSortingRepository<Repair, Long>
        , QuerydslPredicateExecutor<Repair> {
    Page<Repair> findAll(Pageable pageable);
    Page<Repair> findAll(Predicate predicate, Pageable pageable);
    Optional<Repair> findByStartDate(Date startDate);
}