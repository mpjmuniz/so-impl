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
		
		//Supondo inexistência de memória virtual
		if(tamanho > tamanhoDisponivel) throw new TamanhoInsuficiente();
		
		qtdPaginas = tamanho / confs.getTamanhoPagina();
		
		List<Pagina> pgs = new ArrayList<>(qtdPaginas);
		
		for(int i = 0; i < tamanho ; i++){
			pgs.add(livres.remove(0));
		}
		
		return pgs;
	}

	public void liberarMemoria(Processo p) {

	}

}
