package ui;

import java.net.URL;
import java.util.ResourceBundle;

import controle.Configuracao;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ControladorAbaConfiguracao {

	private Configuracao conf;
	
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
		this.conf = conf;
		tfQtdInicialPaginas.setText(Integer.toString(conf.getQuantidadeInicialPaginas()));
		tfTamanhoMaximoProcesso.setText(Integer.toString(conf.getTamanhoMaximoProcesso()));
		tfEndLogico.setText(Integer.toString(conf.getEnderecoLogico()));
		tfTamanhoMP.setText(Integer.toString(conf.getTamanhoTotalMP()));
		tfTamanhoMS.setText(Integer.toString(conf.getTamanhoTotalMS()));
		tfTamanhoPagina.setText(Integer.toString(conf.getTamanhoPagina()));		
		
		tfQtdInicialPaginas.textProperty().addListener(this::qtdInicialMudou);
		tfTamanhoMaximoProcesso.textProperty().addListener(this::tamanhoMaxMudou);
		tfEndLogico.textProperty().addListener(this::endLogMudou);
		tfTamanhoMP.textProperty().addListener(this::tamMPMudou);
		tfTamanhoMS.textProperty().addListener(this::tamMSMudou);
		tfTamanhoPagina.textProperty().addListener(this::tamPagMudou);	
	}
	
	public void qtdInicialMudou(ObservableValue<? extends String> prop, 
            String oldValue, 
            String newValue) {
		conf.setQuantidadeInicialPaginas(Integer.parseInt(newValue));
	}
	
	public void tamanhoMaxMudou(ObservableValue<? extends String> prop, 
            String oldValue, 
            String newValue) {
		conf.setTamanhoMaximoProcesso(Integer.parseInt(newValue));
	}
	
	public void endLogMudou(ObservableValue<? extends String> prop, 
            String oldValue, 
            String newValue) {
		conf.setEnderecoLogico(Integer.parseInt(newValue));
	}
	public void tamMPMudou(ObservableValue<? extends String> prop, 
            String oldValue, 
            String newValue) {
		/* TODO: implementar aplicação das configurações de tamanho MP, MS*/
		//conf.setTamanhoTotalMP(Integer.parseInt(newValue));
	}
	public void tamMSMudou(ObservableValue<? extends String> prop, 
            String oldValue, 
            String newValue) {
		/* TODO: implementar aplicação das configurações de tamanho MP, MS*/
		//conf.setTamanhoTotalMS(Integer.parseInt(newValue));
	}
	public void tamPagMudou(ObservableValue<? extends String> prop, 
            String oldValue, 
            String newValue) {
		/* TODO: implementar aplicação das configurações de tamanho MP, MS*/
		//conf.setTamanhoPagina(Integer.parseInt(newValue));
	}
}
