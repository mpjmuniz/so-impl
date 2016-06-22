package so;

public class GerenciadorMemoria extends GerenciadorRecurso{
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

		p.setTabela(null);
	}
	
	public int ler(Processo p, int endereco){
		return 0;
	}
	
	public void escrever(Processo p, int endereco, int dado){
		
	}

}
