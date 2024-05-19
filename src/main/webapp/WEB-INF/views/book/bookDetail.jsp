<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-10 mb-4">
            <div class="card shadow-lg">
                <div class="row g-0">
                    <div class="col-md-5">
                        <img src="${book.image}" class="img-fluid rounded-start" alt="${book.title}" style="height: 100%; object-fit: cover;">
                    </div>
                    <div class="col-md-7">
                        <div class="card-body">
                            <h4 class="card-title font-weight-bold text-primary">${book.title}</h4>
                            <p class="card-text text-secondary"><strong>저자:</strong> ${book.author} / <strong>출판사:</strong> ${book.publisher}</p>
                            <p class="card-text text-success font-weight-bold"><strong>가격:</strong> ${book.discount}원</p>
                            <p class="card-text"><small class="text-muted"><strong>출판일:</strong> ${book.pubdate}</small></p>
                            <p class="card-text"><small class="text-muted"><strong>ISBN:</strong> ${book.isbn}</small></p>
                            <p class="card-text mt-3">${book.description}</p>
                            <div class="form-group mt-3">
                                <label for="quantity" class="form-label"><strong>주문수량</strong></label>
                                <input type="number" class="form-control" id="quantity" value="1" min="1" max="100">
                            </div>
                            <div class="mt-3">
                                <button class="btn btn-outline-primary btn-lg mr-2"><i class="fas fa-shopping-cart"></i> 장바구니담기</button>
                                <button class="btn btn-primary btn-lg"><i class="fas fa-credit-card"></i> 주문하기</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
