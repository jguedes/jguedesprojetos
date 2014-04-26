function iniciar(){
	var div_esquerda = document.getElementById("latEsquerdo");
	var link_resumo = document.createElement("a");
	link_resumo.setAttribute("href", "#resumo");
	link_resumo.setAttribute("onclick", '<%mudarElemento("Resumo");%>');
	var texto = document.createTextNode("Resumo");
	link_resumo.appendChild(texto);
	div_esquerda.appendChild(link_resumo);
	
}