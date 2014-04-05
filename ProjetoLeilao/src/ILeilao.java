public interface ILeilao{
	public void lance(double valor) throws Exception;
	public void finalizar(IUsuario usuario);
	public void setDescricao(String descricao);
	public String getDescricao();
	public double getValorAtual();

}
