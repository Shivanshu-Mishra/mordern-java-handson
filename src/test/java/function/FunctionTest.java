package function;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;



/**
 * Functional interface tranform an input parameter into an output value.
 */
public class FunctionTest {
	List<String> names=Arrays.asList("Rakesh","Supriya","Suyash","Sudama","Shrishti");
	/**
	 * Function single abstract method is
	 * 
	 * R apply(T t)
	 * 
	 * This will tranform input of type T into R.
	 */
	@Test
	public void testApply(){
		List<Integer> expectedWordLength=Arrays.asList(6,7,6,6,8);
		List<Integer> actualWordLength=names.stream().map(String::length).collect(Collectors.toList());
		Assert.assertTrue("Expected Word length: "+toString(expectedWordLength)+" transformed word length: "+toString(actualWordLength), actualWordLength.equals(expectedWordLength));		
	}
	
	private String toString(List<?> elements){
		return elements.stream().map(element -> element.toString()).collect(Collectors.joining(", "));
	}
	
	
}
