package recursos;

import java.util.Date;

public class Pagina {

	private Date ultimaUtilizacao;
	private boolean modificado;
	private boolean utilizado;
	private boolean presente;

	public Pagina(){
		this.modificado = false;
		this.presente = false;
		this.utilizado = false;
		this.ultimaUtilizacao = new Date();
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
	public void modificar(){
		this.modificado = true;
		/*Modo mais prático e com menos gambiarra para atualizar o instante*/
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
}
