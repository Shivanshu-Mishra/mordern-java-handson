package stream;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

/**
 * A Stream is a sequence of elements that does not save the elements or modify
 * the original source. Functional programming in java often generate a stream
 * from source of data that passes through intermediate operation (pipeline) and
 * complete with terminal operation.
 * 
 * As Stream do not process any data until a terminal expression is reached , we
 * have to add a terminal method like collect or forEach at the end.
 *
 * Streams can be used once and after it has passed through zero or more
 * intermediate operation and reached terminal operation it is finshed. To
 * process the value again we need to create a new Stream. Streams are lazy and
 * process only as much data to reach terminal condition
 */
public class StreamTest {

	/**
	 * static <T> Stream<T> of(T... values)
	 * 
	 * This static method take a variable argument list of elements and generate
	 * a stream out of it.
	 */
	@Test
	public void testStreamOf() {
		Stream<String> countries = Stream.of("India", "Sweden", "China", "Pakistan", "Bangladesh", "Srilanka");
		String countriesJoin = countries.collect(Collectors.joining(","));
		System.out.println("Joined countries" + countriesJoin);
		// IllegalStateException: stream has already been operated upon or
		// closed
		// countries.forEach(country -> System.out.println("Hail "+country));

		// To do more operation on same countries we have to create a new Stream
		// again
		Stream.of("India", "Sweden", "China", "Pakistan", "Bangladesh", "Srilanka")
				.forEach(country -> System.out.println("Hail " + country));
	}

	@Test
	public void testArraysStream001() {
		String[] pets = { "Cow", "Ox", "Buffalo", "Dog", "Cat", "Rabbit", "Parrot", "Sheep", "Goat", "Horse", "Mouse",
				"Fish", "Pigeon", "Pig", "Hen", "Cock", "Duck" };
		Stream<String> petStream = Arrays.stream(pets);
		petStream.forEach(pet -> System.out.println("Lovely " + pet));

		/*
		 * Stream can be used only once . If below line uncommented and run it
		 * will throw Illegal State exception as Stream is already used and
		 * reach end of lifecycle in above statement
		 */

		// Assert.assertTrue("Mismatch in numbers of pet in java.util.Stream",
		// petStream.count()==16);
	}

	@Test
	public void testArraysStream002() {
		String[] pets = { "Cow", "Ox", "Buffalo", "Dog", "Cat", "Rabbit", "Parrot", "Sheep", "Goat", "Horse", "Mouse",
				"Fish", "Pigeon", "Pig", "Hen", "Cock", "Duck" };
		Stream<String> petStream = Arrays.stream(pets);
		Assert.assertEquals("Mismatch in numbers of pet in java.util.Stream", petStream.count(), 17);
	}

