// _Periodicite.java
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

// DO NOT EDIT.  Make changes to Periodicite.java instead.
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

public abstract class _Periodicite extends  EOGenericRecord {
	public static final String ENTITY_NAME = "Periodicite";
	public static final String ENTITY_TABLE_NAME = "EDTSCOL.PERIODICITE";

	// Attributes
 public static final String ENTITY_PRIMARY_KEY = "perKey";

	public static final String DATE_DEB_KEY = "dateDeb";
	public static final String DATE_FIN_KEY = "dateFin";
	public static final String HCOMP_KEY = "hcomp";
	public static final String PER_KEY_READ_ONLY_KEY = "perKeyReadOnly";

	public static final String DATE_DEB_COLKEY = "DATE_DEB";
	public static final String DATE_FIN_COLKEY = "DATE_FIN";
	public static final String HCOMP_COLKEY = "HCOMP";
	public static final String PER_KEY_READ_ONLY_COLKEY = "perKeyReadOnly";

//Attributs non visibles
 public static final String PER_KEY_KEY = "perKey";
 public static final String RESA_KEY_KEY = "resaKey";

public static final String PER_KEY_COLKEY = "PER_KEY";
public static final String RESA_KEY_COLKEY = "RESA_KEY";

	// Relationships
	public static final String RESERVATION_KEY = "reservation";
	public static final String V_RESERVATION_OBJET_KEY = "vReservationObjet";

	// Utilities methods
	  public Periodicite localInstanceIn(EOEditingContext editingContext) {
	    Periodicite localInstance = (Periodicite)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static Periodicite getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_Periodicite.ENTITY_NAME);
		      return (Periodicite) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public NSTimestamp dateDeb() {
    return (NSTimestamp) storedValueForKey("dateDeb");
  }

  public void setDateDeb(NSTimestamp value) {
    takeStoredValueForKey(value, "dateDeb");
  }

  public NSTimestamp dateFin() {
    return (NSTimestamp) storedValueForKey("dateFin");
  }

  public void setDateFin(NSTimestamp value) {
    takeStoredValueForKey(value, "dateFin");
  }

  public Integer hcomp() {
    return (Integer) storedValueForKey("hcomp");
  }

  public void setHcomp(Integer value) {
    takeStoredValueForKey(value, "hcomp");
  }

  public Integer perKeyReadOnly() {
    return (Integer) storedValueForKey("perKeyReadOnly");
  }

  public void setPerKeyReadOnly(Integer value) {
    takeStoredValueForKey(value, "perKeyReadOnly");
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
  
  public org.cocktail.edtweb.server.metier.VReservationObjet vReservationObjet() {
    return (org.cocktail.edtweb.server.metier.VReservationObjet)storedValueForKey("vReservationObjet");
  }

  public void setVReservationObjetRelationship(org.cocktail.edtweb.server.metier.VReservationObjet value) {
    if (value == null) {
    	org.cocktail.edtweb.server.metier.VReservationObjet oldValue = vReservationObjet();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "vReservationObjet");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "vReservationObjet");
    }
  }
  

  public static Periodicite createPeriodicite(EOEditingContext editingContext, NSTimestamp dateDeb
, NSTimestamp dateFin
, Integer hcomp
, org.cocktail.edtweb.server.metier.Reservation reservation) {
    Periodicite eo = (Periodicite) EOUtilities.createAndInsertInstance(editingContext, _Periodicite.ENTITY_NAME);    
		eo.setDateDeb(dateDeb);
		eo.setDateFin(dateFin);
		eo.setHcomp(hcomp);
    eo.setReservationRelationship(reservation);
    return eo;
  }

  public static NSArray fetchAllPeriodicites(EOEditingContext editingContext) {
    return _Periodicite.fetchAllPeriodicites(editingContext, null);
  }

  public static NSArray fetchAllPeriodicites(EOEditingContext editingContext, NSArray sortOrderings) {
    return _Periodicite.fetchPeriodicites(editingContext, null, sortOrderings);
  }

  public static NSArray fetchPeriodicites(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Periodicite.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Periodicite fetchPeriodicite(EOEditingContext editingContext, String keyName, Object value) {
    return _Periodicite.fetchPeriodicite(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Periodicite fetchPeriodicite(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _Periodicite.fetchPeriodicites(editingContext, qualifier, null);
    Periodicite eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Periodicite)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Periodicite that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Periodicite fetchRequiredPeriodicite(EOEditingContext editingContext, String keyName, Object value) {
    return _Periodicite.fetchRequiredPeriodicite(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Periodicite fetchRequiredPeriodicite(EOEditingContext editingContext, EOQualifier qualifier) {
    Periodicite eoObject = _Periodicite.fetchPeriodicite(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Periodicite that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Periodicite localInstanceIn(EOEditingContext editingContext, Periodicite eo) {
    Periodicite localInstance = (eo == null) ? null : (Periodicite)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
