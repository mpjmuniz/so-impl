package controle;

public class Configuracao {

	private static volatile Configuracao instancia;
	private int quantidadeInicialPaginas = 32;
	private int tamanhoPagina = 1024;
	private int enderecoLogico;
	private int tamanhoTotalMP = 20 * 1024;
	private int tamanhoTotalMS = 100 * 1024;
	private int tamanhoMaximoProcesso;

	private Configuracao() {
	}

	public static Configuracao obterInstancia() {
		if (instancia == null) {
			synchronized (Configuracao.class) {
				if (instancia == null) {
					instancia = new Configuracao();
				}
			}
		}
		return instancia;
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
	
	public int getQuantidadePaginas(int tam){
		int qtdPaginas = tam / this.getTamanhoPagina();
		if(tam % this.getTamanhoPagina() > 0) qtdPaginas++;
		return qtdPaginas;
	}
}
