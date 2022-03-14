package com.kamilereon.storyteller.schedulers;

import com.kamilereon.storyteller.main.StoryTellerMain;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Schedule {
    private int tick = 0;
    private int taskId;

    public Schedule() {
        this.taskId = new BukkitRunnable() {
            public void run() {
                tick++;
            }
        }.runTaskTimer(StoryTellerMain.getPlugin(StoryTellerMain.class), 0, 1).getTaskId();
    }

    public static Schedule createSchedule() {
        Schedule schedule = new Schedule();
        return schedule;
    }

    public int getTick() { return tick; }

    public void destroy() {
        Bukkit.getScheduler().cancelTask(taskId);
    }
}
