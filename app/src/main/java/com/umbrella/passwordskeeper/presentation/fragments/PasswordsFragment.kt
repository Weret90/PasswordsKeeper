package com.umbrella.passwordskeeper.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.umbrella.passwordskeeper.R
import com.umbrella.passwordskeeper.databinding.FragmentPasswordsBinding
import com.umbrella.passwordskeeper.presentation.adapter.PasswordsAdapter
import com.umbrella.passwordskeeper.presentation.viewmodels.PasswordsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PasswordsFragment : Fragment() {

    private var _binding: FragmentPasswordsBinding? = null
    private val binding get() = _binding!!
    private val passwordsAdapter: PasswordsAdapter by lazy {
        PasswordsAdapter()
    }
    private val viewModel: PasswordsViewModel by viewModel()

    companion object {
        fun newInstance(): PasswordsFragment {
            return PasswordsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPasswordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.passwordsRecyclerView.adapter = passwordsAdapter

        setupSwipeListener()

        viewModel.passwordsListLiveData.observe(viewLifecycleOwner) {
            passwordsAdapter.submitList(it)
        }

        binding.addNewPassword.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, AddPasswordFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setupSwipeListener() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.START or ItemTouchHelper.END) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val password = passwordsAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deletePassword(password)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.passwordsRecyclerView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}