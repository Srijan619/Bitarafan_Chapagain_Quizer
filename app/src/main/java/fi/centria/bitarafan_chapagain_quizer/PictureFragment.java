package fi.centria.bitarafan_chapagain_quizer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PictureFragment extends Fragment {
    final String TAG = "quizerTAGS";
    private ImageView ques_picture;

    public PictureFragment() {
        // Required empty public constructor
    }

    public static PictureFragment newInstance(){
        return new PictureFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                    @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View fv = inflater.inflate(R.layout.fragment_picture, container, false);
        ques_picture = fv.findViewById(R.id.ques_picture);
//        ques_picture.setImageDrawable(getResources().getDrawable(R.drawable.animals_bear));
        Log.d(TAG, "onCreateView: PictureFragment CREATED!");
        return fv;
    }

    /*  Purpose :   to change the picture of question
    *   Input:      drawable Identifier
    *   Output:     ImageView updated
    */
    public void changePicture(int drawable_id){
        ques_picture.setImageDrawable(getResources().getDrawable(drawable_id));
    }
}
