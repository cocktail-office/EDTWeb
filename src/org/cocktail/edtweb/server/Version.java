package org.cocktail.edtweb.server;
/*
 * Copyright Cocktail (Consortium) 1995-2007
 * 
 * Ce logiciel est régi par la licence CeCILL soumise au droit français et respectant les principes de diffusion des logiciels libres. Vous
 * pouvez utiliser, modifier et/ou redistribuer ce programme sous les conditions de la licence CeCILL telle que diffusée par le CEA, le CNRS
 * et l'INRIA sur le site "http://www.cecill.info".
 * 
 * En contrepartie de l'accessibilité au code source et des droits de copie, de modification et de redistribution accordés par cette
 * licence, il n'est offert aux utilisateurs qu'une garantie limitée. Pour les mêmes raisons, seule une responsabilité restreinte pèse sur
 * l'auteur du programme, le titulaire des droits patrimoniaux et les concédants successifs.
 * 
 * A cet égard l'attention de l'utilisateur est attirée sur les risques associés au chargement, à l'utilisation, à la modification et/ou au
 * développement et à la reproduction du logiciel par l'utilisateur étant donné sa spécificité de logiciel libre, qui peut le rendre
 * complexe à manipuler et qui le réserve donc à des développeurs et des professionnels avertis possédant des connaissances informatiques
 * approfondies. Les utilisateurs sont donc invités à charger et tester l'adéquation du logiciel à leurs besoins dans des conditions
 * permettant d'assurer la sécurité de leurs systèmes et ou de leurs données et, plus généralement, à l'utiliser et l'exploiter dans les
 * mêmes conditions de sécurité.
 * 
 * Le fait que vous puissiez accéder à cet en-tête signifie que vous avez pris connaissance de la licence CeCILL, et que vous en avez
 * accepté les termes.
 * 
 * 
 * This software is governed by the CeCILL license under French law and abiding by the rules of distribution of free software. You can use,
 * modify and/ or redistribute the software under the terms of the CeCILL license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 * 
 * As a counterpart to the access to the source code and rights to copy, modify and redistribute granted by the license, users are provided
 * only with a limited warranty and the software's author, the holder of the economic rights, and the successive licensors have only limited
 * liability.
 * 
 * In this respect, the user's attention is drawn to the risks associated with loading, using, modifying and/or developing or reproducing
 * the software by the user in light of its specific status of free software, that may mean that it is complicated to manipulate, and that
 * also therefore means that it is reserved for developers and experienced professionals having in-depth computer knowledge. Users are
 * therefore encouraged to load and test the software's suitability as regards their requirements in conditions enabling the security of
 * their systems and/or data to be ensured and, more generally, to use and operate it in the same conditions as regards security.
 * 
 * The fact that you are presently reading this means that you have had knowledge of the CeCILL license and that you accept its terms.
 */

import fr.univlr.cri.webapp.VersionCocktail;

// Versions...
// nom de l'application
public final class Version extends VersionCocktail {

	// Nom de l'appli
	public static final String APPLI_ID = "EDTWeb";

	// Version appli
	public static final String VERSIONDATE = "07/09/2011";

	public static final int VERSIONNUMMAJ = 1;
	public static final int VERSIONNUMMIN = 4;
	public static final int VERSIONNUMPATCH = 0;
	public static final int VERSIONNUMBUILD = 9;

	// Version minimum de la base de donnees necessaire pour fonctionner avec cette version
	private static final String MIN_APPLI_BD_VERSION = "1.3.7";

	// Pour affichage en ligne de commande...
	public static void main(final String argv[]) {
		System.out.println("");
		System.out.println(APPLI_ID);
		System.out.println("Version " + VERSIONNUMMAJ + "." + VERSIONNUMMIN + "." + VERSIONNUMPATCH + "." + VERSIONNUMBUILD + "." + " du "
				+ VERSIONDATE);
		System.out.println("(c) " + VERSIONDATE.substring(VERSIONDATE.length() - 4) + " Consortium Cocktail");
		System.out.println("");
		System.out.println("Version minimum de la base de donnees necessaire : " + MIN_APPLI_BD_VERSION);
		System.out.println("");
	}

	public VersionCocktailRequirements[] dependencies() {
		return new VersionCocktailRequirements[] {
				new VersionCocktailRequirements(new fr.univlr.cri.webapp.VersionCocktailWebObjects(), "5.2.4", null, true),
				new VersionCocktailRequirements(new fr.univlr.cri.webapp.VersionCocktailJava(), "1.4.2", "1.5", true),
				new VersionCocktailRequirements(new fr.univlr.cri.webext.Version(), "3", null, false),
				new VersionCocktailRequirements(new fr.univlr.cri.webapp.Version(), null, null, false),
				new VersionCocktailRequirements(new VersionDB(), MIN_APPLI_BD_VERSION, null, true) };
	}

	public String name() {
		return APPLI_ID;
	}

	public String date() {
		return VERSIONDATE;
	}

	public int versionNumMaj() {
		return VERSIONNUMMAJ;
	}

	public int versionNumMin() {
		return VERSIONNUMMIN;
	}

	public int versionNumPatch() {
		return VERSIONNUMPATCH;
	}

	public int versionNumBuild() {
		return VERSIONNUMBUILD;
	}

}
