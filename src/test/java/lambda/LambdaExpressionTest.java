package lambda;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1. Lambda expression treat a method as though it was a object 2. Method
 * reference treats an existing method as though it was a lambda.
 */
public class LambdaExpressionTest {

	@Test
	public void testRunnableInterfaceAnomynous() {
		new Thread(new Runnable() {
			public void run() {
				System.out.println("Anonymous runnable implementation");
			}
		}).start();
	}

	@Test
	public void testRunnableExpressionLambda() {
		new Thread(() -> System.out.println("Expression lambda implementation")).start();
	}

	@Test
	public void testLambdaExpressionAssignment() {
		Runnable runnable = () -> System.out.println("Lambda expression assignment");
		new Thread(runnable).start();
	}

	@Test
	public void testLambdaWithMultipleArgument() {
		File searchDirectory = new File("./src/test/java/lambda");
		// Return keywords are not required with expression lambda
		String[] files = searchDirectory.list((dir, name) -> name.contains("Test"));
		System.out.println(Arrays.asList(files));
	}

	@Test
	public void testLambdaWithArgumentTypeSpecified() {
		File searchDirectory = new File("./src/test/java/lambda");
		String[] files = searchDirectory.list((File dir, String name) -> name.contains("Test"));
		System.out.println(Arrays.asList(files));
	}

	@Test
	public void testBlockLambdaWithMultipleArgument() {
		File searchDirectory = new File("./src/test/java/lambda");
		String[] files = searchDirectory.list((dir, name) -> {
			return name.contains("Test"); // In Block lambda return statement is
											// needed if there is a return type.
		});
		System.out.println(Arrays.asList(files));
	}

	@Test
	// object::instanceMethod
	public void testInstanceMethodReference() {
		System.out.println("Print number stream using lambda expression");
		Stream.of(3, 1, 4, 1, 5, 9).forEach(x -> System.out.println(x));
		System.out.println("Print number stream using method reference");
		Stream.of(3, 1, 4, 1, 5, 9).forEach(System.out::println); // Here Method
																	// reference
																	// used with
																	// instance
																	// method
	}

	@Test
	// Class::staticMethod
	public void testStaticMethodReference() {
		Stream.generate(Math::random).limit(10).forEach(System.out::println);
	}

	@Test
	// Class::instanceMethod
	public void testClassInstanceMethodReference() {
		// System.out.println("Method reference example by using class reference
		// on instantiate method");
		List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
		List<String> sorted = strings.stream().sorted(String::compareTo).collect(Collectors.toList());
		// sorted.forEach(System.out::println);

		// Each method reference has equivalent lambda but not vice versa
		// Lambda equivalent for above method reference
		// System.out.println("Lambda equivalent of Method reference");
		List<String> sortedLambda = strings.stream().sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.toList());
		// sortedLambda.forEach(System.out::println);
		Assert.assertTrue("Sorted listed generated using lambda equivalent and method reference differs", sorted.equals(sortedLambda));
	}
	
	@Test
	public void testConstructorReference(){
		List<String> names =
			    Arrays.asList("Grace Hopper", "Barbara Liskov", "Ada Lovelace",
			        "Karen Spärck Jones");
		
		List<Person> peoples=names.stream()
				.map(Person::new)
				.collect(Collectors.toList());
		
		List<Person> people_s=names.stream()
				.map(name -> new Person(name))
				.collect(Collectors.toList());
		Assert.assertTrue("Number of names not equals to number of person", names.size()==peoples.size());
		Assert.assertTrue("Number of names not equals to number of person",names.size()==people_s.size());
		Assert.assertTrue("List of people generated from lambda and constructor refernce are not same", peoples.contains(people_s));
	}
	
	@Test
	public void testArrayCreation(){
		List<String> names =
			    Arrays.asList("Grace Hopper", "Barbara Liskov", "Ada Lovelace",
			        "Karen Spärck Jones");
		Person[] people=names.stream().map(Person::new).toArray(Person[]::new);
		Assert.assertTrue("Number of person names is not equal to number of person created", names.size()==people.length);
	}
	
	
	
	private class Person{
		private String name;
		
		public Person(){}
		
		public Person(String name){
			this.name=name;
		}
	}
}
