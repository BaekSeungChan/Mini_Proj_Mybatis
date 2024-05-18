<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<div class="container">
    <div class="row mt-4">
        <div class="col-md-6 offset-md-3">
            <h1 class="text-center">회원 가입</h1>
            <div>
                <div class="mb-3">
                    <label for="username" class="form-label">아이디</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                    <span id="errorPasswordContainer"></span>
                </div>
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
                    <label for="address" class="form-label">주소</label>
                    <input type="text" class="form-control" id="address" name="address" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">성별 : </label>
                    <input type='radio' name='sex' value='female' />여성
                    <input type='radio' name='sex' value='male' style="margin-left: 10px"/>남성
                </div>
                <button id="signUpButton" class="btn btn-primary">가입하기</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

<script>
    document.getElementById('signUpButton').addEventListener('click', async (event) => {
        event.preventDefault();

        const errorPasswordContainer = document.getElementById('errorPasswordContainer');
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;
        const name = document.getElementById("name").value;
        const email = document.getElementById("email").value;
        const address = document.getElementById("address").value;
        const sex = document.querySelector('input[name="sex"]:checked').value;

        const param = {
            username: username,
            password: password,
            name: name,
            email: email,
            address: address,
            sex: sex,
        };

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
                    alert(error.error);
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
    });
</script>
