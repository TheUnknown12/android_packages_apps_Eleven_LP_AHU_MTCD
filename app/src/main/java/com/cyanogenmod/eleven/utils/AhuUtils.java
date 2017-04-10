package com.cyanogenmod.eleven.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * A Collection of helpers to update AndroidHEadUnit widgets
 * @author timojaas
 */

public class AhuUtils {

    public static void sendTimeInfo(final Context context, long secs, long duration) {


        final Intent intent = new Intent("com.microntek.musictime");
        intent.putExtra("currenttime", (int) secs);

        intent.putExtra("duratime",(int) duration);



        context.sendBroadcast(intent);


    }

    public static void sendStateInfo(final Context context,final int n) {
        final Intent intent = new Intent("com.microntek.playpausemusic");
        intent.putExtra("playstate", n);
        context.sendBroadcast(intent);

    }
    public static void sendTitleInfo(final Context context) {
        final Intent intent = new Intent("com.microntek.musictitle");
        intent.putExtra("musicindex", MusicUtils.getQueuePosition());

        intent.putExtra("musictitle", MusicUtils.getArtistName() + " -  " +  MusicUtils.getTrackName());
        context.sendBroadcast(intent);

    }


    public static void removeWidget(final Context context) {
        final Intent intent = new Intent("com.android.MTClauncher.action.INSTALL_WIDGETS");
        intent.putExtra("myWidget.action", 10521);
        intent.putExtra("myWidget.packageName", "com.microntek.music");
        context.sendBroadcast(intent);
    }
    public static void addWdiget(final Context context) {
        final Intent intent = new Intent("com.android.MTClauncher.action.INSTALL_WIDGETS");
        intent.putExtra("myWidget.action", 10520);
        intent.putExtra("myWidget.packageName", "com.microntek.music");
        context.sendBroadcast(intent);

    }
    public static  void sendCanBusMusicOff(final Context context) {
        final Intent intent = new Intent("com.microntek.canbusdisplay");
        intent.putExtra("type", "music-off");
        context.sendBroadcast(intent);
    }

    public static void sendCanBusMusicOn(final Context context) {
        final Intent intent = new Intent("com.microntek.canbusdisplay");
        intent.putExtra("type", "music-on");
        context.sendBroadcast(intent);
    }
    public static void sendAlbumUpdate(final Context context) {
        final Intent intent = new Intent("com.microntek.musicalumb");
        intent.putExtra("album_id", MusicUtils.getCurrentAlbumId());

        intent.putExtra("song_id", MusicUtils.getCurrentAudioId());
        context.sendBroadcast(intent);
    }

}
