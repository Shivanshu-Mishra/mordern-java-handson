package time;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

import org.junit.Test;

/**
 * All Classes in date-time are immutable and Thread safe.
 * 
 * @author shiva
 *
 */
public class DateTimeTest {

	/**
	 * Several Date time classes have now() factory method which will create
	 * instance based on current date and time.
	 */
	@Test
	public void testDateTimeNow() {
		System.out.println("Instant.now():  " + Instant.now());
		System.out.println("LocalDate.now():  " + LocalDate.now());
		System.out.println("LocalTime.now()  " + LocalTime.now());
		System.out.println("LocalDateTime.now()  " + LocalDateTime.now());
		System.out.println("ZonedDateTime.now()  " + ZonedDateTime.now());
	}

	/**
	 * of() factory method will return instance based on input parameter.
	 */
	@Test
	public void testDateTimeOf() {
		LocalDate date = LocalDate.of(2019, Month.AUGUST, 20);
		System.out.println("Todays date: " + date);
		LocalTime time = LocalTime.of(21, 57);
		System.out.println("Current time: " + time);
		System.out.println("Current date and time: " + LocalDateTime.of(date, time));
	}

	@Test
	public void testGetZoneIds() {
		Set<String> regionNames = ZoneId.getAvailableZoneIds();
		regionNames.forEach(System.out::println);
	}

	@Test
	public void testGettingDifferentTimeZone() {
		LocalDateTime dateTime = LocalDateTime.now();
		ZonedDateTime nyc = dateTime.atZone(ZoneId.of("America/New_York"));
		ZonedDateTime london = nyc.withZoneSameInstant(ZoneId.of("Europe/London"));

		System.out.println("Stockholm Time: " + dateTime);
		System.out.println("Newyork Time: " + nyc);
		System.out.println("London Time: " + london);
	}

	@Test
	public void testMonth() {
		System.out.println("Days in feb in leap year " + Month.FEBRUARY.length(true));
		System.out.println("Day of year for the first day of August" + Month.AUGUST.firstDayOfYear(true));
		System.out.println("Month.of(1): " + Month.of(1));
		System.out.println("Adding two months: " + Month.JANUARY.plus(2));
		System.out.println("Subtracting a month: " + Month.MARCH.minus(1));
	}
}
