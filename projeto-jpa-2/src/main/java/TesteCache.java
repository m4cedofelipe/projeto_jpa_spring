import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.caelum.JpaConfigurator;
import br.com.caelum.model.Produto;

public class TesteCache {
	public static void main(String[] args) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfigurator.class);

		EntityManagerFactory emf = (EntityManagerFactory) ctx.getBean(EntityManagerFactory.class);

		/*
		 * Vamos imaginar o cenário que estamos trabalhando: uma aplicação web.
		 * Configuramos nosso projeto para que seja criado um novo EntityManager por
		 * requisição. Vamos criar mais um EntityManager, de exemplo, para tentar buscar
		 * um segundo produto:
		 */
		
		EntityManager em = emf.createEntityManager();
		EntityManager em2 = emf.createEntityManager(); // criando o segundo EntityManager

		Produto produto = em.find(Produto.class, 1);
		System.out.println("Nome: " + produto.getNome());

		Produto outroProduto = em2.find(Produto.class, 1); // buscando a mesma entidade com o segundo EntityManager
		System.out.println("Nome: " + outroProduto.getNome());

	}
}
