package com.group.translate;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
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
    private Controller controller;

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                getActivity().getResources().getStringArray(R.array.Swe_Phrases));
        lvPhrases.setAdapter(adapter);
    }

    private void listener() {
        ButtonListener bl = new ButtonListener();
        btnSpeak.setOnClickListener(bl);

    }


    private void init(View view) {
        btnSpeak = (Button)view.findViewById(R.id.btnSpeak);
        etInput = (EditText)view.findViewById(R.id.etInput);
        //spCategory = (Spinner)view.findViewById(R.id.spCategory);
        spLanguage = (Spinner)view.findViewById(R.id.spLanguage);
        lvPhrases = (ListView)view.findViewById(R.id.lvPhrase);
    }

    private void setSpinnerAdapter(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                getActivity().getResources().getStringArray(R.array.Lang_Array));
        spLanguage.setAdapter(adapter);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            controller.playText((String)spLanguage.getSelectedItem().toString(), etInput.getText().toString());
            Log.d("", "Klickar ");
        }
    }
}
