package vvfriva.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;


@XmlRootElement
@Entity
@Table(name = "protocolli")
public class Protocolli implements Serializable{
	/**
	 * entity protocolli 
	 */
	private static final long serialVersionUID = 1L;
	
	public Protocolli(){
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	
	@Column(name = "data")
	@Type(type = "date")
	private Date data;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "anno")
	private String anno;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "dataProtocollo")
	@Type(type = "date")
	private Date dataProtocollo;
	
	@Column(name = "numProtocollo")
	private String numProtocollo;
	
	@Column(name = "dataMittente")
	@Type(type = "date")
	private Date dataMittente;
	
	@Column(name = "descrFaldone")
	private String descrFaldone;
	
	@Column(name = "numFaldone")
	private String numFaldone;
	
	@Column(name = "oggetto")
	private String oggetto;
	
	@Column(name = "tipologia")
	private String tipologia;
	
	@Column(name = "ente")
	private String entePro;
	
	@Column(name = "ragioneSociale")
	private String ragioneSociale;
	
	@Column(name = "indirizzo")
	private String indirizzo;
	
	@Column(name = "cap")
	private String cap;
	
	@Column(name = "citta")
	private String citta;
	
	@Column(name = "provincia")
	private String provincia;
	
	@Column(name = "nazione")
	private String nazione;
	
	@Column(name = "idFaldone")
	private Integer idFaldone;
	
	@Column(name = "idEnte")
	private Integer idEnte;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDataProtocollo() {
		return dataProtocollo;
	}

	public void setDataProtocollo(Date dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}

	public String getNumProtocollo() {
		return numProtocollo;
	}

	public void setNumProtocollo(String numProtocollo) {
		this.numProtocollo = numProtocollo;
	}

	public Date getDataMittente() {
		return dataMittente;
	}

	public void setDataMittente(Date dataMittente) {
		this.dataMittente = dataMittente;
	}

	public String getDescrFaldone() {
		return descrFaldone;
	}

	public void setDescrFaldone(String descrFaldone) {
		this.descrFaldone = descrFaldone;
	}

	public String getNumFaldone() {
		return numFaldone;
	}

	public void setNumFaldone(String numFaldone) {
		this.numFaldone = numFaldone;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getEntePro() {
		return entePro;
	}

	
	public void setEntePro(String entePro) {
		this.entePro = entePro;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public Integer getIdFaldone() {
		return idFaldone;
	}

	public void setIdFaldone(Integer idFaldone) {
		this.idFaldone = idFaldone;
	}

	public Integer getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}
	
}
