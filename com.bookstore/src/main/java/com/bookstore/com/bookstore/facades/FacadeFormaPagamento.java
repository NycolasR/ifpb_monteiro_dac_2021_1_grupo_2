package com.bookstore.com.bookstore.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.com.bookstore.model.Boleto;
import com.bookstore.com.bookstore.model.Cartao;
import com.bookstore.com.bookstore.model.FormaPagamento;
import com.bookstore.com.bookstore.model.Pix;
import com.bookstore.com.bookstore.model.TipoFormaPagamento;
import com.bookstore.com.bookstore.model.TransferenciaBancaria;
import com.bookstore.com.bookstore.service.FormaPagamentoService;

@Component
public class FacadeFormaPagamento {

	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	
	public void criarFormaPagamento(TipoFormaPagamento tipoFormaPagamento) throws Exception{
				
		if(formaPagamentoService.recuperarPeloTipo(tipoFormaPagamento.getTipo()) == null) {
			
			System.out.println("entrou");
			FormaPagamento formaPagamento = instanciarFormaDePagamento(tipoFormaPagamento);
			
		}else {
			 throw new Exception("Forma de Pagamento já cadastrada");
		}
		
		
	}
		
	public void removerFormaPagamento(Long id) {
		
	}
	
	public FormaPagamento recuperarFormaPagamento(Long id) throws Exception {
		
		return null;
	}
	
	public FormaPagamento recuperarFormaPagamentoPeloTipo(String tipo) {
		
		return formaPagamentoService.recuperarPeloTipo(tipo);
	}
	
	public void atualizarFormaPagamento(FormaPagamento formaPagamento) {
		
	}
	
	private FormaPagamento instanciarFormaDePagamento(TipoFormaPagamento tipoFormaPagamento) {
		
		FormaPagamento formaPagamento;
		
		if(tipoFormaPagamento == TipoFormaPagamento.CARTAO) {
			formaPagamento = new Cartao();
			
		}else if(tipoFormaPagamento == TipoFormaPagamento.BOLETO) {
			formaPagamento = new Boleto();
			
		}else if(tipoFormaPagamento == TipoFormaPagamento.PIX) {
			formaPagamento = new Pix();
			
		}else{
			
			formaPagamento = new TransferenciaBancaria();
		}
		
		return formaPagamento;
	}
	
	
	
}
