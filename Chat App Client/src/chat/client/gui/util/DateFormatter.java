/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.gui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Lupate
 */
public class DateFormatter {

    private static String format = "dd-MM-yyyy";

    public DateFormatter() {
    }

    /*****************Format Symbols Meanings***************************
    G	Era	Text	“GG” -> “AD”
    y	Year	Number	“yy” -> “03?
    M	Month	Text or Number “MM” -> “07?  “MMM” -> “Jul”  “MMMM” -> “December”
    d	Day in month	Number
    h	Hour (1-12, AM/PM
    H	Hour (0-23)	Number
    k	Hour (1-24)	Number
    K	Hour (0-11 AM/PM
    m	Minute	Number
    s	Second	Number
    S	Millisecond (0-999)
    E	Day in week	Text	“EEE” -> “Tue” “EEEE” -> “Tuesday”
    D	Day in year (1-365 or 1-364)
    F	Day of week in month (1-5)
    w	Week in year (1-53)
    W	Week in month (1-5)
    a	AM/PM	Text
    z	Time zone	Text	“z” -> “EST” “zzz” -> “EST” “zzzz” -> “Eastern Standard Time”
    ‘	Excape for text	Delimiter	“‘hour’ h” -> “hour 9?
    ”	Single quote	Literal	“ss”SSS” -> “45?876?
     ********************************************************/
    public static String formatDate(String format, Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);

    }

    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);

    }

    /*****************Format Symbols Meanings***************************
    G       Era     Text    “GG” -> “AD”
    y       Year    Number  “yy” -> “03?
    M       Month   Text or Number “MM” -> “07?  “MMM” -> “Jul”  “MMMM” -> “December”
    d       Day in month    Number
    h       Hour (1-12, AM/PM
    H       Hour (0-23)     Number
    k       Hour (1-24)     Number
    K       Hour (0-11 AM/PM
    m       Minute  Number
    s       Second  Number
    S       Millisecond (0-999)
    E       Day in week     Text    “EEE” -> “Tue” “EEEE” -> “Tuesday”
    D       Day in year (1-365 or 1-364)
    F       Day of week in month (1-5)
    w       Week in year (1-53)
    W       Week in month (1-5)
    a       AM/PM   Text
    z       Time zone       Text    “z” -> “EST” “zzz” -> “EST” “zzzz” -> “Eastern Standard Time”
    ‘       Excape for text Delimiter       “‘hour’ h” -> “hour 9?
    ”       Single quote    Literal “ss”SSS” -> “45?876?
     ********************************************************/
    public static Date formatString(String format,
            String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);

    }

    public static Date formatString(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);

    }

    public static Date addDays(Date date, int daysNo) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(cal.DATE, daysNo);
        return cal.getTime();
    }

    public static Date addDays(Date date, String daysNo) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(cal.DATE, Integer.valueOf(daysNo));
        return cal.getTime();
    }

    public static String addDays(String date, int daysNo) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(formatString(date));
            cal.add(cal.DATE, daysNo);
            return formatDate(cal.getTime());
        } catch (ParseException e) {
            return date;
        }
    }

    public static String addDays(String date, String daysNo) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(formatString(date));
            cal.add(cal.DATE, Integer.valueOf(daysNo));
            return formatDate(cal.getTime());
        } catch (ParseException e) {
            return date;
        }
    }
}
