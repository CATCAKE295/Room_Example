package edu.uca.ni.roomapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.uca.ni.roomapp.R
import edu.uca.ni.roomapp.data.User
import edu.uca.ni.roomapp.data.UserViewModel
import edu.uca.ni.roomapp.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private  lateinit var mUserViewModel: UserViewModel
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnGuardar.setOnClickListener {
            insertDataToDatabase()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun insertDataToDatabase() {
       val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text

        if (inputCheck(firstName,lastName,age)){
            val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))

            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Agregado Correctamente",Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{


            Toast.makeText(requireContext(),"Por favor llene todos los campos",Toast.LENGTH_LONG).show()

        }
    }

    private fun inputCheck(firstName : String, lastName: String, age: Editable): Boolean{

        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())

    }

}