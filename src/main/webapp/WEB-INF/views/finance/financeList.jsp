<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<style>
    .titleDesign {
        font-size: 24px; /* 폰트 크기 */
        font-weight: bold; /* 폰트 굵기 */
        text-align: center; /* 텍스트 가운데 정렬 */
        margin-bottom: 10px; /* 아래쪽 여백 */
        color: #333; /* 글자 색상 */
    }
</style>


<div class="empty-space"></div>

<div class="container-fluid">
    <div class="container text-center">
        <div class="row align-items-start">
            <div class="col mr-3">
                <div class="titleDesign">주식 거래량 정보</div>
                <div class="financeTest" style="height: 500px; width: 620px; overflow-y: auto;"></div> <!-- 데이터를 표시할 div 요소 -->
            </div>
            <div class="col mr-3">
                <div class="titleDesign">실시간 뉴스</div>
                <div class="newDiv" style="height: 500px; margin-left:10px; widthwidth:400px;overflow-y: auto; padding-right: 15px"></div>
            </div>
            <div class="col">
                <div class="titleDesign">AI 질문</div>
                <form action="/gpt/chat">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="Prompt 입력" name="prompt">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="submit">전송</button>
                        </div>
                    </div>
                </form>
                <div class="gptView">
                    ${gptResponse}
                </div>
            </div>

        </div>
    </div>
</div>

<div class="newDiv"></div>

<script>
    window.onload = function () {
        news();
        finance();
    }

    const news = async () => {
        try {
            const response = await fetch("https://newsapi.org/v2/top-headlines?country=kr&category=business&apiKey=677982bdcd6549329d32edfcb90766bd", {
                method: 'GET',
            });
            const data = await response.json();

            console.log("news Data" , data)

            // 데이터를 표시할 HTML 요소 선택
            const container = document.querySelector('.newDiv');

            // 각 뉴스 항목에 대해 제목과 URL을 표시
            data.articles.forEach(article => {
                const title = document.createElement("div");
                title.textContent = article.title;
                container.appendChild(title);

                const url = document.createElement("a");
                url.href = article.url;
                url.textContent = "원문 보기";
                url.target = "_blank"; // 새 창에서 열도록 설정
                container.appendChild(url);

                container.appendChild(document.createElement("br")); // 줄바꿈 추가
            });

        } catch (error) {
            console.error("error " + error);
        }
    };
</script>



<script>


    const finance = async () => {
        try {
            const response = await fetch("/volume-rank", {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            const data = await response.json();

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
