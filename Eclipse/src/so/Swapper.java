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
	
	public Swapper(GerenciadorMemoria gm, GerenciadorDisco gd){
		this.gm = gm;
		this.gd = gd;
	}
	
	/*
	 * 	swap-in-processo: Trazer da mem√≥ria processo inteiro
	 * */
	public void swapIn(Processo p){
		
	}
	
	/*
	 * 	swap-out-processo: Guardar na mem√≥ria processo inteiro
	 * */
	public void swapOut(Processo p){
		
	}
	
	/*
	 *	Swap-in: Traz p√°gina da mem√≥ria secund√°ria para a mem√≥ria principal
	 * */
	public Pagina swapIn(Pagina p) throws TamanhoInsuficiente{
		Configuracao confs = Configuracao.obterInstancia();
		//Tenta alocar memÔøΩria
		Pagina pagMP = gm.alocarMemoria(confs.getTamanhoPagina()).get(0);
		// Se estava em swapp estÔøΩ modificada
		pagMP.modificar();
		//Tira pÔøΩgina da MS e coloca na MP
		gd.liberaPagina(p);
		return pagMP;
	}
	
	/*
	 * 	Swap-out: Guarda p√°gina na mem√≥ria
	 * */
	public abstract void swapOut(int tamanho);
	
	protected void _swapOut(Pagina p){
		// Se est· modificado salva no disco
		
		// Caso precise gravar a p·gina na MS deve-se alocar memÛria na MS
		// e colocar a pagina p de volta na lista de livres da MP
		// para tanto o processo que tinha a p·gina p alocada deve ter esta entrada
		// removida de sua tabela de p·ginas
		// PossÌveis resoluÁıes
		// observador para modficaÁ„o nas paginas?
		// percorrer lista de processos no kernel e resolver para cada tp de cada processo?
			
	}
	
	/*
	 * 	Retorna endere√ßo da p√°gina eleita para substitui√ß√£o
	 * */
	private int leastRecentlyUsed(){
		
		/*
		 * Primeira implementa√ß√£o.
		 * Possibilidades, caso haja tempo:
		 * 	- Usar heaps, atualizar √°rvore a cada utiliza√ß√£o das p√°ginas
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
