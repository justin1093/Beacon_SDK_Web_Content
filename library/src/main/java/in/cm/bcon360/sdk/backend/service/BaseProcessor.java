/*
 * Copyright (c) 2016, Upnext Technologies Sp. z o.o.
 * All rights reserved.
 *
 * This source code is licensed under the BSD 3-Clause License found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

package in.cm.bcon360.sdk.backend.service;

import android.app.IntentService;
import android.content.Context;

import in.cm.bcon360.sdk.backend.BeaconControlManager;
import in.cm.bcon360.sdk.backend.BeaconControlManagerImpl;
import in.cm.bcon360.sdk.backend.events.EventsManager;
import in.cm.bcon360.sdk.core.BeaconPreferences;
import in.cm.bcon360.sdk.core.BeaconPreferencesImpl;
import in.cm.bcon360.sdk.core.Config;
import in.cm.bcon360.sdk.core.ConfigImpl;

public abstract class BaseProcessor extends IntentService {

    private static final String TAG = BaseProcessor.class.getSimpleName();

    protected EventsManager eventsManager;

    public BaseProcessor(String serviceName) {
        super("BaseProcessor");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Context appContext = getApplicationContext();
        Config config = ConfigImpl.getInstance(appContext);
        BeaconPreferences preferences = BeaconPreferencesImpl.getInstance(appContext);
        BeaconControlManager beaconControlManager = BeaconControlManagerImpl.getInstance(appContext, config, preferences);

        eventsManager = EventsManager.getInstance(appContext, config, preferences, beaconControlManager);
    }
}