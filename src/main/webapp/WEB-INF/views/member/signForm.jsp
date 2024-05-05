<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <title>사용자 등록 페이지</title>
</head>
<body>
<h1>사용자 등록 페이지</h1>
<form action="/member/new" method="post">
    <%--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

    <div>
        <label>이름 : <input type="text" name="name" required/></label>
    </div>

    <div>
        <label>이메일주소 : <input type="email" name="email" /></label>
        <span id="errorEmailContainer"></span>
    </div>

    <div>
        <label>비밀번호 : <input type="password" name="password" required/></label>
        <span id="errorPasswordContainer"></span>
    </div>

    <div>
        <label>주소 : <input type="text" name="address" required/></label>
    </div>

    <input type="submit" value="제출">
</form>

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


</body>
</html>