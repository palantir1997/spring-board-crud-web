<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림창</title>
</head>
<body>
<script>
	let msg = "${msg}";
    let url = "${url}";

    if(msg != '') {
    	alert(msg);
    }

    if(url != '') {
    	location.href = url;
    }
</script>
</body>
</html>