
package telran.utils.test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import telran.utils.Arrays;

class ArraysTests {
Integer[] numbers = {100, -3, 23, 4, 8, 41, 56, -7};
String[] strings = {"abc", "lmn", "123", null, "a"};
String[] stringsMin = {"abc", "lmn", "123",  "y"};
	@Test
	void indexOfTest() {
		assertEquals(1, Arrays.indexOf(strings, "lmn"));
		assertEquals(3, Arrays.indexOf(strings, null));
		assertEquals(-1, Arrays.indexOf(numbers, 150));
		assertEquals(4, Arrays.indexOf(numbers, 8));
	}
	@Test
	void minValueTest() {
		Comparator<String> compLength = (s1, s2) -> s1.length() - s2.length();
		assertEquals("y", Arrays.min(stringsMin,
				compLength));
		Comparator<String> compNative = (s1, s2) -> s1.compareTo(s2);
		assertEquals("123", Arrays.min(stringsMin, compNative));
	}
	@Test
	void bubbleSortTest() {
		Integer [] expected = {4, 8, 56, 100, 41, 23, -3, -7};
		Integer [] numbersCopy = java.util.Arrays.copyOf(numbers, numbers.length);
         /* lambda expression example */
	//	Comparator<Integer> evenOddComp = (num1, num2) ->
   //	evenOddComparator(num1, num2);
		/*************************************/
		/* method reference */
		Comparator<Integer> evenOddComp = ArraysTests::evenOddComparator;
		/*********************************************/
		Arrays.bubbleSort(numbersCopy, evenOddComp);
		assertArrayEquals(expected, numbersCopy);
		
	}
	static int evenOddComparator(Integer num1, Integer num2) {
		
		boolean isNum1Even = num1 % 2 == 0;
		boolean isNum2Even = num2 % 2 == 0;
		int res = 1;
		if (isNum1Even && isNum2Even) {
			res = Integer.compare(num1, num2);
		} else if (!isNum1Even && !isNum2Even) {
			res = Integer.compare(num2, num1);
		} else if (isNum1Even) {
			res = -1;
		}
		return res;
	}
	@Test
	void searchTest() {
		Integer[] expectedEven = {100, 4, 8,  56};
		assertArrayEquals(expectedEven, Arrays.search(numbers,
				a -> a % 2 == 0));
		Integer[] expectedNegative = {-3,-7};
		assertArrayEquals(expectedNegative, Arrays.search(numbers,
				a -> a < 0));
		
	}
	@Test
	void binarySearchTest() {
		Integer [] array = {1,3,5,7,9,11,13,15,17,19,24,35};
		int key = 15;
		Comparator<Integer> comp = Comparator.naturalOrder();
		int result = Arrays.binarySearch(array, key, comp);
		assertEquals(7,result);
		key = 20;
		result = Arrays.binarySearch(array, key, comp);
		assertEquals(-1,result);
	}
	 @Test
	 void removeIfTest() {
		 Integer[]array = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 24, 35};
		 Predicate<Integer>predicate = x -> x % 2 == 0;
		 Integer[]result = Arrays.removeIf(array, predicate);
		 for(Integer num : result) {
			 assertFalse(num % 2 == 0);
		 }
			 for(Integer num : array) {
	            if(!predicate.test(num)) {
	            	assertTrue(java.util.Arrays.asList(result).contains(num));
	            }
		 }
	 }
}
