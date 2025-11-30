<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/boardviews/include/header.jsp"/>
  <main id="main" class="main">

    <div class="pagetitle">
      <h1>${name}님 어서오세요. ${page}입니다. </h1>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title"> ${name}님 오늘 하루도 행복하게 보내세요. 한앙여자대학교 자유게시판에 오신것을 환영합니다.</h5>
            </div>
          </div>

        </div>
      </div>
    </section>

  </main>

<jsp:include page="/boardviews/include/footer.jsp"/>
