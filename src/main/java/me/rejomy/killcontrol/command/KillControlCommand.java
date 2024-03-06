package me.rejomy.killcontrol.command;

import me.rejomy.killcontrol.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class KillControlCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(
                    "\n" + "Available commands:" + "\n/killcontrol reload" + "\n");
            return false;
        }

        switch (args[0]) {
            case "reload":
                Main.getInstance().init();
                sender.sendMessage("KillControl -> Config has been reloaded!");
                break;
        }

        return false;
    }

}
