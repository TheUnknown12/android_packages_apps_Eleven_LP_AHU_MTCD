package com.cyanogenmod.eleven.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * A Collection of helpers to update AndroidHEadUnit widgets
 * @author timojaas
 */

public class AhuUtils {
    public static final int AHU_WIDGET_STATE_PAUSE = 2;
    public static final int AHU_WIDGET_STATE_PLAY = 1;
    public static void sendTimeInfo(final Context context, long curTime, long duration) {


        final Intent intent = new Intent("com.microntek.musictime");
        intent.putExtra("currenttime", (int) curTime);

        intent.putExtra("duratime",(int) duration);




        context.sendBroadcast(intent);


    }
    public static void updateWidgetState(final Context context, Boolean isPlaying) {
        if (isPlaying) {
            sendStateInfo(context, AHU_WIDGET_STATE_PLAY);
        } else {
            sendStateInfo(context, AHU_WIDGET_STATE_PAUSE);
        }
    }
    public static void updateWidgetTitle(final Context context, String artistName, String trackName) {
        final Intent intent = new Intent("com.microntek.musictitle");
        intent.putExtra("musicindex", MusicUtils.getQueuePosition());

        intent.putExtra("musictitle", artistName + " -  " +  trackName);
        context.sendBroadcast(intent);

        Log.i("AhuUtils", "Sending updatemusictitle: "+ artistName + " -  " +  trackName);
    }
    public static void sendStateInfo(final Context context,final int state) {
        final Intent intent = new Intent("com.microntek.playpausemusic");
        intent.putExtra("playstate", state);
        Log.i("AhuUtils", "Sending playstate: "+ state);
        context.sendBroadcast(intent);

    }



    public static void removeWidget(final Context context) {
        final Intent intent = new Intent("com.android.MTClauncher.action.INSTALL_WIDGETS");
        intent.putExtra("myWidget.action", 10521);
        intent.putExtra("myWidget.packageName", "com.microntek.music");
        context.sendBroadcast(intent);
        Log.i("AhuUtils", "Removing widgets");
    }
    public static void addWdiget(final Context context) {
        final Intent intent = new Intent("com.android.MTClauncher.action.INSTALL_WIDGETS");
        intent.putExtra("myWidget.action", 10520);
        intent.putExtra("myWidget.packageName", "com.microntek.music");
        context.sendBroadcast(intent);
        Log.i("AhuUtils", "adding widgets");

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
    public static void updateWidgetAlbum(final Context context, Long albumId, Long trackId) {
        final Intent intent = new Intent("com.microntek.musicalumb");
        intent.putExtra("album_id", albumId);

        intent.putExtra("song_id", trackId);
        context.sendBroadcast(intent);
        Log.i("AhuUtils", "Sending albumupdate: " +albumId + "-" + trackId );
    }







    public static void sendCanbusinfo(Context context, long curTime, long duration, int playListSize, int currentIndex, String artistName, String trackName) {
        final Intent intent = new Intent("com.microntek.canbusdisplay");
        intent.putExtra("type", "music");
        intent.putExtra("all", playListSize);
        intent.putExtra("cur", currentIndex);
        if (duration <= 0) {
            intent.putExtra("time", 0);
        }
        else {
            intent.putExtra("time", (int) curTime);
        }
        //Following is used by custom canbus apk
        intent.putExtra("artistname", artistName);
        intent.putExtra("trackname", trackName);
        context.sendBroadcast(intent);
    }

}
