package me.rejomy.killcontrol.check;

import me.rejomy.killcontrol.Main;
import me.rejomy.killcontrol.util.Victim;
import me.rejomy.killcontrol.util.Violation;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CheckHandler {

    public enum Result {
        CANCEL,
        NONE,
    }

    private Result result = Result.NONE;

    public Result getResult() {
        return result;
    }

    public void handle(Player killer, Player target) {

        ArrayList<Victim> victims = Main.getInstance().kills.get(killer);

        Victim victim = victims.stream().filter(
                target2 -> target2.name.equals(target.getName())
        ).findFirst().orElse(null);

        if(victim == null) {
            victim = new Victim(target);
        }

        victim.deaths+=1;
        victim.timeLapse.add(System.currentTimeMillis());
        victim.lastDeathLocation = victim.deathLocation;
        victim.deathLocation = target.getLocation();

        victim.deaths += 1;
        victim.timeLapse.add(System.currentTimeMillis());

        Main.getInstance().kills.put(killer, victims);

        CheckManager checkManager = new CheckManager();

        for (Check check : checkManager.getChecks(killer, victim)) {
            if (check.violation <= 0 || !check.check()) continue;

            System.out.println("[KC] Check -> " + check.checkType);

            ConfigurationSection groups = Main.getInstance().getConfig().getConfigurationSection("action");

            for (String group : groups.getKeys(false)) {
                List<String> modules = groups.getStringList(group + ".module");

                if (!modules.contains(check.checkType.name().toLowerCase())) continue;

                int sumVl = 0;

                for (String module : modules) {
                    sumVl += Violation.get(Check.CheckType.valueOf(module.toUpperCase()));
                }

                ConfigurationSection thresholds = groups.getConfigurationSection(group + ".threshold");

                for (String violationLevel : thresholds.getKeys(false)) {
                    int vl = Integer.parseInt(violationLevel.replaceAll("-[0-9]+", ""));
                    int repeatVl = Integer.parseInt(violationLevel.replaceAll(".+[^0-9]", ""));

                    if ((vl < sumVl || vl > sumVl + check.violation)
                    && (vl > sumVl && vl % repeatVl == 0 || vl < sumVl)) continue;

                    String command = thresholds.getString(violationLevel).replace("$killer", killer.getName());

                    if(command.equalsIgnoreCase("cancel")) {
                        result = Result.CANCEL;
                    }

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command
                            );
                }
            }

            Violation.add(check.checkType, check.violation);
        }

    }

}
