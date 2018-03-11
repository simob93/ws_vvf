package vvfriva.model;

import java.util.List;

public class ReportTurnario {
	private String periodo;
	private String capoSquadra;
	private Integer numeroSquadra;
	private List<ModelTurni> turni;

	public List<ModelTurni> getTurni() {
		return turni;
	}

	public void setTurni(List<ModelTurni> turni) {
		this.turni = turni;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getCapoSquadra() {
		return capoSquadra;
	}

	public void setCapoSquadra(String capoSquadra) {
		this.capoSquadra = capoSquadra;
	}

	public Integer getNumeroSquadra() {
		return numeroSquadra;
	}

	public void setNumeroSquadra(Integer numeroSquadra) {
		this.numeroSquadra = numeroSquadra;
	}
}
