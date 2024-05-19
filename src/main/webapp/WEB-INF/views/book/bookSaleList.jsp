<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<div class="container mt-5">
    <h1 class="text-center">금융 도서관</h1>
    <div class="row mt-5">
        <c:forEach var="book" items="${books}" varStatus="status">
        <div class="col-md-3 mb-4">
            <div class="card h-100 d-flex flex-column">
                <img src="${book.image}" class="card-img-top" alt="${book.title}" style="height: 200px; object-fit: cover;">
                <div class="card-body flex-grow-1">
                    <h6 class="card-title">${book.title}</h6>
                    <p class="card-text">${book.author} / ${book.publisher}</p>
                    <p class="card-text"><small class="text-muted">출판일: ${book.pubdate}</small></p>
                    <p class="card-text"><small class="text-muted">ISBN: ${book.isbn}</small></p>
                </div>
                <div class="card-footer bg-transparent border-top-0">
                    <a href="/book/detail?title=${book.title}" class="btn btn-primary w-100">상세보기</a>
                </div>
            </div>
        </div>
        <c:if test="${status.index % 4 == 3}">
    </div><div class="row">
    </c:if>
    </c:forEach>
</div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
