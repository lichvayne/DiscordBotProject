package com.example.discordbot.TasksOnTime;

import com.example.discordbot.DiscordBotApplication;
import org.javacord.api.entity.channel.TextChannel;
import org.springframework.format.datetime.joda.LocalDateTimeParser;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TimeTask extends DiscordBotApplication{
    
    /**
     * general ჩენელის ID: 570343070995054594L;
     * bot-test ჩენელის ID: 885589647537881108L;
     * juzonebi ჩენელის ID: 570343481403506710L;
     */
    public void goodMorning(){
        while(true){
            LocalDateTime time = LocalDateTime.now();

            if(time.getHour() == 11 && time.getMinute() == 36)
                System.out.println(time.getMinute());


        }
    }

    }



