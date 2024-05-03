<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>BSUIR Tempo</title>
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
        <a class="navbar-brand" href="/"><span class="flaticon-pizza-1 mr-1"></span>BSUIR<br><small>Tempo</small></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>
        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a href="/" class="nav-link">Главная</a></li>
                <li class="nav-item"><a href="/menu" class="nav-link">Меню</a></li>
                <li class="nav-item"><a href="/log" class="nav-link">Авторизация</a></li>

               <li class="nav-item"><a href="/reg" class="nav-link">Регистрация</a></li>
<%--                <li class="nav-item"><a href="about.html" class="nav-link">О нас</a></li>--%>
<%--                <li class="nav-item"><a href="contact.html" class="nav-link">Контакты</a></li>--%>
            </ul>
        </div>
    </div>
</nav>
<!-- END nav -->

<section class="home-slider owl-carousel img" style="background-image: url(/static/images/bg_1.jpg);">


    <div class="slider-item">
        <div class="overlay"></div>
        <div class="container">
            <div class="row slider-text align-items-center" data-scrollax-parent="true">

                <div class="col-md-6 col-sm-12 ftco-animate">
                    <span class="subheading">Вкуснейшая</span>
                    <h1 class="mb-4">Итальянская кухня</h1>
                    <p class="mb-4 mb-md-5">Для студентов БГУИР скидка 15%</p>
                    <p><a href="/order" class="btn btn-primary p-3 px-xl-4 py-xl-3">Заказать</a> <a href="/menu" class="btn btn-white btn-outline-white p-3 px-xl-4 py-xl-3">Меню</a></p>
                </div>
                <div class="col-md-6 ftco-animate">
                    <img src="/static/images/bg_1.png" class="img-fluid" alt="">
                </div>

            </div>
        </div>
    </div>

    <div class="slider-item">
        <div class="overlay"></div>
        <div class="container">
            <div class="row slider-text align-items-center" data-scrollax-parent="true">

                <div class="col-md-6 col-sm-12 ftco-animate">
                    <span class="subheading">Вкуснейшая</span>
                    <h1 class="mb-4">Итальянская кухня</h1>
                    <p class="mb-4 mb-md-5">Для студентов БГУИР скидка 15%</p>
                    <p><a href="/order" class="btn btn-primary p-3 px-xl-4 py-xl-3">Заказать</a> <a href="/menu" class="btn btn-white btn-outline-white p-3 px-xl-4 py-xl-3">Меню</a></p>
                </div>
                <div class="col-md-6 ftco-animate">
                    <img src="/static/images/bg_2.png" class="img-fluid" alt="">
                </div>

            </div>
        </div>
    </div>

    <div class="slider-item" style="background-image: url(/static/images/bg_3.jpg);">
        <div class="overlay"></div>
        <div class="container">
            <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">

                <div class="col-md-6 col-sm-12 ftco-animate">
                    <span class="subheading">Вкуснейшая</span>
                    <h1 class="mb-4">Итальянская кухня</h1>
                    <p class="mb-4 mb-md-5">Для студентов БГУИР скидка 15%</p>
                    <p><a href="/order" class="btn btn-primary p-3 px-xl-4 py-xl-3">Заказать</a> <a href="/menu" class="btn btn-white btn-outline-white p-3 px-xl-4 py-xl-3">Меню</a></p>
                </div>

            </div>
        </div>
    </div>
</section>

<section class="ftco-intro">
    <div class="container-wrap">
        <div class="wrap d-md-flex">
            <div class="info">
                <div class="row no-gutters">
                    <div class="col-md-4 d-flex ftco-animate">
                        <div class="icon"><span class="icon-phone"></span></div>
                        <div class="text">
                            <h3>(00) 000 00 00</h3>
                            <p>Телефон нашего колл-центра</p>
                        </div>
                    </div>
                    <div class="col-md-4 d-flex ftco-animate">
                        <div class="icon"><span class="icon-my_location"></span></div>
                        <div class="text">
                            <h3>город Минск</h3>
                            <p>Платонова, 39</p>
                        </div>
                    </div>
                    <div class="col-md-4 d-flex ftco-animate">
                        <div class="icon"><span class="icon-clock-o"></span></div>
                        <div class="text">
                            <h3>Открыты все дни недели</h3>
                            <p>10:00 - 22:00</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="social d-md-flex pl-md-5 p-4 align-items-center">
                <ul class="social-icon">
                    <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                    <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                    <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
                </ul>
            </div>
        </div>
    </div>
</section>

<section class="ftco-about d-md-flex">
    <div class="one-half img" style="background-image: url(/static/images/about.jpg);"></div>
    <div class="one-half ftco-animate">
        <div class="heading-section ftco-animate ">
            <h2><span class="flaticon-pizza"> Пицца</span> BSUIR Tempo</h2>
        </div>
        <div>
            <p>У нас вы можете насладиться вкуснейшими блюдами, для этого вам нужно
                быстро и просто зарегистрироваться или войти в уже существующий аккаунт. Понятный и дружелюбный интерфейс
            позволит за считаные секунды сделать заказ! Среднее время выполнения 30 минут - и вкуснейшая пицца готова! Приятного вам аппетита!</p>
        </div>
    </div>
</section>


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
<script async src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
<script src="/static/js/google-map.js"></script>
<script src="/static/js/main.js"></script>

</body>
</html>