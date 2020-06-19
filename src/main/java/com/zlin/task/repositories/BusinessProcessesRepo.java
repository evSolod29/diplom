package com.zlin.task.repositories;

import com.querydsl.core.types.Predicate;
import com.zlin.task.models.BusinessProcess;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BusinessProcessesRepo extends 
        PagingAndSortingRepository<BusinessProcess, Long>
, QuerydslPredicateExecutor<BusinessProcess> {
    Page<BusinessProcess> findAll(Pageable pageable);
    Page<BusinessProcess> findAll(Predicate predicate, Pageable pageable);
}