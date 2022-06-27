package com.codecanyon.umes.data

import com.codecanyon.umes.model.Student

object StudentsData {
    private var numbers = arrayOf("19110131320", "19110131513", "1911016654")
    private var names = arrayOf("Mehmet Ali", "Bilal", "Nurettin")
    private var surNames = arrayOf("Ciğer", "Barbudak", "Kısa")
    private var coordinators = arrayOf("Zeynep Banu Özger", "Zeynep Banu Özger", "Yavuz Canbay")
    private lateinit var studentList: ArrayList<Student>
    private lateinit var student: Student

    fun getData() : ArrayList<Student>{
        studentList = ArrayList()

        /*for (stu in 0 until numbers.size){
            student = Student(numbers.get(stu), names.get(stu), surNames.get(stu), coordinators.get(stu))
            studentList.add(student)
        }*/

        return studentList
    }
}