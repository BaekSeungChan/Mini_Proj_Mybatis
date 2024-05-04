<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>



<div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="https://png.pngtree.com/thumb_back/fw800/background/20230718/pngtree-3d-render-of-the-ocean-and-sky-image_3909990.jpg" class="d-block w-100" alt="..." style="height: 500px;">
        </div>
        <div class="carousel-item">
            <img src="https://blog.kakaocdn.net/dn/8ees0/btq33bjZoFY/6SSEPleWKXNwMHlW3p5mvk/img.jpg" class="d-block w-100" alt="..." style="height: 500px;">
        </div>
        <div class="carousel-item">
            <img src="https://flexible.img.hani.co.kr/flexible/normal/970/582/imgdb/child/2023/1216/53_17026896936857_20231215502435.jpg" class="d-block w-100" alt="..." style="height: 500px;">
        </div>
    </div>
</div>

<div class="empty-space"></div>


<div class="container-fluid">
    <div class="container text-center">
        <div class="row row-cols-4">
            <div class="col">
                <div class="card border-success mb-3" style="max-width: 20rem;">
                    <div class="card-header">Header</div>
                    <div class="card-body">
                        <h4 class="card-title">Success card title</h4>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card border-danger mb-3" style="max-width: 20rem;">
                    <div class="card-header">Header</div>
                    <div class="card-body">
                        <h4 class="card-title">Danger card title</h4>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card border-info mb-3" style="max-width: 20rem;">
                    <div class="card-header">Header</div>
                    <div class="card-body">
                        <h4 class="card-title">Info card title</h4>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card border-warning mb-3" style="max-width: 20rem;">
                    <div class="card-header">Header</div>
                    <div class="card-body">
                        <h4 class="card-title">Warning card title</h4>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<script>
    var myCarousel = document.querySelector('#myCarousel')
    var carousel = new bootstrap.Carousel(myCarousel, {
        interval: 500,
        wrap: false
    })
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>