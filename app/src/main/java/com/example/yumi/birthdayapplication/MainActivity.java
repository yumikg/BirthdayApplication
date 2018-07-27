package com.example.yumi.birthdayapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity
        implements View.OnClickListener {  //画面遷移

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonToSub).setOnClickListener(this);//画面遷移

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        //((TextView)this.findViewById(R.id.textView)).setText(sdf1.format(cal.getTime()));


    }

    //画面遷移
    public void onClick(View view) {
        Intent intent = new Intent(this, SubActivity.class);
        startActivity(intent);
    }


    //////ここから誕生日までの日数計算

    //リーダ
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //フォーマッタ
    private static DateFormat formatYYYYMD = new SimpleDateFormat("yyyy年M月d日");
    private static DateFormat formatMMDD = new SimpleDateFormat("MMdd");


    public static void main(String[]args) {

        //当日の日付
        Calendar cal = createCalendar();
        System.out.print("今日は");
        System.out.println(formatYYYYMD.format(cal.getTime())+"です");
        System.out.println("あなたの次の誕生日までの日数は・・・");

        // 誕生日を入力
        Calendar birth = null;
        try {
            while (birth == null) {
                birth = getBirthDay();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (birth != null) {
            // 日数を計算するため、誕生日の年をシステム日付と合わせる
            birth.set(Calendar.YEAR, cal.get(Calendar.YEAR));

            // 今年の誕生日が過ぎている場合は、誕生日を来年に設定
            if (birth.before(cal)) {
                birth.add(Calendar.YEAR, 1);
            }

            // 誕生日までの日数を計算
            long diff = (birth.getTimeInMillis() - cal.getTimeInMillis()) / 1000 / 60 / 60 / 24;
            System.out.println("誕生日まであと" + diff + "日です");
        }
    }

    /**
     *   カレンダーインスタンス取得メソッド。 日数計算を行いやすくするため、時刻は全て00:00:00.000にセットする。
     *
     * @return カレンダーインスタンス
     */
    private static Calendar createCalendar() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * 誕生日取得メソッド。
     *
     * @return 誕生日のカレンダーインスタンス
     * @throws IOException
     */
    private static Calendar getBirthDay() throws IOException {
        System.out.println("誕生日を数字で入力して下さい（例：4月30日なら0430）");
        Calendar birth = null;

        try {
            String mmdd = br.readLine();
            Date b = formatMMDD.parse(mmdd);
            birth = createCalendar();
            birth.setTime(b);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return birth;
    }


}
