package so;

import recursos.Processo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import controle.Singleton;

public class Escalonador extends Singleton{
	
	private static final long serialVersionUID = -4403178317571188702L;
	
	private Queue<Processo> filaProntos = new LinkedList<Processo>();
	private List<Processo> suspensos = new ArrayList<Processo>();
	private List<Processo> executando = new ArrayList<Processo>();
	private HashMap<String, GerenciadorRecurso> bloqueados = new HashMap<>();
	
	public Escalonador(){
		super.obterInstancia("src/resources/escalonador.ser");
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