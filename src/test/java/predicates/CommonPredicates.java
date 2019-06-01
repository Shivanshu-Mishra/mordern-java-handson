package predicates;

import java.util.function.Predicate;

public class CommonPredicates {
	public static final Predicate<String> WORDS_STARTING_WITH_S=word ->  word.startsWith("S");
	public static final Predicate<String> WORDS_STARTING_WITH_s=word ->  word.startsWith("s");

}
