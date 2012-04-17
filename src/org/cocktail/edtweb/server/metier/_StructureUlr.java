// _StructureUlr.java
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

// DO NOT EDIT.  Make changes to StructureUlr.java instead.
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

public abstract class _StructureUlr extends  EOGenericRecord {
	public static final String ENTITY_NAME = "StructureUlr";
	public static final String ENTITY_TABLE_NAME = "GRHUM.STRUCTURE_ULR";

	// Attributes
 public static final String ENTITY_PRIMARY_KEY = "cStructure";

	public static final String C_STRUCTURE_KEY = "cStructure";
	public static final String C_STRUCTURE_PERE_KEY = "cStructurePere";
	public static final String C_TYPE_STRUCTURE_KEY = "cTypeStructure";
	public static final String GRP_ALIAS_KEY = "grpAlias";
	public static final String LC_STRUCTURE_KEY = "lcStructure";
	public static final String LIBELLE_LONG_KEY = "libelleLong";

	public static final String C_STRUCTURE_COLKEY = "C_STRUCTURE";
	public static final String C_STRUCTURE_PERE_COLKEY = "C_STRUCTURE_PERE";
	public static final String C_TYPE_STRUCTURE_COLKEY = "C_TYPE_STRUCTURE";
	public static final String GRP_ALIAS_COLKEY = "GRP_ALIAS";
	public static final String LC_STRUCTURE_COLKEY = "LC_STRUCTURE";
	public static final String LIBELLE_LONG_COLKEY = "LL_STRUCTURE";

//Attributs non visibles
 public static final String GRP_RESPONSABLE_KEY = "grpResponsable";

public static final String GRP_RESPONSABLE_COLKEY = "GRP_RESPONSABLE";

	// Relationships
	public static final String FILS_KEY = "fils";
	public static final String PERE_KEY = "pere";
	public static final String RESPONSABLE_KEY = "responsable";
	public static final String SECRETARIATS_KEY = "secretariats";

	// Utilities methods
	  public StructureUlr localInstanceIn(EOEditingContext editingContext) {
	    StructureUlr localInstance = (StructureUlr)EOUtilities.localInstanceOfObject(editingContext, this);
	    if (localInstance == null) {
	      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
	    }
	    return localInstance;
	  }

	
			public static StructureUlr getInstance(EOEditingContext editingContext) {
			  EOClassDescription descriptionClass = EOClassDescription.classDescriptionForEntityName(_StructureUlr.ENTITY_NAME);
		      return (StructureUlr) descriptionClass.createInstanceWithEditingContext(editingContext, null);
		}
		
	// Accessors methods
  public String cStructure() {
    return (String) storedValueForKey("cStructure");
  }

  public void setCStructure(String value) {
    takeStoredValueForKey(value, "cStructure");
  }

  public String cStructurePere() {
    return (String) storedValueForKey("cStructurePere");
  }

  public void setCStructurePere(String value) {
    takeStoredValueForKey(value, "cStructurePere");
  }

  public String cTypeStructure() {
    return (String) storedValueForKey("cTypeStructure");
  }

  public void setCTypeStructure(String value) {
    takeStoredValueForKey(value, "cTypeStructure");
  }

  public String grpAlias() {
    return (String) storedValueForKey("grpAlias");
  }

  public void setGrpAlias(String value) {
    takeStoredValueForKey(value, "grpAlias");
  }

  public String lcStructure() {
    return (String) storedValueForKey("lcStructure");
  }

  public void setLcStructure(String value) {
    takeStoredValueForKey(value, "lcStructure");
  }

  public String libelleLong() {
    return (String) storedValueForKey("libelleLong");
  }

  public void setLibelleLong(String value) {
    takeStoredValueForKey(value, "libelleLong");
  }

  public org.cocktail.edtweb.server.metier.StructureUlr pere() {
    return (org.cocktail.edtweb.server.metier.StructureUlr)storedValueForKey("pere");
  }

