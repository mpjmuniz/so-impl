package ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import recursos.GerenciadorRecursos;

public class AbaRecursos extends Tab {
	
	private HBox base;
	private GerenciadorRecursos gerRec;

	public AbaRecursos(String text, GerenciadorRecursos ger) {
		this.setText(text);
		this.gerRec = ger;

		init();
	}

	public void init(){
		ControladorAbas controlador;
		
		FXMLLoader loader = new FXMLLoader(this.getClass()
                .getClassLoader()
                .getResource("resources/fxml/abaRecursos.fxml"));
		
		try{
			base = loader.<HBox>load();
			
			controlador = loader.<ControladorAbas>getController(); 
			controlador.initData(gerRec);		
			
			this.setContent(base);
			//cena.getStylesheets().add("resources/css/estilo.css");
		} catch(IOException e){
			System.out.println("Erro no carregamento da aba " + e.getMessage());
		}
	}

}
