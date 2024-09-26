<#import "../layout.ftlh" as main>

<@main.layout ; spring>
    <h1>Write down to Search profile</h1>

    <form action="/search" method="get" class="form-inline my-2 my-lg-0">
        <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="search" value="${search}">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>

    <div class="mt-4">
        <h2>Search Results:</h2>
        <ul class="list-group">
            <#list users as user>
                <li class="list-group-item">
                    <a href="/profile/${user.id}" class="profile-link">
                        <#if user??>
                            <#if user.avatar?? && user.avatar?has_content>
                                <img src="./images/${user.avatar}" alt="ProfilePhoto" class="profile-img"/>
                            <#else>
                                <img src="/static/images/nophoto.jpg" alt="ProfilePhoto" class="profile-img"/>
                            </#if>
                        <#else>
                            <img src="/static/images/nophoto.jpg" alt="ProfilePhoto" class="profile-img"/>
                        </#if>
                        <span>${user.name!""} (${user.login!""}) - ${user.email!""}</span>
                    </a>
                </li>
            </#list>
        </ul>
    </div>

    <style>
        .profile-img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            margin-right: 10px;
        }

        .profile-link {
            display: flex;
            align-items: center;
            text-decoration: none;
            color: inherit;
        }

        .profile-link:hover {
            text-decoration: none;
        }

        .list-group-item {
            padding: 10px 20px;
            transition: background-color 0.3s;
        }

        .list-group-item:hover {
            background-color: #f8f9fa;
        }
    </style>
</@main.layout>
