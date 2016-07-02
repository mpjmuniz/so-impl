package so;

import controle.Singleton;
import excecoes.FaltaDePagina;
import excecoes.TamanhoInsuficiente;
import recursos.Processo;

public class Kernel {

	public void tratarInterrupcao(Exception excecao) {
		if(excecao instanceof FaltaDePagina){
			//Chamar Swapper
		} else if(excecao instanceof TamanhoInsuficiente){
			//Jogar pro usuário
		}
	}

	public Processo obterProcesso(int id) {
		return null;
	}

}
