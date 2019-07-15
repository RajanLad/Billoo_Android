package fr.billoo.mobile.BaseFunctionalities;

import android.content.Context;
import android.net.ConnectivityManager;

import java.net.InetAddress;

public class APIRelatedStuff
{
    public static String BillooBaseURL= "https://0dee0592.ngrok.io";
    //public static String BillooBaseURL= "http://192.168.174.2:8088/billoo/";

    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }

    }
}
