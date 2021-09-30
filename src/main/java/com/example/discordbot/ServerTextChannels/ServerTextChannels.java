package com.example.discordbot.ServerTextChannels;

import com.example.discordbot.DiscordBotApplication;
import org.javacord.api.entity.channel.TextChannel;

public class ServerTextChannels extends DiscordBotApplication {
  private final TextChannel general = api.getTextChannelById(570343070995054594L).get();
  private final TextChannel botTest = api.getTextChannelById(885589647537881108L).get();
  private final TextChannel juzonebi = api.getTextChannelById(570343481403506710L).get();
  private final TextChannel genshin = api.getTextChannelById(892052501526872104L).get();


    public TextChannel getGeneral() {
        return general;
    }
    public TextChannel getBotTest() {
        return botTest;
    }
    public TextChannel getJuzonebi() {
        return juzonebi;
    }
    public TextChannel getGenshin() {
        return genshin;
    }
}
