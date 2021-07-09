package com.bookstore.facades;

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
/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Classe Facade responsável por métodos relacionados a FormaPagamento.Esse padrão de projeto
 * foi escolhido para ser implementado, pois o mesmo busca facilitar a utilização de 
 * métodos de classes distintas que se relacionam entre si, por parte das classes clientes.
 *
 */
@Component
public class FacadeFormaPagamento {

	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	/**
	 * Método responsável por criar uma FormaPagamento, recebendo um TipoFormaPagamento
	 * @param tipoFormaPagamento tipo correspondente a uma FormaPagamento
	 * @throws Exception lança exceção caso a FormaPagamento já exista
	 */
	public void criarFormaPagamento(TipoFormaPagamento tipoFormaPagamento) throws Exception{
				
		if(formaPagamentoService.recuperarPeloTipo(tipoFormaPagamento.getTipo()) == null) {
			
			FormaPagamento formaPagamento = instanciarFormaDePagamento(tipoFormaPagamento);
			formaPagamentoService.salvarFormaPagamento(formaPagamento);
			
		}else {
			 throw new Exception("[ERRO] Forma de Pagamento já cadastrada");
		}
	}
	
	/**
	 * Esse método remove uma FormaPagamento
	 * @param id correspondente a FormaPagamento
	 * @throws Exception lança exceção caso não seja encontrada a FormaPagamento
	 */
	public void removerFormaPagamento(Long id) throws Exception{
		
		recuperarFormaPagamento(id);
		formaPagamentoService.deletarPeloId(id);
		
	}
	
	/**
	 * Esse método recupera uma FormaPagamento
	 * @param id correspondente a FormaPagamento
	 * @return retorna a FormaPagamento encontrada
	 * @throws Exception lança exceção caso não seja encontrada a FormaPagamento
	 */
	public FormaPagamento recuperarFormaPagamento(Long id) throws Exception {
		
		Optional<FormaPagamento> formaPagamento = formaPagamentoService.recuperarPeloId(id);
		
		if(formaPagamento.isPresent()) {
			return formaPagamento.get();
		}
		
		throw new Exception("[ERRO] Forma de pagamento inexistente");
	}
	
	/**
	 * Esse método recupera uma FormaPagamento pelo tipo
	 * @param tipo tipo relacionado a FormaPagamento que se deseja encontrar
	 * @return retorna a FormaPagamento encontrada
	 * @throws Exception lança exceção caso não seja encontrada a FormaPagamento
	 */
	public FormaPagamento recuperarFormaPagamentoPeloTipo(String tipo) throws Exception{
		
		FormaPagamento formaPagamento = formaPagamentoService.recuperarPeloTipo(tipo);
		
		if(formaPagamento != null) {
			return formaPagamento;
		}
		throw new Exception("[ERRO] Forma de pagamento inexistente");
		
	}
	
	/**
	 * Esse método atualiza uma FormaPagamento 
	 * @param formaPagamento que se deseja atualizar
	 */
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
