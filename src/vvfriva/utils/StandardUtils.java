package vvfriva.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import vvfriva.model.Report;


public class StandardUtils {
	
	public static Date currentDateTime() {
		Date d = new Date();
		return d;
	}
	
	
	public static String[] getLettereAlfabeto() {
		String[] alfa = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "L","M","N", "O", "P", "Q", "R", "S","T", "U", "V", "Z"};
		return alfa;
	}
	
	public static Date azzeraMinutiOreSecondi(Date data) {
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(data);
		 cal.set(Calendar.HOUR_OF_DAY, 0);
		 cal.set(Calendar.MINUTE, 0);
		 cal.set(Calendar.SECOND, 0);
		return cal.getTime();
		
	}
	
	public static int dateBetween(Date d1, Date d2, String type) {
		
		long diffDate = d2.getTime() - d1.getTime();
		long result = -1;
		switch (type) {
		case "d":
			result = TimeUnit.DAYS.convert(diffDate, TimeUnit.MILLISECONDS);
			break;
		default:
			break;
		}
		return (int)result;
		
	}
	
	public static List<String> pulisciStringa(String stringa) {
		String[] result = {};
		List<String> tmpResult = new ArrayList<String>();
		if (!Controlli.isEmptyString(stringa)) {
			result = stringa.split(";");
			for (int i = 0; i < result.length; i++) {
				if (Controlli.stringCompareTo(result[i], "", false) == -1) {
					tmpResult.add(result[i]);
				} 
			}
		}
		return tmpResult;
		
	}

	public static Date sommaGiorni(Date data, Integer numberDay) {
		Calendar cal = Calendar.getInstance();
	    cal.setTime(data);
	    cal.add(Calendar.DATE, numberDay);  
		return cal.getTime();
	}
	/**
	 * ritorna il primo giorno della settimana 
	 * @param data
	 * @return
	 */
	public static Date startOfWeek(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}
	/**
	 * ritorna l'ultimo giorno della settimana 
	 * @param data
	 * @return
	 */
	public static Date endOfWeek(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return cal.getTime();
	}
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static Date getSaturday(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		return cal.getTime();
	}
	
	/**
	 * date due date ne ritorna 
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	public static int numberOfWeek(Date newDate, Date oldDate) {
		Long diff = null;
		Long nrGiorni = null;
		Integer numberWeek = null;
		
		if (newDate instanceof Date && oldDate instanceof Date) {
			diff = newDate.getTime() - oldDate.getTime();
		}
		
		nrGiorni = diff != null ? diff : null;
		if (nrGiorni != null) {
			numberWeek =  (int) TimeUnit.DAYS.convert(nrGiorni, TimeUnit.MILLISECONDS) / 7; 
		}
		return numberWeek;
	}
	/**
	 * somma numero giorni ad una data passata
	 * @param data
	 * @param i
	 * @return
	 */
	public static Date addDay(Date data, int i) {
		Date newDate = data;
		Calendar cal = Calendar.getInstance();
		cal.setTime(newDate);
		cal.add(Calendar.DATE, i);
		return cal.getTime();
	}
	
	/**
	 * indica il numero del giorno della settimana
	 * @param data
	 * @return
	 */
	public static int daysInWeek(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 
	 * @param params
	 * @param fileName
	 * @param dataSource
	 * @return
	 * @throws IOException
	 * @throws CustomException 
	 */
	public static <T> Report doPrint(HashMap<String, Object> params, String fileName, List<T> dataSource) throws IOException, CustomException {
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		File catalinaBase = null;
		InputStream is = null;
		Report data = new Report();
		JRBeanCollectionDataSource collection =  null;
		try {
			
			
			catalinaBase = new File( System.getProperty( "catalina.base" ) ).getAbsoluteFile();
			is = StandardUtils.class.getClassLoader().getResourceAsStream("/vvfriva/report/"+ fileName +".jrxml");
			
			params.put("SUBREPORT_DIR", catalinaBase + "\\webapps\\vvfriva2\\WEB-INF\\classes\\vvfriva\\report\\");
			params.put("IMAGE_PATH", catalinaBase + "\\webapps\\rpt\\image\\");
			
			if (dataSource != null) {
				collection = new JRBeanCollectionDataSource(dataSource);
			} 
			jasperReport = JasperCompileManager.compileReport(is);
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, collection != null ? collection : new JREmptyDataSource());
			System.out.println(catalinaBase);
			JasperExportManager.exportReportToPdfFile(jasperPrint, catalinaBase + "/webapps/rpt/"+ fileName +".pdf");
			data.setPath("../rpt/"+ fileName +".pdf");	
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(new StringBuilder().append(e.getMessage()));
		}
		finally {
			is.close();
		}
		return data;
	}
	/**
	 * aggiunge un numero x di mesi ad una data
	 * @param data
	 * @param i
	 * @return
	 */
	public static Date addMonth(Date data, int i) {
		Date newData = data;
		Calendar cal = Calendar.getInstance();
		cal.setTime(newData);
		cal.add(Calendar.MONTH, i);
		return cal.getTime();
	}
	/**
	 * aggiunge un numero x di anni ad una data
	 * @param data
	 * @param i
	 * @return
	 */
	public static Date addYear(Date data, int i) {
		Date newData = data;
		Calendar cal = Calendar.getInstance();
		cal.setTime(newData);
		cal.add(Calendar.YEAR, i);
		return cal.getTime();
	}

}
