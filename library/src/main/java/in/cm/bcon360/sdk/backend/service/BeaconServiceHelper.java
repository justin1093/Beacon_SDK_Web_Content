/*
 * Copyright (c) 2016, Upnext Technologies Sp. z o.o.
 * All rights reserved.
 *
 * This source code is licensed under the BSD 3-Clause License found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

package in.cm.bcon360.sdk.backend.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.cm.bcon360.sdk.Action;
import in.cm.bcon360.sdk.BeaconControl;
import in.cm.bcon360.sdk.ErrorCode;
import in.cm.bcon360.sdk.backend.BeaconControlManager;
import in.cm.bcon360.sdk.backend.HttpListener;
import in.cm.bcon360.sdk.backend.mediator.GetConfigurationsCallMediator;
import in.cm.bcon360.sdk.backend.mediator.HttpCallMediator;
import in.cm.bcon360.sdk.backend.model.BeaconProximity;
import in.cm.bcon360.sdk.backend.model.BeaconSDKColor;
import in.cm.bcon360.sdk.backend.model.GetConfigurationsResponse;
import in.cm.bcon360.sdk.core.Config;
import in.cm.bcon360.sdk.util.ApplicationUtils;
import in.cm.bcon360.sdk.util.ULog;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
//import org.apache.commons.codec.binary.Base64.*;

public class BeaconServiceHelper {

    private static final String TAG = BeaconServiceHelper.class.getSimpleName();

    private static final Object sInstanceLock = new Object();
    public static BeaconServiceHelper sInstance;

    private final Context context;
    private final Config config;
    private final BeaconControlManager beaconControlManager;

    private GetConfigurationsCallMediator getConfigurationsCallMediator;
    public GetConfigurationsResponse configurations;

    private boolean bound = false;

    private BeaconServiceHelper(Context context, Config config, BeaconControlManager beaconControlManager) {
        this.context = context;
        this.config = config;
        this.beaconControlManager = beaconControlManager;
    }

    public static BeaconServiceHelper getInstance(Context context, Config config, BeaconControlManager beaconControlManager) {
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new BeaconServiceHelper(context, config, beaconControlManager);
            }
            return sInstance;
        }
    }

    public void getConfigurationsAsync() {
/*
                    "event_type": "enter",
                    "type": "event_type"
 */

      //
       // configurations.triggers.add(obj1Trigger);
        configurations = new GetConfigurationsResponse();
        GetConfigurationsResponse.Trigger obj1Trigger = new GetConfigurationsResponse.Trigger();
        GetConfigurationsResponse.Trigger objTriggerAd = new GetConfigurationsResponse.Trigger();
        GetConfigurationsResponse.Trigger objTriggerAd1 = new GetConfigurationsResponse.Trigger();
        GetConfigurationsResponse.Trigger objTriggerAd2 = new GetConfigurationsResponse.Trigger();
        GetConfigurationsResponse.Trigger objTriggerAd3 = new GetConfigurationsResponse.Trigger();

        GetConfigurationsResponse.Range range = new GetConfigurationsResponse.Range();
        GetConfigurationsResponse.Range rangeAd = new GetConfigurationsResponse.Range();
        GetConfigurationsResponse.Range rangeAd1 = new GetConfigurationsResponse.Range();


        GetConfigurationsResponse.Zone zone = new GetConfigurationsResponse.Zone();
        zone.beaconIds= new ArrayList<Long>();
        zone.beaconIds.add(new Long(12));
        zone.color =new BeaconSDKColor("blue");
        zone.id=new Long(12);
        zone.name="test";
        configurations.zones= new ArrayList<GetConfigurationsResponse.Zone>();
        configurations.zones.add(zone);
        configurations.ttl=9980;
        configurations.triggers= new ArrayList<GetConfigurationsResponse.Trigger>();
        configurations.triggers.add(obj1Trigger);
        configurations.triggers.add(objTriggerAd);
        configurations.triggers.add(objTriggerAd1);
        configurations.ranges= new ArrayList<GetConfigurationsResponse.Range>();
        configurations.ranges.add(range);
        configurations.ranges.add(rangeAd);
        configurations.ranges.add(rangeAd1);

        //res.ranges.add(z);

