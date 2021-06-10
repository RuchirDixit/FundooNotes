package com.bridgelabz.fundoonotes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.fundoonotes.entity.Label;

public interface LabelRepository extends JpaRepository<Label, Long> {

	Optional<Label> findByUserId(long token);
}
