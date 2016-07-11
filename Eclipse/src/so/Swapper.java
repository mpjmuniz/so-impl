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
	 * 	swap-in-processo: Trazer da memÃ³ria processo inteiro
	 * */
	public void swapIn(Processo p){
		
	}
	
	/*
	 * 	swap-out-processo: Guardar na memÃ³ria processo inteiro
	 * */
	public void swapOut(Processo p){
		
	}
	
	/*
	 *	Swap-in: Traz pÃ¡gina da memÃ³ria secundÃ¡ria para a memÃ³ria principal
	 * */
	public Pagina swapIn(Pagina p) throws TamanhoInsuficiente{
		Configuracao confs = Configuracao.obterInstancia();
		//Tenta alocar memï¿½ria
		Pagina pagMP = gm.alocarMemoria(confs.getTamanhoPagina()).get(0);
		// Se estava em swapp estï¿½ modificada
		pagMP.modificar();
		//Tira pï¿½gina da MS e coloca na MP
		gd.liberaPagina(p);
		return pagMP;
	}
	
	/*
	 * 	Swap-out: Guarda pagina na memoria secundaria
	 * */
	public abstract void swapOut(int tamanho) throws TamanhoInsuficiente;
	
	// Retira efetivamente uma pagina p da MP
	protected void _swapOut(Pagina p) throws TamanhoInsuficiente{
		// Procurar processo que possui a página
		Processo alvo = null;
		for(Processo pros: k.todosProcessos()){
			if(pros.getTabela().getPaginas().contains(p)) alvo = pros;
		}
		int nPagina = alvo.getTabela().getKey(p);
		// Se está modificado salva no disco
		if(p.isModificado()){
			// TODO pagina está presente? Não deveria.
			Pagina pagDisco = gd.alocarMemoria(1).get(0);
			alvo.getTabela().substituiPagina(nPagina, pagDisco);;
			gm.liberarMemoria(p);
		} else {
			// Dissocia página de processo
			alvo.getTabela().removePagina(nPagina);
			gm.liberarMemoria(p);
		}			
	}
	
	

}
