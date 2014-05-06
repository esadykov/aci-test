package s.e.r.test.aci;

import java.util.*;

/**
 * @author ser
 * @since 15.04.14 23:12
 */
public class TopStrings {

    public static final int TEN = 10;

    public static Set<String> getTop10(final Iterator<String> strings) {

        final HashMap<String, Counter> unsortedMap = new HashMap<>();

        //count each string occurrences
        while (strings.hasNext()) {
            String item = strings.next();
            Counter counter = unsortedMap.get(item);
            if (counter !=  null) {
                counter.inc();
            }
            else
                unsortedMap.put(item, new Counter());
        }

        //comparator for sort by values
        Comparator<String> byValueComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                assert o1 != null;
                assert o2 != null;

                if (unsortedMap.get(o1).value < unsortedMap.get(o2).value)
                    return 1;
                else if (unsortedMap.get(o1).value > unsortedMap.get(o2).value)
                    return -1;
                else
                    return (o1.compareTo(o2));
            }
        };

        //sort strings in map by counters
        SortedSet<String> sortedSet = new TreeSet<>(byValueComparator);
        for (Map.Entry<String, Counter> entry : unsortedMap.entrySet())
        {
            sortedSet.add(entry.getKey());
            if (sortedSet.size() > TEN)
                sortedSet.remove(sortedSet.last());
        }

        return sortedSet;

    }

    static class Counter {
        int value = 1;

        void inc() { value++; }

        @Override
        public String toString() { return String.valueOf(value); }
    }
}
