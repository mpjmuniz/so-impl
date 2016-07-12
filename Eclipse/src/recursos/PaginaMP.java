package recursos;

public class PaginaMP extends Pagina {

	public PaginaMP(int endFisico) {
		super(endFisico);
		super.presente = true;
		super.modificado = false;
		super.utilizado = true;
	}
	
	public void desalocar(){
		super.desalocar();
		super.modificado = false;
	}
	
	public String toString(){
		return  "Processo: \t" + ((this.dono != null) ? this.dono.getId() : "x") + "\n"
				+ "Mod? \t\t" + ((this.modificado) ? "v" : "x") + "\n"
				+ "Util? \t\t" + ((this.utilizado) ? "v" : "x") + "\n"
				+ "Ultima Util.: " + this.ultimaUtilizacao.toString().substring(10, 19) + "\n"
				+ "End. Fisico: " + Long.toString(endFisico) + "\n";
	}
}
