<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.File" %>


<jsp:include page="/boardviews/include/header.jsp"/>

<main id="main" class="main">

    <div class="pagetitle">
      <h1>자유게시판</h1>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <h5 class="card-header d-flex justify-content-between">
                <div>조회수: ${vo.viewcount}</div>
                <div>${vo.regdate}</div>
            </h5>

            <div class="card-body">
                <h5 class="card-title">${vo.title}</h5>
                <p>${vo.content}</p>

                <c:if test="${not empty vo.img_path}">
                    <div class="text-center my-3">
                        <img src="/upload/${vo.img_path}" 
                             alt="첨부 이미지" 
                             class="img-fluid rounded" 
                             style="max-width: 400px;">
                    </div>
                </c:if>
				
            </div>

            <div class="card-footer">
                <div class="d-flex justify-content-center">
                    <a href="/board/list?page=${page}&search_key=${search_key}" 
                       class="btn btn-sm btn-outline-secondary ms-1">목록</a>

                    <c:if test="${vo.fkmember eq sessionScope.MEMBER_PKID}">
                        <a href="/board/modify?pkid=${vo.pkid}&page=${page}&search_key=${search_key}" 
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
        location.href = "/board/delete?pkid=${vo.pkid}&page=${page}&search_key=${search_key}";
    }
}
</script>

<jsp:include page="/boardviews/include/footer.jsp"/>
