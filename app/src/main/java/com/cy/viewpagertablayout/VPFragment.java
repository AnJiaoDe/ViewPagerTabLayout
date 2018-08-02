package com.cy.viewpagertablayout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class VPFragment extends Fragment {

    private View view;

    public static  VPFragment create(String  title){
        VPFragment vpFragment=new VPFragment();
        Bundle bundle=new Bundle();
        bundle.putString("title",title);

        vpFragment.setArguments(bundle);
        return vpFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_firs, container, false);

        TextView textView= (TextView) view.findViewById(R.id.tv);
        textView.setText(getArguments().getString("title"));

        return view;
    }

}
