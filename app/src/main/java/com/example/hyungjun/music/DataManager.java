package littlecold2.welfare_project;

import android.app.Activity;

public class DataManager {

    private static DataManager instance = null;
    private Activity activity;


    private DataManager(){

        //생성자앞에 private으로 선언하면서,

        //다른 클래스에서 new 키워드를 사용하여 인스턴스를 만들 수 있는 여지를 막음.



    }

    public static DataManager getInstance(){
        if(instance == null){
            instance = new DataManager();
        }
        return instance;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;

    }

}



