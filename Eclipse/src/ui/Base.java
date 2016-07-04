package ui;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Base extends Application {

	@Override
	public void start(Stage fundo) throws Exception {
		
		URL fxmlUrl = this.getClass()
                .getClassLoader()
                .getResource("resources/fxml/interface.fxml");
				
		VBox estrutura;
		
		try{
			estrutura = FXMLLoader.<VBox>load(fxmlUrl);
			
			Scene scene = new Scene(estrutura, 1024, 700);
			fundo.setScene(scene);
			fundo.setTitle("Gerenciador de Memória Virtual");
			fundo.show();
		} catch(IOException e){
			throw new IOException("Arquivo Corrompido");
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
