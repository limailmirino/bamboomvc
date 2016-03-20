[#ftl]
[#import "master.ftl" as layout /]
[#import "/spring.ftl" as spring /]

[@layout.masterTemplate title="User Management :: Home"]
<h2>Login</h2>
<div style="border: 1px solid #d40032;">

    <div class="error">[#if loginError??]${loginError}[/#if]</div>
    <form name="login" method="post" action="[@spring.url '/j_spring_security_check' /]">
        <div style="width: 50%; margin: 0px auto;">
            <div>
                <h1>
                    <div style="display: table-cell"><span id="j_env"></span></div>
                </h1>
            </div>
            <div>
                <label for="j_username">Username:</label>
                <input type="text" name="j_username" value="[#if username??]${username}[/#if]"/>
            </div>

            <div>
                <label for="j_password">Password:</label>
                <input type="password" name="j_password"/>
            </div>

            <div>
                [#if loginError??]
                    [#else]
                    <button type="submit">Login</button>
                [/#if]
            </div>
        </div>

    </form>

</div>

[/@layout.masterTemplate]