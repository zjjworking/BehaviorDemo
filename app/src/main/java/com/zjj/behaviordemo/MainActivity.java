package com.zjj.behaviordemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    QQHeaderScrollView qqHeaderScrollView;
    private ImageView iv;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqheader);

        qqHeaderScrollView = findViewById(R.id.lv);
        textView = findViewById(R.id.textView);
        textView.setText(Html.fromHtml("【杭州市】已签收,签收人是西溪水岸12撞.申行天下义当先,江湖好评是良缘.记得给申通小件员五星好评哦！，小件员电话：15088630820/<a href= \"tel:0571-85225800\\\">0571-85225800</a > ，感谢使用申通快递，期待再次为您服务"));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,
                new String[]{
                        "星期一 	和马云洽谈",
                        "星期二	约见李彦宏",
                        "星期三 	约见乔布斯",
                        "星期四 	和Lance钓鱼",
                        "星期五 	和Jett洽谈",
                        "星期六 	和Jason洽谈",
                        "星期日 	和MZ洽谈",
                        "星期一 	和马云洽谈",
                        "星期二	约见李彦宏",
                        "星期三 	约见乔布斯",
                        "星期四 	和Ricky钓鱼",
                        "星期五 	和David洽谈",
                        "星期六 	和Jason洽谈",
                        "星期日 	和MZ洽谈",
                        "……"
                });

        View header = View.inflate(this,R.layout.listview_header,null);
        iv = (ImageView) header.findViewById(R.id.layout_header_image);
        qqHeaderScrollView.setZoomImageView(iv);
        qqHeaderScrollView.addHeaderView(header);
        qqHeaderScrollView.setAdapter(adapter);
        qqHeaderScrollView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this,TangramActivity.class));
            }

        });
//        final Button depentent = (Button) findViewById(R.id.depentent);
//        depentent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ViewCompat.offsetTopAndBottom(v, 10);
//                ViewCompat.offsetTopAndBottom(v,10);
//            }
//        });
//        final TextView swipeView = (TextView)findViewById(R.id.swip);
//        final SwipeDismissBehavior swipe
//                = new SwipeDismissBehavior();
//
//        swipe.setSwipeDirection(
//                SwipeDismissBehavior.SWIPE_DIRECTION_ANY);
//
//        swipe.setListener(
//                new SwipeDismissBehavior.OnDismissListener() {
//                    @Override public void onDismiss(View view) {
//                    }
//
//                    @Override
//                    public void onDragStateChanged(int state) {}
//                });
//
//        CoordinatorLayout.LayoutParams coordinatorParams =
//                (CoordinatorLayout.LayoutParams) swipeView.getLayoutParams();
//
//        coordinatorParams.setBehavior(swipe);
    }

}
