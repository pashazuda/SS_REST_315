//Таблица всех пользователей
const url_admin = 'http://localhost:8080/api/admin';

const renderTable = document.getElementById("allUsersTable");
const addForm = document.getElementById("add-form");

const renderPosts = (users) => {
    let temp = '';
    users.forEach((user) => {
        temp += `<tr>
                                <td class="text-center">${user.id}</td>
                                <td class="text-center" id=${'firstName' + user.id}>${user.firstName}</td>
                                <td class="text-center" id=${'lastName' + user.id}>${user.lastName}</td>
                                <td class="text-center" id=${'age' + user.id}>${user.age}</td>
                                <td class="text-center" id=${'email' + user.id}>${user.email}</td>
                                <td class="text-center" id=${'role' + user.id}>${user.rolesToString}</td>
                                <td class="text-center">
                                <button class="btn btn-primary" type="button"
                                data-toggle="modal" data-target="#modalEdit"
                                onclick="editModal(${user.id})">Edit</button></td>
                                <td class="text-center">
                                <button class="btn btn-danger" type="button"
                                data-toggle="modal" data-target="#modalDelete"
                                onclick="deleteModal(${user.id})">Delete</button></td>
                                </tr>
                                `
    })
    renderTable.innerHTML = temp;
}

function getAllUsers() {
    fetch(url_admin)
        .then(res => res.json())
        .then(data => {
            renderPosts(data)
        })
}

getAllUsers()

// Добавление пользователя
const url_create_user = 'http://localhost:8080/api/admin/create-user';
addForm.addEventListener('submit', (e) => {
    e.preventDefault(); // Отмена действия браузера по умолчанию (перезагрузка страницы)
    let firstValue1 = document.getElementById("firstName");
    let firstValue = firstValue1.value;
    let lastValue1 = document.getElementById("lastName");
    let lastValue = lastValue1.value;
    let ageValue1 = document.getElementById("age");
    let ageValue = ageValue1.value;
    let emailValue1 = document.getElementById("email");
    let emailValue = emailValue1.value;
    let passwordValue1 = document.getElementById("password");
    let passwordValue = passwordValue1.value;
    let roles = getRoles(Array.from(document.getElementById("addRoles").selectedOptions).map(role => role.value));
    let newUser = {
        firstName: firstValue,
        lastName: lastValue,
        age: ageValue,
        email: emailValue,
        password: passwordValue,
        roles: roles
    }
    fetch(url_create_user, {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(newUser)
    })
        .then(data => {
        const dataArr = [];
        dataArr.push(data);
        getAllUsers(data);
    }).then(() => {
        firstValue1.value = ""
        lastValue1.value = ""
        ageValue1.value = ""
        emailValue1.value = ""
        passwordValue1.value = ""
            document.getElementById("nav-home-tab").click();})
})


function getRoles(rols) {
    let roles = [];
    if (rols.indexOf("ADMIN") >= 0) {
        roles.push({"name": "ROLE_ADMIN"});
    }
    if (rols.indexOf("USER") >= 0) {
        roles.push({"name": "ROLE_USER"});
    }
    return roles;
}

// Delete
const url_delete_user = 'http://localhost:8080/api/admin/delete-user/';
function deleteModal(id) {
    fetch(url_get_user + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(us => {
            document.getElementById('idDelete').value = us.id;
            document.getElementById("firstNameDelete").value = us.firstName;
            document.getElementById("lastNameDelete").value = us.lastName;
            document.getElementById('ageDelete').value = us.age;
            document.getElementById('emailDelete').value = us.email;
        })
    });
}

async function deleteUser() {
    console.log(document.getElementById('idDelete').value)
    await fetch(url_delete_user + document.getElementById('idDelete').value, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(document.getElementById('idDelete').value)
    })

    getAllUsers()
    document.getElementById("deleteButton").click();
}

// Edit
const url_get_user = 'http://localhost:8080/api/admin/get-user-by-id/';
function editModal(id) {
    fetch(url_get_user + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(us => {

            document.getElementById('idEdit').value = us.id;
            document.getElementById("firstNameEdit").value = us.firstName;
            document.getElementById("lastNameEdit").value = us.lastName;
            document.getElementById('ageEdit').value = us.age;
            document.getElementById('emailEdit').value = us.email;


        })
    });
}

const url_update_user = 'http://localhost:8080/api/admin/update-user';
async function editUser() {
    let idValue = document.getElementById("idEdit").value;
    let firstNameValue = document.getElementById("firstNameEdit").value;
    let lastNameValue = document.getElementById("lastNameEdit").value;
    let ageValue = document.getElementById("ageEdit").value;
    let emailValue = document.getElementById("emailEdit").value;
    let passwordValue = document.getElementById("passwordEdit").value;
    let roles = getRoles(Array.from(document.getElementById("editRoles").selectedOptions).map(role => role.value));

    let user = {
        id: idValue,
        firstName: firstNameValue,
        lastName: lastNameValue,
        age: ageValue,
        email: emailValue,
        password: passwordValue,
        roles: roles
    }

    await fetch(url_update_user, {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(user)
    });
    getAllUsers()
    document.getElementById("updateButton").click();
}

//User
const tableForUser = document.getElementById("tableForUser");
const urlAuth = 'http://localhost:8080/api/admin/get-auth-user';
const panel = document.getElementById("admin-panel");

function userAuthInfo() {
    fetch(urlAuth)
        .then((res) => res.json())
        .then((u) => {
            let temp = '';
            temp += `<tr>
            <td class="text-center">${u.id}</td>
            <td class="text-center">${u.firstName}</td>
            <td class="text-center">${u.lastName}</td>
            <td class="text-center">${u.age}</td>
            <td class="text-center">${u.email}</td>
            <td class="text-center">${u.rolesToString}</td>
            </tr>`;
            tableForUser.innerHTML = temp;
        });
}

userAuthInfo()

function userPanel() {
    fetch(urlAuth)
        .then((res) => res.json())
        .then((u) => {
            panel.innerHTML = `<h5>${u.email} with roles: ${u.rolesToString}</h5>`
        });
}

userPanel()
