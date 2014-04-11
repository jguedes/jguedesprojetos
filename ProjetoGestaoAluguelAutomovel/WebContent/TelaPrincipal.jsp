<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./estilo.css">
<title>Pega Auto</title>
</head>
<body>
	<div class="cabecalho">
		<div class="slogan">
			<h1>Pega Auto - Gestão de Aluguel de Automóveis</h1>
		</div>
		<div class="usuario">
			<h3>Seja bem-vindo Fulano de Tal.</h3>
			<input class="btn_meuPerfil" type="button" value="Meu perfil">
			<input class="btn_sair" type="button" value="Sair">
		</div>
	</div>
	<!-- hr class="linha" -->
	<div class="barraDeMenu">
		<ul>
			<li><a href="#pedidosDeAluguel">Pedidos de aluguel</a></li>
			<li><a href="#contratos">Contratos</a></li>
		</ul>
	</div>
	<div class="menuEsquerdo">
		<ul class="listaMenuEsquerdo">
			<li>Pedido de aluguel
				<ul>
					<li><a href=#efetuar>Efetuar</a></li>
					<li><a href=#consultar>Consultar</a></li>
					<li><a href=#modificar>Modificar</a></li>
				</ul>
			</li>
		</ul>
	</div>
	<div class="recomendacao">
		<div class="msgRecomendacao">
			<h3>
				A Pega Auto recomenda Peugeot Vision para você <a href=#alugar>alugar</a>
			</h3>
		</div>
		<a href=#alugar><img alt="carro recomendado"
			src="./imagens/08092009_blog.uncovering.org_peugeot.jpg"></a>
	</div>
	<!-- div class="menuDireito"></div -->
</body>
</html>