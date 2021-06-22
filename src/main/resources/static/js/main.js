function getRoles(el){
    var res = "";
    for(var i = 0; i < el.roles.length; i++){
        res = res + " "
        res = res + el.roles[i].nameRole.replace('ROLE_', "")
    }
    return res
}

function getUserFromForm(form){
    var user = {
        id: new FormData(form).get('id'),
        name: new FormData(form).get('name'),
        surname: new FormData(form).get('surname'),
        age: new FormData(form).get('age'),
        mail: new FormData(form).get('mail'),
        password: new FormData(form).get('password'),
        newRole: new FormData(form).get('newRole')
    }
    return user
}

function getUserById(id){
    var msg = $.ajax("/api/users/" + id, {
        async:false,
        method: "get",
        dataType: "json"
    }).responseJSON

    var user = {
        id: msg.id,
        name: msg.name,
        surname: msg.surname,
        age: msg.age,
        mail: msg.mail,
        password: msg.password,
        roles: msg.roles
    }
    return user
}

function loadMyData() {
    $.ajax("/api/userInfo", {
        method: "get",
        dataType: "json",
        success: function (msg) {
            const tdody = $("#users");
            var tr = $("<tr></tr>").addClass("user").appendTo(tdody)
            $("<td></td>").text(msg.id).appendTo(tr);
            $("<td></td>").text(msg.name).appendTo(tr);
            $("<td></td>").text(msg.surname).appendTo(tr);
            $("<td></td>").text(msg.mail).appendTo(tr);
            $("<td></td>").text(msg.age).appendTo(tr);
            $("<td></td>").text(getRoles(msg)).appendTo(tr);
        }
    })
}

