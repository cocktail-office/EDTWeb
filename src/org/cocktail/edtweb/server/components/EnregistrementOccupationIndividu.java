package org.cocktail.edtweb.server.components;
// Generated by the WOLips Core at Wed Jul 21 10:31:43 CEST 2004
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.cocktail.edtweb.server.Jour;
import org.cocktail.edtweb.server.metier.IndividuUlr;
import org.cocktail.edtweb.server.metier.Occupant;
import org.cocktail.edtweb.server.metier.Periodicite;
import org.cocktail.edtweb.server.metier.Reservation;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.webservices.client.WOWebServiceClient;

import fr.univlr.cri.util.wo5.DateCtrl;
import fr.univlr.cri.webapp.CRIMailBus;
import fr.univlr.cri.webapp.CRIWebComponent;
import fr.univlr.cri.webapp.LRLog;
import fr.univlr.cri.webapp.LRUserInfo;
import fr.univlr.cri.webapp.LRUserInfoDB;

public class EnregistrementOccupationIndividu extends CRIWebComponent {
	private static final long serialVersionUID = 2294287996163064219L;
	public static String FORMATER = "%d/%m/%Y %H:%M";
	public static final String TYPE_PRIVE = "R";
	public static final String TYPE_PUBLIC = "P";
	public static final String TYPE_SERVICE = "S";
	public Integer aYear;

	protected NSMutableArray listeDate;
	protected boolean lundi;
	protected boolean mardi;
	protected boolean mercredi;
	protected boolean jeudi;
	protected boolean vendredi;
	protected boolean samedi;
	protected boolean dimanche;
	protected String heureDebut;
	protected String heureFin;
	protected String listeSemaine;
	protected String minuteDebut;
	protected String minuteFin;
	protected String objetResa;
	protected String typeEnregistrment;
	protected String erreurEnregistrement;
	protected Jour jourDebut;
	protected Jour jourFin;
	protected NSTimestamp dateDebut;
	protected NSTimestamp dateFin;
	protected Integer selectedYear;

	protected WOWebServiceClient webService;
	protected boolean affiche;

	public EnregistrementOccupationIndividu(final WOContext context) {
		super(context);
		setErreurEnregistrement(null); // modifier
		initComposant();
	}

	public EOEditingContext localEContext() {
		return session().defaultEditingContext();
	}

	/** @TypeInfo java.lang.String */
	public NSMutableArray listeDate() {
		return listeDate;
	}

	public void setListeDate(final NSMutableArray newListeDate) {
		listeDate = newListeDate;
	}

	public String listeSemaine() {
		return listeSemaine;
	}

	public void setListeSemaine(final String newListeSemaine) {
		listeSemaine = newListeSemaine;
	}

	public boolean lundi() {
		return lundi;
	}

	public void setLundi(final boolean newLundi) {
		lundi = newLundi;
	}

	public boolean mardi() {
		return mardi;
	}

	public void setMardi(final boolean newMardi) {
		mardi = newMardi;
	}

	public boolean mercredi() {
		return mercredi;
	}

	public void setMercredi(final boolean newMercredi) {
		mercredi = newMercredi;
	}

	public boolean jeudi() {
		return jeudi;
	}

	public void setJeudi(final boolean newJeudi) {
		jeudi = newJeudi;
	}

	public boolean vendredi() {
		return vendredi;
	}

	public void setVendredi(final boolean newVendredi) {
		vendredi = newVendredi;
	}

	public boolean samedi() {
		return samedi;
	}

	public void setSamedi(final boolean newSamedi) {
		samedi = newSamedi;
	}

	public boolean dimanche() {
		return dimanche;
	}

	public void setDimanche(final boolean newDimanche) {
		dimanche = newDimanche;
	}

	public String heureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(final String newHeureDebut) {
		heureDebut = newHeureDebut;
	}

	public String heureFin() {
		return heureFin;
	}

	public void setHeureFin(final String newHeureFin) {
		heureFin = newHeureFin;
	}

	public String minuteDebut() {
		return minuteDebut;
	}

	public void setMinuteDebut(final String newMinuteDebut) {
		minuteDebut = newMinuteDebut;
	}

	public String minuteFin() {
		return minuteFin;
	}

	public void setMinuteFin(final String newMinuteFin) {
		minuteFin = newMinuteFin;
	}

	public String objetResa() {
		return objetResa;
	}

