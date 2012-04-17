package org.cocktail.edtweb.server.components;
import java.util.Properties;

import org.cocktail.edtweb.server.AffichePlanningResponder;
import org.cocktail.edtweb.server.Application;
import org.cocktail.edtweb.server.EDTObject;
import org.cocktail.edtweb.server.Heure;
import org.cocktail.edtweb.server.Jour;
import org.cocktail.edtweb.server.Session;
import org.cocktail.edtweb.server.Utils;
import org.cocktail.edtweb.server.metier.Periodicite;
import org.cocktail.edtweb.server.metier.Reservation;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.eocontrol.EOOrQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSComparator;
import com.webobjects.foundation.NSComparator.ComparisonException;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSTimestampFormatter;

import fr.univlr.cri.util.StringCtrl;
import fr.univlr.cri.util.wo5.DateCtrl;
import fr.univlr.cri.webapp.CRIDataBus;
import fr.univlr.cri.webapp.CRIWebComponent;

public class EDTIndividu extends CRIWebComponent {
	private static final long serialVersionUID = 2295512094353938756L;
	public static final String OBJET_RESA = "ResaDate";
	public static final String SHOW_PLANNING = "voirLePlanning";
	public static String TYPE_PLANNING = "TypePlanning";

	public NSTimestamp dateDebut;
	public String heureDebut;
	public String heureFin;
	public Responder responder;

	protected NSMutableArray listeResa;

	private EOQualifier theQualifier2;
	private Number noIndividu;
	protected Jour jour;
	public boolean addButtonMiseAJour;

	public EDTIndividu(final WOContext context) {
		super(context);
		EDTIndividu.this.initEDTIndividu();
		addButtonMiseAJour = false;

	}

	protected EOEditingContext localEContext() {
		return session().defaultEditingContext();
	}

	public WOComponent refresh() {
		session().setObjectForKey(new Boolean(true), "refreshComposant");
		return null;
	}

	public boolean showPublicEtPrive() {
		return true;
	}

	public void initEDTIndividu() {
		listeResa = new NSMutableArray();
		responder = new Responder();
		dateDebut = new NSTimestamp();
		final NSMutableArray liste = new NSMutableArray();
		liste.addObject(criSession().connectedUserInfo().noIndividu());
		session().setObjectForKey(liste, AffichePlanning.LISTE_OBJET);
		session().setObjectForKey(responder, AffichePlanning.RESPONDER);
		setShowPlanning(true);
	}

	public void appendToResponse(final WOResponse arg0, final WOContext arg1) {
		if (session().objectForKey("refresh") != null && ((Boolean) session().objectForKey("refresh")).booleanValue()) {
			session().setObjectForKey(new Boolean(false), "refresh");
			session().setObjectForKey(new Boolean(true), "refreshComposant");
			session().setObjectForKey("AffichePlanning", TYPE_PLANNING);
			initEDTIndividu();
		}

		if (session().objectForKey("dateDebut") != null) {// ajouter
			jour = new Jour((NSTimestamp) session().objectForKey("dateDebut"));
		}
		else {
			jour = new Jour(dateDebut);
		}
		super.appendToResponse(arg0, arg1);
	}

	public boolean showPlanning() {
		return ((Boolean) session().objectForKey(SHOW_PLANNING)).booleanValue();
	}

	public void setShowPlanning(final boolean newShowPlanning) {
		session().setObjectForKey(new Boolean(newShowPlanning), SHOW_PLANNING);
	}

	public String planning() {
		return (String) session().objectForKey(TYPE_PLANNING);
	}

	public Jour jour() {
		return jour;
	}

