<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Manager Promotion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
            crossorigin="anonymous"></script>


    <style>
        .container-gray {
            background-color: rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        .table-white {
            background-color: #ffffff;
            padding: 20px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-2">
            <h6>Admin</h6>

        </div>
        <div class="col-10 container-gray h-100">
            <h1>Manager Promotion</h1>
            <%--                    Bộ lọc Khuyến mãi--%>
            <div class="table-white">
                <h3>Bộ lọc</h3>
                <hr>
                <form>
                    <div class="row">
                        <div class="mb-3 col-4">
                            <label for="promoCode" class="form-label">Mã khuyến mãi</label>
                            <input type="text" class="form-control" id="promoCode">
                        </div>
                        <div class="mb-3 col-4">
                            <label for="promoName" class="form-label">Tên khuyến mãi</label>
                            <input type="text" class="form-control" id="promoName">
                        </div>
                        <div class="mb-3 col-4">
                            <label for="discountValue" class="form-label">Giá trị giảm</label>
                            <input type="text" class="form-control" id="discountValue">
                        </div>
                    </div>

                    <div class="row">
                        <div class="mb-3 col-4">
                            <label for="status" class="form-label">Trạng thái</label>
                            <select class="form-select" id="status">
                                <option value="active">Active</option>
                                <option value="inactive">Inactive</option>
                            </select>
                        </div>
                        <div class="mb-3 col-4">
                            <label for="startDate" class="form-label">Ngày bắt đầu</label>
                            <input type="datetime-local" class="form-control" id="startDate">
                        </div>
                        <div class="mb-3 col-4">
                            <label for="endDate" class="form-label">Ngày kết thúc</label>
                            <input type="datetime-local" class="form-control" id="endDate"
                                   value="yyyy-MM-ddTHH:mm:ss">
                        </div>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button type="reset" class="btn btn-primary text-center">Làm mới</button>
                    </div>
                </form>
            </div>


            <!-- Promotion Management Table -->
            <div class="table-white">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h3>Quản lý khuyến mãi</h3>
                    <hr>
                    <button type="button" class="btn btn-primary"
                            data-bs-toggle="modal" data-bs-target="#exampleModal"> + Thêm
                    </button>
                </div>

                <%--                        Modal--%>
                <!-- Modal Thêm khuyến mãi -->
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Thêm Khuyến Mãi</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form action="/promotion/save" method="post">
                                    <input type="hidden" name="id" id="promoId">
                                    <div class="mb-3">
                                        <label for="promoCode" class="form-label">Mã khuyến mãi</label>
                                        <input type="text" class="form-control" id="promoCode1">
                                    </div>
                                    <div class="mb-3">
                                        <label for="promoName" class="form-label">Tên khuyến mãi</label>
                                        <input type="text" class="form-control" id="promoName1">
                                    </div>
                                    <div class="mb-3">
                                        <label for="promoName" class="form-label">Số lượng</label>
                                        <input type="text" class="form-control" id="number">
                                    </div>
                                    <div class="mb-3">
                                        <label for="promoValue1" class="form-label">Giá trị</label>
                                        <input type="text" class="form-control" id="promoValue1">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Hình thức</label>
                                        <select class="form-select" aria-label="Default select example">
                                            <option value="Tiền mặ1" selected>Tiền mặt</option>
                                            <option value="Phần trăm">Phần trăm</option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="startDate" class="form-label">Ngày bắt đầu</label>
                                        <input type="date" class="form-control" id="startDate1">
                                    </div>
                                    <div class="mb-3">
                                        <label for="endDate" class="form-label">Ngày kết thúc</label>
                                        <input type="date" class="form-control" id="endDate1">
                                    </div>
                                    <div class="modal-footer">
                                        <!-- Button to submit the form -->
                                        <button type="button" class="btn btn-primary">Thêm</button>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Mã khuyến mãi</th>
                        <th scope="col">Tên khuyến mãi</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Giá trị giảm</th>
                        <th scope="col">Ngày bắt đầu</th>
                        <th scope="col">Ngày kết thúc</th>
                        <th scope="col">Ngày cập nhật</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Sample Data Row -->

                    <c:forEach items="${pageKM.content}" var="khuyenMai" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${khuyenMai.ma}</td>
                            <td>${khuyenMai.ten}</td>
                            <td>${khuyenMai.soLuong}</td>
                            <td>${khuyenMai.giaTriGiam}%</td>
                            <td>${khuyenMai.ngayBatDau}</td>
                            <td>${khuyenMai.ngayKetThuc}</td>
                            <td>${khuyenMai.ngayCapNhat}</td>
                            <td>
                                <span class="badge rounded-pill ${khuyenMai.trangThai == 1 ? 'bg-success' : 'bg-danger'}">
                                        ${khuyenMai.trangThai == 1 ? 'Còn hạn' : 'Hết hạn'}
                                </span>
                            </td>
                            <td>
                                <button class="btn btn-warning btn-sm">Sửa</button>
                                <button class="btn btn-danger btn-sm">Xóa</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <%--                                Phân trang--%>
                <div class="pagination justify-content-end">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item ${pageKM.number == 0 ? 'disabled' : ''}">
<%--                                <c:if test="${pageKM.number > 0}">--%>
                                    <a class="page-link" href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
<%--                                </c:if>--%>
                            </li>
                            <li class="page-item"><a class="page-link ${pageKM.number == i ? 'active-page' : ''}" onclick="return false;" href="#">${pageKM.number + 1}</a></li>
                            <li class="page-item ${pageKM.number + 1 == pageKM.totalPages ? 'disabled' : ''}">
<%--                                <c:if test="${pageKM.number + 1 < pageKM.totalPages}">--%>
                                    <a class="page-link" href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
<%--                                </c:if>--%>
                            </li>
                        </ul>
                    </nav>
                </div>

            </div>
        </div>
    </div>

</div>

</body>
</html>