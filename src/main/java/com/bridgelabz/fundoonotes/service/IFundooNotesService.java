package com.bridgelabz.fundoonotes.service;

import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.util.Response;

public interface IFundooNotesService {

	// To add new note
	Response addNewNote(@Valid NotesDTO dto);

	// To update note
	Response updateNote(String token, NotesDTO dto);

	// Add note to archive
	Response addNoteToArchive(String token);

	// Add note to pinned
	Response addNoteToPinned(String token);

	// Delete note and add to trash
	Response addNoteToTrash(String token);

	// Get all notes present in trash
	List<Note> getAllNotesInTrash(String token);

	// get all notes that are pinned
	List<Note> getAllNotesAddedToPin(String token);

	// Get notes
	List<Note> getNotes(String token);

	// To delete note
	Response deleteNote(String token);

	// To restore note from trash
	Response restoreNoteFromTrash(String token);

}