	/**
	 * @param persId
	 * @param debut
	 * @param fin
	 * @return le tableau des EDTObject qui va bien...
	 */
	public static NSArray getPlanningEtudiant(CRIDataBus dataBus, Number persId, NSTimestamp debut, NSTimestamp fin) {
		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat("persId = %@", new NSArray(persId));
		EOFetchSpecification myFetch = new EOFetchSpecification("ScolInscriptionEtudiant", qual, null);
		myFetch.setPrefetchingRelationshipKeyPaths(new NSArray(new Object[] { "scolInscriptionAps", "scolInscriptionAps.scolMaquetteRepartitionAp",
				"scolInscriptionGrps" }));
		myFetch.setRefreshesRefetchedObjects(true);
		myFetch.setUsesDistinct(true);
		NSArray inscriptions = dataBus.editingContext().objectsWithFetchSpecification(myFetch);
		if (inscriptions == null || inscriptions.count() == 0) {
			return null;
		}

		EOQualifier datesQual = null;
		if (debut != null || fin != null) {
			NSMutableArray args = new NSMutableArray();
			if (debut != null) {
				args.addObject(EOQualifier.qualifierWithQualifierFormat("dateFin >= %@", new NSArray(debut)));
			}
			if (fin != null) {
				args.addObject(EOQualifier.qualifierWithQualifierFormat("dateDeb <= %@", new NSArray(fin)));
			}
			datesQual = new EOAndQualifier(args);
		}

		NSMutableArray sort = new NSMutableArray();
		sort.addObject(EOSortOrdering.sortOrderingWithKey("dateDeb", EOSortOrdering.CompareAscending));
		sort.addObject(EOSortOrdering.sortOrderingWithKey("dateFin", EOSortOrdering.CompareAscending));

		NSMutableArray tmp = new NSMutableArray();
		for (int i = 0; i < inscriptions.count(); i++) {
			EOGenericRecord uneInscription = ((EOGenericRecord) inscriptions.objectAtIndex(i));
			NSArray scolInscriptionAps = (NSArray) uneInscription.valueForKeyPath("scolInscriptionAps");
			if (scolInscriptionAps == null || scolInscriptionAps.count() == 0) {
				continue;
			}
			NSMutableArray orQuals = new NSMutableArray();
			for (int k = 0; k < scolInscriptionAps.count(); k++) {
				EOGenericRecord scolInscriptionAp = (EOGenericRecord) scolInscriptionAps.objectAtIndex(k);
				Number imrapDispense = (Number) scolInscriptionAp.valueForKey("imrapDispense");
				// on limite aux imrapDispense 0 (Inscrit), 2 (Réorientation - Arrivée), 10 (A la Carte - Non Comptabilisable), 11 (A la
				// Carte - Comptabilisable)
				if (imrapDispense != null
						&& (imrapDispense.intValue() == 0 || imrapDispense.intValue() == 2 || imrapDispense.intValue() == 10 || imrapDispense
								.intValue() == 11)) {
					Number mapKey = (Number) ((EOGenericRecord) scolInscriptionAp.valueForKey("scolMaquetteRepartitionAp")).valueForKey("mapKey");
					orQuals.addObject(EOQualifier.qualifierWithQualifierFormat("reservation.tosReservationAp.mapKey = %@", new NSArray(mapKey)));
				}
			}
			if (orQuals.count() == 0) {
				continue;
			}
			qual = new EOOrQualifier(orQuals);
			if (datesQual != null) {
				qual = new EOAndQualifier(new NSArray(new Object[] { qual, datesQual }));
			}

			myFetch = new EOFetchSpecification("Periodicite", qual, sort);
			myFetch.setPrefetchingRelationshipKeyPaths(new NSArray(new Object[] { "reservation", "reservation.tosReservationAp",
					"reservation.tosReservationAp.scolGroupeGrp", "reservation.tosResaSalles", "reservation.tosResaSalles.toSalles" }));
			myFetch.setRefreshesRefetchedObjects(true);
			myFetch.setUsesDistinct(true);
			NSArray resas = dataBus.editingContext().objectsWithFetchSpecification(myFetch);

			// si l'étudiant n'est affecté dans aucun groupe, on ne filtre pas...
			// pour récupérer ses groupes on récupère tous les groupes en tenant compte des inclusions...
			NSArray ggrpKeys = (NSArray) ((NSArray) uneInscription.valueForKeyPath("scolInscriptionGrps")).valueForKey("ggrpKey");
			ggrpKeys = groupesAvecInclusion(dataBus, ggrpKeys);
			if (ggrpKeys == null || ggrpKeys.count() == 0) {
				tmp.addObjectsFromArray(resas);
			}
			else {
				// sinon on n'ajoute que les résas qui correspondent soit à aucun groupe, soit à un groupe auquel appartient l'étudiant...
				for (int j = 0; j < resas.count(); j++) {
					EOGenericRecord periodicite = (EOGenericRecord) resas.objectAtIndex(j);
					NSArray tosReservationAp = (NSArray) ((EOGenericRecord) periodicite.valueForKey("reservation"))
							.valueForKeyPath("tosReservationAp");
					boolean shouldAdd = false;
					for (int k = 0; k < tosReservationAp.count(); k++) {
						Number ggrpKey = (Number) ((EOGenericRecord) tosReservationAp.objectAtIndex(k)).valueForKey("ggrpKey");
						if (ggrpKey == null || ggrpKeys.containsObject(ggrpKey)) {
							shouldAdd = true;
							break;
						}
					}
					if (shouldAdd) {
						tmp.addObject(periodicite);
					}
				}
			}
		}

		NSMutableArray res = new NSMutableArray();
		for (int i = 0; i < tmp.count(); i++) {
			EOGenericRecord objet = (EOGenericRecord) tmp.objectAtIndex(i);
			EDTObject obj = new EDTObject(dataBus);
			obj.initObjet(objet, EDTObject.TYPE_PERIODICITE);
			res.addObject(obj);
		}
		return res;
	}

