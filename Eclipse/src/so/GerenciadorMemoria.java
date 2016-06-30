package so;

import recursos.Processo;

public class GerenciadorMemoria extends GerenciadorRecurso{
	
	private static final long serialVersionUID = 4108299752547976951L;

	/*
	 * 	TODO: Implementar como Singleton
	 * */
	public GerenciadorMemoria(int tamanhoTotal, int tamanhoPagina){
		super(tamanhoTotal, tamanhoPagina);
	}
	
	public TabelaDePaginas alocarMemoria(int tamanho){
		return new TabelaDePaginas(tamanhoPagina, tamanho / tamanhoPagina);
	}
	
	public void liberarMemoria(Processo p){

	}
	
	public int ler(Processo p, int endereco){
		return 0;
	}
	
	public void escrever(Processo p, int endereco, int dado){
		
	}

}
