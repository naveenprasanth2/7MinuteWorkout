package com.androstays.a7minuteworkout

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import com.androstays.a7minuteworkout.databinding.ActivityExerciseBinding
import java.nio.file.Paths
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    var binding: ActivityExerciseBinding? = null
    private var restTimer: CountDownTimer? = null
    private var exerciseTimer: CountDownTimer? = null
    private var restProgress: Int = 0
    private var exerciseProgress: Int = 0
    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition: Int = -1
    private var tts: TextToSpeech? = null
    private var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)

        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
        tts = TextToSpeech(this, this)
        exerciseList = Constants.defaultExerciseList()
        setupRestView()
    }

    private fun setupRestView() {

        try {
            val soundURI =
                Uri.parse("android.resource://com.androstays.a7minuteworkout/" + R.raw.press_start)
            player = MediaPlayer.create(applicationContext, soundURI)
            player?.isLooping = false
            player?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        restTimer?.let {
            restTimer?.cancel()
            restProgress = 0
        }
        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvUpcomingLabel?.visibility = View.VISIBLE
        binding?.tvExerciseTitle?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        setRestProgressBar()
    }

    private fun setRestProgressBar() {
        binding?.progressBar?.progress = restProgress
        if (currentExercisePosition < exerciseList!!.size - 1) {
            binding?.tvExerciseTitle?.text = exerciseList!![currentExercisePosition + 1].getName()
        }
        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                if (currentExercisePosition < exerciseList!!.size) {
                    currentExercisePosition++
                    setupExerciseView()
                } else {
                    Toast.makeText(this@ExerciseActivity, "its done", Toast.LENGTH_SHORT).show()
                }
            }

        }.start()
    }


    private fun setExerciseProgressBar() {
        binding?.progressBarExercise?.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = 30 - exerciseProgress
                binding?.tvTimerExercise?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                if (currentExercisePosition < exerciseList!!.size - 1) {
                    setupRestView()
                } else {
                    Toast.makeText(this@ExerciseActivity, "everything is done", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }.start()
    }

    private fun setupExerciseView() {
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvUpcomingLabel?.visibility = View.INVISIBLE
        binding?.tvExerciseTitle?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE

        exerciseList?.let { exerciseList!![currentExercisePosition].getName() }
            ?.let { speakOut(it) }

        exerciseTimer?.let {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePosition].getName()
        setExerciseProgressBar()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
            if ((result == TextToSpeech.LANG_MISSING_DATA) || (result == TextToSpeech.LANG_COUNTRY_AVAILABLE)) {
                Log.e("TTS", "The language specified is not supported")
            }
        } else {
            Log.e("TTS", "Initialisation Failed")
        }
    }

    private fun speakOut(text: String) {
        tts?.let { tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "") }
    }

    override fun onDestroy() {
        super.onDestroy()

        restTimer?.let {
            restTimer?.cancel()
            restProgress = 0
        }

        exerciseTimer?.let {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        tts?.let {
            tts?.stop()
            tts?.shutdown()
        }

        player?.let {
            player?.stop()
        }

        binding = null
    }
}