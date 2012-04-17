package org.cocktail.edtweb.server.components;
//
// ChoixDate.java: Class file for WO Component 'ChoixDate'
// Project EDTLudo
//
// Created by ludo on 18/08/05
//

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.cocktail.edtweb.server.Application;
import org.cocktail.edtweb.server.Jour;
import org.cocktail.edtweb.server.Session;
import org.cocktail.edtweb.server.metier.ResaObjet;
import org.cocktail.edtweb.server.metier.Salles;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;

import fr.univlr.cri.webapp.CRIWebComponent;
import fr.univlr.cri.webapp.LRUserInfo;

public class ChoixDate extends CRIWebComponent {

	private static final long serialVersionUID = 6946659047583781807L;
	protected int i;
	protected int j;
	protected Jour dateDuJour;
	protected NSTimestamp dateDebut;
	protected boolean boo;
	/** @TypeInfo Integer */
	protected NSMutableArray liste;
	protected String ele;
	protected NSTimestamp dateCour;
	protected String heureFin;
	protected String heureDebut;
	protected boolean boo2;
	protected boolean boo3;
	protected String nom;

	public ChoixDate(final WOContext context) {
		super(context);
		boo2 = false;

	}

	public boolean synchronizesVariablesWithBindings() {
		return false;
	}

	public void myInit() {
		heureDebut = (String) session().objectForKey("heureDebut");
		heureFin = (String) session().objectForKey("heureFin");
		if (boo == false) {
			dateCour = (NSTimestamp) (session().objectForKey("Datecour"));
		}
		GregorianCalendar dateCourGC = new GregorianCalendar();
		dateCourGC.setTime(dateCour);
		dateCour = new NSTimestamp(dateCourGC.get(Calendar.YEAR), dateCourGC.get(Calendar.MONTH) + 1, 1, 0, 0, 0, TimeZone.getTimeZone("GMT"));
		dateDuJour = new Jour(dateCour);

		liste = new NSMutableArray();
		final int mois = dateDuJour.get(Calendar.MONTH) + 1;
		int ind, jo, k;
		// ***j sert a savoir le nombre de jour dans le mois
		if (mois == 1 || mois == 3 || mois == 5 || mois == 7 || mois == 8 || mois == 10 || mois == 12) {
			jo = 31;
		}
		else {
			if (mois == 4 || mois == 6 || mois == 9 || mois == 11) {
				jo = 30;
			}
			else {
				if (mois == 2 && dateDuJour.isLeapYear(dateDuJour.get(Calendar.YEAR))) {
					jo = 29;
				}
				else {
					jo = 28;
				}
			}
		}
		// ***k sert a savoir quel est le premier jour du mois
		dateCourGC.setTime(dateCour);
		k = (dateCourGC.get(Calendar.DAY_OF_WEEK) + 5) % 7;
		for (ind = 0; ind < k; ind++) {
			liste.addObject(new Integer(0));
		}
		for (ind = k; ind < jo + k; ind++) {
			liste.addObject(new Integer(ind - k + 1));
		}
		for (ind = k + jo; ind <= 42; ind++) {
			liste.addObject(new Integer(0));
		}
	}

	public void appendToResponse(final WOResponse arg0, final WOContext arg1) {
		boo = false;
		dateDebut = (NSTimestamp) session().objectForKey("dateDebut");
		if (dateDebut == null) {
			dateDebut = new NSTimestamp();
		}
		if ((session().objectForKey("InitChoixDate") == null) || ((Boolean) session().objectForKey("InitChoixDate")).booleanValue() == true) {
			dateCour = dateDebut;
			session().setObjectForKey(dateCour, "Datecour");
			boo = true;
			session().setObjectForKey(new Boolean(false), "InitChoixDate");
		}
		myInit();

		super.appendToResponse(arg0, arg1);
	}

	public int i() {
		return i;
	}

	public void setI(final int newI) {
		i = newI;
	}

	public int j() {
		return j;
	}

	public void setJ(final int newJ) {
		j = newJ;
	}

	public Jour dateDuJour() {
		return dateDuJour;
	}

	public void setDateDuJour(final Jour newDateDuJour) {
		dateDuJour = newDateDuJour;
	}

	/** @TypeInfo Integer */
	public NSMutableArray liste() {
		return liste;
	}

	public void setListe(final NSMutableArray newListe) {
		liste = newListe;
	}

