package com.andersonkich.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.andersonkich.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")//usando o @Value ele puxa o item e atribui a uma variavel
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());                    //Funcao SimpleMailMessage para quem sera enviado o email do pedido 
		sm.setFrom(sender);                                       //Funcao SimpleMailMessage email que ira envial o email do pedido
		sm.setSubject("Pedido Confirmado! Código: "+ obj.getId());//Funcao SimpleMailMessage que define o assunto do email
		sm.setSentDate(new Date(System.currentTimeMillis()));     //pega a data do meu servidor
		sm.setText(obj.toString());                               //Funcao SimpleMailMessage que define o corpo do email
		
		return sm;
	}
	
}
