package com.nikhil.finalabgec.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nikhil.finalabgec.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class about_dev extends Fragment {

    ImageView back;
    View view;
    private Context contextNullSafe;
    CircleImageView profile_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about_dev, container, false);
        if (contextNullSafe == null) getContextNullSafety();


        back = view.findViewById(R.id.imageView4);
        profile_image = view.findViewById(R.id.profile_image);

        back.setOnClickListener(v-> back());
        view.findViewById(R.id.linkedin).setOnClickListener(s->{
            String url = "https://www.linkedin.com/in/aditya-verma-a65817217/";
            Intent linkedInAppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            linkedInAppIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
            startActivity(linkedInAppIntent);
        });

        view.findViewById(R.id.insta).setOnClickListener(s->{
            Intent insta_in;
            String scheme = "http://instagram.com/_u/"+"verma_ji55";
            String path = "https://instagram.com/"+"verma_ji55";
            String nomPackageInfo ="com.instagram.android";
            try {
                getContextNullSafety().getPackageManager().getPackageInfo(nomPackageInfo, 0);
                insta_in = new Intent(Intent.ACTION_VIEW, Uri.parse(scheme));
            } catch (Exception e) {
                insta_in = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
            }
            startActivity(insta_in);
        });

        profile_image.setOnClickListener(v -> {
            back();
            Profile profile = new Profile();
            Bundle args = new Bundle();
            args.putString("uid_sending_profile", "P9KGKwc922cFknGqL34QYDgcVIZ2");
            profile.setArguments(args);
            ((FragmentActivity) v.getContext()).getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                    .add(R.id.container, profile)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }

    private void back(){
        FragmentManager fm=((FragmentActivity) getContextNullSafety()).getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        if(fm.getBackStackEntryCount()>0) {
            fm.popBackStack();
        }
        ft.commit();
    }


    public Context getContextNullSafety() {
        if (getContext() != null) return getContext();
        if (getActivity() != null) return getActivity();
        if (contextNullSafe != null) return contextNullSafe;
        if (getView() != null && getView().getContext() != null) return getView().getContext();
        if (requireContext() != null) return requireContext();
        if (requireActivity() != null) return requireActivity();
        if (requireView() != null && requireView().getContext() != null)
            return requireView().getContext();

        return null;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        contextNullSafe = context;
    }
}