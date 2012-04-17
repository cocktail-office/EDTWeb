// _ScolMaquetteParcours.java
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

// DO NOT EDIT.  Make changes to ScolMaquetteParcours.java instead.
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

public abstract class _ScolMaquetteParcours extends  EOGenericRecord {
	public static final String ENTITY_NAME = "ScolMaquetteParcours";
	public static final String ENTITY_TABLE_NAME = "SCOLARITE.SCOL_MAQUETTE_PARCOURS";

	// Attributes
 public static final String ENTITY_PRIMARY_KEY = "mparKey";

	public static final String FSPN_KEY_KEY = "fspnKey";
	public static final String FVOC_CODE_KEY = "fvocCode";
	public static final String MPAR_ABREVIATION_KEY = "mparAbreviation";
	public static final String MPAR_CODE_KEY = "mparCode";
	public static final String MPAR_KEY_KEY = "mparKey";
	public static final String MPAR_LIBELLE_KEY = "mparLibelle";
	public static final String MPAR_VALIDITE_KEY = "mparValidite";

	public static final String FSPN_KEY_COLKEY = "FSPN_KEY";
	public static final String FVOC_CODE_COLKEY = "FVOC_CODE";
	public static final String MPAR_ABREVIATION_COLKEY = "MPAR_ABREVIATION";
	public static final String MPAR_CODE_COLKEY = "MPAR_CODE";
	public static final String MPAR_KEY_COLKEY = "MPAR_KEY";
	public static final String MPAR_LIBELLE_COLKEY = "MPAR_LIBELLE";
	public static final String MPAR_VALIDITE_COLKEY = "MPAR_VALIDITE";

//Attributs non visibles


	// Relationships
	public static final String SCOL_FORMATION_SPECIALISATION_KEY = "scolFormationSpecialisation";
	public static final String SCOL_MAQUETTE_REPARTITION_SEMS_KEY = "scolMaquetteRepartitionSems";

	// Utilities methods
	  public ScolMaquetteParcours localInstanceIn(EOEditingContext editingContext) {
	    ScolMaquetteParcours localInstance = (ScolMaquetteParcours)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static ScolMaquetteParcours getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_ScolMaquetteParcours.ENTITY_NAME);
		      return (ScolMaquetteParcours) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public Long fspnKey() {
    return (Long) storedValueForKey("fspnKey");
  }

  public void setFspnKey(Long value) {
    takeStoredValueForKey(value, "fspnKey");
  }

  public String fvocCode() {
    return (String) storedValueForKey("fvocCode");
  }

  public void setFvocCode(String value) {
    takeStoredValueForKey(value, "fvocCode");
  }

  public String mparAbreviation() {
    return (String) storedValueForKey("mparAbreviation");
  }

  public void setMparAbreviation(String value) {
    takeStoredValueForKey(value, "mparAbreviation");
  }

  public String mparCode() {
    return (String) storedValueForKey("mparCode");
  }

  public void setMparCode(String value) {
    takeStoredValueForKey(value, "mparCode");
  }

  public Long mparKey() {
    return (Long) storedValueForKey("mparKey");
  }

  public void setMparKey(Long value) {
    takeStoredValueForKey(value, "mparKey");
  }

  public String mparLibelle() {
    return (String) storedValueForKey("mparLibelle");
  }

  public void setMparLibelle(String value) {
    takeStoredValueForKey(value, "mparLibelle");
  }

  public String mparValidite() {
    return (String) storedValueForKey("mparValidite");
  }

  public void setMparValidite(String value) {
    takeStoredValueForKey(value, "mparValidite");
  }

  public EOGenericRecord scolFormationSpecialisation() {
    return (EOGenericRecord)storedValueForKey("scolFormationSpecialisation");
  }

