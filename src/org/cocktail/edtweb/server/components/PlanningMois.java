package org.cocktail.edtweb.server.components;
//
// PlanningMois.java: Class file for WO Component 'PlanningMois'
// Project EDTLudo
//
// Created by ludo on 25/07/05
//
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.cocktail.edtweb.server.AffichePlanningResponder;
import org.cocktail.edtweb.server.Application;
import org.cocktail.edtweb.server.EDTObject;
import org.cocktail.edtweb.server.Jour;
import org.cocktail.edtweb.server.Utils;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSTimestampFormatter;

import fr.univlr.cri.util.StringCtrl;
import fr.univlr.cri.util.wo5.DateCtrl;

public class PlanningMois extends WOComponent {
	private static final long serialVersionUID = -1698551259876312033L;
	public boolean boo;
	public NSTimestamp dateDebut;
	public String heureDebut;
	public String heureFin;

	protected NSTimestamp jour;

	/** @TypeInfo com.webobjects.foundation.NSTimestamp */
	protected NSMutableArray liste;
	protected int lig;
	protected int col;

	/** @TypeInfo java.lang.String */
	protected NSMutableArray liste2;
	protected String ele;
	protected String couleur;
	protected Jour dateJour;
	public AffichePlanningResponder responder;
	public static boolean selectPlanningParJour = false;

	private NSArray listeMoisComplete = null;

	public PlanningMois(final WOContext context) {
		super(context);
		responder = (AffichePlanningResponder) session().objectForKey("responder");
	}

	public boolean synchronizesVariablesWithBindings() {
		return false;
	}

	public NSTimestamp jour() {
		return jour;
	}

	public void setJour(NSTimestamp newJour) {
		jour = newJour;
		return;
	}

	public void myInit() {
		dateDebut = ((NSTimestamp) this.session().objectForKey("dateDebut"));
		liste = new NSMutableArray();
		Jour jour = new Jour(dateDebut);
		int mois = jour.get(Calendar.MONTH) + 1;
		int i, j, k, l;
		// ***j sert a savoir le nombre de jour dans le mois
		if (mois == 1 || mois == 3 || mois == 5 || mois == 7 || mois == 8 || mois == 10 || mois == 12) {
			j = 31;
		}
		else {
			if (mois == 4 || mois == 6 || mois == 9 || mois == 11) {
				j = 30;
			}
			else {
				if (mois == 2 && jour.isLeapYear(jour.get(Calendar.YEAR))) {
					j = 29;
				}
				else {
					j = 28;
				}
			}
		}
		NSTimestamp jdeb = new NSTimestamp(jour.get(Calendar.YEAR), jour.get(Calendar.MONTH) + 1, 1, 0, 0, 0, TimeZone.getTimeZone("GMT"));
		// ***k sert a savoir quel est le premier jour du mois
		jour.setTime(jdeb);
		k = (jour.get(Calendar.DAY_OF_WEEK) + 5) % 7;
		NSTimestamp debut = null, fin = null;
		for (i = 0; i < k; i++) {
			liste.addObject(new NSTimestamp(1970, 1, 1, 0, 1, 0, TimeZone.getTimeZone("GMT")));
		}
		for (i = k; i < j + k; i++) {
			liste.addObject(jdeb.timestampByAddingGregorianUnits(0, 0, i - k, 0, 0, 0));
			if (i == k) {
				debut = (NSTimestamp) liste.lastObject();
			}
		}
		fin = (NSTimestamp) liste.lastObject();
		for (l = k + j; l <= 42; l++) {
			liste.addObject(new NSTimestamp(1970, 1, 1, 0, 1, 0, TimeZone.getTimeZone("GMT")));
		}

		if (responder != null && liste != null && liste.count() > 1) {
			listeMoisComplete = responder.getListeResaObjet(debut, fin);
		}
		else {
			listeMoisComplete = null;
		}
	}

	/** @TypeInfo com.webobjects.foundation.NSTimestamp */
	public NSMutableArray liste() {
		return liste;
	}

	public void setListe(NSMutableArray newListe) {
		liste = newListe;
	}

	public int lig() {
		return lig;
	}

	public void setLig(final int newLig) {
		lig = newLig;
	}

	public int col() {
		return col;
	}

	public void setCol(final int newCol) {
		col = newCol;
	}

	public void appendToResponse(final WOResponse arg0, final WOContext arg1) {
		myInit();
		super.appendToResponse(arg0, arg1);
	}

	public String affiche() {
		final int index = col + (lig * 7);
		final NSTimestamp tmp = (NSTimestamp) liste.objectAtIndex(index);
		GregorianCalendar tmpGC = new GregorianCalendar();
		tmpGC.setTime(tmp);
		if (tmpGC.get(Calendar.YEAR) == 1970) {
			return new String("");
		}
		else {
			return (new Integer(tmpGC.get(Calendar.DAY_OF_MONTH))).toString();
		}
	}

