package so;

import java.util.ArrayList;
import java.util.List;

import controle.Singleton;
import recursos.Pagina;

abstract class GerenciadorRecurso extends Singleton{
	
	private static final long serialVersionUID = 7295802009705502822L;
	protected int tamanhoTotal;
	protected int tamanhoDisponivel;
	protected int tamanhoPagina;
	protected List<Pagina> quadros;

	public GerenciadorRecurso(int tamanhoTotal, int tamanhoPagina) {
		this.tamanhoTotal = this.tamanhoDisponivel = tamanhoTotal;
		this.tamanhoPagina = tamanhoPagina;
		this.quadros = new ArrayList<>();
	}

	public int getTamanhoTotal() {
		return tamanhoTotal;
	}

	public int getTamanhoDisponivel() {
		return tamanhoDisponivel;
	}

	public List<Pagina> getQuadros() {
		return quadros;
	}

	void setQuadros(List<Pagina> quadros) {
		this.quadros = quadros;
	}

	/**
	 * @return the tamanhoPagina
	 */
	public int getTamanhoPagina() {
		return tamanhoPagina;
	}


}