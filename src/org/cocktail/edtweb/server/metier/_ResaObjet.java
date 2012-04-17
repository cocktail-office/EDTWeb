// _ResaObjet.java
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

// DO NOT EDIT.  Make changes to ResaObjet.java instead.
package org.cocktail.edtweb.server.metier;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOAndQualifier;
import com.webobjects.eocontrol.EOClassDescription;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.eocontrol.EOKeyValueQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

public abstract class _ResaObjet extends  EOGenericRecord {
	public static final String ENTITY_NAME = "ResaObjet";
	public static final String ENTITY_TABLE_NAME = "EDTSCOL.RESA_OBJET";

	// Attributes
 public static final String ENTITY_PRIMARY_KEY = "roKey";

	public static final String RESA_TYPE_OBJET_RTO_LIBELLE_KEY = "resaTypeObjet_rtoLibelle";
	public static final String RO_ACCES_KEY = "roAcces";
	public static final String RO_KEY_KEY = "roKey";
	public static final String RO_LIBELLE1_KEY = "roLibelle1";
	public static final String RO_LIBELLE2_KEY = "roLibelle2";
	public static final String RO_RESERVABLE_KEY = "roReservable";
	public static final String RTO_KEY_KEY = "rtoKey";
	public static final String SAL_NUMERO_KEY = "salNumero";

	public static final String RESA_TYPE_OBJET_RTO_LIBELLE_COLKEY = "$attribute.columnName";
	public static final String RO_ACCES_COLKEY = "RO_ACCES";
	public static final String RO_KEY_COLKEY = "RO_KEY";
	public static final String RO_LIBELLE1_COLKEY = "RO_LIBELLE1";
	public static final String RO_LIBELLE2_COLKEY = "RO_LIBELLE2";
	public static final String RO_RESERVABLE_COLKEY = "RO_RESERVABLE";
	public static final String RTO_KEY_COLKEY = "RTO_KEY";
	public static final String SAL_NUMERO_COLKEY = "SAL_NUMERO";

//Attributs non visibles


	// Relationships
	public static final String RESA_OBJET_DEPOSITAIRES_KEY = "resaObjetDepositaires";
	public static final String RESA_OBJET_RESERVES_KEY = "resaObjetReserves";
	public static final String RESA_TYPE_OBJET_KEY = "resaTypeObjet";
	public static final String SALLES_KEY = "salles";

	// Utilities methods
	  public ResaObjet localInstanceIn(EOEditingContext editingContext) {
	    ResaObjet localInstance = (ResaObjet)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static ResaObjet getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_ResaObjet.ENTITY_NAME);
		      return (ResaObjet) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public String resaTypeObjet_rtoLibelle() {
    return (String) storedValueForKey("resaTypeObjet_rtoLibelle");
  }

  public void setResaTypeObjet_rtoLibelle(String value) {
    takeStoredValueForKey(value, "resaTypeObjet_rtoLibelle");
  }

  public String roAcces() {
    return (String) storedValueForKey("roAcces");
  }

  public void setRoAcces(String value) {
    takeStoredValueForKey(value, "roAcces");
  }

  public Integer roKey() {
    return (Integer) storedValueForKey("roKey");
  }

  public void setRoKey(Integer value) {
    takeStoredValueForKey(value, "roKey");
  }

  public String roLibelle1() {
    return (String) storedValueForKey("roLibelle1");
  }

  public void setRoLibelle1(String value) {
    takeStoredValueForKey(value, "roLibelle1");
  }

  public String roLibelle2() {
    return (String) storedValueForKey("roLibelle2");
  }

  public void setRoLibelle2(String value) {
    takeStoredValueForKey(value, "roLibelle2");
  }

  public String roReservable() {
    return (String) storedValueForKey("roReservable");
  }

  public void setRoReservable(String value) {
    takeStoredValueForKey(value, "roReservable");
  }

  public Integer rtoKey() {
    return (Integer) storedValueForKey("rtoKey");
  }

  public void setRtoKey(Integer value) {
    takeStoredValueForKey(value, "rtoKey");
  }

  public Integer salNumero() {
    return (Integer) storedValueForKey("salNumero");
  }

  public void setSalNumero(Integer value) {
    takeStoredValueForKey(value, "salNumero");
  }

  public org.cocktail.edtweb.server.metier.ResaTypeObjet resaTypeObjet() {
    return (org.cocktail.edtweb.server.metier.ResaTypeObjet)storedValueForKey("resaTypeObjet");
  }

