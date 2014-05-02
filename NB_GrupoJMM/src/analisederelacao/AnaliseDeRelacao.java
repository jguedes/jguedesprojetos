package analisederelacao;

import relacao.Classe;
import relacao.Relacao;

public class AnaliseDeRelacao implements IAnaliseDeRelacao {

	private Relacao relacao;

	@Override
	public void setRelacao(Relacao relacao) {

		this.relacao = relacao;

	}

	@Override
	public double getProb_APrioriDeClasse(Classe classe) {

		return getProb_APrioriDeClasse(classe.getNome());

	}

	@Override
	public double getProb_APrioriDeClasse(String nomeDaClasse) {

		return relacao.getDistribuicaoDeClasse(nomeDaClasse);

	}

}
