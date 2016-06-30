package main;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import so.Escalonador;
import so.GerenciadorDisco;
import so.GerenciadorMemoria;
import so.Kernel;
import recursos.Processo;
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
			GerenciadorMemoria memoria = new GerenciadorMemoria(1024 * 1024, tamanhoPagina);
			GerenciadorDisco disco = new GerenciadorDisco(1024 * 1024 * 1024, tamanhoPagina);
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
