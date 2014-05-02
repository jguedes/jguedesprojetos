package analisadordearquivo;
import java.io.Reader;
import java.util.List;

import relacao.Atributo;
import relacao.Classe;
import relacao.Instacia;
import relacao.Relacao;

public interface IAnalisadorDeArquivo {

	public void setArquivo(Reader arquivo);

	public Relacao getRelacao();
	
	public List<Instacia> getlistaDeInstaciasDaRelacaoAnalisada();

	public List<Classe> getlistaDeClassesDaRelacaoAnalisada();

	public List<Atributo> getlistaDeAtributosDaRelacaoAnalisada();

	public String getNomeDaRelacaoAnalisada();

}
