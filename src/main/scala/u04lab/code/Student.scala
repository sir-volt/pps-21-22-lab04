package u04lab.code

import u04lab.code.List.*
import u04lab.code.Option.*

trait Student:
  def name: String
  def year: Int
  def enrolling(course: Course*): Unit // the student participates to a Course
  def courses: List[String] // names of course the student participates to
  def hasTeacher(teacher: String): Boolean // is the student participating to a course of this teacher?

trait Course:
  def name: String
  def teacher: String

object Student:
  def apply(name: String, year: Int = 2017): Student =
    StudentImpl(name, year)
  case class StudentImpl(override val name: String, override val year: Int) extends Student:
    private var studentCourses: List[Course] = List.Nil()

    override def enrolling(course: Course*): Unit =
      course.foreach(el => studentCourses = append(studentCourses, List.Cons(el, List.Nil())))

    override def courses: List[String] =
      List.map(studentCourses)(c => c.name)

    override def hasTeacher(teacher: String): Boolean =
      contains(List.map(studentCourses)(c => c.teacher), teacher)


object Course:
  def apply(name: String, teacher: String): Course =
    CourseImpl(name, teacher)
  case class CourseImpl(override val name: String, override val teacher: String) extends Course

object sameTeacher:
  def unapply(courses: List[Course]): Option[String] =
    val teacher = map(take(courses, 1))(el => el.teacher)
    (map(courses)(el => el.teacher), teacher) match
      case (Cons(h, t), Cons(teach, _)) if(h == teach) => Some(teach)
      case _ => None()





@main def checkStudents(): Unit =
  val cPPS = Course("PPS", "Viroli")
  val cPCD = Course("PCD", "Ricci")
  val cSDR = Course("SDR", "D'Angelo")
  val s1 = Student("mario", 2015)
  val s2 = Student("gino", 2016)
  val s3 = Student("rino") // defaults to 2017
  val s4 = Student("giacomo")
  val allCourses = List(cPPS, cPCD, cSDR)
  val allStudents = List(s1, s2, s3, s4)

  println(allStudents)
  allCourses match
    case sameTeacher => println(s"$allCourses have same teacher $sameTeacher")
    case _ => println(s"$allCourses have different teachers")
  s1.enrolling(cPPS)
  s1.enrolling(cPCD)
  s2.enrolling(cPPS)
  s3.enrolling(cPPS)
  s3.enrolling(cPCD)
  s3.enrolling(cSDR)
  s4.enrolling(cSDR,cPPS,cPCD)
  println(
    (s1.courses, s2.courses, s3.courses)
  ) // (Cons(PCD,Cons(PPS,Nil())),Cons(PPS,Nil()),Cons(SDR,Cons(PCD,Cons(PPS,Nil()))))
  println(s1.hasTeacher("Ricci")) // true
  println(s4.courses)

/** Hints:
  *   - simply implement Course, e.g. with a case class
  *   - implement Student with a StudentImpl keeping a private Set of courses
  *   - try to implement in StudentImpl method courses with map
  *   - try to implement in StudentImpl method hasTeacher with map and find
  *   - check that the two println above work correctly
  *   - refactor the code so that method enrolling accepts a variable argument Course*
  */
