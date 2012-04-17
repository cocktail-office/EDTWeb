// _ResaSalles.java
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

// DO NOT EDIT.  Make changes to ResaSalles.java instead.
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

public abstract class _ResaSalles extends  EOGenericRecord {
	public static final String ENTITY_NAME = "ResaSalles";
	public static final String ENTITY_TABLE_NAME = "EDTSCOL.RESA_SALLES";

	// Attributes
 public static final String ENTITY_PRIMARY_KEY = "resaSalKey";

	public static final String RESA_SAL_DATE_KEY = "resaSalDate";
	public static final String RESA_SAL_ETAT_KEY = "resaSalEtat";
	public static final String SAL_NUMERO_KEY = "salNumero";

	public static final String RESA_SAL_DATE_COLKEY = "RESA_SAL_DATE";
	public static final String RESA_SAL_ETAT_COLKEY = "RESA_SAL_ETAT";
	public static final String SAL_NUMERO_COLKEY = "SAL_NUMERO";

//Attributs non visibles
 public static final String RESA_SAL_KEY_KEY = "resaSalKey";
 public static final String RESA_KEY_KEY = "resaKey";

public static final String RESA_SAL_KEY_COLKEY = "RESA_SAL_KEY";
public static final String RESA_KEY_COLKEY = "RESA_KEY";

	// Relationships
	public static final String TO_SALLES_KEY = "toSalles";

	// Utilities methods
	  public ResaSalles localInstanceIn(EOEditingContext editingContext) {
	    ResaSalles localInstance = (ResaSalles)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static ResaSalles getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_ResaSalles.ENTITY_NAME);
		      return (ResaSalles) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public NSTimestamp resaSalDate() {
    return (NSTimestamp) storedValueForKey("resaSalDate");
  }

  public void setResaSalDate(NSTimestamp value) {
    takeStoredValueForKey(value, "resaSalDate");
  }

  public String resaSalEtat() {
    return (String) storedValueForKey("resaSalEtat");
  }

  public void setResaSalEtat(String value) {
    takeStoredValueForKey(value, "resaSalEtat");
  }

  public Double salNumero() {
    return (Double) storedValueForKey("salNumero");
  }

  public void setSalNumero(Double value) {
    takeStoredValueForKey(value, "salNumero");
  }

  public org.cocktail.edtweb.server.metier.Salles toSalles() {
    return (org.cocktail.edtweb.server.metier.Salles)storedValueForKey("toSalles");
  }

  public void setToSallesRelationship(org.cocktail.edtweb.server.metier.Salles value) {
    if (value == null) {
    	org.cocktail.edtweb.server.metier.Salles oldValue = toSalles();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "toSalles");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "toSalles");
    }
  }
  

  public static ResaSalles createResaSalles(EOEditingContext editingContext, Double salNumero
, org.cocktail.edtweb.server.metier.Salles toSalles) {
    ResaSalles eo = (ResaSalles) EOUtilities.createAndInsertInstance(editingContext, _ResaSalles.ENTITY_NAME);    
		eo.setSalNumero(salNumero);
    eo.setToSallesRelationship(toSalles);
    return eo;
  }

  public static NSArray fetchAllResaSalleses(EOEditingContext editingContext) {
    return _ResaSalles.fetchAllResaSalleses(editingContext, null);
  }

  public static NSArray fetchAllResaSalleses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _ResaSalles.fetchResaSalleses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchResaSalleses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_ResaSalles.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static ResaSalles fetchResaSalles(EOEditingContext editingContext, String keyName, Object value) {
    return _ResaSalles.fetchResaSalles(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ResaSalles fetchResaSalles(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _ResaSalles.fetchResaSalleses(editingContext, qualifier, null);
    ResaSalles eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (ResaSalles)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ResaSalles that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ResaSalles fetchRequiredResaSalles(EOEditingContext editingContext, String keyName, Object value) {
    return _ResaSalles.fetchRequiredResaSalles(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ResaSalles fetchRequiredResaSalles(EOEditingContext editingContext, EOQualifier qualifier) {
    ResaSalles eoObject = _ResaSalles.fetchResaSalles(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ResaSalles that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ResaSalles localInstanceIn(EOEditingContext editingContext, ResaSalles eo) {
    ResaSalles localInstance = (eo == null) ? null : (ResaSalles)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
