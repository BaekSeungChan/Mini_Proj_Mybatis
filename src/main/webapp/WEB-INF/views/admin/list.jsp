<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<div class="container mt-5">
    <h1 class="text-center mb-4">관리자 페이지</h1>

    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th scope="col">아이디</th>
            <th scope="col">이름</th>
            <th scope="col">이메일</th>
            <th scope="col">역할</th>
            <th scope="col">기능</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="member" items="${member}">
            <tr>
                <td>${member.id}</td>
                <td>${member.name}</td>
                <td>${member.email}</td>
                <td>${member.role}</td>
                <td>
                    <button class="btn btn-info btn-sm" onclick="deleteMember(${member.id})">탈퇴</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

<script>
    function deleteMember(id) {
        if (confirm("정말로 삭제하시겠습니까?")) {
            fetch(`/admin/delete?id=${id}`, {
                method: 'GET'
            })
                .then(response => {
                    if (response.ok) {
                        alert("삭제되었습니다.");
                        location.reload();
                    } else {
                        alert("삭제를 실패했습니다.");
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        }
    }
</script>
