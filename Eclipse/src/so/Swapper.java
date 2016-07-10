package so;

import java.util.Date;

import controle.Configuracao;
import excecoes.TamanhoInsuficiente;
import recursos.GerenciadorDisco;
import recursos.GerenciadorMemoria;
import recursos.Pagina;
import recursos.Processo;

public abstract class Swapper {
	/*
	 *	1. Find the location of the desired page on the disk.
	 *	2. Find a free frame:
	 *	a. If there is a free frame, use it.
	 *	b. If there is no free frame, use a page-replacement algorithm to select
	 *	a victim frame.
	 *	c. Write the victim frame to the disk; change the page and frame tables
	 *	accordingly.
	 *	3. Read the desired page into the newly freed frame; change the page and
	 *	frame tables.
	 *	4. Continue the user process from where the page fault occurred.
	 */
	
	protected GerenciadorMemoria gm;
	protected GerenciadorDisco gd;
	protected Kernel k;
	
	public Swapper(GerenciadorMemoria gm, GerenciadorDisco gd, Kernel k){
		this.gm = gm;
		this.gd = gd;
		this.k = k;
	}
	
	/*
	 * 	swap-in-processo: Trazer da memória processo inteiro
	 * */
	public void swapIn(Processo p){
		
	}
	
	/*
	 * 	swap-out-processo: Guardar na memória processo inteiro
	 * */
	public void swapOut(Processo p){
		
	}
	
	/*
	 *	Swap-in: Traz página da memória secundária para a memória principal
	 * */
	public Pagina swapIn(Pagina p) throws TamanhoInsuficiente{
		Configuracao confs = Configuracao.obterInstancia();
		//Tenta alocar mem�ria
		Pagina pagMP = gm.alocarMemoria(confs.getTamanhoPagina()).get(0);
		// Se estava em swapp est� modificada
		pagMP.modificar();
		//Tira p�gina da MS e coloca na MP
		gd.liberaPagina(p);
		return pagMP;
	}
	
	/*
	 * 	Swap-out: Guarda página na memória
	 * */
	public abstract void swapOut(int tamanho) throws TamanhoInsuficiente;
	
	protected void _swapOut(Pagina p) throws TamanhoInsuficiente{
		// Procurar processo que possui a p�gina
		Processo alvo = null;
		for(Processo pros: k.todosProcessos()){
			if(pros.getTabela().getPaginas().contains(p)) alvo = pros;
		}
		int nPagina = alvo.getTabela().getKey(p);
		// Se est� modificado salva no disco
		if(p.isModificado()){
			// TODO pagina est� presente? N�o deveria.
			Pagina pagDisco = gd.alocarMemoria(1).get(0);
			alvo.getTabela().substituiPagina(nPagina, pagDisco);;
			gm.liberarMemoria(p);
		} else {
			// Dissocia p�gina de processo
			alvo.getTabela().removePagina(nPagina);
			gm.liberarMemoria(p);
		}			
	}
	
	/*
	 * 	Retorna endereço da página eleita para substituição
	 * */
	private int leastRecentlyUsed(){
		
		/*
		 * Primeira implementação.
		 * Possibilidades, caso haja tempo:
		 * 	- Usar heaps, atualizar árvore a cada utilização das páginas
		 * */
		
		int endEleito = 0;
		
		long agora = new Date().getTime(),
				tempoEleito = Integer.MAX_VALUE;
		
		for(Pagina pg : gm.getQuadros()){
			if(agora - pg.getUltimaUtilizacao().getTime() < tempoEleito){
				tempoEleito = agora - pg.getUltimaUtilizacao().getTime();
				endEleito = pg.getEndFisico();
			}
		}
		
		return endEleito;
	}
	

}
