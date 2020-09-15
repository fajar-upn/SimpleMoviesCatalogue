package com.example.submission.ui.Settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.submission.R



class SettingsFragment() : Fragment(){

    private lateinit var settingPreference: SettingPreference
    private lateinit var notificationReceiver: NotificationReceiver

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_settings, container, false)

        val switchReminder:Switch = root.findViewById(R.id.swDailyReminder)

        switchReminder.isChecked = settingPreference.getDailyReminder()

        switchReminder.setOnClickListener {
            if (switchReminder.isChecked) {
                notificationReceiver.setDailyAlarm(requireContext().applicationContext)
                settingPreference.setDailyReminder(true)
                Toast.makeText(
                    requireContext().applicationContext,
                    "Pengingat harian diaktifkan",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                notificationReceiver.cancelAlarm(requireContext().applicationContext)
                settingPreference.setDailyReminder(false)
                Toast.makeText(
                    requireContext().applicationContext,
                    "Pengingat harian dinonaktifkan",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return root
    }
}