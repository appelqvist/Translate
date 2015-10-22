package com.group.translate;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private Button btnSpeak;
    private EditText etInput;
    private Spinner spCategory, spLanguage;
    private ListView lvPhrases;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        init(view);
        listener();
        setAdapter();
        return view;
    }

    private void listener() {
        ButtonListener bl = new ButtonListener();
        btnSpeak.setOnClickListener(bl);

    }

    private void init(View view) {
        btnSpeak = (Button)view.findViewById(R.id.btnSpeak);
        etInput = (EditText)view.findViewById(R.id.etInput);
        spCategory = (Spinner)view.findViewById(R.id.spCategory);
        spLanguage = (Spinner)view.findViewById(R.id.spLanguage);
        lvPhrases = (ListView)view.findViewById(R.id.lvPhrase);
    }

    private void setAdapter(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                getActivity().getResources().getStringArray(R.array.Lang_Array));
        spLanguage.setAdapter(adapter);
    }


    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }
}
