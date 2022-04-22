package edu.uca.ni.roomapp.adapter

import android.view.LayoutInflater
import android.view.View

import androidx.navigation.findNavController
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import edu.uca.ni.roomapp.R
import edu.uca.ni.roomapp.data.User
import edu.uca.ni.roomapp.databinding.ItemListBinding
import edu.uca.ni.roomapp.fragments.ListFragmentDirections

class ListAdapter:RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var userList = emptyList<User>()

    class ListViewHolder(val binding: ItemListBinding):RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = userList[position]
        holder.binding.txtName.text = item.firstName.toString()
        holder.binding.txtLastname.text = item.lastName.toString()
        holder.binding.txtAge.text = item.age.toString()

        holder.binding.listLayout.setOnClickListener { view: View ->
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(item)
            findNavController(view).navigate(action)

        }
    }

    override fun getItemCount(): Int = userList.size

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

}