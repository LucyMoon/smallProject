package com.example.databindingexam

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class ObservableData : BaseObservable(){

    @get:Bindable
    var site : String = ""
        set(value) {
            if(value == ""){
                field = "NULL"
            }
            else{
                field = value
            }
            notifyPropertyChanged(BR.site)
        }
}
