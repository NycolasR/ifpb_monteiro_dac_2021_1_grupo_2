package com.bookstore.com.bookstore.facades;

import java.util.Optional;

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
			
			FormaPagamento formaPagamento = instanciarFormaDePagamento(tipoFormaPagamento);
			formaPagamentoService.salvarFormaPagamento(formaPagamento);
			
		}else {
			 throw new Exception("[ERRO] Forma de Pagamento já cadastrada");
		}
	}
		
	public void removerFormaPagamento(Long id) throws Exception{
		
		recuperarFormaPagamento(id);
		formaPagamentoService.deletarPeloId(id);
		
	}
	
	public FormaPagamento recuperarFormaPagamento(Long id) throws Exception {
		
		Optional<FormaPagamento> formaPagamento = formaPagamentoService.recuperarPeloId(id);
		
		if(formaPagamento.isPresent()) {
			return formaPagamento.get();
		}
		
		throw new Exception("[ERRO] Forma de pagamento inexistente");
	}
	
	public FormaPagamento recuperarFormaPagamentoPeloTipo(String tipo) throws Exception{
		
		FormaPagamento formaPagamento = formaPagamentoService.recuperarPeloTipo(tipo);
		
		if(formaPagamento != null) {
			return formaPagamento;
		}
		throw new Exception("[ERRO] Forma de pagamento inexistente");
		
	}
	
	public void atualizarFormaPagamento(FormaPagamento formaPagamento) {
		
		formaPagamentoService.atualizarFormaPagamento(formaPagamento);
	}
	
	/**
	 * Método privado responsável por instanciar as respectivas subclasses de 
	 * FormaPagamento dependendo do tipoFormaPagamento passado como parâmetro
	 * @param tipoFormaPagamento é o tipo de FormaPagamento que se deseja instanciar
	 * @return retorna a FormaPagamento instanciada, correspondente ao tipo passado por parâmetro
	 */
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
