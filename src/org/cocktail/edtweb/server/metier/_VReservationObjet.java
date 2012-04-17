// _VReservationObjet.java
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

// DO NOT EDIT.  Make changes to VReservationObjet.java instead.
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

public abstract class _VReservationObjet extends  EOGenericRecord {
	public static final String ENTITY_NAME = "VReservationObjet";
	public static final String ENTITY_TABLE_NAME = "EDTSCOL.V_RESERVATION_OBJET";

	// Attributes
 public static final String ENTITY_PRIMARY_KEY = "resaKey";

	public static final String NO_INDIVIDU_CLIENT_KEY = "noIndividuClient";
	public static final String RESA_COMMENTAIRE_KEY = "resaCommentaire";
	public static final String RESA_ETAT_KEY = "resaEtat";
	public static final String RESA_KEY_KEY = "resaKey";
	public static final String RO_KEY_KEY = "roKey";
	public static final String TLOC_CODE_KEY = "tlocCode";

	public static final String NO_INDIVIDU_CLIENT_COLKEY = "NO_INDIVIDU_CLIENT";
	public static final String RESA_COMMENTAIRE_COLKEY = "RESA_COMMENTAIRE";
	public static final String RESA_ETAT_COLKEY = "RESA_ETAT";
	public static final String RESA_KEY_COLKEY = "RESA_KEY";
	public static final String RO_KEY_COLKEY = "RO_KEY";
	public static final String TLOC_CODE_COLKEY = "TLOC_CODE";

//Attributs non visibles


	// Relationships
	public static final String INDIVIDU_ULR_KEY = "individuUlr";
	public static final String OCCUPANTS_KEY = "occupants";
	public static final String RESA_OBJET_KEY = "resaObjet";
	public static final String RESERVATION_KEY = "reservation";

	// Utilities methods
	  public VReservationObjet localInstanceIn(EOEditingContext editingContext) {
	    VReservationObjet localInstance = (VReservationObjet)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static VReservationObjet getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_VReservationObjet.ENTITY_NAME);
		      return (VReservationObjet) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public Integer noIndividuClient() {
    return (Integer) storedValueForKey("noIndividuClient");
  }

  public void setNoIndividuClient(Integer value) {
    takeStoredValueForKey(value, "noIndividuClient");
  }

  public String resaCommentaire() {
    return (String) storedValueForKey("resaCommentaire");
  }

  public void setResaCommentaire(String value) {
    takeStoredValueForKey(value, "resaCommentaire");
  }

  public String resaEtat() {
    return (String) storedValueForKey("resaEtat");
  }

  public void setResaEtat(String value) {
    takeStoredValueForKey(value, "resaEtat");
  }

  public Integer resaKey() {
    return (Integer) storedValueForKey("resaKey");
  }

  public void setResaKey(Integer value) {
    takeStoredValueForKey(value, "resaKey");
  }

  public Integer roKey() {
    return (Integer) storedValueForKey("roKey");
  }

  public void setRoKey(Integer value) {
    takeStoredValueForKey(value, "roKey");
  }

  public String tlocCode() {
    return (String) storedValueForKey("tlocCode");
  }

  public void setTlocCode(String value) {
    takeStoredValueForKey(value, "tlocCode");
  }

  public org.cocktail.edtweb.server.metier.IndividuUlr individuUlr() {
    return (org.cocktail.edtweb.server.metier.IndividuUlr)storedValueForKey("individuUlr");
  }

