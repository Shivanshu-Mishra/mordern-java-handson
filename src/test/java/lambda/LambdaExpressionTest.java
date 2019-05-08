package lambda;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;

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
		String[] files = searchDirectory.list((dir, name) -> name.contains("Test"));
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
	public void testInstanceMethodReference() {
		System.out.println("Print number stream using lambda expression");
		Stream.of(3, 1, 4, 1, 5, 9).forEach(x -> System.out.println(x));
		System.out.println("Print number stream using method reference");
		Stream.of(3, 1, 4, 1, 5, 9).forEach(System.out::println); // Here Method reference used with instance method
	}
	
	@Test
	public void testStaticMethodReference(){
		Stream.generate(Math::random).limit(10).forEach(System.out::println);		
	}
}
