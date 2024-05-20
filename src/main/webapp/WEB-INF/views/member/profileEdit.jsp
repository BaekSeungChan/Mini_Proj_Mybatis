<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/classic/translations/ko.js"></script>

<form action="/save">
    <input name="title" type="text" placeholder="제목"><br/>
    <textarea name="content" id="content"></textarea><br/>
    <input type="submit" value="등록">
</form>

<script>

    ClassicEditor.create( document.querySelector( '#content' ), {

        language: "ko"
    } );

</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
