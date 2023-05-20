package org.android.go.sopt.util.extension

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

