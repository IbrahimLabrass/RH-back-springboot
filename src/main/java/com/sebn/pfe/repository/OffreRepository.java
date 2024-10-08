/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sebn.pfe.repository;

import com.sebn.pfe.model.Offre;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OffreRepository extends JpaRepository<Offre,Long> {
    Optional<Offre> findByTitre(String titre);
    List<Offre> findByUser(String user);
    
    
}