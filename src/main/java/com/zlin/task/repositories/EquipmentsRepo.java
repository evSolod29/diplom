package com.zlin.task.repositories;

import com.querydsl.core.types.Predicate;
import com.zlin.task.models.Equipment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EquipmentsRepo extends 
        PagingAndSortingRepository<Equipment, Long>
        , QuerydslPredicateExecutor<Equipment> {
    Page<Equipment> findAll(Pageable pageable);
    Page<Equipment> findAll(Predicate predicate, Pageable pageable);
}