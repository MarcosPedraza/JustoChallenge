package com.marcospb.justocallenge.ui.user_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcospb.justocallenge.domain.mappers.mapUserToDomain
import com.marcospb.justocallenge.domain.models.UserDomain
import com.marcospb.justocallenge.domain.use_cases.UserUserCasesImp
import com.marcospb.justocallenge.utils.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class UserListViewModel @Inject constructor(private val userCasesImp: UserUserCasesImp) :
    ViewModel() {


    private val _userListData = MutableLiveData<ResourceState<List<UserDomain>>>()
    val userListLivedata = _userListData


    fun getUserList() {
        viewModelScope.launch(Dispatchers.IO) {


            try {

                val userList = userCasesImp.getUsersList()
                if (userList.isSuccessful) {
                    val users = userList.body()?.results?.map {
                        mapUserToDomain(it!!)
                    }
                    _userListData.postValue(ResourceState.Success(users))
                } else {
                    _userListData.postValue(
                        ResourceState.Error(
                            null,
                            exception = Exception(userList.errorBody()?.string() ?: "Unknown Error")
                        )
                    )

                }

            }catch (exc:Exception){

                _userListData.postValue(
                    ResourceState.Error(
                        null,
                        exception = exc
                    )
                )
            }


        }
    }


}