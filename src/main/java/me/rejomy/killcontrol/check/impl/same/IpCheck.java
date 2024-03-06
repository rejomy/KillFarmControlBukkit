package me.rejomy.killcontrol.check.impl.same;

import me.rejomy.killcontrol.Main;
import me.rejomy.killcontrol.check.Check;
import me.rejomy.killcontrol.util.Victim;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;

public class IpCheck extends Check {
    public IpCheck(Player killer, Victim victim) {
        super(killer, victim, CheckType.IP, Main.getInstance().getConfig().getInt("check.same.ip"));
    }

    @Override
    public boolean check() {
        InetSocketAddress killerAddress = killer.getAddress();
        InetSocketAddress victimAddress = victim.player.getAddress();

        return killerAddress != null && victimAddress != null && killerAddress.getAddress() == victimAddress.getAddress();
    }
}
