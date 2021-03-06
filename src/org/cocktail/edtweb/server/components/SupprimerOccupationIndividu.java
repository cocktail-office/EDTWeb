package org.cocktail.edtweb.server.components;
// Generated by the WOLips Core at Wed Nov 03 14:35:21 CET 2004

import org.cocktail.edtweb.server.Application;
import org.cocktail.edtweb.server.EDTObject;
import org.cocktail.edtweb.server.Session;
import org.cocktail.edtweb.server.metier.Reservation;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;

import fr.univlr.cri.webapp.CRIWebPage;

public class SupprimerOccupationIndividu extends CRIWebPage {
	private static final long serialVersionUID = -4545113687315274362L;
	public static String FORMATER = "%d/%m/%Y %H:%M";
	protected EDTObject aResaDate;
	protected boolean suppressionOK;
	protected boolean voirDemande;
	public String erreurEnregistrement;
	private static final String OBJET_RESA = "ResaDate";

	public SupprimerOccupationIndividu(final WOContext context) {
		super(context);
		voirDemande = true;
		aResaDate = new EDTObject(criApp.dataBus());
		if (!(session().objectForKey(OBJET_RESA)).getClass().getName().equals("EDTObject")) {
			aResaDate.initObjet(session().objectForKey(OBJET_RESA), EDTObject.TYPE_PERIODICITE);
		}
		else {
			aResaDate = (EDTObject) session().objectForKey(OBJET_RESA);
		}
	}

	public EOEditingContext localEContext() {
		return session().defaultEditingContext();
	}

	public AffichageInfoReservation supprimerResa() {
		voirDemande = false;

		session().setObjectForKey(new Boolean(true), "refresh");

		Reservation laResa = (Reservation) criApp.dataBus().fetchObject(localEContext(), Reservation.ENTITY_NAME,
				EOQualifier.qualifierWithQualifierFormat(Reservation.RESA_KEY_KEY + " = %@", new NSArray(aResaDate.resaKey())));
		if (laResa == null) {
			setSuppressionOK(false);
			erreurEnregistrement = "Suppression Erreur: cette réservation n'existe pas !";
			return null;
		}

		if ((laResa.reservationObjets() != null && laResa.reservationObjets().count() > 0)
				|| (laResa.tosResaExam() != null && laResa.tosResaExam().count() > 0)
				|| (laResa.tosResaSalles() != null && laResa.tosResaSalles().count() > 0)
				|| (laResa.tosReservationAp() != null && laResa.tosReservationAp().count() > 0)) {
			setSuppressionOK(false);
			erreurEnregistrement = "Suppression Erreur: Ce n'est pas un simple agenda personnel.";
			return null;
		}

		if (!laResa.tlocCode().equals("r") && !laResa.tlocCode().equals("s") && !laResa.tlocCode().equals("p")) {
			setSuppressionOK(false);
			erreurEnregistrement = "Suppression Erreur: Ce n'est pas une réservation de type réunion.";
			return null;
		}

		if (laResa.periodicites() != null && laResa.periodicites().count() > 1) {
			setSuppressionOK(false);
			erreurEnregistrement = "Suppression Erreur: Cette réservation concerne plusieurs périodes!";
			return null;
		}

		try {
			laResa.deleteAllPeriodicitesRelationships();
			laResa.deleteAllTosOccupantRelationships();

			localEContext().deleteObject(laResa);

			localEContext().lock();
			try {
				localEContext().saveChanges();
			}
			catch (Exception exe) {
				exe.printStackTrace();
				localEContext().revert();
				throw exe;
			}
			finally {
				localEContext().unlock();
			}

			setSuppressionOK(true);
			erreurEnregistrement = null;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			setSuppressionOK(false);
			erreurEnregistrement = "Suppression Erreur: La suppression a echouée!";
		}

		return null;
	}

	public String fond() {
		return "background:url(" + ((Application) application()).srcfondULR() + ") ; background-position: -120px -100px;";
	}

	public boolean voirDemande() {
		return voirDemande;
	}

	public boolean suppressionOK() {
		return suppressionOK;
	}

	public void setSuppressionOK(final boolean newSuppressionOK) {
		suppressionOK = newSuppressionOK;
	}

	public WOComponent retour() {
		voirDemande = false;
		setSuppressionOK(false);
		return null;
	}

	public String reload() {
		return null;
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