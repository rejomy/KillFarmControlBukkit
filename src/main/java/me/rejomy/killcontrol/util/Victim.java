package me.rejomy.killcontrol.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Victim {

    public Player player;
    public String name;

    public int deaths = 0;

    public Location deathLocation, lastDeathLocation;

    public final ArrayList<Long> timeLapse = new ArrayList<>();

    public Victim(Player player) {
        this.player = player;
        this.name = player.getName();
    }

}
