<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
            padding-top: 80px;
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
        h2{
          position:relative;
          left:550px;
          margin-top:100px;
        }
         .modal-dialog {
                    max-width: 600px;
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
                    <a class="nav-link text-light" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light" href="employee/viewAssignedComplaints">ViewComplaints</a>
                 </li>
                 <li class="nav-item" id="ResetPasswordAnyTime">
                      <a class="nav-link text-light" href="employee/resetPasswordAnyTime">ResetPassword</a>
                  </li>
            </ul>
        </div>
    </nav>
    <div>
      <h2 style="color:red">${msg}</h2>
      <h2 style="color:green">${successMsg}</h2>
      <c:if test="${empDTO.isEmpty() == false}">
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
                                  <th scope="col">UserId</th>
                                  <th scope="col">ComplaintStatus</th>
                                  <th scope="col">Action</th>
                              </tr>
                          </thead>
                          <tbody>
                              <c:forEach items="${empDTO}" var="complaint">
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
                                <form action="#" method="post" class="status-form">
                                                                  <c:if test="${complaint.getComplaintStatus() != 'Resolved'}">
                                                                      <input type="hidden" name="id" value="${complaint.id}">
                                                                      <div class="input-group">
                                                                          <select class="form-select status-select" name="complaintStatus" data-complaint-id="${complaint.id}">
                                                                              <option value="0">Choose...</option>
                                                                              <option value="InProgress">In Progress</option>
                                                                              <option value="Resolved">Resolved</option>
                                                                              <option value="UnResolved">UnResolved</option>
                                                                          </select>
                                                                          <button type="button" class="btn btn-primary ms-2 update-btn" data-complaint-id="${complaint.id}">Update</button>
                                                                      </div>
                                                                  </c:if>
                                   </form>

                              </td>
                          </tr>
                            </c:forEach>
                          </tbody>
                      </table>
                  </div>
              </c:if>
     </div>

<!-- Modal for updating status -->
<div class="modal fade" id="statusModal" tabindex="-1" aria-labelledby="statusModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="statusModalLabel">Update Status</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="statusForm" action="employee/updateComplaintStatusByEmployee">
                    <input type="hidden" name="id" id="complaintId">
                    <input type="hidden" name="complaintStatus" id="complaintStatus">
                    <div class="mb-3">
                        <label for="comments" class="form-label">Comment</label>
                        <textarea class="form-control" id="comments" name="comment" rows="3"></textarea>
                    </div>
                    <div class="mb-3" id="otpSendSection" style="display: none;">
                        <button type="button" class="btn btn-secondary mt-2" id="sendOtpButton">Send OTP</button>
                    </div>
                    <div class="mb-3" id="otpEnterSection" style="display: none;">
                        <label for="otp" class="form-label">Enter OTP</label>
                        <input type="text" class="form-control" id="otp" name="password">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                     <c:if test="${not empty otpError}">
                                    <div class="alert alert-danger mt-3">${otpError}</div>
                     </c:if>
                </form>
            </div>
        </div>
    </div>
</div>
                <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
              <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
<script>
document.querySelectorAll('.update-btn').forEach(function(button) {
       button.addEventListener('click', function() {
           const complaintId = this.dataset.complaintId;
           const form = this.closest('form');
           const status = form.querySelector('.status-select').value;
           const modal = new bootstrap.Modal(document.getElementById('statusModal'), {
               backdrop: 'static',
               keyboard: false
           });

           document.getElementById('complaintId').value = complaintId;
           document.getElementById('complaintStatus').value = status;

           if (status === 'Resolved') {
               document.getElementById('otpSendSection').style.display = 'block';
           } else {
               document.getElementById('otpSendSection').style.display = 'none';
               document.getElementById('otpEnterSection').style.display = 'none';
           }

           modal.show();
       });
   });
 document.getElementById('sendOtpButton').addEventListener('click', function() {
         const complaintId = document.getElementById('complaintId').value;
         const xhr = new XMLHttpRequest();
         xhr.open("POST", "sendOtp", true);
         xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
         xhr.onreadystatechange = function () {
             if (xhr.readyState === 4 && xhr.status === 200) {
                 document.getElementById('otpSendSection').style.display = 'none';
                 document.getElementById('otpEnterSection').style.display = 'block';
             } else if (xhr.readyState === 4) {
                 alert("Failed to send OTP.");
             }
         };
         xhr.send("id=" + complaintId);
     });
</script>
</body>
</html>

