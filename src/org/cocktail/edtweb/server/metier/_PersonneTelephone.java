// _PersonneTelephone.java
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

// DO NOT EDIT.  Make changes to PersonneTelephone.java instead.
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

public abstract class _PersonneTelephone extends  EOGenericRecord {
	public static final String ENTITY_NAME = "PersonneTelephone";
	public static final String ENTITY_TABLE_NAME = "GRHUM.PERSONNE_TELEPHONE";

	// Attributes

	public static final String C_STRUCTURE_KEY = "cStructure";
	public static final String D_CREATION_KEY = "dCreation";
	public static final String D_DEB_VAL_KEY = "dDebVal";
	public static final String D_FIN_VAL_KEY = "dFinVal";
	public static final String D_MODIFICATION_KEY = "dModification";
	public static final String INDICATIF_KEY = "indicatif";
	public static final String NO_TELEPHONE_KEY = "noTelephone";
	public static final String TYPE_NO_KEY = "typeNo";
	public static final String TYPE_TEL_KEY = "typeTel";

	public static final String C_STRUCTURE_COLKEY = "C_STRUCTURE";
	public static final String D_CREATION_COLKEY = "D_CREATION";
	public static final String D_DEB_VAL_COLKEY = "D_DEB_VAL";
	public static final String D_FIN_VAL_COLKEY = "D_FIN_VAL";
	public static final String D_MODIFICATION_COLKEY = "D_MODIFICATION";
	public static final String INDICATIF_COLKEY = "INDICATIF";
	public static final String NO_TELEPHONE_COLKEY = "NO_TELEPHONE";
	public static final String TYPE_NO_COLKEY = "TYPE_NO";
	public static final String TYPE_TEL_COLKEY = "TYPE_TEL";

//Attributs non visibles
 public static final String PERS_ID_KEY = "persId";

public static final String PERS_ID_COLKEY = "PERS_ID";

	// Relationships

	// Utilities methods
	  public PersonneTelephone localInstanceIn(EOEditingContext editingContext) {
	    PersonneTelephone localInstance = (PersonneTelephone)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static PersonneTelephone getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_PersonneTelephone.ENTITY_NAME);
		      return (PersonneTelephone) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public String cStructure() {
    return (String) storedValueForKey("cStructure");
  }

  public void setCStructure(String value) {
    takeStoredValueForKey(value, "cStructure");
  }

  public NSTimestamp dCreation() {
    return (NSTimestamp) storedValueForKey("dCreation");
  }

  public void setDCreation(NSTimestamp value) {
    takeStoredValueForKey(value, "dCreation");
  }

  public NSTimestamp dDebVal() {
    return (NSTimestamp) storedValueForKey("dDebVal");
  }

  public void setDDebVal(NSTimestamp value) {
    takeStoredValueForKey(value, "dDebVal");
  }

  public NSTimestamp dFinVal() {
    return (NSTimestamp) storedValueForKey("dFinVal");
  }

  public void setDFinVal(NSTimestamp value) {
    takeStoredValueForKey(value, "dFinVal");
  }

  public NSTimestamp dModification() {
    return (NSTimestamp) storedValueForKey("dModification");
  }

  public void setDModification(NSTimestamp value) {
    takeStoredValueForKey(value, "dModification");
  }

  public Double indicatif() {
    return (Double) storedValueForKey("indicatif");
  }

  public void setIndicatif(Double value) {
    takeStoredValueForKey(value, "indicatif");
  }

  public String noTelephone() {
    return (String) storedValueForKey("noTelephone");
  }

  public void setNoTelephone(String value) {
    takeStoredValueForKey(value, "noTelephone");
  }

  public String typeNo() {
    return (String) storedValueForKey("typeNo");
  }

  public void setTypeNo(String value) {
    takeStoredValueForKey(value, "typeNo");
  }

  public String typeTel() {
    return (String) storedValueForKey("typeTel");
  }

  public void setTypeTel(String value) {
    takeStoredValueForKey(value, "typeTel");
  }


  public static PersonneTelephone createPersonneTelephone(EOEditingContext editingContext, NSTimestamp dCreation
, NSTimestamp dModification
, String noTelephone
, String typeNo
, String typeTel
) {
    PersonneTelephone eo = (PersonneTelephone) EOUtilities.createAndInsertInstance(editingContext, _PersonneTelephone.ENTITY_NAME);    
		eo.setDCreation(dCreation);
		eo.setDModification(dModification);
		eo.setNoTelephone(noTelephone);
		eo.setTypeNo(typeNo);
		eo.setTypeTel(typeTel);
    return eo;
  }

  public static NSArray fetchAllPersonneTelephones(EOEditingContext editingContext) {
    return _PersonneTelephone.fetchAllPersonneTelephones(editingContext, null);
  }

  public static NSArray fetchAllPersonneTelephones(EOEditingContext editingContext, NSArray sortOrderings) {
    return _PersonneTelephone.fetchPersonneTelephones(editingContext, null, sortOrderings);
  }

  public static NSArray fetchPersonneTelephones(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_PersonneTelephone.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static PersonneTelephone fetchPersonneTelephone(EOEditingContext editingContext, String keyName, Object value) {
    return _PersonneTelephone.fetchPersonneTelephone(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static PersonneTelephone fetchPersonneTelephone(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _PersonneTelephone.fetchPersonneTelephones(editingContext, qualifier, null);
    PersonneTelephone eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (PersonneTelephone)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one PersonneTelephone that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static PersonneTelephone fetchRequiredPersonneTelephone(EOEditingContext editingContext, String keyName, Object value) {
    return _PersonneTelephone.fetchRequiredPersonneTelephone(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static PersonneTelephone fetchRequiredPersonneTelephone(EOEditingContext editingContext, EOQualifier qualifier) {
    PersonneTelephone eoObject = _PersonneTelephone.fetchPersonneTelephone(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no PersonneTelephone that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static PersonneTelephone localInstanceIn(EOEditingContext editingContext, PersonneTelephone eo) {
    PersonneTelephone localInstance = (eo == null) ? null : (PersonneTelephone)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
