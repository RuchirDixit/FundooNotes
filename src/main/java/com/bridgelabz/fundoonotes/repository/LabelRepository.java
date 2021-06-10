package com.bridgelabz.fundoonotes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.entity.Label;

public interface LabelRepository extends JpaRepository<Label, Long> {

	Optional<Label> findByUserId(long token);
	
	Optional<Label> findByNoteId(long token);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM notes_labellist WHERE notes_note_id=:noteId AND labellist_id=:labelId",nativeQuery = true)
	void deleteByNoteAndLabelID(long noteId,long labelId);
}
