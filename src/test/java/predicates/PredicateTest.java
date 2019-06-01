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
	String[] names={"Sunanda","Prakhar","Raymond","Melinda","Karim","Satish"};
	
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
	
	
	private List<String> getNames(Predicate<String> condition,String...names){
		return Arrays.stream(names).filter(condition).collect(Collectors.toList());
	}

}
