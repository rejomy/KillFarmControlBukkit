package me.rejomy.killcontrol.check.impl.same;

import me.rejomy.killcontrol.Main;
import me.rejomy.killcontrol.check.Check;
import me.rejomy.killcontrol.util.Victim;
import org.bukkit.entity.Player;

public class LocationCheck extends Check {

    public LocationCheck(Player killer, Victim victim) {
        super(killer, victim, CheckType.LOCATION, Main.getInstance().getConfig().getInt("check.same.location"));
    }

    @Override
    public boolean check() {
        return victim.lastDeathLocation != null && victim.deathLocation.distance(victim.lastDeathLocation) < 10;
    }

}
