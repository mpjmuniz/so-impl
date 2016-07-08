package ui;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import recursos.GerenciadorRecursos;
import recursos.Pagina;
import recursos.Processo;

public class AbaRecursos extends Tab {
	private HBox base = new HBox();
	private VBox lista = new VBox();
	private FlowPane quadros = new FlowPane();
	private GerenciadorRecursos gerRec;

	public AbaRecursos(String text, GerenciadorRecursos ger) {
		this.setText(text);
		this.gerRec = ger;

		init();
	}

	public void init() {
		Label rotulo;
		
		base.setPrefWidth(1024);
		lista.setPrefWidth(300);

		ListView<Processo> processos = new ListView<>();
		processos.getItems().addAll(gerRec.getFila());
		processos.setCellFactory(new Callback<ListView<Processo>, ListCell<Processo>>() {
			@Override
			public ListCell<Processo> call(ListView<Processo> listView) {
				return new ListCell<Processo>() {
					@Override
					public void updateItem(Processo item, boolean empty) {
						// Must call super
						super.updateItem(item, empty);

						int index = this.getIndex();
						String name = null;

						// Format name
						if (item == null || empty) {
							// No action to perform
						} else {
							name = (index + 1) + ". Processo " + item.getId();
						}

						this.setText(name);
						setGraphic(null);
					}
				};
			}
		});
		
		lista.getChildren().add(new Label("Tamanho Total: "));
		lista.getChildren().add(new Label(Integer.toString(this.gerRec.getTamanhoTotal())));
		lista.getChildren().add(new Label("Tamanho Disponível: "));
		lista.getChildren().add(new Label(Integer.toString(this.gerRec.getTamanhoDisponivel())));
		lista.getChildren().add(new Label("Processos na Fila: "));
		lista.getChildren().add(processos);
		
		

		for(Pagina p : gerRec.getQuadros()){			
			quadros.getChildren().add(new Label(p.toString()));
			
		}
		
		
		
		/*ListView<Pagina> quads = new ListView<>();
		quads.getItems().addAll(gerRec.getQuadros());
		
		quads.setCellFactory(new Callback<ListView<Pagina>, ListCell<Pagina>>() {
			@Override
			public ListCell<Pagina> call(ListView<Pagina> listView) {
				return new ListCell<Pagina>() {
					@Override
					public void updateItem(Pagina item, boolean empty) {
						// Must call super
						super.updateItem(item, empty);

						String name = null;

						// Format name
						if (item == null || empty) {
							// No action to perform
						} else {
							name =  item.toString();
						}

						this.setText(name);
						setGraphic(null);
					}
				};
			}
		});*/
		
		quadros.setPrefWidth(724);

		base.getChildren().addAll(lista, quadros);

		this.setContent(base);
	}

}
