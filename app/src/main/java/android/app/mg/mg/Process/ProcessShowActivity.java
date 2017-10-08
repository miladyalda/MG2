package android.app.mg.mg.Process;

import android.app.Fragment;
import android.app.mg.mg.R;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class ProcessShowActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private InfoPagerAdapter pagerAdapter;
    private Boolean state = true;
    MediaPlayer mp;
    ArrayList<String> lastStepHUH;

    int[] imageArray =    {
            R.drawable.demo1,
            R.drawable.demo2,
            R.drawable.demo3,
            R.drawable.demo4,
            R.drawable.demo5,
            R.drawable.demo6,
            R.drawable.demo7,
            R.drawable.demo8
    };

    private static final int NUM_PAGES = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_show);
            createAudio("sound1");
             Intent intent = getIntent();
            lastStepHUH = intent.getStringArrayListExtra("jsonData");

            ArrayList<Integer> sortedImages = new ArrayList<Integer>();

            for (int i = 0; i < lastStepHUH.size(); i++){
                int j = Integer.parseInt(lastStepHUH.get(i));

                sortedImages.add(imageArray[j]);
            }

            viewPager = (ViewPager) findViewById(R.id.infoPager);
            pagerAdapter = new InfoPagerAdapter(this, sortedImages);
            viewPager.setAdapter(pagerAdapter);

        /**
         *
         * <android.support.v4.view.ViewPager
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         android:layout_weight = "1"
         android:id="@+id/infoPager">

         </android.support.v4.view.ViewPager>
         *
         *
         * **/


        }

        public void rightButton(View view){
            ImageButton button = (ImageButton) findViewById(R.id.rightButton);
            int index = getItem(+1);
            if(index < lastStepHUH.size()) {
                viewPager.setCurrentItem(index, true); //getItem(-1) for previous
                stopAudio(mp);

                createAudio("sound" + index);
            }

        }

        private void createAudio(String soundName)   {

            //mp = MediaPlayer.create(this, R.raw.soundName);
            int ID = this.getResources().getIdentifier(soundName,"raw",this.getPackageName());
            mp = MediaPlayer.create(this,ID);
            mp.start();
        }

        private void stopAudio(MediaPlayer mp)   {
                  mp.stop();
            }




    public void leftButton(View view){
        ImageButton button = (ImageButton) findViewById(R.id.leftButton);
        int index = getItem(-1);

        if(index >= 0) {
            viewPager.setCurrentItem(index, true);
               stopAudio(mp);
               createAudio("sound"+index);
        }


    }


    public void muteToggelSounde(View view){
        ImageButton button = (ImageButton) findViewById(R.id.soundButton);

        if(state) {
            button.setImageResource(R.mipmap.ic_sound_off);
            mp.pause();
            state = false;
        }else{
            button.setImageResource(R.mipmap.ic_sound_on);
            mp.start();
            state = true;


        }

    }


    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    }

