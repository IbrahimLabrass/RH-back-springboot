/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sebn.pfe.repository;
import com.sebn.pfe.model.Service;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ibrahim
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
    Optional<Service> findByTitre(String titre);

}
