package recursos;

import java.util.ArrayList;
import java.util.List;

import excecoes.TamanhoInsuficiente;

public class GerenciadorMemoria extends GerenciadorRecursos {
	
	public GerenciadorMemoria() {
		super(confs.getTamanhoTotalMP());
	}

	public TabelaDePaginas alocarMemoria(int tamanho) throws TamanhoInsuficiente {
		
		int qtdPaginas;
		List<Pagina> pgs;
		TabelaDePaginas tp;
		
		//Supondo inexistência de memória virtual
		if(tamanho > tamanhoDisponivel) throw new TamanhoInsuficiente();
		
		qtdPaginas = tamanho / confs.getTamanhoPagina();
		
		pgs = new ArrayList<>(qtdPaginas);
		
		for(int i = 0; i < tamanho ; i++){
			pgs.add(livres.remove(0));
		}
		
		 tp = new TabelaDePaginas(qtdPaginas, pgs);
		
		this.tamanhoDisponivel -= tp.getTamanho(); 
		
		return tp;
	}

	public void liberarMemoria(Processo p) {
		TabelaDePaginas tp = p.getTabela();
		
		this.tamanhoDisponivel += tp.getTamanho();
		livres.addAll(tp.getPaginas());
		p.setTabela(null);
	}

}
