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
	Response updateNote(int token, NotesDTO dto);

	// Add note to archive
	Response addNoteToArchive(int token);

	// Add note to pinned
	Response addNoteToPinned(int token);

	// Delete note and add to trash
	Response addNoteToTrash(int token);

	// Get all notes present in trash
	List<Note> getAllNotesInTrash(int token);

	// get all notes that are pinned
	List<Note> getAllNotesAddedToPin(int token);

	// Get notes
	List<Note> getNotes(int token);

	// To delete note
	Response deleteNote(int token);

	// To restore note from trash
	Response restoreNoteFromTrash(int token);

}
