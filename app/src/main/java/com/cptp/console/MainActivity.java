//package com.cptp.console;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.cptp.console.keystone.Keystone;
//import com.cptp.console.keystone.KeystoneVertex;
//
//public class MainActivity extends Activity {
//
//    private Button mButtonTopExpand;
//    private Button mButtonTopShrink;
//    private Button mButtonBottomExpand;
//    private Button mButtonBottomShrink;
//    private Button mButtonLeftExpand;
//    private Button mButtonLeftShrink;
//    private Button mButtonRightExpand;
//    private Button mButtonRightShrink;
//
//    private TextView mTextViewLT;
//    private TextView mTextViewRT;
//    private TextView mTextViewLB;
//    private TextView mTextViewRB;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mTextViewLT = (TextView) findViewById(R.id.textView_lt);
//        mTextViewRT = (TextView) findViewById(R.id.textView_rt);
//        mTextViewLB = (TextView) findViewById(R.id.textView_lb);
//        mTextViewRB = (TextView) findViewById(R.id.textView_rb);
//
//        mButtonTopExpand = (Button) findViewById(R.id.button_top_expand);
//        mButtonTopShrink = (Button) findViewById(R.id.button_top_shrink);
//        mButtonBottomExpand = (Button) findViewById(R.id.button_bottom_expand);
//        mButtonBottomShrink = (Button) findViewById(R.id.button_bottom_shrink);
//        mButtonLeftExpand = (Button) findViewById(R.id.button_left_expand);
//        mButtonLeftShrink = (Button) findViewById(R.id.button_left_shrink);
//        mButtonRightExpand = (Button) findViewById(R.id.button_right_expand);
//        mButtonRightShrink = (Button) findViewById(R.id.button_right_shrink);
//
//        mButtonTopExpand.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Keystone.expandTop();
//                updateVertexShow();
//            }
//        });
//        mButtonTopShrink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Keystone.shrinkTop();
//                updateVertexShow();
//            }
//        });
//        mButtonBottomExpand.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Keystone.expandBottom();
//                updateVertexShow();
//            }
//        });
//        mButtonBottomShrink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Keystone.shrinkBottom();
//                updateVertexShow();
//            }
//        });
//        mButtonLeftExpand.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Keystone.expandLeft();
//                updateVertexShow();
//            }
//        });
//        mButtonLeftShrink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Keystone.shrinkLeft();
//                updateVertexShow();
//            }
//        });
//        mButtonRightExpand.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Keystone.expandRight();
//                updateVertexShow();
//            }
//        });
//        mButtonRightShrink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Keystone.shrinkRight();
//                updateVertexShow();
//            }
//        });
//        updateVertexShow();
//    }
//
//    void updateVertexShow() {
//        KeystoneVertex kv = Keystone.getVertex();
//        mTextViewLT.setText(kv.vTopLeft.toString());
//        mTextViewRT.setText(kv.vTopRight.toString());
//        mTextViewLB.setText(kv.vBottomLeft.toString());
//        mTextViewRB.setText(kv.vBottomRight.toString());
//    }
//}
//
//
