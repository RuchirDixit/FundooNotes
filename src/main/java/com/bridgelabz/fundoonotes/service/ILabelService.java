package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.LabelDTO;
import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.util.Response;

public interface ILabelService {

	// Add new label
	Response addNewLabel(long token,LabelDTO labelDTO);

	// Read labels
	List<Label> readLabels(long token);

	// To update existing label
	Response updateLabel(long token, LabelDTO dto);

	// Delete label
	Response deleteLabel(long token);

	// Add specified label to note
	Response addLabelToNote(long token, long noteId, long labelId);

}
