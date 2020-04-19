package view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import adaptar.AndroidFlavorAdapter;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import beans.Item;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.seniorplus.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiaryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArrayList<Item> androidFlavors = new ArrayList<Item>();
        androidFlavors.add(new Item(R.drawable.donut));
        androidFlavors.add(new Item(R.drawable.donut));
        androidFlavors.add(new Item(R.drawable.donut));
        androidFlavors.add(new Item(R.drawable.donut));
        androidFlavors.add(new Item(R.drawable.donut));
        AndroidFlavorAdapter flavorAdapter = new AndroidFlavorAdapter(this.getActivity(), androidFlavors);

        // Get a reference to the ListView, and attach the adapter to the listView.
        View view = inflater.inflate(R.layout.fragment_diary, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listview_diary);
        listView.setAdapter(flavorAdapter);
        return view;
    }
}
