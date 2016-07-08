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
import recursos.GerenciadorDisco;
import recursos.GerenciadorMemoria;
import recursos.Processo;
import so.Escalonador;
import so.Kernel;
import so.Swapper;

public class Controlador {
	private Window fundo = null;

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

		abaMemoria = new AbaRecursos("Memória Principal", new GerenciadorMemoria());
		abaDisco = new AbaRecursos("Memória Secundária", new GerenciadorDisco());
		abaProcessos = new AbaProcessos("Processos", new Escalonador());

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

			Kernel k = new Kernel();

			// Jogar pro kernel (estava no Entrada.java) 
			GerenciadorMemoria memoria = new GerenciadorMemoria();
			GerenciadorDisco disco = new GerenciadorDisco();
			Escalonador esc = new Escalonador();
			Swapper swp = new Swapper();

			Processo atual;

			while (leitor.hasNextLine()) {
				linha = leitor.nextLine();
				partes = linha.split(" ");

				// Obter processo
				atual = k.obterProcesso(partes[0].charAt(1));

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
