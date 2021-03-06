package com.test.iview.dayin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.EditText;

import com.test.iview.dayin.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SingleTouchEditText extends android.support.v7.widget.AppCompatEditText {

    /**
     * 图片的最大缩放比例
     */
    public static final float MAX_SCALE = 10.0f;

    /**
     * 图片的最小缩放比例
     */
    public static final float MIN_SCALE = 0.3f;

    /**
     * 控制缩放，旋转图标所在四个点得位置
     */
    public static final int LEFT_TOP = 0;
    public static final int RIGHT_TOP = 1;
    public static final int RIGHT_BOTTOM = 2;
    public static final int LEFT_BOTTOM = 3;

    /**
     * 一些默认的常量
     */
    public static final int DEFAULT_FRAME_PADDING = 4;
    public static final int DEFAULT_FRAME_WIDTH = 2;
    public static final int DEFAULT_FRAME_COLOR = Color.WHITE;
    public static final float DEFAULT_SCALE = 1.0f;
    public static final float DEFAULT_DEGREE = 0;
    public static final int DEFAULT_CONTROL_LOCATION = RIGHT_TOP;
    public static final int DEFAULT_DELETE_LOCATION = LEFT_TOP;
    public static final boolean DEFAULT_EDITABLE = true;


    /**
     * 用于旋转缩放的Bitmap
     */
//    private Bitmap mBitmap;

    /**
     * SingleTouchView的中心点坐标，相对于其父类布局而言的
     */
    private PointF mCenterPoint = new PointF();

    private PointF mCurCenterPoint;

    /**
     * View的宽度和高度，随着图片的旋转而变化(不包括控制旋转，缩放图片的宽高)
     */
    private int mViewWidth, mViewHeight;

    /**
     * 图片的旋转角度
     */
    private float mDegree = DEFAULT_DEGREE;

    /**
     * 图片的缩放比例
     */
    private float mScale = DEFAULT_SCALE;

    /**
     * 用于缩放，旋转，平移的矩阵
     */
    private Matrix matrix = new Matrix();

    /**
     * SingleTouchView距离父类布局的左间距
     */
    private int mViewPaddingLeft;

    /**
     * SingleTouchView距离父类布局的上间距
     */
    private int mViewPaddingTop;

    /**
     * 图片四个点坐标
     */
    private Point mLTPoint;
    private Point mRTPoint;
    private Point mRBPoint;
    private Point mLBPoint;

    /**
     * 用于缩放，旋转的控制点的坐标
     */
    private Point mControlPoint = new Point();
    /**
     * 用于缩放，旋转的控制点的坐标
     */
    private Point mDeletePoint = new Point();

    /**
     * 用于缩放，旋转的图标
     */
    private Drawable controlDrawable;
    /**
     * 用于点击移除的图标
     */
    private Drawable deleteDrawable;

    /**
     * 缩放，旋转图标的宽和高
     */
    private int mDrawableWidth, mDrawableHeight;
    private int mDeleteDrawableWidth, mDeleteDrawableHeight;

    /**
     * 画外围框的Path
     */
    private Path mPath = new Path();

    /**
     * 画外围框的画笔
     */
    private Paint mPaint ;

    /**
     * 初始状态
     */
    public static final int STATUS_INIT = 0;

    /**
     * 拖动状态
     */
    public static final int STATUS_DRAG = 1;

    /**
     * 旋转或者放大状态
     */
    public static final int STATUS_ROTATE_ZOOM = 2;
    /**
     * 移除状态
     */
    public static final int STATUS_DELETE = 3;

    /**
     * 当前所处的状态
     */
    private int mStatus = STATUS_INIT;

    /**
     * 外边框与图片之间的间距, 单位是dip
     */
    private int framePadding = DEFAULT_FRAME_PADDING;

    /**
     * 外边框颜色
     */
    private int frameColor = DEFAULT_FRAME_COLOR;

    /**
     * 外边框线条粗细, 单位是 dip
     */
    private int frameWidth = DEFAULT_FRAME_WIDTH;

    /**
     * 是否处于可以缩放，平移，旋转状态
     */
    private boolean isEditable = DEFAULT_EDITABLE;

    private DisplayMetrics metrics;


    private PointF mPreMovePointF = new PointF();
    private PointF mCurMovePointF = new PointF();

    /**
     * 图片在旋转时x方向的偏移量
     */
    private int offsetX;
    /**
     * 图片在旋转时y方向的偏移量
     */
    private int offsetY;

    /**
     * 控制图标所在的位置（比如左上，右上，左下，右下）
     */
    private int controlLocation = DEFAULT_CONTROL_LOCATION;
    /**
     * 控制移除图片所在的位置（比如左上，右上，左下，右下）
     */
    private int deleteLocation = DEFAULT_DELETE_LOCATION;

    private SingleTouchView.OnDeleteListener onDeleteListener;

    private Context context;



    public SingleTouchEditText(Context context) {
        super(context);
        this.context = context;
        init();
        Log.e("SingleTouchView","构造1");
    }

    public SingleTouchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttributes(attrs);
        this.context = context;
        init();
        Log.e("SingleTouchView","构造2");
    }

    public SingleTouchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(attrs);
        this.context = context;
        init();
        Log.e("SingleTouchView","构造3");
    }

    /**
     * 获取自定义属性
     * @param attrs
     */
    private void obtainStyledAttributes(AttributeSet attrs){
        Log.e("SingleTouchView","obtainStyledAttributes");
        metrics = getContext().getResources().getDisplayMetrics();
        framePadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_FRAME_PADDING, metrics);
        frameWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_FRAME_WIDTH, metrics);

        TypedArray mTypedArray = getContext().obtainStyledAttributes(attrs,
                R.styleable.SingleTouchView);

        framePadding = mTypedArray.getDimensionPixelSize(R.styleable.SingleTouchView_framePadding, framePadding);
        frameWidth = mTypedArray.getDimensionPixelSize(R.styleable.SingleTouchView_frameWidth, frameWidth);
        frameColor = mTypedArray.getColor(R.styleable.SingleTouchView_frameColor, DEFAULT_FRAME_COLOR);
        mScale = mTypedArray.getFloat(R.styleable.SingleTouchView_scale, DEFAULT_SCALE);
        mDegree = mTypedArray.getFloat(R.styleable.SingleTouchView_degree, DEFAULT_DEGREE);
        controlDrawable = mTypedArray.getDrawable(R.styleable.SingleTouchView_controlDrawable);
        deleteDrawable=mTypedArray.getDrawable(R.styleable.SingleTouchView_deleteDrawable);
        controlLocation = mTypedArray.getInt(R.styleable.SingleTouchView_controlLocation, DEFAULT_CONTROL_LOCATION);
        deleteLocation=mTypedArray.getInt(R.styleable.SingleTouchView_deleteLocation,DEFAULT_DELETE_LOCATION);
        isEditable = mTypedArray.getBoolean(R.styleable.SingleTouchView_editable, DEFAULT_EDITABLE);

        mTypedArray.recycle();

    }


    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(frameColor);
        mPaint.setStrokeWidth(frameWidth);
        mPaint.setStyle(Paint.Style.STROKE);

