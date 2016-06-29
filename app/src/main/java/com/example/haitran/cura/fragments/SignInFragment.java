package com.example.haitran.cura.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haitran.cura.R;
import com.example.haitran.cura.activities.SignInActivity;
import com.example.haitran.cura.activities.SplashActivity;
import com.example.haitran.cura.objects.Personal;
import com.example.haitran.cura.views.adapters.ListviewSignInAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hai.tran on 6/28/2016.
 */
public class SignInFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private ListView mListView;
    private int mPage;
    List list;

//    public static SignInFragment newInstance(int page) {
//        Bundle args = new Bundle();
//        args.putInt(ARG_PAGE, page);
//        SignInFragment fragment = new SignInFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    public void setmPage(int i)
    {
        mPage=i;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mPage = getArguments().getInt(ARG_PAGE);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        list = new ArrayList();
        list.add(new Personal("Mr. Tom F", "Educator"));
        list.add(new Personal("Mrs. Kl S", "Doctor"));
        list.add(new Personal("Mr. Win K", "Doctor"));
        list.add(new Personal("Mr. Ami S", "Educator"));
        list.add(new Personal("Mrs. Krin R", "Doctor"));
        ListviewSignInAdapter listviewSignInAdapter = new ListviewSignInAdapter(getContext(), list);
        mListView = (ListView) view.findViewById(R.id.listView);
        mListView.setAdapter(listviewSignInAdapter);

        if (mPage == 2)
            listviewSignInAdapter.appendList(getList(list, false));
        if (mPage == 3)
            listviewSignInAdapter.appendList(getList(list, true));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                TextView textView = (TextView) view.findViewById(R.id.signin_textName);
//                Toast.makeText(getContext(), textView.getText()+"", Toast.LENGTH_LONG).show();
//                intent.putExtra("NAME", textView.getText()+"");
//                getActivity().startActivity(intent);
//                getActivity().finish();
                AuthenticationFragment frag = new AuthenticationFragment();
                frag.setNameEducator(textView.getText()+"");
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.signin_line2, frag, "PAGE_2").commit();
            }
        });
        return view;
    }

    private List<Personal> getList(List<Personal> list, boolean doctor) {
        List<Personal> listResul = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (doctor && list.get(i).getLevel().equalsIgnoreCase("Doctor"))
                listResul.add(list.get(i));
            else if (!doctor && list.get(i).getLevel().equalsIgnoreCase("Educator"))
                listResul.add(list.get(i));
        }
        return listResul;
    }
}
