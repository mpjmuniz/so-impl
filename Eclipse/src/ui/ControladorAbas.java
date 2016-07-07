package ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import recursos.GerenciadorDisco;
import recursos.GerenciadorMemoria;
import so.Escalonador;
import so.Kernel;

public class ControladorAbas {
		// The reference of msgLbl will be injected by the FXML loader
		@FXML
		private TabPane baseAbas;

		// location and resources wil be automatically injected by the FXML laoder
		@FXML 
		private URL location;

		@FXML 
		private ResourceBundle resources;
		
		// Add a public no-args construtcor explicitly just to 
		// emphasize that it is needed for a controller
		public ControladorAbas() {
		}

		@FXML
		private void initialize() {
			AbaRecursos abaMemoria, abaDisco;
			AbaProcessos abaProcessos;
			abaMemoria = new AbaRecursos("Memória Principal", new GerenciadorMemoria());
			abaDisco = new AbaRecursos("Memória Secundária", new GerenciadorDisco());
			//abaProcessos = new AbaProcessos("Processos", new Kernel());
			
			
			//baseAbas.getTabs().addAll(abaDisco, abaMemoria, abaProcessos);
		}
		
		@FXML
		private void selecionarAba() {
			
		}
}