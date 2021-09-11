package com.example.discordbot.TasksOnTime;

import com.example.discordbot.DiscordBotApplication;
import org.javacord.api.entity.channel.TextChannel;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimeTask extends DiscordBotApplication {
    /**
     * general ჩენელის ID: 570343070995054594L;
     * bot-test ჩენელის ID: 885589647537881108L;
     */
    public void goodMorningText() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                TextChannel channel = api.getTextChannelById(570343070995054594L).get();
                channel.sendMessage("დილამშვიდობის ახალგაზრდებო!");
            }
        };
        timer.schedule(task, goodMorningDate().getTime(), 1000 * 60 * 60 * 24);
    }

    public Calendar goodMorningDate() {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 10);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date;
    }
}
