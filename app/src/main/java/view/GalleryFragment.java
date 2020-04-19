package view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import adaptar.AndroidFlavorAdapter;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import beans.Item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.seniorplus.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArrayList<Item> androidFlavors = new ArrayList<Item>();
        androidFlavors.add(new Item(R.drawable.avastertony));
        androidFlavors.add(new Item(R.drawable.avastertony));
        androidFlavors.add(new Item(R.drawable.avastertony));
        androidFlavors.add(new Item(R.drawable.avastertony));
        androidFlavors.add(new Item(R.drawable.avastertony));
        AndroidFlavorAdapter flavorAdapter = new AndroidFlavorAdapter(this.getActivity(), androidFlavors);

        // Get a reference to the ListView, and attach the adapter to the listView.
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listview_gallery);
        listView.setAdapter(flavorAdapter);
        return view;
    }
}
