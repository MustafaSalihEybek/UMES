package com.codecanyon.umes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.codecanyon.umes.R
import com.codecanyon.umes.model.Coordinator
import com.codecanyon.umes.model.FacultyDirector
import com.codecanyon.umes.model.InsDirector
import com.codecanyon.umes.model.Student
import com.codecanyon.umes.util.show
import com.codecanyon.umes.viewmodel.SignInViewModel
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment(), View.OnClickListener {
    private lateinit var v: View
    private lateinit var navDirection: NavDirections
    private lateinit var signInViewModel: SignInViewModel

    private lateinit var loginArrayAdapter: ArrayAdapter<*>
    private lateinit var loginType: String
    private lateinit var txtUserId: String
    private lateinit var txtUserPassword: String

    private fun init(){
        loginArrayAdapter = ArrayAdapter.createFromResource(v.context, R.array.LoginTypes, R.layout.spinner_item)
        loginArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sign_in_fragment_spinnerLoginType.adapter = loginArrayAdapter

        sign_in_fragment_spinnerLoginType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                p0?.let {
                    loginType = (p2 + 1).toString()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                p0?.let {
                    loginType = "1"
                }
            }
        }

        signInViewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        observeLiveData()

        sign_in_fragment_btnLogin.setOnClickListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        init()
    }

    private fun observeLiveData(){
        signInViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        signInViewModel.successMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.show(v, it)
            }
        })

        signInViewModel.coordinatorData.observe(viewLifecycleOwner, Observer {
            it?.let {
                goToMainPage(loginType, null, it, null, null)
            }
        })

        signInViewModel.facultyDirectorData.observe(viewLifecycleOwner, Observer {
            it?.let {
                goToMainPage(loginType, null, null, null, it)
            }
        })

        signInViewModel.studentData.observe(viewLifecycleOwner, Observer {
            it?.let {
                goToMainPage(loginType, it, null, null, null)
            }
        })

        signInViewModel.insDirectorData.observe(viewLifecycleOwner, Observer {
            it?.let {
                goToMainPage(loginType, null, null, it, null)
            }
        })
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it.id){
                R.id.sign_in_fragment_btnLogin -> loginUser(loginType)
            }
        }
    }

    private fun loginUser(type: String){
        txtUserId = sign_in_fragment_editUserId.text.toString()
        txtUserPassword = sign_in_fragment_editUserPassword.text.toString()

        if (!txtUserId.isEmpty()){
            if (!txtUserPassword.isEmpty())
                signInViewModel.loginToUser(txtUserId, txtUserPassword, type)
            else
                txtUserPassword.show(v, "Lütfen şifrenizi giriniz")
        }else
            txtUserId.show(v, "Lütfen numaranızı giriniz")
    }

    private fun goToMainPage(type: String, studentData: Student?, coordinatorData: Coordinator?, insDirectorData: InsDirector?, facultyDirectorData: FacultyDirector?){
        navDirection = SignInFragmentDirections.actionSignInFragmentToMainFragment(
            type,
            coordinatorData,
            facultyDirectorData,
            insDirectorData,
            studentData
        )
        Navigation.findNavController(v).navigate(navDirection)
    }
}