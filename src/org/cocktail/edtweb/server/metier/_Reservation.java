// _Reservation.java
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

// DO NOT EDIT.  Make changes to Reservation.java instead.
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
import com.webobjects.foundation.NSTimestamp;

public abstract class _Reservation extends  EOGenericRecord {
	public static final String ENTITY_NAME = "Reservation";
	public static final String ENTITY_TABLE_NAME = "EDTSCOL.RESERVATION";

	// Attributes
 public static final String ENTITY_PRIMARY_KEY = "resaKey";

	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String NO_INDIVIDU_CLIENT_KEY = "noIndividuClient";
	public static final String RESA_COMMENTAIRE_KEY = "resaCommentaire";
	public static final String RESA_KEY_READ_ONLY_KEY = "resaKeyReadOnly";
	public static final String TLOC_CODE_KEY = "tlocCode";

	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String NO_INDIVIDU_CLIENT_COLKEY = "NO_INDIVIDU_CLIENT";
	public static final String RESA_COMMENTAIRE_COLKEY = "RESA_COMMENTAIRE";
	public static final String RESA_KEY_READ_ONLY_COLKEY = "resaKeyReadOnly";
	public static final String TLOC_CODE_COLKEY = "TLOC_CODE";

//Attributs non visibles
 public static final String RESA_KEY_KEY = "resaKey";

public static final String RESA_KEY_COLKEY = "RESA_KEY";

	// Relationships
	public static final String INDIVIDU_ULR_KEY = "individuUlr";
	public static final String PERIODICITES_KEY = "periodicites";
	public static final String RESERVATION_OBJETS_KEY = "reservationObjets";
	public static final String TOS_OCCUPANT_KEY = "tosOccupant";
	public static final String TOS_RESA_EXAM_KEY = "tosResaExam";
	public static final String TOS_RESA_SALLES_KEY = "tosResaSalles";
	public static final String TOS_RESERVATION_AP_KEY = "tosReservationAp";

	// Utilities methods
	  public Reservation localInstanceIn(EOEditingContext editingContext) {
	    Reservation localInstance = (Reservation)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static Reservation getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_Reservation.ENTITY_NAME);
		      return (Reservation) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public NSTimestamp dCreation() {
    return (NSTimestamp) storedValueForKey("dCreation");
  }

  public void setDCreation(NSTimestamp value) {
    takeStoredValueForKey(value, "dCreation");
  }

  public NSTimestamp dModification() {
    return (NSTimestamp) storedValueForKey("dModification");
  }

  public void setDModification(NSTimestamp value) {
    takeStoredValueForKey(value, "dModification");
  }

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

  public Integer resaKeyReadOnly() {
    return (Integer) storedValueForKey("resaKeyReadOnly");
  }

  public void setResaKeyReadOnly(Integer value) {
    takeStoredValueForKey(value, "resaKeyReadOnly");
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
  
  public NSArray periodicites() {
    return (NSArray)storedValueForKey("periodicites");
  }

  public NSArray periodicites(EOQualifier qualifier) {
    return periodicites(qualifier, null, false);
  }

  public NSArray periodicites(EOQualifier qualifier, boolean fetch) {
    return periodicites(qualifier, null, fetch);
  }

  public NSArray periodicites(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.edtweb.server.metier.Periodicite.RESERVATION_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.edtweb.server.metier.Periodicite.fetchPeriodicites(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = periodicites();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToPeriodicitesRelationship(org.cocktail.edtweb.server.metier.Periodicite object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "periodicites");
  }

  public void removeFromPeriodicitesRelationship(org.cocktail.edtweb.server.metier.Periodicite object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "periodicites");
  }

  public org.cocktail.edtweb.server.metier.Periodicite createPeriodicitesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Periodicite");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "periodicites");
    return (org.cocktail.edtweb.server.metier.Periodicite) eo;
  }

