package me.rejomy.killcontrol.check.impl.same;

import me.rejomy.killcontrol.Main;
import me.rejomy.killcontrol.check.Check;
import me.rejomy.killcontrol.util.StringSimilarity;
import me.rejomy.killcontrol.util.Victim;
import org.bukkit.entity.Player;

public class NameCheck extends Check {

    public NameCheck(Player killer, Victim victim) {
        super(killer, victim, CheckType.NAME, Main.getInstance().getConfig().getInt("check.same.name.violation"));
    }

    @Override
    public boolean check() {
        StringSimilarity similarity = new StringSimilarity(killer.getName(), victim.name);

        return similarity.getPercent() > Main.getInstance().getConfig().getInt("check.same.name.similarity percent");
    }

}
