package fi.centria.bitarafan_chapagain_quizer;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;


public class AnswerFragment extends Fragment
                implements View.OnClickListener
{
    final String TAG = "quizerTAGS";
    private Button ansBtn_1, ansBtn_2, ansBtn_3, ansBtn_4;
    private IOnAnswerPicked mListener = null;

    public interface IOnAnswerPicked {
        void onAnswerPicked(String msg);
    }

    public static AnswerFragment newInstance(){
        return new AnswerFragment();
    }

    public AnswerFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof IOnAnswerPicked){
            mListener = (IOnAnswerPicked)context;
        }
        else {
            //TODO in case of listener not implemented what will happen
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_answer, container, false);
        ansBtn_1 = fv.findViewById(R.id.answ_1);
        ansBtn_1.setOnClickListener(this);
        ansBtn_2 = (fv.findViewById(R.id.answ_2));
        ansBtn_2.setOnClickListener(this);
        ansBtn_3 = (fv.findViewById(R.id.answ_3));
        ansBtn_3.setOnClickListener(this);
        ansBtn_4 = (fv.findViewById(R.id.answ_4));
        ansBtn_4.setOnClickListener(this);
        return fv;
    }

    public void setAnswers(List<String> ans){
        if (ans != null){
            ansBtn_1.setText(ans.get(0));
            ansBtn_2.setText(ans.get(1));
            ansBtn_3.setText(ans.get(2));
            ansBtn_4.setText(ans.get(3));
        }
        else Toast.makeText(getActivity(), "No questions left!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) mListener.onAnswerPicked(((Button)v).getText().toString());
    }

}
