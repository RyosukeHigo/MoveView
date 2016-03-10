package test.moveview;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class FrameMoveActivity extends Activity implements OnTouchListener {
    private View move_view1;
    private View move_view2;

    int currentX;   //Viewの左辺座標：X軸
    int currentY;   //Viewの上辺座標：Y軸
    int offsetX;    //画面タッチ位置の座標：X軸
    int offsetY;    //画面タッチ位置の座標：Y軸

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        move_view1 = findViewById(R.id.view1);
        move_view2 = findViewById(R.id.view2);

        //リスナーの設定
        move_view1.setOnTouchListener(this);
        move_view2.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int diffX = offsetX - x;
                int diffY = offsetY - y;

                currentX -= diffX;
                currentY -= diffY;
                view.layout(currentX, currentY, currentX + view.getWidth(), currentY + view.getHeight());
                offsetX = x;
                offsetY = y;
                break;
            case MotionEvent.ACTION_DOWN:
                //view.bringToFront();
                currentX = view.getLeft();
                currentY = view.getTop();
                offsetX = x;
                offsetY = y;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }
}