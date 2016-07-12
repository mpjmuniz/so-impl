package ui;

import java.net.URL;
import java.util.ResourceBundle;

import controle.Configuracao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ControladorAbaConfiguracao {

	@FXML
	GridPane base;
	
	@FXML
	TextField tfQtdInicialPaginas,
			  tfTamanhoPagina,
			  tfEndLogico,
			  tfTamanhoMP,
			  tfTamanhoMS,
			  tfTamanhoMaximoProcesso;
	
	@FXML
	private URL location;

	@FXML
	private ResourceBundle resources;

	public ControladorAbaConfiguracao() {
	}
	
	@FXML
	private void initialize() {
	}
	
	public void initData(Configuracao conf) {
		tfQtdInicialPaginas.setText(Integer.toString(conf.getQuantidadeInicialPaginas()));
		tfTamanhoMaximoProcesso.setText(Integer.toString(conf.getTamanhoMaximoProcesso()));
		tfEndLogico.setText(Integer.toString(conf.getEnderecoLogico()));
		tfTamanhoMP.setText(Integer.toString(conf.getTamanhoTotalMP()));
		tfTamanhoMS.setText(Integer.toString(conf.getTamanhoTotalMS()));
		tfTamanhoPagina.setText(Integer.toString(conf.getTamanhoPagina()));		
		
	}
	
	

}
