package com.example.contacts.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

fun Fragment.modifySoftInputMode(softInputMode: Int): SoftInputModeModifier =
    SoftInputModeModifier(this, softInputMode)

val Fragment.viewLifecycle get() = viewLifecycleOwner.lifecycle

class SoftInputModeModifier(private val fragment: Fragment, private val softInputMode: Int) {

    private val previousSoftInputMode: Int =
        fragment.requireActivity().window.attributes.softInputMode
    private val lifecycleObserver = ModifySoftInputModeLifecycleObserver()

    init {
        changeSoftInputModeToNew()
        fragment.viewLifecycle.addObserver(lifecycleObserver)
    }

    /**
     * Sets the softInputMode specified for the current screen.
     */
    fun changeSoftInputModeToNew() {
        setSoftInputMode(softInputMode)
    }

    /**
     * Sets the initial softInputMode.
     */
    fun changeSoftInputModeBack() {
        setSoftInputMode(previousSoftInputMode)
    }

    private fun removeObserver() {
        fragment.viewLifecycle.removeObserver(lifecycleObserver)
    }

    private fun setSoftInputMode(softInputMode: Int) {
        fragment.requireActivity().window.setSoftInputMode(softInputMode)
    }

    private inner class ModifySoftInputModeLifecycleObserver : DefaultLifecycleObserver {

        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            changeSoftInputModeToNew()
        }

        override fun onPause(owner: LifecycleOwner) {
            super.onPause(owner)
            changeSoftInputModeBack()
        }

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            removeObserver()
        }
    }
}