  public void setIndividuUlrRelationship(org.cocktail.edtweb.server.metier.IndividuUlr value) {
    if (value == null) {
    	org.cocktail.edtweb.server.metier.IndividuUlr oldValue = individuUlr();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "individuUlr");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "individuUlr");
    }
  }
  
  public org.cocktail.edtweb.server.metier.ResaObjet resaObjet() {
    return (org.cocktail.edtweb.server.metier.ResaObjet)storedValueForKey("resaObjet");
  }

  public void setResaObjetRelationship(org.cocktail.edtweb.server.metier.ResaObjet value) {
    if (value == null) {
    	org.cocktail.edtweb.server.metier.ResaObjet oldValue = resaObjet();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "resaObjet");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "resaObjet");
    }
  }
  
  public org.cocktail.edtweb.server.metier.Reservation reservation() {
    return (org.cocktail.edtweb.server.metier.Reservation)storedValueForKey("reservation");
  }

  public void setReservationRelationship(org.cocktail.edtweb.server.metier.Reservation value) {
    if (value == null) {
    	org.cocktail.edtweb.server.metier.Reservation oldValue = reservation();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "reservation");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "reservation");
    }
  }
  
  public NSArray occupants() {
    return (NSArray)storedValueForKey("occupants");
  }

  public NSArray occupants(EOQualifier qualifier) {
    return occupants(qualifier, null);
  }

  public NSArray occupants(EOQualifier qualifier, NSArray sortOrderings) {
    NSArray results;
      results = occupants();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToOccupantsRelationship(org.cocktail.edtweb.server.metier.Occupant object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "occupants");
  }

  public void removeFromOccupantsRelationship(org.cocktail.edtweb.server.metier.Occupant object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "occupants");
  }

  public org.cocktail.edtweb.server.metier.Occupant createOccupantsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Occupant");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "occupants");
    return (org.cocktail.edtweb.server.metier.Occupant) eo;
  }

  public void deleteOccupantsRelationship(org.cocktail.edtweb.server.metier.Occupant object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "occupants");
    editingContext().deleteObject(object);
  }

  public void deleteAllOccupantsRelationships() {
    Enumeration objects = occupants().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteOccupantsRelationship((org.cocktail.edtweb.server.metier.Occupant)objects.nextElement());
    }
  }


  public static VReservationObjet createVReservationObjet(EOEditingContext editingContext, Integer noIndividuClient
, String resaEtat
, Integer resaKey
, Integer roKey
, String tlocCode
, org.cocktail.edtweb.server.metier.IndividuUlr individuUlr, org.cocktail.edtweb.server.metier.ResaObjet resaObjet, org.cocktail.edtweb.server.metier.Reservation reservation) {
    VReservationObjet eo = (VReservationObjet) EOUtilities.createAndInsertInstance(editingContext, _VReservationObjet.ENTITY_NAME);    
		eo.setNoIndividuClient(noIndividuClient);
		eo.setResaEtat(resaEtat);
		eo.setResaKey(resaKey);
		eo.setRoKey(roKey);
		eo.setTlocCode(tlocCode);
    eo.setIndividuUlrRelationship(individuUlr);
    eo.setResaObjetRelationship(resaObjet);
    eo.setReservationRelationship(reservation);
    return eo;
  }

  public static NSArray fetchAllVReservationObjets(EOEditingContext editingContext) {
    return _VReservationObjet.fetchAllVReservationObjets(editingContext, null);
  }

  public static NSArray fetchAllVReservationObjets(EOEditingContext editingContext, NSArray sortOrderings) {
    return _VReservationObjet.fetchVReservationObjets(editingContext, null, sortOrderings);
  }

  public static NSArray fetchVReservationObjets(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_VReservationObjet.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static VReservationObjet fetchVReservationObjet(EOEditingContext editingContext, String keyName, Object value) {
    return _VReservationObjet.fetchVReservationObjet(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static VReservationObjet fetchVReservationObjet(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _VReservationObjet.fetchVReservationObjets(editingContext, qualifier, null);
    VReservationObjet eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (VReservationObjet)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one VReservationObjet that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static VReservationObjet fetchRequiredVReservationObjet(EOEditingContext editingContext, String keyName, Object value) {
    return _VReservationObjet.fetchRequiredVReservationObjet(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static VReservationObjet fetchRequiredVReservationObjet(EOEditingContext editingContext, EOQualifier qualifier) {
    VReservationObjet eoObject = _VReservationObjet.fetchVReservationObjet(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no VReservationObjet that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static VReservationObjet localInstanceIn(EOEditingContext editingContext, VReservationObjet eo) {
    VReservationObjet localInstance = (eo == null) ? null : (VReservationObjet)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
