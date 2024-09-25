<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MicroGram</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .profile-container {
            margin-top: 30px;
        }
        .profile-img {
            width: 150px;
            height: 150px;
            object-fit: cover;
            border-radius: 50%;
            border: 2px solid #ddd;
            margin-bottom: 15px;
        }
        .form-control-file {
            border: 1px solid #ced4da;
            border-radius: 0.25rem;
        }
        .btn-outline-info {
            margin-top: 10px;
        }
        .card-title {
            font-size: 1.5rem;
        }
        .card-text {
            font-size: 1rem;
        }
    </style>
</head>

<header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Home</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
        <#assign known = SPRING_SECURITY_CONTEXT?? >
        <#if known>
            <#assign
            user = SPRING_SECURITY_CONTEXT.authentication.principal
            name = user.getUsername()
            >
            <a href="/profile" class="text-dark m-2 m-sm-0">
                <span class="btn btn-outline-info m-2">Profile</span>
            </a>

            <#if _csrf??>

                <form action="/auth/logout" method="post">

                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"

                           value="${(_csrf.token)!'--no-token--'}"/>

                    <button type="submit" class="btn btn-outline-info m-2" id="logout">Logout</button>

                </form>

            <#else>

                <a href="/auth/logout" class="btn btn-outline-info m-2" id="logout">Logout</a>

            </#if>

        <#else>
            <a href="/auth/login"
               class="btn btn-outline-info m-2">Login</a>
            <a href="/profile/register"
               class="btn btn-outline-info m-2">Register</a>
        </#if>

    </nav>
</header>

<body>
<div class="container profile-container">
    <div class="row">
        <div class="col-md-4 text-center">
            <#if userDto??>
                <#if userDto.avatar?? && userDto.avatar?has_content>
                    <img src="./images/${userDto.avatar}" alt="ProfilePhoto" class="profile-img"/>
                <#else>
                    <img src="/static/images/nophoto.jpg" alt="ProfilePhoto" class="profile-img"/>
                </#if>
            <#else>
                <img src="/static/images/nophoto.jpg" alt="ProfilePhoto" class="profile-img"/>
            </#if>

            <form action="/images" method="post" enctype="multipart/form-data" class="mt-3">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>
                <input type="file" name="file"/>
                <button type="submit" class="btn btn-outline-info">Change Photo</button>
            </form>
        </div>
        <div class="col-md-8">
            <h3 class="card-title">${userDto.name!"Not specified"}</h3>
            <p class="card-text"><strong>login:</strong> ${userDto.login!"Not specified"}</p>
            <p class="card-text"><strong>Email:</strong> ${userDto.email!"Not specified"}</p>
            <a href="profile/edit" class="btn btn-outline-info mt-3">Edit profile</a>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
