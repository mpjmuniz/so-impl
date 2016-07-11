package recursos;

import java.util.List;

import so.Swapper;

public class GerenciadorMemoriaVirtual {
	
	private GerenciadorMemoria gm;
	private GerenciadorDisco gd;
	private Swapper swp;

	public GerenciadorMemoriaVirtual(GerenciadorMemoria gm, GerenciadorDisco gd, Swapper swp) {
		this.gm = gm;
		this.gd = gd;
		this.swp = swp;
	}
	
	public List<Pagina> alocarMemoria(int tamanho){
		return null;
	}

}
