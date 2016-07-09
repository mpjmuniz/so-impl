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
	
	private void tratarTamanhoInsuficiente(int tamanho) {
		// swapp out
		swp.swapOut(tamanho);
	}
	
	private Pagina tratarSwappIn(int nPagina, Processo pros){
		// TODO repetir processo caso ocorra exceção
		Pagina pagMP = null;
		try {
			Pagina pSwapp = pros.getTabela().getPagina(nPagina);
			pagMP = swp.swapIn(pSwapp);
			// Sobreescreve a pagina na MS com uma pagina na MP
			pros.getTabela().insertPagina(pagMP, nPagina);
		} catch (TamanhoInsuficiente e) {
			Configuracao confs = Configuracao.obterInstancia();
			tratarTamanhoInsuficiente(confs.getTamanhoPagina());
		}
		return pagMP;
	}
	
	// Pagina não está em MP nem em Swapp
	private Pagina tratarPaginaMS(int nPagina, Processo p){
		Configuracao confs = Configuracao.obterInstancia();
		boolean status = true;
		Pagina pagMP = null;
		while(status){
			status = false;
			try {
				pagMP = gm.alocarMemoria(confs.getTamanhoPagina()).get(0);
				pagMP.trazer();
				p.getTabela().insertPagina(pagMP, nPagina);
			} catch (TamanhoInsuficiente e) {
				tratarTamanhoInsuficiente(confs.getTamanhoPagina());
				status = true;
			}		
		}
		return pagMP;
	}

	public Processo obterProcesso(int id) {
		return ListaProcessos.get(id);
	}
	
	public void criarProcesso(int id, int tamanho){
		Processo p = null;
		boolean status = true;
		while(status){
			status = false;
			try{
				List<Pagina> list = gm.alocarMemoria(tamanho);
				p = new Processo(id, tamanho, new TabelaDePaginas(list.size(),list));
			} catch(TamanhoInsuficiente e){
				tratarTamanhoInsuficiente(tamanho);
				status = true;
			}
		}
		// Colocar na lista do escalonador
		ListaProcessos.put(id, p);
	}

	public void le(int id, int pos){
		Processo p = this.ListaProcessos.get(id);
		int endFisico = this.descobreEnderecoFisico(p, pos);
		// Executa
		gm.ler(p, endFisico);
		// Retorna		
	}
	
	public void escreve(int id, int pos){
		Processo p = this.ListaProcessos.get(id);
		int endFisico = this.descobreEnderecoFisico(p, pos);
		// Executa
		gm.escrever(p, endFisico);
		// Retorna		
	}
	
	public void processa(int id, int pos){
		Configuracao confs = Configuracao.obterInstancia();
		// Resolve endereco: n da pagina + offset
		int nPagina = pos/confs.getTamanhoPagina();

		Processo p = this.ListaProcessos.get(id);
		Pagina pagina = p.getTabela().getPagina(nPagina);
		
		if(pagina == null)
			tratarPaginaMS(nPagina, p).utilizado();
		else if(pagina.isPresente())
			pagina.utilizado();
		else 
			tratarSwappIn(nPagina, p).utilizado();	
	}
	
	// TODO usa dispositivo
	public void usaDispositivo(int id, int dispositivo){
		
	}
	
	public int descobreEnderecoFisico(Processo p, int pos){
		Configuracao confs = Configuracao.obterInstancia();
		// Resolve endereco: n da pagina + offset
		int nPagina = pos/confs.getTamanhoPagina();
		int offset = pos%confs.getTamanhoPagina();
		// Pega o endereco da pagina
		TabelaDePaginas tp = p.getTabela();
		int endFisico = -1;
		try {
			endFisico = tp.getEndPagina(nPagina);
		} catch (FaltaDePagina e) {
			// Se ocorreu falta de página, logo ou a página está em swapp
			// ou em ms
			Pagina pagina = tp.getPagina(nPagina);
			// Não está presente, pois ocorreu falta de páginas
			if(pagina != null)
				//TODO implementação
				endFisico = tratarSwappIn(nPagina, p).getEndFisico();
			else
				endFisico = tratarPaginaMS(nPagina, p).getEndFisico();
			
		}
		return endFisico;
	}
	
	public List<Processo> todosProcessos(){
		return Collections.list(this.ListaProcessos.elements());
	}

}
