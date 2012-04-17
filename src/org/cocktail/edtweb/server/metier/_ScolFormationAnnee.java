// _ScolFormationAnnee.java
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

// DO NOT EDIT.  Make changes to ScolFormationAnnee.java instead.
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

public abstract class _ScolFormationAnnee extends  EOGenericRecord {
	public static final String ENTITY_NAME = "ScolFormationAnnee";
	public static final String ENTITY_TABLE_NAME = "SCOLARITE.SCOL_FORMATION_ANNEE";

	// Attributes
 public static final String ENTITY_PRIMARY_KEY = "fannKey";

	public static final String FANN_COURANTE_KEY = "fannCourante";
	public static final String FANN_DEBUT_KEY = "fannDebut";
	public static final String FANN_FIN_KEY = "fannFin";
	public static final String FANN_KEY_KEY = "fannKey";

	public static final String FANN_COURANTE_COLKEY = "FANN_COURANTE";
	public static final String FANN_DEBUT_COLKEY = "FANN_DEBUT";
	public static final String FANN_FIN_COLKEY = "FANN_FIN";
	public static final String FANN_KEY_COLKEY = "FANN_KEY";

//Attributs non visibles


	// Relationships

	// Utilities methods
	  public ScolFormationAnnee localInstanceIn(EOEditingContext editingContext) {
	    ScolFormationAnnee localInstance = (ScolFormationAnnee)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static ScolFormationAnnee getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_ScolFormationAnnee.ENTITY_NAME);
		      return (ScolFormationAnnee) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public String fannCourante() {
    return (String) storedValueForKey("fannCourante");
  }

  public void setFannCourante(String value) {
    takeStoredValueForKey(value, "fannCourante");
  }

  public Integer fannDebut() {
    return (Integer) storedValueForKey("fannDebut");
  }

  public void setFannDebut(Integer value) {
    takeStoredValueForKey(value, "fannDebut");
  }

  public Integer fannFin() {
    return (Integer) storedValueForKey("fannFin");
  }

  public void setFannFin(Integer value) {
    takeStoredValueForKey(value, "fannFin");
  }

  public Integer fannKey() {
    return (Integer) storedValueForKey("fannKey");
  }

  public void setFannKey(Integer value) {
    takeStoredValueForKey(value, "fannKey");
  }


  public static ScolFormationAnnee createScolFormationAnnee(EOEditingContext editingContext, String fannCourante
, Integer fannDebut
, Integer fannFin
, Integer fannKey
) {
    ScolFormationAnnee eo = (ScolFormationAnnee) EOUtilities.createAndInsertInstance(editingContext, _ScolFormationAnnee.ENTITY_NAME);    
		eo.setFannCourante(fannCourante);
		eo.setFannDebut(fannDebut);
		eo.setFannFin(fannFin);
		eo.setFannKey(fannKey);
    return eo;
  }

  public static NSArray fetchAllScolFormationAnnees(EOEditingContext editingContext) {
    return _ScolFormationAnnee.fetchAllScolFormationAnnees(editingContext, null);
  }

  public static NSArray fetchAllScolFormationAnnees(EOEditingContext editingContext, NSArray sortOrderings) {
    return _ScolFormationAnnee.fetchScolFormationAnnees(editingContext, null, sortOrderings);
  }

  public static NSArray fetchScolFormationAnnees(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_ScolFormationAnnee.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static ScolFormationAnnee fetchScolFormationAnnee(EOEditingContext editingContext, String keyName, Object value) {
    return _ScolFormationAnnee.fetchScolFormationAnnee(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ScolFormationAnnee fetchScolFormationAnnee(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _ScolFormationAnnee.fetchScolFormationAnnees(editingContext, qualifier, null);
    ScolFormationAnnee eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (ScolFormationAnnee)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ScolFormationAnnee that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ScolFormationAnnee fetchRequiredScolFormationAnnee(EOEditingContext editingContext, String keyName, Object value) {
    return _ScolFormationAnnee.fetchRequiredScolFormationAnnee(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ScolFormationAnnee fetchRequiredScolFormationAnnee(EOEditingContext editingContext, EOQualifier qualifier) {
    ScolFormationAnnee eoObject = _ScolFormationAnnee.fetchScolFormationAnnee(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ScolFormationAnnee that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ScolFormationAnnee localInstanceIn(EOEditingContext editingContext, ScolFormationAnnee eo) {
    ScolFormationAnnee localInstance = (eo == null) ? null : (ScolFormationAnnee)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
