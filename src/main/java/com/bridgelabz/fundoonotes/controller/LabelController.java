package com.bridgelabz.fundoonotes.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.LabelDTO;
import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.service.ILabelService;
import com.bridgelabz.fundoonotes.util.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("label")
@Slf4j
public class LabelController {

	@Autowired
	ILabelService labelService;
	
	/**
	 * To create new label
	 * @param token : To verfiy user
	 * @param labelDTO : Details of label passed
	 * @return ResponseEntity<Response>
	 */
	@PostMapping("/addLabel/{token}")
	public ResponseEntity<Response> addNewLabel(@PathVariable long token,@RequestBody LabelDTO labelDTO){
		log.debug("Create label");
		Response response = labelService.addNewLabel(token,labelDTO);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/readLabels/{token}")
	public ResponseEntity<List<?>> readLabels(@PathVariable long token){
		log.debug("Create label");
		List<Label> response = labelService.readLabels(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping("/updateLabel/{token}")
	public ResponseEntity<Response> updateNote(@PathVariable long token,@RequestBody LabelDTO dto){
		log.debug("Update Label");
		Response response = labelService.updateLabel(token,dto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteLabel/{token}")
	public ResponseEntity<Response> deleteLabel(@PathVariable long token){
		log.debug("Delete label");
		Response response = labelService.deleteLabel(token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("addLabelToNote/{token}")
	public ResponseEntity<Response> addLabelToNote(@PathVariable long token,@RequestParam("noteId") long noteId, @RequestParam("labelId") long labelId){
		log.debug("Add label to note");
		Response response = labelService.addLabelToNote(token,noteId,labelId);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("removeLabelFromNote/{token}")
	public ResponseEntity<Response> removeLabelFromNote(@PathVariable long token,@RequestParam("noteId") long noteId, @RequestParam("labelId") long labelId){
		log.debug("Remove label to note");
		Response response = labelService.removeLabelFromNote(token,noteId,labelId);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
}
