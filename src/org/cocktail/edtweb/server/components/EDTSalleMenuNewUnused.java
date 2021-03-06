package org.cocktail.edtweb.server.components;
// Generated by the WOLips Core at Tue Jul 27 09:21:19 CEST 2004

import org.cocktail.edtweb.server.metier.Salles;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOOrQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;

import fr.univlr.cri.webapp.CRIWebComponent;
import fr.univlr.cri.webext.CRIMenuItemSet;
import fr.univlr.cri.webext.CRIMenuListener;

public class EDTSalleMenuNewUnused extends CRIWebComponent {
	private static final long serialVersionUID = -2641684297912395541L;
	public static final String SALLE_SELECTED = "salleSelected";
	public static final String SHOW_PLANNING = "showLePlanning";
	public MenuListener menuListener;
	public CRIMenuItemSet menuItemSet;
	protected int noItemSelected;

	protected NSArray listSalle;
	protected NSArray listImplantationGeos;

	public EDTSalleMenuNewUnused(WOContext context) {
		super(context);
	}

	protected EOEditingContext localEContext() {
		return session().defaultEditingContext();
	}

	public CRIMenuItemSet menuItemSet() {
		if (menuItemSet == null) {
			menuItemSet = initMenuItems();
		}
		return menuItemSet;
	}

	public CRIMenuListener listener() {
		if (menuListener == null) {
			menuListener = new MenuListener();
		}
		return menuListener;
	}

	public class MenuListener extends CRIMenuListener {
		public WOComponent disconnect() {
			return null;
		}

		public void initMenu() {
		}

		public WOComponent selectMenu(int menuId) {
			if (menuId >= listSalle.count()) {
				return criSession().logout();
			}
			Salles objet = (Salles) listSalle.objectAtIndex(menuId);
			session().setObjectForKey(objet, SALLE_SELECTED);
			session().setObjectForKey(Boolean.TRUE, SHOW_PLANNING);
			session().setObjectForKey(new Boolean(true), "refresh");
			return null;
		}

	}

	protected CRIMenuItemSet initMenuItems() {
		final CRIMenuItemSet menuItemSet = new CRIMenuItemSet();
		final NSMutableArray args = new NSMutableArray();
		args.addObject(EOQualifier.qualifierWithQualifierFormat("salReservable = %@", new NSArray("O")));
		args.addObject(EOQualifier.qualifierWithQualifierFormat("batDebVal <= %@", new NSArray(new NSTimestamp())));
		final NSMutableArray args2 = new NSMutableArray();
		args2.addObject(EOQualifier.qualifierWithQualifierFormat("batFinVal >=%@", new NSArray(new NSTimestamp())));
		args2.addObject(EOQualifier.qualifierWithQualifierFormat("batFinVal = nil", null));
		args.addObject(new EOOrQualifier(args2));

		final NSMutableArray sort = new NSMutableArray();
		sort.addObject(EOSortOrdering.sortOrderingWithKey("batNom", EOSortOrdering.CompareAscending));
		sort.addObject(EOSortOrdering.sortOrderingWithKey("salEtage", EOSortOrdering.CompareAscending));
		sort.addObject(EOSortOrdering.sortOrderingWithKey("salPorte", EOSortOrdering.CompareAscending));
		listSalle = criApp.dataBus().fetchArray(localEContext(), "Salles", new EOAndQualifier(args), sort);
		String typeEnCours = null;
		int niveau1 = 0;
		int niveau2 = 0;
		String etage = "";
		for (int i = 0; i < listSalle.count(); i++) {
			final Salles objet = (Salles) listSalle.objectAtIndex(i);
			if (typeEnCours == null || !typeEnCours.equals(objet.valueForKey("batNom"))) {
				// Les entrees du premier niveau
				menuItemSet.addMenuItem(niveau1, (String) objet.valueForKey("batNom"), (String) objet.valueForKey("batNom"), "_self");
				niveau1++;
				typeEnCours = (String) objet.valueForKey("batNom");
			}
			if ("0".equals(objet.valueForKey("salEtage"))) {
				etage = "Rdc";
			}
			else {
				if ("1".equals(objet.valueForKey("salEtage"))) {
					etage = "1er";
				}
				else {
					etage = (String) objet.valueForKey("salEtage") + "ème";
				}
			}

			menuItemSet.addMenuSubItem(niveau1 - 1, niveau2, etage + ":   " + (String) objet.valueForKey("salPorte"), "Etage: "
					+ (String) objet.valueForKey("salEtage") + " Porte: " + objet.valueForKey("salPorte"), "_self");
			niveau2++;
		}

		return menuItemSet;
	}

