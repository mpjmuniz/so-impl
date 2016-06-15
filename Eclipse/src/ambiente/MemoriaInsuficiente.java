package ambiente;

public class MemoriaInsuficiente extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2875867126609093719L;
	public MemoriaInsuficiente() {
		super();
	}
	public MemoriaInsuficiente(String msg){
		super(msg);
	}
}
