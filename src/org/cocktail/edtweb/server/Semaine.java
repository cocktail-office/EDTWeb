package org.cocktail.edtweb.server;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;

public class Semaine {
	private final NSMutableArray _semaine;

	public Semaine() {
		super();
		_semaine = new NSMutableArray();
	}

	public void initSemaine(final int nbJour, final NSTimestamp unJourDeLaSemaine, final int heureDepart, final int nbHeureAfficher) {
		final int[] jour = { Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY,
				Calendar.SUNDAY };
		for (int i = 0; i < nbJour; i++) {
			final Jour aDay = new Jour(unJourDeLaSemaine);
			aDay.initLesHeures(heureDepart, nbHeureAfficher);
			aDay.set(Calendar.DAY_OF_WEEK, jour[i]);
			_semaine.addObject(aDay);
		}
	}

	public void initSemaine(final int nbJour, final GregorianCalendar unJourDeLaSemaine, final int heureDepart, final int nbHeureAfficher) {
		final int[] jour = { Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY,
				Calendar.SUNDAY };
		for (int i = 0; i < nbJour; i++) {
			final Jour aDay = new Jour(unJourDeLaSemaine);
			aDay.set(Calendar.DAY_OF_WEEK, jour[i]);
			aDay.initLesHeures(heureDepart, nbHeureAfficher);
			_semaine.addObject(aDay);
		}
	}

	public NSArray getAllDays() {
		return _semaine;
	}

	public Jour getFirstDay() {
		if (_semaine != null && _semaine.count() > 0) {
			return (Jour) _semaine.objectAtIndex(0);
		}
		return null;
	}

	public Jour getLastDay() {
		if (_semaine != null && _semaine.count() > 0) {
			return (Jour) _semaine.lastObject();
		}
		return null;
	}

	public Jour getJeudi() {
		if (_semaine != null && _semaine.count() > 0) {
			for (int i = 0; i < _semaine.count(); i++) {
				final Jour day = (Jour) _semaine.objectAtIndex(i);
				if (day.nomDuJour().equals(Jour.JEUDI)) {
					return day;
				}
			}
		}
		return null;
	}

}