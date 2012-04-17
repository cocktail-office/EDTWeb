// _Salles.java
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

// DO NOT EDIT.  Make changes to Salles.java instead.
package org.cocktail.edtweb.server.metier;

import java.util.NoSuchElementException;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOClassDescription;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.eocontrol.EOKeyValueQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

public abstract class _Salles extends  EOGenericRecord {
	public static final String ENTITY_NAME = "Salles";
	public static final String ENTITY_TABLE_NAME = "GRHUM.SALLES";

	// Attributes
 public static final String ENTITY_PRIMARY_KEY = "salNumero";

	public static final String BAT_CODE_KEY = "batCode";
	public static final String BAT_DEB_VAL_KEY = "batDebVal";
	public static final String BAT_FIN_VAL_KEY = "batFinVal";
	public static final String BAT_NOM_KEY = "batNom";
	public static final String C_LOCAL_KEY = "cLocal";
	public static final String SAL_ETAGE_KEY = "salEtage";
	public static final String SAL_NUMERO_KEY = "salNumero";
	public static final String SAL_PORTE_KEY = "salPorte";
	public static final String SAL_RESERVABLE_KEY = "salReservable";

	public static final String BAT_CODE_COLKEY = "C_LOCAL";
	public static final String BAT_DEB_VAL_COLKEY = "$attribute.columnName";
	public static final String BAT_FIN_VAL_COLKEY = "$attribute.columnName";
	public static final String BAT_NOM_COLKEY = "$attribute.columnName";
	public static final String C_LOCAL_COLKEY = "C_LOCAL";
	public static final String SAL_ETAGE_COLKEY = "SAL_ETAGE";
	public static final String SAL_NUMERO_COLKEY = "SAL_NUMERO";
	public static final String SAL_PORTE_COLKEY = "SAL_PORTE";
	public static final String SAL_RESERVABLE_COLKEY = "SAL_RESERVABLE";

//Attributs non visibles


	// Relationships
	public static final String TO_BATIMENT_KEY = "toBatiment";

	// Utilities methods
	  public Salles localInstanceIn(EOEditingContext editingContext) {
	    Salles localInstance = (Salles)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static Salles getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_Salles.ENTITY_NAME);
		      return (Salles) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public String batCode() {
    return (String) storedValueForKey("batCode");
  }

  public void setBatCode(String value) {
    takeStoredValueForKey(value, "batCode");
  }

  public NSTimestamp batDebVal() {
    return (NSTimestamp) storedValueForKey("batDebVal");
  }

  public void setBatDebVal(NSTimestamp value) {
    takeStoredValueForKey(value, "batDebVal");
  }

  public NSTimestamp batFinVal() {
    return (NSTimestamp) storedValueForKey("batFinVal");
  }

  public void setBatFinVal(NSTimestamp value) {
    takeStoredValueForKey(value, "batFinVal");
  }

  public String batNom() {
    return (String) storedValueForKey("batNom");
  }

  public void setBatNom(String value) {
    takeStoredValueForKey(value, "batNom");
  }

  public String cLocal() {
    return (String) storedValueForKey("cLocal");
  }

  public void setCLocal(String value) {
    takeStoredValueForKey(value, "cLocal");
  }

  public String salEtage() {
    return (String) storedValueForKey("salEtage");
  }

  public void setSalEtage(String value) {
    takeStoredValueForKey(value, "salEtage");
  }

  public Double salNumero() {
    return (Double) storedValueForKey("salNumero");
  }

  public void setSalNumero(Double value) {
    takeStoredValueForKey(value, "salNumero");
  }

  public String salPorte() {
    return (String) storedValueForKey("salPorte");
  }

  public void setSalPorte(String value) {
    takeStoredValueForKey(value, "salPorte");
  }

  public String salReservable() {
    return (String) storedValueForKey("salReservable");
  }

  public void setSalReservable(String value) {
    takeStoredValueForKey(value, "salReservable");
  }

  public EOGenericRecord toBatiment() {
    return (EOGenericRecord)storedValueForKey("toBatiment");
  }

  public void setToBatimentRelationship(EOGenericRecord value) {
    if (value == null) {
    	EOGenericRecord oldValue = toBatiment();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toBatiment");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toBatiment");
    }
  }
  

  public static Salles createSalles(EOEditingContext editingContext, String batCode
, NSTimestamp batDebVal
, String cLocal
, String salEtage
, Double salNumero
, String salPorte
, String salReservable
, EOGenericRecord toBatiment) {
    Salles eo = (Salles) EOUtilities.createAndInsertInstance(editingContext, _Salles.ENTITY_NAME);    
		eo.setBatCode(batCode);
		eo.setBatDebVal(batDebVal);
		eo.setCLocal(cLocal);
		eo.setSalEtage(salEtage);
		eo.setSalNumero(salNumero);
		eo.setSalPorte(salPorte);
		eo.setSalReservable(salReservable);
    eo.setToBatimentRelationship(toBatiment);
    return eo;
  }

  public static NSArray fetchAllSalleses(EOEditingContext editingContext) {
    return _Salles.fetchAllSalleses(editingContext, null);
  }

  public static NSArray fetchAllSalleses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _Salles.fetchSalleses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchSalleses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Salles.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Salles fetchSalles(EOEditingContext editingContext, String keyName, Object value) {
    return _Salles.fetchSalles(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Salles fetchSalles(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _Salles.fetchSalleses(editingContext, qualifier, null);
    Salles eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Salles)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Salles that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Salles fetchRequiredSalles(EOEditingContext editingContext, String keyName, Object value) {
    return _Salles.fetchRequiredSalles(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Salles fetchRequiredSalles(EOEditingContext editingContext, EOQualifier qualifier) {
    Salles eoObject = _Salles.fetchSalles(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Salles that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Salles localInstanceIn(EOEditingContext editingContext, Salles eo) {
    Salles localInstance = (eo == null) ? null : (Salles)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
