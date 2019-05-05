package lambda;

import java.io.File;
import java.util.Arrays;

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
			return name.contains("Test"); //In Block lambda return statement is needed if there is a return type.	
		});
		System.out.println(Arrays.asList(files));
	}
	
	@Test
	public void testMethodReference(){
		
	}
}
