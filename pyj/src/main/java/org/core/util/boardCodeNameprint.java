package org.core.util;

import org.core.board.model.service.BoardService;
import org.core.board.model.vo.MasterBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component("boardCodeNameprint")

public class boardCodeNameprint {
	@Autowired
	@Qualifier("boardService")
	BoardService boardService;
	
	public String boardCodeNameprint(int boardCodeNum) {
		
		MasterBoard mb = new MasterBoard();
		mb.setBoardCodeNumber(boardCodeNum);
		String strMasterBoardName = boardService.MasterBoardName(mb);
		
		return strMasterBoardName;
		 
	}
	 
}
