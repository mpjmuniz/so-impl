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
		
			while(leitor.hasNextLine()){
				linha = leitor.nextLine();
				partes = linha.split(" ");

				//Obter processo
				int processID = Integer.parseInt(partes[0].substring(1));
				
				//Obter ação
				int index, pos;
				switch(partes[1].charAt(0)){
					case 'C':
						int tam = Integer.parseInt(partes[2]);
						k.criarProcesso(processID, tam);
						break;
					case 'R':
						index = partes[2].indexOf(')');
						pos = Integer.parseInt(partes[2].substring(1, index));
						k.le(processID, pos);
						break;
					case 'P':
						index = partes[2].indexOf(')');
						pos = Integer.parseInt(partes[2].substring(1, index));
						k.processa(processID, pos);
						break;
					case 'W':
						index = partes[2].indexOf(')');
						pos = Integer.parseInt(partes[2].substring(1, index));
						k.escreve(processID, pos);
						break;
					case 'I':
						index = partes[2].indexOf(')');
						int dispositivo = Integer.parseInt(partes[2].substring(1, index));
						k.usaDispositivo(processID, dispositivo);
						break;
					default:
						break;
				}
				
				//Obter parâmetro (definir aqui tamanhos de página, quadro, etc)
				System.out.println(linha);
			}
			leitor.close();
		} catch (FileNotFoundException e) {
			
		}
	}
}
