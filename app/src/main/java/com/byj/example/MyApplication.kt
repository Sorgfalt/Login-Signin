package com.byj.example

import android.app.Application
import io.realm.Realm

class MyApplication : Application(){ //Application 클래스를 상속받아 MyApplication 클래스 생성
    override fun onCreate() {  //onCreate 함수를 오버라이드
        super.onCreate()
        Realm.init(this) //Realm.init()함수로 초기화 //init은 초기화 할때 사용
    }

}