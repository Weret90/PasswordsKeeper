package com.umbrella.passwordskeeper.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.umbrella.passwordskeeper.databinding.ItemPasswordBinding
import com.umbrella.passwordskeeper.domain.entities.Password

class PasswordsAdapter : ListAdapter<Password, PasswordItemViewHolder>(PasswordItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordItemViewHolder {
        val binding = ItemPasswordBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PasswordItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PasswordItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}