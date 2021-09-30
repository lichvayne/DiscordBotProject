package com.example.discordbot;


import com.example.discordbot.BotAudio.DiscordBotAudio;

import com.example.discordbot.BotAudio.YoutubeSearchSystem;
import com.example.discordbot.GenshinImpact.Genshin;
import com.example.discordbot.Messages.DiscordBotMessages;
import com.example.discordbot.TasksOnTime.BotTaskOnTime;
import io.sfrei.tracksearch.exceptions.TrackSearchException;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.interaction.SlashCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DiscordBotApplication {

    public static DiscordApi api = new DiscordApiBuilder().setToken("ODg1NTg4NzQ3OTA1Nzk0MDUw.YTpOyg.sndT8vn3nFYFz6FnQRmARLPFsDI")
                .login().join();


    public static void main(String[] args) throws InterruptedException, TrackSearchException {
        SpringApplication.run(DiscordBotApplication.class, args);

        /** ბოტის მთავარი ბრძანება: /account */
        SlashCommand.with("account", "A simple ping pong command!").createGlobal(api).join();

        /** ამატებს DiscordBotMessages-ში გაწერილ მესიჯებს */
        api.addListener(new DiscordBotMessages());
        api.addListener(new DiscordBotAudio());

        /** ქმნის ობიექტს და ბეჭდავს ტექსტს
         * "მოგესალმებით სერვერზე, @USER "  */
        DiscordBotMessages welcomeText = new DiscordBotMessages();
        welcomeText.welcomeText();
//        BotTaskOnTime botTaskOnTime = new BotTaskOnTime();
//        botTaskOnTime.goodMorning();


    }
}
