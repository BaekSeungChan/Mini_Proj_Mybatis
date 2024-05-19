<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<c:set var="username" value="${sessionScope.username}" />

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
                                <button class="btn btn-outline-primary btn-lg mr-2" onclick="cartSave(event)"><i class="fas fa-shopping-cart"></i> 장바구니담기</button>
                                <button class="btn btn-primary btn-lg"><i class="fas fa-credit-card"></i> 주문하기</button>
                            </div>
                            <div id="responseMessage" class="mt-3"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 모달 시작 -->
<div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="successModalLabel">장바구니에 담았습니다!</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>상품이 장바구니에 담겼습니다. 계속 쇼핑하시겠습니까 아니면 지금 구매하시겠습니까?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">계속 쇼핑하기</button>
                <button type="button" class="btn btn-primary" onclick="redirectToCart()">장바구니로 이동</button>
                <button type="button" class="btn btn-primary" onclick="redirectToCheckout()">구매하기</button>
            </div>
        </div>
    </div>
</div>
<!-- 모달 끝 -->

<script>
    const cartSave = async (event) => {
        event.preventDefault();

        const quantity = document.getElementById("quantity").value;

        const param = {
            username: "${user}",
            title: "${book.title}",
            link: "${book.link}",
            image: "${book.image}",
            author: "${book.author}",
            discount: "${book.discount}",
            publisher: "${book.publisher}",
            pubdate: "${book.pubdate}",
            isbn: "${book.isbn}",
            quantity: quantity
        };

        try {
            const response = await fetch("/book/cart/save", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(param)
            });

            const data = await response.json();
            if (data.message === "Success") {
                const successModal = new bootstrap.Modal(document.getElementById('successModal'));
                successModal.show();
            } else {
                document.getElementById('responseMessage').innerText = '장바구니에 담기 실패했습니다.';
            }
        } catch (error) {
            document.getElementById('responseMessage').innerText = '장바구니에 담기 실패했습니다.';
            console.error('Error:', error);
        }
    }

    const redirectToCart = () => {
        window.location.href = "/book/cart/list";
    }

    const redirectToCheckout = () => {
        window.location.href = "/checkout";
    }
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
