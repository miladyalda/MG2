package android.app.mg.mg.Process;

import android.app.mg.mg.R;
        import android.content.Context;
        import android.support.v4.view.PagerAdapter;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class InfoPagerAdapter extends PagerAdapter {
//ss

    /*
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

*/

    ArrayList<Integer> imageArray = new ArrayList<Integer>();
    Context mContext;
    LayoutInflater mLayoutInflater;

    public InfoPagerAdapter(Context context, ArrayList<Integer> imageArray) {
        mContext = context;
        this.imageArray = imageArray;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return imageArray.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.fragment_info_pager, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.info_image);
        imageView.setImageResource(imageArray.get(position));

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}
