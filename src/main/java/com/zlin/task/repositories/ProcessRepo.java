package com.zlin.task.repositories;

import com.querydsl.core.types.Predicate;
import com.zlin.task.models.Process;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProcessRepo extends CrudRepository<Process, Long>
, PagingAndSortingRepository<Process, Long>
, QuerydslPredicateExecutor<Process> {
    Page<Process> findAll(Pageable pageable);
    Page<Process> findAll(Predicate predicate, Pageable pageable);
}