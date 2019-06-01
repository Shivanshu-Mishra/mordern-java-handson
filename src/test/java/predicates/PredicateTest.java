package predicates;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
// Static Import help in calling static members without class name
import static predicates.CommonPredicates.*;

public class PredicateTest {
	String[] names={"Sunanda","Prakhar","Raymond","Melinda","Karim","Satish","suraj"};
	
	/**
	 * The Single abstract method in predicate
	 * 
	 *  boolean test(T t) 
	 */
	@Test
	public void testAbstractMethod(){		
		List<String> filteredNames=Arrays.stream(names).filter(name -> name.length()>6).collect(Collectors.toList());
		List<String> expectedNames=Arrays.asList("Sunanda","Prakhar","Raymond","Melinda");
		Assert.assertTrue("List contains other names of length more than 6", filteredNames.equals(expectedNames));
	}
	
	@Test
	public void testGeneralPredicateMethod(){
		List<String> expectedNamesStartingWithS=Arrays.asList("Sunanda","Satish");
		Assert.assertTrue("Filtered list contains names other than starting with s", getNames(WORDS_STARTING_WITH_S, names).equals(expectedNamesStartingWithS));
	}
	
	/**
	 * Predicate has following default and static method
	 * 
	 * 1. default Predicate<T> and (Predicate<? super T> other)
	 * 2. default Predicate<T> or (Predicate<? super T> other)
	 * 3. default Predicate<T> negate()
	 * 4. static <T> Predicate<T> isEqual(Object target)
	 */
	@Test
	public void testOr(){
		List<String> expectedNames=Arrays.asList("Sunanda","Satish","suraj");
		List<String> filteredNames=getNames(WORDS_STARTING_WITH_S.or(WORDS_STARTING_WITH_s),names);
		test(filteredNames,expectedNames);
	}
	
	@Test
	public void testAnd(){
		List<String> expectedNames=Arrays.asList("Sunanda");
		List<String> filteredNames=getNames(WORDS_STARTING_WITH_S.and(WORDS_LENGTH_GREATER_THAN_6),names);
		test(filteredNames,expectedNames);
	}
	
	@Test
	public void testNegate(){
		List<String> expectedNames = Arrays.asList("Karim","Satish","suraj");
		List<String> filteredNames=getNames(WORDS_LENGTH_GREATER_THAN_6.negate(),names);
		test(filteredNames,expectedNames);
	}
	
	private void test(List<String> filteredNames,List<String> expectedNames){
		Assert.assertTrue("Filtered list: "+toString(filteredNames) +"\n Expected list: "+toString(expectedNames), filteredNames.equals(expectedNames));
	}
	
	
	private List<String> getNames(Predicate<String> condition,String[] names){
		return Arrays.stream(names).filter(condition).collect(Collectors.toList());
	}
	
	private String toString(List<String> elements){
		return elements.stream().collect(Collectors.joining(", "));
	}

}
