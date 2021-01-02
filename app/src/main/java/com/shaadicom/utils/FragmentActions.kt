package com.shaadicom.utils

class FragmentActions {

    interface FragmentActions {
        fun onActionClick(actionType: ActionType)
    }

    /**
     * List of actions. Create more if required
     */
    enum class ActionType {
        OPEN_STAFF_DIR,
        BACK_PRESSED
    }
}