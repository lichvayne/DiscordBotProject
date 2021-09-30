package com.example.discordbot.Messages;


import com.example.discordbot.BotAudio.YoutubeSearchSystem;
import com.example.discordbot.GenshinImpact.Genshin;
import com.example.discordbot.ServerTextChannels.ServerTextChannels;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static com.example.discordbot.DiscordBotApplication.api;

public class DiscordBotMessages extends Thread implements MessageCreateListener {
    public static boolean stop = false;
    Genshin genshin = new Genshin();
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        Message message = event.getMessage();



        if (message.getContent().equalsIgnoreCase(".გაჩერება")) {
            if(!stop) {
                stop = true;
                event.getChannel().sendMessage("სიმღერა გაჩერდა!").thenAccept(message2 -> {
                    message2.addReactionAddListener(reactionEvent -> {
                        if (reactionEvent.getEmoji().equalsEmoji("👎")) {
                            reactionEvent.deleteMessage();
                        }
                    }).removeAfter(30, TimeUnit.MINUTES);
                });
            }else event.getChannel().sendMessage("სიმღერა ისედაც გაჩერებულია მაგრამ შენ ყრუ ხარ!");
        }
        if (message.getContent().equalsIgnoreCase("გამარჯობა")) {
            event.getChannel().sendMessage("გაგიმარჯოს").thenAccept(message1 -> {
                message1.addReactionAddListener(reactionEvent -> {

                    if (reactionEvent.getEmoji().equalsEmoji("👎")) {
                        reactionEvent.deleteMessage();
                    }
                }).removeAfter(30, TimeUnit.MINUTES);
            });
        }
        if (message.getContent().equalsIgnoreCase("გიყვარვარ თუ გკიდივარ?")) {
            Random rand = new Random();
            int random = rand.nextInt(25-1) + 1;
            if(random<=5) {
                event.getChannel().sendMessage("მიყვარხარ! \uD83D\uDE18").thenAccept(message1 -> {
                    message1.addReactionAddListener(reactionEvent -> {

                        if (reactionEvent.getEmoji().equalsEmoji("👎")) {
                            reactionEvent.deleteMessage();
                        }
                    }).removeAfter(30, TimeUnit.MINUTES);
                });
            }
            if(random>5) {
                event.getChannel().sendMessage("მკიდიხარ!").thenAccept(message1 -> {
                    message1.addReactionAddListener(reactionEvent -> {

                        if (reactionEvent.getEmoji().equalsEmoji("👎")) {
                            reactionEvent.deleteMessage();
                        }
                    }).removeAfter(30, TimeUnit.MINUTES);
                });
            }

        }
        if (message.getContent().equalsIgnoreCase("მპუ")) {
            event.getChannel().sendMessage("მპუ \uD83D\uDE18").thenAccept(message2 -> {
                message2.addReactionAddListener(reactionEvent -> {
                    if (reactionEvent.getEmoji().equalsEmoji("👎")) {
                        reactionEvent.deleteMessage();
                    }
                }).removeAfter(30, TimeUnit.MINUTES);
            });
        }
        if (message.getContent().equalsIgnoreCase(".ბრძანებები")) {
          botCommands();
        }

        String genshinCommand = event.getMessageContent();
        String characterName;
        if (genshinCommand.contains(".genshin")) {
            characterName = genshinCommand.substring(8).trim();
            genshin.containsCharacter(characterName);
        }

    }public void botCommands() {
        EmbedBuilder commands = new EmbedBuilder();
        ServerTextChannels textChannel = new ServerTextChannels();
        commands.setTitle("მეკობოტის ბრძანებები")
                .setColor(Color.RED)
                .setDescription("გიყვარვარ თუ გკიდივარ?\n.სიმღერა სახელი\n.გაჩერება\n.genshin სახელი\nგამარჯობა\nმპუ");
        textChannel.getBotTest().sendMessage(commands);
    }

    public void welcomeText() {
        api.addServerMemberJoinListener(event -> {
            Optional<TextChannel> channel = api.getTextChannelById(570343070995054594L);
            channel.ifPresent(textChannel -> textChannel.sendMessage("მოგესალმებით სერვერზე, " + event.getUser().getMentionTag() + "!"));
        });
    }
}