//		mBitmap = Bitmap.createBitmap(500,400, Bitmap.Config.RGB_565);
        if(controlDrawable == null){
            controlDrawable = getContext().getResources().getDrawable(R.drawable.ic_move);
        }
        if(deleteDrawable==null){
            deleteDrawable=getContext().getResources().getDrawable(R.drawable.ic_delete);
        }

        mDrawableWidth = controlDrawable.getIntrinsicWidth();
        mDrawableHeight = controlDrawable.getIntrinsicHeight();

        if(deleteDrawable!=null) {
            mDeleteDrawableHeight = deleteDrawable.getIntrinsicHeight();
            mDeleteDrawableWidth = deleteDrawable.getIntrinsicWidth();
        }
        Log.e("SingleTouchView","init");
        transformDraw();
    }


    private int width = 500;
    private int height = 400;
    /**
     * 设置Matrix, 强制刷新
     */
    private void transformDraw(){
        Log.e("SingleTouchView","transformDraw");

        int bitmapWidth = (int)(width * mScale);
        int bitmapHeight = (int)(height* mScale);
        Log.e("bitmapWidth",bitmapWidth + "");
        Log.e("bitmapHeight",bitmapHeight + "");
        computeRect(-framePadding, -framePadding, bitmapWidth + framePadding, bitmapHeight + framePadding, mDegree);

        //设置缩放比例
        matrix.setScale(mScale, mScale);
        //绕着图片中心进行旋转
        matrix.postRotate(mDegree % 360, bitmapWidth/2, bitmapHeight/2);
        //设置画该图片的起始点
        matrix.postTranslate(offsetX + mDrawableWidth/2, offsetY + mDrawableHeight/2);
        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("SingleTouchView","onMeasure");
        //获取SingleTouchView所在父布局的中心点
        ViewGroup mViewGroup = (ViewGroup) getParent();
        if(null != mViewGroup){
            int parentWidth = mViewGroup.getWidth();
            int parentHeight = mViewGroup.getHeight();
            if (mCurCenterPoint == null){
                mCenterPoint.set(parentWidth/2, parentHeight/2);
                mCurCenterPoint = mCenterPoint;
            }else{
                mCenterPoint = mCurCenterPoint;
            }


        }
    }


    /**
     * 调整View的大小，位置
     */
    private void adjustLayout(){
        Log.e("SingleTouchView","adjustLayout");
        int actualWidth = mViewWidth + mDrawableWidth + mDeleteDrawableWidth;
        int actualHeight = mViewHeight + mDrawableHeight + mDeleteDrawableHeight;
        int newPaddingLeft = (int) (mCenterPoint.x - actualWidth /2);
        int newPaddingTop = (int) (mCenterPoint.y - actualHeight/2);

        if(mViewPaddingLeft != newPaddingLeft || mViewPaddingTop != newPaddingTop){
            mViewPaddingLeft = newPaddingLeft;
            mViewPaddingTop = newPaddingTop;
        }
        layout(newPaddingLeft, newPaddingTop, newPaddingLeft + actualWidth - 30, newPaddingTop + actualHeight - 35);
        mCurCenterPoint = mCenterPoint;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //每次draw之前调整View的位置和大小
        Log.e("SingleTouchView","onDraw");
        adjustLayout();

        super.onDraw(canvas);

//        if(mBitmap == null) return;
//
//        canvas.drawBitmap(mBitmap, matrix, null);
        //处于可编辑状态才画边框和控制图标
        if(isEditable){
            mPath.reset();
            Log.e("SingleTouchView:::",mLTPoint.x + "");
            mPath.moveTo(mLTPoint.x, mLTPoint.y);
            mPath.lineTo(mRTPoint.x, mRTPoint.y);
            mPath.lineTo(mRBPoint.x, mRBPoint.y);
            mPath.lineTo(mLBPoint.x, mLBPoint.y);
            mPath.lineTo(mLTPoint.x, mLTPoint.y);
            mPath.lineTo(mRTPoint.x, mRTPoint.y);
            canvas.drawPath(mPath, mPaint);
            //画旋转, 缩放图标


            controlDrawable.setBounds(mControlPoint.x - mDrawableWidth / 2,
                    mControlPoint.y - mDrawableHeight / 2, mControlPoint.x + mDrawableWidth
                            / 2, mControlPoint.y + mDrawableHeight / 2);
            controlDrawable.draw(canvas);


            if(deleteDrawable!=null) {
                deleteDrawable.setBounds(mDeletePoint.x - mDeleteDrawableWidth / 2, mDeletePoint.y - mDeleteDrawableHeight / 2,
                        mDeletePoint.x + mDeleteDrawableWidth / 2, mDeletePoint.y + mDeleteDrawableHeight / 2);
                deleteDrawable.draw(canvas);
            }
        }
    }


    //记录当前点
    Point curPoint = new Point();
    List<Point> listPoint = new ArrayList<>();

    public boolean onTouchEvent(MotionEvent event) {
        if(!isEditable){
            return super.onTouchEvent(event);
        }
        switch (event.getAction() ) {
            case MotionEvent.ACTION_DOWN:
                mPreMovePointF.set(event.getX() + mViewPaddingLeft, event.getY() + mViewPaddingTop);
                mStatus = JudgeStatus(event.getX(), event.getY());

                //判断点击点是否在bitmap范围内
                curPoint.set((int)event.getX(), (int)event.getY());
                listPoint.add(mLTPoint);
                listPoint.add(mLBPoint);
                listPoint.add(mRTPoint);
                listPoint.add(mRBPoint);

                if (!isInTriangle(mLTPoint,mLBPoint,mRTPoint,curPoint) &&
                        !isInTriangle(mLBPoint,mRBPoint,mRTPoint,curPoint) &&
                        STATUS_DRAG == mStatus){
                    listPoint.clear();
                    return false;
                }
                listPoint.clear();
                break;
            case MotionEvent.ACTION_UP:
                if(mStatus==STATUS_DELETE){
                    //删除
                    if(onDeleteListener!=null){
                        onDeleteListener.onDelete();
                    }
                    ViewGroup mViewGroup = (ViewGroup) getParent();
                    mViewGroup.removeView(this);
                }
                mStatus = STATUS_INIT;
                break;
            case MotionEvent.ACTION_MOVE:
                mCurMovePointF.set(event.getX() + mViewPaddingLeft, event.getY() + mViewPaddingTop);

                if (mStatus == STATUS_ROTATE_ZOOM) {
                    float scale = 1f;

                    int halfBitmapWidth = width / 2;
                    int halfBitmapHeight = height /2 ;

                    //图片某个点到图片中心的距离
                    float bitmapToCenterDistance = (float) Math.sqrt(halfBitmapWidth * halfBitmapWidth + halfBitmapHeight * halfBitmapHeight);

                    //移动的点到图片中心的距离
                    float moveToCenterDistance = distance4PointF(mCenterPoint, mCurMovePointF);

                    //计算缩放比例
                    scale = moveToCenterDistance / bitmapToCenterDistance;


                    //缩放比例的界限判断
                    if (scale <= MIN_SCALE) {
                        scale = MIN_SCALE;
                    } else if (scale >= MAX_SCALE) {
                        scale = MAX_SCALE;
                    }


                    // 角度
                    double a = distance4PointF(mCenterPoint, mPreMovePointF);
                    double b = distance4PointF(mPreMovePointF, mCurMovePointF);
                    double c = distance4PointF(mCenterPoint, mCurMovePointF);

                    double cosb = (a * a + c * c - b * b) / (2 * a * c);

                    if (cosb >= 1) {
                        cosb = 1f;
                    }

                    double radian = Math.acos(cosb);
                    float newDegree = (float) radianToDegree(radian);

                    //center -> proMove的向量， 我们使用PointF来实现
                    PointF centerToProMove = new PointF((mPreMovePointF.x - mCenterPoint.x), (mPreMovePointF.y - mCenterPoint.y));

                    //center -> curMove 的向量
                    PointF centerToCurMove = new PointF((mCurMovePointF.x - mCenterPoint.x), (mCurMovePointF.y - mCenterPoint.y));

                    //向量叉乘结果, 如果结果为负数， 表示为逆时针， 结果为正数表示顺时针
                    float result = centerToProMove.x * centerToCurMove.y - centerToProMove.y * centerToCurMove.x;

                    if (result < 0) {
                        newDegree = -newDegree;
                    }

                    mDegree = mDegree + newDegree;
                    mScale = scale;

                    transformDraw();
                }else if (mStatus == STATUS_DRAG) {
                    // 修改中心点
                    mCenterPoint.x += mCurMovePointF.x - mPreMovePointF.x;
                    mCenterPoint.y += mCurMovePointF.y - mPreMovePointF.y;

                    adjustLayout();
                }

                mPreMovePointF.set(mCurMovePointF);
                break;
        }
        return true;
    }


    public boolean isInTriangle(Point A, Point B, Point C, Point P) {
        double ABC = triAngleArea(A, B, C);
        double ABp = triAngleArea(A, B, P);
        double ACp = triAngleArea(A, C, P);
        double BCp = triAngleArea(B, C, P);
        if ((int) (ABp + ACp + BCp) == (int) ABC) {// 若面积之和等于原三角形面积，证明点在三角形内,这里做了一个约等于小数点之后没有算（25714.25390625、25714.255859375）
            return true;
        } else {
            return false;
        }
    }

    private double triAngleArea(Point A, Point B, Point C) {// 由三个点计算这三个点组成三角形面积
        double result = Math.abs((A.x * B.y + B.x * C.y
                + C.x * A.y - B.x * A.y - C.x
                * B.y - A.x * C.y) / 2.0D);
        return result;
    }

    /**
     * 获取四个点和View的大小
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @param degree
     */
    private void computeRect(int left, int top, int right, int bottom, float degree){
        Log.e("SingleTouchView","computeRect");
        Point lt = new Point(left, top);
        Point rt = new Point(right, top);
        Point rb = new Point(right, bottom);
        Point lb = new Point(left, bottom);
        Point cp = new Point((left + right) / 2, (top + bottom) / 2);
        mLTPoint = obtainRoationPoint(cp, lt, degree);
        mRTPoint = obtainRoationPoint(cp, rt, degree);
        mRBPoint = obtainRoationPoint(cp, rb, degree);
        mLBPoint = obtainRoationPoint(cp, lb, degree);

        //计算X坐标最大的值和最小的值
        int maxCoordinateX = getMaxValue(mLTPoint.x, mRTPoint.x, mRBPoint.x, mLBPoint.x);
        int minCoordinateX = getMinValue(mLTPoint.x, mRTPoint.x, mRBPoint.x, mLBPoint.x);;

        mViewWidth = maxCoordinateX - minCoordinateX;
        Log.e("mViewWidth111", + mViewWidth + "");

        //计算Y坐标最大的值和最小的值
        int maxCoordinateY = getMaxValue(mLTPoint.y, mRTPoint.y, mRBPoint.y, mLBPoint.y);
        int minCoordinateY = getMinValue(mLTPoint.y, mRTPoint.y, mRBPoint.y, mLBPoint.y);

        mViewHeight = maxCoordinateY - minCoordinateY;
        Log.e("mViewHeight111", + mViewHeight + "");

        //View中心点的坐标
        Point viewCenterPoint = new Point((maxCoordinateX + minCoordinateX) / 2, (maxCoordinateY + minCoordinateY) / 2);

        offsetX = mViewWidth / 2 - viewCenterPoint.x;
        offsetY = mViewHeight / 2 - viewCenterPoint.y;



        int halfDrawableWidth = mDrawableWidth / 2;
        int halfDrawableHeight = mDrawableHeight /2;

        //将Bitmap的四个点的X的坐标移动offsetX + halfDrawableWidth
        mLTPoint.x += (offsetX + halfDrawableWidth);
        mRTPoint.x += (offsetX + halfDrawableWidth);
        mRBPoint.x += (offsetX + halfDrawableWidth);
        mLBPoint.x += (offsetX + halfDrawableWidth);

        //将Bitmap的四个点的Y坐标移动offsetY + halfDrawableHeight
        mLTPoint.y += (offsetY + halfDrawableHeight);
        mRTPoint.y += (offsetY + halfDrawableHeight);
        mRBPoint.y += (offsetY + halfDrawableHeight);
        mLBPoint.y += (offsetY + halfDrawableHeight);

        mControlPoint = LocationToPoint(controlLocation);
        mDeletePoint = LocationToPoint(deleteLocation);
    }


    /**
     * 根据位置判断控制图标处于那个点
     * @return
     */
    private Point LocationToPoint(int location){
        Log.e("SingleTouchView","LocationToPoint");
        switch(location){
            case LEFT_TOP:
                return mLTPoint;
            case RIGHT_TOP:
                return mRTPoint;
            case RIGHT_BOTTOM:
                return mRBPoint;
            case LEFT_BOTTOM:
                return mLBPoint;
        }
        return mLTPoint;
    }


    /**
     * 获取变长参数最大的值
     * @param array
     * @return
     */
    public int getMaxValue(Integer...array){
        List<Integer> list = Arrays.asList(array);
        Collections.sort(list);
        return list.get(list.size() -1);
    }


    /**
     * 获取变长参数最大的值
     * @param array
     * @return
     */
    public int getMinValue(Integer...array){
        List<Integer> list = Arrays.asList(array);
        Collections.sort(list);
        return list.get(0);
    }



    /**
     * 获取旋转某个角度之后的点
     * @param source
     * @param degree
     * @return
     */
    public static Point obtainRoationPoint(Point center, Point source, float degree) {
        Log.e("SingleTouchView","obtainRoationPoint");
        //两者之间的距离
        Point disPoint = new Point();
        disPoint.x = source.x - center.x;
        disPoint.y = source.y - center.y;

        //没旋转之前的弧度
        double originRadian = 0;

        //没旋转之前的角度
        double originDegree = 0;

        //旋转之后的角度
        double resultDegree = 0;

        //旋转之后的弧度
        double resultRadian = 0;

        //经过旋转之后点的坐标
        Point resultPoint = new Point();

        double distance = Math.sqrt(disPoint.x * disPoint.x + disPoint.y * disPoint.y);
        if (disPoint.x == 0 && disPoint.y == 0) {
            return center;
            // 第一象限
        } else if (disPoint.x >= 0 && disPoint.y >= 0) {
            // 计算与x正方向的夹角
            originRadian = Math.asin(disPoint.y / distance);

            // 第二象限
        } else if (disPoint.x < 0 && disPoint.y >= 0) {
            // 计算与x正方向的夹角
            originRadian = Math.asin(Math.abs(disPoint.x) / distance);
            originRadian = originRadian + Math.PI / 2;

            // 第三象限
        } else if (disPoint.x < 0 && disPoint.y < 0) {
            // 计算与x正方向的夹角
            originRadian = Math.asin(Math.abs(disPoint.y) / distance);
            originRadian = originRadian + Math.PI;
        } else if (disPoint.x >= 0 && disPoint.y < 0) {
            // 计算与x正方向的夹角
            originRadian = Math.asin(disPoint.x / distance);
            originRadian = originRadian + Math.PI * 3 / 2;
        }

        // 弧度换算成角度
        originDegree = radianToDegree(originRadian);
        resultDegree = originDegree + degree;

        // 角度转弧度
        resultRadian = degreeToRadian(resultDegree);

        resultPoint.x = (int) Math.round(distance * Math.cos(resultRadian));
        resultPoint.y = (int) Math.round(distance * Math.sin(resultRadian));
        resultPoint.x += center.x;
        resultPoint.y += center.y;

        return resultPoint;
    }

    /**
     * 弧度换算成角度
     *
     * @return
     */
    public static double radianToDegree(double radian) {
        return radian * 180 / Math.PI;
    }


    /**
     * 角度换算成弧度
     * @param degree
     * @return
     */
    public static double degreeToRadian(double degree) {
        return degree * Math.PI / 180;
    }

    /**
     * 根据点击的位置判断是否点中控制旋转，缩放的图片， 初略的计算
     * @param x
     * @param y
     * @return
     */
    private int JudgeStatus(float x, float y){
        PointF touchPoint = new PointF(x, y);
        PointF controlPointF = new PointF(mControlPoint);
        PointF deletePointF = new PointF(mDeletePoint);

        //点击的点到控制旋转，缩放点的距离
        float distanceToControl = distance4PointF(touchPoint, controlPointF);
        float distanceToDelete = distance4PointF(touchPoint, deletePointF);

        //如果两者之间的距离小于 控制图标的宽度，高度的最小值，则认为点中了控制图标
        if(distanceToControl < Math.min(mDrawableWidth/2, mDrawableHeight/2)){
            return STATUS_ROTATE_ZOOM;
        }

        //如果两者之间的距离小于 控制图标的宽度，高度的最小值，则认为点中了移除按钮
        if(distanceToDelete < Math.min(mDrawableWidth/2, mDrawableHeight/2)){
            return STATUS_DELETE;
        }

        return STATUS_DRAG;

    }

    /**
     * 两个点之间的距离
     * @param pf1
     * @param pf2
     * @return
     */
    private float distance4PointF(PointF pf1, PointF pf2) {
        float disX = pf2.x - pf1.x;
        float disY = pf2.y - pf1.y;
        return  (float)Math.sqrt(disX * disX + disY * disY);
    }


    @SuppressWarnings("serial")
    public static class NotSupportedException extends RuntimeException{
        private static final long serialVersionUID = 1674773263868453754L;

        public NotSupportedException() {
            super();
        }

        public NotSupportedException(String detailMessage) {
            super(detailMessage);
        }

    }

    public interface OnDeleteListener{
        void onDelete();
    }

}
