<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${pagging.totalPage > 0 and pagging.totalPage >= pagging.requestPage}">
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">							
			<c:if test="${pagging.prev}">
				<li class="page-item"><a class="page-link"
					href="${pagging.url}?page=${pagging.startPage - 1}${pagging.paramStr}"
					aria-label="Previous"> <span aria-hidden="true">«</span>
				</a></li>
			</c:if>
			<c:if test="${!pagging.prev}">
				<li class="page-item"><a class="page-link" href="#"
					aria-label="Previous"> <span aria-hidden="true">«</span>
				</a></li>
			</c:if> 

			<c:forEach var="i" begin="${pagging.startPage}"
				end="${pagging.endPage}" step="1">
				<c:if test="${pagging.requestPage == i}">
					<li class="page-item"><a class="page-link active"
						href="${pagging.url}?page=${i}${pagging.paramStr}">${i}</a></li>
				</c:if>
				<c:if test="${pagging.requestPage != i}">
					<li class="page-item"><a class="page-link"
						href="${pagging.url}?page=${i}${pagging.paramStr}">${i}</a></li>
				</c:if>

			</c:forEach>

		 	<c:if test="${pagging.next}">
				<li class="page-item"><a class="page-link"
					href="${pagging.url}?page=${pagging.endPage + 1}${pagging.paramStr}" aria-label="Next">
						<span aria-hidden="true">»</span>
				</a></li>
			</c:if>			
			<c:if test="${!pagging.next}">
				<li class="page-item"><a class="page-link" href="#"
					aria-label="Next"> <span aria-hidden="true">»</span></a></li>
			</c:if>			 
		</ul>
	</nav>
</c:if>