	/**
	 * @param persId
	 * @param debut
	 * @param fin
	 * @return le tableau des EDTObject qui va bien...
	 */
	public static NSArray getPlanningEtudiantExamens(CRIDataBus dataBus, Number persId, NSTimestamp debut, NSTimestamp fin) {
		try {
			EOQualifier qual = EOQualifier.qualifierWithQualifierFormat("persId = %@", new NSArray(persId));
			EOFetchSpecification myFetch = new EOFetchSpecification("ScolInscriptionEtudiant", qual, null);
			myFetch.setRefreshesRefetchedObjects(true);
			myFetch.setUsesDistinct(true);
			NSArray inscriptions = dataBus.editingContext().objectsWithFetchSpecification(myFetch);
			if (inscriptions == null || inscriptions.count() == 0) {
				return null;
			}

			EOQualifier datesQual = null;
			if (debut != null || fin != null) {
				NSMutableArray args = new NSMutableArray();
				if (debut != null) {
					args.addObject(EOQualifier.qualifierWithQualifierFormat("dateFin >= %@", new NSArray(debut)));
				}
				if (fin != null) {
					args.addObject(EOQualifier.qualifierWithQualifierFormat("dateDeb <= %@", new NSArray(fin)));
				}
				datesQual = new EOAndQualifier(args);
			}

			NSMutableArray sort = new NSMutableArray();
			sort.addObject(EOSortOrdering.sortOrderingWithKey("dateDeb", EOSortOrdering.CompareAscending));
			sort.addObject(EOSortOrdering.sortOrderingWithKey("dateFin", EOSortOrdering.CompareAscending));

			NSMutableArray tmp = new NSMutableArray();
			for (int i = 0; i < inscriptions.count(); i++) {
				EOGenericRecord uneInscription = ((EOGenericRecord) inscriptions.objectAtIndex(i));
				NSArray scolInscriptionExamens = (NSArray) uneInscription.valueForKeyPath("scolInscriptionExamens");
				if (scolInscriptionExamens == null || scolInscriptionExamens.count() == 0) {
					continue;
				}
				NSMutableArray orQuals = new NSMutableArray();
				for (int k = 0; k < scolInscriptionExamens.count(); k++) {
					EOGenericRecord scolInscriptionExamen = (EOGenericRecord) scolInscriptionExamens.objectAtIndex(k);
					String ieentValidite = (String) scolInscriptionExamen.valueForKey("ieentValidite");
					if (ieentValidite != null && ieentValidite.equals("O")) {
						Number eentKey = (Number) scolInscriptionExamen.valueForKey("eentKey");
						orQuals.addObject(EOQualifier.qualifierWithQualifierFormat("reservation.tosResaExam.eentKey = %@", new NSArray(eentKey)));
					}
				}
				if (orQuals.count() == 0) {
					continue;
				}
				qual = new EOOrQualifier(orQuals);
				if (datesQual != null) {
					qual = new EOAndQualifier(new NSArray(new Object[] { qual, datesQual }));
				}

				myFetch = new EOFetchSpecification("Periodicite", qual, sort);
				myFetch.setPrefetchingRelationshipKeyPaths(new NSArray(new Object[] { "reservation", "reservation.tosReservationAp",
						"reservation.tosReservationAp.scolGroupeGrp", "reservation.tosResaSalles", "reservation.tosResaSalles.toSalles" }));
				myFetch.setRefreshesRefetchedObjects(true);
				myFetch.setUsesDistinct(true);
				tmp.addObjectsFromArray(dataBus.editingContext().objectsWithFetchSpecification(myFetch));
			}

			NSMutableArray res = new NSMutableArray();
			for (int i = 0; i < tmp.count(); i++) {
				EOGenericRecord objet = (EOGenericRecord) tmp.objectAtIndex(i);
				EDTObject obj = new EDTObject(dataBus);
				obj.initObjet(objet, EDTObject.TYPE_PERIODICITE);
				res.addObject(obj);
			}
			return res;
		}
		catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
	}

