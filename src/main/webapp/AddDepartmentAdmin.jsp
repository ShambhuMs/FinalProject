<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Register</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="/FinalProject/script/AddDepartmentAdmin.js"></script>
    <base href="http://localhost:8080/FinalProject/">
    <style>
        body {
            background-color: #f0f2f5;
        }
        .employee-form {
            width: 360px;
            margin: 100px auto;
            font-family: Arial, sans-serif;
        }
        .employee-form form {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 20px 0px rgba(0,0,0,0.1);
        }
        .employee-form h2 {
            margin: 0 0 15px;
            text-align: center;
        }
        .form-control, .form-select, .btn {
            min-height: 38px;
            border-radius: 2px;
        }
        .form-select {
            width: 100%;
        }
        .btn {
            font-size: 15px;
            font-weight: bold;
        }
        .error {
            color: red;
            font-size: 12px;
            display: none;
        }
        #home {
            position: relative;
            left: 1200px;
        }
        h3 {
            color: black;
            text-align: center;
        }
        .navbar-nav {
            align-items: center;
            display: flex;
            justify-content: center;
            height: 100%;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item" id="img">
                    <img src="/FinalProject/images/xworkz.jpg" width="140" height="70" alt="Xworkz" class="logo-img">
                </li>
                <li class="nav-item" id="home">
                    <a class="nav-link text-light" href="AdminHomePage.jsp">Home</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="employee-form">
        <form action="addDepartmentAdmin" method="post">
            <span style="color:red">
                <c:forEach items="${errorMessage}" var="error">
                    ${error.defaultMessage}
                </c:forEach>
            </span>
            <h3 >Department-Admin Register Form</h3>
            <span style="color:green">${msg}</span>
            <span style="color:red">${errorMsg}</span>
            <div class="form-group">
                <label for="depAdminName">Name</label>
                <input type="text" class="form-control" placeholder="Enter Name" id="depAdminName"
                name="departmentAdminName" value="${departmentAdminDto.departmentAdminName}" required>
                <div id="error-depAdminName" class="error"></div>
            </div>
            <div class="form-group">
                <label for="departmentAdminEmail">Email</label>
                <input type="email" class="form-control" placeholder="Enter Email" id="departmentAdminEmail"
                name="email" value="${departmentAdminDto.email}" required>
                <div id="error-depAdminEmail" class="error"></div>
               <div><span style="color:red"></span></div>
            </div>
            <div class="form-group">
                <label for="departmentAdminPhoneNumber">Phone Number</label>
                <input type="text" class="form-control" placeholder="Enter Phone Number" id="departmentAdminPhoneNumber"
                 name="departmentAdminPhoneNumber" value="${departmentAdminDto.departmentAdminPhoneNumber}" required>
                <div id="error-departmentAdminPhoneNumber" class="error"></div>
                <div><span style="color:red"></span></div>
            </div>
            <div class="form-group">
                <label for="employeeDepartment">Department</label>
                <select class="form-select" id="departmentType" name="departmentId" required>
                    <option value="" ${selectedType == null ? 'selected' : ''}>Choose...</option>
                    <c:forEach items="${department}" var="dep">
                        <option value="${dep.department_id}" ${selectedType == 'dep.department_id' ? 'selected' : ''}>${dep.department_type}</option>
                    </c:forEach>
                </select>
                <div id="error-departmentType" class="error"></div>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary btn-block" id="submitBtn" value="Submit" name="submit" disabled/>
            </div>
        </form>
    </div>
</body>
</html>
