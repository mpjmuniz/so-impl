package so;

import recursos.GerenciadorRecursos;
import recursos.Processo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Escalonador{

	
	private Queue<Processo> filaProntos = new LinkedList<Processo>();
	private List<Processo> suspensos = new ArrayList<Processo>();
	private List<Processo> executando = new ArrayList<Processo>();
	private HashMap<String, GerenciadorRecursos> bloqueados = new HashMap<>();
	
	public Escalonador(){
		
	}
	
	public void criarProcesso(int tam){
		
	}
	
	public void terminarProcesso(int pid){
		
	}
	
	public void bloquearProcesso(int pid){
		
	}
	
	public void suspenderProcesso(int pid){
		
	}
	
}