	/**
	 * @param dataBus
	 * @param ggrpKeys
	 * @return un tableau des ggrpKey passés en paramètres + les ggrpKey dans lesquels ils sont inclus, en récursif, attention aux modifs !
	 */
	private static NSArray groupesAvecInclusion(CRIDataBus dataBus, NSArray ggrpKeys) {
		if (ggrpKeys == null || ggrpKeys.count() == 0) {
			return new NSArray();
		}
		NSMutableArray quals = new NSMutableArray(ggrpKeys.count());
		for (int i = 0; i < ggrpKeys.count(); i++) {
			quals.addObject(EOQualifier.qualifierWithQualifierFormat("ggrpKey2 = %@", new NSArray((Number) ggrpKeys.objectAtIndex(i))));
		}
		EOOrQualifier qual = new EOOrQualifier(quals);
		EOFetchSpecification myFetch = new EOFetchSpecification("ScolGroupeInclusion", qual, null);
		// myFetch.setPrefetchingRelationshipKeyPaths(new NSArray("scolGroupeInclusions"));
		myFetch.setRefreshesRefetchedObjects(true);
		NSArray groupesInclusion = dataBus.editingContext().objectsWithFetchSpecification(myFetch);
		if (groupesInclusion == null || groupesInclusion.count() == 0) {
			return ggrpKeys;
		}
		// s'il n'y a pas de niveau d'inclusion supplémentaire, on peut le savoir de suite ici, on évite d'appeler une fois de plus la
		// méthode
		NSArray a = (NSArray) groupesInclusion.valueForKeyPath("scolGroupeInclusions");
		if (a == null || a.count() == 0) {
			return ggrpKeys.arrayByAddingObjectsFromArray((NSArray) groupesInclusion.valueForKey("ggrpKey1"));
		}
		return ggrpKeys.arrayByAddingObjectsFromArray(groupesAvecInclusion(dataBus, (NSArray) groupesInclusion.valueForKey("ggrpKey1")));
	}

