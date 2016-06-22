package so;

public class GerenciadorDisco extends GerenciadorRecurso{
	/*
	 * 	TODO: Implementar como Singleton
	 * */
	public GerenciadorDisco(int tamanhoTotal, int tamanhoQuadro){
		super(tamanhoTotal, tamanhoQuadro);
	}

	public int ler(int endereco){
		return 0;
	}
	
	public void escrever(int endereco, int dado){
		
	}
}