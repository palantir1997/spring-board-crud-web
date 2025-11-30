<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/boardviews/include/header.jsp"/>

<main id="main" class="main">

    <div class="pagetitle">
      <h1>자유게시판 등록</h1>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">

		  <form method="post" id="boardFrm" action="/board/register" enctype="multipart/form-data">
			  <div class="card">
				<div class="card-body">
					<h5 class="card-title">자유게시판 등록</h5>
					<div class="row mb-3">
					  <label for="title" class="col-sm-2 col-form-label">제목</label>
					  <div class="col-sm-10 input-group">
						<input type="text" name="title" id="title" maxlength="100" class="form-control">
					  </div>
					</div>
					<div class="row mb-3">
					  <label for="content" class="col-sm-2 col-form-label">내용</label>
					  <div class="col-sm-10 input-group">
						<textarea name="content" id="content" class="form-control" style="height: 100px"></textarea>
					  </div>

					  <div class="row mb-3">
						<input type="file" name="upload">
										  <c:if test="${not empty vo.img_path}">
										      <img src="/upload/${vo.img_path}" alt="첨부 이미지" style="max-width:300px;">
										  </c:if>
						</div>
					
					</div>
				</div>

				<div class="card-footer">
					<div class="d-flex justify-content-center">
						<a href="/board/list" class="btn btn-sm btn-outline-secondary ms-1">목록</a>
						<button type="submit" href="register.html" class="btn btn-sm btn-outline-primary ms-1">등록</a>
					</div>
				</div>
			  </div>
		  </form>

        </div>
      </div>
    </section>
  </main>
  
  <script>
	//..생략.. Jquery validation 작성
	$(function (){
	  	$('#boardFrm').validate({
	  	  	submitHandler: function (form) {
	            form.submit();
	        },
	        rules: {
	            title: {
	            	required: true,
	            	maxlength: 100
	            },
	            content: {
	            	required: true
	            }
	        },
	        messages: {
	            title: {
	                required: "제목을 입력하세요.",
	                maxlength: "제목은 100자 이하로 입력하세요."
	            },
	            content: {
	                required: "내용을 입력하세요."
	            }
	        },
	        errorElement: 'span',
	        errorPlacement: function (error, element) {
	            error.addClass('invalid-feedback');
	            element.closest('.input-group').append(error);
	        },
	        highlight: function (element, errorClass, validClass) {
	            $(element).addClass('is-invalid');
	        },
	        unhighlight: function (element, errorClass, validClass) {
	            $(element).removeClass('is-invalid');
	        }
	  	});
	});
</script>
  
  <jsp:include page="/boardviews/include/footer.jsp"/>