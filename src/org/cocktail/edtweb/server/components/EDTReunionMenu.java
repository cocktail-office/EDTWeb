package org.cocktail.edtweb.server.components;
// Generated by the WOLips Core at Thu Aug 19 09:20:29 CEST 2004

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
import fr.univlr.cri.webext.CRIWebMenu;
import fr.univlr.cri.webext.CRIWebMenuItemSet;
import fr.univlr.cri.webext.CRIWebMenuListener;

public class EDTReunionMenu extends CRIWebComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2357374698252729951L;
	public static final String BAT_SELECTED = "batimentSelected";
	public static final String SHOW_PLANNING = "showLePlanningBatiment";

	// pour le criwebmenu
	public MenuListener menuListener;
	public CRIWebMenu webMenu;
	public CRIWebMenuItemSet menuItem;
	protected int noItemSelected;

	protected NSArray listSalle;

	public EDTReunionMenu(final WOContext context) {
		super(context);
		webMenu = new CRIWebMenu(context);
		// on rempli le menu.....

		// init listener
		menuListener = new MenuListener();
		menuListener.initMenu(webMenu);

		// init item du menu
		menuItem = new CRIWebMenuItemSet();
		menuItem = initMenuItems();
	}

	private EOEditingContext localEContext() {
		return session().defaultEditingContext();
	}

	protected CRIWebMenuItemSet initMenuItems() {
		final CRIWebMenuItemSet menuItemSet = new CRIWebMenuItemSet();
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
		// sort.addObject(EOSortOrdering.sortOrderingWithKey("salEtage",EOSortOrdering.CompareAscending));
		// sort.addObject(EOSortOrdering.sortOrderingWithKey("salPorte",EOSortOrdering.CompareAscending));
		final NSMutableArray temp = new NSMutableArray();
		temp.addObject("TOUS LES B&Acirc;TIMENTS");
		temp.addObjectsFromArray(criApp.dataBus().fetchArray(localEContext(), "Salles", new EOAndQualifier(args), sort));
		listSalle = new NSArray(temp.objects());
		String typeEnCours = null;
		int niveau1 = 0;
		// LRLog.trace("listObjet = "+listObjet);
		for (int i = 0; i < listSalle.count(); i++) {
			if (i == 0) {
				menuItemSet.addMenuItem(niveau1, (String) listSalle.objectAtIndex(0), (String) listSalle.objectAtIndex(0), "_self");
			}
			else {
				final Salles objet = (Salles) listSalle.objectAtIndex(i);
				// LRLog.trace("objet = "+objet);
				if (typeEnCours == null || !typeEnCours.equals(objet.valueForKey("batNom"))) {
					// Les entrees du premier niveau
					menuItemSet.addMenuItem(niveau1, (String) objet.valueForKey("batNom"), (String) objet.valueForKey("batNom"), "_self");
					typeEnCours = (String) objet.valueForKey("batNom");
				}
				// if("0".equals((String) objet.valueForKey("salEtage")))
				// etage="Rdc";
				// else
				// {
				// if("1".equals((String) objet.valueForKey("salEtage")))
				// etage="1er";
				// else
				// etage=(String) objet.valueForKey("salEtage")+"ème";
				// }
				//
				// menuItemSet.addMenuSubItem(niveau1-1,niveau2,etage+": " + (String) objet.valueForKey("salPorte"), "Etage: "+(String)
				// objet.valueForKey("salEtage")+" Porte: "+objet.valueForKey("salPorte"), "_self");
				// niveau2++;
			}
			niveau1++;
		}
		// String decoDebut = "<font color=\"#FEDB70\" class=\"\" font-family=\"Arial, Verdana, Microsoft Sans Serif, MS Sans Serif,
		// Helvetica, sans-serif\" font-size=\"11px\" font-weight=\" bold\"><B>";
		// String decoFin = "</B></font>";
		// menuItemSet.addMenuItem(niveau1, decoDebut + "Quitter" + decoFin, "Quiter l'application", "_self");

		return menuItemSet;
	}

	// Definit une classe de receveur d'evenements de menu.
	public class MenuListener implements CRIWebMenuListener {
		public void initMenu(final CRIWebMenu menu) {
			// En general, on ne fait rien dans cette methode
		}

		// La methode appelee chaque fois qu'un evenement est genere.
		public WOComponent selectMenu(final CRIWebMenu menu, final int menuId) {
			if (menuId >= listSalle.count()) {
				return criSession().logout();
			}
			if (menuId == 0) {
				session().setObjectForKey(NullValue, BAT_SELECTED);
			}
			else {
				final Salles objet = (Salles) listSalle.objectAtIndex(menuId);
				session().setObjectForKey(objet.toBatiment(), BAT_SELECTED);
			}
			session().setObjectForKey(Boolean.TRUE, SHOW_PLANNING);
			session().setObjectForKey(new Boolean(true), EDTReunion.REFRESH);
			return null;
		}

	}

}