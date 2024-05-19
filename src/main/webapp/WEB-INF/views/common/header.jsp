<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/5.3.3/cerulean/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
    <style>
        .empty-space {
            height: 20px;
        }
        .navbar {
            height: 70px;
        }
        .nav-icon {
            font-size: 1.5rem;
        }
    </style>
</head>
<body>

<%--  navbar  --%>
<nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">KOSA</a>
        <h1>${role}</h1>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarColor01">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="/board/list">커뮤니티
                        <span class="visually-hidden">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/finance/list">금융소식</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/book/list">도서</a>
                </li>
            </ul>
            <ul class="navbar-nav ms-auto">
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link nav-icon" href="/book/cart/list">
                            <i class="fas fa-shopping-cart"></i>
                        </a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link nav-icon" href="/member/myprofile">
                            <i class="fas fa-user"></i>
                        </a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="/member/logout">Logout</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="/member/login">Login</a>
                    </li>
                </sec:authorize>
                <c:if test="${role == 'ADMIN'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">관리자</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="/admin/list">User List</a>
                            <a class="dropdown-item" href="#">Community List</a>
                        </div>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