	public void setObjetResa(final String newObjetResa) {
		objetResa = newObjetResa;
	}

	public void appendToResponse(final WOResponse arg0, final WOContext arg1) {
		if (session().objectForKey("refreshComposant") != null && ((Boolean) session().objectForKey("refreshComposant")).booleanValue()) {
			session().setObjectForKey(new Boolean(false), "refreshComposant");
			initComposant();
		}

		super.appendToResponse(arg0, arg1);
	}

	private void initListeDate() {
		listeDate = new NSMutableArray();
		final int anneeDepart = (new GregorianCalendar()).get(Calendar.YEAR);
		for (int i = anneeDepart - 1; i <= anneeDepart + 1; i++) {
			listeDate.addObject(new Integer(i));
		}
	}

	protected void initComposant() {
		initListeDate();
		setTypeEnregistrment(TYPE_PUBLIC);
		// setErreurEnregistrement(null);
		setErreurEnregistrement(erreurEnregistrement); // modifier
		setObjetResa(null); // *****LUDO
		if (session().objectForKey("testludo") != null) { // *******LUDO
			final Boolean bo = (Boolean) session().objectForKey("testludo"); // ***LUDO
			affiche = bo.booleanValue(); // ********LUDO
		}
		else {
			affiche = false;
		}
		if (getJourDebut() != null) {
			setHeureDebut((new Integer(getJourDebut().get(Calendar.HOUR_OF_DAY))).toString());
		}
		// pour l'affichage en NSTimestamp il faut ajouter 1 heure
		if (getJourFin() != null) {
			setHeureFin((new Integer(getJourFin().get(Calendar.HOUR_OF_DAY))).toString());
		}
		if (getJourDebut() != null) {
			setMinuteDebut((new Integer(getJourDebut().get(Calendar.MINUTE))).toString());
		}
		if (getJourFin() != null) {
			setMinuteFin((new Integer(getJourFin().get(Calendar.MINUTE))).toString());
		}
		if (getJourDebut() != null) {
			setSelectedYear(new Integer(getJourDebut().get(Calendar.YEAR)));
		}
		if (getJourFin() != null) {
			setListeSemaine((new Integer(getJourFin().get(Calendar.WEEK_OF_YEAR))).toString());
		}
		if (!selectionPlusieurJour() && getJourDebut() != null) {
			lundi = mardi = mercredi = jeudi = vendredi = samedi = dimanche = false;
			switch (getJourDebut().get(Calendar.DAY_OF_WEEK)) {
				case Calendar.MONDAY:
					setLundi(true);
					break;
				case Calendar.TUESDAY:
					setMardi(true);
					break;
				case Calendar.WEDNESDAY:
					setMercredi(true);
					break;
				case Calendar.THURSDAY:
					setJeudi(true);
					break;
				case Calendar.FRIDAY:
					setVendredi(true);
					break;
				case Calendar.SATURDAY:
					setSamedi(true);
					break;
				case Calendar.SUNDAY:
					setDimanche(true);
					break;
				default:
					break;
			}
		}
		else {
			if (getJourDebut() != null) {
				dateDebut = getJourDebut().toNSTimestamp();
			}
			else {
				dateDebut = null;
			}
			if (getJourFin() != null) {
				dateFin = getJourFin().toNSTimestamp();
			}
			else {
				dateFin = null;
			}
		}
	}

	// sert a savoir si le rdv s etale sur plusieurs jours
	public boolean selectionPlusieurJour() {
		if (getJourDebut() == null || getJourFin() == null) {
			return false;
		}
		return !(getJourDebut().get(Calendar.DAY_OF_YEAR) == getJourFin().get(Calendar.DAY_OF_YEAR) && getJourDebut().get(Calendar.YEAR) == getJourFin()
				.get(Calendar.YEAR));
	}

	/**
	 * @return le debut de la selection
	 */
	public Jour getJourDebut() {
		try {
			return (Jour) session().objectForKey(AffichePlanning.JOUR_DEBUT);
		}
		catch (final Exception e) {
			return null;
		}
	}

	/**
	 * @return la fin de la selection
	 */
	public Jour getJourFin() {
		try {
			return (Jour) session().objectForKey(AffichePlanning.JOUR_FIN);
		}
		catch (final Exception e) {
			return null;
		}
	}

