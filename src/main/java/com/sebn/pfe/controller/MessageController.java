/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sebn.pfe.controller;

import com.sebn.pfe.model.Message;
import com.sebn.pfe.model.User;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HP
 */
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class MessageController {
    
    @Autowired
   private com.sebn.pfe.repository.MessageRepository MessageRepository;
   private Optional<User> user;
   
   

   @GetMapping("/messages")
   public List<Message> getAllMessages() {
       return  (List<Message>) MessageRepository.findAll();
       //return (List<Message>) MessageRepository.findAll();
   }

   @PostMapping("/message")
   public Message createMessage(@Valid @RequestBody Message messages) {
       return MessageRepository.save(messages);
   }
}
