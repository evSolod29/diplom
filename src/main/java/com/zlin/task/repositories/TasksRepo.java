package com.zlin.task.repositories;

import com.querydsl.core.types.Predicate;
import com.zlin.task.models.Task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TasksRepo extends CrudRepository<Task, Long>
, PagingAndSortingRepository<Task, Long>
, QuerydslPredicateExecutor<Task> {
    Page<Task> findAll(Pageable pageable);
    Page<Task> findAll(Predicate predicate, Pageable pageable);
}