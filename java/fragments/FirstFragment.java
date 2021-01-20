package afinal.game.lior.findthesets;

import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FirstFragment extends Fragment {
    private String text;

    public static FirstFragment newInstance(String text) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString("instructions", text);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text = getArguments().getString("instructions");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_first_fragment, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.tvInstructions);
        tvLabel.setText(text);

        return view;
    }
}
