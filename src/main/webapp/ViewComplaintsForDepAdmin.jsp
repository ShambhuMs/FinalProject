<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ViewComplaintDetails</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <base href="http://localhost:8080/FinalProject/">
    <style>
        body {
            background-color: #f0f2f5;
            margin: 0;
            padding-top: 80px; /* Adjust this to match the height of your navbar */
        }
        .complaint-form-wrapper {
            position:relative;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            padding: 20px;
            margin:-30px;
        }
        .complaint-form {
             position:relative;
             margin-top:60px;
             left :500px;
             width: 400px;
            padding: 40px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0px 0px 20px 0px rgba(0,0,0,0.1);
            font-family: Arial, sans-serif;
            margin-bottom: 10px;
        }
        .complaint-form h2 {
            text-align: center;
            color: #333;
        }
        .complaint-form .form-group {
            margin-bottom: 20px;
        }
        .complaint-form label {
            font-weight: bold;
        }
        .complaint-form input,
        .complaint-form select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        .complaint-form button {
            width: 100%;
            padding: 10px;
            background: #4267b2;
            border: none;
            color: #fff;
            font-size: 18px;
            border-radius: 5px;
            cursor: pointer;
        }
        .complaint-form button:hover {
            background: #365899;
        }
        .navbar {
            margin-bottom: 20px;
        }
        .logo-img {
            margin-right: 15px;
        }
        .navbar-nav {
            align-items: center;
            display: flex;
            justify-content: center;
            height: 100%;
        }
        .tableOut {
            width: 80%;
            margin: 40px auto;
        }
        h3{
        position:relative;
         left:550px;
         margin-top:50px;
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
                <li class="nav-item" id="AdminHomePage">
                    <a class="nav-link text-light" href="DepartmentAdminHome.jsp">Home</a>
                </li>
            </ul>
        </div>
    </nav>
        <div class="complaint-form">
            <form action="departmentAdmin/findByComplaintType" method="post">
                <h2>Fetch</h2>
                <span style="color:red">${msg}</span>
                <div class="form-group">
                    <label for="complaintType">Complaint Type</label>
                    <select class="form-control" id="complaintType" name="complaintType">
                        <option value=""  >Choose...</option>
                        <option value="Drainage Problem" >Drainage Problem</option>
                        <option value="Electric Problem" >Electric Problem</option>
                        <option value="Plumber Problem"  >Plumber Problem</option>
                        <option value="Wastage Problem"  >Wastage Problem</option>
                        <option value="Water Problem"    >Water Problem</option>
                    </select>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" id="submitBtn" value="Submit" name="submit">Submit</button>
                </div>
            </form>
        </div>
</div>
   <h3 style="color:red">${errorMsg}</h3>
   <h3 style="color:green">${updateMsg}</h3>
        <c:if test="${dto.isEmpty() == false}">
            <div class="tableOut">
                <span style="color:green">${updateMsg}</span>
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
                            <th scope="col">UserId</th>
                            <th scope="col">ComplaintStatus</th>
                            <th scope="col">AssignEmployee</th>
                            <th scope="col">Action</th>
                            <th scope="col">Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${dto}" var="complaint">
                        <span style="color:red">${errorMsg}</span>
                            <tr>
                                <td scope="row">${complaint.getId()}</td>
                                <td>${complaint.getComplaintType()}</td>
                                <td>${complaint.getCountry()}</td>
                                <td>${complaint.getState()}</td>
                                <td>${complaint.getCity()}</td>
                                <td>${complaint.getAddress()}</td>
                                <td>${complaint.getDescription()}</td>
                                <td>${complaint.getUserId()}</td>
                                <td>${complaint.getComplaintStatus()}</td>
                                <td class="d-none d-md-table-cell">
                   <c:if test="${complaint.getComplaintStatus() != 'Resolved'}">
                        <form action="departmentAdmin/updateStatusOrAssign" method="post">
                            <input type="hidden" name="id" value="${complaint.id}" >
                            <div class="input-group">
                            <select class="form-select" name="employeeId">
                                  <c:choose>
                                      <c:when test="${fn:length(employeesByDepartment[complaint.id]) > 0}">
                                          <option value="">Choose...</option>
                                          <c:forEach items="${employeesByDepartment[complaint.id]}" var="employee">
                                              <option value="${employee.employeeId}">${employee.employeeName}</option>
                                          </c:forEach>
                                      </c:when>
                                      <c:otherwise>
                                          <option value="">No employee</option>
                                      </c:otherwise>
                                  </c:choose>
                              </select>
                          <td class="d-none d-md-table-cell">
                                <div class="input-group">
                                <select class="form-select" id="status" name="complaintStatus">
                                    <option value="" ${selectedType == null ? 'selected' : ''}>Choose...</option>
                                    <option value="Pending" ${selectedType == 'Pending' ? 'selected' : ''}>Pending</option>
                                    <option value="InProgress" ${selectedType == 'InProgress' ? 'selected' : ''}>InProgress</option>
                                </select>
                                <td>
                                   <button type="submit" class="btn btn-primary">Update</button>
                                </td>
                                 </div>
                               </form>
                        </td>
                    </c:if>
                    </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>
