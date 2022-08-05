<%--
  Created by IntelliJ IDEA.
  User: ghoul
  Date: 8/2/22
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="loader-wrapper">
    <div id="loader"></div>

    <div class="loader-section section-left"></div>
    <div class="loader-section section-right"></div>

</div>
<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
            <i class="fas fa-film mr-2"></i>
            ONLINE ENTERTAINMENT
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto mb-2 mb-lg-0">
                <c:choose>
                    <c:when test="${not empty sessionScope.currentUser}">
                        <li class="nav-item">
                            <a class="nav-link nav-link-1 active" aria-current="page" href="#">Welcome, ${sessionScope.currentUser.username}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-1" href="${pageContext.request.contextPath}/favorites">My favorites</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-2" href="${pageContext.request.contextPath}/history">History</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-3" href="${pageContext.request.contextPath}/logout">Log out</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link nav-link-2" href="${pageContext.request.contextPath}/login">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-3" href="${pageContext.request.contextPath}/register">Register</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-link-4" href="${pageContext.request.contextPath}/forgot-password">Forgot password</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

<div class="tm-hero d-flex justify-content-center align-items-center" data-parallax="scroll" data-image-src="<c:url value='/templates/user/img/hero.jpg' />">
    <form class="d-flex tm-search-form">
        <input class="form-control tm-search-input" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success tm-search-btn" type="submit">
            <i class="fas fa-search"></i>
        </button>
    </form>
</div>
