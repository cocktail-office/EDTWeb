package org.cocktail.edtweb.server.components;
import org.cocktail.edtweb.server.Application;
import org.cocktail.edtweb.server.EDTObject;
import org.cocktail.edtweb.server.Session;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

import fr.univlr.cri.webapp.CRIWebPage;

public class AffichageInfoReservation extends CRIWebPage {

	private static final long serialVersionUID = -1570366338453551698L;

	// /** @TypeInfo ResaDate */
	// /** @TypeInfo Periodicite */
	public EDTObject aResaDate;

	/** @TypeInfo Occupant */
	public EOEnterpriseObject anOccupant;

	/** @TypeInfo ResaSalles */
	public EOEnterpriseObject uneSalle;

	/** @TypeInfo ResaExamen */
	public EOEnterpriseObject aResaExam;

	public AffichageInfoReservation(final WOContext context) {
		super(context);
		aResaDate = new EDTObject(criApp.dataBus());
		if (!(session().objectForKey(EDTIndividu.OBJET_RESA)).getClass().getName().equals("EDTObject")) {
			switch (((Session) session()).getAffichageEnCours()) {
				case Main.AFFICHE_INDIVIDU_PUBLIC:
				case Main.AFFICHE_INDIVIDU:
				case Main.AFFICHE_OBJET:
				case Main.AFFICHE_EXAMEN:
				case Main.AFFICHE_SALLES:
					aResaDate.initObjet(session().objectForKey(EDTIndividu.OBJET_RESA), EDTObject.TYPE_PERIODICITE);
					break;
			}
		}
		else {
			aResaDate = (EDTObject) session().objectForKey(EDTIndividu.OBJET_RESA);
		}
	}

	public String fond() {
		return "background:url(" + ((Application) application()).srcfondULR() + ") ; background-position: -120px -100px;";
	}

	public String nomEtPrenom() {
		return aResaDate.nomEtPrenom();
	}

	public String sujet() {
		return aResaDate.sujet();
	}

	public NSTimestamp dateDebut() {
		return aResaDate.dateDebut();
	}

	public NSTimestamp dateFin() {
		return aResaDate.dateFin();
	}

	public boolean affichageExam() {
		return ((Session) session()).getAffichageEnCours() == Main.AFFICHE_EXAMEN;
	}

	public boolean isModifiable() {
		return aResaDate != null && (aResaDate.isPeriodicite());
	}

	public boolean isSupprimable() {
		return aResaDate != null && (aResaDate.isPeriodicite());
	}

	public NSArray listeResaExam() {
		if (!affichageExam()) {
			return null;
		}
		return (NSArray) ((EOGenericRecord) aResaDate.valueForKey("reservation")).valueForKey("tosResaExam");
	}

	public WOComponent modiferResa() {
		return pageWithName("ModifierOccupationIndividu");
	}

	public WOComponent supprimerResa() {
		return pageWithName("SupprimerOccupationIndividu");
	}

	public boolean isIndividu() {
		return ((((Session) session()).getAffichageEnCours() == Main.AFFICHE_INDIVIDU) || (((Session) session()).getAffichageEnCours() == Main.AFFICHE_INDIVIDU_CHOISI));
	}

	public WOComponent fermerLaFenetre() {
		return ((Session) session()).getMainPage();
	}

	/**
	 * Forcer le refresh de la page appelante en javascript. Le code a executer depend fortement du navigateur !
	 */
	public String btnFermerOnClick() {
		StringBuffer code = new StringBuffer();
		code.append("window.setTimeout('window.close()',200);return false;");
		code.insert(0, "FormFermerLaFenetre.submit();");
		return code.toString();
	}

	public String btnFermerEntOnClick() {
		StringBuffer code = new StringBuffer();
		code.append("window.setTimeout('',200);return false;");
		code.insert(0, "FormFermerLaFenetreEnt.submit();");
		return code.toString();
	}
}