  public void deletePeriodicitesRelationship(org.cocktail.edtweb.server.metier.Periodicite object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "periodicites");
    editingContext().deleteObject(object);
  }

  public void deleteAllPeriodicitesRelationships() {
    Enumeration objects = periodicites().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deletePeriodicitesRelationship((org.cocktail.edtweb.server.metier.Periodicite)objects.nextElement());
    }
  }

  public NSArray reservationObjets() {
    return (NSArray)storedValueForKey("reservationObjets");
  }

  public NSArray reservationObjets(EOQualifier qualifier) {
    return reservationObjets(qualifier, null, false);
  }

  public NSArray reservationObjets(EOQualifier qualifier, boolean fetch) {
    return reservationObjets(qualifier, null, fetch);
  }

  public NSArray reservationObjets(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.edtweb.server.metier.ReservationObjet.RESERVATION_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.edtweb.server.metier.ReservationObjet.fetchReservationObjets(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = reservationObjets();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToReservationObjetsRelationship(org.cocktail.edtweb.server.metier.ReservationObjet object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "reservationObjets");
  }

  public void removeFromReservationObjetsRelationship(org.cocktail.edtweb.server.metier.ReservationObjet object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "reservationObjets");
  }

  public org.cocktail.edtweb.server.metier.ReservationObjet createReservationObjetsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ReservationObjet");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "reservationObjets");
    return (org.cocktail.edtweb.server.metier.ReservationObjet) eo;
  }

  public void deleteReservationObjetsRelationship(org.cocktail.edtweb.server.metier.ReservationObjet object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "reservationObjets");
    editingContext().deleteObject(object);
  }

  public void deleteAllReservationObjetsRelationships() {
    Enumeration objects = reservationObjets().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteReservationObjetsRelationship((org.cocktail.edtweb.server.metier.ReservationObjet)objects.nextElement());
    }
  }

  public NSArray tosOccupant() {
    return (NSArray)storedValueForKey("tosOccupant");
  }

  public NSArray tosOccupant(EOQualifier qualifier) {
    return tosOccupant(qualifier, null, false);
  }

  public NSArray tosOccupant(EOQualifier qualifier, boolean fetch) {
    return tosOccupant(qualifier, null, fetch);
  }

  public NSArray tosOccupant(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.edtweb.server.metier.Occupant.RESERVATION_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.edtweb.server.metier.Occupant.fetchOccupants(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = tosOccupant();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToTosOccupantRelationship(org.cocktail.edtweb.server.metier.Occupant object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosOccupant");
  }

  public void removeFromTosOccupantRelationship(org.cocktail.edtweb.server.metier.Occupant object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosOccupant");
  }

  public org.cocktail.edtweb.server.metier.Occupant createTosOccupantRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Occupant");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosOccupant");
    return (org.cocktail.edtweb.server.metier.Occupant) eo;
  }

  public void deleteTosOccupantRelationship(org.cocktail.edtweb.server.metier.Occupant object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosOccupant");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosOccupantRelationships() {
    Enumeration objects = tosOccupant().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosOccupantRelationship((org.cocktail.edtweb.server.metier.Occupant)objects.nextElement());
    }
  }

  public NSArray tosResaExam() {
    return (NSArray)storedValueForKey("tosResaExam");
  }

  public NSArray tosResaExam(EOQualifier qualifier) {
    return tosResaExam(qualifier, null);
  }

  public NSArray tosResaExam(EOQualifier qualifier, NSArray sortOrderings) {
    NSArray results;
      results = tosResaExam();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToTosResaExamRelationship(EOGenericRecord object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosResaExam");
  }

  public void removeFromTosResaExamRelationship(EOGenericRecord object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosResaExam");
  }

  public EOGenericRecord createTosResaExamRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ResaExamen");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosResaExam");
    return (EOGenericRecord) eo;
  }

  public void deleteTosResaExamRelationship(EOGenericRecord object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosResaExam");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosResaExamRelationships() {
    Enumeration objects = tosResaExam().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosResaExamRelationship((EOGenericRecord)objects.nextElement());
    }
  }

  public NSArray tosResaSalles() {
    return (NSArray)storedValueForKey("tosResaSalles");
  }

  public NSArray tosResaSalles(EOQualifier qualifier) {
    return tosResaSalles(qualifier, null);
  }

  public NSArray tosResaSalles(EOQualifier qualifier, NSArray sortOrderings) {
    NSArray results;
      results = tosResaSalles();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToTosResaSallesRelationship(org.cocktail.edtweb.server.metier.ResaSalles object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosResaSalles");
  }

  public void removeFromTosResaSallesRelationship(org.cocktail.edtweb.server.metier.ResaSalles object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosResaSalles");
  }

  public org.cocktail.edtweb.server.metier.ResaSalles createTosResaSallesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ResaSalles");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosResaSalles");
    return (org.cocktail.edtweb.server.metier.ResaSalles) eo;
  }

  public void deleteTosResaSallesRelationship(org.cocktail.edtweb.server.metier.ResaSalles object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosResaSalles");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosResaSallesRelationships() {
    Enumeration objects = tosResaSalles().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosResaSallesRelationship((org.cocktail.edtweb.server.metier.ResaSalles)objects.nextElement());
    }
  }

  public NSArray tosReservationAp() {
    return (NSArray)storedValueForKey("tosReservationAp");
  }

  public NSArray tosReservationAp(EOQualifier qualifier) {
    return tosReservationAp(qualifier, null);
  }

  public NSArray tosReservationAp(EOQualifier qualifier, NSArray sortOrderings) {
    NSArray results;
      results = tosReservationAp();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToTosReservationApRelationship(EOGenericRecord object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "tosReservationAp");
  }

  public void removeFromTosReservationApRelationship(EOGenericRecord object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosReservationAp");
  }

  public EOGenericRecord createTosReservationApRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("ReservationAp");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "tosReservationAp");
    return (EOGenericRecord) eo;
  }

  public void deleteTosReservationApRelationship(EOGenericRecord object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "tosReservationAp");
    editingContext().deleteObject(object);
  }

  public void deleteAllTosReservationApRelationships() {
    Enumeration objects = tosReservationAp().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTosReservationApRelationship((EOGenericRecord)objects.nextElement());
    }
  }


  public static Reservation createReservation(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, Integer noIndividuClient
, String tlocCode
, org.cocktail.edtweb.server.metier.IndividuUlr individuUlr) {
    Reservation eo = (Reservation) EOUtilities.createAndInsertInstance(editingContext, _Reservation.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setNoIndividuClient(noIndividuClient);
		eo.setTlocCode(tlocCode);
    eo.setIndividuUlrRelationship(individuUlr);
    return eo;
  }

  public static NSArray fetchAllReservations(EOEditingContext editingContext) {
    return _Reservation.fetchAllReservations(editingContext, null);
  }

  public static NSArray fetchAllReservations(EOEditingContext editingContext, NSArray sortOrderings) {
    return _Reservation.fetchReservations(editingContext, null, sortOrderings);
  }

  public static NSArray fetchReservations(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Reservation.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Reservation fetchReservation(EOEditingContext editingContext, String keyName, Object value) {
    return _Reservation.fetchReservation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Reservation fetchReservation(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _Reservation.fetchReservations(editingContext, qualifier, null);
    Reservation eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Reservation)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Reservation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Reservation fetchRequiredReservation(EOEditingContext editingContext, String keyName, Object value) {
    return _Reservation.fetchRequiredReservation(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Reservation fetchRequiredReservation(EOEditingContext editingContext, EOQualifier qualifier) {
    Reservation eoObject = _Reservation.fetchReservation(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Reservation that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Reservation localInstanceIn(EOEditingContext editingContext, Reservation eo) {
    Reservation localInstance = (eo == null) ? null : (Reservation)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
