package com.commonsware.todo

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.commonsware.todo.databinding.TodoDisplayBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DisplayFragment : Fragment() {
    val args: DisplayFragmentArgs by navArgs()
    private lateinit var binding: TodoDisplayBinding
    private val motor: SingleModelMotor by viewModel { parametersOf(args.modelId) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        TodoDisplayBinding.inflate(inflater, container, false).apply { binding = this }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        motor.getModel()?.let {
            binding.apply {
                completed.visibility = if (it.isCompleted) View.VISIBLE else View.GONE
                desc.text = it.description
                createdOn.text = DateUtils.getRelativeDateTimeString(
                    requireContext(),
                    it.createdOn.toEpochMilli(),
                    DateUtils.MINUTE_IN_MILLIS,
                    DateUtils.WEEK_IN_MILLIS,
                    0
                )
                notes.text = it.notes
            }
        }
    }


}