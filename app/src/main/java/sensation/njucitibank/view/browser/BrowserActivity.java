package sensation.njucitibank.view.browser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sensation.njucitibank.R;
import sensation.njucitibank.view.widget.SwipeBackActivity;

public class BrowserActivity extends SwipeBackActivity {
    private static final String ARG_URL = "url";
    String url;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.webview)
    WebView webView;

    public static void activityStart(Activity activity, String url) {
        Intent intent = new Intent(activity, BrowserActivity.class);
        intent.putExtra(ARG_URL, url);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in, R.anim.half_silde_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        url = getIntent().getStringExtra(ARG_URL);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initToolBar();
        initWebView();
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.fab)
    void action(View view) {
        Snackbar.make(view, "分享功能即将上线~", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void initWebView() {
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.half_slide_in, R.anim.slide_out);
    }
}
