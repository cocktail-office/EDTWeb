// _ScolGroupeGrp.java
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

// DO NOT EDIT.  Make changes to ScolGroupeGrp.java instead.
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

public abstract class _ScolGroupeGrp extends  EOGenericRecord {
	public static final String ENTITY_NAME = "ScolGroupeGrp";
	public static final String ENTITY_TABLE_NAME = "SCOLARITE.SCOL_GROUPE_GRP";

	// Attributes
 public static final String ENTITY_PRIMARY_KEY = "ggrpKey";

	public static final String GGRP_CODE_KEY = "ggrpCode";
	public static final String GGRP_DATE_DEBUT_KEY = "ggrpDateDebut";
	public static final String GGRP_DATE_FIN_KEY = "ggrpDateFin";
	public static final String GGRP_INT_CAPACITE_KEY = "ggrpIntCapacite";
	public static final String GGRP_KEY_KEY = "ggrpKey";
	public static final String GGRP_LIBELLE_KEY = "ggrpLibelle";
	public static final String GGRP_MAX_CAPACITE_KEY = "ggrpMaxCapacite";
	public static final String GGRP_MAX_TEMOIN_KEY = "ggrpMaxTemoin";

	public static final String GGRP_CODE_COLKEY = "GGRP_CODE";
	public static final String GGRP_DATE_DEBUT_COLKEY = "GGRP_DATE_DEBUT";
	public static final String GGRP_DATE_FIN_COLKEY = "GGRP_DATE_FIN";
	public static final String GGRP_INT_CAPACITE_COLKEY = "GGRP_INT_CAPACITE";
	public static final String GGRP_KEY_COLKEY = "GGRP_KEY";
	public static final String GGRP_LIBELLE_COLKEY = "GGRP_LIBELLE";
	public static final String GGRP_MAX_CAPACITE_COLKEY = "GGRP_MAX_CAPACITE";
	public static final String GGRP_MAX_TEMOIN_COLKEY = "GGRP_MAX_TEMOIN";

//Attributs non visibles
 public static final String GCOL_KEY_KEY = "gcolKey";

public static final String GCOL_KEY_COLKEY = "GCOL_KEY";

	// Relationships
	public static final String SCOL_GROUPE_COLLECTION_KEY = "scolGroupeCollection";
	public static final String SCOL_GROUPE_INCOMPATIBILITES_KEY = "scolGroupeIncompatibilites";
	public static final String SCOL_GROUPE_OBJETS_KEY = "scolGroupeObjets";
	public static final String SCOL_INSCRIPTION_GRPS_KEY = "scolInscriptionGrps";

	// Utilities methods
	  public ScolGroupeGrp localInstanceIn(EOEditingContext editingContext) {
	    ScolGroupeGrp localInstance = (ScolGroupeGrp)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static ScolGroupeGrp getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_ScolGroupeGrp.ENTITY_NAME);
		      return (ScolGroupeGrp) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public String ggrpCode() {
    return (String) storedValueForKey("ggrpCode");
  }

  public void setGgrpCode(String value) {
    takeStoredValueForKey(value, "ggrpCode");
  }

  public Long ggrpDateDebut() {
    return (Long) storedValueForKey("ggrpDateDebut");
  }

  public void setGgrpDateDebut(Long value) {
    takeStoredValueForKey(value, "ggrpDateDebut");
  }

  public Long ggrpDateFin() {
    return (Long) storedValueForKey("ggrpDateFin");
  }

  public void setGgrpDateFin(Long value) {
    takeStoredValueForKey(value, "ggrpDateFin");
  }

  public Long ggrpIntCapacite() {
    return (Long) storedValueForKey("ggrpIntCapacite");
  }

  public void setGgrpIntCapacite(Long value) {
    takeStoredValueForKey(value, "ggrpIntCapacite");
  }

  public Long ggrpKey() {
    return (Long) storedValueForKey("ggrpKey");
  }

  public void setGgrpKey(Long value) {
    takeStoredValueForKey(value, "ggrpKey");
  }

  public String ggrpLibelle() {
    return (String) storedValueForKey("ggrpLibelle");
  }

  public void setGgrpLibelle(String value) {
    takeStoredValueForKey(value, "ggrpLibelle");
  }

