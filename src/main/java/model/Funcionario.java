package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 40, nullable = false)
	private String nome;

	@ManyToOne()
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@OneToMany(mappedBy = "funcionario")
	private List<Atestado> atestados;

	public Funcionario() {
	}

	public Funcionario(String nome, Empresa empresa) {
		super();
		this.nome = nome;
		this.empresa = empresa;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Atestado> getAtestados() {
		return atestados;
	}

	public void setAtestados(List<Atestado> atestados) {
		this.atestados = atestados;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public String toStringNomeEmpresa() {

		return empresa.getNome();
	}
}
