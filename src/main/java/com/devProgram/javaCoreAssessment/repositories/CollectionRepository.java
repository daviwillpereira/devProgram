package com.devProgram.javaCoreAssessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devProgram.javaCoreAssessment.entities.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

}
