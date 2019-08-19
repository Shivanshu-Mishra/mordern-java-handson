package nio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class FilesTest {

	/**
	 * Files.lines return the content of the text file as stream.
	 * 
	 * @throws URISyntaxException 
	 */
	@Test
	public void testFilesLines() throws URISyntaxException {
		try(Stream<String> lines=Files.lines(Paths.get(getClass().getClassLoader().getSystemResource("words.txt").toURI()))){
			lines.filter(words -> words.length()>20)
			.sorted(Comparator.comparingInt(String::length).reversed())
			.limit(10)
			.forEach(word -> System.out.printf("%s:%d\n", word,word.length()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 1. Files.line() read returns a Stream which represent content of text file line by line
	 * 2. Then using predicate we filter words/lines whose length is greater than 20
	 * 3. Collectors.groupingBy() then return a map with word length as key and count of word with same length as value
	 * 4. Create an entrySet() and get a stream() from it .
	 * 5. sort the map in reverse order based on key.
	 * 6. then print key and value
	 */
	@Test
	public void testDownStreamCollector() {
		try(Stream<String> lines=Files.lines(Paths.get(getClass().getClassLoader().getResource("words.txt").toURI()))){
			lines.filter(word -> word.length()>20)
			.collect(Collectors.groupingBy(String::length,Collectors.counting()))
			.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
			.forEach(e -> System.out.println(e.getKey()+":"+e.getValue()));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 1. Files.list() return a directory stream that wraps path for all files that exist in directory passed as argument.
	 * 2. Print all the firectory inside src/test/java
	 */
	@Test
	public void testFilesList() {
		try(Stream<Path> files=Files.list(Paths.get("src/test/java"))){
			files.forEach(System.out::println);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * To perform depth first operation we can use File.walk() which takes 2 arguments.
	 * 1. Path to vist
	 * 2. FileVisitOption which is an enum with only single value as FOLLOW_LINKS which will check for cycle loops 
	 * and throw FileSystemLoopException if it exist.
	 */
	@Test
	public void testFilesWalk() {
		try(Stream<Path> paths=Files.walk(Paths.get("src/test/java"),FileVisitOption.FOLLOW_LINKS)){
			paths.forEach(System.out::println);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * To find file with a particular attibute use Files.find(). Following are arguments to find() method.
	 * 
	 * 1. Path to find files
	 * 2. Maximum depth to look for files
	 * 3. BiPredicate that takes files path and basic file attribute
	 * 4. FileVisitOption
	 */
	@Test
	public void testFilesFind() {
		try(Stream<Path> paths=Files.find(Paths.get("src/test/java"),
				Integer.MAX_VALUE,
				(path,attributes) ->
		!attributes.isDirectory() && path.toString().contains("nio"), FileVisitOption.FOLLOW_LINKS)){
			paths.forEach(System.out::println);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
