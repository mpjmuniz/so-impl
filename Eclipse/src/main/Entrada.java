package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import controle.Configuracao;
import recursos.GerenciadorDisco;
import recursos.GerenciadorMemoria;
import recursos.Processo;
import so.Escalonador;
import so.Kernel;
import so.Swapper;

public class Entrada{
	public static void main(String[] args){
		/*
		 *	TODO: 
		 *		- Ler arquivo
		 *
		 * */
		try{
			Scanner leitor = new Scanner(new File("src/resources/entrada.txt"));
			String linha;
			String[] partes;
			/*TODO: tornar o tamanhoPagina mais interno*/
			Configuracao conf = Configuracao.obterInstancia();
			conf.setTamanhoPagina(512);
			
			/* Jogar pro kernel */
			GerenciadorMemoria memoria = new GerenciadorMemoria();
			GerenciadorDisco disco = new GerenciadorDisco();
			Escalonador esc = new Escalonador();
			Swapper swp = new Swapper();
			
			Kernel k = new Kernel(memoria, disco, esc, swp);
		
			Processo atual;

			while(leitor.hasNextLine()){
				linha = leitor.nextLine();
				partes = linha.split(" ");

				//Obter processo
				int processID = Integer.parseInt(partes[0].substring(1));
				
				//Obter ação
				switch(partes[1].charAt(0)){
					case 'C':
						int tam = Integer.parseInt(partes[2]);
						k.criarProcesso(processID, tam);
						break;
					case 'R':
						int index = partes[2].indexOf('(');
						int pos = Integer.parseInt(partes[2].substring(0, index));
						k.processoLe(processID, pos);
						break;
					case 'P':
						break;
					case 'W':
						break;
					case 'I':
						break;
					default:
						break;
				}
				
				//Obter parâmetro (definir aqui tamanhos de página, quadro, etc)
				System.out.println(linha);
			}
		} catch (FileNotFoundException e) {
			
		}
	}
}
