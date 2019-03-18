import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

class FileLinesIterator implements Iterator<String> {
	private BufferedReader br;
	private String line;

	public FileLinesIterator(String fileName) throws FileNotFoundException {
		br = new BufferedReader(new FileReader(fileName));
	}
	
	@Override
	public boolean hasNext() {
		try {
			return (line = br.readLine()) != null;
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String next() {
		return line;
	}

	@Override
	public void remove() {}

	public void close() throws IOException {
		br.close();
    }
}

class KeyValuePairComparator<K extends Comparable<K>, V> implements Comparator<KeyValuePair<K, V>> {
	@Override
	public int compare(KeyValuePair<K, V> o1, KeyValuePair<K, V> o2) {
		// Important to disambiguate for equal keys. Otherwise, tree
		// data structure cannot handle objects appropriately. 
		int cmp = o1.getKey().compareTo(o2.getKey()); 
		if (cmp == 0) {
			return o1.getValue().hashCode() - o2.getValue().hashCode();
		}
		return cmp;
	}
}

class KeyValuePair<K extends Comparable<K>, V> {
	private K key;
	private V value;

	public KeyValuePair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public V getValue() {
		return value;
	}
	
	public K getKey() {
		return key;
	}
}

class ExternalFileWriter {
	private PrintWriter printWriter;

	public ExternalFileWriter(String fileName) throws FileNotFoundException {
		printWriter = new PrintWriter(new File(fileName));
	}
	
	public void write(String record) {
		printWriter.println(record);
	}
	
	public void close() {
		printWriter.close();
	}
}

class ExternalFileReader implements Iterable<String> {
	private FileLinesIterator iterator;

	public ExternalFileReader(String fileName) throws FileNotFoundException {
		this.iterator = new FileLinesIterator(fileName);
	}

	@Override
	public Iterator<String> iterator() {
		return iterator;
	}
	
	public void close() throws IOException {
		iterator.close();
	}
}

class SortedFilesChunks {
	private int chunkSizeInBytes;
	private int currentChunkSize;
	private int chunksCount;
	private PrintWriter chunkWriter;
	private ArrayList<String> chunkRecords;

	public SortedFilesChunks(int chunkSizeInBytes) {
		this.chunkSizeInBytes = chunkSizeInBytes;
		this.currentChunkSize = 0;
		this.chunksCount = 0;
		this.chunkRecords = new ArrayList<String>();
	}

	public Iterable<String> getSortedChunkFileNames() {
		ArrayList<String> result = new ArrayList<String>();
		for (int j = 0; j < chunksCount; j++) {
			result.add(String.format("Chunks\\ExternalSortTmpFile.%s", j));
		}
		return result;
	}
	
	public void close() throws FileNotFoundException {
		if (chunkRecords.size() > 0) {
			reset();
		}
	}
	
	public void write(String record) throws FileNotFoundException {
		chunkRecords.add(record);
		currentChunkSize += record.length();
		
		if (currentChunkSize >= chunkSizeInBytes) {
			reset();
		}
	}

	private void reset() throws FileNotFoundException {
		sortChunkRecords();
		flushChunkRecords();
		chunksCount++;
		currentChunkSize = 0;
	}

	private void flushChunkRecords() throws FileNotFoundException {
		chunkWriter = new PrintWriter(String.format("Chunks\\ExternalSortTmpFile.%s", chunksCount));
		for (String record : chunkRecords) {
			chunkWriter.println(record);
		}
		chunkWriter.close();
		chunkRecords.clear();
	}
	
	private void sortChunkRecords() {
		Collections.sort(chunkRecords);
	}
}

public class ExternalMergeSort {

	private final static int CHUNK_SIZE_IN_BYTES = 10 * 1024; // 100kB
	
	public static void main(String[] args) throws IOException {
		externalSort(args[0]);
	}

	private static void externalSort(String fileName) throws IOException {
		ExternalFileReader externalFileReader = new ExternalFileReader(fileName);
		SortedFilesChunks fileChunks = new SortedFilesChunks(CHUNK_SIZE_IN_BYTES);

		// Write chunks
		for (String line : externalFileReader) {
			fileChunks.write(line);
		}
		fileChunks.close();
		
		ArrayList<ExternalFileReader> readers = new ArrayList<ExternalFileReader>(); 
		for (String chunkFileName : fileChunks.getSortedChunkFileNames()) {
			readers.add(new ExternalFileReader(chunkFileName));
		}
		
		// Init min-key structure for merge procedure
		TreeSet<KeyValuePair<String, Iterator<String>>> t =
			new TreeSet<KeyValuePair<String, Iterator<String>>>(new KeyValuePairComparator<String, Iterator<String>>());
		for (int j = 0; j < readers.size(); j++) {
			Iterator<String> iterator = readers.get(j).iterator();
			if (iterator.hasNext()) {
				t.add(new KeyValuePair<String, Iterator<String>>(iterator.next(), iterator));
			}
		}
		
		// Merge
		ExternalFileWriter externalFileWriter = new ExternalFileWriter(String.format("%s.sorted", fileName)); 
		while (!t.isEmpty()) {
			KeyValuePair<String, Iterator<String>> current = t.pollFirst();
			externalFileWriter.write(current.getKey());
			if (current.getValue().hasNext()) {
				t.add(new KeyValuePair<String, Iterator<String>>(current.getValue().next(), current.getValue()));
			}
		}
		
		for (ExternalFileReader reader : readers) {
			reader.close();
		}
		externalFileReader.close();
		externalFileWriter.close();
	}

}

