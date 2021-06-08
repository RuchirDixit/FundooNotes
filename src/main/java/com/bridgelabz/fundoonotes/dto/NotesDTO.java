package com.bridgelabz.fundoonotes.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Data;

public @Data class NotesDTO {

	@NotBlank(message = "Title cannot be blank")
	private String title;
	
	@NotBlank(message = "Description cannot be blank")
	private String description;
	
	private int userId;
	
	@NotBlank(message = "Email id cannot be blank")
	private String emailId;
	
	@NotBlank(message = "Colour cannot be blank")
	private String color;
	
	private LocalDateTime remindertime;
}
