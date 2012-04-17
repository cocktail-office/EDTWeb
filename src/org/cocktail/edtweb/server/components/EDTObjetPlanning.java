package org.cocktail.edtweb.server.components;
// Generated by the WOLips Core at Thu Jul 22 15:56:33 CEST 2004

import org.cocktail.edtweb.server.AffichePlanningResponder;
import org.cocktail.edtweb.server.EDTObject;
import org.cocktail.edtweb.server.Heure;
import org.cocktail.edtweb.server.Session;
import org.cocktail.edtweb.server.Utils;
import org.cocktail.edtweb.server.metier.IndividuUlr;
import org.cocktail.edtweb.server.metier.Periodicite;
import org.cocktail.edtweb.server.metier.RepartStructure;
import org.cocktail.edtweb.server.metier.ResaObjet;
import org.cocktail.edtweb.server.metier.ResaObjetDepositaire;
import org.cocktail.edtweb.server.metier.Reservation;
import org.cocktail.edtweb.server.metier.VReservationObjet;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOOrQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSRange;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSTimestampFormatter;

import fr.univlr.cri.util.StringCtrl;
import fr.univlr.cri.util.wo5.DateCtrl;
import fr.univlr.cri.webapp.CRIWebComponent;

public class EDTObjetPlanning extends CRIWebComponent {

	private static final long serialVersionUID = 1381556374657482890L;
	private static String OBJET_RESA = "ResaDate";
	public NSTimestamp dateDebut;
	public String heureDebut;
	public String heureFin;
	public Responder responder;

	private NSMutableArray listeResa;
	private EOQualifier theQualifier;
	public EOEnterpriseObject aDepositaire;

	public boolean afficheToutLesDepositaires;

	public EDTObjetPlanning(final WOContext context) {
		super(context);
		if (affichePlanning()) {
			initEDTObjetPlanning();
		}
	}

	private EOEditingContext localEContext() {
		return session().defaultEditingContext();
	}

	public boolean showPlanning() {
		return ((Boolean) session().objectForKey(EDTIndividu.SHOW_PLANNING)).booleanValue();
	}

	public void setShowPlanning(final boolean newShowPlanning) {
		session().setObjectForKey(new Boolean(newShowPlanning), EDTIndividu.SHOW_PLANNING);
	}

	public void appendToResponse(final WOResponse arg0, final WOContext arg1) {
		if (session().objectForKey("refresh") != null && ((Boolean) session().objectForKey("refresh")).booleanValue()) {
			session().setObjectForKey(new Boolean(false), "refresh");
			session().setObjectForKey(new Boolean(true), "refreshComposant");
			if (affichePlanning()) {
				initEDTObjetPlanning();
			}
		}
		super.appendToResponse(arg0, arg1);
	}

	public void initEDTObjetPlanning() {
		setShowPlanning(true);
		afficheToutLesDepositaires = false;
		listeResa = new NSMutableArray();
		responder = new Responder();
		final NSMutableArray liste = new NSMutableArray();
		liste.addObject(EOUtilities.primaryKeyForObject(localEContext(), resaObjet()).objectForKey(ResaObjet.RO_KEY_KEY));
		session().setObjectForKey(liste, AffichePlanning.LISTE_OBJET);
		session().setObjectForKey(responder, AffichePlanning.RESPONDER);
		// setShowPlanning(true);
	}

	public boolean affichePlanning() {
		return (session().objectForKey(EDTObjetMenu.SHOW_PLANNING) != null && ((Boolean) session().objectForKey(EDTObjetMenu.SHOW_PLANNING))
				.booleanValue());
	}

	public ResaObjet resaObjet() {
		return (ResaObjet) session().objectForKey(EDTObjetMenu.OBJET_SELECTED);
	}

	public NSArray listeDepositaireTrie() {
		final NSMutableArray sort = new NSMutableArray();
		sort.addObject(EOSortOrdering.sortOrderingWithKey("nomUsuel", EOSortOrdering.CompareAscending));
		sort.addObject(EOSortOrdering.sortOrderingWithKey("prenom", EOSortOrdering.CompareAscending));
		// NSArray depositaires = (NSArray) ((NSArray) ((NSArray) resaObjet().resaObjetDepositaires().valueForKey(
		// ResaObjetDepositaire.REPART_STRUCTURES_KEY)).valueForKey(RepartStructure.PERSONNE_KEY)).valueForKey(Personne.INDIVIDU_ULR_KEY);

		NSMutableArray depositaires = new NSMutableArray();
		for (int i = 0; i < resaObjet().resaObjetDepositaires().count(); i++) {
			NSArray repartStructures = ((ResaObjetDepositaire) resaObjet().resaObjetDepositaires().objectAtIndex(i)).repartStructures();
			for (int j = 0; j < repartStructures.count(); j++) {
				RepartStructure rs = (RepartStructure) repartStructures.objectAtIndex(j);
				depositaires.addObject(rs.personne().individuUlr());
			}
		}
		if (afficheToutLesDepositaires || depositaires.count() < 2) {
			return EOSortOrdering.sortedArrayUsingKeyOrderArray(depositaires, sort);
		}
		return EOSortOrdering.sortedArrayUsingKeyOrderArray(depositaires, sort).subarrayWithRange(new NSRange(0, 1));
	}

