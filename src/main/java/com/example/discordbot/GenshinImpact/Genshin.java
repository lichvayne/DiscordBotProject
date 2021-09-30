package com.example.discordbot.GenshinImpact;

import com.example.discordbot.ServerTextChannels.ServerTextChannels;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.*;
import java.util.ArrayList;


public class Genshin {
    private static HttpURLConnection connection;
    public ArrayList<String> characters = new ArrayList<>();

    public Genshin(){
        characterInitializer();

    }

    public void connect(String characterName) {
        try {
            URL url = new URL("https://genshinlist.com/character/"+characterName);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int status = connection.getResponseCode();
            if (status >= 200 && status <= 299) {
                parseInfo(characterName);

            }
            connection.disconnect();
        } catch (MalformedURLException e) {

        } catch (IOException e) {
        }
    }


    public void parseInfo(String characterName) {
        BufferedReader reader;
        String line;
        String gender = "";
        String birthday = "";
        String rarity = "";

        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {

                if (line.contains("Gender:")) {
                    gender = line.substring(line.indexOf("Gender:"), line.indexOf("</li>"));
                }
                if (line.contains("Birthday:")) {
                    birthday = line.substring(line.indexOf("Birthday:"), line.indexOf("</li>"));
                }
                if (line.contains("Rarity:")) {
                    rarity = line.substring(line.indexOf("Rarity:"), line.indexOf("</li>"));
                }

            }
            reader.close();

            createMessage(characterName, gender, birthday, rarity);

        } catch (IOException e) {

        }

    }
    /** ამატებს ქერექთერებს ლისტში */
    public void characterInitializer() {
        BufferedReader reader;
        String line;
        try {
            URL url = new URL("https://genshinlist.com/characters");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int status = connection.getResponseCode();
            if (status >= 200 && status <= 299) {
                try {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("https://genshinlist.com/character/"))
                            this.characters.add(line.substring(line.indexOf("character/"), line.indexOf("\"\">")).trim());
                    }
                    reader.close();
                } catch (IOException e) {

                }
            }
            connection.disconnect();
        } catch (MalformedURLException e) {

        } catch (IOException e) {
        }

    }

    public void containsCharacter(String characterName) {
        for(int i = 0;i<this.characters.size();i++){
            if(this.characters.get(i).contains(characterName)){
                connect(characterName);
            }
        }

    }

    public void createMessage(String characterName, String gender, String birthday, String rarity) {
        EmbedBuilder genshinEmbed = new EmbedBuilder();
        ServerTextChannels textChannel = new ServerTextChannels();
        genshinEmbed
                .setImage("https://genshinlist.com/assets/img/characters/" + characterName + ".png")
                .setColor(Color.RED)
                .setDescription(gender + "\n" + birthday + "\n" + rarity);
        textChannel.getGenshin().sendMessage(genshinEmbed);
    }

}


