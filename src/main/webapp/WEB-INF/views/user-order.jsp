<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 01.05.2024
  Time: 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <a class="navbar-brand"><span class="flaticon-pizza-1 mr-1"></span>${user.firstName}<br><small>пользователь</small></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>
        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a href="/profile" class="nav-link">Профиль</a></li>
                <li class="nav-item"><a href="/user/menu" class="nav-link">Меню</a></li>
                <li class="nav-item"><a href="/user/orders" class="nav-link">Заказы</a></li>
                <li class="nav-item"><a href="/user/promos" class="nav-link">Акции</a></li>
                <li class="nav-item"><a href="/user/comments" class="nav-link">Комментарии</a></li>
                <li class="nav-item"><a href="/logout" class="nav-link">Выйти</a></li>

            </ul>
        </div>
    </div>
</nav>

<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center mb-2 pb-3">
            <div class="col-md-7 heading-section ftco-animate text-center">
                <h2 class="mb-4">Корзина</h2>
                <p>Здесь вы можете просмотреть выбранные позиции, изменить их размер и кол-во</p>
            </div>
        </div>
        <div class="row justify-content-center mb-2 pb-3 mt-1 pt-1">
            <div class="col-md-7 heading-section text-center ftco-animate">
                <h2 class="mb-4">Стоимость всей корзины <span>
    <c:set var="bucketPrice" value="${user.bucket.bucketPrice}" />
    <c:set var="dotIndex" value="${fn:indexOf(bucketPrice, '.')}"/>
    <c:set var="wholePart" value="${fn:substring(bucketPrice, 0, dotIndex)}" />
    <c:set var="fractionPart" value="${fn:substring(bucketPrice, dotIndex + 1, dotIndex + 3)}" />
    ${wholePart}.<c:out value="${fractionPart}" />
</span></h2>
                <p class="flip"><span class="deg1"></span><span class="deg2"></span><span class="deg3"></span></p>
            </div>
        </div>
    </div>

    <div class="container-wrap">
        <div class="row no-gutters d-flex">
            <c:forEach var="productItem" items="${user.bucket.productItems}">


                    <div class="col-lg-4 d-flex ftco-animate">
                        <div class="services-wrap d-flex">

                            <img src="/image/${productItem.product.image.id}" class="img">


                            <div class="text p-4">
                                <h3>${productItem.product.productName}</h3>
                                <br>
                                    <p class="price">
                                        размер: <span>${productItem.size}</span>
                                        <a href="/user/newSizeS/${productItem.id}" class="ml-2 btn btn-white btn-outline-white">S</a>
                                        <a href="/user/newSizeM/${productItem.id}" class="ml-2 btn btn-white btn-outline-white">M</a>
                                        <a href="/user/newSizeL/${productItem.id}" class="ml-2 btn btn-white btn-outline-white">L</a>
                                    </p>
                                  <br>
                                <p class="price">
                                    кол-во: <span>${productItem.quantity}</span> шт.
                                    <a href="/user/addItem/${productItem.id}" class="ml-2 btn btn-white btn-outline-white">+</a>
                                    <a href="/user/deleteItem/${productItem.id}" class="ml-2 btn btn-white btn-outline-white">-</a>
                                </p>
                                <br>
                                <p class="price">
                                    цена:
                                    <span>
        <c:set var="itemPrice" value="${productItem.itemPrice}" />
        <c:set var="dotIndex" value="${fn:indexOf(itemPrice, '.')}"/>
        <c:set var="wholePart" value="${fn:substring(itemPrice, 0, dotIndex)}" />
        <c:set var="fractionPart" value="${fn:substring(itemPrice, dotIndex + 1, dotIndex + 3)}" />
        ${wholePart}.<c:out value="${fractionPart}" />
    </span>
                                    BYN
                                </p>
                            </div>
                        </div>
                    </div>


            </c:forEach>
        </div>
    </div>

    <section class="ftco-appointment">
        <div class="overlay"></div>
        <div class="container-wrap">
            <div class="row no-gutters d-md-flex align-items-center">

                <div class="col-md-6 appointment ftco-animate" style="margin-left: 30%">
                    <h3 class="mb-3">Ваш заказ общей суммой
                        <span>
                            <c:set var="itemPrice" value="${personalPrice}" />
                            <c:set var="dotIndex" value="${fn:indexOf(itemPrice, '.')}"/>
                            <c:set var="wholePart" value="${fn:substring(itemPrice, 0, dotIndex)}" />
                            <c:set var="fractionPart" value="${fn:substring(itemPrice, dotIndex + 1, dotIndex + 3)}" />
                            ${wholePart}.<c:out value="${fractionPart}" /> BYN
                        </span></h3>
                    <form action="/user/GetPromo" class="appointment-form" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <p>вы можете применить промокод из любой акции, а также ввести свои персональные баллы</p>
                        <div class="d-md-flex">
                            <div class="form-group">
                                <p>Введите баллы (у вас их ${user.points.quantity})</p>
                                <input type="number" step="1" name="points" class="form-control" value="0" required>
                            </div>
                        </div>
                        <div class="d-md-flex">
                            <p>Введите промокод</p>
                            <div class="form-group">
                                <input type="text" name="productDescription" class="form-control" placeholder="нет" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <p>Перед тем, как делать заказ, нажмите применить, без этого промокод и баллы не сработают</p>
                            <input type="submit" value="Применить" class="btn btn-primary py-3 px-4">
                            <a href="/user/createOrder" class="ml-2 btn btn-white btn-outline-white">Сделать заказ</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>


    <div class="container" style="margin-top: 90px">
        <div class="row justify-content-center mb-5 pb-3">
            <div class="col-md-7 heading-section ftco-animate text-center">
                <h2 class="mb-4">История заказов</h2>
            </div>
        </div>
    </div>

    <div class="container-wrap">
        <div class="row no-gutters d-flex">
            <c:forEach var="order" items="${orders}">

                    <div class="col-lg-4 d-flex ftco-animate">
                        <div class="services-wrap d-flex">
                            <c:if test="${not order.cancelled}">
                                  <c:if test="${order.is_ready}">
                                    <img class="img" src="/static/images/ready.png">
                                  </c:if>
                                    <c:if test="${not order.is_ready}">
                                        <img class="img" src="/static/images/notReady.png">
                                    </c:if>
                                </c:if>
                            <c:if test="${order.cancelled}">
                                <img class="img" src="/static/images/Cancel.png">
                            </c:if>
                            <div class="text p-4">
                                <h3>${order.createdTime}</h3>
                                <p>стоимость: ${order.OrderPrice} BYN:</p>

                                <c:set var="itemPrice" value="${order.OrderPrice}" />
                                <c:set var="dotIndex" value="${fn:indexOf(itemPrice, '.')}"/>
                                <c:set var="wholePart" value="${fn:substring(itemPrice, 0, dotIndex)}" />
                                <c:set var="fractionPart" value="${fn:substring(itemPrice, dotIndex + 1, dotIndex + 3)}" />

                                <span>${wholePart}.<c:out value="${fractionPart}" /></span>
                                <c:forEach var="orderItem" items="${order.productItems}">
                                         <h5> - ${orderItem.product.name} ${orderItem.size} ${orderItem.quantity} шт.</h5>
                                     </c:forEach>
                                <c:if test="${not order.cancelled and not order.is_ready}">
                                <a href="/user/cancelOrder/${order.id}" class="ml-2 btn btn-white btn-outline-white">Отменить</a>
                                </c:if>

                            </div>
                        </div>
                    </div>

            </c:forEach>
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
