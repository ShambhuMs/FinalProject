 <%@ page isELIgnored="false" %>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AdminHome</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" crossorigin="anonymous"/> <!-- notification icon -->
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
        .card{
        margin-top:150px;
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
                <li class="nav-item" id="SignIn.jsp">
                       <a class="nav-link text-light" href="AdminHomePage.jsp">Home</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container mt-5 mb-5 d-flex justify-content-center">
        <div class="card px-5 py-4 bg-light w-100 shadow-lg p-3 mb-1 bg-white rounded" style="max-width: 600px;">
            <div class="card-body">
                <h5 class="card-title fs-2">Complaint Details</h5>
                <p><strong>Type:</strong> ${complaint.complaintType}</p>
                <p><strong>Area:</strong> ${complaint.state}</p>
                <p><strong>Country:</strong> ${complaint.country}</p>
                <p><strong>State:</strong> ${complaint.address}</p>
                <p><strong>City:</strong> ${complaint.city}</p>
                <p><strong>Description:</strong> ${complaint.description}</p>
                <p><strong>Status:</strong> ${complaint.complaintStatus}</p>
            </div>
        </div>
    </div>

</body>
</html>
