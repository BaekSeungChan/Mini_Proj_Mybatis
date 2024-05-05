<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<div class="container">
    <div class="row mt-4">
        <div class="col-md-6 offset-md-3">
            <h1 class="text-center">회원 가입</h1>
            <form action="/member/new" method="post">
                <div class="mb-3">
                    <label for="name" class="form-label">이름</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">이메일 주소</label>
                    <input type="email" class="form-control" id="email" name="email">
                    <span id="errorEmailContainer"></span>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                    <span id="errorPasswordContainer"></span>
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">주소</label>
                    <input type="text" class="form-control" id="address" name="address" required>
                </div>
                <button type="submit" class="btn btn-primary">가입하기</button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>


<script>
    const form = document.querySelector('form');
    const errorPasswordContainer = document.getElementById('errorPasswordContainer');

    const signUp = async (event) => {
        event.preventDefault();

        const formData = new FormData(form);
        const param = Object.fromEntries(formData.entries());

        try {
            const response = await fetch("/member/new", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(param)
            });

            if (!response.ok) {
                const error = await response.json();
                console.error('Error:', error);
                if (error.error != null) {
                    alert(error.error)
                } else {
                    if (error.password != null) {
                        errorPasswordContainer.innerHTML = error.password;
                    }
                }
            } else {
                const data = await response.json();
                console.log('Data:', data);
                if (data.SUCCESS == 1) {
                    location.href = "/board/list";
                }
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    form.addEventListener('submit', signUp);
</script>
