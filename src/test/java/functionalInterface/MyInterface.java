package functionalInterface;

@FunctionalInterface 
//Functional Interfaces have single abstract method. Defining @FuntionalInterface
//annotation is not must for functional interface but if defined it provides compile
// time check of single abstract method and provide javadoc
public interface MyInterface {
	/*
	 * Single Abstract method
	 */
	boolean isMyMethod();
	//boolean isAnotherMethodPossible(); If added then this will not be a functional interface.
	
	// Functional interfaces can have as many default and static method as posssible.
	default String sayHello(){
		return "Hello World!";
	}
	
	static String sayBye(){
		return "Good Bye!";
	}

}
