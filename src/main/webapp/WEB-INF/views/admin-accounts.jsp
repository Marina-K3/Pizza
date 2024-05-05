<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 01.05.2024
  Time: 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Администратор</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nothing+You+Could+Do" rel="stylesheet">

    <link rel="stylesheet" href="/static/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/animate.css">

    <link rel="stylesheet" href="/static/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/static/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/static/css/magnific-popup.css">

    <link rel="stylesheet" href="/static/css/aos.css">

    <link rel="stylesheet" href="/static/css/ionicons.min.css">

    <link rel="stylesheet" href="/static/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="/static/css/jquery.timepicker.css">


    <link rel="stylesheet" href="/static/css/flaticon.css">
    <link rel="stylesheet" href="/static/css/icomoon.css">
    <link rel="stylesheet" href="/static/css/style.css">
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand"><span class="flaticon-pizza-1 mr-1"></span>${admin.firstName}<br><small>администратор</small></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>
        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a href="/profile" class="nav-link">Профиль</a></li>
                <li class="nav-item"><a href="/admin/menu" class="nav-link">Меню</a></li>
                <li class="nav-item"><a href="/admin/orders" class="nav-link">Заказы</a></li>
                <li class="nav-item"><a href="/admin/promos" class="nav-link">Акции</a></li>
                <li class="nav-item"><a href="/admin/accounts" class="nav-link">Аккаунты</a></li>
                <li class="nav-item"><a href="/admin/comments" class="nav-link">Отзывы</a></li>
                <li class="nav-item"><a href="/logout" class="nav-link">Выйти</a></li>

            </ul>
        </div>
    </div>
</nav>

<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center mb-5 pb-3">
            <div class="col-md-7 heading-section ftco-animate text-center">
                <h2 class="mb-4">Аккаунты пользователей</h2>
                <p>Здесь вы можете создавать, банить/разбанить, изменить роль пользователя</p>
            </div>
        </div>
    </div>

    <div class="container-wrap">
        <div class="row no-gutters d-flex">
            <c:forEach var="user" items="${users}">
                <div class="col-lg-3 d-flex ftco-animate">
                    <div class="services-wrap d-flex">
                        <div class="staff">
                            <div class="img mb-4">
                                <img class="img mb-4" src = "/image/${user.image.id}">
                            </div>
                            <div class="info text-center">
                                <h2>${user.firstName} ${user.lastName}</h2>
                                <h5>${user.phone}</h5>
                                <h5>логин: ${user.login}</h5>
                                <h5>роль: ${user.role}</h5>
                                <c:if test="${user.active}">
                                 <h5>Не забанен</h5>
                                </c:if>
                                <c:if test="${not user.active}">
                                    <h5>Забанен</h5>
                                </c:if>
                                <p>
                                <a href="/admin/accounts/setRole/${user.id}" class="ml-2 btn btn-white btn-outline-white">Сменить роль</a>
                                <a href="/admin/accounts/ban/${user.id}" class="ml-2 btn btn-white btn-outline-white">Забанить/Разбанить</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<section class="ftco-appointment">
    <div class="overlay"></div>
    <div class="container-wrap">
        <div class="row no-gutters d-md-flex align-items-center">

            <div class="col-md-6 appointment ftco-animate" style="margin-left: 30%">
                <h3 class="mb-3">Регистрация нового пользователя</h3>
                <form action="/admin/createUser" class="appointment-form" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="d-md-flex">
                        <div class="form-group">
                            <input type="text" name="lastName" class="form-control" placeholder="Фамилия" required>
                        </div>
                    </div>
                    <div class="d-me-flex">
                        <div class="form-group">
                            <input type="text" name="firstName" class="form-control" placeholder="Имя" required>
                        </div>
                    </div>
                    <div class="d-me-flex">
                        <div class="form-group">
                            <input type="tel" name="phone" class="form-control" placeholder="Телефон" required>
                        </div>
                    </div>
                    <div class="d-me-flex">
                        <div class="form-group">
                            <input type="text" name="login" class="form-control" placeholder="Логин" required>
                        </div>
                    </div>
                    <div class="d-me-flex">
                        <div class="form-group">
                            <input type="password" name="password" class="form-control" placeholder="Пароль" required>
                        </div>
                    </div>
                    <div class="d-me-flex">
                        <div class="form-group">
                            <input  required type="file" name="image" accept="image/jpeg">
                        </div>
                    </div>

                    <div class="form-group">
                        <input type="submit" value="Регистрация" class="btn btn-primary py-3 px-4">
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>



<!-- loader -->
<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/jquery-migrate-3.0.1.min.js"></script>
<script src="/static/js/popper.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/jquery.easing.1.3.js"></script>
<script src="/static/js/jquery.waypoints.min.js"></script>
<script src="/static/js/jquery.stellar.min.js"></script>
<script src="/static/js/owl.carousel.min.js"></script>
<script src="/static/js/jquery.magnific-popup.min.js"></script>
<script src="/static/js/aos.js"></script>
<script src="/static/js/jquery.animateNumber.min.js"></script>
<script src="/static/js/bootstrap-datepicker.js"></script>
<script src="/static/js/jquery.timepicker.min.js"></script>
<script src="/static/js/scrollax.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
<script src="/static/js/google-map.js"></script>
<script src="/static/js/main.js"></script>

</body>
</html>
