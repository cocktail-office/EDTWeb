package org.cocktail.edtweb.server.components;
// package se.oops.internetcalendar;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import org.cocktail.edtweb.server.CalendarEvent;
import org.cocktail.edtweb.server.SimpleEvent;

import com.webobjects.appserver.WOApplication;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimeZone;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSTimestampFormatter;

/**
 * PublishCalendarPage is a WebObjects component for dynamically generated iCalendar documents.
 * <p>
 * The response created by PublishCalendarPage is an iCalendar document (.ics) containing the events added to PublishCalendarPage by the
 * application (see {@link #addEvent(CalendarEvent) addEvent}). An iCalendar-aware application, such as Apple's iCal, can subscribe to such
 * a calendar, provided that the page has a fixed URL (either is the "Main" page, or a direct action serves the page).
 * <p>
 * Events added to a PublishCalendarPage is objects of any class that implements {@link CalendarEvent the CalendarEvent interface}. Existing
 * classes (for example EOCustomObject subclasses), that correspond to calendar events, can easily be modified to implement
 * {@link CalendarEvent CalendarEvent} and thus be added directly to PublishCalendarPage. If existing classes does not directly correspond
 * to calendar events, create events from business data (or some algorithm) using either the included {@link SimpleEvent SimpleEvent class},
 * a subclass of {@link SimpleEvent SimpleEvent}, or any other class implementing {@link CalendarEvent the CalendarEvent interface}.
 * 
 * @author Johan Carlberg
 * @version 1.0, 2002-09-30
 */

public class PublishCalendarPage extends WOComponent {
	private static final long serialVersionUID = -8847866011260479249L;
	protected String calendarName;
	protected String calendarTimeZone;
	protected final int maxLineLength = 75;
	private String prodId;

	/** @TypeInfo se.oops.CalendarEvent */
	protected NSMutableArray events;
	public CalendarEvent event;
	protected NSTimestamp eventTimestamp;
	protected NSTimestampFormatter dateTimeFormatter;
	protected NSTimestampFormatter dateFormatter;
	protected NSTimestampFormatter utcDateTimeFormatter;
	protected NSTimestampFormatter timeZoneFormatter;

	/**
	 * Standard constructor for WOComponent subclasses.
	 * 
	 * @param context
	 *            context of a transaction
	 * @see WOComponent#pageWithName(String)
	 * @see WOApplication#pageWithName(String, WOContext)
	 */
	public PublishCalendarPage(WOContext context) {
		super(context);

		events = new NSMutableArray();
		calendarTimeZone = new NSTimestampFormatter("%Z").format(new NSTimestamp());
		dateTimeFormatter = new NSTimestampFormatter("%Y%m%dT%H%M%S");
		dateTimeFormatter.setDefaultFormatTimeZone(NSTimeZone.localTimeZone());
		dateFormatter = new NSTimestampFormatter("%Y%m%d");
		dateFormatter.setDefaultFormatTimeZone(NSTimeZone.localTimeZone());
		utcDateTimeFormatter = new NSTimestampFormatter("%Y%m%dT%H%M%SZ");
		utcDateTimeFormatter.setDefaultFormatTimeZone(NSTimeZone.timeZoneWithName("UTC", false));
		timeZoneFormatter = new NSTimestampFormatter("%Z");
		timeZoneFormatter.setDefaultFormatTimeZone(NSTimeZone.systemTimeZone());// modifier
	}

	/**
	 * Modifies content encoding to UTF8, and content type to text/calendar.
	 * 
	 * @param aResponse
	 *            the HTTP response that an application returns to a Web server to complete a cycle of the request-response loop
	 * @param aContext
	 *            context of a transaction
	 */
	public void appendToResponse(final WOResponse aResponse, final WOContext aContext) {
		eventTimestamp = new NSTimestamp();
		aResponse.setContentEncoding("UTF8");
		super.appendToResponse(aResponse, aContext);
		aResponse.setHeader("text/calendar", "content-type");
		aResponse.setHeader("attachement; filename=agenda.ics", "Content-Disposition");// ajouter 060210
		try {
			aResponse.setContent(new NSData(traiterQuot(new String(aResponse.content().bytes(), "UTF-8")).getBytes("UTF-8")));
		}
		catch (final java.io.UnsupportedEncodingException exception) {
			// If encoding is not supported, content of response is left unmodified
			// (although exceptions will be thrown elsewhere if UTF-8 is unsupported).
		}
	}

	/**
	 * Adds an event to the calendar.
	 * 
	 * @param event
	 *            the event to be included in the calendar
	 * @see CalendarEvent
	 * @see #addEventsFromArray(NSArray)
	 */
	public void addEvent(final CalendarEvent event) {
		events.addObject(event);
	}

