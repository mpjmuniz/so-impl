package so;

import ambiente.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Escalonador {
	/*
	 * 	TODO: Implementar como Singleton
	 * */
	private static Queue<Processo> filaProntos = new LinkedList<Processo>();
	private static List<Processo> suspensos = new ArrayList<Processo>();
	private static List<Processo> executando = new ArrayList<Processo>();
	private static GerenciadorMemoria memoriaPrincipal = new GerenciadorMemoria(1024 * 1024 * 1024, 512);
	private static int contadorId = 0;
	
	public void criarProcesso(int tam){
		Processo p = new Processo(contadorId++, tam);
		int numeroDePaginas = tam / 4;
		//
		
		
	}
	
	public void terminarProcesso(int pid){
		
	}
	
	public void bloquearProcesso(int pid){
		
	}
	
	public void suspenderProcesso(int pid){
		
	}
	
}