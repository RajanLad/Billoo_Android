package fr.billoo.mobile.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import fr.billoo.mobile.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private PieChart chart;
    ArrayList<PieEntry> values;
    TextView enterprise,noofitemsbought,totalcostspent,lastdateofpurchase;
    RelativeLayout textInfo;
    String stores[]= {"Auchan","Carrefour","Lidl","E-leclerc"};;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        textInfo=(RelativeLayout)view.findViewById(R.id.textualInfo);

        enterprise = (TextView) view.findViewById(R.id.titleHead);
        noofitemsbought = (TextView) view.findViewById(R.id.noOfItemsBought);
        totalcostspent = (TextView) view.findViewById(R.id.totalMoneySpent);
        lastdateofpurchase = (TextView) view.findViewById(R.id.lastPurchaseDate);

        chart = view.findViewById(R.id.chart);
        chart.setBackgroundColor(Color.WHITE);

        moveOffScreen();

        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);

        //chart.setCenterTextTypeface(tfLight);
        //chart.setCenterText(generateCenterSpannableText());

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);

        chart.setDrawCenterText(true);

        chart.setRotationEnabled(false);
        chart.setHighlightPerTapEnabled(true);

        chart.setMaxAngle(180f); // HALF CHART
        chart.setRotationAngle(180f);
        chart.setCenterTextOffset(0, -20);

        setData(4, 100);

        chart.animateY(1400, Easing.EaseInOutQuad);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chart.setEntryLabelColor(Color.WHITE);
        //chart.setEntryLabelTypeface(tfRegular);
        chart.setEntryLabelTextSize(12f);

        chart.setTouchEnabled(true);

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                int i=(int)h.getX();

                if(i==0)
                {
                    enterprise.setText(stores[i]);
                    enterprise.setTextColor(ColorTemplate.MATERIAL_COLORS[i]);
                    noofitemsbought.setText(getResources().getString(R.string.noofitemsbought)+" "+(i+1+(int)Math.random()*200));
                    totalcostspent.setText(getResources().getString(R.string.totalmoneyspenthere)+" "+(i+(int)Math.random()*100));
                    lastdateofpurchase.setText(getResources().getString(R.string.lastdateofpurchase)+" 07/07/2019");
                }
                else if(i==1)
                {
                    enterprise.setText(stores[i]);
                    enterprise.setTextColor(ColorTemplate.MATERIAL_COLORS[i]);
                    noofitemsbought.setText(getResources().getString(R.string.noofitemsbought)+" "+(i+(int)Math.random()*200));
                    totalcostspent.setText(getResources().getString(R.string.totalmoneyspenthere)+" "+(i+(int)Math.random()*100));
                    lastdateofpurchase.setText(getResources().getString(R.string.lastdateofpurchase)+" 07/07/2019");
                }
                else if(i==2)
                {
                    enterprise.setText(stores[i]);
                    enterprise.setTextColor(ColorTemplate.MATERIAL_COLORS[i]);
                    noofitemsbought.setText(getResources().getString(R.string.noofitemsbought)+" "+(i+(int)Math.random()*200));
                    totalcostspent.setText(getResources().getString(R.string.totalmoneyspenthere)+" "+(i+(int)Math.random()*100));
                    lastdateofpurchase.setText(getResources().getString(R.string.lastdateofpurchase)+" 07/07/2019");
                }
                else if(i==3)
                {
                    enterprise.setText(stores[i]);
                    enterprise.setTextColor(ColorTemplate.MATERIAL_COLORS[i]);
                    noofitemsbought.setText(getResources().getString(R.string.noofitemsbought)+" "+(i+(int)Math.random()*200));
                    totalcostspent.setText(getResources().getString(R.string.totalmoneyspenthere)+" "+(i+(int)Math.random()*100));
                    lastdateofpurchase.setText(getResources().getString(R.string.lastdateofpurchase)+" 07/07/2019");
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });

        return view;
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

    private void setData(int count, float range) {

        values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            values.add(new PieEntry((float) ((Math.random() * range) + range / 5),stores[i % stores.length]));
        }

        PieDataSet dataSet = new PieDataSet(values, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        //data.setValueTypeface(tfLight);
        chart.setData(data);



        chart.invalidate();
    }

    private void moveOffScreen() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;

        int offset = (int)(height * 0.15); /* percent to move */

        RelativeLayout.LayoutParams rlParams =
                (RelativeLayout.LayoutParams) chart.getLayoutParams();
        rlParams.setMargins(0, 0, 0, -offset);

        chart.setLayoutParams(rlParams);
    }

}
