package ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.GridPane;
import recursos.Pagina;
import recursos.Processo;
import so.Escalonador;

/*TODO: Implementar acordiões*/

public class AbaProcessos extends Tab {
	private Accordion lista = new Accordion();
	
	private Escalonador esc;

	public AbaProcessos(String text, Escalonador esc) {
		this.setText(text);
		this.esc = esc;

		init();
	}

	public void init() {
		lista.setPrefWidth(1024);
		List<TitledPane> processos = new ArrayList<>();		
		
		for(Processo p : esc.obterProcessos()){
			processos.add(criarPainelProcesso(p));
		}
		
		lista.getPanes().addAll(processos);
		//lista.setExpandedPane(lista.getPanes().get(0));
	}
	
	@SuppressWarnings("unchecked")
	public TitledPane criarPainelProcesso(Processo p) {
		GridPane grid = new GridPane();
		TableView<Pagina> tabela;
		grid.addRow(0, new Label("Estado:"), new Label(p.getEstado().toString()));
		grid.addRow(1, new Label("Tabela de Paginas:"));
		
		tabela = new TableView<Pagina>(UtilUI.getObservableList(p.getTabela().getPaginas()));
		
		// Cria coluna de última utilização
		TableColumn<Pagina, Date> ultimaUtilizacaoCol =
			UtilUI.getUltUtilCol();

		// usa uma fábrica de entradas da tabela
		ultimaUtilizacaoCol.setCellFactory(col -> {
			TableCell<Pagina, Date> cell = new TableCell<Pagina, Date>() {
				@Override
				public void updateItem(Date item, boolean empty) {
					super.updateItem(item, empty);

					// Cleanup the cell before populating it
					this.setText(null);
					this.setGraphic(null);

					if (!empty) {
						
						this.setText(item.toString());
					}
				}
			};
			return cell;
		});
		
		// cria coluna dos bits de utilizado
		TableColumn<Pagina, Boolean> utilizadoCol = new TableColumn<>("Utilizado?");
		utilizadoCol.setCellValueFactory(
			cellData -> {
				Boolean v = cellData.getValue().isUtilizado();
				return new ReadOnlyBooleanWrapper(v);
			});

		// cria uma fábrica de entradas para o bit utilizado
		utilizadoCol.setCellFactory(
			CheckBoxTableCell.<Pagina>forTableColumn(utilizadoCol));
		
		// adiciona as colunas à tabela
		tabela.getColumns().addAll(UtilUI.getPresCol(),
			utilizadoCol,
			ultimaUtilizacaoCol);
		
		grid.addRow(2, tabela);

		return new TitledPane("Processo " + p.getId(), grid);
	}

}
