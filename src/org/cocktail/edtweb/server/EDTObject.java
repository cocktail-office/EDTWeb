package org.cocktail.edtweb.server;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.cocktail.edtweb.server.metier.Periodicite;
import org.cocktail.edtweb.server.metier.ScolGroupeGrp;

import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSTimestamp;

import fr.univlr.cri.util.StringCtrl;
import fr.univlr.cri.webapp.CRIDataBus;
import fr.univlr.cri.webapp.LRUserInfo;
import fr.univlr.cri.webapp.LRUserInfoDB;

/*
 * Created on 30 août 2004
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author mparadot
 * 
 * To change the template for this generated type comment go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EDTObject extends Object implements CalendarEvent {
	public static final int TYPE_PERIODICITE = 1;
	public static final int TYPE_MISSION = 2; // a ajouter pour mission 060420
	public static final int TYPE_SERVEUR_PLANNING = 3;
	private Object objet;
	private int typeObjet;
	private final CRIDataBus dataBus;
	private Number ordre;

	public EDTObject(final CRIDataBus aDataBus) {
		super();
		dataBus = aDataBus;
		objet = null;
		typeObjet = -1;
		ordre = null;
	}

	public void setOrdre(final Number n) {
		this.ordre = n;
	}

	public Number ordre() {
		return ordre;
	}

	public void initObjet(final Object anObjet, final int aType) {
		objet = anObjet;
		typeObjet = aType;
	}

	public Object getObject() {
		return objet;
	}

	public Number resaKey() {
		if (isPeriodicite()) {
			return (Number) ((Periodicite) objet).reservation().resaKeyReadOnly();
		}
		return null;
	}

	public Number key() {
		if (ordre() != null) {
			return ordre();
		}
		if (isPeriodicite()) {
			return (Number) ((Periodicite) objet).perKeyReadOnly();
		}
		else
			if (isMission()) {// ajouter pour mission 060420
				return (Number) ((EOGenericRecord) objet).valueForKey("fouOrdre");
			}
			else {
				return new Integer(-1);
			}
	}

	public NSTimestamp dateDebut() {
		return new NSTimestamp(dateDebutDTZ().getTime());// ,TimeZone.getTimeZone("Europe/Paris"));
	}

	// ****LUDO
	public String dateDeb() {
		return new NSTimestamp(dateDebutDTZ().getTime()).toString();
	}

	public NSTimestamp dateDebutDTZ() {
		if (objet == null) {
			return null;
		}
		switch (typeObjet) {
			case TYPE_PERIODICITE:
				if (((Periodicite) objet).dateDeb() == null) {
					return null;
				}
				return ((Periodicite) objet).dateDeb();

			case TYPE_MISSION:// ajouter pour mission 0604
				if (((EOGenericRecord) objet).valueForKey("misDebut") == null) {
					return null;
				}
				return (NSTimestamp) ((EOGenericRecord) objet).valueForKey("misDebut");

			case TYPE_SERVEUR_PLANNING:
				if (((NSMutableDictionary) objet).valueForKey("dateDeb") == null) {
					return null;
				}
				return (NSTimestamp) ((NSMutableDictionary) objet).valueForKey("dateDeb");

			default:
				break;
		}
		return null;
	}

	public void setDateDebut(final NSTimestamp time) {
		switch (typeObjet) {
			case TYPE_PERIODICITE:
				((EOGenericRecord) objet).takeValueForKey(time, "dateDeb");
				break;
			case TYPE_MISSION:// ajouter pour mission 0604
				((EOGenericRecord) objet).takeValueForKey(time, "misDebut");
				break;
			case TYPE_SERVEUR_PLANNING:
				((NSMutableDictionary) objet).takeValueForKey(time, "dateDeb");
				break;
			default:
				break;
		}

	}

	public NSTimestamp dateFinDTZ() {
		if (objet == null) {
			return null;
		}
		switch (typeObjet) {
			case TYPE_PERIODICITE:
				if (((EOGenericRecord) objet).valueForKey("dateFin") == null) {
					return null;
				}
				return (NSTimestamp) ((EOGenericRecord) objet).valueForKey("dateFin");

			case TYPE_MISSION:// ajouter pour mission 0604
				if (((EOGenericRecord) objet).valueForKey("misFin") == null) {
					return null;
				}
				return (NSTimestamp) ((EOGenericRecord) objet).valueForKey("misFin");

			case TYPE_SERVEUR_PLANNING:
				if (((NSMutableDictionary) objet).valueForKey("dateFin") == null) {
					return null;
				}
				return (NSTimestamp) ((NSMutableDictionary) objet).valueForKey("dateFin");

			default:
				break;
		}
		return null;
	}

	public NSTimestamp dateFin() {
		return new NSTimestamp(dateFinDTZ().getTime());// ,TimeZone.getTimeZone("Europe/Paris"));
	}

	public void setDateFin(final NSTimestamp time) {
		switch (typeObjet) {
			case TYPE_PERIODICITE:
				((EOGenericRecord) objet).takeValueForKey(time, "dateFin");
				break;

			case TYPE_MISSION:// ajouter pour mission 0604
				((EOGenericRecord) objet).takeValueForKey(time, "misFin");
				break;

			case TYPE_SERVEUR_PLANNING:
				((NSMutableDictionary) objet).takeValueForKey(time, "dateFin");
				break;

			default:
				break;
		}
	}

	public void setSujet(final String sujet) {
		switch (typeObjet) {
			case TYPE_PERIODICITE:
				((EOGenericRecord) ((EOGenericRecord) objet).valueForKey("reservation")).takeValueForKey(sujet, "resaCommentaire");
				break;

			case TYPE_MISSION:// ajouter pour mission 0604
				((EOGenericRecord) objet).takeValueForKey(sujet, "misMotif");
				break;

			case TYPE_SERVEUR_PLANNING:
				((NSMutableDictionary) objet).takeValueForKey(sujet, "motif");
				break;

			default:
				break;
		}
	}

	public String sujetIcal() {
		String str = null;
		switch (typeObjet) {
			case TYPE_PERIODICITE:
				final NSArray a2 = (NSArray) ((EOGenericRecord) ((EOGenericRecord) objet).valueForKey("reservation")).valueForKey("tosReservationAp");
				if (a2 != null && a2.count() > 0) {
					str = (String) ((EOGenericRecord) a2.objectAtIndex(0)).valueForKey("mapLibelle");
					// si y'a bien un ap, ajout du groupe s'il y a...
					if (str != null && str.trim().length() > 0) {
						try {
							final EOGenericRecord rec = (EOGenericRecord) a2.objectAtIndex(0);
							final ScolGroupeGrp scolGroupeGrp = (ScolGroupeGrp) rec.valueForKey("scolGroupeGrp");
							if (scolGroupeGrp != null) {
								str = str + "\n";
								str = str + "Groupe : " + scolGroupeGrp.ggrpLibelle();
							}
						}
						catch (final Exception e) {
						}
					}
				}
				try {
					final NSArray a3 = (NSArray) ((EOGenericRecord) ((EOGenericRecord) objet).valueForKey("reservation")).valueForKey("tosResaExam");
					if (a3 != null && a3.count() > 0) {
						str = (String) ((EOGenericRecord) ((EOGenericRecord) a3.objectAtIndex(0)).valueForKey("toScolExamenEntete"))
								.valueForKey("eentLibelle");
						// si y'a bien un examen, ajout du groupe s'il y a...
						if (str != null && str.trim().length() > 0) {
							str = "Examen : " + str;
							try {
								final EOGenericRecord rec = (EOGenericRecord) a3.objectAtIndex(0);
								final ScolGroupeGrp scolGroupeGrp = (ScolGroupeGrp) rec.valueForKey("scolGroupeGrp");
								if (scolGroupeGrp != null) {
									str = str + "\n";
									str = str + "Groupe : " + scolGroupeGrp.ggrpLibelle();
								}
							}
							catch (final Exception e) {
							}
						}
					}
				}
				catch (Exception e) {
				}

				// ajout de la salle s'il y a...
				try {
					final NSArray resaSalles = (NSArray) ((EOGenericRecord) ((EOGenericRecord) objet).valueForKeyPath("reservation"))
							.valueForKeyPath("tosResaSalles");
					NSMutableArray a = new NSMutableArray();
					if (resaSalles != null && resaSalles.count() != 0) {
						for (int i = 0; i < resaSalles.count(); i++) {
							EOGenericRecord eoSalle = (EOGenericRecord) ((EOGenericRecord) resaSalles.objectAtIndex(i)).valueForKey("toSalles");
							if (!a.containsObject(eoSalle)) {
								a.addObject(eoSalle);
							}
						}
					}
					if (a != null && a.count() != 0) {
						for (int i = 0; i < a.count(); i++) {
							EOGenericRecord eoSalle = (EOGenericRecord) a.objectAtIndex(i);
							final String salle = (String) eoSalle.valueForKey("salPorte");
							if (salle != null && salle.trim().length() > 0) {
								if (str == null || str.trim().length() == 0) {
									str = "";
								}
								else {
									str = str + "\n";
								}
								final String local = (String) eoSalle.valueForKey("cLocal");
								str = str + "Salle : " + (local != null && local.trim().length() > 0 ? (local + " - ") : "") + salle;
							}
						}
					}
				}
				catch (Exception e) {
				}

				// ajout du commentaire s'il y a...
				try {
					final String commentaire = (String) ((EOGenericRecord) ((EOGenericRecord) objet).valueForKey("reservation"))
							.valueForKey("resaCommentaire");
					if (commentaire != null && commentaire.trim().length() > 0) {
						if (str == null || str.trim().length() == 0) {
							str = "";
						}
						else {
							str = str + "\n";
						}
						str = str + commentaire;
					}
				}
				catch (final Exception e) {
				}
				break;
			case TYPE_MISSION:// ajouter pour mission 0604
				str = (String) (((EOGenericRecord) objet).valueForKey("misMotif"));
				break;
			case TYPE_SERVEUR_PLANNING:
				str = (String) ((NSMutableDictionary) objet).valueForKey("motif");
				break;
		}
		if (str == null) {
			return "N.C.";
		}
		return str;
	}

	public String sujet() {
		return sujet(true);
	}

	public String sujet(final boolean withSalle) {
		String str = null;
		switch (typeObjet) {
			case TYPE_PERIODICITE:
				final NSArray a2 = (NSArray) ((EOGenericRecord) ((EOGenericRecord) objet).valueForKey("reservation")).valueForKey("tosReservationAp");
				if (a2 != null && a2.count() > 0) {
					str = (String) ((EOGenericRecord) a2.objectAtIndex(0)).valueForKey("mapLibelle");
					// si y'a bien un ap, ajout du groupe s'il y a...
					if (str != null && str.trim().length() > 0) {
						try {
							final EOGenericRecord rec = (EOGenericRecord) a2.objectAtIndex(0);
							final ScolGroupeGrp scolGroupeGrp = (ScolGroupeGrp) rec.valueForKey("scolGroupeGrp");
							if (scolGroupeGrp != null) {
								str = str + "<BR>";
								str = str + "<B><I>Groupe : </I></B>" + scolGroupeGrp.ggrpLibelle();
							}
						}
						catch (final Exception e) {
						}
					}
				}

				// ajout de la salle s'il y a...
				if (withSalle) {
					try {
						final NSArray resaSalles = (NSArray) ((EOGenericRecord) ((EOGenericRecord) objet).valueForKeyPath("reservation"))
								.valueForKeyPath("tosResaSalles");
						NSMutableArray a = new NSMutableArray();
						if (resaSalles != null && resaSalles.count() != 0) {
							for (int i = 0; i < resaSalles.count(); i++) {
								EOGenericRecord eoSalle = (EOGenericRecord) ((EOGenericRecord) resaSalles.objectAtIndex(i)).valueForKey("toSalles");
								if (!a.containsObject(eoSalle)) {
									a.addObject(eoSalle);
								}
							}
						}
						if (a != null && a.count() != 0) {
							final String salle = (String) ((EOGenericRecord) a.objectAtIndex(0)).valueForKey("salPorte");
							if (salle != null && salle.trim().length() > 0) {
								if (str == null || str.trim().length() == 0) {
									str = "";
								}
								else {
									str = str + "<BR>";
								}
								str = str + "<B><I>Salle : </I></B>" + salle;
							}
						}
					}
					catch (final Exception e) {
					}
				}
				// ajout du commentaire s'il y a...
				try {
					final String commentaire = (String) ((EOGenericRecord) ((EOGenericRecord) objet).valueForKey("reservation"))
							.valueForKey("resaCommentaire");
					if (commentaire != null && commentaire.trim().length() > 0) {
						if (str == null || str.trim().length() == 0) {
							str = "";
						}
						else {
							str = str + "<BR>";
						}
						str = str + commentaire;
					}
				}
				catch (final Exception e) {
				}
				break;
			case TYPE_MISSION:// ajouter pour mission 0604
				str = (String) (((EOGenericRecord) objet).valueForKey("misMotif"));
				break;
			case TYPE_SERVEUR_PLANNING:
				str = (String) ((NSMutableDictionary) objet).valueForKey("motif");
				break;
		}
		if (str == null) {
			return "N.C.";
		}
		return StringCtrl.replace(str, "\n", "<BR>");
	}

	public String sujetMini() {
		String sujet = "";
		switch (typeObjet) {
			case TYPE_PERIODICITE: {
				final EOGenericRecord resa = (EOGenericRecord) ((EOGenericRecord) objet).valueForKey("reservation");
				if (resa != null) {
					final NSMutableArray liste = new NSMutableArray();
					liste.addObjectsFromArray((NSArray) resa.valueForKey("tosResaExam"));
					if (liste == null) {
						return "N.C.";
					}
					if (liste.count() == 0) {
						return (String) resa.valueForKey("resaCommentaire");
					}
					else {
						sujet = "<B>EC :</B> ";
						final EOGenericRecord aResaExam = (EOGenericRecord) liste.objectAtIndex(0);
						sujet += (String) aResaExam.valueForKey("libelleEC") + " - ";
						sujet += (String) ((EOGenericRecord) aResaExam.valueForKey("toScolExamenEntete")).valueForKey("eentLibelle");
						sujet += "<BR>";
					}
					NSArray listeSalle = null;
					if (resa.valueForKey("tosResaSalles") != null) {
						listeSalle = (NSArray) resa.valueForKey("tosResaSalles");
					}
					if (listeSalle != null && listeSalle.count() != 0) {
						sujet += "<B>Salle(s) :</B> ";
						for (int i = 0; i < listeSalle.count(); i++) {
							final EOGenericRecord uneSalle = (EOGenericRecord) listeSalle.objectAtIndex(i);
							if (uneSalle != null) {
								sujet += (String) ((EOGenericRecord) ((EOGenericRecord) uneSalle.valueForKey("toSalles")).valueForKey("toBatiment"))
										.valueForKey("appellation")
										+ " ";
								sujet += (String) ((EOGenericRecord) uneSalle.valueForKey("toSalles")).valueForKey("etage") + " ";
								sujet += (String) ((EOGenericRecord) uneSalle.valueForKey("toSalles")).valueForKey("salPorte");
							}
						}
					}
					return sujet;
				}
			}
				break;

			case TYPE_MISSION:
				return (String) (((EOGenericRecord) objet).valueForKey("misMotif"));

			case TYPE_SERVEUR_PLANNING:
				return (String) ((NSMutableDictionary) objet).valueForKey("motif");
			default:
				break;
		}
		return "N.C.";
	}

	public String nomEtPrenom() {
		final NSMutableArray noIndividu = new NSMutableArray();
		switch (typeObjet) {
			case TYPE_PERIODICITE: {
				noIndividu.addObjectsFromArray((NSArray) ((NSArray) ((EOGenericRecord) ((EOGenericRecord) objet).valueForKey("reservation"))
						.valueForKey("tosOccupant")).valueForKey("noIndividu"));
			}
				break;
			case TYPE_MISSION:

				final LRUserInfoDB leUserInfo = new LRUserInfoDB(dataBus);// a partir du persid
				leUserInfo.individuForPersId((Number) (((EOGenericRecord) ((EOGenericRecord) objet).valueForKey("fournis")).valueForKey("persId")),
						false);
				noIndividu.addObject(leUserInfo.noIndividu());
				break;

			default:
				break;
		}
		String nom = "";
		for (int i = 0; i < noIndividu.count(); i++) {
			final Number noInd = (Number) noIndividu.objectAtIndex(i);
			final LRUserInfoDB user = new LRUserInfoDB(dataBus);
			user.individuForNoIndividu(noInd, false);
			if (user.errorCode() == LRUserInfo.ERROR_NONE) {
				nom += "<B><I>Nom :</I></B> " + user.nom() + "   <B><I>Prénom :</I></B> " + user.prenom() + "<br>";
			}
		}
		return nom;
	}

	public int getTypeObjet() {
		return typeObjet;
	}

	public Object valueForKey(final String arg0) {
		try {
			if (isServeurPlanning()) {
				return ((NSMutableDictionary) objet).valueForKey(arg0);
			}
			else {
				return ((EOGenericRecord) objet).valueForKey(arg0);
			}
		}
		catch (final Throwable e) {
			return null;
		}
	}

	public void takeValueForKey(final Object arg0, final String arg1) {
		try {
			if (isServeurPlanning()) {
				((NSMutableDictionary) objet).takeValueForKey(arg0, arg1);
			}
			else {
				((EOGenericRecord) objet).takeStoredValueForKey(arg0, arg1);
			}
		}
		catch (final Throwable e) {
		}
	}

	public GregorianCalendar dateDebutGC() {
		final GregorianCalendar tmp = new GregorianCalendar();
		tmp.setFirstDayOfWeek(Calendar.MONDAY);
		tmp.setMinimalDaysInFirstWeek(Calendar.THURSDAY);
		tmp.setTimeInMillis(dateDebut().getTime());
		return tmp;
	}

	public GregorianCalendar dateFinGC() {
		final GregorianCalendar tmp = new GregorianCalendar();
		tmp.setFirstDayOfWeek(Calendar.MONDAY);
		tmp.setMinimalDaysInFirstWeek(Calendar.THURSDAY);
		tmp.setTimeInMillis(dateFin().getTime());
		return tmp;
	}

	public boolean isPeriodicite() {
		return typeObjet == TYPE_PERIODICITE;
	}

	public boolean isMission() {
		return typeObjet == TYPE_MISSION;
	}

	public boolean isServeurPlanning() {
		return typeObjet == TYPE_SERVEUR_PLANNING;
	}

	// CalendarEvent interface...

	public NSTimestamp startTime() {
		return dateDebut();
	}

	public NSTimestamp endTime() {
		return dateFin();
	}

	public String summary() {
		return sujetIcal();
	}

	public int repeatFrequency() {
		return 0;
	}

	public int repeatCount() {
		return 1;
	}

	public int repeatDayOfWeek() {
		return 0;
	}

	public int repeatDayOfWeekInMonth() {
		return 0;
	}

	public NSArray repeatDaysOfMonth() {
		return null;
	}

	public int sequence() {
		return (int) (new NSTimestamp().getTime() / 10000);
	}

	public String status() {
		return null;
	}

	public String uniqueId() {
		try {
			return key().toString();
		}
		catch (Throwable th) {
			return null;
		}
	}

	public boolean wholeDay() {
		return false;
	}

	public String toString() {
		return key() + " - " + dateDeb() + " - " + dateFin() + " - " + " ; ";
	}

}
