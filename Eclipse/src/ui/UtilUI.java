package ui;

import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import recursos.Pagina;

public class UtilUI {
	public static <T> ObservableList<T> getObservableList(List<T> objetos) {
		return FXCollections.<T>observableArrayList(objetos);
	}
	
	public static TableColumn<Pagina, Date> getUltUtilCol() {
		TableColumn<Pagina, Date> paginaUltUtilCol = new TableColumn<>("Ultima Utilização");
		paginaUltUtilCol.setCellValueFactory(new PropertyValueFactory<>("paginaUltUtil"));
		return paginaUltUtilCol;
	}
	
	public static TableColumn<Pagina, Boolean> getUtilCol() {
		TableColumn<Pagina, Boolean> utilCol = new TableColumn<>("Utilizado");
		utilCol.setCellValueFactory(new PropertyValueFactory<>("utilizado"));
		return utilCol;
	}
	
	// Returns Last Name TableColumn  
	public static TableColumn<Pagina, Boolean> getPresCol() {
		TableColumn<Pagina, Boolean> presencaCol = new TableColumn<>("Presença");
		presencaCol.setCellValueFactory(new PropertyValueFactory<>("presenca"));
		return presencaCol;
	}
}