  public void setResaTypeObjetRelationship(org.cocktail.edtweb.server.metier.ResaTypeObjet value) {
    if (value == null) {
    	org.cocktail.edtweb.server.metier.ResaTypeObjet oldValue = resaTypeObjet();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "resaTypeObjet");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "resaTypeObjet");
    }
  }
  
  public org.cocktail.edtweb.server.metier.Salles salles() {
    return (org.cocktail.edtweb.server.metier.Salles)storedValueForKey("salles");
  }

  public void setSallesRelationship(org.cocktail.edtweb.server.metier.Salles value) {
    if (value == null) {
    	org.cocktail.edtweb.server.metier.Salles oldValue = salles();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "salles");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "salles");
    }
  }
  
  public NSArray resaObjetDepositaires() {
    return (NSArray)storedValueForKey("resaObjetDepositaires");
  }

  public NSArray resaObjetDepositaires(EOQualifier qualifier) {
    return resaObjetDepositaires(qualifier, null, false);
  }

  public NSArray resaObjetDepositaires(EOQualifier qualifier, boolean fetch) {
    return resaObjetDepositaires(qualifier, null, fetch);
  }

  public NSArray resaObjetDepositaires(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.edtweb.server.metier.ResaObjetDepositaire.RESA_OBJET_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.edtweb.server.metier.ResaObjetDepositaire.fetchResaObjetDepositaires(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = resaObjetDepositaires();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToResaObjetDepositairesRelationship(org.cocktail.edtweb.server.metier.ResaObjetDepositaire object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "resaObjetDepositaires");
  }

  public void removeFromResaObjetDepositairesRelationship(org.cocktail.edtweb.server.metier.ResaObjetDepositaire object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "resaObjetDepositaires");
  }

  public org.cocktail.edtweb.server.metier.ResaObjetDepositaire createResaObjetDepositairesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ResaObjetDepositaire");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "resaObjetDepositaires");
    return (org.cocktail.edtweb.server.metier.ResaObjetDepositaire) eo;
  }

  public void deleteResaObjetDepositairesRelationship(org.cocktail.edtweb.server.metier.ResaObjetDepositaire object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "resaObjetDepositaires");
    editingContext().deleteObject(object);
  }

  public void deleteAllResaObjetDepositairesRelationships() {
    Enumeration objects = resaObjetDepositaires().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteResaObjetDepositairesRelationship((org.cocktail.edtweb.server.metier.ResaObjetDepositaire)objects.nextElement());
    }
  }

  public NSArray resaObjetReserves() {
    return (NSArray)storedValueForKey("resaObjetReserves");
  }

  public NSArray resaObjetReserves(EOQualifier qualifier) {
    return resaObjetReserves(qualifier, null, false);
  }

  public NSArray resaObjetReserves(EOQualifier qualifier, boolean fetch) {
    return resaObjetReserves(qualifier, null, fetch);
  }

  public NSArray resaObjetReserves(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.edtweb.server.metier.ResaObjetReserve.RESA_OBJET_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.edtweb.server.metier.ResaObjetReserve.fetchResaObjetReserves(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = resaObjetReserves();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToResaObjetReservesRelationship(org.cocktail.edtweb.server.metier.ResaObjetReserve object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "resaObjetReserves");
  }

  public void removeFromResaObjetReservesRelationship(org.cocktail.edtweb.server.metier.ResaObjetReserve object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "resaObjetReserves");
  }

  public org.cocktail.edtweb.server.metier.ResaObjetReserve createResaObjetReservesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ResaObjetReserve");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "resaObjetReserves");
    return (org.cocktail.edtweb.server.metier.ResaObjetReserve) eo;
  }

  public void deleteResaObjetReservesRelationship(org.cocktail.edtweb.server.metier.ResaObjetReserve object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "resaObjetReserves");
    editingContext().deleteObject(object);
  }

  public void deleteAllResaObjetReservesRelationships() {
    Enumeration objects = resaObjetReserves().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteResaObjetReservesRelationship((org.cocktail.edtweb.server.metier.ResaObjetReserve)objects.nextElement());
    }
  }


  public static ResaObjet createResaObjet(EOEditingContext editingContext, String resaTypeObjet_rtoLibelle
, String roAcces
, Integer roKey
, String roLibelle1
, Integer rtoKey
, org.cocktail.edtweb.server.metier.ResaTypeObjet resaTypeObjet) {
    ResaObjet eo = (ResaObjet) EOUtilities.createAndInsertInstance(editingContext, _ResaObjet.ENTITY_NAME);    
		eo.setResaTypeObjet_rtoLibelle(resaTypeObjet_rtoLibelle);
		eo.setRoAcces(roAcces);
		eo.setRoKey(roKey);
		eo.setRoLibelle1(roLibelle1);
		eo.setRtoKey(rtoKey);
    eo.setResaTypeObjetRelationship(resaTypeObjet);
    return eo;
  }

  public static NSArray fetchAllResaObjets(EOEditingContext editingContext) {
    return _ResaObjet.fetchAllResaObjets(editingContext, null);
  }

  public static NSArray fetchAllResaObjets(EOEditingContext editingContext, NSArray sortOrderings) {
    return _ResaObjet.fetchResaObjets(editingContext, null, sortOrderings);
  }

  public static NSArray fetchResaObjets(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_ResaObjet.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static ResaObjet fetchResaObjet(EOEditingContext editingContext, String keyName, Object value) {
    return _ResaObjet.fetchResaObjet(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ResaObjet fetchResaObjet(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _ResaObjet.fetchResaObjets(editingContext, qualifier, null);
    ResaObjet eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (ResaObjet)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ResaObjet that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ResaObjet fetchRequiredResaObjet(EOEditingContext editingContext, String keyName, Object value) {
    return _ResaObjet.fetchRequiredResaObjet(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ResaObjet fetchRequiredResaObjet(EOEditingContext editingContext, EOQualifier qualifier) {
    ResaObjet eoObject = _ResaObjet.fetchResaObjet(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ResaObjet that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ResaObjet localInstanceIn(EOEditingContext editingContext, ResaObjet eo) {
    ResaObjet localInstance = (eo == null) ? null : (ResaObjet)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
