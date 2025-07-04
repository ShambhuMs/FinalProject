<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Landing</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <base href="${pageContext.request.contextPath}" />
    <style>
        body {
            background-color: #f8f9fa;
            margin: 0;
            font-family: Arial, sans-serif;
        }
        .navbar {
            padding: 15px 20px;
        }
        .navbar-brand img {
            border-radius: 10px;
        }
        .signup-form {
            width: 450px;
            margin: 120px auto;
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h4 {
            color: #343a40;
            margin-bottom: 10px;
            font-weight: bold;
        }
        span, a {
            color: #6c757d;
        }
        .navbar-nav .nav-link {
            font-size: 16px;
            padding: 10px 15px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" href="#">
            <img src="${pageContext.request.contextPath}/images/xworkz.jpg" width="140" height="70" alt="Xworkz">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link text-light" href="Signup.jsp">Signup</a></li>
                <li class="nav-item"><a class="nav-link text-light" href="AdminSignIn.jsp">AdminSignIn</a></li>
                <li class="nav-item"><a class="nav-link text-light" href="SignIn.jsp">UserSignIn</a></li>
                <li class="nav-item"><a class="nav-link text-light" href="departmentAdmin/adminLogin">DepartmentAdminSignIn</a></li>
                <li class="nav-item"><a class="nav-link text-light" href="employee/adminLogin">EmployeeSignIn</a></li>
            </ul>
        </div>
    </nav>
    <div class="signup-form">
        <form>
            <div>
                <h4>Tech Stack:</h4>
                <span>HTML, Bootstrap CSS, JavaScript, Java, Spring MVC, Spring JPA/Hibernate, Ajax, MySQL.</span>
            </div>
            <hr>
            <div>
                <h4>Start Date:</h4>
                <span>06-03-2024</span>
            </div>
            <hr>
            <div>
                <h4>VCS:</h4>
                <a href="https://github.com/ShambhuMs/FinalProject" target="_blank">GitHub</a>
            </div>
            <hr>
            <div>
                <h4>Description:</h4>
                <span>The Issue Management System is a web application designed to efficiently track, manage, and
                resolve issues within an organization. Built using Spring Framework, Hibernate/JPA, MySQL, AJAX, and
                Spring Security, this system allows users to report issues, assign them to responsible personnel,
                update statuses, and maintain a clear record of issue resolutions.</span>
            </div>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>