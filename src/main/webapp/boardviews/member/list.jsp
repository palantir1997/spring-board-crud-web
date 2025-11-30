<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/boardviews/include/header.jsp"/>

<main id="main" class="main">

    <div class="pagetitle">
      <h1>회원관리</h1>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
			<h5 class="card-header d-flex justify-content-between align-items-center">
				<form method="get" action="/member/list">
					<div class="input-group w-auto">
						<input type="text" name="search_key" value="${search_key}" class="form-control" placeholder="검색어를 입력하세요."/>
						<button class="btn btn-sm btn-outline-secondary" type="submit">검색</button>
					</div>
				</form>
				<div>
					<a href="/member/register?page=${page}&search_key=${search_key}" class="btn btn-sm btn-outline-primary">회원등록</a>
				</div>
			</h5>
            <div class="card-body">
              <table class="table">
                <thead>
                  <tr class="text-center">
                    <th scope="col" class="col-1">번호</th>
                    <th scope="col" class="col-2">아이디</th>
                    <th scope="col" class="col-2">이름</th>
                    <th scope="col" class="col-1">등록일</th>
                  </tr>
                </thead>
                <tbody>
                <c:if test="${not empty list}">
                	<c:forEach var="list" items="${list}">
	                	<tr class="text-center">
	                    	<th scope="row">${list.pkid}</th>
		                    <td><a href="/member/view?pkid=${list.pkid}&page=${page}&search_key=${search_key}">${list.user_id}</a></td>
		                    <td>${list.name}</td>
		                    <td>${list.regdate}</td>
	                  	</tr>
                	</c:forEach>
                </c:if>
                <c:if test="${empty list}">
                	<tr class="text-center">
	                    <th colspan="4">데이타가 없습니다.</th>
	                  </tr>
                </c:if>
                </tbody>
              </table>
            </div>

			<div class="card-footer">
				<jsp:include page="/boardviews/common/pagging.jsp" />
			</div>
          </div>

        </div>
      </div>
    </section>

  </main>

  <jsp:include page="/boardviews/include/footer.jsp"/>