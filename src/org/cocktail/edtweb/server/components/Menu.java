package org.cocktail.edtweb.server.components;
import org.cocktail.edtweb.server.Session;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

import fr.univlr.cri.webext.CRIMenuItemSet;
import fr.univlr.cri.webext.CRIMenuListener;

public class Menu extends WOComponent {

	private static final long serialVersionUID = -5454741249768077683L;
	// les id des items du menu
	private final static int ITEM_DIPLOME = 1;
	private final static int ITEM_SALLES = 2;
	public final static int ITEM_INDIVIDU = 3;
	public final static int ITEM_OBJET = 4;
	private final static int ITEM_REUNION = 5;
	private final static int ITEM_EXAMEN = 6;
	private final static int ITEM_QUITTER = 7;

	public Menu(final WOContext context) {
		super(context);
	}

	public class EDTMenuListener extends CRIMenuListener {

		public void initMenu() {
			int menuItem;
			// boolean goToPage = true;
			try {
				int aff = ((Session) session()).getAffichageEnCours();
				switch (aff) {
					case Main.AFFICHE_INDIVIDU:
						menuItem = ITEM_INDIVIDU;
						break;
					case Main.AFFICHE_CHOIX_INDIVIDU:
					case Main.AFFICHE_INDIVIDU_CHOISI:
					case Main.AFFICHE_INDIVIDU_PUBLIC:
						menuItem = ITEM_INDIVIDU;
						// goToPage = false;
						break;
					case Main.AFFICHE_DIPLOME:
						menuItem = ITEM_DIPLOME;
						break;
					case Main.AFFICHE_SALLES:
						menuItem = ITEM_SALLES;
						break;
					case Main.AFFICHE_REUNION:
						menuItem = ITEM_REUNION;
						break;
					case Main.AFFICHE_EXAMEN:
						menuItem = ITEM_EXAMEN;
						break;
					case Main.AFFICHE_OBJET:
						menuItem = ITEM_OBJET;
						break;
					case Main.AFFICHE_IDENTIFICATION:
						switch (((Integer) session().objectForKey(EDTLogin.TYPE_IDENTIFICATION)).intValue()) {
							case EDTLogin.IDENTIFICATION_INDIVIDU:
								menuItem = ITEM_INDIVIDU;
								// goToPage = false;
								break;
							case EDTLogin.IDENTIFICATION_OBJET:
								menuItem = ITEM_OBJET;
								// goToPage = false;
								break;
							default:
								menuItem = -1;
						}
						break;
					case Main.AFFICHE_RIEN:
						// if (application() != null && ((Application) application()).config() != null
						// && ((Application) application()).config().intForKey("APP_STARTUP_MENU") != -1) {
						// menuItem = ((Application) application()).config().intForKey("APP_STARTUP_MENU");
						// break;
						// }
					default:
						menuItem = -1;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				menuItem = -1;
			}
			if (menuItem != -1) {
				selectItemWithId(menuItem);
				// if (goToPage) {
				// selectMenu(menuItem);
				// }
			}
		}

		public WOComponent selectMenu(final int id) {
			switch (id) {
				case ITEM_DIPLOME:
					return ((Session) session()).getMainPage().goDiplome();
				case ITEM_SALLES:
					return ((Session) session()).getMainPage().goSalles();
				case ITEM_INDIVIDU:
					return ((Session) session()).getMainPage().goIndividu();
				case ITEM_OBJET:
					return ((Session) session()).getMainPage().goObjet();
				case ITEM_REUNION:
					return ((Session) session()).getMainPage().goReunion();
				case ITEM_EXAMEN:
					return ((Session) session()).getMainPage().goExamen();
				case ITEM_QUITTER:
					return ((Session) session()).logout();
			}
			return null;
		}

		public WOComponent disconnect() {
			return null;
		}

	}

	private CRIMenuItemSet createMenu() {
		final CRIMenuItemSet menuItemSet = new CRIMenuItemSet();
		// Initialisation de menu
		if (((Session) session()).getMainPage().useDiplomes()) {
			menuItemSet.addMenuItem(ITEM_DIPLOME, "Diplômes", "EDT des formations", null);
		}
		menuItemSet.addMenuItem(ITEM_SALLES, "Salles", "EDT par salles", null);
		menuItemSet.addMenuItem(ITEM_INDIVIDU, "Individus", "MON EDT/Agenda", null);
		menuItemSet.addMenuItem(ITEM_OBJET, "Objets", "EDT/Réservations des objets", null);
		menuItemSet.addMenuItem(ITEM_REUNION, "Réunions", "EDT par réunions", null);
		if (((Session) session()).getMainPage().useExamens()) {
			menuItemSet.addMenuItem(ITEM_EXAMEN, "Examens", "EDT des examens", null);
		}
		menuItemSet.addMenuItem(ITEM_QUITTER, "Quitter", "Fermer la session", null);
		return menuItemSet;
	}

	public CRIMenuItemSet menuItemSet() {
		if (((Session) session()).getEdtMenuItemSet() == null) {
			((Session) session()).setEdtMenuItemSet(createMenu());
		}
		return ((Session) session()).getEdtMenuItemSet();
	}

	public CRIMenuListener listener() {
		if (((Session) session()).getEdtMenuLister() == null) {
			((Session) session()).setEdtMenuLister(new EDTMenuListener());
		}
		return ((Session) session()).getEdtMenuLister();
	}

}