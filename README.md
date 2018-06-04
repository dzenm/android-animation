##一、分类



1. Tween Animation（变换动画）
2. Frame Animation（帧动画）
3. Layout Animation（布局动画）

   

##二、使用

### Tween Animation（变换动画）

####一、常见动画

1. Alpha：透明度渐变动画
2. Scale：尺寸缩放动画
3. Translate：位置移动动画
4. Rotate：旋转动画



#### 二、共同属性

1. Duration：动画持续时间（单位：毫秒）
2. fillAfter：设置为true，动画转化在动画结束后应用
3. fillBefore：设置为true，动画转化为动画结束前应用
4. interpolator：动画插入器（分为加速和减速插入器）
5. repeatCount：动画重复次数
6. repeatMode：顺序重复/倒序重复
7. startOffset：动画之间的时间间隔



#### 三、Animation的实现

##### 1、通过配置文件：位于android project的/res/anim，如果不存在该文件夹，可直接新建anim文件夹。可以使用的属性有  `alpha`  ， `scale` ， `translate` ， `rotate`。



#####2、通过Java代码实现：有如下几个方法 AlphaAnimation，ScaleAnimation，TranslateAnimation，RotateAnimation

```java
// 创建Alpha动画，透明度10%---100%的变换
Animation alpha = new AlphaAnimation(0.1f, 1.0f);
// 设置动画时间为5秒
alpha.setDuration(5000);
// 开始播放
img.startAnimation(alpha);
```



####四、AlphaAnimation（透明度动画）

1、fromAlpha：动画起始时透明度

2、toAlpha：动画终止时的透明度

* 注：0.0表示完全透明，1.0表示完全不透明

3、duration：动画过渡时间



```xml
<alpha android:duration="5000"
       android:fromAlpha="0.0"
       android:toAlpha="1.0" />
```

````java
Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
view.startAnimation(animation);
````



