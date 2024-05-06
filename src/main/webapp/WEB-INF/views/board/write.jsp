<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<div class="container-fluid">
    <div class="col-md-10 offset-md-1">
        <h1>게시글 작성</h1>
        <p>
            글은 자신의 인격입니다.
        </p>
    </div>

    <form method="post">
        <div class="row mt-4">
            <div class="col-md-10 offset-md-1">
                <div class="form-floating">
                    <input type="text" name="writer" class="form-control"  value="${sessionScope.userInfo}" readonly>
                    <label class="form-label text-secondary">작성자</label>
                </div>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-md-10 offset-md-1">
                <div class="form-floating">
                    <input type="text" name="title" class="form-control" placeholder="제목" required>
                    <label class="form-label text-secondary">제목</label>
                </div>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-md-10 offset-md-1">
                <textarea name="content" class="form-control" rows="15" placeholder="내용 작성" required></textarea>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-md-10 offset-md-1">
                <div class="form-floating">
                    <input type="password" name="password" class="form-control" placeholder="비밀번호" required>
                    <label class="form-label text-secondary">비밀번호</label>
                </div>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-md-10 offset-md-1 text-end">
                <a href="/board/list" type="button" class="btn btn-secondary btn-lg">목록</a>
                <button type="submit" class="btn btn-primary btn-lg">등록</button>
            </div>
        </div>
    </form>
</div>


<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>