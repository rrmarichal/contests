import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

class ListItem<T extends Comparable<T>> implements Comparable<ListItem<T>> {
	private T item;
	private int index;
	private int position;

	public ListItem(T item, int index, int position) {
		this.item = item;
		this.index = index;
		this.position = position;
	}
	
	public T getItem() {
		return item;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getPosition() {
		return position;
	}
	
	@Override
	public int compareTo(ListItem<T> o) {
		if (getItem().compareTo(o.getItem()) != 0) {
			return getItem().compareTo(o.getItem());
		}
		return Integer.compare(getIndex(), o.getIndex());
    }
}

public class SortListOfSortedLists {

	public static void main(String[] args) {
		ArrayList<List<String>> input = new ArrayList<List<String>>();
		input.add(Arrays.asList(new String[] { "A", "E", "Z" }));
		input.add(Arrays.asList(new String[] { "A", "D", "X" }));
		List<String> result = sort(input);
		for (String s : result) {
			System.out.println(s);
		}
	}

	private static <T extends Comparable<T>> List<T> sort(ArrayList<List<T>> input) {
		TreeSet<ListItem<T>> mins = new TreeSet<ListItem<T>>();
		ArrayList<T> result = new ArrayList<T>();
		
		for (int j = 0; j < input.size(); j++) {
			List<T> l = input.get(j);
			if (l.size() > 0) {
				mins.add(new ListItem<T>(l.get(0), j, 0));
			}
		}
		
		while (mins.size() > 0) {
			ListItem<T> min = mins.pollFirst();
			result.add(min.getItem());
			if (input.get(min.getIndex()).size() > min.getPosition() + 1) {
				mins.add(new ListItem<T>(input.get(min.getIndex()).get(min.getPosition() + 1), min.getIndex(), min.getPosition() + 1));
			}
		}
		
		return result;
    }
    
}
