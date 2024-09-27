<#import "layout.ftlh" as main>

<@main.layout; spring>

    <#assign known = SPRING_SECURITY_CONTEXT??>
    <#if known>
        <#assign
        user = SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        >
        <ul class="post-list mt-4">
            <#list followListPosts as post>
                <li class="post-item">
                    <div class="post-header">
                        <img src="/images/${post.authorId.avatar}" alt="User Avatar" class="post-user-avatar"/>
                        <div class="post-user-info">
                            <strong>${post.authorId.name!""}</strong>
                            <span class="post-username">(${post.authorId.login!""})</span>
                        </div>
                    </div>
                    <img src="/images/post/${post.image}" alt="Post Image" class="post-img"/>
                    <div class="post-content">
                        <p class="post-description"><strong>Description:</strong> ${post.description}</p>
                        <div class="post-interactions">
                            <span><strong>Comments:</strong> ${post.comments}</span>
                            <span><strong>Likes:</strong> ${post.likes}</span>
                            <div class="post-buttons">
                                <#assign known = SPRING_SECURITY_CONTEXT??>
                                <#if known>
                                    <a href="/comment/${post.id}" class="btn btn-primary btn-sm">Comment</a>
                                    <a href="/like/${post.id}" class="btn btn-success btn-sm">Like</a>
                                <#else>
                                    <p>You have to authorize yourself to like and comment</p>
                                </#if>
                            </div>
                        </div>
                    </div>
                </li>
            </#list>
        </ul>

    <#else>
        <h2>Hello مرحبًا Привет こんにちは :) !</h2>

        <div class="row backpost">
            <div class="col col-12 col-md-6 colpost">
                <img src="../static/images/photo.jpg" class="w-100" alt="flats"/>
            </div>

            <div class="col col-12 col-md-6 colpost">
                <p class="mt-3"><strong class="poststrong">Create an account and then open a new resume for different opportunities</strong></p>
                <p class="postp-2">Make a Business it is easy open new vacancy for your company and start growing with us together!</p>
            </div>
        </div>

        <div class="row mt-5 g-0">
            <div class="col col-12 col-md-12 col-lg-6 ">
                <div class="card rounded-0 yellowleft h-100">
                    <h4 class="mt-5 ms-5">It started out as a simple idea and evolved into our passion</h4>
                    <p class="yellowp mt-4 ms-5">Open your heart to work with different people from around the world and make new friends. It's not hard to do; just be confident and smile—it is sunnah and work hard!</p>
                </div>
            </div>

            <div class="col col-12 col-md-12 col-lg-6">
                <div class="card rounded-0 h-100">
                    <img src="../static/images/photo-group.jpg" alt="group"/>
                </div>
            </div>
        </div>
    </#if>

    <style>
        .post-list {
            list-style-type: none;
            padding: 0;
        }

        .post-item {
            border: 1px solid #eaeaea;
            border-radius: 8px;
            margin-bottom: 20px;
            overflow: hidden;
            transition: transform 0.2s;
            width: 60%;
            margin: 0 auto;
        }

        .post-item:hover {
            transform: scale(1.02);
        }

        .post-header {
            display: flex;
            align-items: center;
            padding: 10px;
        }

        .post-user-avatar {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            margin-right: 10px;
        }

        .post-content {
            padding: 10px;
        }

        .post-img {
            width: 100%;
            height: auto;
            display: block;
        }

        .post-interactions {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 10px;
        }

        .post-buttons button {
            margin-right: 10px;
        }
    </style>


</@main.layout>
