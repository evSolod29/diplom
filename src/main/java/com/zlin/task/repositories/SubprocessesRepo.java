package com.zlin.task.repositories;

import com.querydsl.core.types.Predicate;
import com.zlin.task.models.Subprocess;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubprocessesRepo extends 
        PagingAndSortingRepository<Subprocess, Long>
, QuerydslPredicateExecutor<Subprocess> {
    Page<Subprocess> findAll(Pageable pageable);
    Page<Subprocess> findAll(Predicate predicate, Pageable pageable);
}