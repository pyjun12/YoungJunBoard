<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	$(document).ready(function() {
	
		var link="?boardCodeNum=${param.boardCodeNum}&currentPage=${param.currentPage}&bno=${param.bno}&datepicker1=${param.datepicker1}&datepicker2=${param.datepicker2}";
			var formObj = $("form[name='updateForm']");

			$(".update_btn").on("click", function() {
		 		formObj.attr("action", "/updateinsertFile.do"+link); 
				formObj.attr("method", "post");
				formObj.submit();
			});

			fn_addFile();

			$("#fileDel1").on("click", function() {

				formObj.attr("action", "/deleteFile.do"+link);
				formObj.attr("method", "post");
				formObj.submit();
			});

			$("#fileDel2").on("click", function() {

				formObj.attr("action", "/deleteFile.do"+link);
				formObj.attr("method", "post");
				formObj.submit();
			});

			$("#fileDel3").on("click", function() {

				formObj.attr("action", "/deleteFile.do"+link);
				formObj.attr("method", "post");
				formObj.submit();
			});

			$("#fileDel4").on("click", function() {

				formObj.attr("action", "/deleteFile.do"+link);
				formObj.attr("method", "post");
				formObj.submit();
			});

			$("#fileDel5").on("click", function() {

				formObj.attr("action", "/deleteFile.do"+link);
				formObj.attr("method", "post");
				formObj.submit();
			});

			$("#fileDel6").on("click", function() {

				formObj.attr("action", "/deleteFile.do"+link);
				formObj.attr("method", "post");
				formObj.submit();
			});
		})