<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>viewComplaint</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <base href="${pageContext.request.contextPath}" />
    <style>
        body {
            background-color: #f0f2f5;
            margin: 0;
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

        h2 {
            position: relative;
            left: 400px;
            top: 150px;
            width: 700px;
        }

        h3 {
            position: relative;
            left: 450px;
            top: 200px;
            width: 700px;
        }

        .tableOut {
            position: relative;
            top: 180px;
            margin-left: 60px;
            margin-right: 100px;
            width: 90%;
        }

        .btn.dropdown-toggle {
            background-color: black;
            border-color: black;
            color: white;
        }

        .dropdown-menu {
            background-color: #343a40;
        }

        .dropdown-item {
            color: white;
        }

        .dropdown-item:hover {
            background-color: #23272b;
            color: white;
        }
         #profileImage {
            position: relative;
            left: 1050px;
        }
        #dropdownMenuButton {
            position: relative;
            left: 950px;
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
                <li class="nav-item" id="UserHomePage">
                    <a class="nav-link text-light" href="UserHomePage.jsp">Home</a>
                </li>
                <li class="nav-item dropdown">
                    <button class="btn text-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ViewComplaints
                    </button>
                    <div class="dropdown-menu bg-dark" aria-labelledby="dropdownMenuButton">
                        <form id="viewComplaint" action="viewComplaints" method="post">
                            <button class="dropdown-item text-light" type="submit" name="viewComplaints" value="Active">Active</button>
                            <button class="dropdown-item text-light" type="submit" name="viewComplaints" value="Resolved">Resolved</button>
                            <button class="dropdown-item text-light" type="submit" name="viewComplaints" value="UnResolved">UnResolved</button>
                        </form>
                    </div>
                </li>
                 <li>
                    <img src="${pageContext.request.contextPath}${sessionScope.profileDTO}"
                        width="70" height="70" value="" class="rounded-circle profile-image" id="profileImage"> </a>
                </li>
            </ul>
        </div>
    </nav>
    <h2>View Complaint Details:</h2>
    <h3 style="color:red">${msg}</h3>
    <c:if test="${complaintDto.isEmpty() == false}">
        <div class="tableOut">
            <table class="table">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">ComplaintType</th>
                        <th scope="col">Country</th>
                        <th scope="col">State</th>
                        <th scope="col">City</th>
                        <th scope="col">Address</th>
                        <th scope="col">Description</th>
                   <%--      <c:forEach items="${complaintDto}" var="complaintDto">
                        <c:if test="${complaintDto.getComplaintStatus() == 'Active'}">   --%>
                            <th scope="col">Edit</th>
                    <%--     </c:if>
                       </c:forEach>  --%>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${complaintDto}" var="complaintDto">
                        <tr>
                            <td scope="row">${complaintDto.getId()}</td>
                            <td>${complaintDto.getComplaintType()}</td>
                            <td>${complaintDto.getCountry()}</td>
                            <td>${complaintDto.getState()}</td>
                            <td>${complaintDto.getCity()}</td>
                            <td>${complaintDto.getAddress()}</td>
                            <td>${complaintDto.getDescription()}</td>
                            <c:if test="${complaintDto.getComplaintStatus() == 'Active'}">
                                <td><a href="findByComplaintId?id=${complaintDto.id}&edit=edit">Edit</a></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>
