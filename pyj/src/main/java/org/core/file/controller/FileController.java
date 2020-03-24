package org.core.file.controller;

import org.core.file.model.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class FileController {

	@Autowired
	@Qualifier("fileService")
	FileService fileService;

}
