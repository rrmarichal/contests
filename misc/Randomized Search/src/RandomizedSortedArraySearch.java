import java.util.Random;

class SearchResult {

    public int index, iterations;

    public SearchResult(int index, int iterations) {
        this.index = index;
        this.iterations = iterations;
    }

}

public class RandomizedSortedArraySearch {

    private static final boolean CHECKED = true;

    private SearchResult _search(int[] list, int target, int low, int high, int iterations, Random random) {
        if (low == high) {
            int index = list[low] == target ? low : -1;
            return new SearchResult(index, iterations);
        }
        int index = random.nextInt(high - low) + low;
        if (target <= list[index]) {
            return _search(list, target, low, index, iterations + 1, random);
        }
        return _search(list, target, index+1, high, iterations + 1, random);
    }

    /**
     * Searches for an element in a sorted array. 
     * @param list The sorted array instance.
     * @param target The target element.
     * @return The index of in the array where this element is, or -1 if not in the list.
     */
    public SearchResult search(int[] list, int target) {
        if (CHECKED && list == null) {
            throw new IllegalArgumentException("Invalid argument value (list is null).");
        }
        if (list.length == 0) {
            return new SearchResult(-1, 0);
        }
        return _search(list, target, 0, list.length - 1, 0, new Random());
    }

}
