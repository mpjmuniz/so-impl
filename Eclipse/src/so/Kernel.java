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
	
	// Pagina nï¿½o estï¿½ em MP nem em Swapp
	private Pagina tratarPaginaMS(int nPagina, Processo p) throws TamanhoInsuficiente{
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

	public Processo obterProcesso(int id) throws ProcessoInexistente {
		Processo p = listaProcessos.get(id);
		
		if(p == null) throw new ProcessoInexistente("Processo nÃ£o existe.");
		
		return p;
	}
	
	public void criarProcesso(int id, int tamanho) throws TamanhoInsuficiente{
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
			// Se ocorreu falta de pï¿½gina, logo ou a pï¿½gina estï¿½ em swapp
			// ou em ms
			Pagina pagina = tp.getPagina(nPagina);
			// Nï¿½o estï¿½ presente, pois ocorreu falta de pï¿½ginas
			if(pagina != null)
				//TODO implementaï¿½ï¿½o
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
