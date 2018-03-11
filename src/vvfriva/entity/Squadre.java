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
@Table(name = "squadra")
public class Squadre implements Serializable {
	/**
	 * entity Suadre
	 */
	private static final long serialVersionUID = 1L;

	public Squadre() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "lettera")
	private String lettera;
	
	@Column(name = "numeroSquadra")
	private Integer numeroSquadra;

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLettera() {
		return lettera;
	}

	public void setLettera(String lettera) {
		this.lettera = lettera;
	}

	public Integer getId() {
		return id;
	}

	public Integer getNumeroSquadra() {
		return numeroSquadra;
	}

	public void setNumeroSquadra(Integer numeroSquadra) {
		this.numeroSquadra = numeroSquadra;
	}
}
