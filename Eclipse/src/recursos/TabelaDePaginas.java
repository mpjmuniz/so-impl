package recursos;

import java.util.ArrayList;
import java.util.List;

public class TabelaDePaginas {

	private int tamanho;
	private List<Pagina> paginas;

	public TabelaDePaginas(int linhas, int tamanhoPagina) {
		this.tamanho = linhas * tamanhoPagina;
		this.paginas = new ArrayList<>(linhas);
	}

	public int getTamanho() {
		return this.tamanho;
	}

	public List<Pagina> getPaginas() {
		return paginas;
	}
}
