[#ftl]
[#macro masterTemplate title="Gestione Utente :: Home"]
<html>
	<!-- [#import "/spring.ftl" as spring /] -->
	<head>
		<title>${title}</title>
		<link rel="stylesheet" type="text/css" href="[@spring.url '/resources/css/main.css' /]" />
		<script src="[@spring.url '/resources/js/jquery-1.11.1.min.js' /]" type="text/javascript"></script>
		<script src="[@spring.url '/resources/js/moment.js' /]" type="text/javascript"></script>
		<script src="[@spring.url '/resources/js/main.js' /]" type="text/javascript"></script>
	</head>
	<body>
		<div class="container">
			<div id="header">
				<div id="pageTitle">Bamboo (MVC)</div>
				<img src="[@spring.url '/resources/img/grey_new_seo_128.png' /]" id="mainLogo" alt="[Logo]" />
			</div>
			
			<div id="main">
				<div id="navBar" class="highlight">
					<ul id="navBarMenu">
						<li><a href="[@spring.url '/index' /]">Home</a></li> | 
						<li><a href="[@spring.url '/user/add' /]">Creare Utenza</a></li> |
						<li><a href="[@spring.url '/groups/list' /]">Gestione Gruppi</a></li> 	
						[#if userDetails??]
						<li style="float: right; margin-right: 10px;"><a href="[@spring.url '/j_spring_security_logout' /]">Logout</a></li>
						[/#if]
					</ul>
				</div>

				<div id="pageBody">
					[#nested /]
				</div>

			</div>
		</div>
	</body>
</html>
[/#macro]