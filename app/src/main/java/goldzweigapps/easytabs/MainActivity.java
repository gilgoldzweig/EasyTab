package goldzweigapps.easytabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;

import goldzweigapps.tabs.Builder.EasyDialogBuilder;
import goldzweigapps.tabs.Builder.EasyTabsBuilder;
import goldzweigapps.tabs.Colors.EasyTabsColors;
import goldzweigapps.tabs.Interface.TabsListener;
import goldzweigapps.tabs.Items.TabItem;
import goldzweigapps.tabs.View.EasyBottomTabs;
import goldzweigapps.tabs.View.EasyTabs;

public class MainActivity extends AppCompatActivity {
    EasyBottomTabs tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(MainActivity.this, Main2Activity.class));
        tabs = (EasyBottomTabs) findViewById(R.id.EasyTabs);
//        EasyTabsBuilder.with(tabs)
//                .addTabs(
//                        new TabItem(new frag1(), "Profile"), // Add four Tab items with fragment and title
//                        new TabItem(new frag2(), "Camera"),
//                        new TabItem(new frag3(), "Favorite"),
//                        new TabItem(new frag4(), "Help"))
//                .setTabsBackgroundColor(EasyTabsColors.Red)
//                .setIndicatorColor(EasyTabsColors.Yellow)
//                .setTextColors(EasyTabsColors.RoyalBlue, EasyTabsColors.Gray) //Setting two colors selected one and unselected one
//                .addIcons(
//                        R.drawable.ic_person_white_36dp,
//                        R.drawable.ic_photo_camera_white_36dp,
//                        R.drawable.ic_favorite_white_36dp,
//                        R.drawable.ic_help_white_36dp) // Adding four icons
//                .withListener(new TabsListener() {
//
//                    @Override
//                    public void onScreenPosition(int position) {
//                        Log.d("tag", String.valueOf(position));
//                    }
//                })
//                .setIconFading(true)// Added the icon fader like in faceboook app
//                .hideAllTitles(false)
//
//                .Build(); // Building the tabs

        EasyDialogBuilder.with(this)
                .addTabs(
                        new TabItem(new frag1(), "Profile"), // Add four Tab items with fragment and title
                        new TabItem(new frag2(), "Camera"),
                        new TabItem(new frag3(), "Favorite"),
                        new TabItem(new frag4(), "Help"))
                .setTabsBackgroundColor(EasyTabsColors.Red)
                .setIndicatorColor(EasyTabsColors.Yellow)
                .setTextColors(EasyTabsColors.RoyalBlue, EasyTabsColors.Gray) //Setting two colors selected one and unselected one
                .addIcons(
                        R.drawable.ic_person_white_36dp,
                        R.drawable.ic_photo_camera_white_36dp,
                        R.drawable.ic_favorite_white_36dp,
                        R.drawable.ic_help_white_36dp) // Adding four icons
                .addOnTabListener(new TabsListener() {

                    @Override
                    public void onScreenPosition(int position) {
                        Log.d("tag", String.valueOf(position));
                    }
                })
                .setIconFading(true)// Added the icon fader like in faceboook app
                .hideAllTitles(false)
                .Build(); // Building the tabs
    }
}
