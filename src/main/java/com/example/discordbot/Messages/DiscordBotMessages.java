package com.example.discordbot.Messages;

import com.example.discordbot.DiscordBotApplication;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.Optional;

import static com.example.discordbot.DiscordBotApplication.api;

public class DiscordBotMessages extends DiscordBotApplication implements MessageCreateListener {
    /**
     * general ჩენელის ID: 570343070995054594L;
     * bot-test ჩენელის ID: 885589647537881108L;
     */

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        Message message = event.getMessage();

        if (message.getContent().equalsIgnoreCase("გამარჯობა")) {
            event.getChannel().sendMessage("გაგიმარჯოს");
        }
        if (message.getContent().equalsIgnoreCase("მპუ")) {
            event.getChannel().sendMessage("მპუ \uD83D\uDE18");
        }
    }
    public void welcomeText(){
        api.addServerMemberJoinListener(event -> {
            Optional<TextChannel> channel = api.getTextChannelById(570343070995054594L);
            channel.ifPresent(textChannel -> textChannel.sendMessage("მოგესალმებით სერვერზე, " + event.getUser().getMentionTag() + "!"));
        });
    }


}