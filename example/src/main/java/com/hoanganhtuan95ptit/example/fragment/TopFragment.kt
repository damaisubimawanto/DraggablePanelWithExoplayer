package com.hoanganhtuan95ptit.example.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.MediaSourceFactory
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.hoanganhtuan95ptit.example.R
import kotlinx.android.synthetic.main.fragment_top.view.*

class TopFragment: Fragment() {
    private lateinit var mRootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mRootView = inflater.inflate(R.layout.fragment_top,container,false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bandwidthMeter = DefaultBandwidthMeter.Builder(requireContext()).setInitialBitrateEstimate(100000).build()
        val dataSourceFactory = DefaultDataSourceFactory(requireContext(), "DraggablePanel")
        val trackSelectionFactory = AdaptiveTrackSelection.Factory()
        val trackSelector = DefaultTrackSelector(requireContext(), trackSelectionFactory)
        val mediaSourceFactory: MediaSourceFactory = DefaultMediaSourceFactory(dataSourceFactory)
        val player: SimpleExoPlayer = SimpleExoPlayer.Builder(
            requireContext(),
            DefaultRenderersFactory(requireContext())
                .setExtensionRendererMode(DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF)
        )
            .setTrackSelector(trackSelector)
            .setMediaSourceFactory(mediaSourceFactory)
            .setBandwidthMeter(bandwidthMeter)
            .build()

        val uri = Uri.parse("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4")
        val mediaItem = MediaItem.Builder().setUri(uri).build()
        player.setMediaItem(mediaItem)
        player.prepare()
        player.playWhenReady = true
        mRootView.playerView.player = player
    }
}