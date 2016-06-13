package so;

import java.util.ArrayList;
import java.util.List;

import ambiente.Pagina;

public class TabelaDePaginas{
	List<Pagina> paginas;

	public TabelaDePaginas(int linhas){
		if(linhas <= 0) 
			throw new IllegalArgumentException("Quantidade de linhas inválida.");
		
		paginas = new ArrayList<Pagina>(linhas);
	}
}