![透明度动画](https://github.com/freedomeden/AnimationDemo/blob/master/screenshots/1.gif?raw=true)





#### 五、ScaleAnimation（缩放动画）

1、fromX，toX：分别是起始和结束时x坐标上的伸缩尺寸

2、fromY，toY：分别是起始和结束时y坐标上的伸缩尺寸

3、pivotX，pivotY：分别为伸缩动画相对于x，y坐标开始时的位置

4、interpolator：动画插入器，@android:anim/accelerate_decelerate_interpolator为系统提供的先加速后减速的效果

5、fillAfter：动画结束后是否显示



```xml
<scale
        android:duration="5000"
        android:fillAfter="false"
        android:fromXScale="0.0"
        android:fromYScale="0.0"
        android:interpolator="@android:anim/accelerate_decelerate_interpolator"
        android:pivotX="50%"
        android:pivotY="50%"
        android:toXScale="1.0"
        android:toYScale="1.0" />
```

```java
animation = AnimationUtils.loadAnimation(this, R.anim.scale);
                imageView.startAnimation(animation);
```

![缩放动画](https://github.com/freedomeden/AnimationDemo/blob/master/screenshots/2.gif?raw=true)





#### 六、TranslateAnimation（位移动画）

1、fromXDelta、fromYDelta：分别时起始时X、Y的坐标

2、toXDelta、toYDelta：分别是结束时X，Y的坐标



```xml
<translate
        android:duration="5000"
        android:fromXDelta="10"
        android:fromYDelta="10"
        android:toXDelta="100"
        android:toYDelta="100" />
```

```java
animation = AnimationUtils.loadAnimation(this, R.anim.translate);
imageView.startAnimation(animation);
```

![位移动画](https://github.com/freedomeden/AnimationDemo/blob/master/screenshots/3.gif?raw=true)





####七、RotateAnimation（旋转动画）

1、fromDegrees、toDegrees：开始旋转的角度，结束旋转的角度

2、pivotX、pivotY：分别为伸缩动画相对于x，y坐标开始时的位置



```xml
<rotate
        android:duration="5000"
        android:fromDegrees="0"
        android:interpolator="@android:anim/accelerate_decelerate_interpolator"
        android:pivotX="50%"
        android:pivotY="50%"
        android:toDegrees="360" />
```

```java
animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
imageView.startAnimation(animation);
```

![旋转动画](https://github.com/freedomeden/AnimationDemo/blob/master/screenshots/4.gif?raw=true)





####八、组合动画

1、使用setAnimationListener设置监听事件，当第一个动画结束后，开始执行第二个动画



2、使用动画集AnimationSet，在一个动画xml里添加多个动画，使用startOffset为开始播放下一个动画设置上一个动画所用duration所用的时间

```XML
<scale
        android:duration="1000"
        android:fillAfter="false"
        android:fromXScale="0.0"
        android:fromYScale="0.0"
        android:interpolator="@android:anim/accelerate_decelerate_interpolator"
        android:pivotX="50%"
        android:pivotY="50%"
        android:toXScale="0.5"
        android:toYScale="0.5" />
    <alpha
        android:duration="1000"
        android:startOffset="1000"
        android:fromAlpha="0.1"
        android:toAlpha="0.5" />
    <rotate
        android:duration="5000"
        android:fromDegrees="0"
        android:startOffset="2000"
        android:interpolator="@android:anim/accelerate_decelerate_interpolator"
        android:pivotX="50%"
        android:pivotY="50%"
        android:toDegrees="360" />
```

![使用动画集AnimationSet](https://github.com/freedomeden/AnimationDemo/blob/master/screenshots/5.gif?raw=true)





3、使用Animation的setRepeatCount、setRepeatMode来实现循环播放动画（通过代码实现）

```java
AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
alphaAnimation.setDuration(100);
alphaAnimation.setRepeatCount(10);
alphaAnimation.setRepeatMode(Animation.REVERSE);
imageView.startAnimation(alphaAnimation);
```



4、Activity切换动画：使用overridePendingTransition方法，两个参数分别为第二个activity进入动画，第一个activity退出时的动画。

* zoom_in.xml，进入时的动画

```xml
<scale
        android:duration="1000"
        android:fromXScale="0.1"
        android:fromYScale="0.1"
        android:pivotX="50%"
        android:pivotY="50%"
        android:toXScale="1.0"
        android:toYScale="1.0" />
    <alpha
        android:duration="1000"
        android:fromAlpha="0"
        android:toAlpha="1.0" />
```

* zoom_out.xml，退出时的动画

```xml
<scale   android:duration="@android:integer/config_mediumAnimTime"
        android:fromXScale="1.0"
        android:fromYScale="1.0"
        android:pivotX="50%p"
        android:pivotY="50%p"
        android:toXScale="0.1"
        android:toYScale="0.1" />
    <alpha
        android:duration="@android:integer/config_mediumAnimTime"
        android:fromAlpha="1.0"
        android:toAlpha="0" />
```

* Activity跳转

```java
Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
```

![Activity切换动画](https://github.com/freedomeden/AnimationDemo/blob/master/screenshots/6.gif?raw=true)



### LayoutAnimation（布局动画）

> 为View Groups添加动画
>
> 使用LayoutAnimationController



#### 一、使用LayoutAnimationController

* 加载布局动画使用setLayoutAnimation()
* setOrder设置加载的顺序，随机（ORDER_RANDOM），正常（ORDER_NORMAL），倒序（ORDER_REVERSE）

```java
LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.zoom_in));
lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
listView.setLayoutAnimation(lac);
listView.startLayoutAnimation();
```





### FrameAnimation（逐帧动画）

#### 一、使用animation-list标签来分组一个item标签集合，定义要显示的图片，指定显示它的时间（以毫秒为单位）。

```xml
<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android">
	<item android:drawable="@drawable/one"
	  	  android:duration="500"/>
    <item android:drawable="@drawable/two"
          android:duration="500"/>
    <item android:drawable="@drawable/three"
          android:duration="500"/>
    <item android:drawable="@drawable/four"
          android:duration="500"/>
    <item android:drawable="@drawable/five"
          android:duration="500"/>
    <item android:drawable="@drawable/six"
          android:duration="500"/>    
</animation-list>
```



###[APK下载](https://github.com/freedomeden/AnimationDemo/raw/master/screenshots/app-debug.apk)