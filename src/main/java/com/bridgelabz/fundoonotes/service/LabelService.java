package com.bridgelabz.fundoonotes.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoonotes.dto.LabelDTO;
import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.exception.NotesException;
import com.bridgelabz.fundoonotes.repository.FundooNotesRepository;
import com.bridgelabz.fundoonotes.repository.LabelRepository;
import com.bridgelabz.fundoonotes.util.Response;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LabelService implements ILabelService{

	@Autowired
	LabelRepository labelRepository;
	
	@Autowired
	FundooNotesRepository notesRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public Response addNewLabel(long token,LabelDTO labelDTO) {
		if(token!=0) {
			Label label = modelMapper.map(labelDTO, Label.class);
			label.setLabelName(labelDTO.getLabelName());
			label.setUserId(token);
			labelRepository.save(label);
			return new Response(200, "New Label created", null);
		}
		else {
			log.error("User not Valid.");
			throw new NotesException(400,"User not Valid.");
		}
	}

	@Override
	public List<Label> readLabels(long token) {
		if(token!=0) {
			List<Label> labels = labelRepository.findAll();
			return labels;
		}
		else {
			log.error("User not valid");
			throw new NotesException(400,"User not Valid.");
		}
	}

	@Override
	public Response updateLabel(long token, LabelDTO dto) {
		if(token!=0) {
			Optional<Label> isLabelPresent = labelRepository.findByUserId(token);
			if(isLabelPresent.isPresent()) {
				isLabelPresent.get().setLabelName(dto.getLabelName());
				isLabelPresent.get().setUpdateDate(LocalDateTime.now());
				labelRepository.save(isLabelPresent.get());
				return new Response(200, "Label updated successfully.", null);
			}
			else {
				log.error("Label not present");
				throw new NotesException(400,"Label not present");
			}
		}
		else {
			log.error("User not valid");
			throw new NotesException(400,"User not Valid.");
		}
	}

	@Override
	public Response deleteLabel(long token) {
		if(token!=0) {
			Optional<Label> isLabelPresent = labelRepository.findByUserId(token);
			if(isLabelPresent.isPresent()) {
				labelRepository.delete(isLabelPresent.get());
				return new Response(200, "Label deleted successfully.", null);
			}
			else {
				log.error("Label not present");
				throw new NotesException(400,"Label not present");
			}
		}
		else {
			log.error("User not valid");
			throw new NotesException(400,"User not Valid.");
		}
	}

	@Override
	public Response addLabelToNote(long token, long noteId, long labelId) {
		if(token!=0) {
			Optional<Label> islabelPresent = labelRepository.findById(labelId);
			if(islabelPresent.isPresent()) {
				Optional<Note> isNotePresent = notesRepository.findById(noteId);
				if(isNotePresent.isPresent()) {
					// Adding notes to label entity
					islabelPresent.get().setNoteId(noteId);
					islabelPresent.get().getNotes().add(isNotePresent.get());
					labelRepository.save(islabelPresent.get());
					
					// Adding labels to note entity
					isNotePresent.get().getLabellist().add(islabelPresent.get());
					notesRepository.save(isNotePresent.get());
					return new Response(200, "Label added to note", null);
				}
				else {
					log.error("Note not present");
					throw new NotesException(400,"Note not present.");
				}
			}
			else {
				log.error("Label not present");
				throw new NotesException(400,"Label not present.");
			}
		}
		else {
			log.error("User not valid");
			throw new NotesException(400,"User not Valid.");
		}
	}

	@Override
	public Response removeLabelFromNote(long token, long noteId, long labelId) {
		if(token!=0) {
			Optional<Label> islabelPresent = labelRepository.findById(labelId);
			if(islabelPresent.isPresent()) {
				Optional<Note> isNotePresent = notesRepository.findById(noteId);
				if(isNotePresent.isPresent()) {
					labelRepository.deleteByNoteAndLabelID(noteId, labelId);
					if(islabelPresent.get().getNotes().size()!=0) {
						islabelPresent.get().setNoteId(islabelPresent.get().getNotes().get(0).getId());
					}
					else {
						islabelPresent.get().setNoteId(0L);
					}
					labelRepository.save(islabelPresent.get());					
					isNotePresent.get().getLabellist().remove(islabelPresent.get());
					notesRepository.save(isNotePresent.get());
					return new Response(200, "Label removed from note", null);
				}
				else {
					log.error("Note not present");
					throw new NotesException(400,"Note not present.");
				}
			}
			else {
				log.error("Label not present");
				throw new NotesException(400,"Label not present.");
			}
		}
		else {
			log.error("User not valid");
			throw new NotesException(400,"User not Valid.");
		}
	}

}