	public WOComponent goJour() {
		final int index = col + (lig * 7);
		// session().setObjectForKey((NSTimestamp)liste.objectAtIndex(index),"dateDebut");
		session().setObjectForKey(liste.objectAtIndex(index), "dateDebut");
		session().setObjectForKey("PlanningParJour", EDTIndividu.TYPE_PLANNING);
		session().setObjectForKey(new Boolean(true), "InitChoixDate");
		selectPlanningParJour = true;
		return null;
	}

	/** @TypeInfo java.lang.String */
	public NSMutableArray liste2() {
		Jour j;
		String str;
		boolean boo = false;
		int max = 30;
		liste2 = new NSMutableArray();
		int index = col + (lig * 7);
		NSTimestamp tmp2 = (NSTimestamp) liste.objectAtIndex(index);
		GregorianCalendar tmp2GC = new GregorianCalendar();
		tmp2GC.setTime(tmp2);
		if (tmp2GC.get(Calendar.YEAR) != 1970) {
			j = new Jour(tmp2);
			NSTimestamp debut = j.debutDeJournnee();
			NSTimestamp fin = j.finDeJournnee();
			NSArray tmp;
			// if (responder != null) {
			// tmp = responder.getListeResaObjet(debut, fin);
			// }
			// else {
			// tmp = new NSArray();
			// }
			if (listeMoisComplete != null) {
				NSMutableArray resas = new NSMutableArray();
				for (int i = 0; i < listeMoisComplete.count(); i++) {
					EDTObject obj = (EDTObject) listeMoisComplete.objectAtIndex(i);
					if ((obj.dateDebut().after(debut) && obj.dateDebut().before(fin)) || (obj.dateFin().after(debut) && obj.dateFin().before(fin))) {
						resas.addObject(obj);
					}
				}
				tmp = resas.immutableClone();
			}
			else {
				tmp = new NSArray();
			}
			NSMutableArray tmp3 = new NSMutableArray(tmp);
			NSMutableArray tmp4 = new NSMutableArray(tmp);
			for (int i = 0; i < tmp3.count(); i++) {
				EDTObject obj = (EDTObject) tmp3.objectAtIndex(i);
				Jour d = new Jour(obj.dateDebutGC());
				Jour f = new Jour(obj.dateFinGC());
				if ((f.before(new Jour(j.debutDeJournnee()))) || (d.after(new Jour(j.finDeJournnee())))) {
					tmp4.removeObject(obj);
				}
			}
			if (tmp4.count() > max) {
				boo = true;
			}
			else {
				max = tmp4.count();
			}

			NSTimestampFormatter formatJour = new NSTimestampFormatter("%d / %m / %Y  à  %H : %M");
			for (int i = 0; i < max; i++) {
				EDTObject obj = (EDTObject) tmp4.objectAtIndex(i);
				str = obj.sujet(false);
				if (str.length() > 19) {
					str = Utils.cleanCut(str, 19);
					str = str.concat("...");
				}
				str = "<a href=\"javascript:return false;\" onMouseOver=\"return overlib('"
						+ formatString(obj.sujet(true))
						+ "', CAPTION, '"
						+ formatString("Du " + DateCtrl.dateToString(obj.dateDebut(), formatJour.pattern()) + " <BR>Au "
								+ DateCtrl.dateToString(obj.dateFin(), formatJour.pattern())) + "');\" onMouseOut=\"return nd();\"" + "> -&nbsp;"
						+ str + "</a>";
				liste2.addObject(str);
			}
			if (boo == true) {
				liste2.addObject("...");
			}
		}
		return liste2;
	}

	public String ele() {
		return ele;
	}

	public void setEle(final String newEle) {
		ele = newEle;
	}

	public WOComponent goMoisPrec() {
		dateDebut = dateDebut.timestampByAddingGregorianUnits(0, -1, 0, 0, 0, 0);
		session().setObjectForKey(dateDebut, "dateDebut");
		session().setObjectForKey(new Boolean(true), "InitChoixDate");
		return null;
	}

	public WOComponent goMoisSuiv() {
		dateDebut = dateDebut.timestampByAddingGregorianUnits(0, 1, 0, 0, 0, 0);
		session().setObjectForKey(dateDebut, "dateDebut");
		session().setObjectForKey(new Boolean(true), "InitChoixDate");
		return null;
	}

	public String couleur() {
		int index = col + (lig * 7);
		NSTimestamp tmp2 = (NSTimestamp) liste.objectAtIndex(index);
		GregorianCalendar tmp2GC = new GregorianCalendar();
		tmp2GC.setTime(tmp2);
		if (tmp2GC.get(Calendar.YEAR) != 1970) {
			couleur = "#ffffff";
		}
		else {
			couleur = ((Application) application()).coulTClair();
		}
		return couleur;
	}

	public Jour dateJour() {
		dateJour = new Jour(dateDebut);
		return dateJour;
	}

	private String formatString(String str) {
		// remplace les " par \"
		if (str == null) {
			return "";
		}
		str = StringCtrl.replace(str, "\"", "&quot;");
		// remplace les ' par \'
		str = StringCtrl.replace(str, "'", "\\'");
		// remplace le retour chariot
		str = str.replaceAll("\n", "");
		str = StringCtrl.replace(str, "\r", "<BR>");
		return str;
	}

}
