package com.proway.projetosharedpreferences.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.content.edit
import com.proway.projetosharedpreferences.BuildConfig
import com.proway.projetosharedpreferences.MainActivity
import com.proway.projetosharedpreferences.R
import com.proway.projetosharedpreferences.preferences.AppPreferences
import com.proway.projetosharedpreferences.preferences.Keys

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var preferences: AppPreferences
    private lateinit var inputEmailEditText: EditText

    override fun onAttach(context: Context) {
        super.onAttach(context)
        preferences = AppPreferences(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputEmailEditText = view.findViewById(R.id.inputEmailTextView)

        checkSavedValues()

        view.findViewById<Button>(R.id.buttonSave).apply {
            setOnClickListener {
                preferences.setValue(Keys.EMAIL, inputEmailEditText.text.toString())
                (requireActivity() as? MainActivity)?.replaceFragment(SettingsFragment.newInstance())
            }
        }
    }

    fun checkSavedValues() {
        val savedEmail = preferences.base.getString(Keys.EMAIL.name, "")
        if (!savedEmail.isNullOrEmpty()) {
            inputEmailEditText.setText(savedEmail)
        }
    }


}