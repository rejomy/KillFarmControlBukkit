package me.rejomy.killcontrol.util;

import me.rejomy.killcontrol.check.Check;

import java.util.HashMap;

public class Violation {

    private static HashMap<Check.CheckType, Integer> violations = new HashMap<>();

    public static int get(Check.CheckType checkType) {
        return violations.getOrDefault(checkType, 0);
    }

    public static void set(Check.CheckType checkType, int amount) {
        violations.put(checkType, amount);
    }

    public static void add(Check.CheckType checkType, int amount) {
        set(checkType, get(checkType) + amount);
    }

    public static void remove(Check.CheckType checkType, int amount) {
        set(checkType, Math.max(get(checkType) - amount, 0));
    }

}
