package fi.centria.bitarafan_chapagain_quizer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class AnswerFragment extends Fragment
                implements View.OnClickListener
{
    private IOnMessageSend mListener = null;

    public interface IOnMessageSend {
        abstract void onMessageSend(String msg);
    }

    public static AnswerFragment newInstance(){
        return new AnswerFragment();
    }

    public AnswerFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof IOnMessageSend){
            mListener = (IOnMessageSend)context;
        }
        else {
            Toast.makeText(context, "App is malfunctioning", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_answer, container, false);
        (fv.findViewById(R.id.answ_1)).setOnClickListener(this);
        (fv.findViewById(R.id.answ_2)).setOnClickListener(this);
        (fv.findViewById(R.id.answ_3)).setOnClickListener(this);
        (fv.findViewById(R.id.answ_4)).setOnClickListener(this);
        return fv;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) mListener.onMessageSend(((Button)v).getText().toString());
    }

}
