// _IndividuUlr.java
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

// DO NOT EDIT.  Make changes to IndividuUlr.java instead.
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

public abstract class _IndividuUlr extends  EOGenericRecord {
	public static final String ENTITY_NAME = "IndividuUlr";
	public static final String ENTITY_TABLE_NAME = "GRHUM.INDIVIDU_ULR";

	// Attributes
 public static final String ENTITY_PRIMARY_KEY = "noIndividu";

	public static final String IND_PHOTO_KEY = "indPhoto";
	public static final String NO_INDIVIDU_KEY = "noIndividu";
	public static final String NOM_PATRONYMIQUE_KEY = "nomPatronymique";
	public static final String NOM_USUEL_KEY = "nomUsuel";
	public static final String PERS_ID_KEY = "persId";
	public static final String PRENOM_KEY = "prenom";

	public static final String IND_PHOTO_COLKEY = "IND_PHOTO";
	public static final String NO_INDIVIDU_COLKEY = "NO_INDIVIDU";
	public static final String NOM_PATRONYMIQUE_COLKEY = "NOM_PATRONYMIQUE";
	public static final String NOM_USUEL_COLKEY = "NOM_USUEL";
	public static final String PERS_ID_COLKEY = "PERS_ID";
	public static final String PRENOM_COLKEY = "PRENOM";

//Attributs non visibles


	// Relationships

	// Utilities methods
	  public IndividuUlr localInstanceIn(EOEditingContext editingContext) {
	    IndividuUlr localInstance = (IndividuUlr)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static IndividuUlr getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_IndividuUlr.ENTITY_NAME);
		      return (IndividuUlr) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public String indPhoto() {
    return (String) storedValueForKey("indPhoto");
  }

  public void setIndPhoto(String value) {
    takeStoredValueForKey(value, "indPhoto");
  }

  public Integer noIndividu() {
    return (Integer) storedValueForKey("noIndividu");
  }

  public void setNoIndividu(Integer value) {
    takeStoredValueForKey(value, "noIndividu");
  }

  public String nomPatronymique() {
    return (String) storedValueForKey("nomPatronymique");
  }

  public void setNomPatronymique(String value) {
    takeStoredValueForKey(value, "nomPatronymique");
  }

  public String nomUsuel() {
    return (String) storedValueForKey("nomUsuel");
  }

  public void setNomUsuel(String value) {
    takeStoredValueForKey(value, "nomUsuel");
  }

  public Integer persId() {
    return (Integer) storedValueForKey("persId");
  }

  public void setPersId(Integer value) {
    takeStoredValueForKey(value, "persId");
  }

  public String prenom() {
    return (String) storedValueForKey("prenom");
  }

  public void setPrenom(String value) {
    takeStoredValueForKey(value, "prenom");
  }


  public static IndividuUlr createIndividuUlr(EOEditingContext editingContext, Integer noIndividu
, String nomUsuel
, Integer persId
, String prenom
) {
    IndividuUlr eo = (IndividuUlr) EOUtilities.createAndInsertInstance(editingContext, _IndividuUlr.ENTITY_NAME);    
		eo.setNoIndividu(noIndividu);
		eo.setNomUsuel(nomUsuel);
		eo.setPersId(persId);
		eo.setPrenom(prenom);
    return eo;
  }

  public static NSArray fetchAllIndividuUlrs(EOEditingContext editingContext) {
    return _IndividuUlr.fetchAllIndividuUlrs(editingContext, null);
  }

  public static NSArray fetchAllIndividuUlrs(EOEditingContext editingContext, NSArray sortOrderings) {
    return _IndividuUlr.fetchIndividuUlrs(editingContext, null, sortOrderings);
  }

  public static NSArray fetchIndividuUlrs(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_IndividuUlr.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static IndividuUlr fetchIndividuUlr(EOEditingContext editingContext, String keyName, Object value) {
    return _IndividuUlr.fetchIndividuUlr(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static IndividuUlr fetchIndividuUlr(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _IndividuUlr.fetchIndividuUlrs(editingContext, qualifier, null);
    IndividuUlr eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (IndividuUlr)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one IndividuUlr that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static IndividuUlr fetchRequiredIndividuUlr(EOEditingContext editingContext, String keyName, Object value) {
    return _IndividuUlr.fetchRequiredIndividuUlr(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static IndividuUlr fetchRequiredIndividuUlr(EOEditingContext editingContext, EOQualifier qualifier) {
    IndividuUlr eoObject = _IndividuUlr.fetchIndividuUlr(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no IndividuUlr that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static IndividuUlr localInstanceIn(EOEditingContext editingContext, IndividuUlr eo) {
    IndividuUlr localInstance = (eo == null) ? null : (IndividuUlr)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
