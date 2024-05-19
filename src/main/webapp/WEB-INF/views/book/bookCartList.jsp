<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<style>
    .table td, .table th {
        vertical-align: middle;
    }
    .title-cell {
        max-width: 200px; /* Adjust the width as needed */
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    .container {
        margin-top: 50px;
    }
    .table-responsive {
        margin-top: 30px;
    }
    .text-right {
        text-align: right;
    }
    .total-price {
        font-size: 1.5rem;
        font-weight: bold;
    }
</style>

<div class="container">
    <h1 class="text-center">도서 바구니</h1>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">
                    <input type="checkbox" id="selectAll" onclick="toggleSelectAll(this)">
                </th>
                <th scope="col">제목</th>
                <th scope="col">저자 / 출판사</th>
                <th scope="col">ISBN</th>
                <th scope="col">가격</th>
                <th scope="col">수량</th>
                <th scope="col">총 가격</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books}" varStatus="status">
                <tr>
                    <td>
                        <input type="checkbox" class="select-item" data-id="${book.id}" onclick="updateTotalPrice()">
                    </td>
                    <td class="title-cell">${book.title}</td>
                    <td>${book.author} / ${book.publisher}</td>
                    <td>${book.isbn}</td>
                    <td>${book.discount}원</td>
                    <td>${book.quantity}</td>
                    <td>${book.discount * book.quantity}원</td>
                    <td>
                        <a href="/book/cart/remove?id=${book.id}"><button class="btn btn-primary">삭제</button></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="text-right mt-3">
        <h4 class="total-price">총 가격: <span id="totalPrice">0원</span></h4>
        <button class="btn btn-primary" onclick="proceedToCheckout()">결제하기</button>
    </div>
</div>

<script>
    const updateTotalPrice = () => {
        let totalPrice = 0;
        document.querySelectorAll('.select-item:checked').forEach(checkbox => {
            const row = checkbox.closest('tr');
            const total = parseInt(row.cells[6].textContent.replace('원', ''));
            totalPrice += total;
        });
        document.getElementById('totalPrice').textContent = totalPrice + '원';
    }

    <%--const removeFromCart = (id) => {--%>
    <%--    --%>
    <%--    fetch(`/book/cart/remove?id=${id}`, {--%>
    <%--        method: 'GET'--%>
    <%--    })--%>
    <%--        .then(response => response.json())--%>
    <%--        .then(data => {--%>
    <%--            if (data.message === 'Success') {--%>
    <%--                location.reload();--%>
    <%--            } else {--%>
    <%--                alert('장바구니에서 제거하는 데 실패했습니다.');--%>
    <%--            }--%>
    <%--        })--%>
    <%--        .catch(error => console.error('Error:', error));--%>
    <%--}--%>

    const proceedToCheckout = () => {
        window.location.href = "/checkout";
    }

    const toggleSelectAll = (selectAllCheckbox) => {
        const checkboxes = document.querySelectorAll('.select-item');
        checkboxes.forEach(checkbox => {
            checkbox.checked = selectAllCheckbox.checked;
        });
        updateTotalPrice();
    }

    document.addEventListener('DOMContentLoaded', updateTotalPrice);
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
