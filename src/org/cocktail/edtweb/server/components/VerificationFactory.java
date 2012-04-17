package org.cocktail.edtweb.server.components;
/*
 * Copyright COCKTAIL (www.cocktail.org), 1995, 2011 This software
 * is governed by the CeCILL license under French law and abiding by the
 * rules of distribution of free software. You can use, modify and/or
 * redistribute the software under the terms of the CeCILL license as
 * circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 * As a counterpart to the access to the source code and rights to copy, modify
 * and redistribute granted by the license, users are provided only with a
 * limited warranty and the software's author, the holder of the economic
 * rights, and the successive licensors have only limited liability. In this
 * respect, the user's attention is drawn to the risks associated with loading,
 * using, modifying and/or developing or reproducing the software by the user
 * in light of its specific status of free software, that may mean that it
 * is complicated to manipulate, and that also therefore means that it is
 * reserved for developers and experienced professionals having in-depth
 * computer knowledge. Users are therefore encouraged to load and test the
 * software's suitability as regards their requirements in conditions enabling
 * the security of their systems and/or data to be ensured and, more generally,
 * to use and operate it in the same conditions as regards security. The
 * fact that you are presently reading this means that you have had knowledge
 * of the CeCILL license and that you accept its terms.
 */

import org.cocktail.edtweb.server.metier.IndividuUlr;
import org.cocktail.edtweb.server.metier.RepartStructure;
import org.cocktail.edtweb.server.metier.ResaObjet;
import org.cocktail.edtweb.server.metier.ResaObjetDepositaire;
import org.cocktail.edtweb.server.metier.ResaObjetReserve;
import org.cocktail.edtweb.server.metier.Secretariat;
import org.cocktail.edtweb.server.metier.StructureUlr;

import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOKeyValueQualifier;
import com.webobjects.eocontrol.EOOrQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

public class VerificationFactory {

	public static boolean testIndividuADroitReserverObjet(EOEditingContext ec, IndividuUlr individu, ResaObjet objet) {
		if (individu == null || objet == null) {
			return false;
		}
		if (testIndividuEstDepositaireObjet(ec, individu, objet)) {
			return true;
		}
		if (objet.resaObjetReserves() == null || objet.resaObjetReserves().count() == 0) {
			return true;
		}
		NSMutableArray andQuals = new NSMutableArray();
		andQuals.addObject(new EOKeyValueQualifier(ResaObjetReserve.RESA_OBJET_KEY, EOKeyValueQualifier.QualifierOperatorEqual, objet));

		NSMutableArray orQuals = new NSMutableArray();
		orQuals.addObject(new EOKeyValueQualifier(ResaObjetReserve.REPART_STRUCTURES_KEY + "." + RepartStructure.INDIVIDU_ULR_KEY,
				EOKeyValueQualifier.QualifierOperatorEqual, individu));
		orQuals.addObject(new EOKeyValueQualifier(ResaObjetReserve.STRUCTURE_ULR_KEY + "." + StructureUlr.SECRETARIATS_KEY + "."
				+ Secretariat.INDIVIDU_ULR_KEY, EOKeyValueQualifier.QualifierOperatorEqual, individu));
		orQuals.addObject(new EOKeyValueQualifier(ResaObjetReserve.STRUCTURE_ULR_KEY + "." + StructureUlr.RESPONSABLE_KEY,
				EOKeyValueQualifier.QualifierOperatorEqual, individu));
		andQuals.addObject(new EOOrQualifier(orQuals));
		NSArray a = ResaObjetReserve.fetchResaObjetReserves(ec, new EOAndQualifier(andQuals), null);

		return (a != null && a.count() > 0);
	}

	public static boolean testIndividuEstDepositaireObjet(EOEditingContext ec, IndividuUlr individu, ResaObjet objet) {
		if (individu == null || objet == null) {
			return false;
		}
		if (objet.resaObjetDepositaires() == null || objet.resaObjetDepositaires().count() == 0) {
			return false;
		}
		NSMutableArray andQuals = new NSMutableArray();
		andQuals.addObject(new EOKeyValueQualifier(ResaObjetDepositaire.RESA_OBJET_KEY, EOKeyValueQualifier.QualifierOperatorEqual, objet));
		andQuals.addObject(new EOKeyValueQualifier(ResaObjetDepositaire.REPART_STRUCTURES_KEY + "." + RepartStructure.INDIVIDU_ULR_KEY,
				EOKeyValueQualifier.QualifierOperatorEqual, individu));
		NSArray a = ResaObjetDepositaire.fetchResaObjetDepositaires(ec, new EOAndQualifier(andQuals), null);
		return (a != null && a.count() > 0);
	}
}
