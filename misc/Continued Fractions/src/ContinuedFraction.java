import java.util.*;

class ContinuedFraction {
    private List<Long> terms;

    public ContinuedFraction() {
        terms = new ArrayList<Long>();
    }

    public void add(long c) {
        terms.add(c);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int j = 0; j < terms.size(); j++) {
            sb.append(terms.get(j));
            if (j < terms.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
