package android.gabriel_izzo_c196_scheduler.Database;

import android.app.Application;
import android.gabriel_izzo_c196_scheduler.DAO.AssessmentDAO;
import android.gabriel_izzo_c196_scheduler.DAO.CourseDAO;
import android.gabriel_izzo_c196_scheduler.DAO.TermDAO;
import android.gabriel_izzo_c196_scheduler.Entity.Assessment;
import android.gabriel_izzo_c196_scheduler.Entity.Course;
import android.gabriel_izzo_c196_scheduler.Entity.Term;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    //DAOs
    private final AssessmentDAO mAssessmentDAO;
    private final CourseDAO mCourseDAO;
    private final TermDAO mTermDAO;

    //Lists of Entity Objects
    private List<Assessment> mAllAssessments;
    private List<Term> mAllTerms;
    private List<Course> mAllCourses;

    private static final int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        SchedulerDatabaseBuilder db=SchedulerDatabaseBuilder.getDatabase(application);
        mTermDAO=db.termDAO();
        mCourseDAO=db.courseDAO();
        mAssessmentDAO=db.assessmentDAO();
    }

    //Lists of all Terms, Courses and Assessments
    public List<Term> getAllTerms(){
        databaseExecutor.execute(()->{
            mAllTerms=mTermDAO.getAllTerms();
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllTerms;
    }
    public List<Course> getAllCourses(){
        databaseExecutor.execute(()->{
            mAllCourses=mCourseDAO.getAllCourses();
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllCourses;
    }
    public List<Assessment> getAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments=mAssessmentDAO.getAllAssessments();
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    //Insert methods
    public void insert(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.insert(term);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void insert(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.insert(course);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void insert(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.insert(assessment);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //Update methods
    public void update(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.update(assessment);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

    }
    public void update(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.update(term);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void update(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.update(course);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //Delete methods
    public void delete(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.delete(course);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void delete(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.delete(term);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void delete(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.delete(assessment);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    //For RecyclerView in Term Details
    public List<Course> getAssociatedCourses(int termID) {
        databaseExecutor.execute(() -> {
            mAllCourses = mCourseDAO.getAllAssociatedCourses(termID);
        });

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }
    //For RecyclerView in Course Details
    public List<Assessment> getAssociatedAssessments(int courseID) {
        databaseExecutor.execute(() -> {
            mAllAssessments = mAssessmentDAO.getAllAssocAssessments(courseID);
        });

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mAllAssessments;
    }
}

