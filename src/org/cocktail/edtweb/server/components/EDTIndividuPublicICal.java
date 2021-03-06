package org.cocktail.edtweb.server.components;
// Generated by the WOLips Core at Mon Aug 30 10:11:42 CEST 2004

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.cocktail.edtweb.server.Application;
import org.cocktail.edtweb.server.Semaine;
import org.cocktail.edtweb.server.Version;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;

import fr.univlr.cri.webapp.LRUserInfo;

public class EDTIndividuPublicICal extends EDTIndividuPublic {
	private static final long serialVersionUID = 6815504581975502975L;
	public PublishCalendarPage icalPage;

	public EDTIndividuPublicICal(final WOContext context) {
		super(context);
		icalPage = (PublishCalendarPage) pageWithName("PublishCalendarPage");
	}

	public boolean showPublicEtPrive() {
		return false;
	}

	public void appendToResponse(final WOResponse arg0, final WOContext arg1) {
		arg0.setHeader("attachement; filename=" + icalPage.calendarName + ".ics", "Content-Disposition");
		super.appendToResponse(arg0, arg1);
	}

	public void initIcal(NSTimestamp debut, NSTimestamp fin) {
		listeResa = new NSMutableArray();
		Semaine semaineDebut = null, semaineFin = null;
		if (debut == null || fin == null) {
			final GregorianCalendar now = new GregorianCalendar();
			// Lundi est le premier jour de la semaine
			now.setFirstDayOfWeek(Calendar.MONDAY);
			// La première semaine est celle qui contient
			// le premier jeudi de Janvier
			now.setMinimalDaysInFirstWeek(4);
			semaineDebut = new Semaine();
			semaineDebut.initSemaine(7, now, 0, 24);
			semaineFin = new Semaine();
			now.add(Calendar.WEEK_OF_YEAR, ((Application) application()).nbWeekIcal() - 1);
			semaineFin.initSemaine(7, now, 0, 24);
		}
		else {
			semaineDebut = new Semaine();
			semaineDebut.initSemaine(7, debut, 0, 24);
			semaineFin = new Semaine();
			semaineFin.initSemaine(7, fin, 0, 24);
		}
		final Responder responder = (Responder) session().objectForKey(AffichePlanning.RESPONDER);

		responder.initPlanning((NSMutableArray) session().objectForKey(AffichePlanning.LISTE_OBJET));
		responder.initListeResa(semaineDebut.getFirstDay().debutDeJournnee(), semaineFin.getLastDay().debutDeJournnee());
		icalPage.setCalendarName(localUser.nomEtPrenom());
		icalPage.setProdId(Version.APPLI_ID + " v" + Version.VERSIONNUMMAJ + "." + Version.VERSIONNUMMIN + "." + Version.VERSIONNUMPATCH + "."
				+ Version.VERSIONNUMBUILD);

		// si etudiant, récupère en plus son edt de formation...
		if (localUser.userStatus() == LRUserInfo.STATUS_ETUDIANT) {
			listeResa.addObjectsFromArray(getPlanningEtudiant(criApp.dataBus(), localUser.persId(), semaineDebut.getFirstDay().debutDeJournnee(),
					semaineFin.getLastDay().finDeJournnee()));
			if (((Application) criApp).useExamens()) {
				listeResa.addObjectsFromArray(getPlanningEtudiantExamens(criApp.dataBus(), localUser.persId(), semaineDebut.getFirstDay()
						.debutDeJournnee(), semaineFin.getLastDay().finDeJournnee()));
			}
		}

		icalPage.addEventsFromArray(listeResa);
	}

	public WOComponent afficheICal() {
		try {
			initIcal(null, null);
			return icalPage;
		}
		catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}
}