package ambiente;

import java.util.Date;

public class Pagina{
	/*	TODO: Verificar este tamanho, de acordo com os tamanhos do resto*/
	private int tam;
	
	/*	Para a implementação do método de subst. LRU*/
	private Date ultimaUtilização;
	
	/*Foi modificado?*/
	private boolean modificado;
	
	/*	Para a implementação do método de subst. do Relógio*/
	private boolean utilizado;
	
	/*Presente na MP*/
	private boolean presente;

	public Pagina(int tam){
		this.tam = tam;
		this.modificado = false;
		this.presente = false;
		this.utilizado = false;
		this.ultimaUtilização = new Date();
	}

	public int getTam(){
		return this.tam;
	}
	
	public Date getUltimaModificacao(){
		return this.ultimaUtilização;
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
		this.ultimaUtilização = new Date();
	}
	
	public void ler(){
		this.ultimaUtilização = new Date();
		this.utilizado = true;
	}
	
	/*	Para o algoritmo do Relógio*/
	public void inutilizado(){
		this.utilizado = false;
	}
}
