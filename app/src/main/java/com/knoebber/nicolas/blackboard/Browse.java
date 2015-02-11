package com.knoebber.nicolas.blackboard;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.app.ProgressDialog;

public class Browse extends ActionBarActivity {
    final Context context = this;
    String username;
    String password;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        Intent i = getIntent();
        username = i.getStringExtra("name");
        password = i.getStringExtra("pass");
        /*
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Alert");
        alertDialogBuilder.setMessage("User "+username+"\n"+"Password "+password);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        */
        webView = (WebView) findViewById(R.id.webView);
        startWebView("https://pioneerweb.grinnell.edu");
        webView.getSettings().setDomStorageEnabled(true); //so we can store locally
        //
    }
/*
 copy pasta from http://www.codeproject.com/Tips/761467/Android-App-To-Browse-A-Website
 */
    private void startWebView(String url) {
    //Create new webview Client to show progress dialog
        //When opening a url or click on link
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
                                     /*
                                 }
            ProgressDialog progressDialog;

            //If you will not use this method URL links are open in new browser not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on URL load
            public void onLoadResource (WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(Browse.this);
                    progressDialog.setMessage("Loading..Have Patience Buddy!!");
                    progressDialog.show();
                }
            }
            */
            public void onPageFinished(WebView view, String url) {
                //inject some delicious java script to log us in
                webView.loadUrl("javascript:" +
                        "var iFrame = document.getElementById('contentFrame');"+
                        "var innerDoc = iFrame.contentDocument || iFrame.contentWindow.document;"+
                        "var username=innerDoc.getElementById('user_id');"+
                        "var password=innerDoc.getElementById('password');"+
                        "username.value="+"'"+username+"'"+";"+
                        "password.value="+"'"+password+"'"+";"+
                        "var button=innerDoc.getElementsByName('login');"+
                        "button[0].submit()");





        /*
                try{
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
                */

            }

        });

         // Javascript enabled on webview


        //Load url in webview
        webView.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
