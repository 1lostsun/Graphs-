import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class GaleShapleyTest {

    @Test
    void testStableMarriage() {
        List<Person> men = new ArrayList<>();
        List<Person> women = new ArrayList<>();

        Person m1 = new Person("M1");
        Person m2 = new Person("M2");
        Person m3 = new Person("M3");

        Person w1 = new Person("W1");
        Person w2 = new Person("W2");
        Person w3 = new Person("W3");

        men.add(m1);
        men.add(m2);
        men.add(m3);

        women.add(w1);
        women.add(w2);
        women.add(w3);

        m1.addPreference(w1);
        m1.addPreference(w2);
        m1.addPreference(w3);

        m2.addPreference(w2);
        m2.addPreference(w1);
        m2.addPreference(w3);

        m3.addPreference(w1);
        m3.addPreference(w2);
        m3.addPreference(w3);

        w1.addPreference(m1);
        w1.addPreference(m2);
        w1.addPreference(m3);

        w2.addPreference(m2);
        w2.addPreference(m1);
        w2.addPreference(m3);

        w3.addPreference(m1);
        w3.addPreference(m2);
        w3.addPreference(m3);

        Map<Person, Person> matches = GaleShapley.stableMarriage(men, women);

        assertEquals(w1, matches.get(m1));
        assertEquals(w2, matches.get(m2));
        assertEquals(w3, matches.get(m3));
    }
}