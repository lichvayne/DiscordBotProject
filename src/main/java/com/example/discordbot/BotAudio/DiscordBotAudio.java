package com.example.discordbot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class DiscordBotAudio implements MessageCreateListener {
    private static final Logger logger = LogManager.getLogger(DiscordBotAudio.class);
    public static String songURL = " ";

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String cmd = event.getMessageContent();
        if (cmd.contains("!სიმღერა")) {
            try {
                URL url = new URL(cmd.substring(8,cmd.length()).trim());
                songURL = url.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            cmdSound(event);
        }
    }



    private static void cmdSound(MessageCreateEvent event) {

        User user = event.getMessage().getAuthor().asUser().get();

        long userVoiceChannelId = 0;
        List<ServerVoiceChannel> voiceChannels = event.getServer().get().getVoiceChannels();
        for (ServerVoiceChannel chan : voiceChannels) {
            if (user.isConnected(chan)) {
                userVoiceChannelId = chan.getId();
            }
        }
        // If User is connected
        if (userVoiceChannelId != 0) {

            try {

                /** უკავშირდება ჩენელს სადაც USER არი */
                ServerVoiceChannel channel = event.getApi().getServerVoiceChannelById(userVoiceChannelId).get();
                channel.connect().thenAccept(AudioConnection -> {

                    /** ქმნის აუდიო ფლეიერს */
                    AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
                    playerManager.registerSourceManager(new YoutubeAudioSourceManager());
                    AudioPlayer player = playerManager.createPlayer();


                    AudioSource source = new LavaplayerAudioSource(event.getApi(), player);
                    AudioConnection.setAudioSource(source);


                    playerManager.loadItem(songURL, new AudioLoadResultHandler() {
                        @Override
                        public void trackLoaded(AudioTrack track) {
                            player.playTrack(track);
                        }

                        @Override
                        public void playlistLoaded(AudioPlaylist playlist) {
                            for (AudioTrack track : playlist.getTracks()) {
                                player.playTrack(track);
                            }
                        }

                        @Override
                        public void noMatches() {

                        }

                        @Override
                        public void loadFailed(FriendlyException throwable) {

                        }
                    });

                }).exceptionally(e -> {

                    e.printStackTrace();
                    return null;
                });

            } catch (Exception ex) {
                logger.error(ex.getMessage());
                logger.error(ex.getStackTrace());
            }
        }
    }

}