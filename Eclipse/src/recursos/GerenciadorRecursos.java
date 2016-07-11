package recursos;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import controle.Configuracao;

public abstract class GerenciadorRecursos {

	protected static Configuracao confs = Configuracao.obterInstancia();
	
	protected int tamanhoTotal;
	protected int tamanhoDisponivel;
	protected int tempoLeitura;
	protected List<Pagina> quadros;
	protected List<Pagina> livres;

	protected Queue<Processo> aguardando;
	
	public GerenciadorRecursos(int tamanhoTotal){
		this.tamanhoTotal = tamanhoTotal;
		this.tamanhoDisponivel = tamanhoTotal;
		
		int tamanho = tamanhoTotal / confs.getTamanhoPagina();
		
		this.quadros = new ArrayList<>(tamanho);
		//TODO otimizar
		this.livres = new ArrayList<>(tamanho);
		
		Pagina atual;
		
		for(int i = 0; i < tamanho; i++){
			atual = new Pagina(i);
			
			this.quadros.add(atual);
			this.livres.add(atual);
		}
		
		this.aguardando = new LinkedBlockingDeque<Processo>();
	}
	
	public int getTamanhoTotal() {
		return this.tamanhoTotal;
	}

	public int getTamanhoDisponivel() {
		return this.tamanhoDisponivel;
	}
	
	/*	Tendo em mãos o endereço físico gerado pelo processador,
	 * 	usamo-lo para ler/escrever na memoria 
	 *  
	 *  Note que a verificação de limites da tabela de página do processo não fica aqui
	 * */
	public void ler(Processo p, int enderecoFisico) {
		aguardando.add(p);
		quadros.get(enderecoFisico).ler();
	}
	
	public void escrever(Processo p, int enderecoFisico) {
		aguardando.add(p);
		quadros.get(enderecoFisico).modificar();
	}
	
	public Queue<Processo> getFila(){
		return this.aguardando;
	} 
	
	public List<Pagina> getQuadros(){
		return this.quadros;
	}
}