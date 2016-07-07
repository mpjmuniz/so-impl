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

	/*	Trazer da memória Secundária*/
	public void trazer(){
		this.presente = true;
		this.modificado = false;
	}
	
	/*	Guardar na memória Secundária*/
	public void guardar(){
		this.modificado = false;
		this.presente = false;
	}
	
	/*	Modificar Página*/
	public void modificar(int dado){
		this.modificado = true;
		this.ultimaUtilizacao = new Date();		
	}
	
	public void ler(){
		this.ultimaUtilizacao = new Date();
		this.utilizado = true;
	}
	
	/*	Para o algoritmo do Relógio*/
	public void inutilizado(){
		this.utilizado = false;
	}

	public boolean isUtilizado() {
		return utilizado;
	}

	public boolean isPresente() {
		return presente;
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