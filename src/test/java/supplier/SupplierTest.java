package supplier;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.junit.Test;

import junit.framework.Assert;

/**
 * Supplier functional interface has a single abstract method that take no input
 * argument and only return value.
 * T get()
 * 
 * Supplier interface are simple and they do not have any abstract or default method
 */
public class SupplierTest {
	Logger logger=Logger.getLogger("SupplierTest");
	
	@Test
	public void testSupplier(){
		Supplier<String> randomNumber=() -> String.valueOf(Math.random()*10);
		logger.info(randomNumber);
	}
	
	/**
	 * Supplier interface has following variants.
	 * 
	 * 1. IntSupplier - int getAsInt()
	 * 2. DoubleSupplier - double getAsDouble()
	 * 3. LongSupplier - long getAsLong()
	 * 4. BooleanSupplier - boolean getAsBoolean()
	 */
	@Test
	public void testSupplierVariant(){
	Supplier<Long> randomNumber= () -> Math.round(Math.random()*100);	
	System.out.println(randomNumber.get());
	}
	
	/**
	 * One of the primary use case of supplier is to support the concept of deferred execution.
	 * The java.util.logging.Logger.info() takes a supplier whose get method is only called if the
	 * log level means the message will be seen.  
	 */
	@Test
	public void deferredExecutionExample(){
		List<String> teams=Arrays.asList("Mharri","Sbilal","Msander","Mshang");
		Optional<String> first_O=teams.stream().filter(member -> member.startsWith("O")).findAny();
		
		//This will print Optional.empty
		System.out.println(first_O);
		
		//This will print None
		System.out.println(first_O.orElse("None"));
		
		//This will always create string even if result are found.
		System.out.println(first_O.orElse(String.format("No result found in %s", teams.stream().collect(Collectors.joining(",")))));
		
		//Form the comma seperated String only if Optional is empty.
		System.out.println(first_O.orElseGet(() ->String.format("No result found in %s", teams.stream().collect(Collectors.joining(",")))));
		
		Optional<String> first_M=teams.stream().filter(member -> member.startsWith("M")).findFirst();
		System.out.println(first_M.orElseGet(() -> String.format("No memeber have initials as M in team %s", teams.stream().collect(Collectors.joining(",")))));
	}	
}
