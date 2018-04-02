/*
 * Copyright (c) 2016, Upnext Technologies Sp. z o.o.
 * All rights reserved.
 *
 * This source code is licensed under the BSD 3-Clause License found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

package in.cm.bcon360.sdk.backend.mediator;

import android.content.Context;

import in.cm.bcon360.sdk.backend.HttpListener;
import in.cm.bcon360.sdk.backend.BeaconControlManager;
import in.cm.bcon360.sdk.backend.model.GetPresenceRequest;
import in.cm.bcon360.sdk.backend.model.GetPresenceResponse;

public class GetPresenceCallMediator extends HttpCallMediator<GetPresenceResponse> {

    private GetPresenceRequest request;

    public GetPresenceCallMediator(Context context, BeaconControlManager beaconControlManager, HttpListener httpListener) {
        super(context, beaconControlManager, httpListener);
    }

    public void getPresence(GetPresenceRequest request) {
        this.request = request;

        onStartCall();
    }

    @Override
    protected void execute() {
        setCall(getBeaconControlManager().getPresenceCall(request));
    }
}
