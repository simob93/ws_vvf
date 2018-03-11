package vvfriva.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@Table(name = "vie_idranti")
public class Strade implements Serializable {
	/**
	 * entity Suadre
	 */
	private static final long serialVersionUID = 1L;

	public Strade() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "percorso")
	private String percorso;
	
	@Column(name = "idrante")
	private String idrante;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPercorso() {
		return percorso;
	}

	public void setPercorso(String percorso) {
		this.percorso = percorso;
	}

	public String getIdrante() {
		return idrante;
	}

	public void setIdrante(String idrante) {
		this.idrante = idrante;
	}

	
}

