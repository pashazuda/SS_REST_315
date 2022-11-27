const data = document.getElementById("data-user");
const url_user = 'http://localhost:8080/api/user';
const panel = document.getElementById("user-panel");

function userAuthInfo() {
    fetch(url_user)
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
            data.innerHTML = temp;
            panel.innerHTML = `<h5>${u.email} with roles: ${u.rolesToString}</h5>`
        });
}

userAuthInfo()