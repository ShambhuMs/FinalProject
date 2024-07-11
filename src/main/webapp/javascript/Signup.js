document.addEventListener('DOMContentLoaded', function() {
        const firstNameInput = document.getElementById('firstName');
        const lastNameInput = document.getElementById('lastName');
        const emailInput = document.getElementById('email');
        const phoneNumberInput = document.getElementById('phoneNumber');
        const signUpCheck = document.getElementById('signUpCheck');
        const submitBtn = document.getElementById('submitBtn');

        const errorFirstName = document.getElementById('error-firstName');
        const errorLastName = document.getElementById('error-lastName');
        const errorEmail = document.getElementById('error-email');
        const errorPhoneNumber = document.getElementById('error-phoneNumber');
        const errorSignUpCheck = document.getElementById('error-signUpCheck');

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
            if (lastName.length < 3 || lastName.length > 15 || /\d/.test(lastName)) {
                errorLastName.textContent = "Last name must be between 3 and 15 characters and not contain numbers.";
                errorLastName.style.display = 'block';
            } else {
                errorLastName.textContent = "";
                errorLastName.style.display = 'none';
            }
            validateForm();
        }

        /*function validateEmail() {
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
       }*/
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

        function validateCheckbox() {
            if (!signUpCheck.checked) {
                errorSignUpCheck.textContent = "You must agree to sign up for the newsletter.";
                errorSignUpCheck.style.display = 'block';
            } else {
                errorSignUpCheck.textContent = "";
                errorSignUpCheck.style.display = 'none';
            }
            validateForm();
        }

        function validateForm() {
            const isFirstNameValid = errorFirstName.textContent === "";
            const isLastNameValid = errorLastName.textContent === "";
            const isEmailValid = errorEmail.textContent === "";
            const isEmailCheck=errorEmail.innerHTML==="";
            const isPhoneNumberValid = errorPhoneNumber.textContent === "";
            const isCheckboxValid = signUpCheck.checked;

            submitBtn.disabled = !(isFirstNameValid && isLastNameValid && isEmailValid && isPhoneNumberValid &&
              isCheckboxValid);
        }

        firstNameInput.addEventListener('input', validateFirstName);
        lastNameInput.addEventListener('input', validateLastName);
        emailInput.addEventListener('input', validateEmail);
        phoneNumberInput.addEventListener('input', validatePhoneNumber);
        signUpCheck.addEventListener('change', validateCheckbox);

        validateForm();
    });