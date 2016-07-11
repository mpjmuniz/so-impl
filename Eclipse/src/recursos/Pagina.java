package recursos;

import java.util.Date;

public class Pagina {

	private Date ultimaUtilizacao;
	private boolean modificado;
	private boolean utilizado;
	private boolean presente;
	private int endFisico;

	public Pagina(int endFisico){
		this.modificado = false;
		this.presente = false;
		this.utilizado = false;
		this.ultimaUtilizacao = new Date();
		this.endFisico =  endFisico;
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
		this.presente = false;
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
}