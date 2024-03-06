package me.rejomy.killcontrol;

import me.rejomy.killcontrol.check.CheckManager;
import me.rejomy.killcontrol.command.KillControlCommand;
import me.rejomy.killcontrol.listener.ConnectionListener;
import me.rejomy.killcontrol.listener.EntityDamageListener;
import me.rejomy.killcontrol.util.Victim;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {

    public HashMap<Player, ArrayList<Victim>> kills = new HashMap<>();

    private static Main INSTANCE;

    public static Main getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        init();

        registerListener(new EntityDamageListener());
        registerListener(new ConnectionListener());

        getCommand("killcontrol").setExecutor(new KillControlCommand());
    }

    public void init() {
        reloadConfig();
        saveDefaultConfig();
    }

    private void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

}