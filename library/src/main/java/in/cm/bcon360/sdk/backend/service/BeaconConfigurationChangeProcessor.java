/*
 * Copyright (c) 2016, Upnext Technologies Sp. z o.o.
 * All rights reserved.
 *
 * This source code is licensed under the BSD 3-Clause License found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

package in.cm.bcon360.sdk.backend.service;

import android.content.Intent;

import in.cm.bcon360.sdk.internal.model.BeaconsList;
import in.cm.bcon360.sdk.util.ULog;

/**
 * Service responsible for notifying client that new beacon configuration has been just loaded from
 * the backend.
 */
public class BeaconConfigurationChangeProcessor extends BaseProcessor {

    private static final String TAG = BeaconConfigurationChangeProcessor.class.getSimpleName();

    public interface Extra {
        String BEACONS_LIST = "in.cm.bcon360.sdk.backend.service.BeaconConfigurationChangeProcessor.BEACONS_LIST";
    }

    public BeaconConfigurationChangeProcessor() {
        super("BeaconConfigurationChangeProcessor");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ULog.d(TAG, "onHandleIntent.");
        eventsManager.processConfigurationLoaded((BeaconsList) intent.getSerializableExtra(Extra.BEACONS_LIST));
    }
}