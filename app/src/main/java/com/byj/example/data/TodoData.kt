package com.byj.example.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TodoData (
    @PrimaryKey  //레코드를 구분할 수 있는 고유값
    var id : Long = 0,
    var title : String = "",
    var date : Long = 0
) : RealmObject(){  //RealmObject클래스 상속

}