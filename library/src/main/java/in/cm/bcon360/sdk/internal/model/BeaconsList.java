/*
 * Copyright (c) 2016, Upnext Technologies Sp. z o.o.
 * All rights reserved.
 *
 * This source code is licensed under the BSD 3-Clause License found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

package in.cm.bcon360.sdk.internal.model;

import java.io.Serializable;
import java.util.List;

import in.cm.bcon360.sdk.Beacon;

public class BeaconsList implements Serializable {

    public List<Beacon> beaconList;

    public BeaconsList(List<Beacon> beaconList) {
        this.beaconList = beaconList;
    }
}