package com.bignerdranch.android.hellomoon;


import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.MediaController;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;


public class HelloMoonFragment extends Fragment {
    private Button mPlayButton;
    private Button mStopButton;
    private Button mPauseButton;
    private AudioPlayer mPlayer = new AudioPlayer();
    private VideoView mVideo;
    private MediaController mediaController;
    private Uri resourceUri = Uri.parse("android.resource://" + "com.bignerdranch.android.hellomoon/raw/apollo_17_stroll");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_hello_moon, parent, false);


        mVideo = (VideoView) v.findViewById(R.id.hellomoon_video);

        mVideo.setVideoURI(resourceUri);
        mPlayButton = (Button)v.findViewById(R.id.hellomoon_playButton);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mPlayer.play(getActivity());
                if (mVideo.isPlaying())
                    mVideo.pause();
                else {
                    mVideo.start();
                }


            }
        });


        mStopButton = (Button)v.findViewById(R.id.hellomoon_stopButton);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mPlayer.stop();
                mVideo.stopPlayback();
                mVideo.setVideoURI(resourceUri);

            }
        });


        return v;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.stop();

    }
}
