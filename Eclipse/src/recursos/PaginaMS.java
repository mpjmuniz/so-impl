package recursos;

public class PaginaMS extends Pagina {

	public PaginaMS(int endFisico) {
		super(endFisico);
		super.presente = false;
		super.modificado = true;
	}

}
