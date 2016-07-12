package recursos;

public class PaginaMP extends Pagina {

	public PaginaMP(int endFisico) {
		super(endFisico);
		super.presente = true;
		super.modificado = false;
		super.utilizado = false;
	}
	
	public void desalocar(){
		super.desalocar();
		super.modificado = false;
	}
}
