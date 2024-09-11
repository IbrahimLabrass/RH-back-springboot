package com.sebn.pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebn.pfe.model.Demande;

public interface DemandeRepository extends JpaRepository<Demande,Long>  {

}