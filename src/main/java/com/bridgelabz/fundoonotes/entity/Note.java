package com.bridgelabz.fundoonotes.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "notes")
public @Data class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "note_id")
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="note_description")
	private String description;
	
	@Column(name="userid")
	private long userId;
	
	@Column(name = "registered_date")
	private LocalDateTime registerDate  = LocalDateTime.now();
	
	@Column(name = "updated_date")
	private LocalDateTime updateDate;
	
	@Column(name="is_trashed")
	private boolean isTrashed = false;
	
	@Column(name="is_archived")
	private boolean isArchived = false;
	
	@Column(name="is_pinned")
	private boolean isPinned = false;
	
	@Column(name="email_id")
	private String emailId;

	@Column(name="color")
	private String color;
	
	@Column(name="reminder")
	private LocalDateTime remindertime;

	@JsonIgnore()
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Label> labellist;



}
