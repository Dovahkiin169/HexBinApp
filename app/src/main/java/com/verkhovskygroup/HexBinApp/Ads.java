package com.verkhovskygroup.HexBinApp;

import android.content.Context;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;


public class Ads {
    private static final String AD_UNIT_ID = "ca-app-pub-3197360098895857/4609183528";

    private InterstitialAd interstitialAd;

    public void adsInApp(Context context) {
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(AD_UNIT_ID);
        interstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {}
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {}
                    @Override
                    public void onAdClosed() {startAdRequest(); }
                });
        startAdRequest();
    }

    public void showInterstitial() {
        if (interstitialAd != null && interstitialAd.isLoaded())
            interstitialAd.show();
        else
            startAdRequest();
    }

    public void startAdRequest() {
        if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
        }
    }
}