	/**
	 * @param jour
	 */
	public void setJourDebut(final Jour jour) {
		session().setObjectForKey(jour, AffichePlanning.JOUR_DEBUT);
	}

	/**
	 * @param jour
	 */
	public void setJourFin(final Jour jour) {
		session().setObjectForKey(jour, AffichePlanning.JOUR_FIN);
	}

	public NSTimestamp dateDebut() {
		return dateDebut;
	}

	public void setDateDebut(final NSTimestamp newDateDebut) {
		dateDebut = newDateDebut;
	}

	public NSTimestamp dateFin() {
		return dateFin;
	}

	public void setDateFin(final NSTimestamp newDateFin) {
		dateFin = newDateFin;
	}

	public Integer selectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(final Integer newSelectedYear) {
		selectedYear = newSelectedYear;
	}

	public String affichageYear() {
		if (aYear != null) {
			return " " + aYear.intValue() + " / " + (aYear.intValue() + 1) + " ";
		}
		return null;
	}

	public String typeEnregistrment() {
		return typeEnregistrment;
	}

	public void setTypeEnregistrment(final String newTypeEnregistrment) {
		typeEnregistrment = newTypeEnregistrment;
	}

	public String typePrive() {
		return TYPE_PRIVE;
	}

	public String typePublic() {
		return TYPE_PUBLIC;
	}

	public String typeService() {
		return TYPE_SERVICE;
	}

	public WOComponent annuler() {
		session().setObjectForKey(new Boolean(true), EDTIndividu.SHOW_PLANNING);
		session().setObjectForKey(new Boolean(true), "refreshComposant");
		session().setObjectForKey(new Boolean(true), "Form");

		session().setObjectForKey(new Boolean(false), "voirGraphique");
		session().setObjectForKey(new Boolean(false), "CHOIX_PAR_GRAPH");
		setErreurEnregistrement(null);// ajouter
		//
		return null;
	}

	public WOComponent faireReservation() {
		if (!selectionPlusieurJour()) {
			setErreurEnregistrement(reservationMultiSemaine2());
		}
		else {
			setErreurEnregistrement(reservationSelectionPllusieurJour2());
		}
		if (getErreurEnregistrement() != null) {
			return null;
		}
		session().setObjectForKey(new Boolean(true), EDTIndividu.SHOW_PLANNING);
		session().setObjectForKey(new Boolean(true), "refreshComposant");
		session().setObjectForKey(new Boolean(true), "Form");

		session().setObjectForKey(new Boolean(false), "voirGraphique");
		session().setObjectForKey(new Boolean(false), "CHOIX_PAR_GRAPH");
		//
		return null;
	}

