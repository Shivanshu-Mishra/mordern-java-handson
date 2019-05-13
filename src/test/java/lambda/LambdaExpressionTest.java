package lambda;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
		List<String> sorted = strings.stream().sorted(String::compareTo).collect(Collectors.toList());
		sorted.forEach(System.out::println);
	}
}
