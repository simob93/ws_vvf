package vvfriva.entity;


import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Type;

@XmlRootElement
@Entity
@Table(name = "vigile")
public class Vigili implements Serializable {
	/**
	 * entity vigili
	 */
	private static final long serialVersionUID = 1L;

	public Vigili() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "datadiNascita")
	@Type(type="date")
	private Date dataNascita;

	@Column(name = "grado")
	private String grado;

	@Column(name = "qualifica")
	private String qualifica;

	@Column(name = "cercapersone")
	private String cercaPersone;

	@Column(name = "patente")
	private String patente;

	@Column(name = "caposquadra")
	private Integer capoSquadra;

	@Column(name = "letteravigile")
	private String letteraVigile;
	
	@Column(name = "note")
	private String note;

	@ManyToOne
	@JoinColumn(name = "idsquadra")
	private Squadre squadra;
	
	@Transient
	private Integer idSquadra;
	
	
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getGrado() {
		return grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
	}

	public String getQualifica() {
		return qualifica;
	}

	public void setQualifica(String qualifica) {
		this.qualifica = qualifica;
	}

	public String getCercaPersone() {
		return cercaPersone;
	}

	public void setCercaPersone(String cercaPersone) {
		this.cercaPersone = cercaPersone;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Integer getCapoSquadra() {
		return capoSquadra;
	}

	public void setCapoSquadra(Integer capoSquadra) {
		this.capoSquadra = capoSquadra;
	}

	public String getLetteraVigile() {
		return letteraVigile;
	}

	public void setLetteraVigile(String letteraVigile) {
		this.letteraVigile = letteraVigile;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	public Squadre getSquadra() {
		return squadra;
	}

	public void setSquadra(Squadre squadra) {
		this.squadra = squadra;
	}

	public Integer getIdSquadra() {
		return (squadra  != null ? squadra.getId() : null);
	}


}