	public String reservationMultiSemaine2() {

		final NSArray semaines = getAllWeeks();
		// ****LUDO
		if (semaines == null) {
			return "Probleme avec les semaines";
		}
		final NSMutableArray listeHeureReserver = new NSMutableArray();

		// ********LUDO
		if (!lundi() && !mardi() && !mercredi() && !jeudi() && !vendredi() && !samedi() && !dimanche()) {
			return "cocher au moins un jour";
		}

		// *******LUDO
		if ((Integer.parseInt(heureDebut()) > 23) || (Integer.parseInt(heureFin()) > 23) || (Integer.parseInt(minuteDebut()) > 60)
				|| (Integer.parseInt(minuteFin()) > 60)) {
			return "Horaire incoherent";
		}

		// Création des dates debut et fin a rÈserver
		for (int i = 0; i < semaines.count(); i++) {
			final Integer noSemaine = (Integer) semaines.objectAtIndex(i);
			final int annee = selectedYear().intValue();

			final GregorianCalendar debut = new GregorianCalendar();
			// Lundi est le premier jour de la semaine
			debut.setFirstDayOfWeek(Calendar.MONDAY);
			// La premiËre semaine est celle qui contient
			// le premier jeudi de Janvier
			debut.setMinimalDaysInFirstWeek(4);
			// debut.setTimeZone(TimeZone.getTimeZone("GMT"));
			debut.set(Calendar.YEAR, annee);
			debut.set(Calendar.WEEK_OF_YEAR, noSemaine.intValue());
			// On retire l'heure ajouter pour l'affichage
			try {
				debut.set(Calendar.HOUR_OF_DAY, Integer.parseInt(heureDebut()));
				debut.set(Calendar.MINUTE, Integer.parseInt(minuteDebut()));
			}
			catch (final Throwable e) {
				return "Heure de dÈbut incorrecte";
			}

			debut.set(Calendar.SECOND, 0);

			final GregorianCalendar fin = new GregorianCalendar();
			// Lundi est le premier jour de la semaine
			fin.setFirstDayOfWeek(Calendar.MONDAY);
			// La premiËre semaine est celle qui contient
			// le premier jeudi de Janvier
			fin.setMinimalDaysInFirstWeek(4);
			// fin.setTimeZone(TimeZone.getTimeZone("GMT"));
			fin.set(Calendar.YEAR, annee);
			fin.set(Calendar.WEEK_OF_YEAR, noSemaine.intValue());
			try {
				fin.set(Calendar.HOUR_OF_DAY, Integer.parseInt(heureFin()));
				fin.set(Calendar.MINUTE, Integer.parseInt(minuteFin()));
			}
			catch (final Exception e) {
				return "Heure de fin incorrecte";
			}

			fin.set(Calendar.SECOND, 0);

			// ///*****LUDO

			if (fin.getTimeInMillis() <= debut.getTimeInMillis()) {
				return "Date de debut et de fin incoherentes";
			}

			if (lundi()) {
				debut.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				debut.setGregorianChange(debut.getGregorianChange());
				fin.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				fin.setGregorianChange(fin.getGregorianChange());
				listeHeureReserver.addObject(new Jour(debut));
				listeHeureReserver.addObject(new Jour(fin));
			}
			if (mardi()) {
				debut.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
				debut.setGregorianChange(debut.getGregorianChange());
				fin.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
				fin.setGregorianChange(fin.getGregorianChange());

				listeHeureReserver.addObject(new Jour(debut));
				listeHeureReserver.addObject(new Jour(fin));
			}
			if (mercredi()) {
				debut.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
				debut.setGregorianChange(debut.getGregorianChange());
				fin.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
				fin.setGregorianChange(fin.getGregorianChange());
				listeHeureReserver.addObject(new Jour(debut));
				listeHeureReserver.addObject(new Jour(fin));
			}
			if (jeudi()) {
				debut.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
				debut.setGregorianChange(debut.getGregorianChange());
				fin.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
				fin.setGregorianChange(fin.getGregorianChange());
				// LRLog.trace("debut = "+(new Jour(debut)).toStringComplet());
				// LRLog.trace("fin = "+(new Jour(fin)).toStringComplet());
				listeHeureReserver.addObject(new Jour(debut));
				listeHeureReserver.addObject(new Jour(fin));
			}
			if (vendredi()) {
				debut.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
				debut.setGregorianChange(debut.getGregorianChange());
				fin.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
				fin.setGregorianChange(fin.getGregorianChange());
				// LRLog.trace("debut = "+(new Jour(debut)).toStringComplet());
				// LRLog.trace("fin = "+(new Jour(fin)).toStringComplet());
				listeHeureReserver.addObject(new Jour(debut));
				listeHeureReserver.addObject(new Jour(fin));
			}
			if (samedi()) {
				debut.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
				// LRLog.trace("debut = "+(new Jour(debut)).toStringComplet());
				fin.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
				fin.setGregorianChange(fin.getGregorianChange());
				// LRLog.trace("fin = "+(new Jour(fin)).toStringComplet());
				listeHeureReserver.addObject(new Jour(debut));
				listeHeureReserver.addObject(new Jour(fin));
			}
			if (dimanche()) {
				debut.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				debut.setGregorianChange(debut.getGregorianChange());
				fin.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				fin.setGregorianChange(fin.getGregorianChange());
				// LRLog.trace("debut = "+(new Jour(debut)).toStringComplet());
				// LRLog.trace("fin = "+(new Jour(fin)).toStringComplet());
				listeHeureReserver.addObject(new Jour(debut));
				listeHeureReserver.addObject(new Jour(fin));
			}
		}

		// verification de la disponibilite
		final NSMutableArray periodes = new NSMutableArray();
		for (int i = 0; i < listeHeureReserver.count() - 1; i = i + 2) {
			final Jour debut = (Jour) listeHeureReserver.objectAtIndex(i);
			final Jour fin = (Jour) listeHeureReserver.objectAtIndex(i + 1);
			periodes.addObject(debut.toNSTimestamp());
			periodes.addObject(fin.toNSTimestamp());

			// verif dispo individu
			NSMutableArray quals = new NSMutableArray(2);
			quals.addObject(EOQualifier.qualifierWithQualifierFormat(Periodicite.DATE_FIN_KEY + " > %@ and " + Periodicite.DATE_DEB_KEY + " < %@",
					new NSArray(new Object[] { debut.toNSTimestamp(), fin.toNSTimestamp() })));
			quals.addObject(EOQualifier.qualifierWithQualifierFormat(Periodicite.RESERVATION_KEY + "." + Reservation.TOS_OCCUPANT_KEY + "."
					+ Occupant.NO_INDIVIDU_KEY + " = %@", new NSArray(localUser().noIndividu())));
			NSArray a = criApp.dataBus().fetchArray(Periodicite.ENTITY_NAME, new EOAndQualifier(quals), null);
			if (a != null && a.count() > 0) {
				return "Individu occupé !";
			}
		}

		String typeAgenda = "r";
		if (typeEnregistrment().equals(TYPE_PUBLIC)) {
			typeAgenda = "r";
		}
		if (typeEnregistrment().equals(TYPE_PRIVE)) {
			typeAgenda = "p";
		}
		if (typeEnregistrment().equals(TYPE_SERVICE)) {
			typeAgenda = "s";
		}

		IndividuUlr individuAgent = (IndividuUlr) criApp.dataBus().fetchObject(localEContext(),
				IndividuUlr.ENTITY_NAME,
				EOQualifier.qualifierWithQualifierFormat(IndividuUlr.NO_INDIVIDU_KEY + " = %@", new NSArray(criSession().connectedUserInfo()
						.noIndividu())));
		IndividuUlr individuOccupant = (IndividuUlr) criApp.dataBus().fetchObject(localEContext(), IndividuUlr.ENTITY_NAME,
				EOQualifier.qualifierWithQualifierFormat(IndividuUlr.NO_INDIVIDU_KEY + " = %@", new NSArray(localUser().noIndividu())));

		for (int i = 0; i < listeHeureReserver.count() - 1; i = i + 2) {
			final Jour debut = (Jour) listeHeureReserver.objectAtIndex(i);
			final Jour fin = (Jour) listeHeureReserver.objectAtIndex(i + 1);

			// Creation d'une Reservation
			Reservation nouveauReservation = Reservation.getInstance(localEContext());
			localEContext().insertObject(nouveauReservation);
			nouveauReservation.setIndividuUlrRelationship(individuAgent);
			nouveauReservation.setNoIndividuClient(individuAgent.noIndividu());
			nouveauReservation.setResaCommentaire(objetResa);
			nouveauReservation.setTlocCode(typeAgenda);
			nouveauReservation.setDCreation(new NSTimestamp());
			nouveauReservation.setDModification(nouveauReservation.dCreation());

			// Creation d'une Périodicite
			Periodicite nouveauPeriodicite = Periodicite.getInstance(localEContext());
			localEContext().insertObject(nouveauPeriodicite);
			nouveauPeriodicite.setReservationRelationship(nouveauReservation);
			nouveauPeriodicite.setDateDeb(debut.toNSTimestamp());
			nouveauPeriodicite.setDateFin(fin.toNSTimestamp());
			nouveauPeriodicite.setHcomp(new Integer(0));

			// Creation d'un occupant
			Occupant nouveauOccupant = Occupant.getInstance(localEContext());
			localEContext().insertObject(nouveauOccupant);
			nouveauOccupant.setHcompRec(new Integer(1));
			nouveauOccupant.setNoIndividu(individuOccupant.noIndividu());
			nouveauOccupant.setReservationRelationship(nouveauReservation);
		}

		localEContext().lock();
		try {
			localEContext().saveChanges();
			localEContext().invalidateAllObjects();
		}
		catch (Exception exe) {
			exe.printStackTrace();
			localEContext().revert();
			return "Erreur d'enregistrement !";
		}
		finally {
			localEContext().unlock();
		}

		return null;
	}

