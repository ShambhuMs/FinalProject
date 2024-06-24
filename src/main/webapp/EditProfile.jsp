<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f0f2f5;
        }
        .signup-form {
            width: 360px;
            margin: 100px auto;
            margin-top: 20px;
            font-family: Arial, sans-serif;
        }
        .signup-form form {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 20px 0px rgba(0,0,0,0.1);
        }
        .signup-form h2 {
            margin: 0 0 15px;
            text-align: center;
        }
        .form-control, .btn {
            min-height: 38px;
            border-radius: 2px;
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
            left: 1100px;
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
        #profileImage{
           position: relative;
           left:1120px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item" id="img">
                    <img src="/FinalProject/images/xworkz.jpg" width="140" height="70" alt="Xworkz" class="logo-img">
                </li>
                <li class="nav-item" id="home">
                    <a class="nav-link text-light" href="UserHomePage.jsp">UserHomePage</a>
                </li>
                <img src="${pageContext.request.contextPath}${sessionScope.profileDTO}" width="70" height="70" value="" class="rounded-circle profile-image" id="profileImage"/>

            </ul>
        </div>
    </nav>
    <div class="signup-form">
        <form id="profilePic" action="editProfile" method="post" enctype="multipart/form-data">
            <span style="color:green">${successMessage}</span>
            <span style="color:red">${failedMessage}</span>
            <h2>Edit Profile</h2>
            <div class="input-group">
                <div class="custom-file">
                    <input type="file" class="custom-file-input" id="inputGroupFile04" name="file" onchange="updateFileName(this)">
                    <label class="custom-file-label" for="inputGroupFile04" id="fileLabel">Choose file</label>
                </div>
            </div>
            <div class="form-group">
                <label for="firstName">First Name</label>
                <input type="text" class="form-control" placeholder="Enter First Name" id="firstName" name="firstName" value="${sessionScope.dto.firstName}" required>
                <div id="error-firstName" class="error"></div>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <input type="text" class="form-control" placeholder="Enter Last Name" id="lastName" name="lastName" value="${sessionScope.dto.lastName}" required>
                <div id="error-lastName" class="error"></div>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email"  class="form-control" placeholder="Enter email" id="email" name="email" value="${sessionScope.dto.email}" readonly>
                <div id="error-email" class="error"></div>
                <div><span style="color:red">${failedEmailMsg}</span></div>
            </div>
            <div class="form-group">
                <label for="phoneNumber">Phone Number</label>
                <input type="text" class="form-control" placeholder="Enter Phone Number" id="phoneNumber" name="phoneNumber" value="${sessionScope.dto.phoneNumber}" required>
                <div id="error-phoneNumber" class="error"></div>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary btn-block" id="submitBtn" value="Submit" name="submit" disabled />
            </div>
        </form>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const firstNameInput = document.getElementById('firstName');
            const lastNameInput = document.getElementById('lastName');
            const emailInput = document.getElementById('email');
            const phoneNumberInput = document.getElementById('phoneNumber');
            const fileInput = document.getElementById('inputGroupFile04');
            const submitBtn = document.getElementById('submitBtn');

            const errorFirstName = document.getElementById('error-firstName');
            const errorLastName = document.getElementById('error-lastName');
            const errorEmail = document.getElementById('error-email');
            const errorPhoneNumber = document.getElementById('error-phoneNumber');

            function updateFileName(input) {
                const fileName = input.files[0].name;
                const label = document.getElementById('fileLabel');
                label.innerHTML = fileName;
                validateForm(); // Revalidate form when file is chosen
            }

            function validateFirstName() {
                const firstName = firstNameInput.value;
                if (firstName.length < 3 || firstName.length > 15 || /\d/.test(firstName)) {
                    errorFirstName.textContent = "First name must be between 3 and 15 characters and not contain numbers.";
                    errorFirstName.style.display = 'block';
                } else {
                    errorFirstName.textContent = "";
                    errorFirstName.style.display = 'none';
                }
                validateForm();
            }

            function validateLastName() {
                const lastName = lastNameInput.value;
                if (lastName.length < 1 || lastName.length > 15 || /\d/.test(lastName)) {
                    errorLastName.textContent = "Last name must be between 1 and 15 characters and not contain numbers.";
                    errorLastName.style.display = 'block';
                } else {
                    errorLastName.textContent = "";
                    errorLastName.style.display = 'none';
                }
                validateForm();
            }

            function validateEmail() {
                const email = emailInput.value;
                const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
                if (!emailPattern.test(email)) {
                    errorEmail.textContent = "Please enter a valid email address.";
                    errorEmail.style.display = 'block';
                } else {
                    errorEmail.textContent = "";
                    errorEmail.style.display = 'none';
                }
                validateForm();
            }

            function validatePhoneNumber() {
                const phoneNumber = phoneNumberInput.value;
                if (!/^\d{10}$/.test(phoneNumber)) {
                    errorPhoneNumber.textContent = "Phone number must be 10 digits.";
                    errorPhoneNumber.style.display = 'block';
                } else {
                    errorPhoneNumber.textContent = "";
                    errorPhoneNumber.style.display = 'none';
                }
                validateForm();
            }

            function validateForm() {
                const isFirstNameValid = errorFirstName.textContent === "";
                const isLastNameValid = errorLastName.textContent === "";
                const isEmailValid = errorEmail.textContent === "";
                const isPhoneNumberValid = errorPhoneNumber.textContent === "";
                const isFileValid = fileInput.files.length > 0; // Check if a file is selected

                submitBtn.disabled = !(isFirstNameValid && isLastNameValid && isEmailValid && isPhoneNumberValid && isFileValid);
            }

            firstNameInput.addEventListener('input', validateFirstName);
            lastNameInput.addEventListener('input', validateLastName);
            emailInput.addEventListener('input', validateEmail);
            phoneNumberInput.addEventListener('input', validatePhoneNumber);
            fileInput.addEventListener('change', function() {
                updateFileName(this);
                validateForm();
            });

            validateForm(); // Initial validation on page load
        });
    </script>
</body>
</html>
