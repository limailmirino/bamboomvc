[#ftl]
[#import "master.ftl" as layout /]

[@layout.masterTemplate title="User Management :: Errore"]

<div class="boxout" style="width: 60%; margin: 100px auto;">
    <div class="boxout-title">Messaggio</div>
    <div class="boxout-body">
		<div style="text-align: center; color: #F00;margin: 20px;">${error}</div>
		<div style="text-align: center;"><a href="../index">Indietro</a></div>
    </div>
</div>

[/@layout.masterTemplate]