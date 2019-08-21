package time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZonedDateTime;

import org.junit.Test;

/**
 * All Classes in date-time are immutable and Thread safe.
 * 
 * @author shiva
 *
 */
public class DateTimeTest {

	/**
	 * Several Date time classes have now() factory method which will create instance based on current date and time.
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
		LocalDate date=LocalDate.of(2019, Month.AUGUST, 20);
		System.out.println("Todays date: "+date);
		LocalTime time=LocalTime.of(21, 57);
		System.out.println("Current time: "+time);
		System.out.println("Current date and time: "+LocalDateTime.of(date, time));
	}
}
