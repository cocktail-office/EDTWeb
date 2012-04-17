package org.cocktail.edtweb.server.components;
// Generated by the WOLips Core at Thu Jul 22 14:39:48 CEST 2004

import org.cocktail.edtweb.server.metier.ResaObjet;
import org.cocktail.edtweb.server.metier.ResaTypeObjet;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOKeyValueQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

import fr.univlr.cri.webapp.CRIWebComponent;
import fr.univlr.cri.webext.CRIWebMenu;
import fr.univlr.cri.webext.CRIWebMenuItemSet;
import fr.univlr.cri.webext.CRIWebMenuListener;

public class EDTObjetMenu extends CRIWebComponent {
	private static final long serialVersionUID = -7294460429252222631L;
	public static final String OBJET_SELECTED = "objetSelected";
	public static final String SHOW_PLANNING = "showLePlanning";
	// pour le criwebmenu
	public MenuListener menuListener;
	public CRIWebMenu webMenu;
	public CRIWebMenuItemSet menuItem;
	private NSArray listObjet;

	public EDTObjetMenu(final WOContext context) {
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

	// Definit une classe de receveur d'evenements de menu.
	public class MenuListener implements CRIWebMenuListener {
		public void initMenu(final CRIWebMenu menu) {
			// En general, on ne fait rien dans cette methode
		}

		// La methode appelee chaque fois qu'un evenement est genere.
		public WOComponent selectMenu(final CRIWebMenu menu, final int menuId) {
			if (menuId >= listObjet.count()) {
				return criSession().logout();
			}
			final ResaObjet objet = (ResaObjet) listObjet.objectAtIndex(menuId);
			session().setObjectForKey(objet, OBJET_SELECTED);
			session().setObjectForKey(Boolean.TRUE, SHOW_PLANNING);
			session().setObjectForKey(new Boolean(true), "refresh");
			return null;
		}

	}

	// Construit et renvoie une collection des elements
	// du menu. Pourrait etre appelee une fois.
	private CRIWebMenuItemSet initMenuItems() {
		final CRIWebMenuItemSet menuItemSet = new CRIWebMenuItemSet();
		// on recupère les types d'objet qui ne doivent pas être affichés
		final NSMutableArray listeType = new NSMutableArray();
		listeType.addObject(EOQualifier.qualifierWithQualifierFormat(ResaObjet.RESA_TYPE_OBJET_RTO_LIBELLE_KEY + " != %@", new NSArray("AGENDA")));
		listeType.addObject(new EOKeyValueQualifier(ResaObjet.RO_RESERVABLE_KEY, EOKeyValueQualifier.QualifierOperatorEqual, "O"));

		final NSArray tmp = criApp.dataBus().fetchArray(localEContext(), "QuotaRepartValeurTobj", null, null);
		final NSArray tmp2 = (NSArray) tmp.valueForKey("typeCode");
		for (int i = 0; i < tmp2.count(); i++) {
			final String type = (String) tmp2.objectAtIndex(i);
			listeType.addObject(EOQualifier.qualifierWithQualifierFormat(ResaObjet.RESA_TYPE_OBJET_RTO_LIBELLE_KEY + " != %@", new NSArray(type)));// exclure
			// les
			// objets
			// pour
			// le
			// kiosque
		}
		final NSMutableArray sort = new NSMutableArray();
		sort.addObject(EOSortOrdering.sortOrderingWithKey(ResaObjet.RESA_TYPE_OBJET_RTO_LIBELLE_KEY, EOSortOrdering.CompareAscending));
		sort.addObject(EOSortOrdering.sortOrderingWithKey(ResaObjet.RO_LIBELLE1_KEY, EOSortOrdering.CompareAscending));
		sort.addObject(EOSortOrdering.sortOrderingWithKey(ResaObjet.RO_LIBELLE2_KEY, EOSortOrdering.CompareAscending));
		listObjet = criApp.dataBus().fetchArray(localEContext(), ResaObjet.ENTITY_NAME, new EOAndQualifier(listeType), sort);
		ResaTypeObjet typeEnCours = null;
		int niveau1 = 0;
		int niveau2 = 0;
		for (int i = 0; i < listObjet.count(); i++) {
			final ResaObjet objet = (ResaObjet) listObjet.objectAtIndex(i);
			if (typeEnCours == null || typeEnCours != objet.resaTypeObjet()) {
				// Les entrees du premier niveau
				menuItemSet.addMenuItem(niveau1, objet.resaTypeObjet_rtoLibelle(), objet.resaTypeObjet_rtoLibelle(), "_self");
				niveau1++;
				typeEnCours = objet.resaTypeObjet();
			}
			menuItemSet.addMenuSubItem(niveau1 - 1, niveau2, objet.roLibelle1(), objet.roLibelle1(), "_self");
			niveau2++;
		}

		return menuItemSet;
	}
}