	public WOComponent afficherToutLesDestinataires() {
		afficheToutLesDepositaires = true;
		return null;
	}

	public String affichageDepositaire() {
		return aDepositaire.valueForKey("nomUsuel") + " " + aDepositaire.valueForKey("prenom");
	}

	public boolean plusieurDestinataire() {
		return resaObjet().resaObjetDepositaires().count() > 1;
	}

	public boolean aPasDroitDeReserverObjet() {
		IndividuUlr ind = IndividuUlr.fetchRequiredIndividuUlr(localEContext(), IndividuUlr.NO_INDIVIDU_KEY, criSession().connectedUserInfo()
				.noIndividu());
		return !VerificationFactory.testIndividuADroitReserverObjet(localEContext(), ind, resaObjet());
		// final NSArray liste = (NSArray) resaObjet().resaObjetReserves().valueForKey(ResaObjetReserve.C_STRUCTURE_KEY);
		// final Number persId = criSession().connectedUserInfo().persId();
		// final NSMutableArray args2 = new NSMutableArray();
		// for (int i = 0; i < liste.count(); i++) {
		// args2.addObject(EOQualifier.qualifierWithQualifierFormat(RepartStructure.C_STRUCTURE_KEY + " = %@", new
		// NSArray(liste.objectAtIndex(i))));
		// }
		// final NSMutableArray args = new NSMutableArray();
		// args.addObject(EOQualifier.qualifierWithQualifierFormat(RepartStructure.PERS_ID_KEY + " = %@", new NSArray(persId)));
		// args.addObject(new EOOrQualifier(args2));
		// return (criApp.dataBus().fetchArray(localEContext(), RepartStructure.ENTITY_NAME, new EOAndQualifier(args), null)).count() == 0;
	}

	class Responder implements AffichePlanningResponder {

		public String nbObjetDispoForDayAndTime(final Heure aDay) {
			String affichage = "";
			for (int i = 0; i < listeResa.count(); i++) {
				final EDTObject resaDate = (EDTObject) listeResa.objectAtIndex(i);
				final NSTimestamp debut = resaDate.dateDebut();// new
				final NSTimestamp fin = resaDate.dateFin();// new
				int oldNoResa = 0;

				if (debut != null && fin != null && debut.before(aDay.fin()) && fin.after(aDay.debut())) {
					session().setObjectForKey(resaDate, OBJET_RESA);
					final Number resaOrdre = resaDate.key();
					//
					if (resaOrdre.intValue() != oldNoResa) {
						affichage += (oldNoResa == 0 ? "" : "<br>") + "<a onMouseOver=\"return overlib('" + formatString(title2()) + "', CAPTION, '"
								+ formatString(title1()) + "');\" onMouseOut=\"return nd();\"> -&nbsp;" + motifReservation(10) + "</a>";
						oldNoResa = resaOrdre.intValue();
					}
					return affichage;
				}
			}
			return affichage;
		}

		public int initPlanning(final String typeCode) {
			return 0;
		}

		public int initPlanning(final NSMutableArray listeObjetCle) {
			final NSMutableArray args = new NSMutableArray();
			args.addObject(EOQualifier.qualifierWithQualifierFormat(Periodicite.V_RESERVATION_OBJET_KEY + "." + VReservationObjet.RESA_OBJET_KEY
					+ "." + ResaObjet.RO_KEY_KEY + " = %@", listeObjetCle));
			final NSMutableArray etat = new NSMutableArray();
			etat.addObject(EOQualifier.qualifierWithQualifierFormat(Periodicite.V_RESERVATION_OBJET_KEY + "." + VReservationObjet.RESA_ETAT_KEY
					+ " = %@", new NSArray("R")));
			etat.addObject(EOQualifier.qualifierWithQualifierFormat(Periodicite.V_RESERVATION_OBJET_KEY + "." + VReservationObjet.RESA_ETAT_KEY
					+ " = %@", new NSArray("P")));
			args.addObject(new EOOrQualifier(etat));
			theQualifier = new EOAndQualifier(args);
			return 0;
		}

