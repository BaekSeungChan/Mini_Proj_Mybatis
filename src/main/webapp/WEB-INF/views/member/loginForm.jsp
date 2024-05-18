<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<div class="container">
    <div class="row mt-4">
        <div class="col-md-6 offset-md-3">
            <h1 class="text-center">로그인</h1>
            <div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="mb-3">
                    <label for="username" class="form-label" >아이디</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <c:if test="${not empty loginErrorMsg}">
                    <script>alert("<c:out value='${loginErrorMsg}'/>")</script>
                </c:if>
                <div>
                    <label>자동 로그인 <input type="checkbox" name="remember-me" id="rememberMe"></label>
                </div>
                <div style="margin-top: 10px">
                    <button onclick="login()" class="btn btn-primary">로그인</button>
                    <a href="/member/new" class="btn btn-secondary">회원가입</a>
                </div>
                <div>
                    <button onclick="onNaverLogin()">네이버 로그인</button>
                </div>
                <div>
                    <button onclick="onGoogleLogin()">
                        구글 로그인
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    const onNaverLogin = () => {
        window.location.href = "http://localhost:8888/oauth2/authorization/naver"
    }

    const onGoogleLogin = () => {
        window.location.href = "http://localhost:8888/oauth2/authorization/google"
    }
</script>

<script>
    const username = document.getElementById("username");
    const password = document.getElementById("password");

    const login = () => {
        const formData = new URLSearchParams();
        formData.append('username', username.value);
        formData.append('password', password.value);

        fetch("/auth/login", {
            method: 'POST',
            body: formData,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
        })
            .then(response => response.json())
            .then(data => {
                if(data.status == "OK"){
                    location.href = "/"
                }
            })
            .catch(error => console.error('Error:', error));
    };

</script>

<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript">
    Kakao.init('Javascript API KEY를 여기에 입력한다');
    function kakaoLogin() {
        Kakao.Auth.login({
            success: function (response) {
                Kakao.API.request({
                    url: '/v2/user/me',
                    success: function (response) {
                        alert(JSON.stringify(response))
                    },
                    fail: function (error) {
                        alert(JSON.stringify(error))
                    },
                })
            },
            fail: function (error) {
                alert(JSON.stringify(error))
            },
        })
    }
</script>


<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
