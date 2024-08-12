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
        .form-container {
            padding-top: 80px;
        }
         .navbar-nav {
            align-items: center;
            display: flex;
            justify-content: center;
            height: 100%;
        }
        h2{
        position:relative;
        left:500px;
        top:150px;
        margin-bottom:30px;
        width:700px;
        }
        .tableOut{
        position:relative;
        top:150px;
          margin-left: 60px;
          margin-right:100px;
          width: 90%;
        }
        .nav-link .badge {
                    position: absolute;
                    top: 0;
                    right: 0;
                    transform: translate(5%, 5%);
                    background-color: red;
                    color: white;
                    border-radius: 50%;
                    padding: 2px 5px;
                }
                .nav-item {
                    position: relative;
                }
                .notification-sidebar {
                    height: 100%;
                    width: 0;
                    position: fixed;
                    top: 0;
                    right: 0;
                    background-color: #f8f9fa;
                    overflow-x: hidden;
                    transition: 0.5s;
                    padding-top: 60px;
                    margin-top:25px;
                    box-shadow: -2px 0 5px rgba(0,0,0,0.3);
                }
                .notification-sidebar .notification-header {
                    display: flex;
                    justify-content: space-between;
                    padding: 10px;
                    background-color: #343a40;
                    color: #fff;
                }
                .notification-sidebar .notification-list {
                    padding: 10px;
                }
                .notification-item {
                    padding: 10px;
                    border-bottom: 1px solid #ddd;
                    cursor: pointer;
                }
                .notification-item.read {
                    background-color: #e9ecef;
                }
                .notification-sidebar .btn-close {
                    background: none;
                    border: none;
                    font-size: 1.5rem;
                    color: #fff;
                    cursor: pointer;
                }
                .notification-sidebar.open {
                    width: 300px;
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
                       <a class="nav-link text-light" href="index.jsp">Home</a>
                </li>
                <li>
                <a class="nav-link active text-light fs-5 fw-bold" aria-current="page" href="viewDetails">userDetails</a>
                </li>
                <li>
                <a class="nav-link active text-light fs-5 fw-bold" aria-current="page" href="viewComplaintDetails">ComplaintDetailsDetails</a>
                </li>
                  <li>
                <a class="nav-link active text-light fs-5 fw-bold" aria-current="page" href="addDepartmentAdmin">AddDepartmentAdmin</a>
                </li>
                <li class="nav-item">
                                    <a class="nav-link active text-light fs-5" href="javascript:void(0);" onclick="toggleSidebar()">
                                        <i class="fas fa-bell"></i>
                                        <span class="badge bg-danger" id="notificationCount">${sessionScope.notificationCount}</span>
                                    </a>
                    </li>
            </ul>
        </div>
    </nav>
    <!-- Notification Sidebar -->
    <div id="notificationSidebar" class="notification-sidebar">
        <div class="notification-header">
            <h4>Notifications</h4>
            <button class="btn-close" onclick="toggleSidebar()">&times;</button>
        </div>
        <div class="notification-list">
            <c:forEach var="notification" items="${sessionScope.notifications}">
                <div class="notification-item ${notification.adminRead ? 'adminRead' : ''}" onclick="openComplaint(${notification.id})">
                    <strong>${notification.complaintType}</strong>
                    <p><i>${notification.address}, ${notification.city}</i></p>
                    <div class="notification-date-time m-0">
                        <p>${sessionScope.formattedDates[notification.id]}</p>
                        <p>${sessionScope.formattedTimes[notification.id]}</p>
                    </div>
                </div>
            </c:forEach>
     </div>
    </div>
    <c:if test="${usersDTO.isEmpty()==false}">
<h2> View All User Details.. </h2>
<div class="tableOut">
        <span style="color:red">${msg} </span>
            <table class="table">
              <thead class="thead-light">
                <tr>
                 <th scope="col">Id</th>
                  <th scope="col">First Name</th>
                  <th scope="col">Last Name</th>
                  <th scope="col">Email</th>
                  <th scope="col">Phone number</th>
                  <th scope="col">Login count</th>
                  <th scope="col">Created By</th>
                  <th scope="col">Created Date</th>
                  <th scope="col">Updated By</th>
                  <th scope="col">Updated Date</th>

              </tr>
              </thead>
              <tbody>
              <c:forEach items="${usersDTO}" var="signup">
                <tr>
                  <td scope="row">${signup.getId()}</td>
                  <td>${signup.getFirstName()}</td>
                  <td>${signup.getLastName()}</td>
                  <td>${signup.getEmail()}</td>
                  <td>${signup.getPhoneNumber()}</td>
                  <td>${signup.getLogin_count()}</td>
                  <td>${signup.getCreatedBy()}</td>
                  <td>${signup.getCreatedDate()}</td>
                  <td>${signup.getUpdatedBy()}</td>
                  <td>${signup.getUpdatedDate()}</td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
</div>
</c:if>
<script>
function toggleSidebar() {
        var sidebar = document.getElementById('notificationSidebar');
        sidebar.classList.toggle('open');
}

function openComplaint(complaintId) {
        // Mark the notification as read
        fetch('markNotificationAsRead?notificationId=' + complaintId)
            .then(response => response.text())
            .then(result => {
                if (result === 'success') {
                    // Decrease the notification count by 1
                    var notificationCountElem = document.getElementById('notificationCount');
                    var count = parseInt(notificationCountElem.innerText);
                    if (count > 0) {
                        notificationCountElem.innerText = count - 1;
                    }

                    // Redirect to the complaint details page
                    window.location.href = 'viewNotificationComplaints?id=' + complaintId;
                } else {
                    console.error('Failed to mark notification as read');
                }
            })
            .catch(error => console.error('Error:', error));
    }

function fetchNotifications() {
            fetch('getNotification')
                .then(response => response.json())
                .then(data => {
                    // Update notification count
                    var notificationCountElem = document.getElementById('notificationCount');
                    notificationCountElem.innerText = data.notificationCount;

                    // Update notification list
                    var notificationListElem = document.getElementById('notificationList');
                    notificationListElem.innerHTML = '';
                    data.notifications.forEach(notification => {
                        var notificationItem = document.createElement('div');
                        notificationItem.className = 'notification-item ' + (notification.read ? 'read' : '');
                        notificationItem.onclick = function() {
                            openComplaint(notification.id);
                        };

                        var strongElem = document.createElement('strong');
                        strongElem.innerText = notification.type;

                        var pElem = document.createElement('p');
                        pElem.innerHTML = '<i>' + notification.area + ', ' + notification.address + '</i>';

                        var dateElem = document.createElement('p');
                        dateElem.innerText = notification.formattedDate;

                        var timeElem = document.createElement('p');
                        timeElem.innerText = notification.formattedTime;

                        var notificationDateTimeElem = document.createElement('div');
                        notificationDateTimeElem.className = 'notification-date-time m-0';
                        notificationDateTimeElem.appendChild(dateElem);
                        notificationDateTimeElem.appendChild(timeElem);

                        notificationItem.appendChild(strongElem);
                        notificationItem.appendChild(pElem);
                        notificationItem.appendChild(notificationDateTimeElem);

                        notificationListElem.appendChild(notificationItem);
                    });
                })
                .catch(error => console.error('Error fetching notifications:', error));
        }

        // Periodically fetch notifications every 30 seconds
        setInterval(fetchNotifications, 500);
</script>
</body>
</html>
