package so;

public class GerenciadorDisco extends GerenciadorRecurso{
	
	private static final long serialVersionUID = -7872086859890914698L;

	public GerenciadorDisco(int tamanhoTotal, int tamanhoQuadro){
		super(tamanhoTotal, tamanhoQuadro);
	}

	public int ler(int endereco){
		return 0;
	}
	
	public void escrever(int endereco, int dado){
		
	}
}