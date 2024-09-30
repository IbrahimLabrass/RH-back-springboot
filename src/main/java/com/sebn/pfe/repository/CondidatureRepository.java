package com.sebn.pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebn.pfe.model.Condidature;
import org.springframework.stereotype.Repository;

@Repository
public interface CondidatureRepository extends JpaRepository<Condidature,Long>  {

}
