package recursos;



public class Processo {

	private Estado estado;
	private TabelaDePaginas tabela;
	private int id;

	public Processo(int id, int tamanho, TabelaDePaginas tp){
		this.setId(id);		
		this. estado = Estado.NOVO;
		this.tabela = tp;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void alocar(){
		this.estado = Estado.PRONTO;
	}

	public void suspender(){
		this.estado = Estado.SUSPENSO;
	}
	
	public void dispachar(){
		this.estado = Estado.EXECUTANDO;
	}

	public void bloquear(){
		this.estado = Estado.BLOQUEADO;
	}

	public void terminar(){
		this.estado = Estado.TERMINADO;
	}
	
	public TabelaDePaginas getTabela() {
		return tabela;
	}
	
	/*	Visibilidade para o pacote*/
	void setTabela(TabelaDePaginas tp){
		this.tabela = tp;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}
