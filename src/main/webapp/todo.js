/**
 * Created by ugyan on 2017.05.09..
 */

function myfunction(json) {

    var json = JSON.parse(json);

    document.getElementsByClassName("content").item(0).innerHTML = "";

    for(var i = 0; i < json.length; i++) {
        var li = document.createElement("li");
        li.setAttribute("id", json[i].id);
        li.setAttribute("status", json[i].completion);

        var checkbox = document.createElement("input");

        checkbox.setAttribute("type", "checkbox");
        //checkbox.setAttribute("id", "cb" + json[i].id);
        checkbox.setAttribute("onclick", "toggleStatus(" + json[i].id + ")");
        if (json[i].completion == "true") {
            checkbox.checked = true;
        }

        var deleteButton = document.createElement("button");

        deleteButton.setAttribute("type", "button");
        deleteButton.innerHTML = "X";
        //deleteButton.setAttribute("id", "db" + json[i].id);
        deleteButton.setAttribute("onclick", "deleteTask(" + json[i].id + ")");

        var br = document.createElement("br");

        document.getElementsByClassName("content").item(0).appendChild(checkbox);
        document.getElementsByClassName("content").item(0).appendChild(li);
        document.getElementsByClassName("content").item(0).appendChild(deleteButton);
        document.getElementsByClassName("content").item(0).appendChild(br);
        $("#" + json[i].id).text(json[i].name);


    }

}

function toggleStatus(id) {
    $.post("/EditServlet", {"id" : id})

    $.get("/TodoServlet", function( data ) {
        myfunction( data );
    })
}

function deleteTask(id) {
    $.ajax({
        url: "/EditServlet/" + id,
        type: "DELETE"
    })

    $.get("/TodoServlet", function( data ) {
        myfunction( data );
    })
}


$(function () {
    $("#putButton").click(function () {
        $.ajax({
            url: "TodoServlet",
            type: "PUT",
            data: $("#todo").val()
        })
    })
    $.get("/TodoServlet", function( data ) {
        myfunction( data );
    })
})

function setUpCookies(username) {
    if (document.cookie) { return }
    else {
    document.cookie = "username=" + username;
    document.cookie = "requestType=all"
    }
}

function setCookie(requestType) {
    document.cookie = "requestType=" + requestType;
    $.get("/TodoServlet", function( data ) {
        myfunction( data );
    })
}
