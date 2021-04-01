package com.devProgram.javaCoreAssessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devProgram.javaCoreAssessment.entities.SubCollection;

@Repository
public interface SubCollectionRepository extends JpaRepository<SubCollection, Long>{

}
