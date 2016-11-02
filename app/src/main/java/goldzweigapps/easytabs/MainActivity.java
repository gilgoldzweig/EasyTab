package goldzweigapps.easytabs;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import goldzweigapps.tabs.Builder.EasyTabsBuilder;
import goldzweigapps.tabs.Colors.EasyTabsColors;
import goldzweigapps.tabs.Interface.TabsListener;
import goldzweigapps.tabs.Items.TabItem;
import goldzweigapps.tabs.View.EasyTabs;

public class MainActivity extends AppCompatActivity {
    EasyTabs tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabs = (EasyTabs) findViewById(R.id.EasyTabs);
        EasyTabsBuilder.init(tabs)
                .addTabs(
                        new TabItem(new frag1(), "Profile"), // Add four Tab items with fragment and title
                        new TabItem(new frag2(), "Camera"),
                        new TabItem(new frag3(), "Favorite"),
                        new TabItem(new frag4(), "Help"))
                .setBackgroundColor(EasyTabsColors.White)
                .setIndicatorColor(EasyTabsColors.Black)
                .setTextColors(EasyTabsColors.Black, EasyTabsColors.RoyalBlue) //Setting two colors selected one and unselected one
                .addIcons(
                        R.drawable.ic_person_white_36dp,
                        R.drawable.ic_photo_camera_white_36dp,
                        R.drawable.ic_favorite_white_36dp,
                        R.drawable.ic_help_white_36dp) // Adding four icons
                .setTabLayoutScrollable(false)
                .setCustomTypeface(Typeface.createFromAsset(getAssets(), "fonts/bubble.ttf")) // Adding custome font
                .withListener(new TabsListener() {

                    @Override
                    public void onScreenPosition(int position) {
                        Log.d("tag", String.valueOf(position));
                    }
                })
                .setIconFading(true) // Added the icon fader like in faceboook app
                .HideTitle(true) //Hiding titles that only icons will be visiable
                .Build(); // Building the tabs

    }
}
