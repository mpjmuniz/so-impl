package recursos;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

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
	
	public void removePagina(int nPagina){
		this.paginas.remove(nPagina);
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
	
	public void substituiPagina(int nPagina,Pagina p){
		this.paginas.replace(nPagina, p);
	}
	
	public int getKey(Pagina p){
		for(Entry<Integer, Pagina> e: this.paginas.entrySet()){
			if(e.getValue().equals(p)) return e.getKey().intValue();
		}
		return -1;
	}
}