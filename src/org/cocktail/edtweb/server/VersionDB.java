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

import fr.univlr.cri.webapp.VersionCocktailOracleUser;

/**
 * Classe de controle de la version du user Oracle de mon application a moi
 * 
 */
public class VersionDB extends VersionCocktailOracleUser {

	public String name() {
		return "EDTSCOL ORACLE user";
	}

	public String dbUserTableName() {
		return "EDTSCOL.DB_VERSION";
	}

	public String dbVersionDateColumnName() {
		return "DB_DATE";
	}

	public String dbVersionIdColumnName() {
		return "DB_VERSION";
	}

	public VersionCocktailRequirements[] dependencies() {
		return null;
	}

}