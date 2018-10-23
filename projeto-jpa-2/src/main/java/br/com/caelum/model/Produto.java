package br.com.caelum.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Produto {

	/*
	 * (*** Estrategia de Cache ***)
	 * 
	 * Nessa situação, podemos usar a estratégia NON_STRICT_READ_WRITE ideal, ou
	 * seja, quando não há problemas em ler dados inconsistentes caso hajam
	 * alterações simultâneas.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String linkDaFoto;

	/*
	 * A anotação (@Version) para versionar todo update feito o hibernate irá
	 * verificar automaticamente o valor desse campo. Caso o registro no banco
	 * possua um valor menor do que o está sendo enviado para o campo versao, ele
	 * aceita a atualização e incrementa seu valor. Caso possua um valor maior, será
	 * disparado uma exceção do tipo StaleObjectStateException dentro de uma
	 * javax.persistence.OptimisticLockException.
	 */

	/*
	 * (LOCK PESSIMISTA & LOCK OTIMISTA)
	 * 
	 * Quando usamos (Lock Pessimista) pedimos uma trava o registro no banco
	 * enquanto ocorre o processo de edição. Portanto, caso alguém já esteja
	 * atualizando um certo registro, outra pessoa ao tentar atualizar o mesmo
	 * registro ficará travada aguardando a liberação do registro.
	 */
	@Version
	private int versao;

	@NotEmpty
	@Column(columnDefinition = "TEXT")
	private String descricao;

	@Min(20)
	private double preco;

	/*
	 * Deixei definido como CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, que diz
	 * que iremos eventualmente editar essa entidade e que o controle de
	 * concorrência pode ser mais “simples”, pois é muito pouco provável que dois
	 * processos estejam alterando ao mesmo tempo essa entidade.
	 */
	@ManyToMany
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Categoria> categorias = new ArrayList<>();

	@Valid
	@ManyToOne
	private Loja loja;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void adicionarCategorias(Categoria... categorias) {
		for (Categoria categoria : categorias) {
			this.categorias.add(categoria);
		}
	}

	public String getLinkDaFoto() {
		return linkDaFoto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public void setLinkDaFoto(String linkDaFoto) {
		this.linkDaFoto = linkDaFoto;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Loja getLoja() {
		return loja;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

}