  public void setScolFormationSpecialisationRelationship(EOGenericRecord value) {
    if (value == null) {
    	EOGenericRecord oldValue = scolFormationSpecialisation();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "scolFormationSpecialisation");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "scolFormationSpecialisation");
    }
  }
  
  public NSArray scolMaquetteRepartitionSems() {
    return (NSArray)storedValueForKey("scolMaquetteRepartitionSems");
  }

  public NSArray scolMaquetteRepartitionSems(EOQualifier qualifier) {
    return scolMaquetteRepartitionSems(qualifier, null, false);
  }

  public NSArray scolMaquetteRepartitionSems(EOQualifier qualifier, boolean fetch) {
    return scolMaquetteRepartitionSems(qualifier, null, fetch);
  }

  public NSArray scolMaquetteRepartitionSems(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.edtweb.server.metier.ScolMaquetteRepartitionSem.SCOL_MAQUETTE_PARCOURS_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.edtweb.server.metier.ScolMaquetteRepartitionSem.fetchScolMaquetteRepartitionSems(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = scolMaquetteRepartitionSems();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToScolMaquetteRepartitionSemsRelationship(org.cocktail.edtweb.server.metier.ScolMaquetteRepartitionSem object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "scolMaquetteRepartitionSems");
  }

  public void removeFromScolMaquetteRepartitionSemsRelationship(org.cocktail.edtweb.server.metier.ScolMaquetteRepartitionSem object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "scolMaquetteRepartitionSems");
  }

  public org.cocktail.edtweb.server.metier.ScolMaquetteRepartitionSem createScolMaquetteRepartitionSemsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ScolMaquetteRepartitionSem");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "scolMaquetteRepartitionSems");
    return (org.cocktail.edtweb.server.metier.ScolMaquetteRepartitionSem) eo;
  }

  public void deleteScolMaquetteRepartitionSemsRelationship(org.cocktail.edtweb.server.metier.ScolMaquetteRepartitionSem object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "scolMaquetteRepartitionSems");
    editingContext().deleteObject(object);
  }

  public void deleteAllScolMaquetteRepartitionSemsRelationships() {
    Enumeration objects = scolMaquetteRepartitionSems().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteScolMaquetteRepartitionSemsRelationship((org.cocktail.edtweb.server.metier.ScolMaquetteRepartitionSem)objects.nextElement());
    }
  }


  public static ScolMaquetteParcours createScolMaquetteParcours(EOEditingContext editingContext, Long fspnKey
, String fvocCode
, String mparCode
, Long mparKey
, String mparLibelle
, String mparValidite
, EOGenericRecord scolFormationSpecialisation) {
    ScolMaquetteParcours eo = (ScolMaquetteParcours) EOUtilities.createAndInsertInstance(editingContext, _ScolMaquetteParcours.ENTITY_NAME);    
		eo.setFspnKey(fspnKey);
		eo.setFvocCode(fvocCode);
		eo.setMparCode(mparCode);
		eo.setMparKey(mparKey);
		eo.setMparLibelle(mparLibelle);
		eo.setMparValidite(mparValidite);
    eo.setScolFormationSpecialisationRelationship(scolFormationSpecialisation);
    return eo;
  }

  public static NSArray fetchAllScolMaquetteParcourses(EOEditingContext editingContext) {
    return _ScolMaquetteParcours.fetchAllScolMaquetteParcourses(editingContext, null);
  }

  public static NSArray fetchAllScolMaquetteParcourses(EOEditingContext editingContext, NSArray sortOrderings) {
    return _ScolMaquetteParcours.fetchScolMaquetteParcourses(editingContext, null, sortOrderings);
  }

  public static NSArray fetchScolMaquetteParcourses(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_ScolMaquetteParcours.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static ScolMaquetteParcours fetchScolMaquetteParcours(EOEditingContext editingContext, String keyName, Object value) {
    return _ScolMaquetteParcours.fetchScolMaquetteParcours(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ScolMaquetteParcours fetchScolMaquetteParcours(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _ScolMaquetteParcours.fetchScolMaquetteParcourses(editingContext, qualifier, null);
    ScolMaquetteParcours eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (ScolMaquetteParcours)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ScolMaquetteParcours that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ScolMaquetteParcours fetchRequiredScolMaquetteParcours(EOEditingContext editingContext, String keyName, Object value) {
    return _ScolMaquetteParcours.fetchRequiredScolMaquetteParcours(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ScolMaquetteParcours fetchRequiredScolMaquetteParcours(EOEditingContext editingContext, EOQualifier qualifier) {
    ScolMaquetteParcours eoObject = _ScolMaquetteParcours.fetchScolMaquetteParcours(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ScolMaquetteParcours that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ScolMaquetteParcours localInstanceIn(EOEditingContext editingContext, ScolMaquetteParcours eo) {
    ScolMaquetteParcours localInstance = (eo == null) ? null : (ScolMaquetteParcours)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
