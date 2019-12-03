package com.andersonkich.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.andersonkich.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	//Simula o pagamento de boleto, somente uma brincadeira
	public void preencherPagamentoComBoleto(PagamentoComBoleto pag, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7); 
		pag.setDataPagamento(cal.getTime());
	}
	
}
