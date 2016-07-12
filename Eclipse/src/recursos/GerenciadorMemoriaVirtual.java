package recursos;

import java.util.ArrayList;
import java.util.List;

import controle.Configuracao;
import excecoes.TamanhoInsuficiente;
import so.Swapper;

public class GerenciadorMemoriaVirtual {
	
	private GerenciadorMemoria gm;
	private Swapper swp;

	public GerenciadorMemoriaVirtual(GerenciadorMemoria gm, GerenciadorDisco gd, Swapper swp) {
		this.gm = gm;
		this.swp = swp;
	}
	
	public List<Pagina> alocarMemoria(Processo p, int tamanho) throws TamanhoInsuficiente{
		Configuracao confs = Configuracao.obterInstancia();
		
		int qtdPaginas = confs.getQuantidadePaginas(tamanho);
		
		List<Pagina> pgs = new ArrayList<>(qtdPaginas);
		int i=0;
		try{
			for(; i<qtdPaginas; i++)
				pgs.add(gm.getQuadroLivre(p));
		} catch (TamanhoInsuficiente e) {
			swp.swapOut(confs.getQuantidadePaginas(qtdPaginas-i));
			for(; i<qtdPaginas; i++)
				pgs.add(gm.getQuadroLivre(p));
		}
		
		return pgs;
	}

}
