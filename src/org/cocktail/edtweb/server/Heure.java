package org.cocktail.edtweb.server;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.webobjects.foundation.NSTimestamp;

import fr.univlr.cri.util.wo5.DateCtrl;

public class Heure extends GregorianCalendar {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8607109043805347013L;
	private GregorianCalendar debut;
	private GregorianCalendar fin;

	public Heure(final GregorianCalendar jour, final int heure) {
		initHeure(jour, heure, 0, 0);
	}

	public Heure(final GregorianCalendar jour, final int heure, final int minute) {
		initHeure(jour, heure, minute, 0);
	}

	public Heure(final GregorianCalendar jour, final int heure, final int minute, final int seconde) {
		initHeure(jour, heure, minute, seconde);
	}

	public void initHeure(final GregorianCalendar jour, final int heure, final int minute, final int seconde) {
		// NSTimestamp tmp = new NSTimestamp(jour.getTime());
		// debut = tmp.timestampByAddingGregorianUnits(0,0,0,heure,0,0);
		// fin = tmp.timestampByAddingGregorianUnits(0,0,0,heure,59,59);
		debut = new GregorianCalendar();
		// Lundi est le premier jour de la semaine
		debut.setFirstDayOfWeek(Calendar.MONDAY);
		// La première semaine est celle qui contient
		// le premier jeudi de Janvier
		debut.setMinimalDaysInFirstWeek(4);
		// debut.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		debut.set(YEAR, jour.get(YEAR));
		debut.set(MONTH, jour.get(MONTH));
		debut.set(DAY_OF_MONTH, jour.get(DAY_OF_MONTH));
		debut.set(HOUR_OF_DAY, heure);
		debut.set(MINUTE, minute);
		debut.set(SECOND, seconde);
		debut.set(MILLISECOND, 0);

		fin = new GregorianCalendar();
		// Lundi est le premier jour de la semaine
		fin.setFirstDayOfWeek(Calendar.MONDAY);
		// La première semaine est celle qui contient
		// le premier jeudi de Janvier
		fin.setMinimalDaysInFirstWeek(4);
		// fin.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		fin.set(YEAR, jour.get(YEAR));
		fin.set(MONTH, jour.get(MONTH));
		fin.set(DAY_OF_MONTH, jour.get(DAY_OF_MONTH));
		fin.set(HOUR_OF_DAY, heure + 1);
		fin.set(MINUTE, minute);
		fin.set(SECOND, seconde);
		fin.set(MILLISECOND, 0);
	}

	public String toString() {
		return DateCtrl.dateToString(debut(), "%H:%M");// +"<br>"+DateCtrl.dateToString(fin(),"%H:%M");
	}

	public NSTimestamp debut() {
		// NSTimestamp time = new NSTimestamp(debut.getTimeInMillis(),TimeZone.getTimeZone("Europe/Paris"));
		return new NSTimestamp(debut.getTimeInMillis());
	}

	public NSTimestamp fin() {
		// NSTimestamp time = new NSTimestamp(fin.getTimeInMillis(),TimeZone.getTimeZone("Europe/Paris"));
		return new NSTimestamp(fin.getTimeInMillis());
	}

	public GregorianCalendar debutGC() {
		return debut;
	}

	public GregorianCalendar finGC() {
		return fin;
	}

}
