package com.example.discordbot.BotAudio;

import io.sfrei.tracksearch.clients.MultiSearchClient;
import io.sfrei.tracksearch.clients.MultiTrackSearchClient;
import io.sfrei.tracksearch.exceptions.TrackSearchException;
import io.sfrei.tracksearch.tracks.Track;
import io.sfrei.tracksearch.tracks.TrackList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class YoutubeSearchSystem {
    Queue<String> urlQueue = new LinkedList<>();
    Queue<String> songQueue = new LinkedList<>();
    public String search(String songName) throws TrackSearchException {
        MultiTrackSearchClient searchClient = new MultiSearchClient();
        TrackList<Track> tracksForSearch = searchClient.getTracksForSearch(songName);
        BotSongInfo songInfo = new BotSongInfo();
        String parseURL = tracksForSearch.getTracks().subList(0, 1).toString();
        String url = tracksForSearch.getTracks().subList(0, 1).toString().substring(parseURL.indexOf("https://"), parseURL.indexOf(")]"));
        String source = parseURL.substring(parseURL.indexOf("source=") + 7, parseURL.indexOf("title=")).trim();
        String title = parseURL.substring(parseURL.indexOf("title=") + 6, parseURL.indexOf("length=")).trim();
        songInfo.sendSongInfo(source,title,url);
        urlQueue.add(url);
        songQueue.add(title);
        return url;
    }
    public String getSongFromQueue(){
        songQueue.poll();
        return urlQueue.poll();
    }

}
