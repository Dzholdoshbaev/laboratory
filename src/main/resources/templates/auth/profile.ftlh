<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MicroGram</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
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
        .post-img {
            width: 100%;
            height: auto;
            border-radius: 10px;
            margin-bottom: 15px;
        }
        .post-content {
            background-color: #fff;
            padding: 15px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .post-item {
            margin-bottom: 30px;
        }
        .post-buttons button {
            margin-right: 10px;
        }
        .navbar {
            margin-bottom: 30px;
        }
    </style>
</head>

<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Home</a>
            <a class="navbar-brand" href="/search">Search</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav ml-auto">
                    <#assign known = SPRING_SECURITY_CONTEXT?? >
                    <#if known>
                        <#assign
                        user = SPRING_SECURITY_CONTEXT.authentication.principal
                        name = user.getUsername()
                        >
                        <a href="/profile" class="btn btn-outline-info">Profile</a>
                        <#if _csrf??>
                            <form action="/auth/logout" method="post" class="d-inline">
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                                <button type="submit" class="btn btn-outline-info">Logout</button>
                            </form>
                        <#else>
                            <a href="/auth/logout" class="btn btn-outline-info">Logout</a>
                        </#if>
                    <#else>
                        <a href="/auth/login" class="btn btn-outline-info">Login</a>
                        <a href="/profile/register" class="btn btn-outline-info">Register</a>
                    </#if>
                </div>
            </div>
        </div>
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
                <input type="file" name="file" class="form-control-file"/>
                <button type="submit" class="btn btn-outline-info">Change Photo</button>
            </form>
        </div>
        <div class="col-md-8">
            <h3 class="card-title">${userDto.name!"Not specified"}</h3>
            <p class="card-text"><strong>Login:</strong> ${userDto.login!"Not specified"}</p>
            <p class="card-text"><strong>Bio:</strong> ${userDto.bio!"Not specified"}</p>
            <p class="card-text"><strong>Followers:</strong> ${userCount.followersCount!"Not specified"} <strong>Posts:</strong>${userCount.publicationCount!"Not specified"}    <strong>Following:</strong> ${userCount.followingCount!"Not specified"}  </p>
            <a href="profile/edit" class="btn btn-outline-info mt-3">Edit profile</a>
        </div>
    </div>
</div>

<div class="container mt-5">
    <form action="/images/post" method="post" enctype="multipart/form-data" class="mb-4">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>
        <input type="file" name="file" class="form-control-file mb-3"/>
        <div class="form-outline mb-4">
            <label class="form-label" for="form2Example1">Description</label>
            <input type="text" id="form2Example1" class="form-control" name="description"/>
        </div>
        <button type="submit" class="btn btn-outline-info">Post Publication</button>
    </form>

    <ul class="post-list">
        <#list usersPosts as post>
            <li class="post-item">
                <img src="/images/post/${post.image}" alt="Post Image" class="post-img"/>
                <div class="post-content">
                    <p class="post-description"><strong>Description:</strong> ${post.description}</p>
                    <div class="post-interactions">
                        <span><strong>Comments:</strong> ${post.comments}</span>
                        <span><strong>Likes:</strong> ${post.likes}</span>
                        <div class="post-buttons">
                            <#assign known = SPRING_SECURITY_CONTEXT??>
                            <#if known>
                                <#assign
                                user = SPRING_SECURITY_CONTEXT.authentication.principal
                                name = user.getUsername()
                                >
                                <button class="btn btn-outline-primary btn-sm">Comment</button>
                                <button class="btn btn-outline-success btn-sm">Like</button>
                            <#else>
                                <p>You have to authorize yourself to like and comment</p>
                            </#if>
                        </div>
                    </div>
                </div>
            </li>
        </#list>
    </ul>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