	/**
	 * @param mapKey
	 * @param debut
	 * @param fin
	 * @return le tableau des EDTObject qui va bien...
	 */
	public static NSArray getPlanningAp(CRIDataBus dataBus, Number mapKey, NSTimestamp debut, NSTimestamp fin) {
		if (mapKey == null) {
			return null;
		}
		EOQualifier datesQual = null;
		if (debut != null || fin != null) {
			NSMutableArray args = new NSMutableArray();
			if (debut != null) {
				args.addObject(EOQualifier.qualifierWithQualifierFormat("dateFin >= %@", new NSArray(debut)));
			}
			if (fin != null) {
				args.addObject(EOQualifier.qualifierWithQualifierFormat("dateDeb <= %@", new NSArray(fin)));
			}
			datesQual = new EOAndQualifier(args);
		}

		NSMutableArray sort = new NSMutableArray();
		sort.addObject(EOSortOrdering.sortOrderingWithKey("dateDeb", EOSortOrdering.CompareAscending));
		sort.addObject(EOSortOrdering.sortOrderingWithKey("dateFin", EOSortOrdering.CompareAscending));

		EOQualifier qual = EOQualifier.qualifierWithQualifierFormat("reservation.tosReservationAp.mapKey = %@", new NSArray(mapKey));
		if (datesQual != null) {
			qual = new EOAndQualifier(new NSArray(new Object[] { qual, datesQual }));
		}
		EOFetchSpecification myFetch = new EOFetchSpecification("Periodicite", qual, sort);
		myFetch.setPrefetchingRelationshipKeyPaths(new NSArray(new Object[] { "reservation" }));
		myFetch.setRefreshesRefetchedObjects(true);
		myFetch.setUsesDistinct(true);
		NSArray tmp = (dataBus.editingContext().objectsWithFetchSpecification(myFetch));
		NSMutableArray res = new NSMutableArray();
		for (int i = 0; i < tmp.count(); i++) {
			EOGenericRecord objet = (EOGenericRecord) tmp.objectAtIndex(i);
			EDTObject obj = new EDTObject(dataBus);
			obj.initObjet(objet, EDTObject.TYPE_PERIODICITE);
			res.addObject(obj);
		}
		return res;
	}

