package org.cocktail.edtweb.server.components;
// Generated by the WOLips Core at Thu Sep 23 11:09:41 CEST 2004

import org.cocktail.edtweb.server.Application;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;

import fr.univlr.cri.webapp.LRUserInfo;

public class EDTIndividuChoisi extends EDTIndividu {
	private static final long serialVersionUID = 2109995553799831556L;
	public static String INDIVIDU_CHOISI = "IndividuChoisi";

	public EDTIndividuChoisi(final WOContext context) {
		super(context);
	}

	public void initEDTIndividu() {
		listeResa = new NSMutableArray();
		responder = new Responder();
		dateDebut = new NSTimestamp();
		heureDebut = (new Integer(((Application) application()).heureDebutParDefaut())).toString();
		heureFin = (new Integer(((Application) application()).heureFinParDefaut())).toString();
		final NSMutableArray liste = new NSMutableArray();
		liste.addObject(((LRUserInfo) session().objectForKey(INDIVIDU_CHOISI)).noIndividu());
		session().setObjectForKey(liste, AffichePlanning.LISTE_OBJET);
		session().setObjectForKey(responder, AffichePlanning.RESPONDER);
		setShowPlanning(true);
	}

	public LRUserInfo user() {
		return (LRUserInfo) session().objectForKey(EDTIndividuChoisi.INDIVIDU_CHOISI);
	}

}