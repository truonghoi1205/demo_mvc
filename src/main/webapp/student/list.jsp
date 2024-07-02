<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 28/06/2024
  Time: 09:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách học sinh</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Trang chủ</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="/student">Học sinh</a>
                <a class="nav-link" href="/teacher">Giáo viên</a>
                <a class="nav-link" href="/class">Lớp học</a>
            </div>
        </div>
    </div>
</nav>
<div class="container">
    <p style="color: blue">${message}</p>
    <div class="d-flex justify-content-between">
        <form action="/student?action=search" class="m-0 d-flex">
            <input type="hidden" name="action" value="search">
            <input type="search" class="form-control me-3" placeholder="Tìm kiếm" name="search">
            <button class="btn btn-secondary btn-sm px-3">Tìm</button>
        </form>
        <button class="btn btn-primary btn-sm px-4" onclick="window.location.href='/student?action=create'">Thêm</button>
    </div>
   
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Số thứ tự</th>
            <th>Mã</th>
            <th>Họ và tên</th>
            <th>Địa chỉ</th>
            <th>Điểm</th>
            <th>Học lực</th>
            <th>Chức năng</th>
        </tr>
        </thead>
        <tbody>
        <%--        for(Student student : students)--%>
        <c:forEach var="student" items="${students}" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.address}</td>
                <td>${student.point}</td>
                <td>
                        <%--                    <c:if test="${student.point >= 8}">Học sinh giỏi</c:if>--%>
                        <%--                    <c:if test="${student.point >= 6.5 && student.point < 8}">Học sinh khá</c:if>--%>
                        <%--                    <c:if test="${student.point >= 5 && student.point <6.5}">Học sinh trung bình</c:if>--%>
                        <%--                    <c:if test="${student.point >= 3.5 && student.point < 5}">Học sinh yếu</c:if>--%>
                        <%--                    <c:if test="${student.point < 3.5}">Cook</c:if>--%>
                    <c:choose>
                        <c:when test="${student.point >= 8}">Học sinh giỏi</c:when>
                        <c:when test="${student.point >= 6.5}">Học sinh khá</c:when>
                        <c:when test="${student.point >= 5}">Học sinh trung bình</c:when>
                        <c:when test="${student.point >= 3.5}">Học sinh yếu</c:when>
                        <c:otherwise>Cook</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="/student?action=update&id=${student.id}" class="btn btn-primary">Sửa</a>
                    <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal${student.id}">
                        Xóa
                    </button>
                    <div class="modal fade" id="deleteModal${student.id}" tabindex="-1"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Xóa học sinh</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    Bạn có muốn xóa học sinh có tên là ${student.name}?
                                    <p style="color: red">Hành động này không thể hoàn tác!!!!!</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                    <form action="/student?action=delete" method="post">
                                        <button type="submit" class="btn btn-primary">Xác nhận</button>
                                        <input type="hidden" name="id" value="${student.id}">
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</html>