	public String ele() {
		final int index = i + j * 7;
		if (((Integer) liste.objectAtIndex(index)).intValue() == 0) {
			return " ";
		}
		return ((Integer) liste.objectAtIndex(index)).toString();
	}

	public WOComponent selection() {
		final int index = i + j * 7;
		final int nb = ((Integer) liste.objectAtIndex(index)).intValue();
		GregorianCalendar dateCourGC = new GregorianCalendar();
		dateCourGC.setTime(dateCour);
		dateCour = new NSTimestamp(dateCourGC.get(Calendar.YEAR), dateCourGC.get(Calendar.MONTH) + 1, nb, 0, 0, 0, TimeZone.getTimeZone("GMT"));
		session().setObjectForKey(dateCour, "dateDebut");
		session().setObjectForKey(new Boolean(true), "InitChoixDate");
		session().setObjectForKey(new Boolean(true), "refreshComposant");
		session().setObjectForKey(new Boolean(true), "refreshPlanning");// ajouter
		session().setObjectForKey("AffichePlanning", EDTIndividu.TYPE_PLANNING);// ajouter

		return null;
	}

	public NSTimestamp dateCour() {
		return dateCour;
	}

	public WOComponent goMoisP() {
		dateCour = dateCour.timestampByAddingGregorianUnits(0, -1, 0, 0, 0, 0);
		session().setObjectForKey(dateCour, "Datecour");
		session().setObjectForKey(new Boolean(false), "InitChoixDate");
		return null;
	}

	public WOComponent goAnneeP() {
		dateCour = dateCour.timestampByAddingGregorianUnits(-1, 0, 0, 0, 0, 0);
		session().setObjectForKey(dateCour, "Datecour");
		session().setObjectForKey(new Boolean(false), "InitChoixDate");
		return null;
	}

	public WOComponent goMoisS() {
		dateCour = dateCour.timestampByAddingGregorianUnits(0, 1, 0, 0, 0, 0);
		session().setObjectForKey(dateCour, "Datecour");
		session().setObjectForKey(new Boolean(false), "InitChoixDate");
		return null;
	}

	public WOComponent goAnneeS() {
		dateCour = dateCour.timestampByAddingGregorianUnits(1, 0, 0, 0, 0, 0);
		session().setObjectForKey(dateCour, "Datecour");
		session().setObjectForKey(new Boolean(false), "InitChoixDate");
		return null;
	}

	public String color() {
		final int index = i + j * 7;
		if (((Integer) liste.objectAtIndex(index)).intValue() == 0) {
			return ((Application) application()).coulTClair();
		}
		GregorianCalendar dateDebutGC = new GregorianCalendar();
		dateDebutGC.setTime(dateDebut);
		GregorianCalendar dateCourGC = new GregorianCalendar();
		dateCourGC.setTime(dateCour);
		if ((dateDebutGC.get(Calendar.YEAR) == dateCourGC.get(Calendar.YEAR)) && (dateDebutGC.get(Calendar.MONTH) == dateCourGC.get(Calendar.MONTH))
				&& (((Integer) liste.objectAtIndex(index)).intValue() == dateDebutGC.get(Calendar.DAY_OF_MONTH))) {
			return ((Application) application()).coulTFonce();
		}
		return "#ffffff";
	}

	public String jour() {
		String str;
		if (dateDebut != null) {
			GregorianCalendar dateDebutGC = new GregorianCalendar();
			dateDebutGC.setTime(dateDebut);
			str = (new Integer(dateDebutGC.get(Calendar.DAY_OF_MONTH))).toString();
		}
		else {
			str = (new Integer(new GregorianCalendar().get(Calendar.DAY_OF_MONTH))).toString();
		}
		if (str.length() == 1) {
			str = "0" + str;
		}
		return str;
	}

	public String mois() {
		String str;
		if (dateDebut != null) {
			GregorianCalendar dateDebutGC = new GregorianCalendar();
			dateDebutGC.setTime(dateDebut);
			str = (new Integer(dateDebutGC.get(Calendar.MONTH) + 1)).toString();
		}
		else {
			str = (new Integer(new GregorianCalendar().get(Calendar.MONTH) + 1)).toString();
		}
		if (str.length() == 1) {
			str = "0" + str;
		}
		return str;
	}

