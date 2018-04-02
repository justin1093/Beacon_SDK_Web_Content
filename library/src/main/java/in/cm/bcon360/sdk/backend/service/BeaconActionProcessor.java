/*
 * Copyright (c) 2016, Upnext Technologies Sp. z o.o.
 * All rights reserved.
 *
 * This source code is licensed under the BSD 3-Clause License found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

package in.cm.bcon360.sdk.backend.service;

import android.content.Intent;

import in.cm.bcon360.sdk.backend.model.GetConfigurationsResponse.Trigger;
import in.cm.bcon360.sdk.util.ULog;

/**
 * Service responsible for handling user-defined actions triggered by beacon/zone changes
 */
public class BeaconActionProcessor extends BaseProcessor {

    private static final String TAG = BeaconActionProcessor.class.getSimpleName();

    public interface Extra {
        String BEACON = "in.cm.bcon360.sdk.backend.service.BeaconIntentProcessor.BEACON";
        String TRIGGER = "in.cm.bcon360.sdk.backend.service.BeaconIntentProcessor.TRIGGER";
        String EVENT = "in.cm.bcon360.sdk.backend.service.BeaconIntentProcessor.EVENT";
    }

    public BeaconActionProcessor() {
        super("BeaconActionProcessor");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ULog.d(TAG, "onHandleIntent.");

        eventsManager.processEvent((BeaconModel) intent.getSerializableExtra(Extra.BEACON),
                (Trigger) intent.getSerializableExtra(Extra.TRIGGER),
                (EventInfo) intent.getSerializableExtra(Extra.EVENT));
    }
}
