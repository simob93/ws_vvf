package vvfriva.model;

public class ModelTurni {
	
	private String nominativoVigile;
	private String dataTurno;
	private String giorno;
	private Integer numeroSquadra;
	private String patente;
	private String cercaPersone;
	private String lettera; 
	private Integer idSquadra;
	private String capoSquadra;
	private String periodoSett;
	
	public ModelTurni() {}
	
	public ModelTurni(String nominativoVigile, String dataTurno, String giorno, Integer numeroSquadra, String patente, String lettera, String cercaPersone, Integer idSquadra, String capoSquadra, String periodoSett) {
		
		this.nominativoVigile = nominativoVigile;
		this.dataTurno = dataTurno;
		this.giorno = giorno;
		this.numeroSquadra = numeroSquadra;
		this.patente = patente;
		this.lettera = lettera;
		this.cercaPersone = cercaPersone;
		this.idSquadra = idSquadra;
		this.capoSquadra = capoSquadra;
		this.periodoSett = periodoSett;
	}
	
	public String getNominativoVigile() {
		return nominativoVigile;
	}
	
	public void setNominativoVigile(String nominativoVigile) {
		this.nominativoVigile = nominativoVigile;
	}
	
	public String getDataTurno() {
		return dataTurno;
	}
	
	public void setDataTurno(String dataTurno) {
		this.dataTurno = dataTurno;
	}
	
	public String getGiorno() {
		return giorno;
	}

	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}

	public Integer getNumeroSquadra() {
		return numeroSquadra;
	}

	public void setNumeroSquadra(Integer numeroSquadra) {
		this.numeroSquadra = numeroSquadra;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getLettera() {
		return lettera;
	}

	public void setLettera(String lettera) {
		this.lettera = lettera;
	}

	public String getCercaPersone() {
		return cercaPersone;
	}

	public void setCercaPersone(String cercaPersone) {
		this.cercaPersone = cercaPersone;
	}

	public Integer getIdSquadra() {
		return idSquadra;
	}

	public void setIdSquadra(Integer idSquadra) {
		this.idSquadra = idSquadra;
	}

	public String getCapoSquadra() {
		return capoSquadra;
	}

	public void setCapoSquadra(String capoSquadra) {
		this.capoSquadra = capoSquadra;
	}

	public String getPeriodoSett() {
		return periodoSett;
	}

	public void setPeriodoSett(String periodoSett) {
		this.periodoSett = periodoSett;
	}
	
	
}
