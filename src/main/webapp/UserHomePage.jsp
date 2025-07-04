<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UserLanding</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <base href="${pageContext.request.contextPath}" />
    <style>
        body {
            background-color: #f0f2f5;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            flex-direction: column;
        }

        .navbar {
            width: 100%;
            position: fixed;
            top: 0;
            left: 0;
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
            height: 100%;
            width: 1200px;
        }
        .btn {
            width: 160px;
            position: relative;
            left: 500px;
        }
        #profileImage {
            position: relative;
            left: 900px;
        }
        #RaiseComplaint {
            position: relative;
            left: 850px;
        }
        #ViewComplaint{
           position: relative;
            left: 500px;
        }

        .typewriter-container {
            font-size: 1.5rem;
            font-weight: bold;
            white-space: pre-wrap;
            color: red;
            display: inline-block;
            overflow: hidden;
            word-wrap: break-word;
            text-align: center;
        }

        .cursor {
            display: inline-block;
            width: 8px;
            height: 20px;
            background-color: red;
            margin-left: 5px;
            animation: blink 0.6s step-end infinite;
        }

        @keyframes blink {
            50% { opacity: 0; }
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
                <li class="nav-item" id="ResetPasswordAnyTime">
                    <a class="nav-link text-light" href="ResetPasswordAnyTime.jsp">ResetPassword</a>
                </li>
                <li class="nav-item" id="EditProfile">
                    <a class="nav-link text-light" href="EditProfile.jsp">EditProfile</a>
                </li>
                <li class="nav-item" id="RaiseComplaint">
                    <a class="nav-link text-light" href="RaiseComplaint.jsp">RaiseComplaint</a>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath}${sessionScope.profileDTO}"
                        width="70" height="70" value="" class="rounded-circle profile-image" id="profileImage">
                </li>
                <li class="nav-item" id="ViewComplaint">
                           <a class="nav-link text-light" href="viewComplaints">ViewComplaint</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="typewriter-container" id="typewriter"></div><span class="cursor" id="cursor"></span>

    <script>
        const text = "Welcome to the Issue Management System - Track, Report, and Resolve Issues Efficiently!";
        const speed = 50; // Typing speed in milliseconds
        let i = 0;

        function typeWriter() {
            if (i < text.length) {
                document.getElementById("typewriter").innerHTML += text.charAt(i);
                i++;
                setTimeout(typeWriter, speed);
            } else {
                document.getElementById("cursor").style.display = "none"; // Hide cursor after typing
            }
        }

        typeWriter();
    </script>
</body>
</html>