<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/boardviews/include/header.jsp"/>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>회원관리</h1>
	</div>
	<!-- End Page Title -->

	<section class="section">
		<div class="row">
			<div class="col-lg-12">

				<form method="post" action="/member/register" id="memberFrm">
					<input type="hidden" name="page" value="${page}"/>
					<input type="hidden" name="search_key" value="${search_key}"/>
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">회원등록</h5>
							<div class="row mb-3">
								<label for="user_id" class="col-sm-2 col-form-label">아이디</label>
								<div class="col-sm-10 input-group">
									<input type="text" name="user_id" id="user_id" class="form-control">
								</div>
							</div>
							<div class="row mb-3">
								<label for="user_pw" class="col-sm-2 col-form-label">Password</label>
								<div class="col-sm-10 input-group">
									<input type="password" name="user_pw" id="user_pw" class="form-control">
								</div>
							</div>
							<div class="row mb-3">
								<label for="user_pw1" class="col-sm-2 col-form-label">Password 확인</label>
								<div class="col-sm-10 input-group">
									<input type="password" name="user_pw1" id="user_pw1" class="form-control">
								</div>
							</div>
							<div class="row mb-3">
								<label for="user_name" class="col-sm-2 col-form-label">이름</label>
								<div class="col-sm-10 input-group">
									<input type="text" name="user_name" id="user_name" class="form-control">
								</div>
							</div>
						</div>

						<div class="card-footer">
							<div class="d-flex justify-content-center">
								<a href="/member/list?page=${page}&search_key=${search_key}"
									class="btn btn-sm btn-outline-secondary ms-1">목록</a>
								<button type="submit"
									class="btn btn-sm btn-outline-primary ms-1">등록</button>
							</div>
						</div>
					</div>
				</form>

			</div>
		</div>
	</section>
</main>

<script>
	$(function (){
	  	$('#memberFrm').validate({
	  	  	submitHandler: function (f) {
	            f.submit();
	        },
	        rules: {
	        	user_id: {
	                required: true
	                
	            },
	            user_pw: {
	            	required: true,
	            },
	            user_pw1: {
	            	required: true,
	            	equalTo: '#user_pw'
	            },
	            user_name:{
	            	required: true,
	            }
	            
	        },
	        messages: {
	        	user_id: {
	                required: "아이디를 입력하세요.",
	                remote: "중복된 아이디입니다."
	            },
	            user_pw: {
	                required: "Password를 입력하세요.",
	            },
	            user_pw1: {
	                required: "Password확인를 입력하세요.",
	                equalTo: "입력하신 비밀번호가 서로 틀립니다."
	            },
	            user_name: {
	                required: "이름을 입력하세요.",
	            },
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