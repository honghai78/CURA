package com.example.haitran.cura.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.haitran.cura.R;
import com.example.haitran.cura.objects.Personal;
import com.example.haitran.cura.views.adapters.ListviewSignInAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hai.tran on 6/28/2016.
 */
public class SignInFragment extends Fragment {
        public static final String ARG_PAGE = "ARG_PAGE";

        private int mPage;

        public static SignInFragment newInstance(int page) {
            Bundle args = new Bundle();
            args.putInt(ARG_PAGE, page);
            SignInFragment fragment = new SignInFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mPage = getArguments().getInt(ARG_PAGE);
        }

        // Inflate the fragment layout we defined above for this fragment
        // Set the associated text for the title
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_signin, container, false);
            List list = new ArrayList();
            list.add(new Personal("Mr. Tom F", "Educator"));
            list.add(new Personal("Mrs. Kl S", "Doctor"));
            list.add(new Personal("Mr. Win K", "Doctor"));
            list.add(new Personal("Mr. Ami S", "Educator"));
            list.add(new Personal("Mrs. Krin R", "Doctor"));
            ListviewSignInAdapter listviewSignInAdapter = new ListviewSignInAdapter(getContext(), list);
            ListView listView = (ListView) view.findViewById(R.id.listView);
            listView.setAdapter(listviewSignInAdapter);

            if(mPage==2)
                listviewSignInAdapter.appendList(getList(list, false));
            if(mPage==3)
                listviewSignInAdapter.appendList(getList(list, true));
            return view;
        }
    private List<Personal> getList(List<Personal> list, boolean doctor)
    {
        List<Personal> listResul = new ArrayList();
        for(int i=0; i< list.size(); i++)
        {
            if(doctor && list.get(i).getLevel().equalsIgnoreCase("Doctor")) listResul.add(list.get(i));
            else if(!doctor &&  list.get(i).getLevel().equalsIgnoreCase("Educator")) listResul.add(list.get(i));
        }
        return listResul;
    }
}
