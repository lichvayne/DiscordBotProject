package com.example.discordbot.Messages;


import com.example.discordbot.BotAudio.YoutubeSearchSystem;
import com.example.discordbot.GenshinImpact.Genshin;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import static com.example.discordbot.DiscordBotApplication.api;

public class DiscordBotMessages extends Thread implements MessageCreateListener {
    public static boolean stop = false;
    public String characterName;
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        Message message = event.getMessage();
        Genshin genshin = new Genshin();



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
        if (message.getContent().equalsIgnoreCase("მპუ")) {
            event.getChannel().sendMessage("მპუ \uD83D\uDE18").thenAccept(message2 -> {
                message2.addReactionAddListener(reactionEvent -> {
                    if (reactionEvent.getEmoji().equalsEmoji("👎")) {
                        reactionEvent.deleteMessage();
                    }
                }).removeAfter(30, TimeUnit.MINUTES);
            });
        }
            String characterName = message.getContent().toLowerCase();
           genshin.getCharacter(characterName);

    }

    public void welcomeText() {
        api.addServerMemberJoinListener(event -> {
            Optional<TextChannel> channel = api.getTextChannelById(570343070995054594L);
            channel.ifPresent(textChannel -> textChannel.sendMessage("მოგესალმებით სერვერზე, " + event.getUser().getMentionTag() + "!"));
        });
    }
}
