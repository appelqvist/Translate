package com.group.translate;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private String TAG = "MainFragment";
    private Button btnSpeak, btnTranslate;
    private EditText etInput;
    private Spinner spOutLanguage, spInLanguage;
    private TextView tvTextTranslated;
    private ListView lvPhrases;
    private Controller controller;
    private String[] phrases;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        init(view);
        listener();
        setSpinnerAdapter();
        setListViewAdapter();
        return view;
    }

    private void setListViewAdapter() {
        phrases = getActivity().getResources().getStringArray(R.array.Swe_Phrases);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, phrases);
        lvPhrases.setAdapter(adapter);
    }

    private void listener() {
        ButtonListener bl = new ButtonListener();
        btnSpeak.setOnClickListener(bl);
        btnTranslate.setOnClickListener(bl);


        //ListView Listener
        lvPhrases.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Klickade på: " + phrases[position]);
                controller.translateAndSpeak("Svenska", spOutLanguage.getSelectedItem().toString(), phrases[position]);
            }
        });

    }

    private void init(View view) {
        btnSpeak = (Button) view.findViewById(R.id.btnSpeak);
        btnTranslate = (Button)view.findViewById(R.id.btnTranslate);
        etInput = (EditText) view.findViewById(R.id.etInput);
        tvTextTranslated = (TextView)view.findViewById(R.id.tvTextTranslated);
        spOutLanguage = (Spinner) view.findViewById(R.id.spLangOut);
        spInLanguage = (Spinner) view.findViewById(R.id.spLanguage);
        lvPhrases = (ListView) view.findViewById(R.id.lvPhrase);
    }

    private void setSpinnerAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                getActivity().getResources().getStringArray(R.array.Lang_Array));
        spInLanguage.setAdapter(adapter);
        spOutLanguage.setAdapter(adapter);
    }

    private void setTextTranslated(String str){
        tvTextTranslated.setText(str);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnSpeak) {
                controller.translateAndSpeak(spInLanguage.getSelectedItem().toString(),spOutLanguage.getSelectedItem().toString(), etInput.getText().toString());
                Log.d(TAG, "Klickar ");
            } else if (v.getId() == R.id.btnTranslate) {
                Log.d(TAG,"Click");
                String newText = controller.translate(spInLanguage.getSelectedItem().toString(), spOutLanguage.getSelectedItem().toString(),etInput.getText().toString());
                MainFragment.this.setTextTranslated(newText);
            }
        }
    }
}
