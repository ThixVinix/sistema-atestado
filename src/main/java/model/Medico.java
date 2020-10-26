package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "medico")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 40, nullable = false)
	private String nome;

	@Column(columnDefinition = "CHAR(7)", nullable = false, unique = true)
	private String crm;

	@OneToMany(mappedBy = "medico")
	private List<Atestado> atestados;

	public Medico() {
	}

	public Medico(String nome, String crm) {
		super();
		this.nome = nome;
		this.crm = crm;
	}

	public List<Atestado> getAtestados() {
		return atestados;
	}

	public void setAtestados(List<Atestado> atestados) {
		this.atestados = atestados;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

}
