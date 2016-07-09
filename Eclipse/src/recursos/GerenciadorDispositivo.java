package recursos;

import controle.Configuracao;

public class GerenciadorDispositivo extends GerenciadorRecursos {
	
	public GerenciadorDispositivo() {
		super(Configuracao.obterInstancia().getTamanhoPagina());
	}
	
	@Override
	public void ler(Processo p, int quantidade /*stub*/){
		p.bloquear();
		aguardando.add(p);
		
		try {
			wait(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		aguardando.remove(p);
		p.alocar();
	}

}
