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
				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label class="col-form-label">아이디</label>
									<div class="form-control form-data">${vo.user_id}</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label class="col-form-label">이름</label>
									<div class="form-control form-data">${vo.name}</div>
								</div>
							</div>
						</div>
					</div>
					<div class="card-footer">
					    <div class="d-flex justify-content-center">

					        <!-- 목록 버튼은 항상 표시 -->
					        <a href="/member/list?page=${page}&search_key=${search_key}" 
					           class="btn btn-sm btn-outline-secondary ms-1">목록</a>     

					        <!-- 로그인한 회원이 자기 정보일 때만 수정/삭제 표시 -->
					        <c:if test="${sessionScope.MEMBER_PKID == vo.pkid}">
					            <a href="/member/modify?pkid=${vo.pkid}&page=${page}&search_key=${search_key}" 
					               class="btn btn-sm btn-outline-primary ms-1">수정</a>

					            <a href="javascript:Delete();" 
					               class="btn btn-sm btn-outline-danger ms-1">삭제</a>
					        </c:if>

					    </div>
					</div>

				</div>

			</div>
		</div>
	</section>

</main>

<script>
	function Delete() {
		if(confirm("정말 삭제하시겠습니까?")) {
			location.href = "/member/delete?pkid=${vo.pkid}&page=${page}&search_key=${search_key}";
		}
	}
</script>
  <jsp:include page="/boardviews/include/footer.jsp"/>