	/**
	 * Adds an array of events to the calendar.
	 * 
	 * @param eventsArray
	 *            the events to be included in the calendar
	 * @see CalendarEvent
	 * @see #addEvent(CalendarEvent)
	 */
	public void addEventsFromArray(final NSArray eventsArray) {
		events.addObjectsFromArray(eventsArray);
	}

	/**
	 * Removes a previously added event from the calendar.
	 * 
	 * @param event
	 *            the event to be removed from the calendar
	 * @see CalendarEvent
	 * @see #removeEventsInArray(NSArray)
	 */
	public void removeEvent(final CalendarEvent event) {
		events.removeObject(event);
	}

	/**
	 * Removes an array of previously added events from the calendar.
	 * 
	 * @param eventsArray
	 *            the events to be removed from the calendar
	 * @see CalendarEvent
	 * @see #removeEvent(CalendarEvent)
	 */
	public void removeEventsInArray(final NSArray eventsArray) {
		events.removeObjectsInArray(eventsArray);
	}

	/** @TypeInfo se.oops.CalendarEvent */
	public NSMutableArray events() {
		return events;
	}

	/**
	 * @return name of the calendar
	 * @see #setCalendarName(String)
	 */
	public String calendarName() {
		return calendarName;
	}

	/**
	 * @return name of the calendar, backslash escaped for inclusion in iCalendar document.
	 * @see #calendarName
	 */
	public String escapedCalendarName() {
		return escapedString(calendarName);
	}

	/**
	 * Sets the name of the calendar.
	 * 
	 * @param value
	 *            name of the calendar
	 * @see #calendarName
	 */
	public void setCalendarName(final String value) {
		calendarName = value;
	}

	/**
	 * @return originating time zone for the calendar (name of the system default time zone, if not changed by
	 *         {@link #setCalendarTimeZone(String) setCalendarTimeZone}
	 * @see #setCalendarTimeZone(String)
	 */
	public String calendarTimeZone() {
		return calendarTimeZone;
	}

	/**
	 * @return time zone name of the calendar, backslash escaped for inclusion in iCalendar document.
	 * @see #calendarTimeZone
	 */
	public String escapedCalendarTimeZone() {
		return escapedString(calendarTimeZone);
	}

	/**
	 * Sets the name of the time zone for the calendar.
	 * 
	 * @param value
	 *            name of the time zone
	 * @see #calendarTimeZone
	 */
	public void setCalendarTimeZone(final String value) {
		calendarTimeZone = value;
	}

	/**
	 * @return status of the current event, backslash escaped for inclusion in iCalendar document.
	 * @see CalendarEvent#status
	 */
	public String escapedEventStatus() {
		return escapedString(event.status());
	}

	/**
	 * @return summary of the current event, backslash escaped for inclusion in iCalendar document.
	 * @see CalendarEvent#summary
	 */
	public String escapedEventSummary() {
		return escapedString(event.summary());
	}

	/**
	 * @return unique id of the current event, backslash escaped for inclusion in iCalendar document.
	 * @see CalendarEvent#uniqueId
	 */
	public String escapedEventUniqueId() {
		return escapedString(event.uniqueId());
	}

	/**
	 * @return timestamp of the current event. This will always be the current time, as this is the time the event is converted to an
	 *         iCalendar event.
	 */
	public NSTimestamp eventTimestamp() {
		return eventTimestamp;
	}

	/**
	 * @return the recurring rule frequency, as one of "YEARLY", "MONTHLY", "WEEKLY", "DAILY", "HOURLY", "MINUTELY", "SECONDLY" depending on
	 *         the value returned by {@link CalendarEvent#repeatFrequency repeatFrequency}.
	 * @see CalendarEvent#repeatFrequency
	 */
	public String eventRepeatFrequency() {
		switch (event.repeatFrequency()) {
		case Calendar.YEAR:
			return "YEARLY";
		case Calendar.MONTH:
			return "MONTHLY";
		case Calendar.WEEK_OF_YEAR:
			return "WEEKLY";
		case Calendar.DAY_OF_MONTH:
			return "DAILY";
		case Calendar.HOUR_OF_DAY:
			return "HOURLY";
		case Calendar.MINUTE:
			return "MINUTELY";
		case Calendar.SECOND:
			return "SECONDLY";
		default:
			return null;
		}
	}

	/**
	 * @return month number of a repeating event for use in the "BYMONTH" parameter.
	 */
	public Number eventRepeatMonth() {
		final GregorianCalendar calendarDate = new GregorianCalendar();

		calendarDate.setTime(event.startTime());
		return new Integer(calendarDate.get(Calendar.MONTH) + 1);
	}

