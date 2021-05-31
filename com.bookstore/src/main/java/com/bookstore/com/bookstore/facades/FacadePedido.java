package com.bookstore.com.bookstore.facades;

import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.com.bookstore.model.FormaPagamento;
import com.bookstore.com.bookstore.model.ItemPedido;
import com.bookstore.com.bookstore.model.Livro;
import com.bookstore.com.bookstore.model.Pedido;
import com.bookstore.com.bookstore.service.ItemPedidoService;
import com.bookstore.com.bookstore.service.LivroService;
import com.bookstore.com.bookstore.service.PedidoService;

@Component
public class FacadePedido {

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private FacadeLivros facadeLivros;
	
	@Autowired
	private FacadeFormaPagamento facadeFormaPagamento;
	
	@Autowired
	private ItemPedidoService itemPedidoService;
	
	public void criarPedido(Long idLivro, Integer quantidade) throws Exception{
		
		Livro livro = facadeLivros.recuperarLivro(idLivro);
		
		Pedido pedido = new Pedido();
		ItemPedido itemPedido = new ItemPedido(livro, quantidade);		
		pedido.adicionarItemPedido(itemPedido);
		
		itemPedidoService.salvarItemPedido(itemPedido);
		pedidoService.salvarPedido(pedido);
		
	}
	
	public void removerPedido(Long id) throws Exception{
		
		recuperarPedido(id);
		pedidoService.deletarPeloId(id);
	}
	
	public Pedido recuperarPedido(Long id) throws Exception{
		
		Optional<Pedido> pedido = pedidoService.recuperarPeloId(id);
		
		if(pedido.isPresent()) {
			return pedido.get();
		}
		
		throw new Exception("[ERRO] Pedido Inexistente");
	}
	
	public void atualizarPedido(Long idPedido, Long idLivro, Integer quantidade) throws Exception{
		
		Pedido pedido = recuperarPedido(idPedido);
		Livro livro = facadeLivros.recuperarLivro(idLivro);
		ItemPedido itemPedido;
		
		if(pedido.getItensPedidos().size() == 1 && quantidade == 0) {
			
			removerPedido(idPedido);
			
		}else if(quantidade == 0) {
			
			itemPedido = pedido.recuperarItemPedido(idLivro);
			pedido.removerItemPedido(itemPedido.getID());
			
			pedidoService.atualizarPedido(pedido);
			
		}else {
			
			itemPedido = new ItemPedido(livro, quantidade);
			itemPedidoService.salvarItemPedido(itemPedido);
			pedido.adicionarItemPedido(itemPedido);
			
			pedidoService.atualizarPedido(pedido);
		}
		
		
	}
	
	public void finalizarPedido(Long idPedido, String localDeEntrega, Long idFormaPagamento) throws Exception{
				
		Pedido pedido = recuperarPedido(idPedido);
		FormaPagamento formaPagamento = facadeFormaPagamento.recuperarFormaPagamento(idFormaPagamento);
		
		formaPagamento.realizarPagamento(pedido.getvalorItensTotal());
		
		pedido.setFormaPagamento(formaPagamento);
		pedido.setLocalDeEntrega(localDeEntrega);
		pedido.setDataFechamento(Date.from(Instant.now()));
		pedido.setStatusPedido("Finalizado");
		diminuirDoEstoque(pedido);
		
		pedidoService.atualizarPedido(pedido);
		
	}
	
	/**
	 * Esse método faz a diminuição do estoque em relação a quantidade de livros que foram pedidos de cada livro
	 * @param pedido
	 * @throws Exception
	 */
	private void diminuirDoEstoque(Pedido pedido) throws Exception {	
		
		pedido.verificarEstoque(); // verifica se o estoque tem a quantidade suficiente de livros que estou pedindo
		
		Iterator<ItemPedido> itensPedidosIterator = pedido.getItensPedidos().iterator();
		while(itensPedidosIterator.hasNext()) {
			
			ItemPedido itemPedido = itensPedidosIterator.next();
			Livro livro = facadeLivros.recuperarLivro(itemPedido.getLivro().getId());
			
			livro.setQuantidadeEmEstoque(livro.getQuantidadeEmEstoque() - itemPedido.getQuantidade());
			
			facadeLivros.atualizarLivro(livro);
			
		}	
	}
			
}
