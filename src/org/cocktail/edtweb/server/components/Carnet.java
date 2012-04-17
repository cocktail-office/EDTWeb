package org.cocktail.edtweb.server.components;
//
// Carnet.java: Class file for WO Component 'Carnet'
// Project EDTLudo
//
// Created by ludo on 27/07/05
//

import java.util.Calendar;

import org.cocktail.edtweb.server.AffichePlanningResponder;
import org.cocktail.edtweb.server.EDTObject;
import org.cocktail.edtweb.server.Jour;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;

public class Carnet extends WOComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8341681939308141348L;
	public NSTimestamp dateDebut;
	public String heureDebut;
	public String heureFin;

	protected Jour dateDuJour;

	public AffichePlanningResponder responder;

	/** @TypeInfo java.lang.String */
	protected NSMutableArray liste;
	protected String ele;

	public Carnet(final WOContext context) {
		super(context);
		liste = new NSMutableArray();
		responder = (AffichePlanningResponder) session().objectForKey("responder");
	}

	public boolean synchronizesVariablesWithBindings() {
		return false;
	}

	protected void initialListe() {
		dateDebut = (NSTimestamp) session().objectForKey("dateDebut");
		dateDuJour = new Jour(dateDebut);
		final NSTimestamp debut = dateDebut;
		final NSTimestamp fin = debut.timestampByAddingGregorianUnits(1, 0, 0, 23, 59, 0);
		liste = new NSMutableArray();
		if (responder != null) {
			final NSArray tmp = responder.getListeResaObjet(debut, fin);
			for (int i = 0; i < tmp.count(); i++) {
				final EDTObject obj = (EDTObject) tmp.objectAtIndex(i);
				final Jour d = new Jour(obj.dateDebutGC());
				final Jour f = new Jour(obj.dateFinGC());
				if ((!f.before(new Jour(dateDuJour.debutDeJournnee()))) && (!d.after(new Jour(fin)))) {
					liste.addObject("<b>Du</b> " + (new Jour(obj.dateDebutDTZ())).toStringComplet() + " <b>au</b> "
							+ (new Jour(obj.dateFinDTZ())).toStringComplet() + "<br><b>Sujet:</b> " + obj.sujet(false));
				}
			}
		}
	}

	public void appendToResponse(final WOResponse arg0, final WOContext arg1) {
		initialListe();
		super.appendToResponse(arg0, arg1);
	}

	public WOComponent goPrev() {
		dateDuJour.add(Calendar.MONTH, -1);
		session().setObjectForKey((dateDuJour).toNSTimestamp(), "dateDebut");
		session().setObjectForKey(new Boolean(true), "InitChoixDate");
		return null;

	}

	public WOComponent goNext() {
		dateDuJour.add(Calendar.MONTH, 1);
		session().setObjectForKey((dateDuJour).toNSTimestamp(), "dateDebut");
		session().setObjectForKey(new Boolean(true), "InitChoixDate");
		return null;
	}

	/** @TypeInfo java.lang.String */
	public NSMutableArray liste() {
		return liste;
	}

	public String ele() {
		return ele;
	}

	public void setEle(final String newEle) {
		ele = newEle;
	}

}