// _ScolMaquetteRepartitionSem.java
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

// DO NOT EDIT.  Make changes to ScolMaquetteRepartitionSem.java instead.
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

public abstract class _ScolMaquetteRepartitionSem extends  EOGenericRecord {
	public static final String ENTITY_NAME = "ScolMaquetteRepartitionSem";
	public static final String ENTITY_TABLE_NAME = "SCOLARITE.SCOL_MAQUETTE_REPARTITION_SEM";

	// Attributes
 public static final String ENTITY_PRIMARY_KEY = "mrsemKey";

	public static final String FANN_KEY_KEY = "fannKey";
	public static final String MPAR_KEY_KEY = "mparKey";
	public static final String MRSEM_KEY_KEY = "mrsemKey";
	public static final String MSEM_KEY_KEY = "msemKey";

	public static final String FANN_KEY_COLKEY = "FANN_KEY";
	public static final String MPAR_KEY_COLKEY = "MPAR_KEY";
	public static final String MRSEM_KEY_COLKEY = "MRSEM_KEY";
	public static final String MSEM_KEY_COLKEY = "MSEM_KEY";

//Attributs non visibles


	// Relationships
	public static final String SCOL_FORMATION_ANNEE_KEY = "scolFormationAnnee";
	public static final String SCOL_MAQUETTE_PARCOURS_KEY = "scolMaquetteParcours";
	public static final String SCOL_MAQUETTE_SEMESTRE_KEY = "scolMaquetteSemestre";

	// Utilities methods
	  public ScolMaquetteRepartitionSem localInstanceIn(EOEditingContext editingContext) {
	    ScolMaquetteRepartitionSem localInstance = (ScolMaquetteRepartitionSem)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static ScolMaquetteRepartitionSem getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_ScolMaquetteRepartitionSem.ENTITY_NAME);
		      return (ScolMaquetteRepartitionSem) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public Long fannKey() {
    return (Long) storedValueForKey("fannKey");
  }

  public void setFannKey(Long value) {
    takeStoredValueForKey(value, "fannKey");
  }

  public Long mparKey() {
    return (Long) storedValueForKey("mparKey");
  }

  public void setMparKey(Long value) {
    takeStoredValueForKey(value, "mparKey");
  }

  public Long mrsemKey() {
    return (Long) storedValueForKey("mrsemKey");
  }

  public void setMrsemKey(Long value) {
    takeStoredValueForKey(value, "mrsemKey");
  }

  public Long msemKey() {
    return (Long) storedValueForKey("msemKey");
  }

  public void setMsemKey(Long value) {
    takeStoredValueForKey(value, "msemKey");
  }

  public org.cocktail.edtweb.server.metier.ScolFormationAnnee scolFormationAnnee() {
    return (org.cocktail.edtweb.server.metier.ScolFormationAnnee)storedValueForKey("scolFormationAnnee");
  }

  public void setScolFormationAnneeRelationship(org.cocktail.edtweb.server.metier.ScolFormationAnnee value) {
    if (value == null) {
    	org.cocktail.edtweb.server.metier.ScolFormationAnnee oldValue = scolFormationAnnee();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "scolFormationAnnee");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "scolFormationAnnee");
    }
  }
  
  public org.cocktail.edtweb.server.metier.ScolMaquetteParcours scolMaquetteParcours() {
    return (org.cocktail.edtweb.server.metier.ScolMaquetteParcours)storedValueForKey("scolMaquetteParcours");
  }

  public void setScolMaquetteParcoursRelationship(org.cocktail.edtweb.server.metier.ScolMaquetteParcours value) {
    if (value == null) {
    	org.cocktail.edtweb.server.metier.ScolMaquetteParcours oldValue = scolMaquetteParcours();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "scolMaquetteParcours");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "scolMaquetteParcours");
    }
  }
  
  public org.cocktail.edtweb.server.metier.ScolMaquetteSemestre scolMaquetteSemestre() {
    return (org.cocktail.edtweb.server.metier.ScolMaquetteSemestre)storedValueForKey("scolMaquetteSemestre");
  }

  public void setScolMaquetteSemestreRelationship(org.cocktail.edtweb.server.metier.ScolMaquetteSemestre value) {
    if (value == null) {
    	org.cocktail.edtweb.server.metier.ScolMaquetteSemestre oldValue = scolMaquetteSemestre();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "scolMaquetteSemestre");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "scolMaquetteSemestre");
    }
  }
  

  public static ScolMaquetteRepartitionSem createScolMaquetteRepartitionSem(EOEditingContext editingContext, Long fannKey
, Long mparKey
, Long mrsemKey
, Long msemKey
, org.cocktail.edtweb.server.metier.ScolFormationAnnee scolFormationAnnee, org.cocktail.edtweb.server.metier.ScolMaquetteParcours scolMaquetteParcours, org.cocktail.edtweb.server.metier.ScolMaquetteSemestre scolMaquetteSemestre) {
    ScolMaquetteRepartitionSem eo = (ScolMaquetteRepartitionSem) EOUtilities.createAndInsertInstance(editingContext, _ScolMaquetteRepartitionSem.ENTITY_NAME);    
		eo.setFannKey(fannKey);
		eo.setMparKey(mparKey);
		eo.setMrsemKey(mrsemKey);
		eo.setMsemKey(msemKey);
    eo.setScolFormationAnneeRelationship(scolFormationAnnee);
    eo.setScolMaquetteParcoursRelationship(scolMaquetteParcours);
    eo.setScolMaquetteSemestreRelationship(scolMaquetteSemestre);
    return eo;
  }

  public static NSArray fetchAllScolMaquetteRepartitionSems(EOEditingContext editingContext) {
    return _ScolMaquetteRepartitionSem.fetchAllScolMaquetteRepartitionSems(editingContext, null);
  }

  public static NSArray fetchAllScolMaquetteRepartitionSems(EOEditingContext editingContext, NSArray sortOrderings) {
    return _ScolMaquetteRepartitionSem.fetchScolMaquetteRepartitionSems(editingContext, null, sortOrderings);
  }

  public static NSArray fetchScolMaquetteRepartitionSems(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_ScolMaquetteRepartitionSem.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static ScolMaquetteRepartitionSem fetchScolMaquetteRepartitionSem(EOEditingContext editingContext, String keyName, Object value) {
    return _ScolMaquetteRepartitionSem.fetchScolMaquetteRepartitionSem(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ScolMaquetteRepartitionSem fetchScolMaquetteRepartitionSem(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _ScolMaquetteRepartitionSem.fetchScolMaquetteRepartitionSems(editingContext, qualifier, null);
    ScolMaquetteRepartitionSem eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (ScolMaquetteRepartitionSem)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ScolMaquetteRepartitionSem that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ScolMaquetteRepartitionSem fetchRequiredScolMaquetteRepartitionSem(EOEditingContext editingContext, String keyName, Object value) {
    return _ScolMaquetteRepartitionSem.fetchRequiredScolMaquetteRepartitionSem(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ScolMaquetteRepartitionSem fetchRequiredScolMaquetteRepartitionSem(EOEditingContext editingContext, EOQualifier qualifier) {
    ScolMaquetteRepartitionSem eoObject = _ScolMaquetteRepartitionSem.fetchScolMaquetteRepartitionSem(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ScolMaquetteRepartitionSem that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ScolMaquetteRepartitionSem localInstanceIn(EOEditingContext editingContext, ScolMaquetteRepartitionSem eo) {
    ScolMaquetteRepartitionSem localInstance = (eo == null) ? null : (ScolMaquetteRepartitionSem)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
