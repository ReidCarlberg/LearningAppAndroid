package com.salesforce.kp.wheresreid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import us.feras.mdv.MarkdownView;

/**
 * MainActivity is the primary activity.
 *
 * This activity extends AppCompatActivity to provide the primary interface for user interaction.
 *
 *
 * @author Salesforce (R) 2015.
 *
 */

public class MainActivity extends BaseActivity {

    private MarkdownView markdownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        prepareDisplay();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* adds items to the action bar if present. */
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         * Handles action bar item clicks
         * The action bar automatically handles clicks on the Home/Up button if
         * a parent activity in AndroidManifest.xml has been specified.
         */

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
        else if (id == R.id.action_cloudpage_inbox){
            startActivity(new Intent(this, CloudPageInboxActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void prepareDisplay(){
        markdownView = (MarkdownView) findViewById(R.id.markdownView);
        markdownView.loadMarkdownFile("https://raw.githubusercontent.com/PhilCommunication/marketingCloudSDK-android/master/README.md");
        markdownView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/PhilCommunication/marketingCloudSDK-android#readme"));
                startActivity(browserIntent);
            }
        });
    }
}
