<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<div class="container mt-5">
    <div class="row">
        <c:forEach var="book" items="${books}" varStatus="status">
        <div class="col-md-3 mb-4">
            <div class="card h-100">
                <img src="${book.image}" class="card-img-top" alt="${book.title}" style="height: 200px; object-fit: cover;">
                <div class="card-body">
                    <h5 class="card-title"><a href="${book.link}">${book.title}</a></h5>
                    <p class="card-text">${book.author} / ${book.publisher}</p>
                    <p class="card-text"><small class="text-muted">출판일: ${book.pubdate}</small></p>
                    <p class="card-text"><small class="text-muted">ISBN: ${book.isbn}</small></p>
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
