package com.kamilereon.storyteller.schedulers;

import com.kamilereon.storyteller.main.StoryTellerMain;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class Schedule {
    private int tick = 0;
    private BukkitTask task;

    public static Schedule createSchedule() {
        Schedule schedule = new Schedule();
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(StoryTellerMain.getPlugin(StoryTellerMain.class), () -> schedule.tick++ , 0, 1);
        schedule.setTask(task);
        return schedule;
    }

    private void setTask(BukkitTask task) {
        if(task != null) {
            this.task = task;
        }
        destroy();
        this.task = task;
    }

    public int getTick() { return tick; }

    public void destroy() {
        if(task == null) return;
        task.cancel();
    }
}
