package analisadordearquivo;

import java.io.Reader;

import montadorderelacao.IMontadorDeRelacao;

public interface IAnalisadorDeArquivo {

	public void setArquivo(Reader arquivo);

	public void setMontadorDeRelacao(IMontadorDeRelacao montadorDeRelacao);

}
