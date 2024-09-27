<#import "../layout.ftlh" as main>

<@main.layout ; spring>

    <div class="container profile-container">
        <div class="row">
            <div class="col-md-4 text-center">
                <img src="/images/${userDto.avatar!"default-avatar.png"}" alt="ProfilePhoto" class="profile-img"/>
            </div>
            <div class="col-md-8">
                <h3 class="card-title">${userDto.name!"Not specified"}</h3>
                <p class="card-text"><strong>Login:</strong> ${userDto.login!"Not specified"}</p>
                <p class="card-text"><strong>Bio:</strong> ${userDto.bio!"Not specified"}</p>
                <#assign known = SPRING_SECURITY_CONTEXT??>
                <#if known>
                    <#assign
                    user = SPRING_SECURITY_CONTEXT.authentication.principal
                    name = user.getUsername()
                    >
                    <#if isFollow>
                        <p>Followed</p>
                    <#else>
                        <a href="/follow/${userDto.id}" class="btn btn-outline-primary btn-sm" id="logout">Follow</a>
                    </#if>
                <#else>
                    <p>You have to authorize yourself to follow</p>
                </#if>
            </div>
        </div>
    </div>

    <#if usersPosts?has_content>
        <ul class="post-list mt-4">
            <#list usersPosts as post>
                <li class="post-item">
                    <div class="post-header">
                        <#if userDto.avatar?? && userDto.avatar?has_content>
                            <img src="/images/${userDto.avatar}" alt="User Avatar" class="post-user-avatar"/>
                        <#else>
                            <img src="/static/images/nophoto.jpg" alt="User Avatar" class="post-user-avatar"/>
                        </#if>
                        <div class="post-user-info">
                            <strong>${userDto.name!""}</strong>
                            <span class="post-username">(${userDto.login!""})</span>
                        </div>
                    </div>
                    <img src="/images/post/${post.image}" alt="Post Image" class="post-img"/>
                    <div class="post-content">
                        <p class="post-description"><strong>Description:</strong> ${post.description}</p>
                        <div class="post-interactions">
                            <span><strong>Comments:</strong> ${post.comments}</span>
                            <span><strong>Likes:</strong> ${post.likes}</span>
                        </div>
                    </div>
                </li>
            </#list>
        </ul>
    </#if>

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
        .post-list {
            list-style: none;
            padding: 0;
        }
        .post-item {
            border: 1px solid #ddd;
            border-radius: 8px;
            margin-bottom: 20px;
            overflow: hidden;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            width: 85%;
            margin-left: auto;
            margin-right: auto;
        }
        .post-header {
            display: flex;
            align-items: center;
            padding: 10px;
        }
        .post-user-avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            margin-right: 10px;
        }
        .post-user-info {
            display: flex;
            flex-direction: column;
        }
        .post-img {
            width: 100%;
            height: auto;
            object-fit: cover;
        }
        .post-content {
            padding: 10px;
        }
        .post-description {
            margin: 5px 0;
        }
        .post-interactions {
            margin-top: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .card-title {
            font-size: 1.5rem;
        }
        .card-text {
            font-size: 1rem;
        }
        .post-username {
            color: #888;
            font-size: 0.9rem;
        }
    </style>

</@main.layout>
