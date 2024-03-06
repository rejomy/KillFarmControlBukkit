package me.rejomy.killcontrol.check;

import me.rejomy.killcontrol.util.Victim;
import org.bukkit.entity.Player;

public abstract class Check {

    public int violation;
    public CheckType checkType;

    protected Player killer;
    protected Victim victim;

    public Check(Player killer, Victim victim, CheckType checkType, int violation) {
        this.killer = killer;
        this.victim = victim;
        this.checkType = checkType;
        this.violation = violation;
    }

    public abstract boolean check();

    public enum CheckType {

        IP,
        LOCATION,
        NAME,
        TIME,
        DEATHS,

    }

}
