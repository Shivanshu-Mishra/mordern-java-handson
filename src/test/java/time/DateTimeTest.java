package time;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import org.junit.Test;

import junit.framework.Assert;

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
	
	@Test
	public void testCreatingDateFromExisting() {
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate start=LocalDate.of(1988, Month.JULY, 30);
		LocalDate afterOneDay=start.plusDays(1);
		Assert.assertEquals("1988-07-31", formatter.format(afterOneDay));
		
		LocalDate afterOneMonth=start.plusMonths(1);
		Assert.assertEquals("1988-08-30", formatter.format(afterOneMonth));
		
		LocalDate afterOneYear=start.plusYears(1);
		Assert.assertEquals("1989-07-30", formatter.format(afterOneYear));
		
		LocalDate leapStart=LocalDate.of(2020, Month.FEBRUARY, 28);
		LocalDate afterOneDayInLeapFeb=leapStart.plusDays(1);
		Assert.assertEquals("2020-02-29", formatter.format(afterOneDayInLeapFeb));
		
		LocalDate after2Day=start.plus(2, ChronoUnit.DAYS);
		Assert.assertEquals("1988-08-01",formatter.format(after2Day));
		
		LocalDate after3Decades=start.plus(3, ChronoUnit.DECADES);
		Assert.assertEquals("2018-07-30",formatter.format(after3Decades));
	}
	
	@Test
	public void testCreatingTimeFromExisting() {
		DateTimeFormatter formatter=DateTimeFormatter.ISO_LOCAL_TIME;
		LocalTime startTime=LocalTime.of(21, 30,0,0);
		LocalTime after1NanoSecond=startTime.plusNanos(1_000_000);
		LocalTime after1Second= startTime.plusSeconds(1);
		LocalTime after1Minues = startTime.plusMinutes(1);
		LocalTime after1Hour = startTime.plusHours(1);
		LocalTime after10Hours = startTime.plus(10, ChronoUnit.HOURS);
		
		Assert.assertEquals("21:30:00.001", formatter.format(after1NanoSecond));
		Assert.assertEquals("21:30:01", formatter.format(after1Second));
		Assert.assertEquals("21:31:00", formatter.format(after1Minues));
		Assert.assertEquals("22:30:00", formatter.format(after1Hour));
		Assert.assertEquals("07:30:00", formatter.format(after10Hours));		
	}	
}
