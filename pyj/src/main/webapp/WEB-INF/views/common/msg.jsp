<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<script>

		$.alert({
		    title: 'C O R E M E T H O D',
		    content: '${msg}',
		    autoClose: 'ok|1000',
		    buttons : {
		    	ok : function() {
		    		location.href = "${url}";
		    	}
		    }
		});

		// enter 키 누르면 url로 바로 이동
		$(document).keydown(function(key) {
			if(key.keyCode == 13) {
				location.href="${url}";
			}
		});
	</script>
</body>
</html>