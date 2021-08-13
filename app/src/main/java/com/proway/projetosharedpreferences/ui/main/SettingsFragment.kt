package com.proway.projetosharedpreferences.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.core.content.edit
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial
import com.proway.projetosharedpreferences.MainActivity
import com.proway.projetosharedpreferences.R
import com.proway.projetosharedpreferences.preferences.AppPreferences
import com.proway.projetosharedpreferences.preferences.Keys

class SettingsFragment : Fragment(R.layout.settings_fragment) {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var preferences: AppPreferences
    private lateinit var emailSwitch: SwitchMaterial
    private lateinit var pushSwitch: SwitchMaterial
    private lateinit var emailTextView: TextView
    private lateinit var buttonClear: MaterialButton
    private var activityManager: MainActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        preferences = AppPreferences(requireContext())
        activityManager = (requireActivity() as? MainActivity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailSwitch = view.findViewById<SwitchMaterial>(R.id.emailSwitch)
        pushSwitch = view.findViewById<SwitchMaterial>(R.id.pushSwitch)
        emailTextView = view.findViewById<TextView>(R.id.emailTextView)
        buttonClear = view.findViewById<MaterialButton>(R.id.buttonClear)

        checkSavedValues()

        emailSwitch.setOnCheckedChangeListener { button, value ->
            preferences.setValue<Boolean>(Keys.EMAIL_NOTIFICATION, value)
        }
        pushSwitch.setOnCheckedChangeListener { button, value ->
            preferences.setValue<Boolean>(Keys.PUSH_NOTIFICATION, value)
        }
        buttonClear.setOnClickListener {
            preferences.base.edit { clear() }
            activityManager?.replaceFragment(MainFragment.newInstance())
        }


    }

    fun checkSavedValues() {
        emailSwitch.isChecked = preferences.base.getBoolean(Keys.EMAIL_NOTIFICATION.name, false)
        pushSwitch.isChecked = preferences.base.getBoolean(Keys.PUSH_NOTIFICATION.name, false)
        emailTextView.text = preferences.base.getString(Keys.EMAIL.name, "")
    }


}