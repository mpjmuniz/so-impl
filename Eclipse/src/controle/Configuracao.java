package controle;

public class Configuracao extends Singleton{

	private static final long serialVersionUID = 6751867719979054019L;
	private int quantidadeInicialPaginas;
	private int tamanhoPagina;
	private int enderecoLogico;
	private int tamanhoTotalMP;
	private int tamanhoTotalMS;
	private int tamanhoMaximoProcesso;
	
	//Implementar como singleton realmente, estático
	public Configuracao(){
		super.obterInstancia("src/resources/configuracoes.ser");
	}
	
	public void setQuantidadeInicialPaginas(int quantidadeInicialPaginas) {
		this.quantidadeInicialPaginas = quantidadeInicialPaginas;
	}

	public int getQuantidadeInicialPaginas() {
		return quantidadeInicialPaginas;
	}

	public void setTamanhoPagina(int tamanhoPagina) {
		this.tamanhoPagina = tamanhoPagina;
	}

	public int getTamanhoPagina() {
		return this.tamanhoPagina;
	}

	public void setEnderecoLogico(int enderecoLogico) {
		this.enderecoLogico = enderecoLogico;
	}

	public int getEnderecoLogico() {
		return this.enderecoLogico;
	}

	public void setTamanhoTotalMP(int tamanhoTotalMP) {
		this.tamanhoTotalMP = tamanhoTotalMP;
	}

	public int getTamanhoTotalMP() {
		return this.tamanhoTotalMP;
	}

	public void setTamanhoTotalMS(int tamanhoTotalMS) {
		this.tamanhoTotalMS = tamanhoTotalMS;
	}

	public int getTamanhoTotalMS() {
		return this.tamanhoTotalMS;
	}

	public void setTamanhoMaximoProcesso(int tamanhoMaximoProcesso) {
		this.tamanhoMaximoProcesso = tamanhoMaximoProcesso;
	}

	public int getTamanhoMaximoProcesso() {
		return this.tamanhoMaximoProcesso;
	}

	public static Configuracao obterConfiguracoes() {
		// TODO Auto-generated method stub
		return null;
	}
}
