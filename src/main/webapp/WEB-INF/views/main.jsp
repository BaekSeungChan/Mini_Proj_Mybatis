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



<script>
    var myCarousel = document.querySelector('#myCarousel')
    var carousel = new bootstrap.Carousel(myCarousel, {
        interval: 500,
        wrap: false
    })
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>