	public void sendMail(final int type, final String commentaire, final NSMutableArray periodicites, final String subjet) {
		final String expediteur = criSession().connectedUserInfo().email();
		final String destinateur = localUser().email();
		final StringBuffer msg = new StringBuffer();
		if (type == 1) {
			msg.append("Vous etes convi\u00e9(e)s \u00e0 la r\u00e9union suivante : ");
		}
		else
			if (type == 3) {
				msg.append("La r\u00e9union suivante a \u00e9t\u00e9 annul\u00e9e : ");
			}
			else
				if (type == 2) {
					msg.append("La r\u00e9union suivante a \u00e9t\u00e9 modifi\u00e9e : ");
					msg.append("\n\n");
					msg.append("Nouvelles informations : ");
					msg.append("\n\n");
				}
		msg.append("\n" + commentaire);
		// recherche des destinataires
		for (int i = 0; i < periodicites.count(); i += 2) {
			msg.append("\n\nde ");
			final Jour debut = (Jour) periodicites.objectAtIndex(i);
			final Jour fin = (Jour) periodicites.objectAtIndex(i + 1);
			msg.append(DateCtrl.dateToString(debut.toNSTimestamp(), FORMATER));
			msg.append("\n " + " \u00e0 ");
			msg.append(DateCtrl.dateToString(fin.toNSTimestamp(), FORMATER));
		}
		msg.append("\n\n\nCordialement\n\n");
		msg.append(criSession().connectedUserInfo().prenom() + " " + criSession().connectedUserInfo().nom());

		final CRIMailBus mailBus = new CRIMailBus(criApp.config());
		mailBus.sendMail(expediteur, destinateur, null, subjet, msg.toString());
	}

