package com.example.discordbot;

import com.example.discordbot.Messages.DiscordBotMessages;
import com.example.discordbot.TasksOnTime.TimeTask;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.ServerTextChannelBuilder;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.server.Server;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.util.logging.ExceptionLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class DiscordBotApplication {
    /**
     * general ჩენელის ID: 570343070995054594L;
     * bot-test ჩენელის ID: 885589647537881108L;
     */

    /** TigerMafia-ს ბოტის ავტორიზაცია*/
    public static DiscordApi api = new DiscordApiBuilder()
            .setToken("ODg1NTg4NzQ3OTA1Nzk0MDUw.YTpOyg.Dqx-EFVtthtGGmdOjNUSpSPs3Go")
            .login().join();

    public static void main(String[] args) {
        SpringApplication.run(DiscordBotApplication.class, args);
        /** ბოტის მთავარი ბრძანება: /account */
        SlashCommand.with("account", "A simple ping pong command!").createGlobal(api).join();

        /** ამატებს DiscordBotMessages-ში გაწერილ მესიჯებს */
        api.addListener(new DiscordBotMessages());
        /** ქმნის ობიექტს და ბეჭდავს ტექსტს
         * "მოგესალმებით სერვერზე, @USER "  */
        DiscordBotMessages welcomeText = new DiscordBotMessages();
        welcomeText.welcomeText();

        /** ქმნის დილამშვიდობისას ობიექტს და ბეჭდავს ტექსტს
         * "დილამშვიდობის ახალგაზრდებო!" 10:00 საათზე */
        // TimeTask task = new TimeTask();
        // task.goodMorningText();


    }
}
