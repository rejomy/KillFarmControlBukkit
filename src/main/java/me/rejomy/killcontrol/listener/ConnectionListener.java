package me.rejomy.killcontrol.listener;

import me.rejomy.killcontrol.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Main.getInstance().kills.put(event.getPlayer(), new ArrayList<>());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Main.getInstance().kills.remove(event.getPlayer());
    }

}
