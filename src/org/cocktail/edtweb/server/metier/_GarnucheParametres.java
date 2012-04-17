// _GarnucheParametres.java
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

// DO NOT EDIT.  Make changes to GarnucheParametres.java instead.
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

public abstract class _GarnucheParametres extends  EOGenericRecord {
	public static final String ENTITY_NAME = "GarnucheParametres";
	public static final String ENTITY_TABLE_NAME = "GARNUCHE.GARNUCHE_PARAMETRES";

	// Attributes
 public static final String ENTITY_PRIMARY_KEY = "paramOrdre";

	public static final String PARAM_COMMENTAIRES_KEY = "paramCommentaires";
	public static final String PARAM_KEY_KEY = "paramKey";
	public static final String PARAM_VALUE_KEY = "paramValue";

	public static final String PARAM_COMMENTAIRES_COLKEY = "PARAM_COMMENTAIRES";
	public static final String PARAM_KEY_COLKEY = "PARAM_KEY";
	public static final String PARAM_VALUE_COLKEY = "PARAM_VALUE";

//Attributs non visibles
 public static final String PARAM_ORDRE_KEY = "paramOrdre";

public static final String PARAM_ORDRE_COLKEY = "PARAM_ORDRE";

	// Relationships

	// Utilities methods
	  public GarnucheParametres localInstanceIn(EOEditingContext editingContext) {
	    GarnucheParametres localInstance = (GarnucheParametres)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static GarnucheParametres getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_GarnucheParametres.ENTITY_NAME);
		      return (GarnucheParametres) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public String paramCommentaires() {
    return (String) storedValueForKey("paramCommentaires");
  }

  public void setParamCommentaires(String value) {
    takeStoredValueForKey(value, "paramCommentaires");
  }

  public String paramKey() {
    return (String) storedValueForKey("paramKey");
  }

  public void setParamKey(String value) {
    takeStoredValueForKey(value, "paramKey");
  }

  public String paramValue() {
    return (String) storedValueForKey("paramValue");
  }

  public void setParamValue(String value) {
    takeStoredValueForKey(value, "paramValue");
  }


  public static GarnucheParametres createGarnucheParametres(EOEditingContext editingContext) {
    GarnucheParametres eo = (GarnucheParametres) EOUtilities.createAndInsertInstance(editingContext, _GarnucheParametres.ENTITY_NAME);    
    return eo;
  }

  public static NSArray fetchAllGarnucheParametreses(EOEditingContext editingContext) {
    return _GarnucheParametres.fetchAllGarnucheParametreses(editingContext, null);
  }

  public static NSArray fetchAllGarnucheParametreses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _GarnucheParametres.fetchGarnucheParametreses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchGarnucheParametreses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_GarnucheParametres.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static GarnucheParametres fetchGarnucheParametres(EOEditingContext editingContext, String keyName, Object value) {
    return _GarnucheParametres.fetchGarnucheParametres(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static GarnucheParametres fetchGarnucheParametres(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _GarnucheParametres.fetchGarnucheParametreses(editingContext, qualifier, null);
    GarnucheParametres eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (GarnucheParametres)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one GarnucheParametres that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static GarnucheParametres fetchRequiredGarnucheParametres(EOEditingContext editingContext, String keyName, Object value) {
    return _GarnucheParametres.fetchRequiredGarnucheParametres(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static GarnucheParametres fetchRequiredGarnucheParametres(EOEditingContext editingContext, EOQualifier qualifier) {
    GarnucheParametres eoObject = _GarnucheParametres.fetchGarnucheParametres(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no GarnucheParametres that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static GarnucheParametres localInstanceIn(EOEditingContext editingContext, GarnucheParametres eo) {
    GarnucheParametres localInstance = (eo == null) ? null : (GarnucheParametres)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
