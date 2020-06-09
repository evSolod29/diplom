package com.zlin.task.repositories;

import com.querydsl.core.types.Predicate;
import com.zlin.task.models.Work;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WorksRepo
        extends CrudRepository<Work, Long>
, PagingAndSortingRepository<Work, Long>
, QuerydslPredicateExecutor<Work> {
    Page<Work> findAll(Pageable pageable);
    Page<Work> findAll(Predicate predicate, Pageable pageable);
}