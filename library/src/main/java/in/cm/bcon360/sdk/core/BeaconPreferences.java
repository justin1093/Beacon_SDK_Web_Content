/*
 * Copyright (c) 2016, Upnext Technologies Sp. z o.o.
 * All rights reserved.
 *
 * This source code is licensed under the BSD 3-Clause License found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

package in.cm.bcon360.sdk.core;

import in.cm.bcon360.sdk.backend.model.CreateEventsRequest;
import in.cm.bcon360.sdk.backend.model.TokenCredentials;

import java.util.List;

public interface BeaconPreferences {

    void setOAuthCredentials(TokenCredentials tokenCredentials);

    TokenCredentials getOAuthCredentials();

    void setEventsSentTimestamp(long timestamp);

    long getEventsSentTimestamp();

    /*
     * Stores events that will be sent to server in a batch.
     */
    void setEventsList(List<CreateEventsRequest.Event> events);

    List<CreateEventsRequest.Event> getEventsList();
}
