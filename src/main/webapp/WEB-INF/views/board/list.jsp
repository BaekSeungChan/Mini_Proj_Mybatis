<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/lightpick@1.6.2/css/lightpick.css">


<%--   제목    --%>
<div class="row mt-4">
    <div class="col-md-10 offset-md-1">
        <h1>자유 게시판</h1>
        <p>
            타인에 대한 비방 또는 모욕은 사전 예고 없이 삭제됩니다.
        </p>
    </div>
</div>


<%-- 복합 검색을 위한 아코디언 검색창   --%>
<div class="row mt-4">
    <div class="col-md-10 offset-md-1">
        <div class="card border-dark">
            <div class="card-header">
                <a class="btn" data-bs-toggle="collapse" href="#collapse-body">검색 도구 상자</a>
            </div>
            <div class="collapse" id="collapse-body">
                <div class="card-body">
                    <form method="get" class="search-form" action="/board/list">
                        <%--   번호 검색    --%>
                        <div class="row mt-4">
                            <div class="col">
                                <div class="form-floating">
                                    <input name="id" class="form-control" type="text" placeholder="번호">
                                    <label class="form-label text-secondary">번호</label>
                                </div>
                            </div>
                        </div>
                        <%--   작성자 검색    --%>
                        <div class="row mt-4">
                            <div class="col">
                                <div class="form-floating">
                                    <input name="writer" class="form-control" type="text" placeholder="작성자">
                                    <label class="form-label text-secondary">작성자</label>
                                </div>
                            </div>
                        </div>
                        <%--   제목 검색    --%>
                        <div class="row mt-4">
                            <div class="col">
                                <div class="form-floating">
                                    <input name="title" class="form-control" type="text" placeholder="제목">
                                    <label class="form-label text-secondary">제목</label>
                                </div>
                            </div>
                        </div>
                        <%--   내용 검색    --%>
                        <div class="row mt-4">
                            <div class="col">
                                <div class="form-floating">
                                    <input name="content" class="form-control" type="text" placeholder="내용">
                                    <label class="form-label text-secondary">내용</label>
                                </div>
                            </div>
                        </div>
                        <div class="row mt-4">
                            <div class="col text-end">
                                <input type="reset" class="btn btn-warning btn-lg" value="초기화">
                                <a href="/board/list" class="btn btn-secondary btn-lg">목록</a>
                                <button type="submit" class="btn btn-success btn-lg">검색</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%--게시글 개수 및 글쓰기 버튼--%>
<div class="row mt-4">
    <div class="col-md-10 offset-md-1">
        <div class="row">
            <div class="col text-start">
                (1/125)
            </div>
            <div class="col text-end">
                <a href="/board/write" class="btn btn-primary">글쓰기</a>
            </div>
        </div>
    </div>
</div>

<%--  container  --%>
<div class="row mt-4">
    <div class="col-md-10 offset-md-1">
        <table class="table table-hover">
            <thead class="bg-light text-light">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="board" items="${list}">
                <tr>
                    <td>${board.id}</td>
                    <td align="left">
                        <a href="/board/detail?id=${board.id}">
                                ${board.title}
                        </a>
                    </td>
                    <td>${board.writer}</td>
                    <td>${board.writeTime}</td>
                    <td>${board.readCount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="empty-space"></div>

<%-- 페이지 네이게이터  --%>
<div class="row mt-4">
    <div class="col-md-10 offset-md-1">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <c:choose>
                    <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
                <c:when test="${paging.page<=1}">
            <li class="page-item"><a class="page-link disabled rounded-0" href="#">&lt;</a></li>
            </c:when>
                <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
            <c:otherwise>
                <li class="page-item"><a class="page-link rounded-0" href="/board/list?page=${paging.page-1}">&lt;</a></li>
            </c:otherwise>
            </c:choose>
            </li>
            <%--  for(int i=startPage; i<=endPage; i++)      --%>
            <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
                <c:choose>
                    <%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
                    <c:when test="${i eq paging.page}">
                        <li class="page-item active"><a class="page-link rounded-0" href="#">${i}</a></li>
                    </c:when>

                    <c:otherwise>
                        <li class="page-item"><a class="page-link rounded-0" href="/board/list?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <li class="page-item">
                <c:choose>
                <c:when test="${paging.page>=paging.maxPage}">
            <li class="page-item"><a class="page-link disabled rounded-0" href="#">&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link rounded-0" href="/board/list?page=${paging.page+1}">&gt;</a></li>
            </c:otherwise>
            </c:choose>
            </li>
        </ul>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>