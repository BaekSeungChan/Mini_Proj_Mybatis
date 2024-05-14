<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<div class="container">
    <div class="row mt-4">
        <div class="col-md-6 offset-md-3">
            <h1 class="text-center">로그인</h1>
            <form action="/member/login" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="mb-3">
                    <label for="email" class="form-label" >이메일 주소</label>
                    <input type="email" class="form-control" id="email" name="username" required>
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
                    <button type="submit" class="btn btn-primary">로그인</button>
                    <a href="/member/new" class="btn btn-secondary">회원가입</a>
                </div>
                <div>
                    <a href="javascript:kakaoLogin()">
                        <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222"
                             alt="카카오 로그인 버튼" />
                    </a>
                    <button>네이버 로그인</button>
                </div>
            </form>
        </div>
    </div>
</div>
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
