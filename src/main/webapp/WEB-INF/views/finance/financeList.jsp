<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<div class="empty-space"></div>

<div class="container-fluid">
    <div class="container text-center">
        <div class="row align-items-start">
            <div class="col">
                <div class="financeTest" style="height: 500px; width: 620px; overflow-y: auto;"></div> <!-- 데이터를 표시할 div 요소 -->
            </div>
            <div class="col">
                One of three columns
            </div>
            <div class="col">
                One of three columns
            </div>
        </div>
    </div>
</div>

<script>
    window.onload = function() {
        finance();
    };

    const finance = async () => {
        try {
            const response = await fetch("/volume-rank", {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            const data = await response.json();
            console.log("data ", data);

            // 데이터를 표시할 HTML 요소 선택
            const column = document.querySelector('.financeTest');

            // 테이블 생성
            const table = document.createElement("table");
            table.classList.add("table");

            // 테이블 헤더 생성
            const thead = document.createElement("thead");
            const headerRow = document.createElement("tr");
            const headers = ["주식 이름", "현재 주가", "거래량 증가율", "평균 거래 가격", "평균 거래량"];
            headers.forEach(headerText => {
                const th = document.createElement("th");
                th.textContent = headerText;
                headerRow.appendChild(th);
            });
            thead.appendChild(headerRow);
            table.appendChild(thead);

            // 테이블 바디 생성
            const tbody = document.createElement("tbody");
            data.forEach(item => {
                const row = document.createElement("tr");
                const values = [item.htsKorIsnm, numberWithCommas(item.stckPrpr), numberWithCommas(item.volInrt), numberWithCommas(item.avrgTrPbmn), numberWithCommas(item.avrgVol)];
                values.forEach(valueText => {
                    const td = document.createElement("td");
                    td.textContent = valueText;
                    row.appendChild(td);
                });
                tbody.appendChild(row);
            });
            table.appendChild(tbody);

            // 테이블을 financeTest에 추가
            column.appendChild(table);

        } catch (error) {
            console.error("error " + error);
        }
    };

    // 숫자를 쉼표로 포맷하는 함수
    function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
