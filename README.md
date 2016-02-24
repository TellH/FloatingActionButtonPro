# FloatingActionButtonPro
Extend from [FloatingActionButton](https://github.com/makovkastar/FloatingActionButton)
##usage
add this in your build.gradle(app module):
```
dependencies {
    compile 'com.tellh:library:1.0'
}
```
And add this to your build.gradle(application root dir):
```
allprojects {
    repositories {
        jcenter()
        maven {
            url 'https://dl.bintray.com/tellh/maven/'
        }
    }
}
```
##Features:
* fab with scale transform animation:

![](https://github.com/TellH/FloatingActionButtonPro/blob/master/gif%2Flv_scale.gif)

xml:

```
    <com.tellh.library.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|right"
        android:layout_margin="16dp"
        android:src="@drawable/ic_add_white_24dp"
        fab:fab_animation="scale"
        fab:fab_colorNormal="@color/accent"
        fab:fab_colorPressed="@color/accent_pressed"
        fab:fab_colorRipple="@color/ripple"/>
```
* in RecyclerView , you can use the smoothly scale transform during dragging:

![](https://github.com/TellH/FloatingActionButtonPro/blob/master/gif%2Frv_scale.gif)

xml is the same above. But some difference in java code.
```
  fab.attachToRecyclerView(recyclerView,null,null,true);//pass the ture flag if you need this effect.
```

* in RecyclerView , you can also use the smoothly translate transform during dragging:

![](https://github.com/TellH/FloatingActionButtonPro/blob/master/gif%2Frv_translation.gif)

xml:
```
	<com.tellh.library.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|right"
        android:layout_margin="16dp"
        android:src="@drawable/ic_add_white_24dp"
        fab:fab_animation="translate"
        fab:fab_colorNormal="@color/accent"
        fab:fab_colorPressed="@color/accent_pressed"
        fab:fab_colorRipple="@color/ripple"/>
```
java code:
```
    fab.attachToRecyclerView(recyclerView,null,null,true);//pass the ture flag if you need this effect.
```
