package org.cocktail.edtweb.server;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSTimestampFormatter;

import fr.univlr.cri.util.wo5.DateCtrl;

public class Jour extends GregorianCalendar {
	/*
	 * public static final String LUNDI="Lundi"; public static final String MARDI="Mardi"; public static final String MERCREDI="Mercredi";
	 * public static final String JEUDI="Jeudi"; public static final String VENDREDI="Vendredi"; public static final String SAMEDI="Samedi";
	 * public static final String DIMANCHE="Dimanche";
	 */

	private static final long serialVersionUID = -2993071427884572517L;
	public static final String LUNDI = "Lun";
	public static final String MARDI = "Mar";
	public static final String MERCREDI = "Mer";
	public static final String JEUDI = "Jeu";
	public static final String VENDREDI = "Ven";
	public static final String SAMEDI = "Sam";
	public static final String DIMANCHE = "Dim";
	private NSMutableArray _listeHeures;

	public Jour() {
		super();
	}

	public Jour(final NSTimestamp day) {
		super();
		// Lundi est le premier jour de la semaine
		setFirstDayOfWeek(Calendar.MONDAY);
		// La première semaine est celle qui contient
		// le premier jeudi de Janvier
		setMinimalDaysInFirstWeek(4);
		this.setTime(day);
		// this.initLesHeures();
	}

	public Jour(final GregorianCalendar day) {
		super();
		// Lundi est le premier jour de la semaine
		setFirstDayOfWeek(Calendar.MONDAY);
		// La première semaine est celle qui contient
		// le premier jeudi de Janvier
		setMinimalDaysInFirstWeek(4);
		this.setTimeInMillis(day.getTimeInMillis());
	}

	public void initLesHeures(final int heureDepart, final int nbHeureAfficher) {
		this.set(Calendar.HOUR_OF_DAY, 0);
		this.set(Calendar.MINUTE, 0);
		this.set(Calendar.SECOND, 0);
		this.set(Calendar.MILLISECOND, 0);
		this.initHeures(heureDepart, nbHeureAfficher);
	}

	private void initHeures(final int debut, final int nbHeure) {
		_listeHeures = new NSMutableArray();
		for (int i = 0; i < nbHeure; i++) {
			_listeHeures.addObject(new Heure(this, debut + i));
		}
	}

	public NSArray heuresDuJour() {
		return _listeHeures;
	}

	public String nomDuJour() {
		final int leJour = super.get(DAY_OF_WEEK);
		switch (leJour) {
			case MONDAY:
				return LUNDI;
			case TUESDAY:
				return MARDI;
			case WEDNESDAY:
				return MERCREDI;
			case THURSDAY:
				return JEUDI;
			case FRIDAY:
				return VENDREDI;
			case SATURDAY:
				return SAMEDI;
			case SUNDAY:
				return DIMANCHE;
			default:
				break;
		}
		return null;
	}

	public String nomDuJour(final int jour) {
		switch (jour) {
			case MONDAY:
				return LUNDI;
			case TUESDAY:
				return MARDI;
			case WEDNESDAY:
				return MERCREDI;
			case THURSDAY:
				return JEUDI;
			case FRIDAY:
				return VENDREDI;
			case SATURDAY:
				return SAMEDI;
			case SUNDAY:
				return DIMANCHE;
			default:
				break;
		}
		return null;
	}

	public String nomDuMois() {
		final int leJour = super.get(MONTH);
		switch (leJour) {
			case JANUARY:
				return "Janvier";
			case FEBRUARY:
				return "F&eacute;vrier";
			case MARCH:
				return "Mars";
			case APRIL:
				return "Avril";
			case MAY:
				return "Mai";
			case JUNE:
				return "Juin";
			case JULY:
				return "Juillet";
			case AUGUST:
				return "Ao&ucirc;t";
			case SEPTEMBER:
				return "Septembre";
			case OCTOBER:
				return "Octobre";
			case NOVEMBER:
				return "Novembre";
			case DECEMBER:
				return "D&eacute;cembre";
			default:
				break;
		}
		return null;
	}

	public int noSemaine() {
		return get(Calendar.WEEK_OF_YEAR);
	}

	public String toString() {
		return nomDuJour() + " " + get(DATE) + "  " + nomDuMois();
	}

	public NSTimestamp toNSTimestamp() {
		final NSTimestamp time = new NSTimestamp(this.getTime());
		return time;
	}

	public String toStringComplet() {
		return this.toString() + " " + this.get(Calendar.YEAR) + "  à  " + DateCtrl.dateToString(this.toNSTimestamp(), "%H:%M");
	}

	public String toStringWithFormatter(final NSTimestampFormatter formatter) {
		// NSTimestamp tmp = new NSTimestamp(this.toNSTimestamp().getTime(),TimeZone.getTimeZone("GMT+01"));
		// return formatter.format(new NSTimestamp(tmp));
		return DateCtrl.dateToString(this.toNSTimestamp(), formatter.pattern());
	}

	public NSTimestamp debutDeJournnee() {
		final GregorianCalendar tmp = new GregorianCalendar();
		// Lundi est le premier jour de la semaine
		tmp.setFirstDayOfWeek(Calendar.MONDAY);
		// La première semaine est celle qui contient
		// le premier jeudi de Janvier
		tmp.setMinimalDaysInFirstWeek(4);
		tmp.setTimeInMillis(this.getTimeInMillis());
		// tmp.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		tmp.set(Calendar.HOUR_OF_DAY, 0);
		tmp.set(Calendar.MINUTE, 0);
		tmp.set(Calendar.SECOND, 0);
		tmp.set(Calendar.MILLISECOND, 0);
		return new NSTimestamp(tmp.getTime());
	}

	public NSTimestamp finDeJournnee() {
		final GregorianCalendar tmp = new GregorianCalendar();
		// Lundi est le premier jour de la semaine
		tmp.setFirstDayOfWeek(Calendar.MONDAY);
		// La première semaine est celle qui contient
		// le premier jeudi de Janvier
		tmp.setMinimalDaysInFirstWeek(4);
		tmp.setTimeInMillis(this.getTimeInMillis());
		// tmp.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		tmp.set(Calendar.HOUR_OF_DAY, 23);
		tmp.set(Calendar.MINUTE, 59);
		tmp.set(Calendar.SECOND, 59);
		tmp.set(Calendar.MILLISECOND, 999);
		return new NSTimestamp(tmp.getTime());
	}

	// ****Ludo
	public int annee() {
		return get(YEAR);
	}

	// ****Ludo
	public int numJour() {
		return get(DATE);
	}

	// ***LUDO
	public String heure() {
		return DateCtrl.dateToString(this.toNSTimestamp(), "%H:%M");
	}
}