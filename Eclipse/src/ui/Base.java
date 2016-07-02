package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Base extends Application {

	@Override
	public void start(Stage fundo) throws Exception {
		
		/*	Abas: */
		TabPane base = new TabPane();
		/*	Rodapé:
		 * 	- Linha para execução de código
		 * 	- Botão para execução
		 * 	- Botão para seleção de arquivo
		 * 	- Botão para Selecionar algoritmo
		 * */
		
		TextField comando = new TextField();
		Button 	executar = new Button("Executar"),
				carregarArquivo = new Button("Carregar um Arquivo"),
				pausar = new Button("Pausar"),
				andar = new Button("Andar"),
				zerar = new Button("Zerar");
		
		Label msg = new Label();
		
		msg.setStyle("-fx-text-fill: blue;");
		comando.setPrefWidth(512);

		HBox rodape = new HBox(10);

		rodape.getChildren().addAll(carregarArquivo, comando, executar, pausar, andar, zerar);
		
		GridPane estrutura = new GridPane();
		
		estrutura.addRow(0, rodape);
		
		Scene scene = new Scene(estrutura, 1024, 700);
		fundo.setScene(scene);
		fundo.setTitle("Gerenciador de Memória Virtual");
		fundo.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
