package ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import recursos.Pagina;
import recursos.Processo;
import so.Kernel;

public class ControladorAbaProcessos {

	@FXML
	private Accordion acProcessos;

	@FXML
	private URL location;

	@FXML
	private ResourceBundle resources;

	public ControladorAbaProcessos() {
	}

	@FXML
	private void initialize() {
	}

	public void initData(Kernel ker) {
		List<TitledPane> processos = new ArrayList<>();

		for (Processo p : ker.todosProcessos()) {
			processos.add(criarPainelProcesso(p));
		}

		acProcessos.getPanes().addAll(processos);
	}

	@SuppressWarnings("unchecked")
	public TitledPane criarPainelProcesso(Processo p) {

		GridPane grid = new GridPane();
		TableView<Pagina> tabela;

		grid.addRow(0, new Label("Estado:"), new Label(p.getEstado().toString()));
		grid.addRow(1, new Label("Tabela de Paginas:"));

		tabela = new TableView<>(UtilUI.getObservableList(p.getTabela().getPaginas()));
		tabela.setPrefWidth(800);

		TableColumn<Pagina, String> endFisCol = new TableColumn<>("Endereço Físico");
		endFisCol.setCellValueFactory(cellData -> {
			Integer ef = cellData.getValue().getEndFisico();
			
			return new ReadOnlyStringWrapper(ef.toString());
		});
		
		TableColumn<Pagina, String> ultModCol = new TableColumn<>("Última Modificação");
		ultModCol.setCellValueFactory(cellData -> {
			Date dum = cellData.getValue().getUltimaUtilizacao();
			
			return new ReadOnlyStringWrapper(dum.toString());
		});
		
		TableColumn<Pagina, Boolean> presenteCol = new TableColumn<>("Presente?");
		presenteCol.setEditable(false);
		
		// Set a cell value factory
		presenteCol.setCellValueFactory(cellData -> {
	        Pagina pag = cellData.getValue();
			Boolean v =  pag.isPresente();
			return new ReadOnlyBooleanWrapper(v);
		});
		
		TableColumn<Pagina, Boolean> modificadoCol = new TableColumn<>("Modificado?");
		modificadoCol.setEditable(false);
		
		// Set a cell value factory
		modificadoCol.setCellValueFactory(cellData -> {
	        Pagina pag = cellData.getValue();
			Boolean v =  pag.isModificado();
			return new ReadOnlyBooleanWrapper(v);
		});
		
		TableColumn<Pagina, Boolean> utilizadoCol = new TableColumn<>("Utilizado?");
		utilizadoCol.setEditable(false);
		
		// Set a cell value factory
		utilizadoCol.setCellValueFactory(cellData -> {
	        Pagina pag = cellData.getValue();
			Boolean v =  pag.isModificado();
			return new ReadOnlyBooleanWrapper(v);
		});
		
		tabela.getColumns().addAll(endFisCol, 
								   ultModCol,
								   presenteCol,
								   modificadoCol,
								   utilizadoCol);

		grid.addRow(2, tabela);

		return new TitledPane("Processo " + p.getId(), grid);

	}

	Accordion getBase() {
		return this.acProcessos;
	}

}
