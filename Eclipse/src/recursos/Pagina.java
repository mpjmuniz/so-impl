package recursos;

import java.util.Date;

public abstract class Pagina implements Comparable {

	protected Date ultimaUtilizacao;
	protected boolean modificado;
	protected boolean utilizado;
	protected boolean presente;
	protected int endFisico;
	protected Processo dono;

	public Pagina(int endFisico){
		this.ultimaUtilizacao = new Date();
		this.endFisico =  endFisico;
	}
	
	public Processo getProcesso(){
		return dono;
	}
	
	public int getEndFisico(){
		return this.endFisico;
	}
	
	public Date getUltimaUtilizacao() {
		return ultimaUtilizacao;
	}

	public boolean isModificado() {
		return this.modificado;
	}
	
	/*	Modificar Pagina*/
	public void modificar(){
		this.modificado = true;
		this.ultimaUtilizacao = new Date();		
	}
	
	public void ler(){
		this.ultimaUtilizacao = new Date();
		this.utilizado = true;
	}
	
	public void alocar(Processo p){
		this.dono = p;
		this.ultimaUtilizacao = new Date();
		this.utilizado = true;
	}
	
	/*	Para o algoritmo do Relogio*/
	public void inutilizado(){
		this.utilizado = false;
	}
	
	public void utilizado(){
		this.utilizado = true;
	}

	public boolean isUtilizado() {
		return utilizado;
	}

	public boolean isPresente() {
		return presente;
	}
	
	public void limpar(){
		this.modificado = false;
		this.utilizado = false;
	}
	
	@Override
	public String toString(){
		return "Mod? " + ((this.modificado) ? "v" : "x") + "\n"
				+ "Pres? " + ((this.presente) ? "v" : "x") + "\n"
				+ "Util? " + ((this.utilizado) ? "v" : "x") + "\n"
				+ "Últ. Util.: " + this.ultimaUtilizacao.toString().substring(10, 19) + "\n"
				+ "End. Fisico: " + Long.toString(endFisico) + "\n";
	}

	@Override
	public int compareTo(Object o) {
		Pagina p = (Pagina) o;
		return this.endFisico - p.endFisico;
	}
}