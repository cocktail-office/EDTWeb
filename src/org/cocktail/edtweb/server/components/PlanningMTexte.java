package org.cocktail.edtweb.server.components;
//
// PlanningMTexte.java: Class file for WO Component 'PlanningMTexte'
// Project EDTLudo
//
// Created by ludo on 16/08/05
//
import java.util.Calendar;
import java.util.TimeZone;

import org.cocktail.edtweb.server.AffichePlanningResponder;
import org.cocktail.edtweb.server.EDTObject;
import org.cocktail.edtweb.server.Jour;
import org.cocktail.edtweb.server.Session;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSTimestamp;

public class PlanningMTexte extends WOComponent {

	private static final long serialVersionUID = 228633995194097737L;
	public NSTimestamp dateDebut;
	public Jour dateDuJour;
	/** @TypeInfo Jour */
	protected NSMutableArray liste;
	protected Jour unJour;
	protected String ele;

	/** @TypeInfo java.lang.String */
	protected NSMutableArray liste2;
	protected boolean boo;
	public AffichePlanningResponder responder;

	private NSArray listeMoisComplete = null;

	private static final String OBJET_RESA = "ResaDate";

	public PlanningMTexte(final WOContext context) {
		super(context);
		responder = (AffichePlanningResponder) session().objectForKey("responder");
	}

	public boolean synchronizesVariablesWithBindings() {
		return false;
	}

	public void myInit() {

		dateDebut = ((NSTimestamp) this.session().objectForKey("dateDebut"));
		dateDuJour = new Jour(dateDebut);
		liste = new NSMutableArray();
		final Jour jour = new Jour(dateDebut);
		final int mois = jour.get(Calendar.MONTH) + 1;
		int i, j;
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
		final NSTimestamp jdeb = new NSTimestamp(jour.get(Calendar.YEAR), jour.get(Calendar.MONTH) + 1, 1, 0, 0, 0, TimeZone.getTimeZone("GMT"));
		for (i = 0; i < j; i++) {
			liste.addObject(new Jour(jdeb.timestampByAddingGregorianUnits(0, 0, i, 0, 0, 0)));
		}
		if (responder != null && liste != null && liste.count() > 1) {
			setListeMoisComplete(responder.getListeResaObjet(((Jour) liste.objectAtIndex(0)).toNSTimestamp(),
					((Jour) liste.lastObject()).toNSTimestamp()).immutableClone());
		}
		else {
			setListeMoisComplete(null);
		}
	}

	public void setListeMoisComplete(NSArray listeMoisComplete) {
		this.listeMoisComplete = listeMoisComplete;
	}

	public NSArray listeMoisComplete() {
		return this.listeMoisComplete;
	}

	public void appendToResponse(final WOResponse arg0, final WOContext arg1) {
		myInit();
		super.appendToResponse(arg0, arg1);
	}

	/** @TypeInfo Jour */
	public NSMutableArray liste() {
		return liste;
	}

	public void setListe(final NSMutableArray newListe) {
		liste = newListe;
	}

	public Jour unJour() {
		return unJour;
	}

	public void setUnJour(final Jour newUnJour) {
		unJour = newUnJour;
	}

	public String ele() {
		return ele;
	}

	public void setEle(final String newEle) {
		ele = newEle;
	}

