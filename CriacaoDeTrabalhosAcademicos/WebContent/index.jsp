<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./estilo.css">
<script src="./script.js" type="text/javascript"></script>
<title>Criação de Trabalhos Acadêmicos</title>
</head>
<body onload="iniciar()">
	<%!String elemento;

	public String mudarElemento(String titulo) {
		elemento = titulo;
		System.out.print("Realizando mudarElemento!");
		return elemento;
	}%>
	<%
		mudarElemento("RESUMO");
	%>
	<div class="latEsquerdo" id="latEsquerdo">
		<input type="button" onclick='alert(<%=mudarElemento("Abstract")%>)'
			value="Abstract">
	</div>
	<div class="entradas">
		<table border="1">
			<tr>
				<td><%=elemento%></td>
				<td><form class="inputTxtResumo" action="">
						<textarea cols="50" rows="25"></textarea>
					</form></td>
			</tr>
		</table>
	</div>
	<div class="latDireito"></div>
</body>
</html>