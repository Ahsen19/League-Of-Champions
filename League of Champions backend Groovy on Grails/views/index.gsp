<!DOCTYPE html>

<html lang="en">

<head>

    <!-- Put all meta tags here -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Put all CSS includes here -->
    <link href="/static/PROJ/PROJ.css" rel="stylesheet">

    <title>BDCD</title>

</head>

<body>

<!-- This is the root of the app, all content will go into this div -->
<div id="app"></div>

<!-- This is where the log will write to -->
<div id="applog"></div>

<!-- This is the modal used by our common spinner -->
<div class="modal" id="spinnerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div id="spinner" style="display:inline-block; position:fixed;top:50%;left:50%" ></div>
</div>

<script src="/static/PROJ/PROJ.js"></script>

<!-- This is the main line that starts the app -->
<script>
    $( document ).ready(function() {
        spapp.initialize();
    });
</script>

</body>

</html>
