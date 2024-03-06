package me.rejomy.killcontrol.check.impl;

import me.rejomy.killcontrol.Main;
import me.rejomy.killcontrol.check.Check;
import me.rejomy.killcontrol.util.TimeUtil;
import me.rejomy.killcontrol.util.Victim;
import org.bukkit.entity.Player;

public class DeathsCheck extends Check {

    public DeathsCheck(Player killer, Victim victim) {
        super(killer, victim, CheckType.DEATHS, Main.getInstance().getConfig().getInt("check.kills per victim.violation"));
    }

    @Override
    public boolean check() {
        TimeUtil timeUtil = new TimeUtil(Main.getInstance().getConfig().getString("check.kills per victim.time"));

        if(victim.deaths < Main.getInstance().getConfig().getInt("check.kills per victim.kills")) return false;

        long time = victim.timeLapse.size() > victim.deaths?
                victim.timeLapse.get(victim.timeLapse.size() - victim.deaths - 1)
                : victim.timeLapse.get(victim.deaths - 1);

        if(System.currentTimeMillis() - time > timeUtil.getTimeInMillis()) {
            victim.deaths = 0;
            return false;
        }

        return true;
    }
}
