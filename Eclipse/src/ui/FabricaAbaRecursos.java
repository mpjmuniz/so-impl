package ui;

import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import javafx.util.BuilderFactory;

public class FabricaAbaRecursos implements BuilderFactory{

	private final JavaFXBuilderFactory fxFactory = new JavaFXBuilderFactory();
	
	@Override
	public Builder<?> getBuilder(Class<?> type) {
		if(type == AbaRecursos.class){
			return new ConstrutorAbaRecursos();
		}
		
		return fxFactory.getBuilder(type);
	}
	
}
