package com.bridgelabz.fundoonotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.entity.Collaborator;

public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM collaborator where collab_email=:emailId",nativeQuery = true)
	void deleteCollaborator(String emailId);
}
