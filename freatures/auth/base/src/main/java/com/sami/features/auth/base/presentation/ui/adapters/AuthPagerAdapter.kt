package com.sami.features.auth.base.presentation.ui.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sami.features.auth.base.R
import com.sami.features.auth.login.presentation.ui.LoginFragment
import com.sami.features.auth.register.prensetation.ui.RegisterFragment

class AuthPagerAdapter(fm: FragmentManager, context: Context) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = listOf(
        RegisterFragment(),
        LoginFragment()
    )

    private val titles = listOf(
        context.getString(R.string.register),
        context.getString(R.string.login)
    )

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}