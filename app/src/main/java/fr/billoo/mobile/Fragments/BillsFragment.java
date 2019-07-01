package fr.billoo.mobile.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.*;

import fr.billoo.mobile.Adapters.BillsAdapter;
import fr.billoo.mobile.Models.BillsModel;
import fr.billoo.mobile.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BillsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BillsFragment#} factory method to
 * create an instance of this fragment.
 */
public class BillsFragment extends Fragment implements BillsAdapter.ItemClickListener {
    // TODO: Rename parameter arguments, choose names that match

    private OnFragmentInteractionListener mListener;
    BillsAdapter billsAdapter;

    public BillsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_bills, container, false);

        List<BillsModel> animalNames = new ArrayList<>();
        animalNames.add(new BillsModel("ABC","Alimentaires","20EUR","03/04/19"));
        animalNames.add(new BillsModel("ABC","Alimentaires","20EUR","03/04/19"));
        animalNames.add(new BillsModel("ABC","Alimentaires","20EUR","03/04/19"));
        animalNames.add(new BillsModel("ABC","Alimentaires","20EUR","03/04/19"));



        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.billsList);
        //recyclerView.setLayoutManager(new CoordinatorLayout(getContext()));
        billsAdapter = new BillsAdapter(getContext(), animalNames);
        recyclerView.setAdapter(billsAdapter);

        billsAdapter.setClickListener(this);



    return view;
    }

    @Override
    public void onItemClick(View view, int position) {

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
