package com.example.discordbot.BotAudio;

import com.example.discordbot.ServerTextChannels.ServerTextChannels;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;

public class BotSongInfo {
    ServerTextChannels textChannel = new ServerTextChannels();
    EmbedBuilder songInfo = new EmbedBuilder();

    public void sendSongInfo(String source, String title, String url) {
        songInfo.setTitle(title)
                .setColor(Color.RED)
                .addField(source, url);
        textChannel.getJuzonebi().sendMessage(songInfo);
    }


}
