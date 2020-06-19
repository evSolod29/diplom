package com.zlin.task.repositories;

import com.querydsl.core.types.Predicate;
import com.zlin.task.models.Computer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComputersRepo extends PagingAndSortingRepository<Computer, Long>
    , QuerydslPredicateExecutor<Computer> {
    Page<Computer> findAll(Pageable pageable);
    Page<Computer> findAll(Predicate predicate, Pageable pageable);
}