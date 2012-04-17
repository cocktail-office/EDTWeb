// _Adresse.java
/*
 * Copyright Cocktail, 2001-2008 
 * 
 * This software is governed by the CeCILL license under French law and
 * abiding by the rules of distribution of free software. You can use, 
 * modify and/or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info". 
 * 
 * As a counterpart to the access to the source code and rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty and the software's author, the holder of the
 * economic rights, and the successive licensors have only limited
 * liability. 
 * 
 * In this respect, the user's attention is drawn to the risks associated
 * with loading, using, modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean that it is complicated to manipulate, and that also
 * therefore means that it is reserved for developers and experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and, more generally, to use and operate it in the 
 * same conditions as regards security. 
 * 
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */

// DO NOT EDIT.  Make changes to Adresse.java instead.
package org.cocktail.edtweb.server.metier;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOClassDescription;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.eocontrol.EOKeyValueQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;

public abstract class _Adresse extends  EOGenericRecord {
	public static final String ENTITY_NAME = "Adresse";
	public static final String ENTITY_TABLE_NAME = "GRHUM.INDIVIDU_ULR";

	// Attributes
 public static final String ENTITY_PRIMARY_KEY = "noIndividu";

	public static final String ADR_CIVILITE_KEY = "adrCivilite";
	public static final String ADR_NOM_KEY = "adrNom";
	public static final String ADR_PRENOM_KEY = "adrPrenom";
	public static final String ADR_QUALITE_KEY = "adrQualite";
	public static final String ADR_VALIDE_KEY = "adrValide";
	public static final String NO_INDIVIDU_KEY = "noIndividu";
	public static final String PERS_ID_KEY = "persId";

	public static final String ADR_CIVILITE_COLKEY = "C_CIVILITE";
	public static final String ADR_NOM_COLKEY = "NOM_USUEL";
	public static final String ADR_PRENOM_COLKEY = "PRENOM";
	public static final String ADR_QUALITE_COLKEY = "IND_QUALITE";
	public static final String ADR_VALIDE_COLKEY = "TEM_VALIDE";
	public static final String NO_INDIVIDU_COLKEY = "NO_INDIVIDU";
	public static final String PERS_ID_COLKEY = "PERS_ID";

//Attributs non visibles


	// Relationships
	public static final String TO_PERSONNE_TELEPHONE_KEY = "toPersonneTelephone";

	// Utilities methods
	  public Adresse localInstanceIn(EOEditingContext editingContext) {
	    Adresse localInstance = (Adresse)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static Adresse getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_Adresse.ENTITY_NAME);
		      return (Adresse) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public String adrCivilite() {
    return (String) storedValueForKey("adrCivilite");
  }

  public void setAdrCivilite(String value) {
    takeStoredValueForKey(value, "adrCivilite");
  }

  public String adrNom() {
    return (String) storedValueForKey("adrNom");
  }

  public void setAdrNom(String value) {
    takeStoredValueForKey(value, "adrNom");
  }

  public String adrPrenom() {
    return (String) storedValueForKey("adrPrenom");
  }

  public void setAdrPrenom(String value) {
    takeStoredValueForKey(value, "adrPrenom");
  }

  public String adrQualite() {
    return (String) storedValueForKey("adrQualite");
  }

  public void setAdrQualite(String value) {
    takeStoredValueForKey(value, "adrQualite");
  }

  public String adrValide() {
    return (String) storedValueForKey("adrValide");
  }

  public void setAdrValide(String value) {
    takeStoredValueForKey(value, "adrValide");
  }

  public Double noIndividu() {
    return (Double) storedValueForKey("noIndividu");
  }

  public void setNoIndividu(Double value) {
    takeStoredValueForKey(value, "noIndividu");
  }

  public Double persId() {
    return (Double) storedValueForKey("persId");
  }

  public void setPersId(Double value) {
    takeStoredValueForKey(value, "persId");
  }

  public NSArray toPersonneTelephone() {
    return (NSArray)storedValueForKey("toPersonneTelephone");
  }

  public NSArray toPersonneTelephone(EOQualifier qualifier) {
    return toPersonneTelephone(qualifier, null);
  }

  public NSArray toPersonneTelephone(EOQualifier qualifier, NSArray sortOrderings) {
    NSArray results;
      results = toPersonneTelephone();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToToPersonneTelephoneRelationship(org.cocktail.edtweb.server.metier.PersonneTelephone object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "toPersonneTelephone");
  }

  public void removeFromToPersonneTelephoneRelationship(org.cocktail.edtweb.server.metier.PersonneTelephone object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "toPersonneTelephone");
  }

  public org.cocktail.edtweb.server.metier.PersonneTelephone createToPersonneTelephoneRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("PersonneTelephone");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "toPersonneTelephone");
    return (org.cocktail.edtweb.server.metier.PersonneTelephone) eo;
  }

  public void deleteToPersonneTelephoneRelationship(org.cocktail.edtweb.server.metier.PersonneTelephone object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "toPersonneTelephone");
    editingContext().deleteObject(object);
  }

  public void deleteAllToPersonneTelephoneRelationships() {
    Enumeration objects = toPersonneTelephone().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteToPersonneTelephoneRelationship((org.cocktail.edtweb.server.metier.PersonneTelephone)objects.nextElement());
    }
  }


  public static Adresse createAdresse(EOEditingContext editingContext, String adrCivilite
, Double noIndividu
, Double persId
) {
    Adresse eo = (Adresse) EOUtilities.createAndInsertInstance(editingContext, _Adresse.ENTITY_NAME);    
		eo.setAdrCivilite(adrCivilite);
		eo.setNoIndividu(noIndividu);
		eo.setPersId(persId);
    return eo;
  }

  public static NSArray fetchAllAdresses(EOEditingContext editingContext) {
    return _Adresse.fetchAllAdresses(editingContext, null);
  }

  public static NSArray fetchAllAdresses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _Adresse.fetchAdresses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchAdresses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Adresse.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Adresse fetchAdresse(EOEditingContext editingContext, String keyName, Object value) {
    return _Adresse.fetchAdresse(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Adresse fetchAdresse(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _Adresse.fetchAdresses(editingContext, qualifier, null);
    Adresse eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Adresse)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Adresse that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Adresse fetchRequiredAdresse(EOEditingContext editingContext, String keyName, Object value) {
    return _Adresse.fetchRequiredAdresse(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Adresse fetchRequiredAdresse(EOEditingContext editingContext, EOQualifier qualifier) {
    Adresse eoObject = _Adresse.fetchAdresse(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Adresse that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Adresse localInstanceIn(EOEditingContext editingContext, Adresse eo) {
    Adresse localInstance = (eo == null) ? null : (Adresse)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
