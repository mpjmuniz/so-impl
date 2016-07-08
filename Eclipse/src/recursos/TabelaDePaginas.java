package recursos;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import controle.Configuracao;
import excecoes.FaltaDePagina;
import excecoes.TamanhoInsuficiente;

public class TabelaDePaginas {

	private static Configuracao confs = Configuracao.obterInstancia();
	private int tamanho;
	private Hashtable<Integer, Pagina> paginas;

	public TabelaDePaginas(int linhas, List<Pagina> pgs) {
		paginas = new Hashtable<>();
		this.tamanho = linhas * confs.getTamanhoPagina();
		for(int i=0; i<linhas; i++){
			paginas.put(i, pgs.get(i));
		}
	}

	public int getTamanho() {
		return this.tamanho;
	}
	
	public void insertPagina(Pagina p, int nPagina){
		paginas.put(nPagina, p);
	}
	
	public void removePagina(Pagina p){
		this.paginas.remove(p.getEndFisico());
	}

	public List<Pagina> getPaginas() {
		return Collections.list(paginas.elements());
	}
	
	public int getEndPagina(int nPagina) throws FaltaDePagina {
		if(paginas.containsKey(nPagina) && paginas.get(nPagina).isPresente())		
			return paginas.get(nPagina).getEndFisico();
		else
			throw new FaltaDePagina();		
	}
	
	public Pagina getPagina(int nPagina){
		if(paginas.containsKey(nPagina))
			return paginas.get(nPagina);
		else
			return null;
	}
}