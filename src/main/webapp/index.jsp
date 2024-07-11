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
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item" id="img">
                    <img src="/FinalProject/images/xworkz.jpg" width="140" height="70" alt="Xworkz" class="logo-img">
                </li>
                <li class="nav-item" id="signup">
                    <a class="nav-link text-light" href="Signup.jsp">Signup</a>
                </li>
                <li class="nav-item" id="AdminSignIn">
                    <a class="nav-link text-light" href="AdminSignIn.jsp">AdminSignIn</a>
                </li>
                <li class="nav-item" id="SignIn.jsp">
                       <a class="nav-link text-light" href="SignIn.jsp">SignIn</a>
                </li>
                <li class="nav-item" id="SignIn.jsp">
                       <a class="nav-link text-light" href="departmentAdmin/adminLogin">DepartmentAdminSignIn</a>
                 </li>
                 <li class="nav-item" id="SignIn.jsp">
                       <a class="nav-link text-light" href="ResetPasswordAnyTime.jsp">ResetPasswordAnyTime</a>
                 </li>
                 <li class="nav-item" id="EditProfile">
                       <a class="nav-link text-light" href="DepartmentAdminHome.jsp">DepartmentAdminHome</a>
                 </li>
                 <li class="nav-item" id=" AdminHomePage">
                       <a class="nav-link text-light" href="AdminHomePage.jsp">AdminHomePage</a>
                 </li>
                 <li class="nav-item" id=" FindByEmail">
                       <a class="nav-link text-light" href="UserHomePage.jsp">UserHomePage.jsp</a>
                 </li>
            </ul>
        </div>
    </nav>
    <div class="signup-form form-container">
        <form id="contactForm" action="loan" method="post">
                    <div class="mb-1 mt-3">
                        <p class="fw-bold">
                            <h4>Tech Stack:</h4>
                            <span>JSP, Bootstrap CSS, JavaScript, Java, Spring, Spring JPA/Hibernate.</span>
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
                            <span>Currently working on designing Landing Page and Sign Up page. Saving data to the database.</span>
                        </p>
                    </div>

        </form>
    </div>
</body>
</html>
