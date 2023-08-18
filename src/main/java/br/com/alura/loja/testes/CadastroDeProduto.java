package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import java.util.List;
import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);

		Produto p = produtoDAO.buscarPorId(1l);
		System.out.println(p.getPreco());
			
		
		List<Produto> produtosLista = produtoDAO.buscarTodos();
		produtosLista.forEach(p1 -> System.out.println(p1.getNome()));
		
		List<Produto> produtosLista2 = produtoDAO.buscarPorNome("Samsung");
		produtosLista2.forEach(p2 -> System.out.println(p2.getNome()));
		
		List<Produto> produtosLista3 = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
		produtosLista3.forEach(p3 -> System.out.println(p3.getNome()));
		
		BigDecimal precoDoProduto = produtoDAO.buscarPrecoDoProdutoComNome("Samsung");
		System.out.println(precoDoProduto);
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto(
				"Samsung", 
				"Muito bom", 
				new BigDecimal("800"), 
				celulares
			);
		
		
		EntityManager em = JPAUtil.getEntityManager();	
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDAO.cadastrar(celulares);		
		produtoDAO.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
	}

}
