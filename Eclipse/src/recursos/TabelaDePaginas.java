package recursos;

import java.util.List;

import controle.Configuracao;

public class TabelaDePaginas {

	private static Configuracao confs = Configuracao.obterInstancia();
	private int tamanho;
	private List<Pagina> paginas;

	public TabelaDePaginas(int linhas, List<Pagina> pgs) {
		this.tamanho = linhas * confs.getTamanhoPagina();
		this.paginas = pgs;
	}

	public int getTamanho() {
		return this.tamanho;
	}

	public List<Pagina> getPaginas() {
		return paginas;
	}
}