package recursos;

import java.util.Date;

public class Pagina {

	private Date ultimaUtilizacao;
	private boolean modificado;
	private boolean utilizado;
	private boolean presente;
	private int dado;

	public Pagina(){
		this.modificado = false;
		this.presente = false;
		this.utilizado = false;
		this.ultimaUtilizacao = new Date();
		this.dado = 0;
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
	
	public int ler(){
		this.ultimaUtilizacao = new Date();
		this.utilizado = true;
		return this.dado;
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
}