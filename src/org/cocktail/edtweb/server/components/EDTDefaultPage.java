package org.cocktail.edtweb.server.components;
import org.cocktail.edtweb.server.Session;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;

import fr.univlr.cri.webapp.CRIWebComponent;

/*
 * Copyright Consortium Coktail, 12 févr. 07
 * 
 * cyril.tarade at univ-lr.fr
 * 
 * Ce logiciel est un programme informatique servant � g�rer des produits dangereux, connaitre leur quantite, leur localisation et leur
 * repartition.
 * 
 * Ce logiciel est r�gi par la licence CeCILL soumise au droit fran�ais et respectant les principes de diffusion des logiciels libres. Vous
 * pouvez utiliser, modifier et/ou redistribuer ce programme sous les conditions de la licence CeCILL telle que diffus�e par le CEA, le CNRS
 * et l'INRIA sur le site "http://www.cecill.info".
 * 
 * En contrepartie de l'accessibilit� au code source et des droits de copie, de modification et de redistribution accord�s par cette
 * licence, il n'est offert aux utilisateurs qu'une garantie limit�e. Pour les m�mes raisons, seule une responsabilit� restreinte p�se sur
 * l'auteur du programme, le titulaire des droits patrimoniaux et les conc�dants successifs.
 * 
 * A cet �gard l'attention de l'utilisateur est attir�e sur les risques associ�s au chargement, � l'utilisation, � la modification et/ou au
 * d�veloppement et � la reproduction du logiciel par l'utilisateur �tant donn� sa sp�cificit� de logiciel libre, qui peut le rendre
 * complexe � manipuler et qui le r�serve donc � des d�veloppeurs et des professionnels avertis poss�dant des connaissances informatiques
 * approfondies. Les utilisateurs sont donc invit�s � charger et tester l'ad�quation du logiciel � leurs besoins dans des conditions
 * permettant d'assurer la s�curit� de leurs syst�mes et ou de leurs donn�es et, plus g�n�ralement, � l'utiliser et l'exploiter dans les
 * m�mes conditions de s�curit�.
 * 
 * Le fait que vous puissiez acc�der � cet en-t�te signifie que vous avez pris connaissance de la licence CeCILL, et que vous en avez
 * accept� les termes.
 */

/**
 * Composant conteneur de toutes les pages de l'application
 * 
 * @author Cyril Tarade <cyril.tarade at univ-lr.fr>
 */

public class EDTDefaultPage extends CRIWebComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -738593714552445374L;

	/**
	 * L'ancre a laquel se rendre au prochain rechargement de la page
	 */
	public String targetPosition;

	/**
	 * La page appelante : clic dessus pour revenir en arriere
	 */
	public WOComponent caller;

	/**
	 * Passage de parametre vers CRIDefaultPage si besoin du CRIToolTip
	 */
	public Boolean useToolTip;

	public EDTDefaultPage(final WOContext context) {
		super(context);
	}

	/**
	 * 
	 */
	public void appendToResponse(final WOResponse arg0, final WOContext arg1) {
		super.appendToResponse(arg0, arg1);
		addHTMLBinding(arg0, "bgcolor", "#ffffff", TAG_OPEN_BODY);
		addHTMLBinding(arg0, "link", "#2d4488", TAG_OPEN_BODY);
		addHTMLBinding(arg0, "text", "#000000", TAG_OPEN_BODY);
		addHTMLBinding(arg0, "vlink", "#2d4488", TAG_OPEN_BODY);

		// dans le cas d'une page avec le menu, on retaille pour que la
		// page soit completement remplie
		// addHTMLBinding(arg0, "marginwidth", "0", TAG_OPEN_BODY);
		addHTMLBinding(arg0, "topmargin", "0", TAG_OPEN_BODY);
		addHTMLBinding(arg0, "leftmargin", "0", TAG_OPEN_BODY);
		addHTMLBinding(arg0, "marginheight", "0", TAG_OPEN_BODY);

		// ajout de la css specifique a cette appli
		// addLocalCss(arg0, "css/edt.css");

		addLocalJScript(arg0, "getMouse.js");
	}

	/**
	 * Nom prenom de la personne connectee + statut
	 */
	public String nomPrenom() {
		return "";
	}

	/**
	 * On affiche les infos de connexion partout sauf sur la page de login
	 */
	public boolean isShowUserInfo() {
		return false;
	}

	public String titrePagePrincipale() {
		String titre = "Emploi du temps ";

		switch (((Session) session()).getAffichageEnCours()) {
			case Main.AFFICHE_INDIVIDU_CHOISI:
			case Main.AFFICHE_CHOIX_INDIVIDU:
			case Main.AFFICHE_INDIVIDU_PUBLIC:
				titre += " - Individus Public";// ajouter
				break;
			case Main.AFFICHE_INDIVIDU:
				titre += " - Individus";
				break;
			case Main.AFFICHE_OBJET:
				titre += " - Objet";
				break;
			case Main.AFFICHE_SALLES:
				titre += " - Salles";
				break;
			case Main.AFFICHE_REUNION:
				titre += " - R&eacute;unions";
				break;
			case Main.AFFICHE_EXAMEN:
				titre += " - Examens";
				break;
			case Main.AFFICHE_DIPLOME:
				titre += " - Dipl&ocirc;me";
				break;
			default:
				break;
		}
		return titre;
	}
}