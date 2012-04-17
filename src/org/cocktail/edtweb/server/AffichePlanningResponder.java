package org.cocktail.edtweb.server;

import com.webobjects.appserver.WOComponent;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;

public interface AffichePlanningResponder {

	public WOComponent goReservation();

	public String nbObjetDispoForDayAndTime(Heure aDay);

	public int initPlanning(NSMutableArray listeObjetCle);

	public int initPlanning(String typeCode);

	public String couleurTR();

	public void initListeResa(NSTimestamp debut, NSTimestamp fin);

	public String title1();

	public String title2();

	public EDTObject getObjetForIndex(int index);

	public NSArray getListeResaObjet(NSTimestamp debut, NSTimestamp fin);

	public String motifReservation(int maxCar);
	
}