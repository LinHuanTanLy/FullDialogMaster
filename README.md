# FullDialogMaster


这是一个使用DialogFragment 做成的dialog样式


![效果图片][1]


  [1]: http://image.talkmoney.cn/TIM%E5%9B%BE%E7%89%8720170810023650.png "TIM图片20170810023650.png"




其中没有使用ViewPager，而是用了RecyclerView做成了Viewpager的样式



具体使用：

```java

package com.ly.fulldialog_master;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialog(View view) {
        GiftDialog.showDialog(this);
    }
}



```
新增加activity交互的方式：

1. activity的不用管，就那么几行
2. fragment的有2种方法：
  ```java
    /**
     * @param activity
     * @param type     1 callback   2 startActivityForResult
     */
    public static void toFragmentFather(Activity activity, String type) {
        Intent intent = new Intent(activity, FragmentFatherActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

  ```

    对应方法是：

    ```java
      /**
         * show the fragment with callback
         *
         * @param view
         */
        public void toCallBack(View view) {
            FragmentFatherActivity.toFragmentFather(MainActivity.this, "1");
        }

        /**
         * show the fragment with startActivityForResult
         *
         * @param view
         */
        public void toForResult(View view) {
            FragmentFatherActivity.toFragmentFather(MainActivity.this, "2");
        }
    ```
    type是1的话就是使用自定义interface来做交互
    type是2的话就是通过startActivityFragment来做交互

    对应的类是**GiftDialogInFragment**,
    核心代码有注释：
    ```java
                   tvGiftSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (type.equals("2")) {
                            // startActivityForResult config
                            Intent intent = new Intent();
                            intent.putExtra("money", "礼物的id");
                            //获得目标Fragment,并将数据通过onActivityResult放入到intent中进行传值
                            getTargetFragment().onActivityResult(1, Activity.RESULT_OK, intent);
                            // end of startActivityForResult config
                        } else if (type.equals("1")) {
                            // callback config
                            if (onGiftItemClickListener != null) {
                                onGiftItemClickListener.onGiftSelectedListener("礼物的id");
                            }
                        }
                        // end of callback config
                    }

    ```

    作者QQ:137544897  如看不懂可以联系。
    #此致
如果需要自定义什么方法的话 可以修改默认的适配器。
