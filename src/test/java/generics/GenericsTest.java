package generics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;

public class GenericsTest {

	@Test
	public void testGenericsCanContainOnlyDeclaredType() {
		List<String> strings = new ArrayList<>();
		List<Object> objects = new ArrayList<>();

		// List<String> is not subtype of List<Object>

		strings.add("Hello"); // Its possible to add its type
		objects.add(new Locale("Hindi")); // Same reason as above
		objects.add("Hindi");// As string is subtype of objects

		Object language = "Hindi";
		// strings.add(language); Not possible as List<String> is not subtype of
		// List<Object>
	}

	@Test
	public void testUnboundedWildCards() {
		// A wild card is a type argument that uses a question mark

		// Limitation of unbounded Type arguments is that you can read from it
		// but cannot write into it.
		List<?> stuff = new ArrayList<>();
		// stuff.add("This"); Not possible , only method which does not expected
		// any type or read operation
		Assert.assertEquals(0, stuff.size());
	}

	@Test
	public void testUnboundedWildCardUse() {
		List<String> strings = new ArrayList<>();
		List<Integer> ints = new ArrayList<>();

		strings.add("Hello");
		strings.add("Namaste");
		strings.add("ciao");
		ints.add(1);
		ints.add(69);
		ints.add(23);

		printElement(strings);
		printElement(ints);
	}

	@Test
	public void testUpperBound() {
		// Upper bounded wildcard uses the extends keyword with ? to set a
		// superclass
		// limit
		List<? extends Number> numbers = new ArrayList<>();

		// With upper bounded wildcard also its not possible to add different
		// values as int,float,double and bignumeric which extends Number class
		// numbers.add(3);
		// numbers.add(3.4159);

		List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);
		List<Double> floats = Arrays.asList(1.0, 2.3, 4.5, 8.3);
		List<BigDecimal> bigdecimals = Arrays.asList(new BigDecimal("1.0"), new BigDecimal("5.7"),
				new BigDecimal("10.2"));

		System.out.println("Sum of ints " + sum(ints));
		System.out.println("Sum of float " + sum(floats));
		System.out.println("Sum of big decimal" + sum(bigdecimals));
	}

	@Test
	public void testUpperBoundInteger() {
		List<? extends Integer> integers = new ArrayList<>();
		// integers.add(new Integer(1)); Not allowed
	}

	@Test
	public void testLowerBoundInteger() {
		// Lower bound wildcard uses super keyword with ? to specify a lower
		// bound.
		List<? super Integer> numbers = new ArrayList<>();
		numbers.add(1);
	}

	@Test
	public void testLowerBound() {

		List<Integer> integers = new ArrayList<>();
		getRange(5, integers);
		System.out.println("Print integers");
		integers.forEach(System.out::println);
		
		List<Number> numbers= new ArrayList<>();
		getRange(6, numbers);
		System.out.println("Print numbers");
		numbers.forEach(System.out::println);
	}

	private void getRange(Integer limit, List<? super Integer> consumer) {
		IntStream.rangeClosed(0, limit).forEach(consumer::add);
	}

	private Double sum(List<? extends Number> numbers) {
		return numbers.stream().mapToDouble(Number::doubleValue).sum();
	}

	private void printElement(List<?> list) {
		list.forEach(System.out::println);
	}

}
