document.addEventListener('DOMContentLoaded', function() {
    const nameInput = document.getElementById('employeeName');
    const emailInput = document.getElementById('employeeEmail');
    const passwordInput = document.getElementById('employeePassword');
    const phoneNumberInput = document.getElementById('employeePhoneNumber');
    const submitBtn = document.getElementById('submitBtn');

    const errorName = document.getElementById('error-employeeName');
    const errorEmail = document.getElementById('error-employeeEmail');
    const errorPassword = document.getElementById('error-employeePassword');
    const errorPhoneNumber = document.getElementById('error-employeePhoneNumber');


    function validateName() {
        const name = nameInput.value;
        if (name.length < 3 || name.length > 30 || /\d/.test(name)) {
            errorName.textContent = "Name must be between 3 and 30 characters and not contain numbers.";
            errorName.style.display = 'block';
        } else {
            errorName.textContent = "";
            errorName.style.display = 'none';
        }
        validateForm();
    }

    function validateEmail() {
        const email = emailInput.value.trim();
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

        if (!emailPattern.test(email)) {
            errorEmail.textContent = "Please enter a valid email address.";
            errorEmail.style.display = 'block';
            validateForm();
            return;
        }

        // AJAX request to check if email already exists
        let xhr = new XMLHttpRequest();
        xhr.open("GET", `http://localhost:8080/FinalProject/validateEmail?email=${encodeURIComponent(email)}`, true);
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    let responseText = xhr.responseText.trim();
                    if (responseText !== "") {
                        errorEmail.textContent = responseText; // Display custom error message from server
                        errorEmail.style.display = 'block';
                    } else {
                        errorEmail.textContent = "";
                        errorEmail.style.display = 'none';
                    }
                } else {
                    console.error('Error validating email:', xhr.statusText);
                }
                validateForm();
            }
        };

        xhr.send();
    }

    function validatePassword() {
        const password = passwordInput.value;
        if (password.length < 6) {
            errorPassword.textContent = "Password must be at least 6 characters long.";
            errorPassword.style.display = 'block';
        } else {
            errorPassword.textContent = "";
            errorPassword.style.display = 'none';
        }
        validateForm();
    }

    function validatePhoneNumber() {
        const phoneNumber = phoneNumberInput.value.trim();
        if (!/^\d{10}$/.test(phoneNumber)) {
            errorPhoneNumber.textContent = "Phone number must be 10 digits.";
            errorPhoneNumber.style.display = 'block';
            validateForm();
            return;
        }

        // AJAX request to check if phone number already exists
        let xhr = new XMLHttpRequest();
        xhr.open("GET", `http://localhost:8080/FinalProject/validatePhone?phoneNumber=${encodeURIComponent(phoneNumber)}`, true);
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    let phoneExists = xhr.responseText.trim();
                    if (phoneExists) {
                        errorPhoneNumber.textContent = "Phone number is already registered. Please use a different phone number.";
                        errorPhoneNumber.style.display = 'block';
                    } else {
                        errorPhoneNumber.textContent = "";
                        errorPhoneNumber.style.display = 'none';
                    }
                } else {
                    console.error('Error validating phone number:', xhr.statusText);
                }
                validateForm();
            }
        };

        xhr.send();
    }



    function validateForm() {
        const isNameValid = errorName.textContent === "";
        const isEmailValid = errorEmail.textContent === "";
        const isPasswordValid = errorPassword.textContent === "";
        const isPhoneNumberValid = errorPhoneNumber.textContent === "";

        submitBtn.disabled = !(isNameValid && isEmailValid && isPasswordValid && isPhoneNumberValid);
    }

    nameInput.addEventListener('input', validateName);
    emailInput.addEventListener('input', validateEmail);
    passwordInput.addEventListener('input', validatePassword);
    phoneNumberInput.addEventListener('input', validatePhoneNumber);

    validateForm();
});
