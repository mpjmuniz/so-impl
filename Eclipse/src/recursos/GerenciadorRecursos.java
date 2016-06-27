package recursos;

import java.util.List;
import java.util.Queue;

public abstract class GerenciadorRecursos {

	protected int tamanhoTotal;
	protected int tamanhoDisponivel;
	protected int tamanhoPagina;
	private List<Pagina> quadros;

	private Queue<Processo> aguardando;

	public int getTamanhoTotal() {
		return 0;
	}

	public int getTamanhoDisponivel() {
		return 0;
	}

	public int getTamanhoPagina() {
		return 0;
	}

	public List<Pagina> getQuadros() {
		return null;
	}

	void setQuadros(List<Pagina> quadros) {

	}

	public int ler(Processo p, int endereco) {
		return 0;
	}

	public void escrever(Processo p, int endereco, int dado) {

	}

}
