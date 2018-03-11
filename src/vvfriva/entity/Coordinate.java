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
@Table(name = "coordinate")
public class Coordinate implements Serializable {
	/**
	 * entity Menu
	 */
	private static final long serialVersionUID = 1L;

	public Coordinate() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "LATITUDINE")
	private Float latitudine;
	
	@Column(name = "LONGITUDINE")
	private Float longitudine;
	
	@Column(name = "TIPO")
	private String tipo;
	
	@Column(name = "ATTACCO")
	private String attacco;
	
	@Column(name = "USCITA")
	private String uscita;
	
	@Column(name = "POSIZIONE")
	private String posizione;
	
	@Column(name = "VIA")
	private String via;
	
	@Column(name = "FRAZIONE")
	private String frazione;
	
	@Column(name = "COMUNE")
	private String comune;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(Float latitudine) {
		this.latitudine = latitudine;
	}

	public Float getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(Float longitudine) {
		this.longitudine = longitudine;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAttacco() {
		return attacco;
	}

	public void setAttacco(String attacco) {
		this.attacco = attacco;
	}

	public String getUscita() {
		return uscita;
	}

	public void setUscita(String uscita) {
		this.uscita = uscita;
	}

	public String getPosizione() {
		return posizione;
	}

	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getFrazione() {
		return frazione;
	}

	public void setFrazione(String frazione) {
		this.frazione = frazione;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	
}
