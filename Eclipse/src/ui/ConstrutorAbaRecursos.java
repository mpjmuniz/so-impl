package ui;

import javafx.util.Builder;
import recursos.GerenciadorRecursos;

public class ConstrutorAbaRecursos implements Builder<AbaRecursos> {

	String text;
	GerenciadorRecursos ger;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public GerenciadorRecursos getGer() {
		return ger;
	}

	public void setGer(GerenciadorRecursos ger) {
		this.ger = ger;
	}

	@Override
	public AbaRecursos build() {
		return new AbaRecursos(text, ger);
	}

}
