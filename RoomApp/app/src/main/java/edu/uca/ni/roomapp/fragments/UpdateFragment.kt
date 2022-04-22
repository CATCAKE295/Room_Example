package edu.uca.ni.roomapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uca.ni.roomapp.R
import edu.uca.ni.roomapp.data.User
import edu.uca.ni.roomapp.data.UserViewModel
import edu.uca.ni.roomapp.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {

    private  lateinit var mUserViewModel: UserViewModel
    private val args by navArgs<UpdateFragmentArgs>()
    private var _binging: FragmentUpdateBinding? = null
    private val binding get() = _binging!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binging = FragmentUpdateBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.etFirstNameUpdate.setText(args.currentUser.firstName)
        binding.etLastNameUpdate.setText(args.currentUser.lastName)
        binding.etAgeUpdate.setText(args.currentUser.age.toString())

            binding.btnActualizar.setOnClickListener {
                updateItem()
            }

        setHasOptionsMenu(true)
    }



    override fun onDestroy() {
        super.onDestroy()
        _binging = null
    }

    private fun updateItem() {
       val uFirstName = binding.etFirstNameUpdate.text.toString()
       val uLastName = binding.etLastNameUpdate.text.toString()
       val uAge = Integer.parseInt(binding.etAgeUpdate.text.toString())

        if (inputCheck(uFirstName,uLastName,binding.etAgeUpdate.text)){

            val uUser = User(args.currentUser.id, uFirstName,uLastName,uAge)

            mUserViewModel.updateUser(uUser)

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

            Toast.makeText(requireContext(),"Actualizado Correctamente",Toast.LENGTH_SHORT).show()

        }else{

            Toast.makeText(requireContext(),"Llene todos Los campos",Toast.LENGTH_SHORT).show()

        }

    }


    private fun inputCheck(uFirstName : String, uLastName: String, uAge: Editable): Boolean{

        return !(TextUtils.isEmpty(uFirstName) && TextUtils.isEmpty(uLastName) && uAge.isEmpty())

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_icon){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->

            mUserViewModel.deleteUser(args.currentUser)

            Toast.makeText(requireContext(),"Borrado con exito el usuario: ${args.currentUser.firstName}",Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_ ->

        }

        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()

    }


}