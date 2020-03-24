<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	function fn_addFile() {

			var fileIndex = 1;

			$(".fileAdd_btn")
					.on(
							"click",
							function() {
								$("#fileIndex")
										.append(
												"<div><input type='file' id='fileName' onchange='checkFile(this)'  style='float:left;' name='file_"
														+ (fileIndex++)
														+ "'>"
														+ "</button>"
														+ "<button type='button' style='float:right;'name='fileDelButton' id='fileDelBtn'>"
														+ "삭제"
														+ "</button></div>");
							});
							
							
							
							
			$(document).on("click", "#fileDelBtn", function() {
				$(this).parent().remove();
			});
		}