package com.zlin.task.repositories;

import com.querydsl.core.types.Predicate;
import com.zlin.task.models.SubprocessReport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubprocessReportsRepo extends CrudRepository<SubprocessReport, Long>
, PagingAndSortingRepository<SubprocessReport, Long>
, QuerydslPredicateExecutor<SubprocessReport> {
Page<SubprocessReport> findAll(Pageable pageable);
Page<SubprocessReport> findAll(Predicate predicate, Pageable pageable);
    
}