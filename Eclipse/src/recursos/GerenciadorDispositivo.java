package recursos;

import controle.Configuracao;

public class GerenciadorDispositivo extends GerenciadorRecursos {
	
	public GerenciadorDispositivo() {
		super(Configuracao.obterInstancia().getTamanhoPagina());
	}
	
	public Pagina ler(Processo p, int quantidade /*stub*/){
		p.bloquear();
		aguardando.add(p);
		return null;
	}

}
