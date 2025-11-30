<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/boardviews/include/header.jsp"/>

<main id="main" class="main">

    <div class="pagetitle">
      <h1>자유게시판 수정</h1>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">

		  <form method="post" id="boardFrm" action="/board/modify" enctype="multipart/form-data">
		  	  <input type="hidden" name="page" value="${page}"/>
			  <input type="hidden" name="search_key" value="${search_key}"/>
			  <input type="hidden" name="pkid" value="${vo.pkid}"/>
			  
			  <div class="card">
				<div class="card-body">
					<h5 class="card-title">자유게시판 수정</h5>
					<div class="row mb-3">
					  <label for="title" class="col-sm-2 col-form-label">제목</label>
					  <div class="col-sm-10 input-group">
						<input type="text" name="title" id="title"  maxlength="100" class="form-control">
					  </div>
					</div>
					<div class="row mb-3">
					  <label for="content" class="col-sm-2 col-form-label">내용</label>
					  <div class="col-sm-10 input-group">
						<textarea name="content" id="content" class="form-control" style="height: 100px"></textarea>
					  </div>
					</div>
					<div class="row mb-3">
					  <label for="upload" class="col-sm-2 col-form-label">첨부 이미지</label>
					  <div class="col-sm-10 input-group">
					      <input type="file" name="upload" id="upload">
					      
					      <!-- 기존 이미지 표시 -->
					      <c:if test="${not empty vo.img_path}">
					          <div class="my-2">
					              <img src="/upload/${vo.img_path}" alt="기존 이미지" style="max-width:300px;">
					          </div>
					      </c:if>
					  </div>
					</div>

				</div>

				<div class="card-footer">
					<div class="d-flex justify-content-center">
						<a href="/board/list?page=${page}&search_key=${search_key}" class="btn btn-sm btn-outline-secondary ms-1">목록</a>
						<button type="submit" class="btn btn-sm btn-outline-primary ms-1">수정</a>
					</div>
				</div>
			  </div>
		  </form>

        </div>
      </div>
    </section>
  </main>
  
  <script>
	//Jquery Validation 작성
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