package com.bookstore.facades;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bookstore.model.FormaPagamento;
import com.bookstore.model.ItemPedido;
import com.bookstore.model.Livro;
import com.bookstore.model.Pedido;
import com.bookstore.model.RegistroVendas;
import com.bookstore.model.Usuario;
import com.bookstore.service.ItemPedidoService;
import com.bookstore.service.PedidoService;
import com.bookstore.service.RegistroVendasService;
import com.bookstore.service.UsuarioService;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema) Classe
 *         Facade responsável por métodos relacionados a Pedido. Esse padrão de
 *         projeto foi escolhido para ser implementado, pois o mesmo busca
 *         facilitar a utilização de métodos de classes distintas que se
 *         relacionam entre si, por parte das classes clientes.
 *
 */
@Component
public class FacadePedido {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private FacadeLivros facadeLivros;

	@Autowired
	private FacadeEnderecos facadeEnderecos;

	@Autowired
	private FacadeFormaPagamento facadeFormaPagamento;

	@Autowired
	private ItemPedidoService itemPedidoService;

	@Autowired
	private RegistroVendasService registroVendasService;
	
	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Método responsável por criar um Pedido (Adicionar ao carrinho de compras)
	 * 
	 * @param idLivro id correspondente ao livro que se deseja comprar
	 * @throws Exception lança exceção caso o livro não seja encontrado
	 */
	public void criarPedido(List<Long> idLivros, Long idUsuario) throws Exception {

		Pedido pedido = pedidoService.recuperarPedidoNaoFinalizadoDoUsuario(idUsuario);

		ItemPedido itemPedido;

		if (idLivros.size() > 0) {

			List<Livro> livros = facadeLivros.recuperarLivros(idLivros);

			for (Livro livro : livros) {

				itemPedido = new ItemPedido(livro, 1);
				pedido.adicionarItemPedido(itemPedido);
				itemPedidoService.salvarItemPedido(itemPedido);
			}

		}

//		Usuario usuario = usuarioService.recuperarPeloId(idUsuario);

		pedido.setUsuario(usuarioService.recuperarPeloId(idUsuario));
		pedido.setMotivoCancelamento("Sem motivo");
		pedidoService.salvarPedido(pedido);

	}

	/**
	 * Esse método remove um Pedido pelo seu id
	 * 
	 * @param id id correspondente ao Pedido
	 * @throws Exception lança exceção caso não seja encontrado o Pedido
	 */
	public void removerPedido(Long id) throws Exception {

		recuperarPedido(id);
		pedidoService.deletarPeloId(id);
	}

	/**
	 * Esse método recupera um Pedido pelo seu id
	 * 
	 * @param id id correspondente ao Pedido
	 * @return retorna o Pedido encontrado
	 * @throws Exception lança exceção caso não seja encontrado o Pedido
	 */
	public Pedido recuperarPedido(Long id) throws Exception {

		Optional<Pedido> pedido = pedidoService.recuperarPeloId(id);

		if (pedido.isPresent()) {
			return pedido.get();
		}

		throw new Exception("[ERRO] Pedido inexistente");
	}

	/**
	 * Esse método atualiza um Pedido
	 * 
	 * @param idPedido   id do Pedido que se deseja atualizar
	 * @param idLivro    id do livro que se deseja adicionar ao Pedido
	 * @param quantidade quantidade correspondente ao livro que se deseja adiconar
	 *                   ao pedido
	 * @throws Exception lança exceção caso o Pedido e o livro não sejam encontrados
	 */
	public void atualizarPedido(Long idPedido, Long idLivro, Integer quantidade) throws Exception {

		Pedido pedido = recuperarPedido(idPedido);
		Livro livro = facadeLivros.recuperarLivro(idLivro);
		ItemPedido itemPedido;

		if (pedido.getItensPedidos().size() == 1 && quantidade == 0) {

			removerPedido(idPedido);

		} else if (quantidade == 0) {

			itemPedido = pedido.recuperarItemPedido(idLivro);
			pedido.removerItemPedido(itemPedido.getID());

			pedidoService.atualizarPedido(pedido);

		} else {

			itemPedido = new ItemPedido(livro, quantidade);
			itemPedidoService.salvarItemPedido(itemPedido);
			pedido.adicionarItemPedido(itemPedido);

			pedidoService.atualizarPedido(pedido);
		}

	}

	public void atualizarPedido(Long id) throws Exception {
		Pedido pedido = recuperarPedido(id);
		pedidoService.atualizarPedido(pedido);
	}

