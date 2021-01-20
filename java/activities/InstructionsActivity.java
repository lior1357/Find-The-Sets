package afinal.game.lior.findthesets;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InstructionsActivity extends AppCompatActivity {

    private MyPagerAdapter adapterViewPager;
    private ViewPager vpPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        vpPager = (ViewPager)findViewById(R.id.vpPager);

        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages.
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for a particular page.

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            String text;
            text = "Try and find as many sets as you vam in 2 minutes\n" +
                    "A valid set consists of three tiles that meet ALL of these conditions:\n" +
                    "1. They all have the same shape, or they have three different shapes\n" +
                    "2. They all have the same color, or they have three different colors\n" +
                    "3. They all have the same shading, or they have three different shadings\n"+
                    "4. They all have the same number of shapes, or they have three differnect numbers of shapes\n";

            switch (position) {
                case 0:
                    return FirstFragment.newInstance(text);
                case 1:
                    return SecondFragment.newInstance("valid", R.mipmap.c1111, R.mipmap.c1112, R.mipmap.c1113 );
                case 2:
                    return SecondFragment.newInstance("invalid", R.mipmap.c2111, R.mipmap.c2112, R.mipmap.c2113);
                default:
                    return null;
            }
        }
    }

}
