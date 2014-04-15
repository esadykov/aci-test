package s.e.r.test.aci;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.*;

/**
 * @author ser
 * @since 15.04.14 23:34
 */
public class TopStringsBenchmark {

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    private static  List<String> words = new LinkedList<>();

    @BeforeClass
    public static void prepare() {
        Scanner scanner = new Scanner(ClassLoader.getSystemResourceAsStream("text.txt"));
        while (scanner.hasNext())
            words.add(scanner.next());
    }

    @Test
    @BenchmarkOptions(warmupRounds = 10, benchmarkRounds = 1000, concurrency = 16)
    public void testBigInput() {
        TopStrings.getTop10(words.iterator());
    }

}
