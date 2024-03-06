package me.rejomy.killcontrol.listener;

import me.rejomy.killcontrol.Main;
import me.rejomy.killcontrol.check.CheckHandler;
import org.bukkit.Statistic;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity killer = event.getDamager(),
                entity = event.getEntity();

        if (Main.getInstance().getConfig().getStringList("ignore worlds").contains(entity.getWorld().getName())
        || !(killer instanceof Player) || !(entity instanceof Player)
        || (((Player) entity).getHealth() - event.getFinalDamage() > 0)
        || ((Player) killer).getAddress() == null
        || ((Player) entity).getAddress() == null) {
            return;
        }

        CheckHandler checkHandler = new CheckHandler();
        checkHandler.handle((Player) killer, (Player) entity);

        if(checkHandler.getResult() == CheckHandler.Result.CANCEL) {
            int kills = ((Player) killer).getStatistic(Statistic.PLAYER_KILLS);
            ((Player) killer).setStatistic(Statistic.PLAYER_KILLS, kills - 1);
        }

    }

}