	/**
	 * static <T> Stream<T> iterate(T seed, UnaryOperator<T> f)
	 * 
	 * iterate() static factory method returns an infinite sequential ordered
	 * Stream produced by iterative application of a function f to an initial
	 * element seed.
	 * 
	 * Unary operator is a function whose input and output are of same type .
	 * Since the resulting stream are unbounded we have to use intermediate
	 * operation limit() to bound it.
	 */
	@Test
	public void testStreamIterate() {
		List<BigDecimal> nums = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE)).limit(10)
				.collect(Collectors.toList());
		System.out.println(nums);

		System.out.println();
		System.out.println("Print next 10 days");

		Stream.iterate(LocalDate.now(), ld -> ld.plusDays(1L)).limit(10).forEach(System.out::println);
	}

	/**
	 * static <T> Stream<T> generate(Supplier<T> s)
	 * 
	 * generate() method produce sequential ,unordered stream by repeatedly
	 * invoking the Supplier
	 */
	@Test
	public void testStreamGenerate() {
		Stream.generate(Math::random).limit(10).forEach(System.out::println);
	}

	/**
	 * Primitive stream interfaces
	 * 
	 * 1. IntStream 2. LongStream 3. DoubleStream
	 * 
	 * static IntStream range(int startInclusive, int endExclusive) static
	 * IntStream rangeClosed(int startInclusive, int endInclusive) static
	 * LongStream range(long startInclusive, long endExclusive) static
	 * LongStream rangeClosed(long startInclusive, long endInclusive)
	 * 
	 * To convert primitive to List<T> one of the alternative is to use boxed
	 * method
	 */
	@Test
	public void testPrimitiveStreamBoxed() {
		System.out.println("IntStream range from 10 to 14");
		IntStream.range(10, 15).boxed().collect(Collectors.toList()).forEach(System.out::println);

		System.out.println();

		System.out.println("LongStream range from 10 to 15");
		LongStream.rangeClosed(10, 15).boxed().collect(Collectors.toList()).forEach(System.out::println);
	}

	/**
	 * To convert primitive from Primitive Stream to List<T> another alternative
	 * is mapToObj()
	 */
	@Test
	public void testPrimitiveStreamMapToObj() {
		List<Integer> ints = IntStream.range(11, 112).mapToObj(Integer::valueOf).collect(Collectors.toList());
		ints.forEach(System.out::println);
	}

	/**
	 * Another alternative <R> R collect(Supplier<R> supplier,ObjIntConsumer<R>
	 * accumulator,BiConsumer<R,R> combiner)
	 */
	@Test
	public void testPrimitiveCollect() {
		List<Integer> ints = IntStream.rangeClosed(99, 1456).collect(ArrayList<Integer>::new, ArrayList::add,
				ArrayList::addAll);
		System.out.println(ints.size());
		Assert.assertTrue("Number of element in generated int stream is not as expected", ints.size() == 1358);
	}

	/**
	 * There are several reduction operation whcih will convert a stream of
	 * elements into a single value.
	 * 
	 * 1. count() define on Stream interface 2. sum(), average, max, min define
	 * on primitive stream only so need to convert Stream to Primitive Stream
	 * using mapToPrimitive method
	 */
	@Test
	public void testReductionOperation() {
		String[] arrays = "Today is a nice beautiful sunny day".split(" ");
		long counts = Arrays.stream(arrays).mapToInt(String::length).count();
		System.out.println("There are " + counts + " string");

		int totalWordLength = Arrays.stream(arrays).mapToInt(String::length).sum();
		System.out.println("Total word length is " + totalWordLength);

		double averageWordLength = Arrays.stream(arrays).mapToInt(String::length).average().orElse(0);
		System.out.println("Average words lenght is " + averageWordLength);

		int max = Arrays.stream(arrays).mapToInt(String::length).max().orElse(0);
		System.out.println("Maximum word size is " + max);

		int min = Arrays.stream(arrays).mapToInt(String::length).min().orElse(-1);
		System.out.println("Minimum word lenght " + min);
	}

	/**
	 * OptionalInt reduce(IntBinaryOperator op)
	 * 
	 * There are 2 input argument x,y to IntBinaryOperator x acts as accumulator
	 * and y as value from stream
	 */
	@Test
	public void testIntStreamReduction001() {
		int sum = IntStream.rangeClosed(1, 10).reduce((x, y) -> {
			System.out.printf("x:%d , y:%d", x, y);
			System.out.println();
			return x + y;
		}).orElse(0);
	}

	/**
	 * int reduce(int identity, IntBinaryOperator op)
	 * 
	 * There is a identity value that is assigned to first argument x of
	 * BinaryOpertor which is an accumulator. Its intialization of accumulator
	 * with identity element.
	 * 
	 * Identity elements for different operation are. sum -> 0 multiply -> 1
	 * String concat/append -> ""
	 */
	@Test
	public void testIntStreamReduction002() {
		int sum = IntStream.rangeClosed(1, 10).reduce(0, (x, y) -> {
			System.out.printf("Acc:%d , n:%d", x, y);
			System.out.println();
			return x + y;
		});
	}

	/**
	 * Integer,Long,Double have included sum(), max() , min() to simplify reduce
	 * operation.
	 * 
	 */
	@Test
	public void testStreamReduction() {
		int sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).reduce(0, Integer::sum);
		System.out.println(sum);

		Integer max = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).reduce(Integer.MIN_VALUE, Integer::max);
		System.out.println(max);

		Integer min = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10).reduce(Integer.MAX_VALUE, Integer::min);
		System.out.println(min);

		String concatString = Stream.of("This", "is", "a", "sunny", "day").reduce("", String::concat);
		System.out.println(concatString);
	}
	
	/**
	 * 
	 */
	@Test
	public void testStreamCollectReduction(){
		String buildConcatString=Stream.of("I","will","jog","today").collect(StringBuilder::new,StringBuilder::append,StringBuilder::append).toString();
		System.out.println(buildConcatString);
		
		String concatString=Stream.of("I","am","thinking","to","swim","too").collect(Collectors.joining());
		System.out.println(concatString);
	}

}