	private String reservationSelectionPllusieurJour2() {
		// on ajout un jour car le premier de la semaine est le 0 avec GC et 1
		// avec NSTimestamp
		Jour debut = null;
		try {
			debut = new Jour(dateDebut());// .timestampByAddingGregorianUnits(0,0,1,0,0,0));
			// debut.setTimeZone(TimeZone.getTimeZone("GMT"));
			debut.set(Calendar.HOUR_OF_DAY, Integer.parseInt(heureDebut()));
			debut.set(Calendar.MINUTE, Integer.parseInt(minuteDebut()));
		}
		catch (final Throwable e) {
			return "Date de début incorrecte";
		}
		debut.set(Calendar.SECOND, 0);

		// on ajout un jour car le premier de la semaine est le 0 avec GC et 1
		// avec NSTimestamp
		Jour fin = null;
		try {
			fin = new Jour(dateFin());// .timestampByAddingGregorianUnits(0,0,1,0,0,0));
			// pdm pas de ça, il faut laisser le tz par défaut défini dans le
			// fichier de config !
			// fin.setTimeZone(TimeZone.getTimeZone("GMT"));
			fin.set(Calendar.HOUR_OF_DAY, Integer.parseInt(heureFin()));
			fin.set(Calendar.MINUTE, Integer.parseInt(minuteFin()));
		}
		catch (final Throwable e) {
			return "Date de fin incorrecte";
		}
		fin.set(Calendar.SECOND, 0);

		// verification de la disponibilite
		final NSMutableArray periodes = new NSMutableArray();
		periodes.addObject(debut.toNSTimestamp());
		periodes.addObject(fin.toNSTimestamp());

		NSMutableArray quals = new NSMutableArray(2);
		quals.addObject(EOQualifier.qualifierWithQualifierFormat(Periodicite.DATE_FIN_KEY + " > %@ and " + Periodicite.DATE_DEB_KEY + " < %@",
				new NSArray(new Object[] { debut.toNSTimestamp(), fin.toNSTimestamp() })));
		quals.addObject(EOQualifier.qualifierWithQualifierFormat(Periodicite.RESERVATION_KEY + "." + Reservation.TOS_OCCUPANT_KEY + "."
				+ Occupant.NO_INDIVIDU_KEY + " = %@", new NSArray(localUser().noIndividu())));
		NSArray a = criApp.dataBus().fetchArray(Periodicite.ENTITY_NAME, new EOAndQualifier(quals), null);
		if (a != null && a.count() > 0) {
			return "Individu occupé !";
		}

		String typeAgenda = "r";
		if (typeEnregistrment().equals(TYPE_PUBLIC)) {
			typeAgenda = "r";
		}
		if (typeEnregistrment().equals(TYPE_PRIVE)) {
			typeAgenda = "p";
		}
		if (typeEnregistrment().equals(TYPE_SERVICE)) {
			typeAgenda = "s";
		}

		IndividuUlr individuAgent = (IndividuUlr) criApp.dataBus().fetchObject(
				IndividuUlr.ENTITY_NAME,
				EOQualifier.qualifierWithQualifierFormat(IndividuUlr.NO_INDIVIDU_KEY + " = %@", new NSArray(criSession().connectedUserInfo()
						.noIndividu())));
		IndividuUlr individuOccupant = (IndividuUlr) criApp.dataBus().fetchObject(IndividuUlr.ENTITY_NAME,
				EOQualifier.qualifierWithQualifierFormat(IndividuUlr.NO_INDIVIDU_KEY + " = %@", new NSArray(localUser().noIndividu())));

		// Creation d'une Reservation
		Reservation nouveauReservation = Reservation.getInstance(localEContext());
		localEContext().insertObject(nouveauReservation);
		nouveauReservation.setIndividuUlrRelationship(individuAgent);
		nouveauReservation.setNoIndividuClient(individuAgent.noIndividu());
		nouveauReservation.setResaCommentaire(objetResa);
		nouveauReservation.setTlocCode(typeAgenda);
		nouveauReservation.setDCreation(new NSTimestamp());
		nouveauReservation.setDModification(nouveauReservation.dCreation());

		// Creation d'une Périodicite
		Periodicite nouveauPeriodicite = Periodicite.getInstance(localEContext());
		localEContext().insertObject(nouveauPeriodicite);
		nouveauPeriodicite.setReservationRelationship(nouveauReservation);
		nouveauPeriodicite.setDateDeb(debut.toNSTimestamp());
		nouveauPeriodicite.setDateFin(fin.toNSTimestamp());
		nouveauPeriodicite.setHcomp(new Integer(0));

		// Creation d'un occupant
		Occupant nouveauOccupant = Occupant.getInstance(localEContext());
		localEContext().insertObject(nouveauOccupant);
		nouveauOccupant.setHcompRec(new Integer(1));
		nouveauOccupant.setNoIndividu(individuOccupant.noIndividu());
		nouveauOccupant.setReservationRelationship(nouveauReservation);

		localEContext().lock();
		try {
			localEContext().saveChanges();
		}
		catch (Exception exe) {
			exe.printStackTrace();
			localEContext().revert();
			return "Erreur d'enregistrement !";
		}
		finally {
			localEContext().unlock();
		}

		return null;
	}

