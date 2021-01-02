package com.shaadicom.utils

import android.content.Context
import androidx.fragment.app.Fragment

class BaseFragment: Fragment() {

    var fragmentActions: FragmentActions? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentActions = context as? FragmentActions
        if (fragmentActions == null) {
//            throw ClassCastException("$context must implement OnArticleSelectedListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentActions = null
    }
}