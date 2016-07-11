package so;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import controle.Configuracao;
import excecoes.FaltaDePagina;
import excecoes.ProcessoInexistente;
import excecoes.TamanhoInsuficiente;
import recursos.GerenciadorDisco;
import recursos.GerenciadorDispositivo;
import recursos.GerenciadorMemoria;
import recursos.Pagina;
import recursos.Processo;
import recursos.TabelaDePaginas;

public class Kernel {
	
	private Hashtable<Integer, Processo> listaProcessos; 
	private GerenciadorMemoria gm;
	private GerenciadorDisco gd;
	private GerenciadorDispositivo gp;
	private Escalonador esc;
	private Swapper swp;
	
	public Kernel(){
		this.listaProcessos = new Hashtable<>();
		this.gm = new GerenciadorMemoria();
		this.gd = new GerenciadorDisco();
		this.esc = new Escalonador();
		this.swp = new SwapperRelogio(gm, gd, this);
		this.gp = new GerenciadorDispositivo();		
	}
	
	private void tratarTamanhoInsuficiente(int tamanho) throws TamanhoInsuficiente {
		// swapp out
		swp.swapOut(tamanho);
	}
	
	private Pagina tratarSwappIn(int nPagina, Processo pros) throws TamanhoInsuficiente{
		Pagina pagMP = null;
		try {
			Pagina pSwap = pros.getTabela().getPagina(nPagina);
			pagMP = swp.swapIn(pSwap);
		} catch (TamanhoInsuficiente e) { // Caso n„o haja espaco para o swap in na MP
			Configuracao confs = Configuracao.obterInstancia();
			tratarTamanhoInsuficiente(confs.getTamanhoPagina());
			Pagina pSwap = pros.getTabela().getPagina(nPagina);
			pagMP = swp.swapIn(pSwap);
		}
		// Corrige referencia na tabela de p·ginas
		pros.getTabela().substituiPagina(nPagina, pagMP);
		return pagMP;
	}
	
	// Pagina nao esta em MP nem em Swap
	private Pagina tratarPaginaMS(int nPagina, Processo p) throws TamanhoInsuficiente{
		Configuracao confs = Configuracao.obterInstancia();
		Pagina pagMP = null;
		try {
			pagMP = gm.alocarMemoria(confs.getTamanhoPagina()).get(0);
		} catch (TamanhoInsuficiente e) {
			tratarTamanhoInsuficiente(confs.getTamanhoPagina());
			pagMP = gm.alocarMemoria(confs.getTamanhoPagina()).get(0);
		}
		p.getTabela().insertPagina(pagMP, nPagina);
		return pagMP;
	}

	public Processo obterProcesso(int id) throws ProcessoInexistente {
		Processo p = listaProcessos.get(id);
		
		if(p == null) throw new ProcessoInexistente("Processo n√£o existe.");
		
		return p;
	}
	
	public void criarProcesso(int id, int tamanho) throws TamanhoInsuficiente{
		Processo p = null;
		Configuracao confs = Configuracao.obterInstancia();
		try{
			if(tamanho < confs.getQuantidadeInicialPaginas()*confs.getTamanhoPagina())
				tamanho = confs.getQuantidadeInicialPaginas()*confs.getTamanhoPagina();
			List<Pagina> list = gm.alocarMemoria(tamanho);
			p = new Processo(id, tamanho, new TabelaDePaginas(list.size(),list));
		} catch(TamanhoInsuficiente e){
			tratarTamanhoInsuficiente(tamanho);
			List<Pagina> list = gm.alocarMemoria(tamanho);
			p = new Processo(id, tamanho, new TabelaDePaginas(list.size(),list));
		}
		// Colocar na lista do escalonador
		listaProcessos.put(id, p);
	}

	public void le(int id, int pos) throws TamanhoInsuficiente{
		Processo p = this.listaProcessos.get(id);
		int endFisico = this.descobreEnderecoFisico(p, pos);
		// Executa
		gm.ler(p, endFisico);
		// Retorna		
	}
	
	public void escreve(int id, int pos) throws TamanhoInsuficiente{
		Processo p = this.listaProcessos.get(id);
		int endFisico = this.descobreEnderecoFisico(p, pos);
		// Executa
		gm.escrever(p, endFisico);
		// Retorna		
	}
	
	public void processa(int id, int pos) throws TamanhoInsuficiente{
		Configuracao confs = Configuracao.obterInstancia();
		// Resolve endereco: n da pagina + offset
		int nPagina = pos/confs.getTamanhoPagina();

		Processo p = this.listaProcessos.get(id);
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
	
	public int descobreEnderecoFisico(Processo p, int pos) throws TamanhoInsuficiente{
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
			// Se ocorreu falta de pagina, logo ou a pagina esta em swapp
			// ou em ms
			Pagina pagina = tp.getPagina(nPagina);
			// Nao esta presente, pois ocorreu falta de pagina
			if(pagina != null)
				endFisico = tratarSwappIn(nPagina, p).getEndFisico();
			else
				endFisico = tratarPaginaMS(nPagina, p).getEndFisico();
			
		}
		return endFisico;
	}
	
	public GerenciadorMemoria obterGerenciadorMP(){
		return this.gm;
	}
	
	public GerenciadorDispositivo obterGerenciadorDP(){
		return this.gp;
	}
	
	public GerenciadorDisco obterGerenciadorMS(){
		return this.gd;
	}
	
	public List<Processo> todosProcessos(){
		return Collections.list(this.listaProcessos.elements());
	}

}
