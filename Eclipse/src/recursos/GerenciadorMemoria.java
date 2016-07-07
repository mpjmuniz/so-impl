package recursos;

import java.util.ArrayList;
import java.util.List;

import excecoes.TamanhoInsuficiente;

public class GerenciadorMemoria extends GerenciadorRecursos {
	
	public GerenciadorMemoria() {
		super(confs.getTamanhoTotalMP());
	}

	public List<Pagina> alocarMemoria(int tamanho) throws TamanhoInsuficiente {
		
		int qtdPaginas;
		List<Pagina> pgs;
		TabelaDePaginas tp;
		
		//Supondo inexistência de memória virtual
		if(tamanho > tamanhoDisponivel) throw new TamanhoInsuficiente();
		
		qtdPaginas = tamanho / confs.getTamanhoPagina();
		if(tamanho % confs.getTamanhoPagina() > 0) qtdPaginas++;
		
		pgs = new ArrayList<>(qtdPaginas);
		
		for(int i = 0; i < qtdPaginas ; i++){
			pgs.add(livres.remove(0));
		}
		
		this.tamanhoDisponivel -= tamanho; 
		
		return pgs;
	}

	public void liberarMemoria(Processo p) {
		TabelaDePaginas tp = p.getTabela();
		
		this.tamanhoDisponivel += tp.getTamanho();
		livres.addAll(tp.getPaginas());
		// TODO sort lista de livres
		p.setTabela(null);
	}

}
