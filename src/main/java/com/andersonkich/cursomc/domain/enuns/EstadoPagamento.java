package com.andersonkich.cursomc.domain.enuns;

public enum EstadoPagamento {

	PENDENTE(1, "Pagamento pendente"),
	QUITADO(2, "Pagamento efetuado"),
	CANCELADO(3, "Pagamento cancelado");
	
	private int codigo;
	private String descricao;
	
	private EstadoPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static EstadoPagamento toEnum(Integer codigo) {
		
		if(codigo == null) {
			return null;
		}
		
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido "+ codigo);
	}
	
}
