<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Landing</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <base href="http://localhost:8080/FinalProject/" />
    <style>
        body {
            background-color: #f0f2f5;
            margin: 0;
        }
        .signup-form {
            width: 360px;
            margin: 100px auto;
            font-family: Arial, sans-serif;
        }
        .signup-form form {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 20px 0px rgba(0,0,0,0.1);
        }
        .navbar {
            margin-bottom: 20px;
        }
        .logo-img {
            margin-right: 15px;
        }
        .form-container {
            padding-top: 80px;
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
        <a class="navbar-brand" href="#">
            <img src="/FinalProject/images/xworkz.jpg" width="140" height="70" alt="Xworkz" class="logo-img">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link text-light" href="Signup.jsp">Signup</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light" href="AdminSignIn.jsp">AdminSignIn</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light" href="SignIn.jsp">UserSignIn</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light" href="departmentAdmin/adminLogin">DepartmentAdminSignIn</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light" href="employee/adminLogin">EmployeeSignIn</a>
                </li>

            </ul>
        </div>
    </nav>
    <div class="signup-form form-container">
        <form id="contactForm" action="loan" method="post">
            <div class="mb-1 mt-3">
                <p class="fw-bold">
                    <h4>Tech Stack:</h4>
                    <span>JSP, Bootstrap CSS, JavaScript, Java, Spring MVC, Spring JPA/Hibernate, Ajax, MYSQL.</span>
                </p>
            </div>
            <div class="mb-1 mt-3">
                <p class="fw-bold">
                    <h4>Start Date:</h4>
                    <span>6-11-2024</span>
                </p>
            </div>
            <div class="mb-1 mt-3">
                <p class="fw-bold">
                    <h4>VCS:</h4>
                    <a href="https://github.com/ShambhuMs/">GitHub</a>
                </p>
            </div>
            <div class="mb-1 mt-3">
                <p class="fw-bold">
                    <h4>Description:</h4>
                    <span>Currently working on designing for accepting the complaints and department Admin will handling
                     the complaints and assigning complaint to Particular department employees.after complaint resolved
                     by employee he will confirming from the customer by an otp otherwise it cannot be consider as resolved and also
                     saving all details in database.</span>
                </p>
            </div>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
