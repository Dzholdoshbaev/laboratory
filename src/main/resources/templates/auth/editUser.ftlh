<#import "../layout.ftlh" as main>

<@main.layout ; spring>
    <div class="container mt-5">
        <h2 class="mb-4">Edit Profile</h2>
        <form action="/profile/edit" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <th scope="row">Name</th>
                    <td>
                        <@spring.formInput "usersDto.name" 'class="form-control" aria-label="Name" aria-describedby="name" name="name"' />
                        <div class="text-danger"><@spring.showErrors "<br>" "error"/></div>
                    </td>
                </tr>
                <tr>
                    <th scope="row">Login</th>
                    <td>
                        <@spring.formInput "usersDto.login" 'class="form-control" aria-label="Login" aria-describedby="login" name="login"' />
                        <div class="text-danger"><@spring.showErrors "<br>" "error"/></div>
                    </td>
                </tr>
                <tr>
                    <th scope="row">Bio</th>
                    <td>
                        <@spring.formInput "usersDto.bio" 'class="form-control" aria-label="BIO" aria-describedby="bio" name="bio"' />
                        <div class="text-danger"><@spring.showErrors "<br>" "error"/></div>
                    </td>
                </tr>
                <tr>
                    <th scope="row">Email</th>
                    <td>
                        <@spring.formInput "usersDto.email" 'class="form-control" aria-label="Email" aria-describedby="email" name="email"' />
                        <div class="text-danger"><@spring.showErrors "<br>" "error"/></div>
                    </td>
                </tr>
                <tr>
                    <th scope="row">Password</th>
                    <td>
                        <@spring.formInput "usersDto.password" 'class="form-control" aria-label="Password" aria-describedby="password" type="password" name="password"' />
                        <div class="text-danger"><@spring.showErrors "<br>" "error"/></div>
                    </td>
                </tr>
                </tbody>
            </table>
            <button type="submit" class="btn btn-outline-info m-2">Edit</button>
        </form>
    </div>
</@main.layout>
