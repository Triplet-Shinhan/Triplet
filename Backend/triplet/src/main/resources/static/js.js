function submitForm() {
    var form = document.getElementById("signupForm");
    var formData = {
        name: form.elements.name.value,
        birth: form.elements.birth.value,
        email: form.elements.email.value,
        password: form.elements.password.value,
        phoneNum: form.elements.phoneNum.value,
        accountNum: form.elements.accountNum.value
    };

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/users/signup", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var response = xhr.responseText;
                // 성공적으로 서버 응답을 받았을 때 처리
            } else {
                // 요청에 실패한 경우 처리
            }
        }
    };
    xhr.send(JSON.stringify(formData));
}
