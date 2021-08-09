package com.bookstore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.model.Categoria;
import com.bookstore.model.Livro;

/**
 * 
 * @author NPG (Grupo 2)
 * Interface para acesso ao banco de dados voltado para a entidade Livro.
 * Implementações fornecidas pelo Spring, logo não é necessário a uma classe
 * implementar esta interface.
 * 
 */
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
	
	@Query("SELECT l FROM Livro l WHERE l.quantidadeEmEstoque > 0 ORDER BY preco")
	public Page<Livro> livrosEmEstoque(Pageable pageable);
	
	//Essa query realiza a remoção de uma instância na tabela Livro
	@Transactional
	@Modifying
	@Query(value = "delete from tb_livro where id = :id and (select count(*) from tb_pedido p join tb_item_pedido on p.id = pedido_fk where status_pedido = 'Não Finalizado' and livro_fk = :id) = 0", nativeQuery = true)
	public void deletarLivro(@Param ("id") Long id);
	
	//Essa query realiza a remoção de uma instância na tabela Livro_Autor
	@Transactional
	@Modifying
	@Query(value = "delete from tb_livro_autor where fk_livro = :id", nativeQuery = true)
	public void deletarLivroAutor(@Param ("id") Long id);
	
	//Essa query realiza a remoção de uma instância na tabela Livro_Categoria
	@Transactional
	@Modifying
	@Query(value = "delete from tb_livro_categoria where fk_livro = :id", nativeQuery = true)
	public void deletarLivroCategoria(@Param ("id") Long id);
	
	//Essa query faz uma consulta com uma filtragem
	@Query(value = "select * from tb_livro join tb_livro_categoria on id = fk_livro where fk_categoria in (:categorias) group by id order by titulo", nativeQuery = true)
	public Page<Livro> filtrarPorCategoria(@Param ("categorias") List<Integer> categorias, Pageable pageable);
	
	//Essa query faz uma busca pelo titulo
	@Query(value = "select * from tb_livro where titulo like :texto order by titulo", nativeQuery = true)
	public Page<Livro> buscarPorTitulo(@Param("texto") String texto, Pageable pageable);
	
	@Query(value = "select * from tb_livro where id in (:ids)", nativeQuery = true)
	public List<Livro> listarPorIds(@Param("ids") List<Long> ids);

	//Essa query realiza a remoção de todas as instâncias na tabela Livro_Autor
	@Transactional
	@Modifying
	@Query(value = "truncate table tb_livro_autor", nativeQuery = true)
	public void deletarInstancasLivroAutor();
	
}
