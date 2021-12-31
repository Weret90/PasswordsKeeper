package com.umbrella.passwordskeeper.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.umbrella.passwordskeeper.domain.entities.Password

class PasswordItemDiffCallback : DiffUtil.ItemCallback<Password>() {

    override fun areItemsTheSame(oldItem: Password, newItem: Password): Boolean {
        return oldItem.site == newItem.site
    }

    override fun areContentsTheSame(oldItem: Password, newItem: Password): Boolean {
        return oldItem == newItem
    }
}