  public Long ggrpMaxCapacite() {
    return (Long) storedValueForKey("ggrpMaxCapacite");
  }

  public void setGgrpMaxCapacite(Long value) {
    takeStoredValueForKey(value, "ggrpMaxCapacite");
  }

  public String ggrpMaxTemoin() {
    return (String) storedValueForKey("ggrpMaxTemoin");
  }

  public void setGgrpMaxTemoin(String value) {
    takeStoredValueForKey(value, "ggrpMaxTemoin");
  }

  public EOGenericRecord scolGroupeCollection() {
    return (EOGenericRecord)storedValueForKey("scolGroupeCollection");
  }

  public void setScolGroupeCollectionRelationship(EOGenericRecord value) {
    if (value == null) {
    	EOGenericRecord oldValue = scolGroupeCollection();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "scolGroupeCollection");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "scolGroupeCollection");
    }
  }
  
  public NSArray scolGroupeIncompatibilites() {
    return (NSArray)storedValueForKey("scolGroupeIncompatibilites");
  }

  public NSArray scolGroupeIncompatibilites(EOQualifier qualifier) {
    return scolGroupeIncompatibilites(qualifier, null, false);
  }

  public NSArray scolGroupeIncompatibilites(EOQualifier qualifier, boolean fetch) {
    return scolGroupeIncompatibilites(qualifier, null, fetch);
  }

  public NSArray scolGroupeIncompatibilites(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier("scolGroupeGrp", EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      EOFetchSpecification fetchSpec = new EOFetchSpecification("ScolGroupeIncompatibilite", qualifier, sortOrderings);
      fetchSpec.setIsDeep(true);
      results = (NSArray)editingContext().objectsWithFetchSpecification(fetchSpec);
    }
    else {
      results = scolGroupeIncompatibilites();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToScolGroupeIncompatibilitesRelationship(EOGenericRecord object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "scolGroupeIncompatibilites");
  }

  public void removeFromScolGroupeIncompatibilitesRelationship(EOGenericRecord object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "scolGroupeIncompatibilites");
  }

  public EOGenericRecord createScolGroupeIncompatibilitesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ScolGroupeIncompatibilite");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "scolGroupeIncompatibilites");
    return (EOGenericRecord) eo;
  }

  public void deleteScolGroupeIncompatibilitesRelationship(EOGenericRecord object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "scolGroupeIncompatibilites");
    editingContext().deleteObject(object);
  }

  public void deleteAllScolGroupeIncompatibilitesRelationships() {
    Enumeration objects = scolGroupeIncompatibilites().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteScolGroupeIncompatibilitesRelationship((EOGenericRecord)objects.nextElement());
    }
  }

  public NSArray scolGroupeObjets() {
    return (NSArray)storedValueForKey("scolGroupeObjets");
  }

  public NSArray scolGroupeObjets(EOQualifier qualifier) {
    return scolGroupeObjets(qualifier, null, false);
  }

  public NSArray scolGroupeObjets(EOQualifier qualifier, boolean fetch) {
    return scolGroupeObjets(qualifier, null, fetch);
  }

  public NSArray scolGroupeObjets(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.edtweb.server.metier.ScolGroupeObjet.SCOL_GROUPE_GRP_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.edtweb.server.metier.ScolGroupeObjet.fetchScolGroupeObjets(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = scolGroupeObjets();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToScolGroupeObjetsRelationship(org.cocktail.edtweb.server.metier.ScolGroupeObjet object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "scolGroupeObjets");
  }

  public void removeFromScolGroupeObjetsRelationship(org.cocktail.edtweb.server.metier.ScolGroupeObjet object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "scolGroupeObjets");
  }

  public org.cocktail.edtweb.server.metier.ScolGroupeObjet createScolGroupeObjetsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ScolGroupeObjet");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "scolGroupeObjets");
    return (org.cocktail.edtweb.server.metier.ScolGroupeObjet) eo;
  }

  public void deleteScolGroupeObjetsRelationship(org.cocktail.edtweb.server.metier.ScolGroupeObjet object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "scolGroupeObjets");
    editingContext().deleteObject(object);
  }

  public void deleteAllScolGroupeObjetsRelationships() {
    Enumeration objects = scolGroupeObjets().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteScolGroupeObjetsRelationship((org.cocktail.edtweb.server.metier.ScolGroupeObjet)objects.nextElement());
    }
  }

  public NSArray scolInscriptionGrps() {
    return (NSArray)storedValueForKey("scolInscriptionGrps");
  }

  public NSArray scolInscriptionGrps(EOQualifier qualifier) {
    return scolInscriptionGrps(qualifier, null, false);
  }

  public NSArray scolInscriptionGrps(EOQualifier qualifier, boolean fetch) {
    return scolInscriptionGrps(qualifier, null, fetch);
  }

  public NSArray scolInscriptionGrps(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier("scolGroupeGrp", EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      EOFetchSpecification fetchSpec = new EOFetchSpecification("ScolInscriptionGrp", qualifier, sortOrderings);
      fetchSpec.setIsDeep(true);
      results = (NSArray)editingContext().objectsWithFetchSpecification(fetchSpec);
    }
    else {
      results = scolInscriptionGrps();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToScolInscriptionGrpsRelationship(EOGenericRecord object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "scolInscriptionGrps");
  }

  public void removeFromScolInscriptionGrpsRelationship(EOGenericRecord object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "scolInscriptionGrps");
  }

  public EOGenericRecord createScolInscriptionGrpsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ScolInscriptionGrp");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "scolInscriptionGrps");
    return (EOGenericRecord) eo;
  }

  public void deleteScolInscriptionGrpsRelationship(EOGenericRecord object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "scolInscriptionGrps");
    editingContext().deleteObject(object);
  }

  public void deleteAllScolInscriptionGrpsRelationships() {
    Enumeration objects = scolInscriptionGrps().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteScolInscriptionGrpsRelationship((EOGenericRecord)objects.nextElement());
    }
  }


  public static ScolGroupeGrp createScolGroupeGrp(EOEditingContext editingContext, String ggrpCode
, Long ggrpDateDebut
, Long ggrpKey
, String ggrpMaxTemoin
, EOGenericRecord scolGroupeCollection) {
    ScolGroupeGrp eo = (ScolGroupeGrp) EOUtilities.createAndInsertInstance(editingContext, _ScolGroupeGrp.ENTITY_NAME);    
		eo.setGgrpCode(ggrpCode);
		eo.setGgrpDateDebut(ggrpDateDebut);
		eo.setGgrpKey(ggrpKey);
		eo.setGgrpMaxTemoin(ggrpMaxTemoin);
    eo.setScolGroupeCollectionRelationship(scolGroupeCollection);
    return eo;
  }

  public static NSArray fetchAllScolGroupeGrps(EOEditingContext editingContext) {
    return _ScolGroupeGrp.fetchAllScolGroupeGrps(editingContext, null);
  }

  public static NSArray fetchAllScolGroupeGrps(EOEditingContext editingContext, NSArray sortOrderings) {
    return _ScolGroupeGrp.fetchScolGroupeGrps(editingContext, null, sortOrderings);
  }

  public static NSArray fetchScolGroupeGrps(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_ScolGroupeGrp.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static ScolGroupeGrp fetchScolGroupeGrp(EOEditingContext editingContext, String keyName, Object value) {
    return _ScolGroupeGrp.fetchScolGroupeGrp(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ScolGroupeGrp fetchScolGroupeGrp(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _ScolGroupeGrp.fetchScolGroupeGrps(editingContext, qualifier, null);
    ScolGroupeGrp eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (ScolGroupeGrp)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ScolGroupeGrp that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ScolGroupeGrp fetchRequiredScolGroupeGrp(EOEditingContext editingContext, String keyName, Object value) {
    return _ScolGroupeGrp.fetchRequiredScolGroupeGrp(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static ScolGroupeGrp fetchRequiredScolGroupeGrp(EOEditingContext editingContext, EOQualifier qualifier) {
    ScolGroupeGrp eoObject = _ScolGroupeGrp.fetchScolGroupeGrp(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ScolGroupeGrp that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ScolGroupeGrp localInstanceIn(EOEditingContext editingContext, ScolGroupeGrp eo) {
    ScolGroupeGrp localInstance = (eo == null) ? null : (ScolGroupeGrp)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
