package telran.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class Arrays {
	public static <T> int indexOf(T[] array, T element) {
		int index = 0;
		while (index < array.length && !equals(array[index], element)) {
			index++;
		}
		return index == array.length ? -1 : index;
	}

	private static <T> boolean equals(T elem1, T elem2) {

		return elem1 == null ? elem1 == elem2 : elem1.equals(elem2);
	}

	public static <T> T min(T[] array, Comparator<T> comp) {
		T res = null;
		if (array != null && array.length > 0) {
			res = array[0];
			for (int i = 1; i < array.length; i++) {
				if (comp.compare(res, array[i]) > 0) {
					res = array[i];
				}
			}
		}
		return res;

	}

	public static <T> void bubbleSort(T[] array, Comparator<T> comp) {
		int n = array.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (comp.compare(array[j], array[j + 1]) > 0) {
					T temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	public static <T> int binarySearch(T[] array, T key, Comparator<T> comp) {

		int left = 0;
		int right = array.length - 1;
		while (left <= right) {
			int middle = (left + right) / 2;
			int cmp = comp.compare(array[middle], key);
			if (cmp < 0) {
				left = middle + 1;
			} else if (cmp > 0) {
				right = middle - 1;
			} else {
				return middle;
			}
		}

		return -1;
	}

	public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {
       return search(array, e-> !predicate.test(e));
	}

	public static <T> T[] search(T[] array, Predicate<T> predicate) {
		// Impossible to allocate memory for generic array
		// Only Arrays.copyOf may be used
		T[] arResult = java.util.Arrays.copyOf(array, array.length);
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (predicate.test(array[i])) {
				arResult[index++] = array[i];
			}
		}
		return java.util.Arrays.copyOf(arResult, index);
	}

}