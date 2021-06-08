package com.bridgelabz.fundoonotes.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.repository.FundooNotesRepository;
import com.bridgelabz.fundoonotes.util.Response;
import com.bridgelabz.fundoonotes.util.TokenUtil;
import com.bridgelabz.fundoonotes.exception.UserRegisterException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FundooNotesService implements IFundooNotesService{

	@Autowired
	FundooNotesRepository fundooNotesRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	TokenUtil tokenUtil;

	@Override
	public Response addNewNote(NotesDTO dto) {
		Optional<Note> isNotePresent = fundooNotesRepository.findByEmailId(dto.getEmailId());
		if(isNotePresent.isPresent()) {
			log.error("Not exists already.");
			throw new UserRegisterException(400,"User already exists");
		}
		else {
			Note note = mapper.map(dto, Note.class);
			fundooNotesRepository.save(note);
			String token = tokenUtil.createToken(note.getId());
			log.debug("Note added.");
			return new Response(200, "Note added!", token);
		}
	}

	@Override
	public Response updateNote(String token, NotesDTO dto) {
		Long id = tokenUtil.decodeToken(token);
		Optional<Note> isNotePresent =  fundooNotesRepository.findById(id);
		if(isNotePresent.isPresent()) {
			isNotePresent.get().setTitle(dto.getTitle());
			isNotePresent.get().setDescription(dto.getDescription());
			isNotePresent.get().setColor(dto.getColor());
			isNotePresent.get().setEmailId(dto.getEmailId());
			isNotePresent.get().setRemindertime(dto.getRemindertime());
			isNotePresent.get().setUserId(dto.getUserId());
			isNotePresent.get().setUpdateDate(LocalDateTime.now());
			fundooNotesRepository.save(isNotePresent.get());
			log.debug("Note updated");
			return new Response(200, "Note updated SuccessFully", null);
		}
		else {
			log.error("Note not Found");
			throw new UserRegisterException(404, "Note not found");
		}
	}

	@Override
	public Response addNoteToArchive(String token) {
		Long id = tokenUtil.decodeToken(token);
		Optional<Note> isNotePresent =  fundooNotesRepository.findById(id);
		if(isNotePresent.isPresent()) {
			if(isNotePresent.get().isArchived() == false) {
				isNotePresent.get().setArchived(true);
				fundooNotesRepository.save(isNotePresent.get());
				log.debug("Note Archived!");
				return new Response(200, "Note archived", null);
			}
			else {
				log.debug("Note already Archived!");
				return new Response(400, "Note already archived", null);
			}
		}
		else {
			log.error("Note not Found");
			throw new UserRegisterException(404, "Note not found");
		}
	}

	@Override
	public Response addNoteToPinned(String token) {
		Long id = tokenUtil.decodeToken(token);
		Optional<Note> isNotePresent =  fundooNotesRepository.findById(id);
		if(isNotePresent.isPresent()) {
			if(isNotePresent.get().isPinned() == false) {
				isNotePresent.get().setPinned(true);
				fundooNotesRepository.save(isNotePresent.get());
				log.debug("Note Pinned!");
				return new Response(200, "Note Pinned", null);
			}
			else {
				log.debug("Note already Pinned!");
				return new Response(400, "Note already Pinned", null);
			}
		}
		else {
			log.error("Note not Found");
			throw new UserRegisterException(404, "Note not found");
		}
	}

	@Override
	public Response addNoteToTrash(String token) {
		Long id = tokenUtil.decodeToken(token);
		Optional<Note> isNotePresent =  fundooNotesRepository.findById(id);
		if(isNotePresent.isPresent()) {
			if(isNotePresent.get().isTrashed() == false) {
				isNotePresent.get().setTrashed(true);
				fundooNotesRepository.save(isNotePresent.get());
				log.debug("Note added to trash!");
				return new Response(200, "Note added to trash", null);
			}
			else {
				log.debug("Note already added to trash!");
				return new Response(400, "Note already added to trash", null);
			}
		}
		else {
			log.error("Note not Found");
			throw new UserRegisterException(404, "Note not found");
		}
	}

	@Override
	public List<Note> getAllNotesInTrash(String token) {
		Long id = tokenUtil.decodeToken(token);
		Optional<Note> isNotePresent =  fundooNotesRepository.findById(id);
		if(isNotePresent.isPresent()) {
			List<Note> notes = fundooNotesRepository.findAll();
			return notes.stream().filter(note -> note.isArchived()==true).collect(Collectors.toList());
		}
		else {
			log.error("Note not Found");
			throw new UserRegisterException(404, "Note not found");
		}
	}

	@Override
	public List<Note> getAllNotesAddedToPin(String token) {
		Long id = tokenUtil.decodeToken(token);
		Optional<Note> isNotePresent =  fundooNotesRepository.findById(id);
		if(isNotePresent.isPresent()) {
			List<Note> notes = fundooNotesRepository.findAll();
			return notes.stream().filter(note -> note.isPinned()==true).collect(Collectors.toList());
		}
		else {
			log.error("Note not Found");
			throw new UserRegisterException(404, "Note not found");
		}
	}

	@Override
	public List<Note> getNotes(String token) {
		Long id = tokenUtil.decodeToken(token);
		Optional<Note> isNotePresent =  fundooNotesRepository.findById(id);
		if(isNotePresent.isPresent()) {
			List<Note> notes = fundooNotesRepository.findAll();
			return notes.stream().filter(note -> note.isPinned()==false && note.isArchived() == false && note.isTrashed()==false).collect(Collectors.toList());
		}
		else {
			log.error("Note not Found");
			throw new UserRegisterException(404, "Note not found");
		}
	}

	@Override
	public Response deleteNote(String token) {
		Long id = tokenUtil.decodeToken(token);
		Optional<Note> isNotePresent =  fundooNotesRepository.findById(id);
		if(isNotePresent.isPresent()) {
			if(isNotePresent.get().isTrashed() == false) {
				return addNoteToTrash(token);
			}
			else {
				fundooNotesRepository.delete(isNotePresent.get());
				log.debug("Note deleted forever");
				return new Response(200, "Note permanently deleted", null);
			}
		}
		else {
			log.error("Note not Found");
			throw new UserRegisterException(404, "Note not found");
		}
	}

	@Override
	public Response restoreNoteFromTrash(String token) {
		Long id = tokenUtil.decodeToken(token);
		Optional<Note> isNotePresent =  fundooNotesRepository.findById(id);
		if(isNotePresent.isPresent()) {
			if(isNotePresent.get().isTrashed() == true) {
				isNotePresent.get().setTrashed(false);
				fundooNotesRepository.save(isNotePresent.get());
				return new Response(200, "Note Removed from trash", null);
			}
			else {			
				return new Response(400, "Note not in trash", null);
			}
		}
		else {
			log.error("Note not Found");
			throw new UserRegisterException(404, "Note not found");
		}
	}
}
