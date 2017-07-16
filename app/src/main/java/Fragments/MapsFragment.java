package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mylife.faiza.spara.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback {
    FrameLayout frameLayout;
    //  private GoogleMap mMap;
    private SupportMapFragment supportMapFragment;

    private final LatLng HAMBURG = new LatLng(53.558, 9.927);
    private final LatLng KIEL = new LatLng(53.551, 9.993);

    private static final String ARG_SECTION_NUMBER = "section_number";

    private GoogleMap mMap;
    private Marker marker;
    private MapsFragment mapsFragment;


    public MapsFragment() {
        // Required empty public constructor
    }

  /*  public void onResume() {
        super.onResume();

        Log.d("MyMap", "onResume");
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {

        if (mMap == null) {

            Log.d("MyMap", "setUpMapIfNeeded");

          //  getMapAsync(this);
        supportMapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("MyMap", "onMapReady");
        mMap = googleMap;
        setUpMap();
    }

    private void setUpMap() {

        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.getUiSettings().setMapToolbarEnabled(false);


        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.getUiSettings().setMapToolbarEnabled(false);

        Marker hamburg = mMap.addMarker(new MarkerOptions().position(HAMBURG)
                .title("Hamburg"));
        Marker kiel = mMap.addMarker(new MarkerOptions()
                .position(KIEL)
                .title("Kiel")
                .snippet("Kiel is cool")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_launcher)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }
}*/


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mapView = inflater.inflate(R.layout.fragment_maps, container, false);
        // Inflate the layout for this fragment
        frameLayout = (FrameLayout)mapView.findViewById(R.id.mapFragment);
        //  mapsFragment = (MapsFragment)getFragmentManager().findFragmentById(R.id.map);
        //before  supportMapFragment = (SupportMapFragment)getFragmentManager().findFragmentById(R.id.map);
        supportMapFragment = (SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.map);


        supportMapFragment.getMapAsync(this);
        //   supportMapFragment.getMapAsync((OnMapReadyCallback) this.getActivity());


        return mapView ;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng( 28.5846875, 77.3159296);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }}

    /*
   public void onDestroyView() {
        super.onDestroyView();

        if (this.getActivity() != null
                && getFragmentManager().findFragmentById(
                this.getActivity().getTaskId()) != null) {

            getFragmentManager().beginTransaction().remove(this)
                    .commit();
            this.= null;
        }
    }*/