	public static NSArray getPlanningViaServeurDePlanning(NSTimestamp debut, NSTimestamp fin, Number noIndividu, String spMethode) {
		NSMutableArray resas = new NSMutableArray();
		NSArray occupsFromServeurPlanning = null;
		boolean serveurPlanningAvailable = false;
		try {
			if (fr.univlr.cri.planning.DemandePlanning.serveurPlanningAccessible()) {
				serveurPlanningAvailable = true;
			}
			else {
				System.out.println("ATTENTION : le serveur de Planning est inaccessible !!!!!");
				serveurPlanningAvailable = false;
			}
		}
		catch (java.lang.NoClassDefFoundError noclass) {
			System.out.println("ATTENTION : pas de framework ServeurPlanningFwk, serveur de Planning inaccessible !!!!!");
			serveurPlanningAvailable = false;
		}
		catch (Throwable e) {
			System.out.println("ATTENTION : le serveur de Planning est inaccessible !!");
			serveurPlanningAvailable = false;
		}
		if (serveurPlanningAvailable) {
			try {
				System.out.println(DateCtrl.currentDateTimeString() + " Appel du serveur de Planning pour noIndividu " + noIndividu);
				Properties response = fr.univlr.cri.planning.DemandePlanning.connectionAuServeur("tmpClientEDTSCOL",
						fr.univlr.cri.planning.SPConstantes.IDKEY_INDIVIDU, noIndividu, debut, fin);
				if (fr.univlr.cri.planning.DemandePlanning.getStatut(response)) {
					occupsFromServeurPlanning = fr.univlr.cri.planning.DemandePlanning.getPlanning(response);
				}
				else {
					occupsFromServeurPlanning = null;
					throw new Exception(fr.univlr.cri.planning.DemandePlanning.getError(response));
				}
			}
			catch (Exception e) {
				System.out.println("Erreur lors de la tentative d'accès au serveur de planning : " + e);
				e.printStackTrace();
			}
			if (occupsFromServeurPlanning == null) {
				return resas;
			}

			for (int i = 0; i < occupsFromServeurPlanning.count(); i++) {
				NSArray aOccupation = (NSArray) occupsFromServeurPlanning.objectAtIndex(i);
				for (int j = 0; j < aOccupation.count(); j++) {
					fr.univlr.cri.planning.SPOccupation occ = (fr.univlr.cri.planning.SPOccupation) aOccupation.objectAtIndex(j);
					if (occ.getDateDebut() != null && occ.getDateFin() != null) {
						if ((spMethode == null)
								|| (spMethode.equals("MIS") && occ.getTypeTemps().equals(fr.univlr.cri.planning.SPConstantes.SPOCC_TYPE_MISSION))
								|| (spMethode.equals("DT") && occ.getTypeTemps().equals(fr.univlr.cri.planning.SPConstantes.SPOCC_TYPE_DT))
								|| (spMethode.startsWith("ICS") && occ.getTypeTemps().equals(fr.univlr.cri.planning.SPConstantes.SPOCC_TYPE_ICS))) {
							// pour l'instant on ne met pas les horaires...
							if (!occ.getTypeTemps().equals(fr.univlr.cri.planning.SPConstantes.SPOCC_TYPE_PRESENCE)) {
								Object[] objects = { occ.getDateDebut(), occ.getDateFin(),
										occ.getDetailsTemps() == null ? "" : occ.getDetailsTemps() + " [" + occ.getTypeTemps() + "]" };
								Object[] keys = { "dateDeb", "dateFin", "motif" };
								try {
									NSMutableDictionary ressource = new NSMutableDictionary(objects, keys);
									resas.addObject(ressource);
								}
								catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
		return resas;
	}

	protected class Responder implements AffichePlanningResponder {

		public String nbObjetDispoForDayAndTime(Heure aDay) {
			String affichage = "";
			int oldNoResa = 0;

			for (int i = 0; i < listeResa.count(); i++) {
				EDTObject resaDate = (EDTObject) listeResa.objectAtIndex(i);
				NSTimestamp debut = resaDate.dateDebut();// new
				NSTimestamp fin = resaDate.dateFin();// new

				if (debut != null && fin != null && debut.before(aDay.fin()) && fin.after(aDay.debut())) {

					session().setObjectForKey(resaDate, OBJET_RESA);
					Number resaOrdre = resaDate.key();
					((Session) session()).getObjetResa().setObjectForKey(resaDate, Integer.toString(resaOrdre.intValue()));
					if (resaOrdre.intValue() != oldNoResa) {
						NSDictionary dico = new NSDictionary(Integer.toString(resaOrdre.intValue()), "objet");
						String url = context().directActionURLForActionNamed("infoForResa", dico);

						affichage += (oldNoResa == 0 ? "" : "<br>");
						// on ajoute 2 fois le lien pour avoir un comportement différent selon l'environnement:
						// - un qui sera affiché uniquement dans l'ent
						// - l'autre affiché uniquement en dehors de l'ent

						// ajout du lien hors ent: ouvre une fenetre séparée
						affichage += "<div class=\"aCacherDansEnt\"><a href=\"#\" onclick=\"" + "messageWindow('" + url
								+ "','Informations','300','300');return false;" + "\"" + " onMouseOver=\"return overlib('" + formatString(title2())
								+ "', CAPTION, '" + formatString(title1()) + "');\" onMouseOut=\"return nd();\">-&nbsp;" + motifReservation(20)
								+ "</a></div>";
						// ajout du lien pour l'ent: dans la même fenêtre
						affichage += "<div class=\"monDisplayNoneAMoi\"><a href=\"" + url + "\"" + " onMouseOver=\"return overlib('"
								+ formatString(title2()) + "', CAPTION, '" + formatString(title1()) + "');\" onMouseOut=\"return nd();\"> -&nbsp;"
								+ motifReservation(20) + "</a></div>";

						oldNoResa = resaOrdre.intValue();
					}

				}
			}
			return affichage;
		}

		public int initPlanning(String typeCode) {
			return 0;
		}

		public int initPlanning(NSMutableArray listeObjetCle) {
			theQualifier2 = EOQualifier.qualifierWithQualifierFormat("reservation.tosOccupant.noIndividu = %@", listeObjetCle);

			NSMutableArray args2 = new NSMutableArray();
			if (!showPublicEtPrive()) {
				// pdm - on n'affiche pas que les réunions...
				args2.addObject(theQualifier2);
			}
			if (((Session) session()).getAffichageEnCours() == Main.AFFICHE_INDIVIDU) {
				args2.addObject(theQualifier2);
			}
			if (((Session) session()).getAffichageEnCours() == Main.AFFICHE_INDIVIDU_CHOISI) {
				args2.addObject(theQualifier2);
			}

			theQualifier2 = new EOAndQualifier(args2);

			session().setObjectForKey(theQualifier2, "theQualifier2");

			if (listeObjetCle != null && listeObjetCle.count() > 0) {
				noIndividu = (Number) listeObjetCle.lastObject();
			}
			else {
				noIndividu = null;
			}

			return 0;
		}

		public void initListeResa(NSTimestamp debut, NSTimestamp fin) {
			listeResa.removeAllObjects();
			NSMutableArray tmp = new NSMutableArray();
			NSMutableArray args = new NSMutableArray();

			// Periodicite : reservation autre....(edtscol)
			args.removeAllObjects();
			args.addObject(EOQualifier.qualifierWithQualifierFormat(Periodicite.DATE_FIN_KEY + " >= %@", new NSArray(debut)));
			args.addObject(EOQualifier.qualifierWithQualifierFormat(Periodicite.DATE_DEB_KEY + " <= %@", new NSArray(fin)));
			if (theQualifier2 != null) {
				args.addObject(theQualifier2);
			}
			NSMutableArray sort = new NSMutableArray();
			sort.addObject(EOSortOrdering.sortOrderingWithKey(Periodicite.DATE_DEB_KEY, EOSortOrdering.CompareAscending));
			sort.addObject(EOSortOrdering.sortOrderingWithKey(Periodicite.DATE_FIN_KEY, EOSortOrdering.CompareAscending));
			tmp.addObjectsFromArray(criApp.dataBus().fetchArray(localEContext(), Periodicite.ENTITY_NAME, new EOAndQualifier(args), sort));

			// si etudiant, récupère en plus son edt de formation...
			// LRUserInfo user = null;
			// switch (((Session) session()).getAffichageEnCours()) {
			// case Main.AFFICHE_INDIVIDU_CHOISI:
			// user = ((LRUserInfo) session().objectForKey(EDTIndividuChoisi.INDIVIDU_CHOISI));
			// break;
			// case Main.AFFICHE_CHOIX_INDIVIDU:
			// case Main.AFFICHE_INDIVIDU_PUBLIC:
			// user = new LRUserInfoDB(criApp.dataBus());
			// user.compteForLogin((String) session().objectForKey(EDTIndividuPublic.LOGIN_EXTERNE), null, true);
			// break;
			// case Main.AFFICHE_INDIVIDU:
			// default:
			// user = criSession().connectedUserInfo();
			// }
			// if (user.userStatus() == LRUserInfo.STATUS_ETUDIANT) {
			// listeResa.addObjectsFromArray(getPlanningEtudiant(user.persId(), debut, fin));
			// }

			// EOGenericRecord fournis;
			// if (((Session) session()).getAffichageEnCours() == Main.AFFICHE_INDIVIDU) {
			// fournis = (EOGenericRecord) criApp.dataBus().fetchObject(localEContext(), "FournisUlr",
			// EOQualifier.qualifierWithQualifierFormat("persId=" + criSession().connectedUserInfo().persId(), null));
			// }
			// else {
			// LRUserInfoDB leUserInfo = new LRUserInfoDB(criApp.dataBus());
			// leUserInfo.compteForLogin((String) session().objectForKey(EDTIndividuPublic.LOGIN_EXTERNE), null, true);
			// fournis = (EOGenericRecord) criApp.dataBus().fetchObject(localEContext(), "FournisUlr",
			// EOQualifier.qualifierWithQualifierFormat("persId=" + leUserInfo.persId(), null));
			// }

			for (int i = 0; i < tmp.count(); i++) {
				Periodicite objet = (Periodicite) tmp.objectAtIndex(i);
				EDTObject obj = new EDTObject(criApp.dataBus());
				obj.initObjet(objet, EDTObject.TYPE_PERIODICITE);
				listeResa.addObject(obj);
			}

			// pdm - Serveur de planning en plus...
			if (((Application) criApp).useServeurPlanning()) {
				NSArray occups = getPlanningViaServeurDePlanning(debut, fin, noIndividu, null);
				if (occups != null) {
					for (int i = 0; i < occups.count(); i++) {
						EDTObject obj = new EDTObject(criApp.dataBus());
						obj.initObjet((NSMutableDictionary) occups.objectAtIndex(i), EDTObject.TYPE_SERVEUR_PLANNING);
						obj.setOrdre(new Integer(1000000 + i));
						listeResa.addObject(obj);
					}
				}
			}

			try {
				listeResa.sortUsingComparator(new MySort());
			}
			catch (ComparisonException e) {
			}
		}

		private class MySort extends NSComparator {
			public int compare(Object o1, Object o2) {
				NSTimestamp d1 = ((NSTimestamp) ((EDTObject) o1).dateDebut());
				NSTimestamp d2 = ((NSTimestamp) ((EDTObject) o2).dateDebut());
				if (d1 == null || d2 == null) {
					return NSComparator.OrderedAscending;
				}
				return d1.compare(d2);
			}
		}

		public String couleurTR() {
			Number resaOrdre = ((EDTObject) session().objectForKey(OBJET_RESA)).key();
			if (((Session) session()).getCouleurResa().objectForKey(resaOrdre) == null) {
				String Rouge = Integer.toHexString((new Double(Math.random() * 50)).intValue() + 200);
				String Vert = Integer.toHexString((new Double(Math.random() * 40)).intValue() + 200);
				String Bleu = Integer.toHexString((new Double(Math.random() * 40)).intValue() + 200);
				((Session) session()).getCouleurResa().setObjectForKey("#" + Rouge + Vert + Bleu, resaOrdre);
			}

			return (String) ((Session) session()).getCouleurResa().objectForKey(resaOrdre);
		}

		public WOComponent goReservation() {
			setShowPlanning(false);
			session().setObjectForKey(new Boolean(true), "refreshComposant");
			return null;
		}

		public String title1() {
			if (OBJET_RESA != null && session().objectForKey(OBJET_RESA) != null && session().objectForKey(OBJET_RESA) != NullValue) {
				final EDTObject resaDate = (EDTObject) session().objectForKey(OBJET_RESA);
				final NSTimestampFormatter formatJour = new NSTimestampFormatter("%d / %m / %Y &nbsp;à&nbsp; %H : %M");
				if (resaDate != null) {
					return "Du " + DateCtrl.dateToString(resaDate.dateDebut(), formatJour.pattern()) + " <BR>Au "
							+ DateCtrl.dateToString(resaDate.dateFin(), formatJour.pattern());
				}
			}
			return null;
		}

		public String title2() {
			if (OBJET_RESA != null && session().objectForKey(OBJET_RESA) != null && session().objectForKey(OBJET_RESA) != NullValue) {
				final EDTObject resaDate = (EDTObject) session().objectForKey(OBJET_RESA);
				if (resaDate != null) {
					return resaDate.sujet();
				}
			}
			return null;
		}

		public EDTObject getObjetForIndex(final int index) {
			return (EDTObject) listeResa.objectAtIndex(index);
		}

		public NSArray getListeResaObjet(final NSTimestamp debut, final NSTimestamp fin) {
			listeResa.removeAllObjects();
			initListeResa(debut, fin);
			return listeResa;
		}

		public String motifReservation(final int maxCar) {
			final EDTObject resaDate = (EDTObject) session().objectForKey(OBJET_RESA);
			String affichage = resaDate.sujet();

			String etatAcess = " (public)";
			if (resaDate.isPeriodicite()) {
				String str = ((Reservation) resaDate.valueForKey(Periodicite.RESERVATION_KEY)).tlocCode();
				if (str.equals("p")) {
					etatAcess = " (priv&eacute;)";
				}
				if (str.equals("s")) {
					etatAcess = " (service)";
				}
			}
			if (affichage == null) {
				return "--N.C.--";
			}
			if (affichage.length() > maxCar) {
				affichage = Utils.cleanCut(affichage, maxCar - 3) + "...";
			}
			if (!affichage.equals("")) {
				affichage += etatAcess;
			}
			return affichage;
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

}