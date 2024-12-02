import java.util.*;

class Person {
    String name;
    List<Person> preferences;
    Person partner;

    Person(String name) {
        this.name = name;
        this.preferences = new ArrayList<>();
        this.partner = null;
    }

    void addPreference(Person person) {
        preferences.add(person);
    }

    boolean prefers(Person person) {
        return preferences.indexOf(person) < preferences.indexOf(partner);
    }

    @Override
    public String toString() {
        return name;
    }
}

class GaleShapley {
    public static Map<Person, Person> stableMarriage(List<Person> men, List<Person> women) {
        Map<Person, Person> matches = new HashMap<>();
        Queue<Person> freeMen = new LinkedList<>(men);

        while (!freeMen.isEmpty()) {
            Person man = freeMen.poll();
            for (Person woman : man.preferences) {
                if (woman.partner == null) {
                    matches.put(man, woman);
                    woman.partner = man;
                    break;
                } else if (woman.prefers(man)) {
                    freeMen.add(woman.partner);
                    matches.remove(woman.partner);
                    matches.put(man, woman);
                    woman.partner = man;
                    break;
                }
            }
        }

        return matches;
    }
}

public class StableMarriageExample {
    public static void main(String[] args) {
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

        System.out.println("Стабильные браки:");
        for (Map.Entry<Person, Person> entry : matches.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}