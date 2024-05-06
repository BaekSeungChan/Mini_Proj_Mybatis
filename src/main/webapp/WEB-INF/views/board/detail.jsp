<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>


<div class="container-fluid">
    <div class="row mt-4">
        <div class="col-md-10 offset-md-1">
            <h1>${board.title}</h1>
            <h2 class="text-secondary">${board.writer}</h2>
        </div>
    </div>

    <%-- 게시글 정보표시 자리   --%>
    <div class="row mt-4">
        <div class="col-md-10 offset-md-1">
            <div class="row">
                <div class="col-6 text-start">
                    <i class="fa-solid fa-eye"></i><span class="ms-1">${board.readCount}</span>
                    <span class="ms-4 text-secondary">
                        <fmt:parseDate value="${board.writeTime}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedWriteTime" />
                        <fmt:formatDate value="${parsedWriteTime}" pattern="yyyy'년' M'월' d'일' a h'시' m'분'" />
                    </span>
                </div>
                <div class="col-6 text-end">
                    <li class="ms-2 fa-regular fa-bookmark"></li>
                    <li class="ms-2 fa-solid fa-share-nodes"></li>
                    <li class="ms-2 fa-regular fa-share-from-square"></li>
                </div>
            </div>
            <hr>
        </div>
    </div>

    <div class="row mt-4" style="min-height: 350px">
        <div class="col-md-10 offset-md-1">
            <pre>${board.content}</pre>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-md-10 offset-md-1 text-end">
            <hr>
            <a class="btn btn-primary">글쓰기</a>
            <a class="btn btn-success">답글쓰기</a>
            <button onclick="listFn()" class="btn btn-warning">목록</button>
            <button onclick="updateFn()" class="btn btn-danger">수정</button>
            <button onclick="deleteFn()" class="btn btn-btn-dark">삭제</button>
        </div>
    </div>

    <%--    댓글 표시 영역    --%>
    <div class="row mt-4">
        <div class="col-md-10 offset-md-1">
            5,000 댓글이 있습니다.
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-md-10 offset-md-1">
            <div>
                <textarea id="commentContent" class="form-control" rows="4" style="resize: none"></textarea>
                <button id="comment-write-btn" onclick="commentWrite()" class="btn btn-primary w-100 mt-3">등록</button>
            </div>
        </div>
    </div>


    <c:forEach var="reply" items="${replyList}">
        <div class="row mt-4">
            <div class="col-md-10 offset-md-1">
                <hr>
                <h5 class="text-dark">${reply.writer}</h5>
                <h6 class="text-secondary">?분 전
                    <i class="ms-4 fa-regular fa-thumbs-up text-danger"></i>
                    <span class="text-danger">500</span>
                </h6>
                <pre class="mt-3" style="min-height: 75px">${reply.content}</pre>
            </div>
        </div>
    </c:forEach>
</div>


<script>
    const listFn = () => {
        location.href = "/board/list"
    }

    const updateFn = () => {
        location.href = "/board/edit?id=${board.id}"
    }

    const deleteFn = () => {
        location.href = "/board/delete?id=${board.id}"
    }

    const commentWrite = () => {
        const writer = '${sessionScope.userInfo}';
        const content = document.getElementById("commentContent").value;
        const boardId = '${board.id}';

        const param = {
            writer: writer,
            content: content,
            boardId: boardId
        };

        fetch("/reply/save", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(param)
        })
            .then(response => response.json())
            .then(json => {
                // 받아온 댓글 목록을 화면에 추가
                const replyList = json;
                const container = document.querySelector('.container-fluid');

                replyList.forEach(reply => {
                    const newRow = document.createElement('div');
                    newRow.classList.add('row', 'mt-4');

                    const newCol = document.createElement('div');
                    newCol.classList.add('col-md-10', 'offset-md-1');

                    const newHr = document.createElement('hr');
                    newCol.appendChild(newHr);

                    const writerHeader = document.createElement('h5');
                    writerHeader.classList.add('text-dark');
                    writerHeader.textContent = reply.writer;

                    const timeHeader = document.createElement('h6');
                    timeHeader.classList.add('text-secondary');
                    timeHeader.innerHTML = `?분 전
                    <i class="ms-4 fa-regular fa-thumbs-up text-danger"></i>
                    <span class="text-danger">500</span>`;

                    const contentPre = document.createElement('pre');
                    contentPre.classList.add('mt-3');
                    contentPre.style.minHeight = '75px';
                    contentPre.textContent = reply.content;

                    newCol.appendChild(writerHeader);
                    newCol.appendChild(timeHeader);
                    newCol.appendChild(contentPre);
                    newRow.appendChild(newCol);
                    container.appendChild(newRow);
                });

                // 댓글 등록 후 textarea 초기화
                document.getElementById("commentContent").value = "";

                location.href = "/board/detail?id=${board.id}";
            })
            .catch(error => console.error('Error:', error));
    };




</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>