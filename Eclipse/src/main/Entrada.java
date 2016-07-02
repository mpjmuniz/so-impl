package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
			int tamanhoPagina = 512;
			
			Kernel k = new Kernel();
			
			/* Jogar pro kernel */
			GerenciadorMemoria memoria = new GerenciadorMemoria();
			GerenciadorDisco disco = new GerenciadorDisco();
			Escalonador esc = new Escalonador();
			Swapper swp = new Swapper();
		
			Processo atual;

			while(leitor.hasNextLine()){
				linha = leitor.nextLine();
				partes = linha.split(" ");

				//Obter processo
				atual = k.obterProcesso(partes[0].charAt(1));

				//Obter ação
				switch(partes[1].charAt(0)){
					case 'C':
						break;
					case 'R':
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
