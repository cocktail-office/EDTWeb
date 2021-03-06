package org.cocktail.edtweb.server.components;
// Generated by the WOLips Core at Fri Jul 23 10:59:37 CEST 2004
import org.cocktail.edtweb.server.metier.Adresse;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSMutableDictionary;

import fr.univlr.cri.webapp.CRIWebComponent;
import fr.univlr.cri.webapp.LRUserInfo;
import fr.univlr.cri.webapp.LRUserInfoDB;

public class EDTIndividuInfo extends CRIWebComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6378609233479407657L;
	private Adresse localUser;
	private Number oldUserNoIndividu;
	private NSData oldPhoto;
	public boolean showphoto = false;
	public String userFonction;
	public boolean forPublic = false;

	public EDTIndividuInfo(final WOContext context) {
		super(context);
	}

	private EOEditingContext localEContext() {
		return session().defaultEditingContext();
	}

	public NSData photoIndividu() {
		if (!usePhoto()) {
			return null;
		}
		if (oldUserNoIndividu != null && oldUserNoIndividu == noIndividu()) {
			return oldPhoto;
		}
		if (user() == null || user().noIndividu() == null) {
			return null;
		}
		final EOQualifier arg = EOQualifier.qualifierWithQualifierFormat("noIndividu = %@", new NSArray(user().noIndividu()));
		EOGenericRecord photo = null;
		if (criSession().connectedUserInfo().userStatus() == LRUserInfo.STATUS_ETUDIANT) {
			photo = (EOGenericRecord) criApp.dataBus().fetchArray(localEContext(), "PhotosEtudiants", arg, null, false).lastObject();
		}
		else {
			photo = (EOGenericRecord) criApp.dataBus().fetchArray(localEContext(), "PhotosEmployes", arg, null, false).lastObject();
		}
		if (photo == null) {
			return null;
		}
		oldPhoto = (NSData) photo.valueForKey("datasPhoto");
		oldUserNoIndividu = noIndividu();
		return oldPhoto;
	}

	public Adresse user() {
		if (localUser == null || ((Number) localUser.valueForKey("noIndividu")).intValue() != noIndividu().intValue()) {
			final EOQualifier arg = EOQualifier.qualifierWithQualifierFormat("noIndividu = %@", new NSArray(noIndividu()));
			localUser = (Adresse) criApp.dataBus().fetchObject(localEContext(), "Adresse", arg);
			if (localUser != null && localUser.adrQualite() != null) {
				userFonction = localUser.adrQualite().toUpperCase();
			}
			else {
				userFonction = null;
			}
		}
		return localUser;
	}

	public boolean isEtudiant() {
		return criSession().connectedUserInfo().userStatus() == LRUserInfo.STATUS_ETUDIANT;
	}
	
	public void appendToResponse(final WOResponse arg0, final WOContext arg1) {
		user();
		super.appendToResponse(arg0, arg1);
	}

	private boolean usePhoto() {
		// test pour savoir si on peut utiliser les photos.....(BLOBS)
		final EOQualifier qualifier = EOQualifier.qualifierWithQualifierFormat("paramKey = %@", new NSArray("GRHUM_PHOTO"));
		final EOEnterpriseObject parametre = (EOEnterpriseObject) criApp.dataBus().fetchArray("ul_GrhumParametres", qualifier, null).lastObject();
		if (parametre == null) {
			return false;
		}
		return "OUI".equals(parametre.valueForKey("paramValue"));
	}

	private Number noIndividu() {
		if (session().objectForKey(EDTIndividuChoisi.INDIVIDU_CHOISI) != null
				&& session().objectForKey(EDTIndividuChoisi.INDIVIDU_CHOISI) != NullValue) {
			return ((LRUserInfo) session().objectForKey(EDTIndividuChoisi.INDIVIDU_CHOISI)).noIndividu();
		}
		return criSession().connectedUserInfo().noIndividu();
	}

	public String directActionIcal() {
		final LRUserInfoDB aUser = new LRUserInfoDB(criApp.dataBus());
		aUser.individuForNoIndividu(noIndividu(), true);
		if (aUser.errorCode() != LRUserInfo.ERROR_NONE) {
			return null;
		}
		final NSMutableDictionary dico = new NSMutableDictionary();
		dico.takeValueForKey(aUser.login() + "/ical", "login");
		String url = context().directActionURLForActionNamed("default", dico);
		if (url.indexOf("&wosid") > 0) {
			url = url.substring(0, url.indexOf("&wosid"));
		}
		return url;
	}

	public WOComponent voirPhoto() {
		final NSData dataPhoto = photoIndividu();
		if (dataPhoto != null) {
			session().setObjectForKey(new Boolean(true), "showphoto");
			session().setObjectForKey(dataPhoto, "photo");
		}
		else {
			session().setObjectForKey(new Boolean(false), "showphoto");
		}
		return null;
	}

	public String email() {
		if (session().objectForKey(EDTIndividuChoisi.INDIVIDU_CHOISI) != null
				&& session().objectForKey(EDTIndividuChoisi.INDIVIDU_CHOISI) != NullValue) {
			return ((LRUserInfo) session().objectForKey(EDTIndividuChoisi.INDIVIDU_CHOISI)).email();
		}
		return criSession().connectedUserInfo().email();
	}

	public String mailto() {
		return "mailto:" + email();
	}

}