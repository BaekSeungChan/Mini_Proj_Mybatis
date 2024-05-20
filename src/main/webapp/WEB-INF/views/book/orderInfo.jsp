<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<style>
    .table td, .table th {
        vertical-align: middle;
    }
    .title-cell {
        max-width: 150px; /* Adjust the width as needed */
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
    .customer-info, .shipping-info {
        margin-top: 30px;
    }
    .customer-info label, .shipping-info label {
        margin-top: 10px;
    }
</style>

<div class="container">
    <h1 class="text-center">주문 정보</h1>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col" class="title-cell">제목</th>
                <th scope="col">가격</th>
                <th scope="col">수량</th>
                <th scope="col">총 가격</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}" varStatus="status">
                <tr>
                    <td class="title-cell">${order.title}</td>
                    <td>${order.discount}원</td>
                    <td>${order.quantity}</td>
                    <td>${order.discount * order.quantity}원</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="customer-info">
        <h4>고객 정보</h4>
        <div class="form-group">
            <label>이름</label>
            <input type="text" class="form-control" id="customerName">
        </div>
        <div class="form-group">
            <label>우편번호</label>
            <input type="text" class="form-control" id="customerZip">
        </div>
        <div class="form-group">
            <label>주소</label>
            <input type="text" class="form-control" id="customerAddress">
        </div>
        <div class="form-group">
            <label>전화번호</label>
            <input type="text" class="form-control" id="customerPhone">
        </div>
    </div>
    <div class="form-check mt-3">
        <input type="checkbox" class="form-check-input" id="sameAddress" onclick="copyCustomerInfo()">
        <label class="form-check-label">배송지가 동일할 경우 선택하세요.</label>
    </div>
    <div class="shipping-info">
        <h4>배송지 정보</h4>
        <div class="form-group">
            <label>이름</label>
            <input type="text" class="form-control" id="shippingName">
        </div>
        <div class="form-group">
            <label>우편번호</label>
            <input type="text" class="form-control" id="shippingZip">
        </div>
        <div class="form-group">
            <label>주소</label>
            <input type="text" class="form-control" id="shippingAddress">
        </div>
        <div class="form-group">
            <label>전화번호</label>
            <input type="text" class="form-control" id="shippingPhone">
        </div>
    </div>
    <div class="text-right mt-3">
        <h4 class="total-price">총 결제 금액: <span id="totalPrice">0원</span></h4>
        <button class="btn btn-primary">결제하기</button>
    </div>
</div>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>


<script>

</script>

<%--<script>--%>
<%--    document.addEventListener('DOMContentLoaded', function() {--%>
<%--        var merchant_uid = "O" + new Date().getTime();--%>
<%--        var totalPrice = document.getElementById('totalPrice').textContent.replace('원', '').trim();--%>

<%--        fetch('/payment/prepare', {--%>
<%--            method: 'POST',--%>
<%--            headers: {--%>
<%--                'Content-Type': 'application/json'--%>
<%--            },--%>
<%--            body: JSON.stringify({--%>
<%--                merchant_uid: merchant_uid, // 가맹점 주문번호--%>
<%--                amount: totalPrice // 결제 예정금액--%>
<%--            })--%>
<%--        })--%>
<%--            .then(response => response.json())--%>
<%--            .then(data => {--%>
<%--                console.log('결제 준비 완료:', data);--%>
<%--            })--%>
<%--            .catch(error => {--%>
<%--                console.error('결제 준비 중 오류 발생:', error);--%>
<%--            });--%>
<%--    })--%>
<%--</script>--%>
<script>
    const orderBtn = () => {
        var username = document.getElementById('customerName');
        var bookName = ${book.title}
        var post = document.getElementById('shippingZip');
        var addr = document.getElementById('shippingAddress');
        var phone = document.getElementById('shippingPhone');
        var merchant_uid = "0" + new Date().getTime(); // 고유한 주문번호 생성

        var IMP = window.IMP;
        IMP.init('imp37653765'); // 가맹점 식별코드 입력

        IMP.request_pay({
            pg: "html5_inicis",           // 등록된 pg사 (적용된 pg사는 KG이니시스)
            pay_method: "card",           // 결제방식: card(신용카드), trans(실시간계좌이체), vbank(가상계좌), phone(소액결제)
            merchant_uid: merchant_uid,   // 주문번호
            name: ${order.title},                  // 상품명
            amount: totalPrice,           // 금액
            buyer_name: username,         // 주문자
            buyer_tel: phone,             // 전화번호 (필수입력)
            buyer_addr: addr,    		  // 주소
            buyer_postcode: post          // 우편번호

            }, function (rsp) {
                if (rsp.success) {
                    var mesg = '결제가 완료되었습니다.';

                    // 겅증 후 결제 정보 & 주문 정보 DB 저장

                } else {
                    var mesg = '결제를 실패하였습니다.';
                    alert(msg);
                }
            }
        );


    }

</script>

<script>
    const updateTotalPrice = () => {
        let totalPrice = 0;
        document.querySelectorAll('tbody tr').forEach(row => {
            const total = parseInt(row.cells[3].textContent.replace('원', ''));
            totalPrice += total;
        });
        document.getElementById('totalPrice').textContent = totalPrice + '원';
    }

    const copyCustomerInfo = () => {
        if (document.getElementById('sameAddress').checked) {
            document.getElementById('shippingName').value = document.getElementById('customerName').value;
            document.getElementById('shippingZip').value = document.getElementById('customerZip').value;
            document.getElementById('shippingAddress').value = document.getElementById('customerAddress').value;
            document.getElementById('shippingPhone').value = document.getElementById('customerPhone').value;
        } else {
            document.getElementById('shippingName').value = '';
            document.getElementById('shippingZip').value = '';
            document.getElementById('shippingAddress').value = '';
            document.getElementById('shippingPhone').value = '';
        }
    }

    document.addEventListener('DOMContentLoaded', updateTotalPrice);
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
