/*
 * Copyright (c) 2016, Upnext Technologies Sp. z o.o.
 * All rights reserved.
 *
 * This source code is licensed under the BSD 3-Clause License found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

package in.cm.bcon360.sdk.backend;

import in.cm.bcon360.sdk.backend.model.CreateEventsRequest;
import in.cm.bcon360.sdk.backend.model.GetConfigurationsResponse;
import in.cm.bcon360.sdk.backend.model.GetPresenceRequest;
import in.cm.bcon360.sdk.backend.model.GetPresenceResponse;

import retrofit2.Call;

public interface BeaconControlManager {

    Call<GetConfigurationsResponse> getConfigurationsCall();

    Call<Void> createEventsCall(CreateEventsRequest request);

    Call<GetPresenceResponse> getPresenceCall(GetPresenceRequest request);

    void clearToken();
}
