package org.cocktail.edtweb.server.components;
//
// AjoutResa.java: Class file for WO Component 'AjoutResa'
// Project EDTLudo
//
// Created by ludo on 26/08/05
//

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.cocktail.edtweb.server.AffichePlanningResponder;
import org.cocktail.edtweb.server.Jour;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSMutableArray;

public class AjoutResa extends WOComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2924531561796365167L;
	/** @TypeInfo java.lang.String */
	protected NSMutableArray jourSem;
	protected String jo;

	/** @TypeInfo java.lang.String */
	protected NSMutableArray mois;
	protected String mo;

	/** @TypeInfo java.lang.String */
	protected NSMutableArray annee;
	protected String an;

	protected String jof;
	protected String mof;
	protected String anf;
	protected boolean entiere;
	protected boolean periode;
	protected String dateInvalide;
	protected boolean valide;
	protected AffichePlanningResponder responder;

	/** @TypeInfo java.lang.String */
	protected NSMutableArray heure;

	/** @TypeInfo java.lang.String */
	protected NSMutableArray min;
	protected String md;
	protected String mf;
	protected String hd;
	protected String hf;
	protected boolean boo;
	// public Jour jourd,jourf;

	protected boolean choixGraph;
	public boolean afficheInstruct;

	public AjoutResa(final WOContext context) {
		super(context);
		responder = (AffichePlanningResponder) session().objectForKey("responder");
		valide = false;
		choixGraph = false;
		afficheInstruct = false;
		myInit();
	}

	public NSMutableArray initCombo(final int deb, final int fin) {
		int i;
		Integer j;
		String str;
		NSMutableArray tab;

		tab = new NSMutableArray(fin - deb + 1);
		for (i = deb; i < fin + 1; i++) {
			j = new Integer(i);
			if (i < 10) {
				str = new String("0");
			}
			else {
				str = new String("");
			}
			str = new String(str + j.toString());
			tab.addObject(str);
		}
		return tab;
	}

	public String chifToNumber(final int day) {// ajouter
		String str = "";
		if (day < 10) {
			str = "0" + (new Integer(day)).toString();
		}
		else {
			str = (new Integer(day)).toString();
		}
		return str;
	}

	public void myInit() {
		final GregorianCalendar today = new GregorianCalendar();
		jof = jo = String.valueOf(chifToNumber(today.get(Calendar.DAY_OF_MONTH)));
		mof = mo = String.valueOf(chifToNumber(today.get(Calendar.MONTH) + 1));
		anf = an = String.valueOf(today.get(Calendar.YEAR));
		md = mf = "00";
		hd = hf = "08";
		entiere = periode = false;
		jourSem = initCombo(1, 31);
		mois = initCombo(1, 12);
		annee = initCombo(today.get(Calendar.YEAR) - 1, today.get(Calendar.YEAR) + 2);
		heure = initCombo(0, 23);
		min = initCombo(0, 59);
		responder = (AffichePlanningResponder) session().objectForKey("responder");
	}

	public void appendToResponse(final WOResponse arg0, final WOContext arg1) {
		// myInit();
		// choixGraph = true;
		if (choixGraph) {
			synchronizedSelection();
		}
		else {
			;// choix par calendrier
		}
		super.appendToResponse(arg0, arg1);
	}

	private void synchronizedSelection() {
		Jour jourDebut = null;
		Jour jourFin = null;

		if (session().objectForKey(AffichePlanning.JOUR_DEBUT) != NullValue) {
			jourDebut = (Jour) session().objectForKey(AffichePlanning.JOUR_DEBUT);
		}
		if (session().objectForKey(AffichePlanning.JOUR_FIN) != NullValue) {
			jourFin = (Jour) session().objectForKey(AffichePlanning.JOUR_FIN);
		}
		if (jourDebut != null) {
			setJo(chifToNumber(jourDebut.get(Calendar.DAY_OF_MONTH)));
			setMo(chifToNumber(jourDebut.get(Calendar.MONTH) + 1));
			setAn(chifToNumber(jourDebut.get(Calendar.YEAR)));
			setHd(chifToNumber(jourDebut.get(Calendar.HOUR_OF_DAY)));
			setMd(chifToNumber(jourDebut.get(Calendar.MINUTE)));
		}
		if (jourFin != null) {
			setJof(chifToNumber(jourFin.get(Calendar.DAY_OF_MONTH)));
			setMof(chifToNumber(jourFin.get(Calendar.MONTH) + 1));
			setAnf(chifToNumber(jourFin.get(Calendar.YEAR)));
			setHf(chifToNumber(jourFin.get(Calendar.HOUR_OF_DAY)));
			setMf(chifToNumber(jourFin.get(Calendar.MINUTE)));
		}
	}

	public WOComponent ValiderForm() {
		// pdm hmmm...
		// if (selectionPlusieurJour()) {
		// valide = true;
		// dateInvalide = plusieurjourInterdire();
		// return null;
		// }

		responder = (AffichePlanningResponder) session().objectForKey("responder");// ajouter

		Jour jourd, jourf;
		// **Test si le jour de debut existe par rapport au calendrier
		if ((Integer.parseInt(jo) == 31)
				&& ((Integer.parseInt(mo) == 2) || (Integer.parseInt(mo) == 4) || (Integer.parseInt(mo) == 6) || (Integer.parseInt(mo) == 9) || (Integer
						.parseInt(mo) == 11))) {
			valide = true;
			dateInvalide = "Date de debut invalide";
			return null;
		}

		if ((Integer.parseInt(jo) == 30) && (Integer.parseInt(mo) == 2)) {
			valide = true;
			dateInvalide = "Date de debut invalide";
			return null;
		}

		// ***Test pour savoir si le jour de fin existe
		if ((Integer.parseInt(jof) == 31)
				&& ((Integer.parseInt(mof) == 2) || (Integer.parseInt(mof) == 4) || (Integer.parseInt(mof) == 6) || (Integer.parseInt(mof) == 9) || (Integer
						.parseInt(mof) == 11))) {
			valide = true;
			dateInvalide = "Date de fin invalide";
			return null;
		}

		if ((Integer.parseInt(jof) == 30) && (Integer.parseInt(mof) == 2)) {
			valide = true;
			dateInvalide = "Date de fin invalide";
			return null;
		}

		if (entiere == true) {
			// ***regarde si jour debut et jour fin sont bien les memes
			if (!jof.equals(jo) || !mof.equals(mo) || !anf.equals(an)) {
				valide = true;
				dateInvalide = "Si vous selectionnez journee entiere vous devez avoir la meme date en debut et en fin";
				return null;
			}
			jourd = new Jour(new GregorianCalendar(new Integer(an()).intValue(), new Integer(mo()).intValue() - 1, new Integer(jo()).intValue()));
			// pdm pas de ça, il faut laisser le tz par défaut défini dans le
			// fichier de config !
			// jourd.setTimeZone(TimeZone.getTimeZone("GMT"));
			jourd.set(Calendar.HOUR_OF_DAY, 0);
			jourd.set(Calendar.MINUTE, 0);

			jourf = new Jour(new GregorianCalendar(new Integer(anf()).intValue(), new Integer(mof()).intValue() - 1, new Integer(jof()).intValue()));
			// pdm pas de ça, il faut laisser le tz par défaut défini dans le
			// fichier de config !
			// jourf.setTimeZone(TimeZone.getTimeZone("GMT"));
			jourf.set(Calendar.HOUR_OF_DAY, 23);
			jourf.set(Calendar.MINUTE, 59);
		}
		else {
			jourd = new Jour(new GregorianCalendar(new Integer(an()).intValue(), new Integer(mo()).intValue() - 1, new Integer(jo()).intValue()));
			// pdm pas de ça, il faut laisser le tz par défaut défini dans le
			// fichier de config !
			// jourd.setTimeZone(TimeZone.getTimeZone("GMT"));
			jourd.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hd));
			jourd.set(Calendar.MINUTE, Integer.parseInt(md));

			jourf = new Jour(new GregorianCalendar(new Integer(anf()).intValue(), new Integer(mof()).intValue() - 1, new Integer(jof()).intValue()));
			// pdm pas de ça, il faut laisser le tz par défaut défini dans le
			// fichier de config !
			// jourf.setTimeZone(TimeZone.getTimeZone("GMT"));
			jourf.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hf));
			jourf.set(Calendar.MINUTE, Integer.parseInt(mf));
		}

		// ****Teste la date du 29 fevrier autrement dit l annee bisextile
		if ((Integer.parseInt(mo) == 2) && (Integer.parseInt(jo) >= 29) && !(jourd.isLeapYear(Integer.parseInt(an)))) {
			valide = true;
			dateInvalide = "Date de debut invalide";
			return null;
		}

		if ((Integer.parseInt(mof) == 2) && (Integer.parseInt(jof) >= 29) && !(jourd.isLeapYear(Integer.parseInt(anf)))) {// modifier
			valide = true;
			dateInvalide = "Date de fin invalide";
			return null;
		}

		// **Test pour savoir si le debut est bien place avant la fin dans le
		// temps

		if (jourd.getTimeInMillis() > jourf.getTimeInMillis()) {
			valide = true;
			dateInvalide = "Le debut doit se trouver avant la fin";
			return null;
		}

		session().setObjectForKey(jourd, AffichePlanning.JOUR_DEBUT);
		session().setObjectForKey(jourf, AffichePlanning.JOUR_FIN);
		session().setObjectForKey(new Boolean(false), "testludo");
		valide = false;

		if (responder == null) {
			return null;
		}

		afficheInstruct = false;

		return responder.goReservation();
	}

	/** @TypeInfo java.lang.String */
	public NSMutableArray jourSem() {
		return jourSem;
	}

	public String jo() {
		return jo;
	}

	public void setJo(final String newJo) {
		jo = newJo;
	}

	/** @TypeInfo java.lang.String */
	public NSMutableArray mois() {
		return mois;
	}

	public String mo() {
		return mo;
	}

	public void setMo(final String newMo) {
		mo = newMo;
	}

	/** @TypeInfo java.lang.String */
	public NSMutableArray annee() {
		return annee;
	}

	public String an() {
		return an;
	}

	public void setAn(final String newAn) {
		an = newAn;
	}

	public String jof() {
		return jof;
	}

	public void setJof(final String newJof) {
		jof = newJof;
	}

	public String mof() {
		return mof;
	}

	public void setMof(final String newMof) {
		mof = newMof;
	}

	public String anf() {
		return anf;
	}

	public void setAnf(final String newAnf) {
		anf = newAnf;
	}

	public boolean entiere() {
		return entiere;
	}

	public void setEntiere(final boolean newEntiere) {
		entiere = newEntiere;
	}

	public boolean periode() {
		return periode;
	}

	public void setPeriode(final boolean newPeriode) {
		periode = newPeriode;
	}

	public String dateInvalide() {
		return dateInvalide;
	}

	public boolean valide() {
		return valide;
	}

	/** @TypeInfo java.lang.String */
	public NSMutableArray heure() {
		return heure;
	}

	/** @TypeInfo java.lang.String */
	public NSMutableArray min() {
		return min;
	}

	public String md() {
		return md;
	}

	public void setMd(final String newMd) {
		md = newMd;
	}

	public String mf() {
		return mf;
	}

	public void setMf(final String newMf) {
		mf = newMf;
	}

	public String hd() {
		return hd;
	}

	public void setHd(final String newHd) {
		hd = newHd;
	}

	public String hf() {
		return hf;
	}

	public void setHf(final String newHf) {
		hf = newHf;
	}

	public boolean boo() {
		boo = ((Boolean) session().objectForKey("Form")).booleanValue();
		return boo;
	}

	public WOComponent showForm() {
		session().setObjectForKey(new Boolean(false), "Form");
		return null;
	}

	public WOComponent voirGraphique()// ajouter
	{
		Jour jdebut, jfin;
		jdebut = new Jour(new GregorianCalendar(new Integer(an()).intValue(), new Integer(mo()).intValue() - 1, new Integer(jo()).intValue()));
		// pdm pas de ça, il faut laisser le tz par défaut défini dans le
		// fichier de config !
		// jdebut.setTimeZone(TimeZone.getTimeZone("GMT"));
		jdebut.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hd));
		jdebut.set(Calendar.MINUTE, Integer.parseInt(md));

		jfin = new Jour(new GregorianCalendar(new Integer(anf()).intValue(), new Integer(mof()).intValue() - 1, new Integer(jof()).intValue()));
		// pdm pas de ça, il faut laisser le tz par défaut défini dans le
		// fichier de config !
		// jfin.setTimeZone(TimeZone.getTimeZone("GMT"));
		jfin.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hf));
		jfin.set(Calendar.MINUTE, Integer.parseInt(mf));

		session().setObjectForKey(jdebut, AffichePlanning.JOUR_DEBUT);
		session().setObjectForKey(jfin, AffichePlanning.JOUR_FIN);
		session().setObjectForKey(new Boolean(true), "voirGraphique");// supposeer
		// que
		// si
		// refreshComposant=false:
		// dans
		// l'affiche
		// planning,
		// ne
		// pas
		// effacer
		// jourBebut
		// et
		// jourFiin
		session().setObjectForKey(jdebut.toNSTimestamp(), "dateDebut");

		return null;
	}

	public WOComponent annuler() {
		final Calendar today = new GregorianCalendar();
		jof = jo = chifToNumber(today.get(Calendar.DAY_OF_MONTH));
		mof = mo = chifToNumber(today.get(Calendar.MONTH) + 1);
		anf = an = chifToNumber(today.get(Calendar.YEAR));
		md = mf = "00";
		hd = hf = "08";
		entiere = periode = false;

		session().setObjectForKey(NullValue, AffichePlanning.JOUR_DEBUT);
		session().setObjectForKey(NullValue, AffichePlanning.JOUR_FIN);
		session().setObjectForKey(new Boolean(false), "voirGraphique");// supposeer
		// que
		// si
		// refreshComposant=false:
		// dans
		// l'affiche
		// planning,
		// ne
		// pas
		// effacer
		// jourBebut
		// et
		// jourFiin
		session().setObjectForKey(new Boolean(false), "CHOIX_PAR_GRAPH");
		afficheInstruct = false;

		valide = false;

		return null;
	}

	public boolean choixGraph() {
		return choixGraph;
	}

	public void setChoixGraph(final boolean newChoixGraph) {
		choixGraph = newChoixGraph;
	}

	public WOComponent modeSelection() {
		setChoixGraph(true);
		session().setObjectForKey(new Boolean(true), "CHOIX_PAR_GRAPH");
		afficheInstruct = true;
		return null;
	}

	public boolean selectionPlusieurJour() {
		if (session().objectForKey(AffichePlanning.JOUR_DEBUT) == null || session().objectForKey(AffichePlanning.JOUR_FIN) == null) {
			return false;
		}
		if (session().objectForKey(AffichePlanning.JOUR_DEBUT) == NullValue || session().objectForKey(AffichePlanning.JOUR_FIN) == NullValue) {
			return false;
		}
		return !(getJourDebut().get(Calendar.DAY_OF_YEAR) == getJourFin().get(Calendar.DAY_OF_YEAR) && getJourDebut().get(Calendar.YEAR) == getJourFin()
				.get(Calendar.YEAR));
	}

	public Jour getJourDebut() {
		return (Jour) session().objectForKey(AffichePlanning.JOUR_DEBUT);
	}

	public Jour getJourFin() {
		return (Jour) session().objectForKey(AffichePlanning.JOUR_FIN);
	}

	public String plusieurjourInterdire() {
		return "Selectionner une duree dans une jounee!";
	}
	// fin
}
