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


如果需要自定义什么方法的话 可以修改默认的适配器。
