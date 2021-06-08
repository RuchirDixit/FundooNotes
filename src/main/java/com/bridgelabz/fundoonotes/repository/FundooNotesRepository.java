package com.bridgelabz.fundoonotes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundoonotes.entity.Note;

public interface FundooNotesRepository extends JpaRepository<Note, Long> {

	Optional<Note> findByEmailId(String emailId);
}
