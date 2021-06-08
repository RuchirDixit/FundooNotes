package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.service.IFundooNotesService;
import com.bridgelabz.fundoonotes.util.Response;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/notes")
@Slf4j
public class FundooNotesController {

	@Autowired
	IFundooNotesService fundoNotesService;
	
	@PostMapping("/createNote")
	public ResponseEntity<Response> addNewUser(@Valid @RequestBody NotesDTO dto){
		log.debug("Add notes");
		Response notesEntity = fundoNotesService.addNewNote(dto);
		return new ResponseEntity<Response>(notesEntity,HttpStatus.OK);
	}
	
	@GetMapping("/archiveNote/{token}")
	public ResponseEntity<Response> addNoteToArchive(@PathVariable String token){
		Response response = fundoNotesService.addNoteToArchive(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/pinNote/{token}")
	public ResponseEntity<Response> addNoteToPinned(@PathVariable String token){
		Response response = fundoNotesService.addNoteToPinned(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getAllTrash/{token}")
	public ResponseEntity<List<?>> getAllTrash(@PathVariable String token){
		List<Note> response = fundoNotesService.getAllNotesInTrash(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getAllPinned/{token}")
	public ResponseEntity<List<?>> getAllPinned(@PathVariable String token){
		List<Note> response = fundoNotesService.getAllNotesAddedToPin(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getNotes/{token}")
	public ResponseEntity<List<?>> getNotes(@PathVariable String token){
		List<Note> response = fundoNotesService.getNotes(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/restoreNote/{token}")
	public ResponseEntity<Response> restoreNote(@PathVariable String token){
		Response response = fundoNotesService.restoreNoteFromTrash(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping("/updateNote/{token}")
	public ResponseEntity<Response> updateNote(@PathVariable String token,@RequestBody NotesDTO dto){
		log.debug("Update notes");
		Response response = fundoNotesService.updateNote(token,dto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/addNoteToTrash/{token}")
	public ResponseEntity<Response> addNoteToTrash(@PathVariable String token){
		Response response = fundoNotesService.addNoteToTrash(token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteNote/{token}")
	public ResponseEntity<Response> deleteNote(@PathVariable String token){
		Response response = fundoNotesService.deleteNote(token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