	public String annee() {
		String str;
		if (dateDebut != null) {
			GregorianCalendar dateDebutGC = new GregorianCalendar();
			dateDebutGC.setTime(dateDebut);
			str = (new Integer(dateDebutGC.get(Calendar.YEAR))).toString();
		}
		else {
			str = (new Integer(new GregorianCalendar().get(Calendar.YEAR))).toString();
		}
		if (str.length() == 1) {
			str = "0" + str;
		}
		return str;
	}

	public WOComponent affiche() {
		try {
			final int nb1 = Integer.parseInt(heureDebut);
			final int nb2 = Integer.parseInt(heureFin);
			if ((nb1 >= nb2) || (nb1 < 0) || (nb2 < 0) || (nb1 > 24) || (nb2 > 24)) {
				boo2 = true;
				return null;
			}
		}
		catch (final NumberFormatException e) {
			boo2 = true;
			return null;
		}
		boo2 = false;
		session().setObjectForKey(heureDebut, "heureDebut");
		session().setObjectForKey(heureFin, "heureFin");
		session().setObjectForKey(new Boolean(true), "refreshComposant");

		session().setObjectForKey(session().objectForKey("dateDebut"), "dateDebut");// ajouter
		session().setObjectForKey(new Boolean(true), "refreshPlanning");// ajouter

		return null;
	}

	public String heureFin() {
		return heureFin;
	}

	public void setHeureFin(final String newHeureFin) {
		heureFin = newHeureFin;
	}

	public String heureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(final String newHeureDebut) {
		heureDebut = newHeureDebut;
	}

	public boolean boo2() {
		return boo2;
	}

	public WOComponent goJour() {
		session().setObjectForKey("PlanningParJour", EDTIndividu.TYPE_PLANNING);
		return null;
	}

	public WOComponent goSem() {
		session().setObjectForKey("AffichePlanning", EDTIndividu.TYPE_PLANNING);
		return null;
	}

	public WOComponent goMC() {
		session().setObjectForKey("PlanningMois", EDTIndividu.TYPE_PLANNING);
		return null;
	}

	public WOComponent goMT() {
		session().setObjectForKey("PlanningMTexte", EDTIndividu.TYPE_PLANNING);
		return null;
	}

	public WOComponent goCarnet() {
		session().setObjectForKey("Carnet", EDTIndividu.TYPE_PLANNING);
		return null;
	}

	public boolean boo3() {
		return ((Session) session()).getAffichageEnCours() == Main.AFFICHE_INDIVIDU;
	}

	public boolean boo4() {
		return ((Session) session()).getAffichageEnCours() == Main.AFFICHE_OBJET;
	}

	public ResaObjet resaObjet() {
		return (ResaObjet) session().objectForKey(EDTObjetMenu.OBJET_SELECTED);
	}

	public String nom() {

		switch (((Session) session()).getAffichageEnCours()) {
			case Main.AFFICHE_INDIVIDU_CHOISI:
				nom = ((LRUserInfo) session().objectForKey(EDTIndividuChoisi.INDIVIDU_CHOISI)).nomEtPrenom() + " (inscrit en tant que "
						+ criSession().connectedUserInfo().nomEtPrenom() + ")";
				break;
			case Main.AFFICHE_CHOIX_INDIVIDU:
			case Main.AFFICHE_INDIVIDU_PUBLIC:
				nom = (String) session().objectForKey(EDTIndividuPublic.LOGIN_EXTERNE);// ajouter
				break;
			case Main.AFFICHE_INDIVIDU:
				nom = criSession().connectedUserInfo().nomEtPrenom();
				break;
			case Main.AFFICHE_OBJET:
				final ResaObjet obj = (ResaObjet) session().objectForKey(EDTObjetMenu.OBJET_SELECTED);
				if (obj != null) {
					nom = " l'objet " + obj.roLibelle1();
				}
				else {
					nom = "";
				}
				break;
			case Main.AFFICHE_SALLES:
				final Salles salleSelected = (Salles) session().objectForKey(EDTSalleMenu.SALLE_SELECTED);
				if (salleSelected != null) {
					nom = " la salle" + salleSelected.salPorte() + ", etage " + salleSelected.salEtage() + ", du batiment " + salleSelected.batNom();
				}
				else {
					nom = "";
				}
				break;
			case Main.AFFICHE_REUNION:
			case Main.AFFICHE_EXAMEN:
			case Main.AFFICHE_DIPLOME:
		}

		return nom;
	}
}
