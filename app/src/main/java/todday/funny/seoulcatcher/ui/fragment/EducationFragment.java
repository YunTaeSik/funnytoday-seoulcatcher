package todday.funny.seoulcatcher.ui.fragment;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.HashMap;
import java.util.Locale;

import todday.funny.seoulcatcher.BaseActivity;
import todday.funny.seoulcatcher.R;
import todday.funny.seoulcatcher.databinding.EducationBinding;
import todday.funny.seoulcatcher.util.HeartSpeech;
import todday.funny.seoulcatcher.viewmodel.EducationViewModel;

public class EducationFragment extends Fragment implements View.OnClickListener{

    private EducationBinding binding = null;
    private EducationViewModel model = null;

    private ConstraintLayout emerLayout;
    private ConstraintLayout disasterLayout;
    private ConstraintLayout accidentLayout;
    private ConstraintLayout layout199;

    private ExpandableLayout expandable_emerLayout;
    private ExpandableLayout expandable_disasterLayout;
    private ExpandableLayout expandable_accidentLayout;

    public static EducationFragment newInstance() {
        Bundle args = new Bundle();
        EducationFragment fragment = new EducationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_education, container, false);
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            model = ((BaseActivity) getActivity()).getEducationViewModel();
            binding.setModel(model);
        }
        View view = binding.getRoot();

        init(view);

        return view;
    }

    private void init(View view){
        emerLayout = view.findViewById(R.id.layout_emergency);
        disasterLayout = view.findViewById(R.id.layout_disaster);
        accidentLayout = view.findViewById(R.id.layout_accident);
        layout199 = view.findViewById(R.id.layout_119);

        expandable_emerLayout = view.findViewById(R.id.expandable_layout_1);
        expandable_disasterLayout = view.findViewById(R.id.expandable_layout_2);
        expandable_accidentLayout = view.findViewById(R.id.expandable_layout_3);

        expandable_emerLayout.collapse();
        expandable_disasterLayout.collapse();
        expandable_accidentLayout.collapse();

        emerLayout.setOnClickListener(this);

        disasterLayout.setOnClickListener(this);

        accidentLayout.setOnClickListener(this);
        accidentLayout.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layout_emergency:
                if(expandable_emerLayout.isExpanded())
                    expandable_emerLayout.collapse();
                else {
                    expandable_accidentLayout.collapse();
                    expandable_disasterLayout.collapse();
                    expandable_emerLayout.expand();
                }
                break;
            case R.id.layout_disaster:
                if(expandable_disasterLayout.isExpanded())
                    expandable_disasterLayout.collapse();
                else {
                    expandable_emerLayout.collapse();
                    expandable_accidentLayout.collapse();
                    expandable_disasterLayout.expand();
                }
                    break;
            case R.id.layout_accident:
                if(expandable_accidentLayout.isExpanded())
                    expandable_accidentLayout.collapse();
                else {
                    expandable_disasterLayout.collapse();
                    expandable_emerLayout.collapse();
                    expandable_accidentLayout.expand();
                }
                break;
            case R.id.layout_119:
                break;
        }
    }
}
