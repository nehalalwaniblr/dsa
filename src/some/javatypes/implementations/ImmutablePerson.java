package some.javatypes.implementations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ImmutablePerson {          // 1. final class — prevent subclassing

    private final String name;                // 2. all fields final and private
    private final List<String> hobbies;       // 3. mutable fields need special handling

    public ImmutablePerson(String name, List<String> hobbies) {
        this.name = name;
        this.hobbies = List.copyOf(hobbies);  // 4. defensive copy in constructor
    }

    public String getName() {
        return name;
    }

    public List<String> getHobbies() {
        return Collections.unmodifiableList(hobbies); // 5. never return mutable reference
    }

    // 6. No setters

    public static void main(String[] args) {
        ImmutablePerson immutablePerson1 = new ImmutablePerson("Neha", Arrays.asList(new String[] {"painting"}));
        immutablePerson1.hobbies.add("painting");
    }
}