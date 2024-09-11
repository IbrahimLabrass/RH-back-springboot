package com.sebn.pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebn.pfe.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long>  {

}