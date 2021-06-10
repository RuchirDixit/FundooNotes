package com.bridgelabz.fundoonotes.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.entity.Note;

import lombok.Data;

public @Data class NotesDTO {

	@NotBlank(message = "Title cannot be blank")
	private String title;
	
	@NotBlank(message = "Description cannot be blank")
	private String description;
	
	private long userId;
	
	@NotBlank(message = "Email id cannot be blank")
	private String emailId;
	
	@NotBlank(message = "Colour cannot be blank")
	private String color;
	
	private LocalDateTime remindertime;
}
