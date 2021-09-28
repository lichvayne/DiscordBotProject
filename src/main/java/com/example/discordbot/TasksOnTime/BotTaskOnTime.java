package com.example.discordbot.TasksOnTime;

import com.example.discordbot.DiscordBotApplication;
import com.example.discordbot.ServerTextChannels.ServerTextChannels;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class BotTaskOnTime extends DiscordBotApplication {
    ServerTextChannels textChannel =new ServerTextChannels();

    public void goodMorning() {
        Timer goodMorningTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                textChannel.getGeneral().sendMessage("დილამშვიდობის ახალგაზრდებო!");
            }
        };goodMorningTimer.schedule(task, goodMorningDate().getTime(), 1000 * 60 * 60 * 24);
    }

    public Calendar goodMorningDate() {
        Calendar goodMorning = Calendar.getInstance();
        goodMorning.set(Calendar.HOUR_OF_DAY, 10);
        goodMorning.set(Calendar.MINUTE, 0);
        goodMorning.set(Calendar.SECOND, 0);
        goodMorning.set(Calendar.MILLISECOND, 0);
        return goodMorning;
    }

}