	/**
	 * Esse método é responsável por finalizar um Pedido (o carrinho de compras é
	 * finalizado)
	 * 
	 * @param idPedido         id do Pedido a ser finalizado
	 * @param localDeEntrega   endereço do cliente para ser entregue o Pedido
	 * @param idFormaPagamento id da FormaPagamento para ser pago o Pedido
	 * @throws Exception lança exceção caso o Pedido e a FormaPagamento não sejam
	 *                   encontrados.
	 */
	public void finalizarPedido(Long idPedido, Long localDeEntrega, Long idFormaPagamento) throws Exception {

		Pedido pedido = recuperarPedido(idPedido);
		FormaPagamento formaPagamento = facadeFormaPagamento.recuperarFormaPagamento(idFormaPagamento);
		formaPagamento.realizarPagamento(pedido.getValorItensTotal());

		pedido.setFormaPagamento(formaPagamento);
		pedido.setLocalDeEntrega(facadeEnderecos.recuperarEndereco(localDeEntrega));
		pedido.setDataFechamento(LocalDate.now());
		pedido.setStatusPedido("Finalizado");

		registrarVendas(pedido);
		diminuirDoEstoque(pedido);

		pedidoService.atualizarPedido(pedido);

	}
	
	/**
	 * Método utilizado para recuperar a quantidade de itens pedidos de um pedido não finalizado
	 * @param id do usuario
	 * @return quantidade de itens pedidos de um pedido
	 */
	public Integer recuperarQuantidadeDeItensPedidos(Long id) {
		
		Integer quantidade = pedidoService.recuperarQuantidadeItemPedidos(id);
		
		if(quantidade == null) {
			quantidade = 0;
		}
		
		return quantidade;
	}

	/**
	 * Esse método faz a diminuição do estoque em relação a quantidade de livros que
	 * foram pedidos de cada livro
	 * 
	 * @param pedido que vai possui os livros para diminuir do estoque
	 * @throws Exception lança exceção caso o estoque não possua a quantidade
	 *                   solicitada
	 */
	private void diminuirDoEstoque(Pedido pedido) throws Exception {

		pedido.verificarEstoque(); // verifica se o estoque tem a quantidade suficiente de livros que estou pedindo

		Iterator<ItemPedido> itensPedidosIterator = pedido.getItensPedidos().iterator();
		while (itensPedidosIterator.hasNext()) {

			ItemPedido itemPedido = itensPedidosIterator.next();
			Livro livro = facadeLivros.recuperarLivro(itemPedido.getLivro().getId());

			livro.setQuantidadeEmEstoque(livro.getQuantidadeEmEstoque() - itemPedido.getQuantidade());

			facadeLivros.atualizarLivro(livro, livro.getId());

		}
	}

	/**
	 * Esse método faz o registros das vendas feitas quando um pedido é finalizado
	 * 
	 * @param pedido pedido que vai ser finalizado
	 * @throws Exception lança exceção caso não tenha nenhum usuário cadastrado para
	 *                   o pedido
	 */
	private void registrarVendas(Pedido pedido) throws Exception {

		if (pedido.getUsuario() != null) {

			Iterator<ItemPedido> itensPedidosIterator = pedido.getItensPedidos().iterator();
			while (itensPedidosIterator.hasNext()) {

				ItemPedido itemPedido = itensPedidosIterator.next();

				RegistroVendas registroVendas = new RegistroVendas(LocalDate.now(), itemPedido.getLivro(),
						pedido.getUsuario(), itemPedido.getQuantidade(), itemPedido.getValorTotalItemPedido());

				registroVendasService.salvarRegistroVendas(registroVendas);
			}

		} else {
			throw new Exception("[ERRO] - Nenhum usuário cadastrado para o pedido");
		}
	}

	public void cancelarPedido(Long id, String motivo) throws Exception {
		// 1. Recuperar o pedido
		Optional<Pedido> optional = pedidoService.recuperarPeloId(id);
		Pedido pedido = null;

		if (optional.isPresent())
			pedido = optional.get();
		else
			throw new Exception("[ERRO] Pedido não encontrado na base de dados");

		// 2. Verificar se pode ser cancelado (lim. 1 semana)
		LocalDate dataPedido = pedido.getDataFechamento();
		dataPedido = dataPedido.plusDays(7);
		System.out.println(dataPedido);

		if (dataPedido.isBefore(LocalDate.now()))
			throw new Exception("Não é possível cancelar pedidos depois de 7 dias de seu fechamento.");
		else
			System.out.println("PODE SER CANCELADO");

		// 3. Restaurar os lvros no estoque
		Set<ItemPedido> itensPedidos = pedido.getItensPedidos();
		ItemPedido[] itensPedidosArr = (ItemPedido[]) itensPedidos.toArray(new ItemPedido[itensPedidos.size()]);

		for (int i = 0; i < itensPedidosArr.length; i++) {
			// 3.1 Atualizar livros envolvidos
			Livro livro = itensPedidosArr[i].getLivro();

			livro.setQuantidadeEmEstoque(livro.getQuantidadeEmEstoque() + itensPedidosArr[i].getQuantidade());

			System.out.println(livro);

			if (facadeLivros.livroExisteNoBd(livro.getId()))
				facadeLivros.atualizarLivro(livro, livro.getId());
		}

		// 4. Alterar o status do pedido para CANCELADO e setar o motivo
		pedido.setStatusPedido("CANCELADO");
		pedido.setMotivoCancelamento(motivo);

		// 5. Atualizar o pedido no bd
		pedidoService.atualizarPedido(pedido);
	}

}
