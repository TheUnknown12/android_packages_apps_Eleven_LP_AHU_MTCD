/*
 * Copyright (C) 2007 The Android Open Source Project Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.cyanogenmod.eleven;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.view.KeyEvent;

import com.cyanogenmod.eleven.utils.MusicUtils;

/**
 * Used to control Android Head Unit buttons for playback.
 *   Single press: pause/resume
 *   Double press: next track
 *   Triple press: previous track
 *   Long press: voice search
 */
public class AhuButtonIntentReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = true;
    private static final String TAG = "AhuButtonIntentRec";

    public static final int KEYCODE_AHU_BUTTON_PLAYPAUSE = 257;
    public static final int KEYCODE_AHU_BUTTON_STOP = 267;
    public static final int KEYCODE_AHU_BUTTON_PREV = 260;
    public static final int KEYCODE_AHU_BUTTON_PREV_1 = 276;
    public static final int KEYCODE_AHU_BUTTON_PREV_2 = 299;
    // Delayed?
    public static final int KEYCODE_AHU_BUTTON_PREV_3 = 311;
    public static final int KEYCODE_AHU_BUTTON_NEXT = 268;
    public static final int KEYCODE_AHU_BUTTON_NEXT_1 = 278;
    public static final int KEYCODE_AHU_BUTTON_NEXT_2 = 300;
    // Delayed? 300L
    public static final int KEYCODE_AHU_BUTTON_NEXT_3 = 312;
    public static final int KEYCODE_AHU_BUTTON_REPEAT_MODE = 271;
    //Set playing track to
    //TODO: should be delayed 1000L to catch double usage (pressing 1 two times inside one second = 11)
    public static final int KEYCODE_AHU_BUTTON_SETTRACK_1 = 283;
    public static final int KEYCODE_AHU_BUTTON_SETTRACK_2 = 284;
    public static final int KEYCODE_AHU_BUTTON_SETTRACK_3 = 285;
    public static final int KEYCODE_AHU_BUTTON_SETTRACK_4 = 286;
    public static final int KEYCODE_AHU_BUTTON_SETTRACK_5 = 287;
    public static final int KEYCODE_AHU_BUTTON_SETTRACK_6 = 288;
    public static final int KEYCODE_AHU_BUTTON_SETTRACK_7 = 289;
    public static final int KEYCODE_AHU_BUTTON_SETTRACK_8 = 290;
    public static final int KEYCODE_AHU_BUTTON_SETTRACK_9 = 291;
    public static final int KEYCODE_AHU_BUTTON_SETTRACK_0 = 292;
    public static final int KEYCODE_AHU_BUTTON_SETTRACK_CANCEL = 293;


    private  static final String ACTION_AHU_KEYDOWN = "com.microntek.irkeyDown";




    /**
     * {@inheritDoc}
     */
    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (!MusicUtils.isPlaybackServiceConnected()) {
            if (DEBUG) Log.v(TAG, "Received intent: " + intent + ":" +intent.getAction() + ":" + intent.getIntExtra("keyCode", 0) + " but no service running");
            return;
        }
        if (MusicPlaybackService.mBtLock) {
            if (DEBUG) Log.v(TAG, "Received intent: " + intent + ":" +intent.getAction() + ":" + intent.getIntExtra("keyCode", 0) + " but BTLock is on!");
            return;
        }
        if (DEBUG) Log.v(TAG, "Received intent: " + intent  + ":" +intent.getAction() + ":" + intent.getIntExtra("keyCode", 0));
        final String intentAction = intent.getAction();
        final int keyCode = intent.getIntExtra("keyCode", 0);

       if (ACTION_AHU_KEYDOWN.equals(intentAction)) {



            String command = null;
           String action = MusicPlaybackService.SERVICECMD;
            switch (keyCode) {
                case KEYCODE_AHU_BUTTON_STOP:
                    command = MusicPlaybackService.CMDSTOP;
                    break;

                case KEYCODE_AHU_BUTTON_PLAYPAUSE:
                    command = MusicPlaybackService.CMDTOGGLEPAUSE;
                    break;
                case KEYCODE_AHU_BUTTON_NEXT:
                case KEYCODE_AHU_BUTTON_NEXT_1:
                case KEYCODE_AHU_BUTTON_NEXT_2:
                case KEYCODE_AHU_BUTTON_NEXT_3:
                    command = MusicPlaybackService.CMDNEXT;
                    break;
                case KEYCODE_AHU_BUTTON_PREV:
                case KEYCODE_AHU_BUTTON_PREV_1:
                case KEYCODE_AHU_BUTTON_PREV_2:
                case KEYCODE_AHU_BUTTON_PREV_3:
                    command = MusicPlaybackService.CMDPREVIOUS;
                    break;
                case KEYCODE_AHU_BUTTON_REPEAT_MODE:
                    action = MusicPlaybackService.REPEAT_ACTION;
                    break;
                case KEYCODE_AHU_BUTTON_SETTRACK_0:
                case KEYCODE_AHU_BUTTON_SETTRACK_1:
                case KEYCODE_AHU_BUTTON_SETTRACK_2:
                case KEYCODE_AHU_BUTTON_SETTRACK_3:
                case KEYCODE_AHU_BUTTON_SETTRACK_4:
                case KEYCODE_AHU_BUTTON_SETTRACK_5:
                case KEYCODE_AHU_BUTTON_SETTRACK_6:
                case KEYCODE_AHU_BUTTON_SETTRACK_7:
                case KEYCODE_AHU_BUTTON_SETTRACK_8:
                case KEYCODE_AHU_BUTTON_SETTRACK_9:
                    command = MusicPlaybackService.CMDSETTRACK;
                    break;
            }
            if (command != null) {
                final Intent i = new Intent(context, MusicPlaybackService.class);
                i.setAction(action);
                i.putExtra(MusicPlaybackService.CMDNAME, command);
                if (MusicPlaybackService.CMDSETTRACK.equals(command)) {
                    i.putExtra("track", keyCode - 282);
                }
                i.putExtra(MusicPlaybackService.FROM_AHU_BUTTON, true);
                context.startService(i);


            }

        }
    }






}
