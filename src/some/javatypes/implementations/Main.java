package some.javatypes.implementations;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ImmutablePerson immutablePerson1 = new ImmutablePerson("Neha", Arrays.asList(new String[] {"painting"}));
        immutablePerson1.getHobbies().add("painting");
    }
}
