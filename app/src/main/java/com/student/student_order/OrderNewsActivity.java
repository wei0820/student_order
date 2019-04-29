package com.student.student_order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import iammert.com.expandablelib.ExpandCollapseListener;
import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;

public class OrderNewsActivity extends AppCompatActivity {
    private static final String TAG = "OrderNewsActivity";
    String[] parents = new String[]{"三明治",
            "漢堡","蛋餅","河粉","點心","吐司類","中式類","套餐類","飲料類","鐵板類"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        ExpandableLayout sectionLinearLayout = (ExpandableLayout) findViewById(R.id.el);

        sectionLinearLayout.setRenderer(new ExpandableLayout.Renderer<FruitCategory, Fruit>() {
            @Override
            public void renderParent(View view, FruitCategory model, boolean isExpanded, int parentPosition) {
                ((TextView) view.findViewById(R.id.tvParent)).setText(model.name);
                Log.d(TAG, "renderParent: "+model.name);
                if (isExpanded){
                    view.findViewById(R.id.arrow).setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);

                }else {
                    view.findViewById(R.id.arrow).setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);

                }
            }

            @Override
            public void renderChild(View view, Fruit model, int parentPosition, int childPosition) {
                Log.d(TAG, "renderChild: "+model.name);

                ((TextView) view.findViewById(R.id.tvChild)).setText(model.name);

            }
        });

        sectionLinearLayout.setExpandListener(new ExpandCollapseListener.ExpandListener<FruitCategory>() {
            @Override
            public void onExpanded(int parentIndex, FruitCategory parent, View view) {
                view.findViewById(R.id.arrow).setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
            }
        });

        sectionLinearLayout.setCollapseListener(new ExpandCollapseListener.CollapseListener<FruitCategory>() {
            @Override
            public void onCollapsed(int parentIndex, FruitCategory parent, View view) {
                view.findViewById(R.id.arrow).setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);

            }
        });

        sectionLinearLayout.addSection(getSection(parents[0],new String[]{"煎蛋 20元","起司蛋 25元","火腿蛋 25元","豬肉蛋 30元","培根蛋 25元","肉鬆蛋 25元","雞肉蛋 30元","薯餅蛋 30元","鮪魚蛋 30元","牛肉蛋 35元","鱈魚 35元","黑胡椒豬排 35元","煙燻雞肉 35元","無骨雞排 35元","起司豬排 45元","卡啦雞腿 45元","椒麻雞腿 45元","泰式雞腿 45元","熱狗薯餅蛋 40元","豬肉總匯 40元","薯餅總匯 45元","牛肉總匯 45元","豬肉總匯 50元","卡啦雞總匯 55元"}));
        sectionLinearLayout.addSection(getSection(parents[1],new String[]{"煎蛋 25元","起司蛋 30元","火腿蛋 30元","豬肉蛋 30元","培根蛋 30元","肉鬆蛋 30元","雞肉蛋 30元","薯餅蛋 30元","鮪魚蛋 30元","牛肉蛋 35元","鱈魚 35元","黑胡椒豬排 35元","煙燻雞肉 35元","無骨雞排 35元","起司豬排 45元","卡啦雞腿 45元","椒麻雞腿 45元","泰式雞腿 45元"}));
        sectionLinearLayout.addSection(getSection(parents[2],new String[]{"原味 20元","玉米 25元","乳酪絲 25元","起司 25元","火腿 25元","肉鬆 25元","培根 25元","蔬菜 25元","熱狗 25元",
                "鮪魚 30元","豬肉 30元","雞肉 30元","薯餅 30元","豬排 35元","牛肉 35元","燻雞 35元","德國香腸 35元","卡啦 45元",}));
        sectionLinearLayout.addSection(getSection(parents[3],new String[]{"原味 20元","玉米 30元","乳酪絲 30元","起司 30元","火腿 30元","肉鬆 30元","培根 30元","蔬菜 30元","熱狗 30元",
                "鮪魚 35元","豬肉 35元","雞肉 35元","薯餅 35元","豬排 40元","牛肉 40元","燻雞 40元","德國香腸 40元","卡啦 45元",}));
        sectionLinearLayout.addSection(getSection(parents[4],new String[]{"薯餅 15元","薯餅塔 30元","小熱狗 20元","大熱狗 30元","小雞塊 20元","大雞塊 30元","薯條 25元","卡啦 30元","椒麻 30元"}));
        sectionLinearLayout.addSection(getSection(parents[5],new String[]{"花生 薄片 15元","花生 厚片 20元","草莓 薄片 15元","草莓 厚片 20元",
                "巧克力 薄片 15元","巧克力 厚片 20元","椰香 薄片 15元","椰香 厚片 20元",
                "香蒜 薄片 15元","香蒜 厚片 20元","煙燻起司 薄片 15元","煙燻起司 厚片 20元",
                "綠茶 薄片 15元","綠茶 厚片 20元","奶油 薄片 15元","奶油 厚片 20元",
                "藍莓 薄片 15元","藍莓 厚片 20元"}));
        sectionLinearLayout.addSection(getSection(parents[6],new String[]{"蘿蔔糕 25元","煎餃 25 元","鍋貼 25 元","蔥抓餅 25元","翡翠抓餅 25元"}));
        sectionLinearLayout.addSection(getSection(parents[7],new String[]{"蘑菇豬排 65元","黑胡椒牛肉 65 元","咖喱雞肉 65元","義大利肉醬 德國香腸 75元"}));
        sectionLinearLayout.addSection(getSection(parents[8],new String[]{"豆漿 大 15元","豆漿 小 10元","豆紅茶 大 15元","豆紅茶 小 10元"
                ,"紅茶 大 20元","紅茶 中 15元","紅茶 小 10元","奶茶 大 20元","奶茶 中 15元","奶茶 小 10元","檸檬紅茶 大 20元","檸檬紅茶 小 15元",
                "蘋果紅茶 大 20元","蘋果紅茶 小 15元","柳橙汁 大 20元","柳橙汁 中 15元","柳橙汁 小 10元",
                "檸檬汁 大 20元","檸檬汁 中 15元","檸檬汁 小 10元",
                "蘋果汁 大 20元","蘋果汁 中 15元","蘋果汁 小 10元",
                "可樂 20元","雪碧 20元","鮮奶茶 25元","鮮奶茶 20元","玉米濃湯 30元","玉米濃湯 20元"}));
        sectionLinearLayout.addSection(getSection(parents[9],new String[]{"蘑菇 35元","黑胡椒 35元","肉燥 35元","咖哩 40元","韓國肉醬  40元","義大利肉醬 40元","奶油白醬 45 元","三杯雞 45元","宮保雞丁 45元","川味麻婆 45元"}));


    }

    public Section<FruitCategory, Fruit> getSection(String name ,String[] s) {
        Section<FruitCategory, Fruit> section = new Section<>();
        FruitCategory fruitCategory = new FruitCategory(name);

        section.parent = fruitCategory;
        for (String s1 : s) {
            section.children.add(new Fruit(s1));

        }
//        section.children.add(fruit1);
//        section.children.add(fruit2);
//        section.children.add(fruit3);
//        section.children.add(fruit4);
//        section.children.add(fruit5);
//        section.children.add(fruit6);
        section.expanded = false;
        return section;
    }
}