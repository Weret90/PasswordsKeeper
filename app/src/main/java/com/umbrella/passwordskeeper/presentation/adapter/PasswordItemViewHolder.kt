package com.umbrella.passwordskeeper.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.umbrella.passwordskeeper.R
import com.umbrella.passwordskeeper.databinding.ItemPasswordBinding
import com.umbrella.passwordskeeper.domain.entities.Password

class PasswordItemViewHolder(private val binding: ItemPasswordBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(password: Password) {
        binding.tvSite.text = String.format(
            binding.root.resources.getString(R.string.site_text_view), password.site
        )
        binding.tvPassword.text = String.format(
            binding.root.resources.getString(R.string.password_text_view), password.password
        )
    }
}