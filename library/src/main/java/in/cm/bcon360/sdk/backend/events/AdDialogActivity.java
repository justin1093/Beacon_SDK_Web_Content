/*
 * Copyright (c) 2016, Upnext Technologies Sp. z o.o.
 * All rights reserved.
 *
 * This source code is licensed under the BSD 3-Clause License found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

package in.cm.bcon360.sdk.backend.events;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import in.cm.bcon360.sdk.R;
import in.cm.bcon360.sdk.util.ULog;
import android.app.AlertDialog;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;
import android.util.*;
public class AdDialogActivity extends Activity {


    interface Extra {
        String NAME = "NAME";
        String URL = "URL";
    }
    private static final String TAG = AdDialogActivity.class.getSimpleName();
    public static Intent getIntent(Context context, String name, String url) {
        Intent intent = new Intent(context, AdDialogActivity.class);

        intent.putExtra(Extra.NAME, name);
        intent.putExtra(Extra.URL, url);
        return intent;
    }
    @NonNull
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_web_view);
        setTitle(getNameFromExtras());
        setWebView(getURLFromExtras());


        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
                // TODO Auto-generated method stub
            }
        });
        showMyDialog(this);


    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setWebView(String url) {
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        ULog.d(TAG, "URL." + url);
        //webView.loadUrl("www.google.com");
    }
    private void showMyDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);

        TextView textView = (TextView) dialog.findViewById(R.id.txtTitle);

        Button btnBtmLeft = (Button) dialog.findViewById(R.id.btnBtmLeft);


        btnBtmLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        /**
         * if you want the dialog to be specific size, do the following
         * this will cover 85% of the screen (85% width and 85% height)
         */
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dialogWidth = (int)(displayMetrics.widthPixels * 0.85);
        int dialogHeight = (int)(displayMetrics.heightPixels * 0.85);
        dialog.getWindow().setLayout(dialogWidth, dialogHeight);

        dialog.show();
    }

    private String getNameFromExtras() {
        return getIntent().getExtras().getString(Extra.NAME);
    }

    private String getURLFromExtras() {
        return getIntent().getExtras().getString(Extra.URL);
    }


}
