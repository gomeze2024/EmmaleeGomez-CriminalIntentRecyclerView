package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeRequiresPoliceBinding

class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class RequiresPoliceHolder(
    private val binding: ListItemCrimeRequiresPoliceBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.crimeRequiresPolice.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "Contacting Police!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}


class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        val bindingPolice = ListItemCrimeRequiresPoliceBinding.inflate(inflater, parent, false)
        return when (viewType) {
            1 -> RequiresPoliceHolder(bindingPolice)
            0 -> CrimeHolder(binding)
            else -> CrimeHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]
        when (holder) {
            is CrimeHolder -> holder.bind(crime)
            is RequiresPoliceHolder -> holder.bind(crime)
        }
    }

    override fun getItemCount() = crimes.size

    override fun getItemViewType(position: Int): Int {
        return when (crimes[position].requiresPolice) {
            true -> 1
            false -> 0
        }
    }
}
