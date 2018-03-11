package vvfriva.model;

import java.util.List;

public class ReportTurniTestata {

	private List<ReportTurnario> listaTurni;
	

	public ReportTurniTestata(List<ReportTurnario> listaTurni) {
		this.listaTurni = listaTurni;
	}

	public List<ReportTurnario> getListaTurni() {
		return listaTurni;
	}

	public void setListaTurni(List<ReportTurnario> listaTurni) {
		this.listaTurni = listaTurni;
	}
}