	/** @TypeInfo java.lang.String */
	public NSMutableArray liste2() {
		liste2 = new NSMutableArray();
		final NSTimestamp debut = unJour.debutDeJournnee();
		final NSTimestamp fin = unJour.finDeJournnee();
		if (responder != null) {
			// final NSArray tmp = responder.getListeResaObjet(debut, fin);
			NSArray tmp;
			if (listeMoisComplete() != null) {
				NSMutableArray resas = new NSMutableArray();
				for (int i = 0; i < listeMoisComplete().count(); i++) {
					EDTObject obj = (EDTObject) listeMoisComplete().objectAtIndex(i);
					if ((obj.dateDebut().after(debut) && obj.dateDebut().before(fin)) || (obj.dateFin().after(debut) && obj.dateFin().before(fin))) {
						resas.addObject(obj);
					}
				}
				tmp = resas.immutableClone();
			}
			else {
				tmp = new NSArray();
			}
			for (int i = 0; i < tmp.count(); i++) {
				final EDTObject obj = (EDTObject) tmp.objectAtIndex(i);
				final Jour d = new Jour(obj.dateDebutGC());
				final Jour f = new Jour(obj.dateFinGC());
				if ((!f.before(new Jour(unJour.debutDeJournnee()))) && (!d.after(new Jour(unJour.finDeJournnee())))) {
					String strTmp;
					if (d.numJour() == f.numJour()) {
						strTmp = (new Jour(obj.dateDebutDTZ())).heure() + "-" + (new Jour(obj.dateFinDTZ())).heure() + " : " + obj.sujet(false);
					}
					else {
						if (d.numJour() != unJour.numJour()) {
							strTmp = "00:00-" + (new Jour(obj.dateFinDTZ())).heure() + " : " + obj.sujet(false);
						}
						else {
							strTmp = (new Jour(obj.dateDebutDTZ())).heure() + "-23:59 : " + obj.sujet(false);
						}
					}
					liste2.addObject(strTmp);
				}
			}
		}
		return liste2;
	}

	private String voirResaUrl() {
		int heured, mind, heuref, minf;
		final String[] lou = ele.substring(0, 11).split("-");
		try {
			heured = new Integer((lou[0].split(":"))[0]).intValue();
		}
		catch (final NumberFormatException e) {
			heured = 0;
		}
		try {
			mind = new Integer((lou[0].split(":"))[1]).intValue();
		}
		catch (final NumberFormatException e) {
			mind = 0;
		}
		try {
			heuref = new Integer((lou[1].split(":"))[0]).intValue();
		}
		catch (final NumberFormatException e) {
			heuref = 0;
		}
		try {
			minf = new Integer((lou[1].split(":"))[1]).intValue();
		}
		catch (final NumberFormatException e) {
			minf = 0;
		}
		NSTimestamp deb = unJour.debutDeJournnee();
		// ***Probleme si rdv de une minute
		deb = deb.timestampByAddingGregorianUnits(0, 0, 0, heured, mind, 0);
		NSTimestamp fin = unJour.debutDeJournnee();
		fin = fin.timestampByAddingGregorianUnits(0, 0, 0, heuref, minf, 0);
		NSArray tmp;
		if (listeMoisComplete() != null) {
			NSMutableArray resas = new NSMutableArray();
			for (int i = 0; i < listeMoisComplete().count(); i++) {
				EDTObject obj = (EDTObject) listeMoisComplete().objectAtIndex(i);
				if ((obj.dateDebut().equals(deb) && obj.dateFin().equals(fin))) {
					resas.addObject(obj);
					break;
				}
			}
			tmp = resas.immutableClone();
		}
		else {
			tmp = new NSArray();
		}
		if (tmp.count() != 0) {
			final EDTObject obj = (EDTObject) tmp.objectAtIndex(0);
			session().setObjectForKey(obj, OBJET_RESA);
			final Number resaOrdre = obj.key();
			((Session) session()).getObjetResa().setObjectForKey(obj, Integer.toString(resaOrdre.intValue()));
			final NSMutableDictionary dico = new NSMutableDictionary();
			dico.setObjectForKey(Integer.toString(resaOrdre.intValue()), "objet");
			final String url = context().directActionURLForActionNamed("infoForResa", dico);
			return url;
		}
		return null;
	}

	public String voirResaMemePage() {
		return voirResaUrl();
	}

	public String voirResa() {
		String url = voirResaUrl();
		if (url == null) {
			return null;
		}
		return "javascript: messageWindow('" + url + "','Informations','300','300');";
	}

	public boolean boo() {
		boo = false;
		if (liste2.count() == 0) {
			boo = true;
		}
		return boo;
	}

	public WOComponent goPrec() {
		dateDuJour.add(Calendar.MONTH, -1);
		session().setObjectForKey((dateDuJour).toNSTimestamp(), "dateDebut");
		session().setObjectForKey(new Boolean(true), "InitChoixDate");
		return null;
	}

	public WOComponent goSuiv() {
		dateDuJour.add(Calendar.MONTH, 1);
		session().setObjectForKey((dateDuJour).toNSTimestamp(), "dateDebut");
		session().setObjectForKey(new Boolean(true), "InitChoixDate");
		return null;
	}

}
