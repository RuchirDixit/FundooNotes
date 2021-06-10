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

import com.bridgelabz.fundoonotes.dto.ColabDTO;
import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.entity.Collaborator;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.repository.CollaboratorRepository;
import com.bridgelabz.fundoonotes.repository.FundooNotesRepository;
import com.bridgelabz.fundoonotes.util.Response;
import com.bridgelabz.fundoonotes.util.TokenUtil;
import com.bridgelabz.fundoonotes.exception.NotesException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FundooNotesService implements IFundooNotesService{

	@Autowired
	FundooNotesRepository fundooNotesRepository;
	
	@Autowired
	CollaboratorRepository collabRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	TokenUtil tokenUtil;

	@Override
	public Response addNewNote(NotesDTO dto) {
		Optional<Note> isNotePresent = fundooNotesRepository.findByEmailId(dto.getEmailId());
		if(isNotePresent.isPresent()) {
			log.error("Not exists already.");
			throw new NotesException(400,"User already exists");
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
	public Response updateNote(long userId, NotesDTO dto) {
		//Long id = tokenUtil.decodeToken(token);
		if(userId != 0) {
			Optional<Note> isNotePresent =  fundooNotesRepository.findByUserId(userId);
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
				throw new NotesException(404, "Note not found");
			}
		}
		else {
			log.error("Note not Found");
			throw new NotesException(404, "Note not found");
		}
	}

	@Override
	public Response addNoteToArchive(long token) {
		//Long id = tokenUtil.decodeToken(token);
		if(token != 0) {
			Optional<Note> isNotePresent =  fundooNotesRepository.findByUserId(token);
			if(isNotePresent.isPresent()) {
				if(isNotePresent.get().isArchived() == false) {
					isNotePresent.get().setArchived(true);
					fundooNotesRepository.save(isNotePresent.get());
					log.debug("Note Archived!");
					return new Response(200, "Note archived", null);
				}
				else {
					log.debug("Note already Archived!");
					isNotePresent.get().setArchived(false);
					fundooNotesRepository.save(isNotePresent.get());
					return new Response(400, "Note unarchived", null);
				}
			}
			else {
				log.error("Note not Found");
				throw new NotesException(404, "Note not found");
			}
		}	
		else {
			log.error("Note not Found");
			throw new NotesException(404, "Note not found");
		}
	}

	@Override
	public Response addNoteToPinned(long token) {
		//Long id = tokenUtil.decodeToken(token);
		if(token != 0) {
			Optional<Note> isNotePresent =  fundooNotesRepository.findByUserId(token);
			if(isNotePresent.isPresent()) {
				if(isNotePresent.get().isPinned() == false) {
					isNotePresent.get().setPinned(true);
					fundooNotesRepository.save(isNotePresent.get());
					log.debug("Note Pinned!");
					return new Response(200, "Note Pinned", null);
				}
				else {
					log.debug("Note already Pinned!");
					isNotePresent.get().setPinned(false);
					fundooNotesRepository.save(isNotePresent.get());
					return new Response(400, "Note unPinned", null);
				}
			}
			else {
				log.error("Note not Found");
				throw new NotesException(404, "Note not found");
			}
		}	
		else {
			log.error("Note not Found");
			throw new NotesException(404, "Note not found");
		}
	}

	@Override
	public Response addNoteToTrash(long token) {
		//Long id = tokenUtil.decodeToken(token);
		if(token != 0) {
			Optional<Note> isNotePresent =  fundooNotesRepository.findByUserId(token);
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
				throw new NotesException(404, "Note not found");
			}
		}
		else {
			log.error("Note not Found");
			throw new NotesException(404, "Note not found");
		}
	}

	@Override
	public List<Note> getAllNotesInTrash(long token) {
		//Long id = tokenUtil.decodeToken(token);
		if(token != 0) {
			Optional<Note> isNotePresent =  fundooNotesRepository.findByUserId(token);
			if(isNotePresent.isPresent()) {
				List<Note> notes = fundooNotesRepository.findAll();
				return notes.stream().filter(note -> note.isArchived()==true).collect(Collectors.toList());
			}
			else {
				log.error("Note not Found");
				throw new NotesException(404, "Note not found");
			}
		}
		else {
			log.error("Note not Found");
			throw new NotesException(404, "Note not found");
		}	
	}

	@Override
	public List<Note> getAllNotesAddedToPin(long token) {
		//Long id = tokenUtil.decodeToken(token);
		if(token != 0) {
			Optional<Note> isNotePresent =  fundooNotesRepository.findByUserId(token);
			if(isNotePresent.isPresent()) {
				List<Note> notes = fundooNotesRepository.findAll();
				return notes.stream().filter(note -> note.isPinned()==true).collect(Collectors.toList());
			}
			else {
				log.error("Note not Found");
				throw new NotesException(404, "Note not found");
			}
		}
		else {
			log.error("Note not Found");
			throw new NotesException(404, "Note not found");
		}
	}

	@Override
	public List<Note> getNotes(long token) {
		//Long id = tokenUtil.decodeToken(token);
		if(token != 0) {
			Optional<Note> isNotePresent =  fundooNotesRepository.findByUserId(token);
			if(isNotePresent.isPresent()) {
				List<Note> notes = fundooNotesRepository.findAll();
				return notes.stream().filter(note -> note.isPinned()==false && note.isArchived() == false && note.isTrashed()==false).collect(Collectors.toList());
			}
			else {
				log.error("Note not Found");
				throw new NotesException(404, "Note not found");
			}
		}
		else {
			log.error("Note not Found");
			throw new NotesException(404, "Note not found");
		}
		
	}

	@Override
	public Response deleteNote(long token) {
		//Long id = tokenUtil.decodeToken(token);
		if(token!=0) {
			Optional<Note> isNotePresent =  fundooNotesRepository.findByUserId(token);
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
				throw new NotesException(404, "Note not found");
			}
		}
		else {
			log.error("Note not Found");
			throw new NotesException(404, "Note not found");
		}
	}

	@Override
	public Response restoreNoteFromTrash(long token) {
		//Long id = tokenUtil.decodeToken(token);
		if(token!=0) {
			Optional<Note> isNotePresent =  fundooNotesRepository.findByUserId(token);
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
				throw new NotesException(404, "Note not found");
			}
		}
		else {
			log.error("Note not Found");
			throw new NotesException(404, "Note not found");
		}
	}

	/**
	 * To add collaborator to notes
	 * @param token: user id to verify, colabDto: ColabDTO
	 * @return Response
	 */
	@Override
	public Response addCollaboratorToNotes(Long token, ColabDTO colabDto) {
		if(token!=0) {
			Optional<Note> isNotePresent =  fundooNotesRepository.findById(colabDto.getNoteId());
			if(isNotePresent.isPresent()) {
				Collaborator colabModel = mapper.map(colabDto, Collaborator.class);
				colabModel.setCollabEmail(colabDto.getCollabEmail());
				colabModel.setNoteId(colabDto.getNoteId());
				collabRepository.save(colabModel);
				//isNotePresent.get().getCollaborator().add
				isNotePresent.get().setUpdateDate(LocalDateTime.now());
				fundooNotesRepository.save(isNotePresent.get());
				log.info("User :" +colabDto.getCollabEmail() + " Added as collab.");
				return new Response(200, "Collaborator added!", null);
			}
			else {
				log.error("Note not Found");
				throw new NotesException(404, "Note with mentioned ID not found");
			}
		}
		else {
			log.error("Note not Found");
			throw new NotesException(404, "Note not found");
		}
	}
}
