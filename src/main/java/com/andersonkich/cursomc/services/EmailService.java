package com.andersonkich.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.andersonkich.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
