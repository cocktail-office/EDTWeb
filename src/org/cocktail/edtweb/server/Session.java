package org.cocktail.edtweb.server;

import java.util.TimeZone;

import org.cocktail.edtweb.server.components.Main;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WORequest;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSTimeZone;

import fr.univlr.cri.webapp.CRIWebSession;
import fr.univlr.cri.webext.CRIMenuItemSet;
import fr.univlr.cri.webext.CRIMenuListener;

public class Session extends CRIWebSession {
	private static final long serialVersionUID = 7937291874113409491L;
	private int affichageEnCours;
	private NSMutableDictionary couleurResa;
	private NSMutableDictionary objetResa;

	// gestion du menu : 1 seule instance
	private CRIMenuListener edtMenuLister;
	private CRIMenuItemSet edtMenuItemSet;

	private Main mainPage;

	public CRIMenuListener getEdtMenuLister() {
		return edtMenuLister;
	}

	public void setEdtMenuLister(final CRIMenuListener value) {
		edtMenuLister = value;
	}

	public CRIMenuItemSet getEdtMenuItemSet() {
		return edtMenuItemSet;
	}

	public void setEdtMenuItemSet(final CRIMenuItemSet value) {
		edtMenuItemSet = value;
	}

	public Session() {
		super();
		setCouleurResa(new NSMutableDictionary());
		setObjetResa(new NSMutableDictionary());
		String timeZone = criApp.config().stringForKey("DEFAULT_TIME_ZONE");
		if (timeZone != null) {
			NSTimeZone.setDefaultTimeZone(NSTimeZone.timeZoneWithName(timeZone, false));
			TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
		}
	}

	public Main getMainPage() {
		return mainPage;
	}

	public void setMainPage(final Main mainPage) {
		this.mainPage = mainPage;
	}

	public void setAffichageEnCours(final int affichageEnCours) {
		this.affichageEnCours = affichageEnCours;
	}

	public int getAffichageEnCours() {
		return affichageEnCours;
	}

	public void setCouleurResa(final NSMutableDictionary couleurResa) {
		this.couleurResa = couleurResa;
	}

	public NSMutableDictionary getCouleurResa() {
		return couleurResa;
	}

	public void setObjetResa(final NSMutableDictionary objetResa) {
		this.objetResa = objetResa;
	}

	public NSMutableDictionary getObjetResa() {
		return objetResa;
	}

	// public void appendToResponse(WOResponse aResponse, WOContext aContext) {
	// aResponse.setContentEncoding(_NSUtilities.UTF8StringEncoding);
	// aResponse.setHeader("text/html; charset=UTF-8; encoding=UTF-8", "content-type");
	// super.appendToResponse(aResponse, aContext);
	// }

	// fonctionne dans l'ent mais pas en standalone (accents foirés...)
	public void takeValuesFromRequest(WORequest aRequest, WOContext aContext) {
		// aRequest.setDefaultFormValueEncoding(_NSUtilities.UTF8StringEncoding);
		aRequest.setFormValueEncodingDetectionEnabled(true);
		super.takeValuesFromRequest(aRequest, aContext);
	}

}