	/**
	 * @return day of week of a repeating event for use in the "BYDAY" parameter.
	 */
	public String eventRepeatDayOfWeekString() {
		String byDay = "";

		if (event.repeatDayOfWeekInMonth() != 0) {
			byDay = new Integer(event.repeatDayOfWeekInMonth()).toString();
		}
		switch (event.repeatDayOfWeek()) {
		case Calendar.SUNDAY:
			byDay += "SU";
			break;
		case Calendar.MONDAY:
			byDay += "MO";
			break;
		case Calendar.TUESDAY:
			byDay += "TU";
			break;
		case Calendar.WEDNESDAY:
			byDay += "WE";
			break;
		case Calendar.THURSDAY:
			byDay += "TH";
			break;
		case Calendar.FRIDAY:
			byDay += "FR";
			break;
		case Calendar.SATURDAY:
			byDay += "SA";
			break;
		}

		return byDay;
	}

	/**
	 * @return days of month of a repeating event for use in the "BYMONTHDAY" parameter.
	 */
	public String eventRepeatDaysOfMonthString() {
		return event.repeatDaysOfMonth().componentsJoinedByString(",");
	}

	/**
	 * @return formatter for date/time. Will format date/times as "20021003T191234", specified in the local time zone.
	 */
	public NSTimestampFormatter dateTimeFormatter() {
		return dateTimeFormatter;
	}

	/**
	 * @return formatter for dates. Will format dates as "20021003", specified in the local time zone.
	 */
	public NSTimestampFormatter dateFormatter() {
		return dateFormatter;
	}

	/**
	 * @return formatter for date/time stamps. Will format date/times as "20021003T171234Z", specified in UTC (GMT).
	 */
	public NSTimestampFormatter utcDateTimeFormatter() {
		return utcDateTimeFormatter;
	}

	/**
	 * @return formatter for time zone. Will format date/times as only the name of the local time zone.
	 */
	public NSTimestampFormatter timeZoneFormatter() {
		return timeZoneFormatter;
	}

	/**
	 * @return backslash escaped text string, with special characters replaced with its backslash escaped equivalent.
	 */
	protected String escapedString(final String string) {
		final StringBuffer escapedString = new StringBuffer(string);
		int index;

		for (index = escapedString.length() - 1; index >= 0; index -= 1) {
			switch (escapedString.charAt(index)) {
			case '"':
				break;
			case ';':
				break;
			case ':':
				escapedString.setCharAt(index, '\u003A');
				break;
			case '\\':
			case ',':
				escapedString.insert(index, '\\');
				break;

			case '\n':
				escapedString.setCharAt(index, 'n');
				escapedString.insert(index, '\\');
				break;
			}
		}

		return escapedString.toString();
	}

	/**
	 * Folds lines that are longer than the maximum allowed 75 characters.
	 * 
	 * @param content
	 *            unfolded iCalendar content
	 * @return folded content, with no line longer than 75 characters
	 */
	protected String foldLongLinesInString(final String content) {
		final Enumeration enumerator = NSArray.componentsSeparatedByString(content, "\r\n").objectEnumerator();
		final NSMutableArray foldedContent = new NSMutableArray();
		String line;

		while (enumerator.hasMoreElements()) {
			line = (String) enumerator.nextElement();
			while (line.length() > maxLineLength) {
				foldedContent.addObject(line.substring(0, 75));
				line = " " + line.substring(75);
			}
			foldedContent.addObject(line);
		}

		return foldedContent.componentsJoinedByString("\r\n");
	}

	protected String traiterQuot(final String content) {
		final char quot = '\u0022';
		return content.replaceAll("&quot;", String.valueOf(quot));
	}

	public String prodId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
}

/*
 * $Id: PublishCalendarPage.java,v 1.1 2003/10/22 09:03:47 hvillesu Exp $ / $Log: PublishCalendarPage.java,v $ / Revision 1.1 2003/10/22
 * 09:03:47 hvillesu / ajout de l'export iCalendar en DirectAction / / Revision 1.2 2002/10/17 18:53:16 johan / Follow iCalendar standard by
 * escaping " ; : \ , and line breaks in text values / with backslash. / Follow iCalendar standard by folding lines longer than 75
 * characters. / / Revision 1.1 2002/10/09 19:32:15 johan / Renamed "CalendarPage" "PublishCalendarPage". / / Revision 1.7 2002/10/06
 * 19:23:06 johan / Improved JavaDoc comments. / / Revision 1.6 2002/10/06 08:56:41 johan / Removed unused imports of eocontrol and
 * eoaccess. / / Revision 1.5 2002/10/06 08:49:17 johan / Implemented recurring events. / / Revision 1.4 2002/10/03 04:55:18 johan / Added
 * timestamp formatters to ensure date/times are formatted using the / local time zone. / / Revision 1.3 2002/10/02 19:37:39 johan / Added
 * addEventsFromArray and removeEventsInArray. / / Revision 1.2 2002/09/30 20:20:11 johan / Added JavaDoc comments. / / Revision 1.1.1.1
 * 2002/09/30 07:00:42 johan / First Import / /
 */
