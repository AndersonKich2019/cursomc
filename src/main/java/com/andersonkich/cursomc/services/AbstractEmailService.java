package com.andersonkich.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.andersonkich.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")//usando o @Value ele puxa o item e atribui a uma variavel
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
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
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);//passa o pedido para povoar o template
		return templateEngine.process("email/comfirmaçaoPedido", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromPedido(obj);
			sendHtmlEmail(mm);
		}
		catch(MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
		MimeMessage mimeMensage =  (MimeMessage) javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMensage, true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido Confirmado! Código: "+ obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));  
		mmh.setText(htmlFromTemplatePedido(obj),true);
		return mimeMensage;
	}
	
}
