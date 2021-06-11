package com.bridgelabz.fundoonotes.service;

import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.fundoonotes.dto.ColabDTO;
import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.entity.Collaborator;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.util.Response;

public interface IFundooNotesService {

	// To add new note
	Response addNewNote(@Valid NotesDTO dto);

	// To update note
	Response updateNote(long token, NotesDTO dto);

	// Add note to archive
	Response addNoteToArchive(long token);

	// Add note to pinned
	Response addNoteToPinned(long token);

	// Delete note and add to trash
	Response addNoteToTrash(long token);

	// Get all notes present in trash
	List<Note> getAllNotesInTrash(long token);

	// get all notes that are pinned
	List<Note> getAllNotesAddedToPin(long token);

	// Get notes
	List<Note> getNotes(long token);

	// To delete note
	Response deleteNote(long token);

	// To restore note from trash
	Response restoreNoteFromTrash(long token);

	// To add collaborator to notes
	Response addCollaboratorToNotes(Long token, ColabDTO colabDto);

	// To remove collaborator from notes
	Response removeColabFromNote(long token, long noteId, String colabEmail);

	// Get all colaborators
	List<Collaborator> getCollaborators(int token);

}
