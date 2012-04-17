// _ScolGroupeObjet.java
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

// DO NOT EDIT.  Make changes to ScolGroupeObjet.java instead.
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

public abstract class _ScolGroupeObjet extends  EOGenericRecord {
	public static final String ENTITY_NAME = "ScolGroupeObjet";
	public static final String ENTITY_TABLE_NAME = "SCOLARITE.SCOL_GROUPE_OBJET";

	// Attributes
 public static final String ENTITY_PRIMARY_KEY = "gobjKey";

	public static final String FANN_KEY_KEY = "fannKey";
	public static final String GOBJ_TYPE_KEY = "gobjType";
	public static final String MAP_KEY_KEY = "mapKey";
	public static final String MSEM_KEY_KEY = "msemKey";

	public static final String FANN_KEY_COLKEY = "FANN_KEY";
	public static final String GOBJ_TYPE_COLKEY = "GOBJ_TYPE";
	public static final String MAP_KEY_COLKEY = "MAP_KEY";
	public static final String MSEM_KEY_COLKEY = "MSEM_KEY";

//Attributs non visibles
 public static final String GOBJ_KEY_KEY = "gobjKey";
 public static final String GGRP_KEY_KEY = "ggrpKey";

public static final String GOBJ_KEY_COLKEY = "GOBJ_KEY";
public static final String GGRP_KEY_COLKEY = "GGRP_KEY";

	// Relationships
	public static final String SCOL_GROUPE_GRP_KEY = "scolGroupeGrp";

	// Utilities methods
	  public ScolGroupeObjet localInstanceIn(EOEditingContext editingContext) {
	    ScolGroupeObjet localInstance = (ScolGroupeObjet)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static ScolGroupeObjet getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_ScolGroupeObjet.ENTITY_NAME);
		      return (ScolGroupeObjet) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public Long fannKey() {
    return (Long) storedValueForKey("fannKey");
  }

  public void setFannKey(Long value) {
    takeStoredValueForKey(value, "fannKey");
  }

  public String gobjType() {
    return (String) storedValueForKey("gobjType");
  }

  public void setGobjType(String value) {
    takeStoredValueForKey(value, "gobjType");
  }

  public Long mapKey() {
    return (Long) storedValueForKey("mapKey");
  }

  public void setMapKey(Long value) {
    takeStoredValueForKey(value, "mapKey");
  }

  public Long msemKey() {
    return (Long) storedValueForKey("msemKey");
  }

  public void setMsemKey(Long value) {
    takeStoredValueForKey(value, "msemKey");
  }

  public org.cocktail.edtweb.server.metier.ScolGroupeGrp scolGroupeGrp() {
    return (org.cocktail.edtweb.server.metier.ScolGroupeGrp)storedValueForKey("scolGroupeGrp");
  }

  public void setScolGroupeGrpRelationship(org.cocktail.edtweb.server.metier.ScolGroupeGrp value) {
    if (value == null) {
    	org.cocktail.edtweb.server.metier.ScolGroupeGrp oldValue = scolGroupeGrp();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "scolGroupeGrp");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "scolGroupeGrp");
    }
  }
  

  public static ScolGroupeObjet createScolGroupeObjet(EOEditingContext editingContext, Long fannKey
, String gobjType
, org.cocktail.edtweb.server.metier.ScolGroupeGrp scolGroupeGrp) {
    ScolGroupeObjet eo = (ScolGroupeObjet) EOUtilities.createAndInsertInstance(editingContext, _ScolGroupeObjet.ENTITY_NAME);    
		eo.setFannKey(fannKey);
		eo.setGobjType(gobjType);
    eo.setScolGroupeGrpRelationship(scolGroupeGrp);
    return eo;
  }

  public static NSArray fetchAllScolGroupeObjets(EOEditingContext editingContext) {
    return _ScolGroupeObjet.fetchAllScolGroupeObjets(editingContext, null);
  }

  public static NSArray fetchAllScolGroupeObjets(EOEditingContext editingContext, NSArray sortOrderings) {
    return _ScolGroupeObjet.fetchScolGroupeObjets(editingContext, null, sortOrderings);
  }

  public static NSArray fetchScolGroupeObjets(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_ScolGroupeObjet.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static ScolGroupeObjet fetchScolGroupeObjet(EOEditingContext editingContext, String keyName, Object value) {
    return _ScolGroupeObjet.fetchScolGroupeObjet(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ScolGroupeObjet fetchScolGroupeObjet(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _ScolGroupeObjet.fetchScolGroupeObjets(editingContext, qualifier, null);
    ScolGroupeObjet eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (ScolGroupeObjet)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ScolGroupeObjet that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ScolGroupeObjet fetchRequiredScolGroupeObjet(EOEditingContext editingContext, String keyName, Object value) {
    return _ScolGroupeObjet.fetchRequiredScolGroupeObjet(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ScolGroupeObjet fetchRequiredScolGroupeObjet(EOEditingContext editingContext, EOQualifier qualifier) {
    ScolGroupeObjet eoObject = _ScolGroupeObjet.fetchScolGroupeObjet(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ScolGroupeObjet that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ScolGroupeObjet localInstanceIn(EOEditingContext editingContext, ScolGroupeObjet eo) {
    ScolGroupeObjet localInstance = (eo == null) ? null : (ScolGroupeObjet)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
