package com.example.discordbot.Messages;

import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class DiscordBotMessages implements MessageCreateListener {
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

}