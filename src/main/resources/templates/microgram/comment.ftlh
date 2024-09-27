<#import "../layout.ftlh" as main>

<@main.layout ; spring>
    <h2>Comments</h2>

    <form action="/comment/add/${postId}" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>
        <div class="form-group">
            <label>
                <textarea name="comment" rows="3" class="form-control" required placeholder="Add a comment..."></textarea>
            </label>
        </div>
        <button type="submit" class="btn btn-primary">Submit Comment</button>
    </form>

    <ul class="post-comments">
        <#list postComments as comment>
            <li class="post-item">
                <p class="post-description"><strong>${comment.commentator.login}:</strong> ${comment.comment}</p>
                <#if isAuthorPost>
                    <a href="/comment/${comment.post.id}/${comment.id}" class="btn btn-outline-primary btn-sm">Delete comment</a>
                <#else>
                    <p>-------------------------------</p>
                </#if>
            </li>
        </#list>
    </ul>
</@main.layout>
