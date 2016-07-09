package ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import recursos.*;
import so.*;

public class Controlador {
	private Window fundo = null;
	private Kernel kernel;

	private FileChooser navegadorArquivos = new FileChooser();

	@FXML
	private TabPane baseAbas;

	@FXML
	private TextField tfComando;

	@FXML
	private Button bContinuar, bCarregar, bPausar, bAndar, bzerar;

	@FXML
	private URL location;

	@FXML
	private ResourceBundle resources;

	public Controlador() {
	}

	@FXML
	private void initialize() {
		

		AbaRecursos abaMemoria, abaDisco;
		AbaProcessos abaProcessos;
		GerenciadorDisco gd = new GerenciadorDisco();
		GerenciadorMemoria gm = new GerenciadorMemoria();
		Escalonador esc = new Escalonador();
		Swapper swp = new SwapperRelogio(gm, gd);
		this.kernel = new Kernel(gm, gd, esc, swp);
		abaMemoria = new AbaRecursos("Memória Principal", gm);
		abaDisco = new AbaRecursos("Memória Secundária", gd);
		abaProcessos = new AbaProcessos("Processos", kernel);

		baseAbas.getTabs().addAll(abaDisco, abaMemoria, abaProcessos);
	}

	@FXML
	private void selecionarAba() {

	}

	@FXML
	private void carregarArquivo() {
		fundo = bCarregar.getScene().getWindow();
		File arquivo;

		this.navegadorArquivos.setTitle("Selecione um arquivo de " + " comportamento dos processos:");

		arquivo = navegadorArquivos.showOpenDialog(fundo);

		if (arquivo == null) {
			return;
		}
		
		try {

			// TODO: Fazer o que tiver que ser feito com o Kernel

			Scanner leitor = new Scanner(arquivo);
			String linha;
			String[] partes;


			Processo atual;

			while (leitor.hasNextLine()) {
				linha = leitor.nextLine();
				tfComando.setText(linha);
				
				partes = linha.split(" ");

				// Obter processo
				atual = kernel.obterProcesso(partes[0].charAt(1));

				// Obter ação
				switch (partes[1].charAt(0)) {
				case 'C':
					break;
				case 'R':
					break;
				case 'P':
					break;
				case 'W':
					break;
				case 'I':
						//Código depois será passado para o kernel
						
					break;
				default:
					break;
				}
				
				System.out.println(linha);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void continuar() {
		// TODO: Pós implementação do temporizador
	}

	@FXML
	private void pausar() {
		// TODO: Pós implementação do temporizador
	}

	@FXML
	private void andar() {
		// TODO: Pós implementação do temporizador
	}

	@FXML
	private void zerar() {
		// TODO: Pós implementação do temporizador
	}
}
