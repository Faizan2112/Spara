package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.mylife.faiza.spara.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends Fragment implements View.OnClickListener {

    ImageButton fabButton ;
    ImageButton fabButton1 ;
    ImageButton fabButton2 ;
    ImageButton fabButton3 ;
    public WalletFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View walletView = inflater.inflate(R.layout.fragment_wallet, container, false);
        // Inflate the layout for this fragment
        fabButton=(ImageButton)walletView.findViewById(R.id.wallet_float_button);
        fabButton1=(ImageButton)walletView.findViewById(R.id.wallet_float_button_request);
        fabButton2=(ImageButton)walletView.findViewById(R.id.wallet_float_button_send);
        fabButton3=(ImageButton)walletView.findViewById(R.id.wallet_float_button_profile);
        fabButton.setOnClickListener(this);
        return walletView ;

    }

    @Override
    public void onClick(View v) {
        fabButton1.setVisibility(View.VISIBLE);
        fabButton2.setVisibility(View.VISIBLE);
        fabButton3.setVisibility(View.VISIBLE);
    }
}
