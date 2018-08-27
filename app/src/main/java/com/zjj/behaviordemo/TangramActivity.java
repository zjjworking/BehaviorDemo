package com.zjj.behaviordemo;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.tmall.wireless.tangram.TangramBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zjj on 2018/7/31.
 */
public class TangramActivity extends AppCompatActivity implements TangramAdapter.MyItemClickListener {

    private RecyclerView recyclerView;
    TangramAdapter Adapter_linearLayout,Adapter_GridLayout,Adapter_FixLayout,Adapter_ScrollFixLayout
            ,Adapter_FloatLayout,Adapter_ColumnLayout,Adapter_SingleLayout,Adapter_onePlusNLayout,
            Adapter_StickyLayout,Adapter_StaggeredGridLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tangram);

        TangramBuilder.InnerBuilder builder = TangramBuilder.newInnerBuilder(this);
        /**
         * 步骤1：创建RecyclerView & VirtualLayoutManager 对象并进行绑定
         * */
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //创建VirtualLayoutManager对象
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        //绑定
        // 创建VirtualLayoutManager对象
        // 同时内部会创建一个LayoutHelperFinder对象，用来后续的LayoutHelper查找
        recyclerView.setLayoutManager(layoutManager);
        /**
         * 步骤2：设置组件复用回收池
         * */
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        viewPool.setMaxRecycledViews(0,10);
        recyclerView.setRecycledViewPool(viewPool);

        /**
         * 步骤3:设置需要存放的数据
         * */
        ArrayList list = new  ArrayList();
        for (int i= 0;i < 100;i++){
            list.add(new Object());
        }

        /**
         * 步骤4:根据数据列表,创建对应的LayoutHelper
         * */

        /**
         设置线性布局
         */
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        // 创建对应的LayoutHelper对象

        // 公共属性
        linearLayoutHelper.setItemCount(4);// 设置布局里Item个数
        linearLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        linearLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        // linearLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        linearLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // linearLayoutHelper特有属性
        linearLayoutHelper.setDividerHeight(10);
        // 设置间隔高度
        // 设置的间隔会与RecyclerView的addItemDecoration（）添加的间隔叠加.

        linearLayoutHelper.setMarginBottom(100);

        Adapter_linearLayout = new TangramAdapter(this,list,linearLayoutHelper,4){
            @Override
            public void onBindViewHolder(@NonNull TangramViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0){
                    holder.textView.setText("linear");
                    //为了展示效果,将布局里不同位置的Item进行背景颜色设置
                    if (position < 2) {
                        holder.itemView.setBackgroundColor(0x66cc0000 + (position - 6) * 128);
                    } else if (position % 2 == 0) {
                        holder.itemView.setBackgroundColor(0xaa22ff22);
                    } else {
                        holder.itemView.setBackgroundColor(0xccff22ff);
                    }
                }
            }
        };
        Adapter_linearLayout.setOnItemClickListener(this);

        /**
         * 设置吸边布局
         */
        StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();

        //公共属性
        stickyLayoutHelper.setItemCount(3);// 设置布局里Item个数
        stickyLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        stickyLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        stickyLayoutHelper.setBgColor(Color.YELLOW);// 设置背景颜色
        stickyLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // 特有属性
        stickyLayoutHelper.setStickyStart(true);
        // true = 组件吸在顶部
        // false = 组件吸在底部

        stickyLayoutHelper.setOffset(100);// 设置吸边位置的偏移量

        Adapter_StickyLayout = new TangramAdapter(this,list,stickyLayoutHelper,1){
            @Override
            public void onBindViewHolder(@NonNull TangramViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0){
                    holder.textView.setText("Stick");
                    holder.itemView.setBackgroundColor(0xccff66ff);
                }
            }
        };
        Adapter_StickyLayout.setOnItemClickListener(this);

        /**
         设置可选固定布局
         */

        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_RIGHT,0,0);
        // 参数说明:
        // 参数1:设置吸边时的基准位置(alignType) - 有四个取值:TOP_LEFT(默认), TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
        // 参数2:基准位置的偏移量x
        // 参数3:基准位置的偏移量y

        // 公共属性
        scrollFixLayoutHelper.setItemCount(1);// 设置布局里Item个数

        scrollFixLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        scrollFixLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        scrollFixLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        scrollFixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // fixLayoutHelper特有属性
        scrollFixLayoutHelper.setAlignType(FixLayoutHelper.BOTTOM_RIGHT);// 设置吸边时的基准位置(alignType)
        scrollFixLayoutHelper.setX(30);// 设置基准位置的横向偏移量X
        scrollFixLayoutHelper.setY(50);// 设置基准位置的纵向偏移量Y
        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_LEAVE);// 设置Item的显示模式

        Adapter_ScrollFixLayout  = new TangramAdapter(this,list, scrollFixLayoutHelper,1) {
            // 设置需要展示的数据总数,此处设置是1
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为scrollFix
            @Override
            public void onBindViewHolder(TangramViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.textView.setText("scrollFix");
                }
            }
        };
        Adapter_ScrollFixLayout.setOnItemClickListener(this);
        /**
         设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        // 在构造函数设置每行的网格个数

        // 公共属性
        gridLayoutHelper.setItemCount(36);// 设置布局里Item个数
        gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //gridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // gridLayoutHelper特有属性（下面会详细说明）
        gridLayoutHelper.setWeights(new float[]{40, 30, 30});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper.setSpanCount(3);// 设置每行多少个网格
        // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position > 7 ) {
                    return 3;
                    // 第7个位置后,每个Item占3个网格
                }else {
                    return 2;
                    // 第7个位置前,每个Item占2个网格
                }
            }
        });
        Adapter_GridLayout = new TangramAdapter(this,list,gridLayoutHelper,36){
            @Override
            public void onBindViewHolder(@NonNull TangramViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0){
                    holder.textView.setText("GridLayout");
                }
                // 为了展示效果,将布局里不同位置的Item进行背景颜色设置
                if (position < 2) {
                    holder.itemView.setBackgroundColor(0x66cc0000 + (position - 6) * 128);
                } else if (position % 2 == 0) {
                    holder.itemView.setBackgroundColor(0xaa22ff22);
                } else {
                    holder.itemView.setBackgroundColor(0xccff22ff);
                }
            }
        };
        Adapter_GridLayout.setOnItemClickListener(this);



        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        // 2. 将上述创建的Adapter对象放入到DelegateAdapter.Adapter列表里
        adapters.add(Adapter_linearLayout) ;
        adapters.add(Adapter_StickyLayout) ;
        adapters.add(Adapter_ScrollFixLayout) ;
        adapters.add(Adapter_GridLayout) ;
//        adapters.add(Adapter_FixLayout) ;
//        adapters.add(Adapter_FloatLayout) ;
//        adapters.add(Adapter_ColumnLayout) ;
//        adapters.add(Adapter_SingleLayout) ;
//        adapters.add(Adapter_onePlusNLayout) ;
//        adapters.add(Adapter_StaggeredGridLayout) ;

        // 3. 创建DelegateAdapter对象 & 将layoutManager绑定到DelegateAdapter
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);

        // 4. 将DelegateAdapter.Adapter列表绑定到DelegateAdapter
        delegateAdapter.setAdapters(adapters);

        // 5. 将delegateAdapter绑定到recyclerView
        recyclerView.setAdapter(delegateAdapter);

        /**
         *步骤6:Item之间的间隔
         **/
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(5, 5, 5, 5);
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this,"点击了第"+position + "行",Toast.LENGTH_SHORT).show();
    }
}
