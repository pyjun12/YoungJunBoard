package org.core.comment.controller;

import javax.servlet.http.HttpServletRequest;
import org.core.comment.model.service.CommentService;
import org.core.comment.model.vo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {

	@Autowired
	@Qualifier("commentService")
	CommentService commentService;

	// 댓글 작성
	@RequestMapping("/insertcommnet")
	public String insertcomment(HttpServletRequest request, Model model, Comment comment,int boardCodeNum) {
		
		Comment c = new Comment(); 
		int nCommentBoardNum         = Integer.parseInt(request.getParameter("commentBoardNum")); 
		String strCommentBoardUserId = request.getParameter("commentBoardUserId");
		String strCommentContent     = request.getParameter("commentContent");
		int nCurrentPage             = Integer.parseInt(request.getParameter("currentPage"));
		String strDatePicker1 = request.getParameter("datepicker1");
		String strDatePicker2 = request.getParameter("datepicker2");
		
		c.setCommentContent(strCommentContent);
		c.setCommentBoardUserId(strCommentBoardUserId);
		c.setCommentBoardNum(nCommentBoardNum);  						
		    						
		int nResult = commentService.insertcomment(c); // insert하는 비즈니스로직 수행
		    		  
		if ( nResult > 0 ) { // 비즈니스로직이 참이라면
		    model.addAttribute("c", c); // 아래 return 경로에 model값을 넘겨준다.
		    return "redirect:/boardClick.do?bno=" + nCommentBoardNum+"&boardCodeNum="+boardCodeNum+"&currentPage="+nCurrentPage+"&datepicker1="+strDatePicker1+"&datepicker2="+strDatePicker2
; // 게시글상세보기 클릭시화면+게시글번호
		    } else {
		    return "redirect:/";
		    }
		}
	}
