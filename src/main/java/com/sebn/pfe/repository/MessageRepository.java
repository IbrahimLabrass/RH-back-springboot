/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sebn.pfe.repository;

import com.sebn.pfe.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author HP
 */
public interface MessageRepository extends JpaRepository<Message,Long>  {
    
}