		public void initListeResa(final NSTimestamp debut, final NSTimestamp fin) {
			final NSMutableArray args = new NSMutableArray();
			final NSMutableArray args1 = new NSMutableArray();
			args1.addObject(EOQualifier.qualifierWithQualifierFormat(Periodicite.DATE_DEB_KEY + " <= %@", new NSArray(fin)));
			args1.addObject(EOQualifier.qualifierWithQualifierFormat(Periodicite.DATE_FIN_KEY + " >= %@", new NSArray(debut)));
			args.addObject(new EOAndQualifier(args1));
			if (theQualifier != null) {
				args.addObject(theQualifier);
			}
			NSMutableArray sort = new NSMutableArray();
			sort.addObject(EOSortOrdering.sortOrderingWithKey(Periodicite.DATE_DEB_KEY, EOSortOrdering.CompareAscending));
			sort.addObject(EOSortOrdering.sortOrderingWithKey(Periodicite.DATE_FIN_KEY, EOSortOrdering.CompareAscending));
			final NSArray tmp = criApp.dataBus().fetchArray(localEContext(), Periodicite.ENTITY_NAME, new EOAndQualifier(args), sort);
			for (int i = 0; i < tmp.count(); i++) {
				final Periodicite objet = (Periodicite) tmp.objectAtIndex(i);
				final EDTObject obj = new EDTObject(criApp.dataBus());
				obj.initObjet(objet, EDTObject.TYPE_PERIODICITE);
				listeResa.addObject(obj);
			}
		}

		public String couleurTR() {
			final Number resaOrdre = ((EDTObject) session().objectForKey(OBJET_RESA)).key();
			if (((Session) session()).getCouleurResa().objectForKey(resaOrdre) == null) {
				for (int i = 0; i < Math.random() * 10; i++) {
					Math.random();
				}
				final String Rouge = Integer.toHexString((new Double(Math.random() * 50)).intValue() + 200);
				final String Vert = Integer.toHexString((new Double(Math.random() * 40)).intValue() + 200);
				final String Bleu = Integer.toHexString((new Double(Math.random() * 40)).intValue() + 200);
				((Session) session()).getCouleurResa().setObjectForKey("#" + Rouge + Vert + Bleu, resaOrdre);
			}
			return (String) ((Session) session()).getCouleurResa().objectForKey(resaOrdre);
		}

		public WOComponent goReservation() {
			setShowPlanning(false);
			session().setObjectForKey(new Boolean(true), "refreshComposant");
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see AffichePlanningResponder#title1()
		 */
		public String title1() {
			if (OBJET_RESA != null && session().objectForKey(OBJET_RESA) != null && session().objectForKey(OBJET_RESA) != NullValue) {
				final EDTObject resaDate = (EDTObject) session().objectForKey(OBJET_RESA);
				final NSTimestampFormatter formatJour = new NSTimestampFormatter("%d/%m/%Y  à  %H : %M");
				if (resaDate != null) {
					return "Du " + DateCtrl.dateToString(resaDate.dateDebut(), formatJour.pattern()) + " <BR>Au "
							+ DateCtrl.dateToString(resaDate.dateFin(), formatJour.pattern());
				}
			}
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see AffichePlanningResponder#title2()
		 */
		public String title2() {
			if (OBJET_RESA != null && session().objectForKey(OBJET_RESA) != null && session().objectForKey(OBJET_RESA) != NullValue) {
				final EDTObject resaDate = (EDTObject) session().objectForKey(OBJET_RESA);
				if (resaDate != null) {
					return resaDate.sujetMini();
				}
			}
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see AffichePlanningResponder#getObjetForIndex(int)
		 */
		public EDTObject getObjetForIndex(final int index) {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see AffichePlanningResponder#getListeResaObjet()
		 */
		public NSArray getListeResaObjet(final NSTimestamp debut, final NSTimestamp fin) {
			listeResa.removeAllObjects();
			initListeResa(debut, fin);
			return listeResa;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see AffichePlanningResponder#motifReservation(int)
		 */
		public String motifReservation(final int maxCar) {
			boolean isReserver = true;
			final EDTObject resaDate = (EDTObject) session().objectForKey(OBJET_RESA);
			String affichage = ((Reservation) resaDate.valueForKey(Periodicite.RESERVATION_KEY)).resaCommentaire();
			isReserver = (((VReservationObjet) resaDate.valueForKey(Periodicite.V_RESERVATION_OBJET_KEY)).resaEtat()).equals("R");
			if (affichage != null && affichage.length() > maxCar) {
				affichage = Utils.cleanCut(affichage, maxCar - 3) + "...";
			}
			if (!"".equals(affichage)) {
				affichage += (isReserver ? "<br>   (R&eacute;server)" : "<br>   (Pr&eacute;r&eacute;server)");
			}
			return affichage;
		}
	}

	public String voirInfo(final String resaOrdre) {
		final NSMutableDictionary dico = new NSMutableDictionary();
		dico.setObjectForKey(resaOrdre, "objet");
		final String url = context().directActionURLForActionNamed("infoForResa", dico);
		final String info = "javascript: messageWindow('" + url + "','Informations','300','300');";
		return info;
	}

	private String formatString(String str) {
		if (str == null) {
			return "";
		}
		// remplace les " par \"
		str = StringCtrl.replace(str, "\"", "&quot;");
		// remplace les ' par \'
		str = StringCtrl.replace(str, "'", "\\'");
		// remplace le retour chariot
		str = str.replaceAll("\n", "");
		str = StringCtrl.replace(str, "\r", "<BR>");
		return str;
	}

}