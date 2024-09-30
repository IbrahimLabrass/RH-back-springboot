/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sebn.pfe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author ibrahim
 */

@Entity

public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
                        
    private String titre;
        
        
    
    private String date_publication;
         

    
    private String contenu;
    
    
    private String skills;
    
    private String document;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Offre(Integer min, Integer max, String titre, String document, String skills, String date_publication, String dead_line, String contenu, User user, boolean b) {
        this.titre = titre;
        this.skills = skills;

        this.date_publication = date_publication;
        this.contenu = contenu;
        this.document = document;
        this.user = user;
    }
    public Offre() {
		
	}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", contenu='" + contenu + '\'' +
                ", titre='" + titre + '\'' +
                ", skills='" + skills + '\'' +
                ", date_publication='" + date_publication + '\'' +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getDate_publication() {
		return date_publication;
	}
	public void setDate_publication(String date_publication) {
		this.date_publication = date_publication;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
    
	
	
}
