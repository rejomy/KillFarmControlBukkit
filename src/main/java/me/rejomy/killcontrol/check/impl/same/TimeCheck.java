package me.rejomy.killcontrol.check.impl.same;

import me.rejomy.killcontrol.Main;
import me.rejomy.killcontrol.check.Check;
import me.rejomy.killcontrol.util.Victim;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TimeCheck extends Check {
    public TimeCheck(Player killer, Victim victim) {
        super(killer, victim, CheckType.TIME, Main.getInstance().getConfig().getInt("check.same.time.violation"));
    }

    @Override
    public boolean check() {
        ArrayList<Long> timeLapse = victim.timeLapse;

        if(timeLapse.size() < Main.getInstance().getConfig().getInt("check.same.time.min values")) return false;

        long count = timeLapse.stream()
                .flatMapToLong(i -> timeLapse.stream()
                        .mapToLong(j -> Math.abs(i - j))) // Поиск разницы между парами чисел
                .filter(diff -> diff <= 3000 && diff > 0) // Фильтрация по условию погрешности
                .count(); // Подсчет количества подходящих пар

        return (double) count > timeLapse.size() / 2.0;
    }

}
