/*
 * Copyright (c) 2016, Upnext Technologies Sp. z o.o.
 * All rights reserved.
 *
 * This source code is licensed under the BSD 3-Clause License found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

package in.cm.bcon360.sdk.backend.service;

import android.content.Intent;

import in.cm.bcon360.sdk.Beacon;
import in.cm.bcon360.sdk.util.ULog;

/**
 * Service responsible for notifying client that a beacon's proximity has changed
 */
public class BeaconProximityChangeProcessor extends BaseProcessor {

    private static final String TAG = BeaconProximityChangeProcessor.class.getSimpleName();

    public interface Extra {
        String BEACON = "in.cm.bcon360.sdk.backend.service.BeaconProximityChangeProcessor.BEACON";
    }

    public BeaconProximityChangeProcessor() {
        super("BeaconProximityChangeProcessor");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ULog.d(TAG, "onHandleIntent.");
        eventsManager.processBeaconProximityChanged((Beacon) intent.getSerializableExtra(Extra.BEACON));
    }
}