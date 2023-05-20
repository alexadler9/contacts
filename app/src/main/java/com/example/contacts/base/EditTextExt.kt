package com.example.contacts.base

import android.widget.EditText

fun EditText.setOnFocusChangeListenerWithCursorAtEnd() {
    this.setOnFocusChangeListener { _, hasFocus ->
        if (hasFocus) {
            this.setSelection(this.text.length);
        }
    }
}