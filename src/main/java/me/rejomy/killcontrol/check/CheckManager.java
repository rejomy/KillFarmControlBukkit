package me.rejomy.killcontrol.check;

import me.rejomy.killcontrol.check.impl.DeathsCheck;
import me.rejomy.killcontrol.check.impl.same.IpCheck;
import me.rejomy.killcontrol.check.impl.same.LocationCheck;
import me.rejomy.killcontrol.check.impl.same.NameCheck;
import me.rejomy.killcontrol.check.impl.same.TimeCheck;
import me.rejomy.killcontrol.util.Victim;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckManager {

    private ArrayList<Check> checks = new ArrayList<>();

    public ArrayList<Check> getChecks(Player killer, Victim victim) {

        add(
                new NameCheck(killer, victim),
                new IpCheck(killer, victim),
                new TimeCheck(killer, victim),
                new LocationCheck(killer, victim),
                new DeathsCheck(killer, victim)
        );

        return checks;

    }

    public void add(Check... checks) {
        this.checks.addAll(Arrays.asList(checks));
    }

}
