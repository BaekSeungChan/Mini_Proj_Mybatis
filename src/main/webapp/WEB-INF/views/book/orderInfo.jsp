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
        <button class="btn btn-primary" onclick="orderBtn()">결제하기</button>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>

<script>
    $(document).ready(function () {
        updateTotalPrice();

        const merchant_uid = "O" + new Date().getTime();
        const totalPrice = parseInt($("#totalPrice").text().replace(/,/g, '').replace('원', '').trim());

        fetch('/payment/prepare', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                merchant_uid: merchant_uid,
                amount: totalPrice
            })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log('결제 준비 완료:', data);
            })
            .catch(error => {
                console.error('결제 준비 중 오류 발생:', error);
            });
    });

    const orderBtn = () => {
        const username = document.getElementById('customerName').value;
        const bookName = Array.from(document.querySelectorAll('tbody tr')).map(row => row.querySelector('.title-cell').textContent).join(', ');
        const post = document.getElementById('shippingZip').value;
        const addr = document.getElementById('shippingAddress').value;
        const phone = document.getElementById('shippingPhone').value;
        const totalPrice = parseInt(document.getElementById('totalPrice').textContent.replace(/,/g, '').replace('원', '').trim());
        const merchant_uid = "0" + new Date().getTime();

        const IMP = window.IMP;
        IMP.init('imp37653765');

        IMP.request_pay({
            pg: "html5_inicis",
            pay_method: "card",
            merchant_uid: merchant_uid,
            name: bookName,
            amount: totalPrice,
            buyer_name: username,
            buyer_tel: phone,
            buyer_addr: addr,
            buyer_postcode: post
        }, function (rsp) {
            if (rsp.success) {
                $.ajax({
                    url: "payment/validate",
                    method: "POST",
                    contentType: "application/json",
                    data: JSON.stringify({
                        imp_uid: rsp.imp_uid,
                        merchant_uid: rsp.merchant_uid,
                    }),
                }).done(function (data){
                    handlePaymentSuccess(rsp)
                })
            } else {
                alert('결제를 실패하였습니다.');
            }
        });
    }

    const handlePaymentSuccess = (rsp) => {
        // console.log("1 : ", rsp.merchant_uid)
        // console.log("2 : ", rsp.buyer_name)
        // console.log("3 : ", rsp.pay_method)
        // console.log("4 : ", rsp.name)
        // console.log("5 : ", rsp.paid_amount)
        // console.log("6 : ", rsp.buyer_tel)
        // console.log("7 : ", rsp.buyer_addr)
        // console.log("8 : ", rsp.buyer_postcode)

        // const buyerInfo = {
        //     "merchant_uid": rsp.merchant_uid,
        //     "userid": rsp.buyer_name,
        //     "pay_method": rsp.pay_method,
        //     "name": rsp.name,
        //     "amount": rsp.paid_amount,
        //     "phone": rsp.buyer_tel,
        //     "addr": rsp.buyer_addr,
        //     "post": rsp.buyer_postcode
        // };
        //
        // $.ajax({
        //     type: "post",
        //     url: "save_buyerInfo",
        //     contentType: "application/json",
        //     data: JSON.stringify(buyerInfo),
        //     success: function (response) {
        //         console.log("결제정보 저장 완료");
        //     }
        // });

        const orderInfo = {
            "merchant_uid": rsp.merchant_uid,
            "userid": rsp.buyer_name,
            "book_title": rsp.name,
            "book_price": rsp.paid_amount,
            "quantity": 1,
            "recipient": rsp.buyer_name,
            "post": rsp.buyer_postcode,
            "addr": rsp.buyer_addr,
            "phone": rsp.buyer_tel
        };

        console.log("merchant_uid : ", rsp.merchant_uid)
        console.log("userid : ", rsp.buyer_name)
        console.log("book_title : ", rsp.name)
        console.log("book_price : ", rsp.paid_amount)
        console.log("recipient : ", rsp.buyer_name)
        console.log("post : ", rsp.buyer_postcode)
        console.log("addr : ", rsp.buyer_addr)
        console.log("phone : ", rsp.buyer_tel)


        $.ajax({
            type: "post",
            url: "save_orderInfo",
            contentType: "application/json",
            data: JSON.stringify(orderInfo),
            success: function (response) {
                console.log("주문완료");
                Swal.fire({
                    text: '결제가 완료되었습니다.',
                    icon: 'success',
                    confirmButtonColor: '#3085d6',
                    button: {
                        text: '확인',
                        closeModal: true
                    }
                }).then(() => {
                    window.location.href = 'orderDone?merchant_uid=' + response;
                });
            }
        });
    }

    const updateTotalPrice = () => {
        let totalPrice = 0;
        document.querySelectorAll('tbody tr').forEach(row => {
            const total = parseInt(row.cells[3].textContent.replace(/,/g, '').replace('원', '').trim());
            totalPrice += total;
        });
        document.getElementById('totalPrice').textContent = totalPrice.toLocaleString() + '원';
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
