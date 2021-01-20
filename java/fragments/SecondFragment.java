package afinal.game.lior.findthesets;

import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondFragment extends Fragment {
    private int valid1, valid2, valid3;
    private String validOrNot;

    public static SecondFragment newInstance(String validOrNot, int valid1, int valid2, int valid3) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString("validOrNot", validOrNot);
        args.putInt("valid1", valid1);
        args.putInt("valid2", valid2);
        args.putInt("valid3", valid3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        valid1 = getArguments().getInt("valid1", 0);
        valid2 = getArguments().getInt("valid2", 0);
        valid3 = getArguments().getInt("valid3", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_second_fragment, container, false);
        ImageView imageView1 = (ImageView) view.findViewById(R.id.iv1);
        imageView1.setImageResource(valid1);

        ImageView imageView2 = (ImageView) view.findViewById(R.id.iv2);
        imageView2.setImageResource(valid2);

        ImageView imageView3 = (ImageView) view.findViewById(R.id.iv3);
        imageView3.setImageResource(valid3);

        TextView tvValidOrNot = (TextView)view.findViewById(R.id.validornot);
        tvValidOrNot.setText(validOrNot);

        return view;
    }
}
