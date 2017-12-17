package fi.centria.bitarafan_chapagain_quizer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class PictureFragment extends Fragment {

    ImageView ques_picture;

    public PictureFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                    @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View fv = inflater.inflate(R.layout.fragment_answer, container, false);
        ques_picture = fv.findViewById(R.id.ques_picture);
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
