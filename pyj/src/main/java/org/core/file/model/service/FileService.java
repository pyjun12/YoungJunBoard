package org.core.file.model.service;

import org.core.file.model.dao.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FileService {

	@Autowired
	@Qualifier("fileDao")
	FileDao fileDao;

}