  public void setPereRelationship(org.cocktail.edtweb.server.metier.StructureUlr value) {
    if (value == null) {
    	org.cocktail.edtweb.server.metier.StructureUlr oldValue = pere();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "pere");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "pere");
    }
  }
  
  public org.cocktail.edtweb.server.metier.IndividuUlr responsable() {
    return (org.cocktail.edtweb.server.metier.IndividuUlr)storedValueForKey("responsable");
  }

  public void setResponsableRelationship(org.cocktail.edtweb.server.metier.IndividuUlr value) {
    if (value == null) {
    	org.cocktail.edtweb.server.metier.IndividuUlr oldValue = responsable();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "responsable");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "responsable");
    }
  }
  
  public NSArray fils() {
    return (NSArray)storedValueForKey("fils");
  }

  public NSArray fils(EOQualifier qualifier) {
    return fils(qualifier, null, false);
  }

  public NSArray fils(EOQualifier qualifier, boolean fetch) {
    return fils(qualifier, null, fetch);
  }

  public NSArray fils(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.edtweb.server.metier.StructureUlr.PERE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.edtweb.server.metier.StructureUlr.fetchStructureUlrs(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = fils();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToFilsRelationship(org.cocktail.edtweb.server.metier.StructureUlr object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "fils");
  }

  public void removeFromFilsRelationship(org.cocktail.edtweb.server.metier.StructureUlr object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "fils");
  }

  public org.cocktail.edtweb.server.metier.StructureUlr createFilsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("StructureUlr");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "fils");
    return (org.cocktail.edtweb.server.metier.StructureUlr) eo;
  }

  public void deleteFilsRelationship(org.cocktail.edtweb.server.metier.StructureUlr object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "fils");
    editingContext().deleteObject(object);
  }

  public void deleteAllFilsRelationships() {
    Enumeration objects = fils().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteFilsRelationship((org.cocktail.edtweb.server.metier.StructureUlr)objects.nextElement());
    }
  }

  public NSArray secretariats() {
    return (NSArray)storedValueForKey("secretariats");
  }

  public NSArray secretariats(EOQualifier qualifier) {
    return secretariats(qualifier, null, false);
  }

  public NSArray secretariats(EOQualifier qualifier, boolean fetch) {
    return secretariats(qualifier, null, fetch);
  }

  public NSArray secretariats(EOQualifier qualifier, NSArray sortOrderings, boolean fetch) {
    NSArray results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(org.cocktail.edtweb.server.metier.Secretariat.STRUCTURE_ULR_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = org.cocktail.edtweb.server.metier.Secretariat.fetchSecretariats(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = secretariats();
      if (qualifier != null) {
        results = (NSArray)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToSecretariatsRelationship(org.cocktail.edtweb.server.metier.Secretariat object) {
    addObjectToBothSidesOfRelationshipWithKey(object, "secretariats");
  }

  public void removeFromSecretariatsRelationship(org.cocktail.edtweb.server.metier.Secretariat object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "secretariats");
  }

  public org.cocktail.edtweb.server.metier.Secretariat createSecretariatsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Secretariat");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "secretariats");
    return (org.cocktail.edtweb.server.metier.Secretariat) eo;
  }

  public void deleteSecretariatsRelationship(org.cocktail.edtweb.server.metier.Secretariat object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "secretariats");
    editingContext().deleteObject(object);
  }

  public void deleteAllSecretariatsRelationships() {
    Enumeration objects = secretariats().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteSecretariatsRelationship((org.cocktail.edtweb.server.metier.Secretariat)objects.nextElement());
    }
  }


  public static StructureUlr createStructureUlr(EOEditingContext editingContext, String cStructure
, String cTypeStructure
) {
    StructureUlr eo = (StructureUlr) EOUtilities.createAndInsertInstance(editingContext, _StructureUlr.ENTITY_NAME);    
		eo.setCStructure(cStructure);
		eo.setCTypeStructure(cTypeStructure);
    return eo;
  }

  public static NSArray fetchAllStructureUlrs(EOEditingContext editingContext) {
    return _StructureUlr.fetchAllStructureUlrs(editingContext, null);
  }

  public static NSArray fetchAllStructureUlrs(EOEditingContext editingContext, NSArray sortOrderings) {
    return _StructureUlr.fetchStructureUlrs(editingContext, null, sortOrderings);
  }

  public static NSArray fetchStructureUlrs(EOEditingContext editingContext, EOQualifier qualifier, NSArray sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_StructureUlr.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray eoObjects = (NSArray)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static StructureUlr fetchStructureUlr(EOEditingContext editingContext, String keyName, Object value) {
    return _StructureUlr.fetchStructureUlr(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static StructureUlr fetchStructureUlr(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray eoObjects = _StructureUlr.fetchStructureUlrs(editingContext, qualifier, null);
    StructureUlr eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (StructureUlr)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one StructureUlr that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static StructureUlr fetchRequiredStructureUlr(EOEditingContext editingContext, String keyName, Object value) {
    return _StructureUlr.fetchRequiredStructureUlr(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static StructureUlr fetchRequiredStructureUlr(EOEditingContext editingContext, EOQualifier qualifier) {
    StructureUlr eoObject = _StructureUlr.fetchStructureUlr(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no StructureUlr that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static StructureUlr localInstanceIn(EOEditingContext editingContext, StructureUlr eo) {
    StructureUlr localInstance = (eo == null) ? null : (StructureUlr)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
