package com.app.ebank.mbanking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Hichem Himovic on 15/06/2017.
 */

public class ActuFragment extends Fragment {
    ListView actu_list_view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.actufragment,container,false);
        actu_list_view = (ListView)rootView.findViewById(R.id.actu_list_view);
        ArrayList<ModelActu>SetOfActu = new ArrayList<ModelActu>();
        SetOfActu.add(new ModelActu("Devise Conférence","17/06/2017","10H","Salle de conférence"));
        SetOfActu.add(new ModelActu("Recrutement","20/07/2017","09H","Couloir"));
        SetOfActu.add(new ModelActu("Informations","22/07/2017","15H","Salle de conférence"));
        SetOfActu.add(new ModelActu("Nouveaux projets","09/09/2017","10H","Salle de présentation"));
        ActuAdapter actuAdapter = new ActuAdapter(getActivity(),SetOfActu);
        actu_list_view.setAdapter(actuAdapter);
        return rootView;
    }
}
