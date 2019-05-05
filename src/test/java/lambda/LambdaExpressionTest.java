package lambda;

import org.junit.Test;

public class LambdaExpressionTest {
	
	@Test
	public void testRunnableInterfaceAnomynous(){
		new Thread(new Runnable() {			
			public void run() {
				System.out.println("Anonymous runnable implementation");				
			}
		}).start();
	}
	
	@Test
	public void testRunnableExpressionLambda(){
		new Thread(() -> System.out.println("Expression lambda implementation")).start();
	}
	
	@Test
	public void testLambdaExpressionAssignment(){
		Runnable runnable=() -> System.out.println("Lambda expression assignment");
		new Thread(runnable).start();
	}
}