	// FIXME le CRIMenu n'a que 2 niveaux d'arborescence... grillé pour l'instant !!!
	protected CRIMenuItemSet initMenuItems2() {
		final CRIMenuItemSet menuItemSet = new CRIMenuItemSet();
		// on recupère les types d'objet qui ne doivent pas être affichés
		final NSMutableArray args = new NSMutableArray();
		args.addObject(EOQualifier.qualifierWithQualifierFormat("salReservable = %@", new NSArray("O")));
		args.addObject(EOQualifier.qualifierWithQualifierFormat("batDebVal <= %@", new NSArray(new NSTimestamp())));
		final NSMutableArray args2 = new NSMutableArray();
		args2.addObject(EOQualifier.qualifierWithQualifierFormat("batFinVal >=%@", new NSArray(new NSTimestamp())));
		args2.addObject(EOQualifier.qualifierWithQualifierFormat("batFinVal = nil", null));
		args.addObject(new EOOrQualifier(args2));

		final NSMutableArray sort = new NSMutableArray();
		sort.addObject(EOSortOrdering.sortOrderingWithKey("batNom", EOSortOrdering.CompareAscending));
		sort.addObject(EOSortOrdering.sortOrderingWithKey("salEtage", EOSortOrdering.CompareAscending));
		sort.addObject(EOSortOrdering.sortOrderingWithKey("salPorte", EOSortOrdering.CompareAscending));
		listSalle = criApp.dataBus().fetchArray(localEContext(), "Salles", new EOAndQualifier(args), sort);
		// listSalle = EOSortOrdering.sortedArrayUsingKeyOrderArray(listSalle, new
		// NSArray(EOSortOrdering.sortOrderingWithKey("toBatiment.repartBatImpGeos.implantationGeo.llImplantationGeo",
		// EOSortOrdering.CompareAscending)));
		String typeEnCoursGeo = null, typeEnCours = null;
		int niveau0 = 0;
		int niveau1 = 0;
		int niveau2 = 0;
		String etage = "";
		// LRLog.trace("listObjet = "+listObjet);
		for (int i = 0; i < listSalle.count(); i++) {
			final Salles objet = (Salles) listSalle.objectAtIndex(i);
			if (typeEnCoursGeo == null || !typeEnCoursGeo.equals(objet.valueForKey("llImplantationGeo"))) {
				menuItemSet.addMenuItem(niveau0, (String) objet.valueForKey("llImplantationGeo"), (String) objet.valueForKey("llImplantationGeo"),
						"_self");
				niveau0++;
				typeEnCoursGeo = (String) objet.valueForKey("llImplantationGeo");
				typeEnCours = null;
			}
			if (typeEnCours == null || !typeEnCours.equals(objet.valueForKey("batNom"))) {
				menuItemSet.addMenuSubItem(niveau0 - 1, niveau1, (String) objet.valueForKey("batNom"), (String) objet.valueForKey("batNom"), "_self");
				niveau1++;
				typeEnCours = (String) objet.valueForKey("batNom");
			}
			if ("0".equals(objet.valueForKey("salEtage"))) {
				etage = "Rdc";
			}
			else {
				if ("1".equals(objet.valueForKey("salEtage"))) {
					etage = "1er";
				}
				else {
					etage = (String) objet.valueForKey("salEtage") + "ème";
				}
			}

			menuItemSet.addMenuSubItem(niveau1 - 1, niveau2, etage + ":   " + (String) objet.valueForKey("salPorte"), "Etage: "
					+ (String) objet.valueForKey("salEtage") + " Porte: " + objet.valueForKey("salPorte"), "_self");
			niveau2++;
		}

		return menuItemSet;
	}

}