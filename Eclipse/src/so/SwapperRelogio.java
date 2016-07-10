package so;

import java.util.ArrayList;
import java.util.List;

import controle.Configuracao;
import recursos.*;

public class SwapperRelogio extends Swapper {
	
	private int ultimaPagFila = 0;

	public SwapperRelogio(GerenciadorMemoria gm, GerenciadorDisco gd) {
		super(gm, gd);
	}

	@Override
	public void swapOut(int tamanho) {
		Configuracao confs = Configuracao.obterInstancia();
		int qtdPag = confs.getQuantidadePaginas(tamanho);
		while(qtdPag > 0){
			List<Pagina> a =  gm.getQuadros();
			Pagina vitima = null;
			//Faz para todos os outros
			int i;
			for(i=ultimaPagFila; vitima == null; i=(i+1)%a.size()){
				if(!a.get(i).isUtilizado())
					vitima = a.get(i);
				else
					a.get(i).inutilizado();
			}
			ultimaPagFila = i;
			_swapOut(vitima);
			qtdPag--;
		}
	}

}