//*************************************   BEACON   2  *************************************************************



        BeaconProximity prox = new BeaconProximity("9D07149D-23D9-4B30-BA5A-5214346460AF+4104+3186");
        range.id= 6;
        range.name="Beacon 8";
        range.protocol= GetConfigurationsResponse.Range.Protocol.iBeacon;
        range.proximityId= prox;

        Action.Payload payload2 =new Action.Payload();
//commented on 12.03.2018        payload1.url="http://auditor-thomas-10740.bitballoon.com/";
        payload2.url="http://auditor-thomas-10740.bitballoon.com/";
        Action.Type type= Action.Type.coupon;

       GetConfigurationsResponse.Trigger.Condition con1 = new GetConfigurationsResponse.Trigger.Condition();
        con1.eventType=  GetConfigurationsResponse.Trigger.Condition.EventType.near;
        con1.type=GetConfigurationsResponse.Trigger.Condition.Type.event_type;
        obj1Trigger.id=(long)20;
        obj1Trigger.conditions= new ArrayList<GetConfigurationsResponse.Trigger.Condition>();
        obj1Trigger.conditions.add(con1 );
        obj1Trigger.action = new Action();
        obj1Trigger.action.id=(long)20;
        obj1Trigger.action.name="test_Ad";
        obj1Trigger.action.type= Action.Type.coupon;
        obj1Trigger.action.payload=payload2;
        //enumeration

        obj1Trigger.range_ids=new ArrayList<Long>();
        obj1Trigger.range_ids.add(new Long(6));
        obj1Trigger.test=false;
       // obj1Trigger.tt=false;
        obj1Trigger.zone_ids=new ArrayList<Long>();
        obj1Trigger.zone_ids.add(new Long(1233453));

// Ad Beacon


//*************************************   BEACON   1  *************************************************************
        BeaconProximity proxAd = new BeaconProximity("9D07149D-23D5-4B30-BA5A-5214346460AF+4104+3121");
        rangeAd.id= 7;
        rangeAd.name="Beacon 9";
        rangeAd.protocol= GetConfigurationsResponse.Range.Protocol.iBeacon;
        rangeAd.proximityId= proxAd;

        Action.Payload payloadAd1 =new Action.Payload();
//commented on 12.03.2018        payloadAd.url="http://packer-locomotive-21471.bitballoon.com/";
            payloadAd1.url="http://priceless-brown-1c3905.bitballoon.com/";
        Action.Type typeAd= Action.Type.coupon;

        GetConfigurationsResponse.Trigger.Condition conAd = new GetConfigurationsResponse.Trigger.Condition();
        conAd.eventType=  GetConfigurationsResponse.Trigger.Condition.EventType.near;
        conAd.type=GetConfigurationsResponse.Trigger.Condition.Type.event_type;
        objTriggerAd.id=(long)20;
        objTriggerAd.conditions= new ArrayList<GetConfigurationsResponse.Trigger.Condition>();
        objTriggerAd.conditions.add(con1 );
        objTriggerAd.action = new Action();
        objTriggerAd.action.id=(long)20;
        objTriggerAd.action.name="test";
        objTriggerAd.action.type= Action.Type.coupon;
        objTriggerAd.action.payload=payloadAd1;
        //enumeration

        objTriggerAd.range_ids=new ArrayList<Long>();
        objTriggerAd.range_ids.add(new Long(7));
        objTriggerAd.test=false;
        // obj1Trigger.tt=false;
        objTriggerAd.zone_ids=new ArrayList<Long>();
        objTriggerAd.zone_ids.add(new Long(1233453));

