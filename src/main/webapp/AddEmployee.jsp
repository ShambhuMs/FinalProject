<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Register</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script>
             const contextPath = "${pageContext.request.contextPath}";
     </script>
    <script src="${pageContext.request.contextPath}/script/AddEmployee.js"></script>
    <base href="${pageContext.request.contextPath}/">
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
        h2 {
            color: black;
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
                    <a class="nav-link text-light" href="DepartmentAdminHome.jsp">Home</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="employee-form">
        <form action="departmentAdmin/addEmployee" method="post">
            <span style="color:red">
                <c:forEach items="${errorMessage}" var="error">
                    ${error.defaultMessage}
                </c:forEach>
            </span>
            <h2>Employee Register Form</h2>
            <span style="color:green">${msg}</span>
            <span style="color:red">${errorMsg}</span>
            <div class="form-group">
                <label for="employeeName">Name</label>
                <input type="text" class="form-control" placeholder="Enter Name" id="employeeName" name="employeeName" value="${employeeDTO.employeeName}" required>
                <div id="error-employeeName" class="error"></div>
            </div>
            <div class="form-group">
                <label for="employeeEmail">Email</label>
                <input type="text" class="form-control" placeholder="Enter email" id="employeeEmail" name="email" value="${employeeDTO.employeeEmail}" required>
                <div id="error-employeeEmail" class="error"></div>
                <div><span style="color:red"></span></div>
            </div>
            <div class="form-group">
                <label for="employeePhoneNumber">Phone Number</label>
                <input type="text" class="form-control" placeholder="Enter Phone Number" id="employeePhoneNumber" name="employeePhoneNumber" value="${employeeDTO.employeePhoneNumber}" required>
                <div id="error-employeePhoneNumber" class="error"></div>
                <div><span style="color:red"></span></div>
            </div>
            <div class="form-group">
                <label for="employeeDepartment">Employee Department</label>
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
