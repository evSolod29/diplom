package com.zlin.task.Repositories;

import java.util.Optional;

import com.zlin.task.Models.AdditionalEquipment;

import org.springframework.data.repository.CrudRepository;

public interface AdditionalEquipmentsRepo extends CrudRepository<AdditionalEquipment, Long> {
    Iterable<AdditionalEquipment> findAll();
    Optional<AdditionalEquipment> findByName(String name);
}