//*************************************   BEACON   3  *************************************************************
        BeaconProximity proxAd1 = new BeaconProximity("9D07149D-23D5-4B30-BA5A-5214346460AF+3507+3507");
        rangeAd1.id= 8;
        rangeAd1.name="Beacon 9";
        rangeAd1.protocol= GetConfigurationsResponse.Range.Protocol.iBeacon;
        rangeAd1.proximityId= proxAd1;

        Action.Payload payloadAd3 =new Action.Payload();
//commented on 12.03.2018        payloadAd.url="http://packer-locomotive-21471.bitballoon.com/";
        payloadAd3.url="http://vigorous-mestorf-a7cfb8.bitballoon.com/";

//added by 18.03.2018
        objTriggerAd1.id=(long)20;
        objTriggerAd1.conditions= new ArrayList<GetConfigurationsResponse.Trigger.Condition>();
        objTriggerAd1.conditions.add(con1 );
        objTriggerAd1.action = new Action();
        objTriggerAd1.action.id=(long)20;
        objTriggerAd1.action.name="test";
        objTriggerAd1.action.type= Action.Type.coupon;
        objTriggerAd1.action.payload=payloadAd3;
        //enumeration

        objTriggerAd1.range_ids=new ArrayList<Long>();
        objTriggerAd1.range_ids.add(new Long(8));
        objTriggerAd1.test=false;
        // obj1Trigger.tt=false;
        objTriggerAd1.zone_ids=new ArrayList<Long>();
        objTriggerAd1.zone_ids.add(new Long(1233453));


        if (bound) {
            notifyService(getBeaconServiceIntentWithConf(), BeaconService.Command.START_SCAN);
        }

        ULog.d(TAG, "Configuration fetched");
        /* Todo  - vijay.jacob@cm
            overriding the call to bc server and creating a dummy configuration response object

         */
       /*getConfigurationsCallMediator = new GetConfigurationsCallMediator(context, beaconControlManager, new HttpListener<GetConfigurationsResponse>() {
            @Override
            public void onSuccess(GetConfigurationsResponse response) {
                handleUnsupportedProtocols(response);

                configurations = response;


                ULog.d(TAG, "Configuration fetched triggers.get(0).conditions.get(0).type:" + response.triggers.get(0).conditions.get(0).type);
                ULog.d(TAG, ", triggers.get(0).conditions.get(0).eventType:" + response.triggers.get(0).conditions.get(0).eventType );
                ULog.d(TAG, ", action.type:" + response.triggers.get(0).action.type);
                ULog.d(TAG, ", action.name:" + response.triggers.get(0).action.name);
                ULog.d(TAG, ", action.payload.url:" + response.triggers.get(0).action.payload.url);
                ULog.d(TAG, ", action.id:" + response.triggers.get(0).action.id);
                ULog.d(TAG, ",range.sizer" + response.ranges.size() );
                ULog.d(TAG, ",rangeid:" + response.ranges.get(0).id );
                ULog.d(TAG, ",range location:" + response.ranges.get(0).location.toString());
                ULog.d(TAG, ", range name:" + response.ranges.get(0).name );
                ULog.d(TAG, ", Beacon identifier:" + response.ranges.get(0).proximityId.getProximityId() + "+"  + response.ranges.get(0).proximityId.getUUID()
                        + "+"  + response.ranges.get(0).proximityId.getMajor() + "+"  + response.ranges.get(0).proximityId.getMinor() );
                ULog.d(TAG, ", Beacon identifier:" + response.ranges.get(1).proximityId.getProximityId() + "+"  + response.ranges.get(1).proximityId.getUUID()
                        + "+"  + response.ranges.get(1).proximityId.getMajor() + "+"  + response.ranges.get(1).proximityId.getMinor() );
                ULog.d(TAG, ", Beacon identifier:" + response.ranges.get(2).proximityId.getProximityId() + "+"  + response.ranges.get(2).proximityId.getUUID()
                        + "+"  + response.ranges.get(2).proximityId.getMajor() + "+"  + response.ranges.get(2).proximityId.getMinor() );


                ULog.d(TAG, ",ttl:" + response.ttl);
                ULog.d(TAG, ",extension" + response.extensions.presence.ranges.toString());
                ULog.d(TAG, ",extension" + response.extensions.presence.zones.toString());



                if (bound) {
                    notifyService(getBeaconServiceIntentWithConf(), BeaconService.Command.START_SCAN);
                }
            }

            @Override
            public void onError(ErrorCode errorCode, Throwable t) {
                ULog.e(TAG, "Error in getConfigurations task, " + errorCode.name(), t);

                BeaconControl.onError(errorCode);
            }

            @Override
            public void onEnd() {
                getConfigurationsCallMediator = null;
            }
        });
        getConfigurationsCallMediator.getConfigurations();*/

    }

    private void handleUnsupportedProtocols(GetConfigurationsResponse response) {
        List<Long> unsupportedRangeIds = removeRangesWithUnsupportedProtocols(response);
        removeUnsupportedRangesFromTriggers(response, unsupportedRangeIds);
    }

    private List<Long> removeRangesWithUnsupportedProtocols(GetConfigurationsResponse response) {
        List<Long> unsupportedRangeIds = new ArrayList<>();
        Iterator<GetConfigurationsResponse.Range> it = response.ranges.iterator();
        while (it.hasNext()) {
            GetConfigurationsResponse.Range range = it.next();
            switch (range.protocol) {
                case iBeacon:
                    // ignore, as this protocol is supported
                    break;
                default:
                    ULog.w(TAG, "Unsupported protocol for range id " + range.id);
                    unsupportedRangeIds.add(range.id);
                    it.remove();
            }
        }
        return unsupportedRangeIds;
    }

    private void removeUnsupportedRangesFromTriggers(GetConfigurationsResponse response, List<Long> unsupportedRangeIds) {
        Iterator<GetConfigurationsResponse.Trigger> it = response.triggers.iterator();
        while (it.hasNext()) {
            GetConfigurationsResponse.Trigger trigger = it.next();
            trigger.range_ids.removeAll(unsupportedRangeIds);

            if (isTriggerUnused(trigger)) {
                it.remove();
            }
        }
    }

    private boolean isTriggerUnused(GetConfigurationsResponse.Trigger trigger) {
        return trigger.range_ids.isEmpty();
    }

    private boolean isGetConfigurationsInProgress() {
        return getConfigurationsCallMediator != null;
    }

    public void startScan() {
        if (!isGetConfigurationsInProgress()) {
            getConfigurationsAsync();
        }

        if (bound) return;

        notifyService(getBeaconServiceIntentWithConf(), BeaconService.Command.START_SCAN);
        bound = true;
    }

    public void stopScan() {
        HttpCallMediator.cancelHttpMediator(getConfigurationsCallMediator);

        if (bound) {
            notifyService(getBeaconServiceIntent(), BeaconService.Command.STOP_SCAN);
            bound = false;
        }

        configurations = null;
        beaconControlManager.clearToken();
    }

    private void notifyService(Intent i, BeaconService.Command cmd) {
        i.putExtra(BeaconService.Extra.COMMAND, cmd);
        context.startService(i);
    }

    private Intent getBeaconServiceIntentWithConf() {
        return getBeaconServiceIntent().putExtra(BeaconService.Extra.CONFIGURATIONS, configurations);
    }

    private Intent getBeaconServiceIntent() {
        Intent i = new Intent(BeaconService.ACTION_NAME);
        i.setComponent(getAppropriateBeaconService(i));
        i.putExtra(BeaconService.Extra.CLIENT_APP_PACKAGE, context.getPackageName());

        return i;
    }

    private ComponentName getAppropriateBeaconService(Intent i) {
        return ApplicationUtils.getAppropriateBeaconService(context, config, i);
    }


}
