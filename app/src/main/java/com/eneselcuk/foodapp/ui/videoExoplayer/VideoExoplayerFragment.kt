package com.eneselcuk.foodapp.ui.videoExoplayer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import at.huber.youtubeExtractor.YouTubeExtractor
import com.eneselcuk.foodapp.R
import com.eneselcuk.foodapp.databinding.FragmentVideoExoplayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import dagger.hilt.android.AndroidEntryPoint
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YtFile
import android.util.SparseArray
import android.widget.Toast
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource


@AndroidEntryPoint
class VideoExoplayerFragment : Fragment() {

    private lateinit var binding: FragmentVideoExoplayerBinding
    private val args: VideoExoplayerFragmentArgs by navArgs()

    private var player: ExoPlayer? = null
    private var playWhenReady = true
    var currentWindow: Int = 0
    var playBackPosition = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_video_exoplayer, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPlayer()
    }

    private fun initPlayer() {
        player = ExoPlayer.Builder(requireContext()).build()
        binding.playerView.player = player

        youtube(args.videoYoutube)

    }

    @SuppressLint("StaticFieldLeak")
    private fun youtube(url: String) {

        try {
            object : YouTubeExtractor(requireContext()) {
                override fun onExtractionComplete(
                    ytFiles: SparseArray<YtFile>?,
                    videoMeta: VideoMeta?,
                ) {
                    if (ytFiles != null) {
                        val videoTag = 137
                        val audioTag = 140

                        val audioSource =
                            ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                                .createMediaSource(MediaItem.fromUri(ytFiles.get(audioTag).url))

                        if (ytFiles.get(videoTag).url.isNotEmpty()) {
                            val videoSource =
                                ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                                    .createMediaSource(MediaItem.fromUri(ytFiles.get(videoTag).url))

                            player?.setMediaSource(MergingMediaSource(
                                true, videoSource, audioSource), true)
                            player?.prepare()
                            player?.playWhenReady = playWhenReady
                            player?.seekTo(currentWindow, playBackPosition)
                        } else {
                            Toast.makeText(requireContext(), "OPPS", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }.extract(url)
        } catch (ex: Exception) {
            Toast.makeText(requireContext(), "${ex.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playWhenReady = exoPlayer.playWhenReady
            playBackPosition = exoPlayer.currentPosition
            currentWindow = exoPlayer.currentMediaItemIndex
            exoPlayer.release()
            player = null
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

}