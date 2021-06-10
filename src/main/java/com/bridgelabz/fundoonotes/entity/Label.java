package com.bridgelabz.fundoonotes.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "label")
public @Data class Label {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name="id")
	private Long id;


	@Column(name="labelName")
	private String labelName;


	@Column(name="userId")
	private Long userId;


	@Column(name="noteId")
	private Long noteId;


	@Column(name = "registeredDate")
	private LocalDateTime registerDate = LocalDateTime.now();


	@Column(name = "UpdatedDate")
	private LocalDateTime updateDate;


	@ManyToMany(mappedBy = "labellist")
	private List<Note> notes;


}