	public LRUserInfo localUser() {
		final LRUserInfoDB user = new LRUserInfoDB(criApp.dataBus());
		user.individuForNoIndividu((Number) ((NSArray) session().objectForKey(AffichePlanning.LISTE_OBJET)).lastObject(), true);
		return user;
	}

	/**
	 * permet de recuperer la liste de semaine d'aprËs un chaine de caractËre du type "30;37-40;42"
	 * 
	 * @return un Array de Integer contenent les numÈros des semaines
	 */
	public NSArray getAllWeeks() {
		final NSMutableArray listeDesSemaines = new NSMutableArray();
		String[] tmp = null;
		if (listeSemaine() != null) {
			tmp = listeSemaine().split(";");
		}
		if (tmp != null) {
			for (int i = 0; i < tmp.length; i++) {
				final String string = tmp[i];
				final String[] tmp2 = string.split("-");
				try {
					if (tmp2.length == 1) {
						final Integer nb = new Integer(tmp2[0]);
						if (nb.intValue() > 52) {
							return null; // *******LUDO
						}
						listeDesSemaines.addObject(nb);
					}
					else {
						if (!"".equals(tmp2[0])) {
							for (int j = Integer.parseInt(tmp2[0]); j <= Integer.parseInt(tmp2[1]); j++) {
								final Integer nb = new Integer(j);
								if (nb.intValue() > 52) {
									return null; // *****LUDO
								}
								listeDesSemaines.addObject(nb);
							}
						}
					}
				}
				catch (final NumberFormatException e) {
					LRLog.log("PB : " + e);
					return null; // *****LUDO
				}
			}
		}
		else {
			return null; // ***LUDO
		}
		return listeDesSemaines;
	}

	/**
	 * @return
	 */
	public String getErreurEnregistrement() {
		return erreurEnregistrement;
	}

	/**
	 * @param string
	 */
	public void setErreurEnregistrement(final String string) {
		erreurEnregistrement = string;
	}

	public boolean affiche() {
		return affiche;
	}

	public void setAffiche(final boolean newAffiche) {
		affiche = newAffiche;
	}

	public boolean isErreur() {
		if (erreurEnregistrement != null) {
			return true;
		}
		else {
			return false;
		}

	}

}