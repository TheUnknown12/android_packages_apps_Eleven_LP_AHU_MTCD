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
import android.util.Log;

import com.cyanogenmod.eleven.utils.MusicUtils;

/**
 * Used to control Android Head Unitboot info

 */
public class AhuBootReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = true;
    private static final String TAG = "AhuBootReceiver";




    private  static final String ACTION_AHU_KEYDOWN = "com.microntek.irkeyDown";




    /**
     * {@inheritDoc}
     */
    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (DEBUG) {
            Log.v(TAG, "Received intent: " + intent + ":" + intent.getAction() + ":" + intent.getStringExtra("class"));
        }
        if (!MusicUtils.isPlaybackServiceConnected()) {

            return;
        }



        final String action = intent.getAction();
        if (action.equals("com.microntek.eqchange")) {
            //do nothing for now
        } else if (action.equals("com.microntek.bootcheck")) {
            final String stringExtra = intent.getStringExtra("class");
            if (!stringExtra.equals("com.microntek.music")) {
                if (stringExtra.equals("phonecallin")) {
                    MusicPlaybackService.mBtLock = true;
                } else if (stringExtra.equals("phonecallout")) {
                    MusicPlaybackService.mBtLock = false;
                } else {
                    final Intent i = new Intent(context, MusicPlaybackService.class);
                    i.setAction(MusicPlaybackService.SHUTDOWN);
                    context.sendBroadcast(i);
                }
            }
        }
    }






}
