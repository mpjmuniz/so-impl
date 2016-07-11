package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import excecoes.ComandoInvalido;
import excecoes.ProcessoInexistente;
import excecoes.TamanhoInsuficiente;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import recursos.Processo;
import so.Kernel;

public class Controlador {
	
	//TODO: Ajustar tratamento de exceções para mostrar mensagens amigáveis
	
	//Interface
	
	private int contadorProcessos = 0;
	
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

	private AbaRecursos abaMemoria, 
							abaDisco;
	
	private AbaProcessos abaProcessos;
	
	// SO
	
	private Kernel kernel;
	//private ObservableMap<Processo, Integer> map2 = FXCollections.observableMap(backingMap);
	
	// Interno
	
	private String instrucao;
	private Scanner leitor = null;
	private File arquivo = null;
	
	private boolean emExecucao;
		
	public Controlador() {
	}

	@FXML
	private void initialize() {
		kernel = new Kernel();
		
		abaMemoria = new AbaRecursos("Memória Principal", kernel.obterGerenciadorMP());
		abaDisco = new AbaRecursos("Memória Secundária", kernel.obterGerenciadorMS());
		
		//Swapper swp = new SwapperRelogio(gm, gd);

		abaProcessos = new AbaProcessos("Processos", kernel);

		baseAbas.getTabs().addAll(abaDisco, abaMemoria, abaProcessos);
		
		emExecucao = false;
	}

	@FXML
	private void carregarArquivo() {
		
		fundo = bCarregar.getScene().getWindow();
		
		this.navegadorArquivos.setTitle("Selecione um arquivo de " 
				+ " comportamento dos processos:");

		arquivo = navegadorArquivos.showOpenDialog(fundo);		
		
		if(arquivo == null) return;
		
		try {
			leitor = new Scanner(arquivo);
			
			instrucao = leitor.nextLine();
			
			tfComando.setText(instrucao);
		} catch (FileNotFoundException e) {
			alertar(e.getMessage(), "Arquivo não encontrado.");
		}
	}

	@FXML
	private void continuar() {
		if(emExecucao) return;
		
		if(instrucao == null || instrucao == "" || leitor == null){
			
			alertar("Não há uma instrução a ser executada.", "Sem instruções");
			return;
		}
		
		emExecucao = true;
		
		try{
			processarInstrucoes();
		} catch (ComandoInvalido e){
			alertar(e.getMessage(), "Erro na execução do comando: ");
		}
		
		
		emExecucao = false;
	}

	@FXML
	private void pausar() {
		if(emExecucao) emExecucao = false;
	}

	@FXML
	private void andar(){
		if(emExecucao) return;
		
		
		emExecucao = true;
		try {
			processarInstrucao(instrucao);
		} catch (ComandoInvalido e) {
			alertar(e.getMessage(), "Erro na execução do comando: ");
		}
		emExecucao = false;
		
		instrucao = (leitor == null) ? tfComando.getText() : leitor.nextLine();
		tfComando.setText(instrucao);
	}

	@FXML
	private void zerar() {
		if(arquivo == null) return;
		
		try {
			leitor = new Scanner(arquivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		emExecucao = false;
	}
	
	private void processarInstrucao(String instrucao) throws ComandoInvalido{
		//Possivelmente passará para o Kernel
		String[] partes;
		Processo atual = null;
		
		if(instrucao == null || "".equals(instrucao)){
			alertar("Comando inválido", "Comando Inválido");
			return;
		}
			
		
		partes = instrucao.split(" ");
		
		if(partes[1].charAt(0) == 'C'){
		} else {
			alertar(partes[1], "debug");
			try{
				atual = kernel.obterProcesso(partes[0].charAt(1));
			} catch(ProcessoInexistente e){
				alertar(e.getMessage(), "Processo não existe");
				
				return;
			}
		}
		

		// Obter ação
		switch (partes[1].charAt(0)) {
		case 'C':
			try {
				kernel.criarProcesso(partes[0].charAt(1), Integer.parseInt(partes[2]));
			} catch (NumberFormatException e){
				e.printStackTrace();
			} catch(TamanhoInsuficiente e) {
				alertar("Tamanho Insuficiente de memoria", "Erro na criacao do processo");
			}
			
			break;
		case 'R':
			try {
				int ini = partes[2].indexOf('(');
				int fim = partes[2].indexOf(')');
				kernel.le(partes[0].charAt(1), Integer.parseInt(partes[2].substring(ini+1, fim)));
			} catch (NumberFormatException e){
				e.printStackTrace();
			} catch(TamanhoInsuficiente e) {
				alertar("Tamanho Insuficiente de memoria", "Erro na criacao do processo");
			}
			break;
		case 'P':
			break;
		case 'W':
			try {
				int ini = partes[2].indexOf('(');
				int fim = partes[2].indexOf(')');
				kernel.escreve(partes[0].charAt(1), Integer.parseInt(partes[2].substring(ini+1, fim)));
			} catch (NumberFormatException e){
				e.printStackTrace();
			} catch(TamanhoInsuficiente e) {
				alertar("Tamanho Insuficiente de memoria", "Erro na criacao do processo");
			}
			break;
		case 'I':
			kernel.obterGerenciadorDP().ler(atual, Integer.parseInt(partes[2]));
			break;
		default:
			throw new ComandoInvalido("Comando \"" + partes[0] + "\" nao implementado. ");
		}
	}
	
	private void processarInstrucoes() throws ComandoInvalido{
			
		while (leitor.hasNextLine() && emExecucao) {
			
			instrucao = leitor.nextLine();
			tfComando.setText(instrucao);
			processarInstrucao(instrucao);
			
		}
		
		leitor.close();
		leitor = null;
	}
	
	private void alertar(String mensagem, String topo){
		Stage aviso = new Stage();
		aviso.initOwner(fundo);
		aviso.initModality(Modality.APPLICATION_MODAL);
		
		Label lAviso = new Label(mensagem);
					
		VBox root = new VBox();
		root.setPadding(new Insets(20));
		root.getChildren().add(lAviso);
		
		Scene scene = new Scene(root, 300, 100);
		aviso.setScene(scene); 
		aviso.setTitle(topo);
		aviso.show();
	}
}
