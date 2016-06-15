package ambiente;

import so.TabelaDePaginas;

public class Memoria{
	private Pagina[] paginas;
	private int tamanho;
	private int tamanhoPagina;
	//quantidade de páginas livres
	private int paginasLivres;
	
	
	public Memoria(int tam, int tamPag){
		this.tamanho = tam;
		this.paginas = new Pagina[tam];
		this.tamanhoPagina = tamPag;
		this.paginasLivres = this.tamanho / this.tamanhoPagina;
	}
	
	public TabelaDePaginas alocar(int qtdPag) throws MemoriaInsuficiente{
		if(qtdPag > paginasLivres) throw new MemoriaInsuficiente();
		
		for(int i = 0; i < qtdPag; i++){
			
		}
	}
	
	
}