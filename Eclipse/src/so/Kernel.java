package so;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import controle.Configuracao;
import controle.Singleton;
import excecoes.FaltaDePagina;
import excecoes.TamanhoInsuficiente;
import recursos.GerenciadorDisco;
import recursos.GerenciadorMemoria;
import recursos.Pagina;
import recursos.Processo;
import recursos.TabelaDePaginas;

public class Kernel {
	
	private Hashtable<Integer, Processo> ListaProcessos; 
	private GerenciadorMemoria gm;
	private GerenciadorDisco gd;
	private Escalonador esc;
	private Swapper swp;
	
	public Kernel(GerenciadorMemoria gm, GerenciadorDisco gd, Escalonador esc, Swapper swp){
		this.gm = gm;
		this.gd = gd;
		this.esc = esc;
		this.swp = swp;
		ListaProcessos = new Hashtable<>();
	}

	public void tratarInterrupcao(Exception excecao) {
		if(excecao instanceof FaltaDePagina){
			//Chamar Swapper
		} else if(excecao instanceof TamanhoInsuficiente){
			//Jogar pro usuário
		}
	}

	public Processo obterProcesso(int id) {
		return ListaProcessos.get(id);
	}
	
	public void criarProcesso(int id, int tamanho){
		Processo p = null;
		try{
		List<Pagina> list = gm.alocarMemoria(tamanho);
		p = new Processo(id, tamanho, new TabelaDePaginas(list.size(),list));
		} catch(TamanhoInsuficiente e){
			tratarInterrupcao(e);
		}
		// Colocar na lista do escalonador
		ListaProcessos.put(id, p);
	}
	
	public void processoLe(int id, int pos){
		Configuracao confs = Configuracao.obterInstancia();
		// Resolve endereco: n da pagina + offset
		int npagina = pos/confs.getTamanhoPagina();
		int offset = pos%confs.getTamanhoPagina();
		// Pega o endereco da pagina
		Processo p = this.ListaProcessos.get(id);
		TabelaDePaginas tp = p.getTabela();
		try {
			int endFisico = tp.getEndPagina(npagina);
			// Executa
			gm.ler(p, endFisico);
		} catch (FaltaDePagina e) {
			tratarInterrupcao(e);
		}
		// Retorna		
	}
	
	public List<Processo> todosProcessos(){
		return Collections.list(this.ListaProcessos.elements());
	}

}
