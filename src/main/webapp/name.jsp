<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Name</title>
    <script src="webjars/jquery/3.2.0/jquery.min.js"></script>
    <script src="name.js"></script>
    <script src="todo.js"></script>
    <link href="name.css" rel="stylesheet"/>
    <script>
        $(document).ready(function() {
            setUpCookies("${username}")
        })

        $(document).ready(function () {
            $.get( "/TodoServlet", function( data ) {
                myfunction( data );
                })
            })
    </script>

</head>
<body>

<h1>${msg} ${username}</h1>
<br>



<form method="post" action="/TodoServlet">
    <input name="todo">
</form>
<br>

<div class="content"></div>

<br>
<button onclick="setCookie('all')">All</button>
<button onclick="setCookie('unfinished')">Unfinished</button>
<button onclick="setCookie('finished')">Finished</button>

<!--<a href="..">Go back</a>-->
</body>
</html>
