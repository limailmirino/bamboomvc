[#ftl]
[#import "master.ftl" as layout /]
[#import "/spring.ftl" as spring /]

[@layout.masterTemplate title="User Management :: Home"]
<h2>Ricerca Utenti</h2>
<div id="pageContent">
    <form name="searh" method="post" action="" onsubmit="loadingScreenStart()">
        [#if error??]
            <div class="errors" align="right">
                <span class="error">${error}</span>
            </div>
        [/#if]
        <div>
            <label for="firstname">Nome:</label>
            <input type="text" name="name" value="[#if search_name??]${search_name}[/#if]"/>
        </div>
        <div>
            [#if roles?has_content]
                <label for="role" style="position: relative; top: -2px;">Ruolo:</label>
                [#if roles?size=0]
                    <span class="error">NESSUN RUOLO ASSOCIATO A QUESTA UTENZA</span>
                [#else]
                    <div class="select">
                        <select name="role" id="role"
                                style="width: 209px; border: none; background-color: transparent; color: #555">
                            [#if roles?size > 1]
                                <option value="">Tutti</option>[/#if]
                            [#list roles as role]
                                <option value="${role.getIdRole()}"[#if (user?? && user.getRole() == role.getIdRole()) || roles?size=1]
                                        selected="selected"[/#if]>${role.getDescription()}</option>
                            [/#list]
                        </select>
                    </div>
                [/#if]
            [/#if]
        </div>
        <div>
            <label for="cust_code">Codice Cliente:</label>
            <input type="text" name="cust_code" value="[#if search_cust_code??]${search_cust_code}[/#if]"/>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="text" name="email" value="[#if search_email??]${search_email}[/#if]"/>
        </div>
        <div>
            <input type="submit" value="Ricerca"/>
        </div>
    </form>

    [#if events?size > 1]
        [#list events as event]
            ${event.getTitle()}<br/>
        [/#list]
    [/#if]








</div>

<table class="list">
    <thead>
    <!--
	<tr>
        <th colspan="6" style="text-align: right; border-bottom: 1px solid #EEE;">
            <button id="delete">Cancellare</button>
        </th>
    </tr>
	-->
    <tr>
        <th>Nome</th>
        <th>Email</th>
        <th>Username</th>
        <th>Company/Code</th>
        <th>Dettagli</th>
        <!-- <th>Sel. <input type="checkbox" id="selAll"/></th> -->
        <th>Cancella</th>
    </tr>
    </thead>
    <tbody>
        [#if results?has_content]
            [#list results as user]
            <tr>
                <td>${user.getSurname()} ${user.getName()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getUsername()}</td>
                <td>${user.getSapCode()} - ${user.getCompany()}</td>
                <td style="text-align: center;">
                    <a href="user/details.html?id=${user.getUsername()}">dettagli</a>
                </td>
                <td style="text-align: center; width: 20px;">
                    <a href="user/delete?uid=${user.getUsername()}">Cancella</a>
                </td>
            </tr>
            [/#list]
        [#else]
        <tr>
            <td class="noData" colspan="6">Nessun record selezionato</td>
        </tr>
        [/#if]
    </tbody>
    <tfoot>
    </tfoot>
</table>

<div class="modal"></div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#selAll").change(function () {
            if (this.checked) {
                $(".sel").prop("checked", true).change();
                return;
            }

            $(".sel").prop("checked", false).change();
        });

        $(".sel").change(function () {
            $("#delete").prop("disabled", "disabled");
            $(".sel").each(function () {
                if (this.checked) {
                    $("#delete").removeAttr("disabled");
                    return false;
                }
            });
        });

        $("#delete").prop("disabled", "disabled").click(function () {
            if (!confirm("Sei sicuro di voler cancellare le utenze selezionate? Saranno cancellate definitivemente.")) {
                return false;
            }

            deleteUsers();
        });
    });

    function deleteUsers() {
        var uids = $(".sel:checked").map(function () {
            return $(this).val();
        }).get();

        uids = uids.join();
        $.post("user/delete.html", {"uids": uids}, function (data) {
            var data = $.parseJSON(data);
            if (data.status == "OK") {

                alert("Gli utenti selezionati sono stati cancellati correttamente.");
                location.reload();

            } else {
                alert("C'e' stato un problema con la richiesta. Verificare che tutte le utenze siano state cancellate. Prego controllare.");
            }
        });
    }

</script>
[/@layout.masterTemplate]