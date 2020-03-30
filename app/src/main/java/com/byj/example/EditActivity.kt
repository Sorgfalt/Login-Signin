package com.byj.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.byj.example.data.TodoData
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*

class EditActivity : AppCompatActivity() {

    val realm = Realm.getDefaultInstance() //getDeaultInstance로 인스턴스 얻고

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
    }

    val calendar: Calendar = Calendar.getInstance() //getInstance() 함수로 객체를 얻어옴

    calendar.set(Calendar.YEAR, year) //년도 설정
    calendar.set(Calendar.MONTH, month) //월 설정
    calendar.set(Calendar.DAY, day) //일 설정

    val time: Long = calendar.timeInMillis //날짜를 Long형으로 변환 , Long형 시간을 1000분의 1초 단위로 계산산


    //추가
    private fun insertTodo() {
        realm.beginTransaction() //트랜잭션 시작 //db 상태 변화 수행 title 입력과 date 지정 후 커밋트랜잭션으로 종료

        val newItem = realm.createObject<TodoData>(nextId()) // 새 객체 생성
        newItem.title = todoEditText.text.toString() //title은 todoEditText에 입력한 제목
        newItem.date = calendar.timeInMillis

        realm.commitTransaction() //트랜잭션 종료

        alert("내용이 추가되었습니다.") {
            yesButton { finish() }
        }.show()
    }


    //수정
    private fun updateTodo(id: Long) { //id를 파라미터로 받고
        realm.beginTransaction() //트랜잭션 시작

        val updateItem = realm.where<TodoData>().equalTo("id", id).findFirst()!! // where<T>()메서드가 반환하는 T타입 객체로부터 데이터 얻음 ,
        // <TodoData>로부터 데이터 얻고 equalTo()메서드로 조건 설정 (선택한 기준 메서드에 따리 지정된 키 또는 값과 동일한 항목 반환)
        // "id" 컬럼에 id값이 있다면 findFirst로 첫 번째 데이터 반환

        updateItem.title = todoEditText.text.toString()
        updateItem.date = calendar.timeInMillis

        realm.commitTransaction()

        alert("내용이 변경되었습니다"){
            yesButton{finish()}
        }.show()
    }

    //삭제
    private fun deleteTodo(id: Long){
        realm.beginTransaction() //트랜잭션 시작

        val deleteItem = realm.where<TodoData>().equalTo("id",id).findFirst()!!

        deleteItem.deleteFromRealm() // Realm에서 삭제
        realm.commitTransaction() // 트랜잭션 종료

        alert("내용이 삭제되었습니다"){
            yesButton{finish()}
        }.show()
    }

    private fun nextId(): Int {
        val maxId =
            realm.where<TodoData>().max("id") //TodoData 테이블의 모든 값을 얻을려면 where<TodoData>() 함수를 사용
        if (maxId != null) {
            return maxId.toInt() + 1
        }
        return 0
    }


    override fun onDestroy() {
        super.onDestroy()
        realm.close() //인스턴스 해제
    }
}
