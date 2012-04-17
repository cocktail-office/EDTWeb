package org.cocktail.edtweb.server.components;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name: TriDisplayGroup.java

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODisplayGroup;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSSelector;

import fr.univlr.cri.webapp.CRIWebComponent;

public class TriDisplayGroup extends CRIWebComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6878241535699285412L;
	private String fileNameIconUp;
	private String fileNameIconDown;
	private String fileNameIconUpInv;
	private String fileNameIconDownInv;
	private WODisplayGroup DisplayGroup;
	private String sortKey;
	private NSArray sortKeys;
	private boolean actif;
	protected String titleActionUp;
	protected String titleActionDown;
	public String frameworks;

	public TriDisplayGroup(final WOContext context) {
		super(context);
	}

	public String nomIcon() {
		if (getSensTri() == EOSortOrdering.CompareAscending) {
			if (getActif()) {
				return getFileNameIconUp();
			}
			else {
				return getFileNameIconUpInv();
			}
		}
		if (getActif()) {
			return getFileNameIconDown();
		}
		else {
			return getFileNameIconDownInv();
		}
	}

	public WOComponent tri() {
		setActif(true);
		if (getSensTri() == EOSortOrdering.CompareAscending) {
			setSensTri(EOSortOrdering.CompareDescending);
		}
		else {
			setSensTri(EOSortOrdering.CompareAscending);
		}
		final NSMutableArray sort = new NSMutableArray();
		if (getSortKey() != null) {
			sort.addObject(EOSortOrdering.sortOrderingWithKey(getSortKey(), getSensTri()));
		}
		else {
			final NSArray tmp = getSortKeys();
			for (int i = 0; i < tmp.count(); i++) {
				sort.addObject(EOSortOrdering.sortOrderingWithKey((String) tmp.objectAtIndex(i), getSensTri()));
			}

		}
		DisplayGroup.setSortOrderings(sort);
		DisplayGroup.updateDisplayedObjects();
		return null;
	}

	public WODisplayGroup getDisplayGroup() {
		return DisplayGroup;
	}

	public String getFileNameIconDown() {
		if (fileNameIconDown == null) {
			return "triEnableDown.gif";
		}
		else {
			return fileNameIconDown;
		}
	}

	public String getFileNameIconDownInv() {
		if (fileNameIconDownInv == null) {
			return "triDisableDown.gif";
		}
		else {
			return fileNameIconDownInv;
		}
	}

	public String getFileNameIconUp() {
		if (fileNameIconUp == null) {
			return "triEnableUp.gif";
		}
		else {
			return fileNameIconUp;
		}
	}

	public String getFileNameIconUpInv() {
		if (fileNameIconUpInv == null) {
			return "triDisableUp.gif";
		}
		else {
			return fileNameIconUpInv;
		}
	}

	public String getSortKey() {
		return sortKey;
	}

	public void setDisplayGroup(final WODisplayGroup group) {
		DisplayGroup = group;
	}

	public void setFileNameIconDown(final String string) {
		fileNameIconDown = string;
	}

	public void setFileNameIconDownInv(final String string) {
		fileNameIconDownInv = string;
	}

	public void setFileNameIconUp(final String string) {
		fileNameIconUp = string;
	}

	public void setFileNameIconUpInv(final String string) {
		fileNameIconUpInv = string;
	}

	public void setSortKey(final String string) {
		sortKey = string;
	}

	public boolean getActif() {
		return actif;
	}

	public void setActif(final boolean b) {
		actif = b;
	}

	public NSSelector getSensTri() {
		return (NSSelector) session().objectForKey("_Sens_" + getSortKey());
	}

	public void setSensTri(final NSSelector sens) {
		session().setObjectForKey(sens, "_Sens_" + getSortKey());
	}

	public String titleActionUp() {
		return titleActionUp;
	}

	public void setTitleActionUp(final String newTitleActionUp) {
		titleActionUp = newTitleActionUp;
	}

	public String titleActionDown() {
		return titleActionDown;
	}

	public void setTitleActionDown(final String newTitleActionDown) {
		titleActionDown = newTitleActionDown;
	}

	public String title() {
		String estActif = "";
		if (getActif()) {
			estActif = " (actif)";
		}
		if (getSensTri() == null || getSensTri() == EOSortOrdering.CompareAscending) {
			if (titleActionDown != null) {
				return titleActionDown + estActif;
			}
			else {
				return "Tri d\351croissant" + estActif;
			}
		}
		if (titleActionUp != null) {
			return titleActionDown + estActif;
		}
		else {
			return "Tri croissant" + estActif;
		}
	}

	public NSArray getSortKeys() {
		return sortKeys;
	}

	public void setSortKeys(final NSArray array) {
		sortKeys = array;
	}

	public boolean synchronizesVariablesWithBindings() {
		return true;
	}

}
