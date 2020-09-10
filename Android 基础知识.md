## Android 基础知识

https://www.runoob.com/android/android-content-providers.html

Android四大组件： Activity、BroadcastReceiver、Service、Content Provider

Controller(Activity, Fragment 业务逻辑) ->  ViewModel(LiveData) [双向]

Controller(Activity, Fragment 业务逻辑) -> DataBinding -> View Group (View)

ViewModel(LiveData)  -> View Group (View)

类名 对象名 = new 构造方法;//创建对象

如： Student st = new Student();//创建对象  

访问接口（API）需要

a. 查API定义 `android.content.SharedPreferences` 因此同父类`content`的class可以直接访问SharedPreferences API。

b. 父类不同的class想访问API时，需要在类中的构造方法中传入context，然后通过context来getSharPreferences

```java
public class MyData{
	private COntext context;
	public MyData(Context context){
		this.context = context
	}
    
    public void test(){
        SharedPreferences shp = context.getSharePreference();
    }
}
```

c. 在第三方类中创建对象时，传context时要传ApplicationContext

`MyData myData = new MyData(getApplicationContext());`

需要访问全局资源时，用方法`getApplication().getResource()`，例如：getApplication().getResource().getString(R.id.xxx)



*接口方法的抽象方法是实现这个接口必须要实现的方法；*

对于一个Activity，思考如何获取视图、如何管理视图、如何管理动态的视图数据及如何响应用户操作。



当方法的传参提示是成对出现时， 如`Observer<> observer`  和 ` LayoutManager layout` ,即提示用户这里要初始化一个类的实例，所以我们要new一个对象传入或传入已经初始化过的参数。 



解耦： 很大程序上表现为系统组件的生命周期与普通组件之间的解耦。普通组件在使用过程中通常需要依赖于系统组件的生命周期。有时我们需要在系统组件的生命周期回调方法中，主动对普通组件进行调用或控制，因为普通组件无法主动获知系统组件的生命周期事件。例如我们常需要在页面的onCreate方法中对组件进行初始化、在onPause方法中停止组件，在onDestroy方法中进行资源回收





### 1. Activity的4种状态

什么是activity

----

跟用户交互的界面叫Activity。

running ：用户可以点击屏幕，屏幕会做出响应；处于栈顶

paused ：Activity失去焦点时，被透明的Activity占据栈顶时

stopped ：Activity不可见

killed ：Activity已经被系统回收







### 2. Activity的生命周期

**Activity启动：onCreate() - > onStart() ->onResume()**

onCreate：设置布局资源，数据加载，图片的预加载等。

onStart： 未在前台显示，用户还未能与Activity交互。但是可以看见

onResume：前台可见，用户可以交互。



**点击Home键回到主界面（Activity不可见） - >onPause() ->onStop()**

onPause: 可见但是不能被交互，对应onResume

onStop：Activity停止或者完全被覆盖，不可见但是在后台运行。如果系统内存紧张就会被回收。



**当再次回到原Activity时 ->onRestart() ->onStart() ->onResume()**

**当退出当前Activity时 ->onPause() ->onStop() ->onDestroy()**



### 3. Android进程优先级

前台 / 可见 / 服务 / 后台 / 空

前台：正在与用户交互的Activity、前台Activity绑定的Service

可见：用户不能点击

服务：在后台开启的service服务

后台：例如前台通过Home键转为后台进程

空：随时可以被kill的进程



### 4. Android任务栈

一个任务栈（Stack）包含的是activity的集合。

程序退出时，一定要删除任务栈或安全地保存任务栈。

 

### 5. activity启动模式

standard / singletop / singletask / singleinstance / 



### 6. scheme跳转协议

是一种页面内跳转协议，可以非常方便跳转app中的各个页面；

通过scheme协议，服务器可以

a. 定制化告诉App跳转哪个页面；

b. 可以通过通知栏消息定制化跳转页面；

c. 可以通过H5页面跳转页面等；

应用场景

服务端下发url，客户端根据服务端下发url跳转到相应页面；

从H5页面跳转到相应的App Activity；

App 根据url跳转到另一个App；（应用推广）



### 7. Fragment为什么被称为第五大组件

fragment动态灵活地加载activity。

- Fragment 加载到Activity的两种方式

  1） 静态加载：添加Fragment到Activity的布局文件当中

  2） 动态加载：动态在activity中添加fragment

```java
//步骤一： 添加一个FragmentTransaction的实例, 这个实例可以用来添加或替换相对应的fragment。
FragmentManager fragmentManager = getFragmentManager（）;
FragmentTransaction transaction = fragmentManager.beginTransaction();

//步骤二： 用add()方法加上Fragment的对象rightFragment，容器资源用来做标志位。
RightFragment rightFragment = new RightFragment();
transaction.add(R.id.right_container, rightFragment, "rightFragment");

//步骤三： 调用commit()方法使得FragmentTransaction实例的改变生效
transaction.commit();

```



- FragmentPagerAdapter与FragmentStatePagerAdapter的区别

  viewpager是用来实现页面左右滑动的控件；

  前者是用于页面较少的情况，后者是用于页面较多的情况；

  FragmentStatePagerAdapter 比前者更节省内存；因为后者最后一行是remove，前者最后一行是detach

  

### 8. Fragment的生命周期

* 创建Fragment对象

Fragement跟Activity关联后： onAttach() -> onCreate() ->  onCreateView() -> onViewCreated (表明fragment已经完全创建好，可以开始创建资源) -> [Activity]onCreate() -> onActivityCreated() -> [Activity]  onStart() (表明Activity可见) -> onStart() (表明fragment也可见了。) -> [Activity] onResume()(表明Activity可以与用户交互了) -> onResume()(表明fragment也可以与用户交互了)

Activity的生命周期先于Fragment

* Fragment退至后台

onPause() -> [activity]onPause() -> onStop() -> [Activity]onStop() -> onDestoryView() -> onDestory() -> onDetach() -> [Activity] onDestory()



### 9. Fragment通信

* 在Fragment中调用Activity中的方法 getActivity
* 在Activity中调用Fragment中的方法 接口回调
* 在Fragment中调用Fragment中的方法 findFragmentById



### 10. Fragment的replace、add、remove方法

* replace是将Fragment的最上层替换成想目标fragment
* add是直接将目标fragment加到最上层



### 11. Service基础 （四大组件之一）

* 什么是Service

service是一种可以在后台执行长时间运行操作而没有用户界面的应用组件。service可以被activity或broadcast（广播）启动

可以将service绑定到activity进行数据交互

service是运行在主线程，不能做耗时操作，否则会造成UI线程阻塞，屏幕无响应，甚至ANR（Application Not Responding）一定要做耗时操作时，开启线程去操作。因为主线程也是UI线程。



* service和Thread的区别

Thread是程序执行的最小单元， 运行相对独立，用于开启一个子线程。

```java
protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R,layout.activity_main);
    Log.d("MainActivity", "MainActivity thread id is" + Thread.currentThread().getId());
    final Intent intent = new Intent(MainActivity.this, StartService.class);
    
    mBtnStartService.setOnCLickListener(new View.OnclickListener() {
        @Override
        public void onClick(View v) {
            startService(intent);
        }
    });
}
```



### 12. 两种启动service的方式

* startservice

  类里有4个生命周期方法

  ```java
  //IBinder 是 Binder类
  public IBinder onBind(Intent intent){
  
  }
  
  /**
  *	首次创建服务是，系统将调用次方法执行一次性设置程序 （在调用 onStartCommand（）或 onBind（）之前）
  *	如果服务已在运行，则不会调用此方法。 该方法只被调用一次
  */
  public void onCreate(){
  
  }
  
  /**
   *	每次通过startService（）方法启动Service时都会被回调
   *  一旦调用这个方法，服务就会被正式开始
   *	@param intent
   *	@param flags
   *	@param startId
   *	@return
   */
  public int onStartCommand (Intent intent, int flags, int startId){
      
  }
  
  /**
   * 服务销毁时的回调
   */
  @Override
  public void onDestroy(){
      System.out.println("onDestroy invoke");
      super.onDestroy();
  }
  
  
  ```

  a. 定义一个类继承Service

  b. 在Manifest.xml文件中配置该Service

  c. 使用Context的startService（Intent）方法启动该Service

  d. 不再使用时，调用stopService（Intent）方法停止该服务

  

* bindService

  多个Activity可以绑定一个service，绑定全部取消后，service自动被销毁

  a. 创建BindService服务端，继承自Service并在类中，创建一个实现IBinder 接口的实例对象并提供公共方法给客户端调用

  b. 从 onBind（）回调方法返回此Binder实例。

  c. 在客户端中，从onServiceConnected（）回调方法接受Binder，并使用提供的方法调用绑定服务；

  

  BindService.java

  ```java
  public class BindService extends Service{
      private final static String TAG = "wzj";
      private int count;
      private Thread thread;
      private LocalBinder binder = new LocalBinder();
      
      /**
       *	创建Binder对象，返回给客户端既Activity使用，提供数据交换的接口
       *	内部类
       */
      public class LocalBinder extends Binder{
          //声明一个方法， getService.(提供给客户端使用)
          BindService getService(){
              // 返回当前对象LocalService，这样我们就可在客户端调用Service的公共方法了.
              // 因为已经获取到Bindservice的实例了
              return BindService.this;
          }
      }
      
      /**
       *	把Binder类返回给客户端
       */
      @Nullable
      @Override
      public IBinder onBind(Intent intent) {return binder;}
  }
  ```

  BindActivity.java

  ```java
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceStated);
      setContentView(R.layout.activity_bind);
      btnBind = (Button) findViewById(R.id.BindService);
      btnUnBind = (Button) findViewById(R.id.unBindService);
      btnGetDatas = (Button) findBiewById(R.id.getServiceDatas);
      //创建绑定对象
      final Intent intent = new Intent(this, BindService.class)
          
      //开启绑定
      btdBind.setOnclickListener(View v){
          Log.d(TAG, "绑定调用：bindService")
          //调用绑定方法
          bindService(intent, conn, Service.BIND_AUTO_CREATE);
      }
      
      //解除绑定
      btnUnBind.setOnClickListener (View v){
          Log.d(TAG, "解除绑定调用：unbindService");
          //解除绑定
          if(mService!=null){
              mService = null;
              unbindService(conn);
          }
      }
  }
  
  conn = new ServiceConnection() {
      /**
       *	与服务器端交互的接口方法 绑定服务器的时候被回调， 在这个方法获取绑定Service传递过来的Ibinder对象。
       *	通过这个IBinder对象，实现宿主与Service的交互
       *  操控的变量是mService
       */
      @Override
      public void onServiceConnected(ComponentName name, IBinder service) {
          Log.d(TAG, "绑定成功调用：onServiceConnected");
          // 获取Binder
          BindService.LocalBinder binder = (Bindservice.LocalBinder) service;
          mService = binder.getService();
      }
      
      /**
       *	当取消绑定的时候被回调， 但正常情况下是不被调用的。它的调用时机是当Service服务被意外销毁时
       *	例如内存的资源不足时这个方法才被自动调用。
       */
      @Override
      public void onServiceDisconnected(ComponentName name){ mService = null;}
  }
  ```



### 13. Broadcast Receiver

* 广播的定义

  在Android中，Broadcast是一种广泛运用在应用程序之间传输&共享信息的机制，我们要发送的广播内容是一个Intent，这个Intent中可以携带我们要传送的数据

  只要和发送广播的Action相同的接受者都能接受到这个广播。一个Broadcast可以被多个receiver接收。

  通知作用：发送程序给activity更新UI，接受service发过来的信息，传给activity。

* 广播的使用场景

  a. 同一app具有多个进程的不同组件之间的消息通信

  b. 不同app之间的组件之间消息通信

* 广播种类

  Normal Broadcast: Context.sendBroadcast

  System Broadcast: Context.sendOrderedBroadcast

  Local Broadcast: 只在自身App内传播

  

### 14. 实现广播 - receiver

* 静态注册：在manifest注册完成就一直运行。特点：它所依赖的activity被销毁了，但是仍然能接收广播。
* 动态注册：跟随activity的生命周期，在代码中调用registerReceiver注册

* 内部实现机制

  a. 自定义广播接受者BoradcastReceiver，并复写onReceiver（）方法；

  b. 广播通过Binder机制向AMS（Activity Manager Service）进制注册；

  c. 广播发送者通过Binder机制向AMS发送广播；

  d. AMS查找符合相应条件（IntentFilter/Permission等）的BroadcastReceiver，将广播发送到BroadcastReceiver（一般情况下是Activity）相应的消息循环队列中；

  e. 消息循环执行拿到此广播，回调BroadcastReceiver中的onReceive（）方法

（AMS是发送广播的枢纽）



### 15. LocalBroadcastManager详解

1. 使用它发送的广播将只在自身App内传播，因此不必担心泄漏隐私数据
2. 其他App无法对你的App发送该广播，因为你的App不可能接收到非自身应用发送的该广播，因此不必担心有安全漏洞可以利用
3. 比系统的全局广播更加高效
4. 2的原因是内部是通过Handler来实现广播发送的。 系统广播通过Binder实现。
5. LocalBroadcastManager内部协作主要是靠这两个Ma集合：mReceviers 和mActions，当然还有一个List集合mPendingBroadcasts, 这个主要就是存储待接收的广播对象 

```java
//LocalBroadcastManager的构造方法
//MainLooper是主线程的Looper
private LocalBroadcastManager(Context context){
	mAppContext = context;
	mHandler = new Handler(context, getMainLooper()){
	
		@Override
		public void handleMessage(Message msg){
			switch (msg.what){
				case MSG_EXEC_PENDING_BROADCASTS:
					executePendingBroadcasts();
					break;
				default:
					super.handleMessage(msg);
			}
		}
	};
}

//mReceivers 是一个HashMap类
//IntentFilter 是过滤类
private final HashMap<BroadcastReceiver, ArrayList<IntentFilter>> mReceivers = new HashMap<->();

private final HashMap<String, ArrayList<ReceiverRecord >> mActions = new HashMap<->();

//存储和发送广播的Action匹配的receiver record的集合
//存储了广播接收器的存储器
private final ArrayList<BroadcastRecord> mPendingBroadcasts = new ArrayList<->();

//注册方法是registerReceiver
entries.add(entry);

//注销方法是unregisterReceiver
//发送广播的方法是sendBroadcast
```

### 16. Webview常见的坑

a. Android API level 16及之前的版本存在远程代码执行安全漏洞。因WebView.addJavascriptInterface方法没有被正确限制使用，远程攻击者可以通过使用Java Reflection API利用该漏洞执行任意Java对象的方法。

b. webview在布局文件中的使用： webview写在其他容器中时，先remove LinearLayout里的webview，然后再调用webview.removeallviews 和 webview.destroy才能真正地销毁整个webview，不会导致内存泄露

c. jsbridge 让本地native 可以调用远端web和js的代码。反过来也行

d. webviewClient.onPageFinished ->WebChromeClient.onProgressChanged.

e. 后台耗电 - > 直接调用system.axxx 直接关闭虚拟机

f. Webview页面渲染问题（白块，闪烁） -> 暂时关闭硬件加速解决



### 17. Webview的内部泄露问题

原因：webview肯定要关联activity，但是webview内部操作又是在新的线程中执行，时间activity是没有办法确定的（activity的生命周期跟webview新线程的生命周期不一样），导致webview会一直持有activity引用，不能回收。应用内部类持有外部类的引用，导致外部类无法被回收。



### 18. Binder - Linux内核的基础知识

* 进程隔离 & 虚拟地址空间

  进程隔离：避免进程A可以操作进程B的数据；

  进程隔离的实现用到了虚拟地址空间

  如果A进程需要跟B进程进行通信，需要借助进程间的通信机制。在Android里是Binder

  

* 系统调用

  

* Binder驱动



### 19. Binder 通信机制介绍

* 为什么使用binder

  a. Anroid使用的Linux内核拥有着非常多的跨进程通信机制

  b. 性能

  c. 安全

* binder通信模型 《用到时再翻视频理解》

  a. 通信录（Service Manager表）： binder驱动 （中间商）

  b. 

* 什么是Binder

  a. Binder指的是一种跨进程的通讯机制

  b. 对于Server进程来说，Binder指的是Binder本地对象。对于Client进程来说，Binder指的是Binder代理对象。

  c. 都传输进程而言，Binder是可以进行跨进程传递的对象

### 20. Aidl



### 21. 什么是Fragment

* Fragment相当于模块化的一段activity
* 具有自己的生命周期，接收自己的事件
* 在activity运行时被添加或删除



### 22. 怎样创建&添加Fragment

在.java文件中，例如TestFragment中的onCreateView方法创建布局 （模块化activity）

创建方法：

```java
TestFragment.java
//View view = inflater.inflater(int resource, ViewGroup root, )
//传入的是需要重复利用的xml
@Nullable
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    Log.i(TAG, "onCreateView");
    View view = inflater.inflater(R.layout.xxx, container);
    TextView nameTextView = (TextView) view.findViewById(R.id.name_text_view);
    nameTextView.setText("fragment");
    return view;
    }
    
public static TestFragment newInstance(String namestring, int number){
    TestFragment testFragment = new TestFragment();
    Bundle bundle = new Bundle();
    bundle.putString("name", nameString);
    bundle.putInt("number", number);
    testFragment.setArguments(bundle);
    return testFragment;
}
    
@Override
public void onCreate(Bundle savedInstancestate){
    super.onCreate(savedInstanceState);
    Log.i(TAG, "onCreate");
    Bundle bundle = getArguments();
    
    if(bundle != null){
        String name = bundle.getString("name");
        String number = bundle.getInt("number");
    }
}
```



在.xml文件中，引用对应的.java Fragment布局。

如何重复利用？

```java
//在xml文件中创建fragment控件引用
<fragment
	android:id="@+id/fragment_test"
	//在本行引用TestFragment.java中创建好的模块化布局
	android:name="com.geekband.Test01.TestFragment"
	android:layout_width="match+parent"
	android:layout_height="60dpi"
    />
        
//重复引用
<fragment
	android:id="@+id/fragment_test_copy"
	android:name="com.geekband.Test01.TestFragment"
	android:layout_width="match+parent"
	android:layout_height="60dpi"
        
    
```



### 23. 如何管理Fragment

* 查找Fragment

  findFragmentById()

  findFragmentByTag()

  ```java
  Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_test);
  
  //判断找到的fragment是否为目标
  //The java instanceof operator is used to test whether the object is an instance of the specified type (class or subclass or interface).
  if（fragment instanceof TestFragment）{
  	//TODO: 待实现
  } else {
      //否则抛异常
      throw new IllegalStateException("is not testFragment");
  }
  
  ```

  

  

* Fragment的后退

  Fragment Stack

  popBackStack()

  addOnBackStackChangedListener()

  ```java
  
  ```

  

  

* 总结Fragment 的操作

  ```java
  //找到管理器
  FragmentManager fragmentManager = getFragmentManager();
  
  //向管理器请求开始交易 
  FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
  
  //交易员开始操作
  //@param viewgroup (本例传了linear layout)
  //@param Fragment （本例传了testFragment.java）
  //创建一个fragment对象实例
  TestFragment testFragment = TestFragment.newInstance("名字", 15);
  //将其添加到VIewGroup
  fragmentTransaction.add(R.id.fragment_view, testFragment);
  
  //交易员完成最后一笔操作，点提交
  //移除testFragment中已经由上面代码添加的view group
  fragmentTransaction.remove(testFragment).commit();
  ```

  

### 24. Fragment的生命周期

* onAttach() -> onCreate() -> onCreateView() -> onActivityCreated() -> onStart() ->onResume()  -> onPause() -> onStop() -> onDestroyView() -> onDestroy() -> onDetach()

  了解其生命周期，可以Override生命周期方法，在运行到例如onCreateView()阶段时，加入自定义的代码。

* 生命周期可以概括为三种状态：Resumed、Paused、Stoped



### 25. 什么是Bundle



### 26. 什么是Handler

定义：A Handler allows you to send and process Message and Runnable objects associated with a thread's MessageQueue. 

作用：    延时执行Message和Runnable 【怎样做到】

​			（主）线程 与 （子）线程间的通信 【怎样做到】



* Message

  两个整形数值：轻量级存储int类型的数据

  一个Object：任意对象

  replyTo：线程通信的时候使用

  What：用户自定义的消息码让接受者识别消息码 【消息的内容或类型】

  **核心数据结构：**When（long）、target（Handler）、what（int）、callback（runnable）

* MessageQueue

  Message的队列

  每一个线程最多只可以拥有一个

  Thread -》 Looper -》 MessageQueue

* Looper

  

* Hanlder

  核心数据结构：mLooper（Looper）、mQueue（MessageQueue）

### 实例： 用Handler写倒计时页面；



### 27. Screen Orientation

* 可以在ManiFest中设置Activity的screenOrientation属性；

* 在Xml中可以Creat Landscape variation，既创建一个横版与竖版不同的布局

  

### 28. ViewModel

* 属于Android Jetpack里的一个类（Androidx的一个库）

* 定义：ViewModel is a class that is responsible for preparing and managing the data for an `Activity` or a `Fragment`. 

* 目的：The purpose of the ViewModel is to acquire and keep the information that is necessary for an Activity or a Fragment. The Activity or the Fragment should be able to observe changes in the ViewModel. ViewModels usually expose this information via `LiveData` or Android Data Binding.

* ViewModel's only responsibility is to manage the data for the UI. It should never access your view hierarchy or hold a reference back to the Activity or the Fragment.

* 生命周期：存活于Activity的整个生命周期，当Activity被后台进程杀死时，ViewModel也会被重新创建，此时ViewModel管理的UI数据会丢失。

* 使用步骤

  a. 新建class文件，继承于ViewModel类

  b. 在class文件里，通过private定义参数，并提供Set()和Get()方法访问; 不写Set方法的，可以写其他访问方法去改变data的值。

  ```java
  private MutableLiveData<Integer> LikedNumber;
  public MutableLiveData<Integer> getLikeNumber(){
      if(LikeNumber == null) {
          LikeNumber = new MutableLiveData<>();
          LikeNumber.setValue(0);
      }
      return LikeNumber;
  }
  ```

  

  c. 回到对应的控制器（Activity或Fragment）中，创建ViewModel对象

  ```java
  //Class 名 对象名
  MyViewModel myViewModel;
  
  myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
  ```

* Fragment间数据共享

  ```java
  public class SharedViewModel extends ViewModel {
      private final MutableLiveData<Item> selected = new MutableLiveData<Item>();
  
      public void select(Item item) {
          selected.setValue(item);
      }
  
      public LiveData<Item> getSelected() {
          return selected;
      }
  }
  
  
  public class MasterFragment extends Fragment {
      private SharedViewModel model;
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
          itemSelector.setOnClickListener(item -> {
              model.select(item);
          });
      }
  }
  
  public class DetailFragment extends Fragment {
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          SharedViewModel model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
          model.getSelected().observe(this, { item ->
             // Update the UI.
          });
      }
  }
  ```

  

### 29.LiveData

LiveData是一种类,持有可被观察的数据。

https://blog.csdn.net/feather_wch/article/details/88648559

* 使用步骤

  a. 创建LiveData对象，`LiveData`通常存储在`ViewModel`之中, 并通过`get方法`来获取

  ```java
  public class UserViewModel extends ViewModel {
      private MutableLiveData<String> mName;
      private MutableLiveData<Integer> mAge;
  
      public MutableLiveData<String> getName() {
          if(mName == null){
              mName = new MutableLiveData<>();
          }
          return mName;
      }
  
      public MutableLiveData<Integer> getAge() {
          if(mAge == null){
              mAge = new MutableLiveData<>();
          }
          return mAge;
      }
  }
  ```

  b. 观察LiveData对象
  	在App组件的哪个生命周期适合观察LiveData对象？为什么？

  ​	app组件的onCreate()方法

  ​	不适合在onResume()等方法中，可能会调用多次
  ​	能确保组件能尽可能快的展示出数据。只要app组件处于启动状态(STARTED)就会立即接收到LiveData对象	中的数据

  ```java
  public class DataBindingActivity extends AppCompatActivity {
  
      ActivityDatabindingLayoutBinding mBinding;
      User mUser;
      private UserViewModel mUserViewModel;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          // DataBinding
          // xxx
  
          //  1. 创建用户信息的ViewModel
          mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
          //  2. 创建更新UI的观察者
          Observer<String> nameObserver = new Observer<String>() {
              @Override
              public void onChanged(@Nullable String s) {
                  // 利用DataBinding更新: 用户账号
                  mUser.setAccount(s);
                  mBinding.setUser(mUser);
              }
          };
          //  3. 注册观察者
          mUserViewModel.getAccount().observe(this, nameObserver);
      }
  }
  ```

  ```java
  viewModelWithLiveData.getLikeNumber().observe(this, new Observer<Integer>(){
      @Override
      public void onChanged(Integer integer){
          textView.setText(String.valueOf(integer));
      }
  });
  ```

  

  c. 更新LiveData对象

  ```java
//调用setValue()或者postValue()都会调用所有观察者的onChanged()方法
  button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
          String anotherName = "John Doe";
          model.getCurrentName().setValue(anotherName);
      }
  });
  
  ```
  
  

### 30. Data Binding

* 什么是Data Binding

  

* Data Binding的使用步骤

  a. 在build.gradle里的android区域声明：dataBinding {

  ​	eanbled true

  } 

  b. 将对应的xml文件convert to databinding layout

  c. 在对应的activity文件中初始化并设置相应的View；

  ```java
  ActivityMainBinding binding;
  binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
  ```

  d. 将数据回绑到控件上

  ```java
  //在xml的data标签组内新建variable
  <variable
      name ="data"
      type ="com.example.databinding.MyViewModel" />
      
  //将数据传给想要的控件
  <TextView
      android：text="@{String.valueOf(data.number)}"/>
  
  <Button
      android:onClick="@{()->data.add()}"/>
  ```

  e. 在Activity中简化

  ```
  //设置数据
  binding.setData(myViewModel);
  //实现Live Data的自我监听
  binding.setLifecycleOwner(this);
  
  ```

  

### 31. SharedPreferences

* 什么是sharedpreferences



* 如何使用

  a. 初始化

  ```java
  //这样创建shp可以被内部的其他activity访问
  SharedPreference shp = getShardPreferences("MY_DATA", Context.MODE_PRIVATE)
  
  //只允许本类访问
  SharedPreference shp = getPreferences(Context.MODE_PRIVATE);
  
  ```

  b. 创建Editor

  ```java
  SharedPreference.Editor editor = shp.edit();
  editor.putInt("NUMBER", 100);
  ```

  c. 提交Editor操作

  ```
  editor.apply();
  ```

  d. 从sharedpreferences中读取数据

  ```java
  int x = shp.getInt("NUMBER", 0);
  ```

  e. 非context父类的类需要访问是，需要在类中的构造方法中传入context，然后通过context来getSharPreferences



### 32. AndroidViewModel

* 什么是AndroidVIewModel



* 怎么使用AndroidViewModel

  a. 需要SavedStateHandle handle；

  b. 在构造方法中，将handle的值传给handle；

  ```java
  // MyViewModel Class对应的构造方法
  public MyViewModel(Application application, SavedStateHandle handle){
      super(application);
      //访问a中的handle，将传入的handle赋值给a中的handle
      this.handle = handle;
      //判断handle中是否包含有需要的信息，如果没有，执行load方法加载
      if (!handle.contains(key)){
          load();
      }
  }
  
  //从SharedPreferences里读取需要的数据，数据的标记是preferences的名称
  //Context.MODE_PRIVATE 是模式的固定写法
  private void load() {
      SharedPreference shp = getApplication().getSharedPreferences(shpName, Context.MODE_PRIVATE)
      //读取后复制给handle
      int x =
      handle.set(key, x)
  }
  ```

  c. 同之前，创建get方法供外部类访问LiveData.并由此可见，handle持有LiveData

  ```
  public LiveData<integer> getNumber(){
  	return handle.getLiveData(key);
  }
  ```

  d. 创建save()方法保存数据到SharedPreferences

  ```java
  void save(){
  	SharedPreferences shp = getApplication().getSharedPreferences(shpName, Context.MODE_PRIVATE);
  	SharedPreferences.Editor editor = shp.edit();
  	//将handle的值存在Preferences
  	editor.putInt(key, getNumber().getValue());
      editor.apply();
  }
  ```

  

### 33. Navigation组件

Handle everything needed for in-app navigation.

它具备以下优势： a. 可视化的页面导航图、便于开发者理清页面间的关系。b. 通过destination和action完成页面间的导航。c. 方便添加页面切换动画。 d. 页面间类型安全的参数传递。 e. 通过Navigation UI类，对菜单、底部导航、抽屉菜单导航进行统一的管理。 f. 支持深层链接DeepLink

* **NavHost**

  是一个容器，用来存放页面

* **NavHostFragment**

  NavHostFragment是一个特殊的Fragment，我们需要将其减价到Activity的布局文件中，作为NavGraph的容器。 

* **NavController**

  是一个Java/Kotlin对象，用于在代码中完成Navigation Graph中具体的页面切换工作

* **NavGraph**

  新型的XML资源文件，包含了App（部分）Fragment的导航关系。



* 怎么使用Navigation组件

  a、新建好空白的fragment activity & xml；

  b、移除fragment xml中的TextView ，并将布局改为ConstraintLayout

  c、创建Navigation资源文件，并添加fragment间的导航线路图（NavGraph）

  d、到Activity_main.xml的Containers控件栏下添加Host - NavHostFragment

  e、到对应的Fragment（Home Fragment）中写监听事件

  ```java
  public void onActivityCreated(Bundle savedInstanceState){
      super.onActivityCreated(sacedInstanceState);
      getView().findViewById(R.id.button).setOnClickListener(new View.OnclickListener()){
          @Override
          public void onClick(View v){
              NavController controller = Navigation.findNavController(v);
              controller.navigate(R.id.action_homeFragment_to_detailFragment);
          }
      });
  }
  ```

  f、在MainActivity中添加返回键

  ```java
  NavController controller = Navigation.findNavController(this, R.id.fragment);
  NavigationUI.setupActionBarWithNavController(this,controller);
  
  //添加返回功能
  @Override
  public boolean onSupportNaviagateUp(){
      NavCOntroller controller = Naviagation.findNavController(this, R.id,fragment);
      return controller.navigateUp();
  }
  ```

  g、Navigate action的数据传递 - 在xml文件中添加Arguments (Key Value型数据)，可以在Fragment activity中获取到。然后action中允许在argument中重载fragment中的值。 【传递静态方式案例】

  ```java
  String string = getArguments().getString(key);
  ```

  h. 用Bundle传递动态数据，先判要传的数据是否为空，再用Bundle传数据

   ```
  //起点放入数据
  Bundle bundle = new Bundle();
  bundle.putString("my_name", string);
  
  NavController controller = Navigation.findNavController(v);
  controller.navigate(R.id.action_homeFragment_to_detailFragment, bundle);
  
  //终点取出数据
  String string2 = getArguments().getString("my_name");
  
   ```

  

* 数据传递（ViewModel篇）

  核心：利用ViewModel来管理Fragment中的数据

  a、建立ViewModel后，与Fragment通讯部分与跟Activity相同

  b、在Fragment中创建Binding实例

  ```java
  MyViewModel myViewModel;
  myViewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
  FragmentMasterBinding binding;
  binding = DataBindingUtil.inflate(inflater, R.layout.fragment_master, container, false);
  binding.setData(ViewMOdel);
  binding.setLifecycleOwner(getActivity());
  ```

  d、按键监听实例

  ```java
  //因为ViewModel的数据可以直接在Fragment之间共享获得，所以按键动作不用再传递数据。
  binding.button.setOnclickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
          NavController controller = Navigation.findNavController(v);
          controller.navigate(R.id.action_masterFaragment_to_detailFragment);    
      }
  });
  ```

  

  使用Safe args插件传递参数：

  在学习safe args插件之前，先回顾Fragment间最常见的传递参数和接受参数的方式。

  ```java
  //传递参数
  Bundle bundle = new Bundle();
  bundle.putString("user_name", "Michael");
  bundle.putInt("age", 30);
  Navigation.findNavController(v)
      .navigate(R.id.action..., bundle);
  ```

  ```java
  //接受参数
  Bundle bundle = getAtguments();
  if(bundle !=null){
      String userName = bundle.getString("user_name");
      int age = bundle.getInt("age");
      TextView tvSub = view.findViewById(R.id.tvSub);
      tvSub.setText(userName + age);
  }
  ```

  接下来看safe args 有什么不同：

  在Fragment之间进行参数传递

  ```java
//插入参数
  Bundle bundle = new MainFragmentArgs.Builder()
    							 .setUserName("Michael")
      							 .setAge(30)
      							 .build().toBunlde();
  
  Navigation.findNavController(v)
      	.navigate(R.id.action_xxx_to_xxx);
  ```
  
  ```java
  //接受参数
  Bundle bundle = getArguments();
  if(bundle != null){
      String userName = MainFragmentArgs
          				.fromBundlegetArguments().getUserName();
      int age = MainFragmentArgs.fromBundle(getArgument()).getAge();
      tvSub.setText(userName + age);
  }
  ```
  
  
  
  
  
  
  
  


### 34. LiveData

* LiveData is a `data holder class` that can be observed within a given `lifecycle`.



### 35. 泛型

https://blog.csdn.net/H12KJGJ/article/details/74482066



### 36. 口算挑战项目总结

`SavedStateHandle`

定义：This is a key-value map that will let you write and retrieve objects to and from the saved state.

用法：You can read a value from SavedStateHandle via `get(String)` or observe SavedStateHandle via `LiveData` returned by `getLiveData(String)`.

You can write a value to SavedStateHandle via `set(String, Object)` or setting a value to `MutableLiveData` returned by `getLiveData(String)`.



`xml文件中的占位符`

https://www.jianshu.com/p/a97beae40eb9

`%s、%1$s、%d、%1$d` s是String类型的占位符， d是int类型的占位符；

```java
1.在strings.xml中定义 

	我叫%1$s，我%2$s贼溜，我段位王者%3$d,不信可以%4$s一起玩!

2.在类中调用

    String.format(mActivity.getResources().getString(R.string.tips),"张三“，”吃鸡“，1,”晚上“))

输出结果就会拼接上。

输出结果：我叫张三，我吃鸡贼溜，我段位王者1,不信可以晚上一起玩!
```



`View.OnClickListener`

```java
View.OnClickListener onClickListener = new View.OnClickListener() {
        StringBuilder stringBuilder = new StringBuilder();
        @Override
        public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
            stringBuilder.append(1);
            binding.textViewCalResult.setText(stringBuilder.toString());
        }
        }
    };
binding.button1.setOnClickListener(onClickListener);
```



`StringBuilder`

```java
//创建实例
StringBuilder stringBuilder = new stringBuilder();
//访问实例中的方法
stringBuilder.xxx;
```



`NavigationUI`

```
//添加NavigationUI时， 定义NavController的作用是？
NavController controller;

//Navigation 是一个框架，用于在 Android 应用中的“目标”之间导航；
controller = Navigation.findNavController(this, R.id.fragment);

//Sets up the ActionBar returned by AppCompatActivity#getSupportActionBar() for use with a NavController.
NavigationUI.setupActionBarWithController(this, controller);

public boolean onNavigateUp(){
	//dialog part
	return super.onNavigateUp();
}
```



`AlertDialog` - 提示框

```java
AlertDialog.Builder builder = new alertDialog.Builder(this);
builder.setTitle{getString(R.string.xxx), new DialogInterface.OnClickListener()} {
    @Override
    public void onClick(DialogInterface dialog, int which){
        controller.navigateUp();
    }
}

//创建上面画好的dialog
AlertDialog dialog = builder.create();
dialog.show();

```



onNavigateUp() 和 onSupportNavigateUp() 的区别

- Handles the Up button by delegating its behavior to the given NavController. This is an alternative to using `NavController.navigateUp()` directly when the given `AppBarConfiguration` needs to be considered when determining what should happen when the Up button is pressed.

- Handles the Up button by delegating its behavior to the given NavController. This should generally be called from `AppCompatActivity.onSupportNavigateUp()`.

  If you do not have a `Openable` layout, you should call `NavController.navigateUp()` directly.

----

区别在于是否有a openable layout.



### 37.  LiftCycles



- chronometer

倒计时计数器

* LifeCycleObserver

通过类implements Lifecycle 来实现

* Static关键字

问题： 为什么页面旋转后， static关键字后存放的数据不会被丢失？



### 38. Room

该库可帮助您在运行应用的设备上创建应用数据的缓存。此缓存充当应用的单一可信来源，使用户能够在应用中查看关键信息的一致副本，无论用户是否具有互联网连接。



主键：



- Entity

实体 - 例如将Excel表看成是数据库，表中的每一行都是一个Entity。 Entity是对数据内容的定义

```
@Entity
public class Word{
	@PrimaryKey(autoGenerate =ture)
	private int = id;
	
	@ColumInfo(name = "english_word")
	private String word;
	
	@ColumInfo(name = "chinese_meaning")
	private string chineseMeaning;
	
	//生成word、chineseMeaning的constructer
    
    //生成word、chineseMeaning和id的getter和setter
}
```





* Dao

访问数据库的接口（DataBase Access Object）

如需使用 [Room 持久性库](https://developer.android.google.cn/training/data-storage/room?hl=zh_cn)访问应用的数据，您可以使用数据访问对象 (DAO)。这些 [`Dao`](https://developer.android.google.cn/reference/androidx/room/Dao?hl=zh_cn) 对象构成了 Room 的主要组件，因为每个 DAO 都包含一些方法，这些方法提供对应用数据库的抽象访问权限。

通过使用 DAO 类（而不是查询构建器或直接查询）访问数据库，您可以拆分数据库架构的不同组件。此外，借助 DAO，您可以在[测试应用](https://developer.android.google.cn/training/data-storage/room/testing-db?hl=zh_cn)时轻松模拟数据库访问。

数据库的增删查改操作

```java
@Dao
public interface WordDao{
    //增
    @Insert
    void insertWords(word... words);
    
    //改
    @Update
    void updateWords(word... words);
    
    //删
    @Delete
    void deleteword (word... words);

    //查 - 删除表中的全部信息
    @Query("DELETE FROM WORD")
    void deleteallwords();
    
    //查 - 查询表中的信息并返回
    //以ID为根据降序排序
    @Query("SELECT * FROM WORD ORDER BY ID DESC" )
    List<Word> getallwords()
```





* DataBase

  创建示例

```java
//继承父类Room database
//定义为抽象，因为实现的功能不用我们自己写，编译器会帮我们写。

@database(entities = (Word.class), version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase{
	
	//如果有多个Entity，需要写多个Dao来操作
	public abstract WordDao getWordDao();
}



```



​	更新示例 - 向数据表中插入一列

```java
.addMigration(MIGRATION_2_3)
    
static final Migration MIGRATION_2_3 = new Migration(2,3){
    @Override
    public void migration(@NotNull SupportSQLitedatabase database){
        database.execSQL("ALTER TABLB table-name ADD COLUMN data INTEGER NOT NULL DEFAULT 1")
    }
}
```



​	更新示例 - 删除数据表中的列信息

```java
 static final Migration MIGRATION_3_4 = new Migration(3,4){
     @Override
     public void migration(@NotNull SupportSQLitedatabase database){
         database.exeSQL("CREATE TABLE table-name-temp(id INTEGER PRIMARY KEY NOT NULL, english_word TEXT, chinese_meaning TEXT)");
         database.exeSQL("INSERT INFO table-name-temp (id, english_word, chinese_meaning) INSERT id,english_word, chinese_meaning FROM table-name");
         database.exeSQL("DROP TABLE talble-name");
         database.exeSQL("ALTER TABLE table-name-temp to table-name");
     }
 }
```





* Repository

  数据存&读取放到仓库类里





### 39. Adapter

特性： Input （View單元） ≠ OutPut （全景View）

An Adapter object acts as a bridge between an `AdapterView` and the underlying data for that view. The Adapter provides access to the data items. The Adapter is also responsible for making a `View` for each item in the data set.

​	a. Adapter 是充当view（Adapter View的展示页）的底层数据 （AdapterView上展示的数据）与AdapterView（是一个ViewGroup）之间的桥梁（搭路）。

​	b. Adapter提供数据项（AdapterView上展示的数据）的访问权限

​	c. Adapter负责为数据集合中的每一项制作View



* **Recycler View**

  对应的Adapter：RecyclerView.Adapter

  RecyclerView.Adapter: 列表中的视图由“视图持有者”对象表示,每个视图持有者负责显示一个带有视图的项.

  

  使用步骤：

  a. 添加dependency支持库;

  ```java
  dependencies {
          implementation 'com.android.support:recyclerview-v7:28.0.0'
      }
  ```

  b. 将 `RecyclerView` 添加到布局文件中;

  ```java
   <?xml version="1.0" encoding="utf-8"?>
      <!-- A RecyclerView with some commonly used attributes -->
      <android.support.v7.widget.RecyclerView
          android:id="@+id/my_recycler_view"
          android:scrollbars="vertical"
          android:layout_width="match_parent"
          android:layout_height="match_parent"/>
  ```

  c. 在布局中添加了 `RecyclerView` 微件之后，获取对象句柄，将其连接到布局管理器，并为要显示的数据附加适配器：

  ```java
  public class MyActivity extends Activity {
          private RecyclerView recyclerView;
          private RecyclerView.Adapter mAdapter;
          private RecyclerView.LayoutManager layoutManager;
  
          @Override
          protected void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.my_activity);
              recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
  
              // use this setting to improve performance if you know that changes
              // in content do not change the layout size of the RecyclerView
              recyclerView.setHasFixedSize(true);
  
              // use a linear layout manager
              layoutManager = new LinearLayoutManager(this);
              recyclerView.setLayoutManager(layoutManager);
  ·
              mAdapter = new MyAdapter(myDataset);
              recyclerView.setAdapter(mAdapter);
          }
          // ...
      }
  ```
  

d. 要将所有数据输入列表中，您必须扩展 `RecyclerView.Adapter` 类。此对象会创建项的视图，并在原始项不再可见时用新数据项替换部分视图的内容:

```java
  public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
          private String[] mDataset;
  
          // Provide a reference to the views for each data item
          // Complex data items may need more than one view per item, and
          // you provide access to all the views for a data item in a view holder
          public static class MyViewHolder extends RecyclerView.ViewHolder {
              // each data item is just a string in this case
              public TextView textView;
              public MyViewHolder(TextView v) {
                  super(v);
                  textView = v;
              }
          }
  
          // Provide a suitable constructor (depends on the kind of dataset)
          public MyAdapter(String[] myDataset) {
              mDataset = myDataset;
          }
  
          // Create new views (invoked by the layout manager)
          @Override
          public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
              // create a new view
              TextView v = (TextView) LayoutInflater.from(parent.getContext())
                      .inflate(R.layout.my_text_view, parent, false);
              ...
              MyViewHolder vh = new MyViewHolder(v);
              return vh;
          }
  
          // Replace the contents of a view (invoked by the layout manager)
          @Override
          public void onBindViewHolder(MyViewHolder holder, int position) {
              // - get element from your dataset at this position
              // - replace the contents of the view with that element
              holder.textView.setText(mDataset[position]);
  
          }
  
          // Return the size of your dataset (invoked by the layout manager)
          @Override
          public int getItemCount() {
              return mDataset.length;
          }
      }
```



布局管理器会调用适配器的 `onCreateViewHolder()` 方法。该方法需要构造一个 `RecyclerView.ViewHolder` 并设置用于显示其内容的视图。ViewHolder 的类型必须与 Adapter 类签名中声明的类型一致。通常，它会通过扩充 XML 布局文件来设置视图。由于视图持有者尚未分配到任何特定数据，因此该方法实际上不会设置视图的内容。

  布局管理器随后会将视图持有者绑定到相应数据。具体操作是调用适配器的 `onBindViewHolder()` 方法并将视图持有者的位置传入 `RecyclerView`。`onBindViewHolder()` 方法需要获取适当的数据，并使用它填充视图持有者的布局。例如，如果 `RecyclerView` 显示名称列表，该方法可能会在列表中找到适当的名称，并填充视图持有者的 `TextView` 微件。

  如果列表需要更新，请对 `RecyclerView.Adapter` 对象调用通知方法，例如 `notifyItemChanged()`。然后，布局管理器会重新绑定任何受影响的视图持有者，使其数据得到更新。

https://www.cnblogs.com/huolan/p/5126794.html



### 40. List



### 41. 设计模式之Signleton

以Signleton的格式封装，从代码上保证某些类只有一个实例存在；（当实例化对象比较耗资源时，建议采用单例的设计模式）

JVM保证单例思維 - JVM确保在加载内部类时只会被加载一次；

- 应用场景

  例如各种各样的Manager

  各种各样的Factory

  

写法1：饿汉式，类加载时就马上实例化了。

```java
//创建
public class Mgr01{
    //创建一个静态的实例
    private static final Mgr01 INSTANCE = new Mgr01();
    //私有化构造方法
    private Mgr01() {};
    //以getInstance的方法返回Mgr01(),无论调用多少次，拿到得都是同一个INSTANCE Mgr01();
    public static Mgr01 getInstance(){ return INSTANCE;}
}

//访问
Mgr01 m1 = Mgr01.getInstance();
```





写法2： 静态内部类方式

加载外部类时不会加载内部类。(写法1的优化)

```java
public class Mgr07{
	private Mgr07(){}
    
    //不调用时不会被加载
    private static class Mgr07Holder{
        private final static Mgr07 INSTANCE = new Mgr07();
        
    }
    
    public static Mgr07 getInstance(){
        return Mgr01Holder.INSTANCE;
    }
}
```



写法3： 枚举类 - 反序列化不成枚举



```java
public enum Mar08 {
    INSTANCE;
   
    public void = (){}
    
    public static void main(String[] args){
        for(int i=0; i<100; i++){
            new Thread(()->{
                System.out.println(Mgr08.INSTANCE.hashcode());
            }).start();
        }
    } 
}
```





### 42.设计模式之策略模式

- Comparator接口

  

https://github.com/android/views-widgets-samples



### 43. volatile关键字的字节码



### 44. Intent

* 基础用法

  ```java
  //实例化Intent时传入Action，对应Intent想操作的数据类型
  Intent intent = new Intent(Intent.ACTION_XXX);
  //将需要Intent处理的数据传给Intent
  intent.setData(data);
  //将intent传给需要新开的activity
  startActivity(intent);
  ```




### 45. 回调函数

当程序跑起来时，一般情况下，应用程序（application program）会时常通过API调用库里所预先备好的函数。但是有些库函数（library function）却要求应用先传给它一个函数，好在合适的时候调用，以完成目标任务。这个被传入的、后又被调用的函数就称为**回调函数**（callback function）。

打个比方，有一家旅馆提供叫醒服务，但是要求旅客自己决定叫醒的方法。可以是打客房电话，也可以是派服务员去敲门，睡得死怕耽误事的，还可以要求往自己头上浇盆水。这里，“叫醒”这个行为是旅馆提供的，相当于库函数，但是叫醒的方式是由旅客决定并告诉旅馆的，也就是回调函数。而旅客告诉旅馆怎么叫醒自己的动作，也就是把回调函数传入库函数的动作，称为**登记回调函数**（to register a callback function）。



### 46.  ItemTouchHelper

https://developer.android.google.cn/reference/androidx/recyclerview/widget/ItemTouchHelper#public-constructors

https://www.jianshu.com/p/2ae483118c8e

* 创建ItemTouchStatus接口

```java
public interface ItemTouchStatus {

    boolean onItemMove(int fromPosition, int toPosition);

    boolean onItemRemove(int position);
}
```

* 创建类继承ItemTouchHelper.Callback

```java
public class CustomItemTouchCallback extends ItemTouchHelper.Callback{
	
    // 定义ItemTouchStatus常量，作用：记录ItemMove和ItemRemove的position
    private final ItemTouchStatus mItemTouchStatus;
    
    // 构造方法，关联ItemTouchStatus接口
    public CustomItemTouchCallback(ItemTouchStatus itemTouchStatus) {
        mItemTouchStatus = itemTouchStatus;
    }
    
    //实现回调函数 getMovementFlag、onMove和onSwiped
 	@Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // 上下拖动
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        // 向左滑动
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }
    
    //如果item离开位置后就一直返回True，否则没有返回值。
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // 交换在数据源中相应数据源的位置
        return mItemTouchStatus.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }
    
    
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        // 从数据源中移除相应的数据
        mItemTouchStatus.onItemRemove(viewHolder.getAdapterPosition());
    }
    
}
```

* 在ViewAdapter中实现ItemTouchStatus接口的OnItemMove和OnItemRemove接口

```java
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> implements ItemTouchStatus {
    
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //交换list集合中任意两个位置的元素
        Collections.swap(mDataList, fromPosition, toPosition);
        //通知UI刷新视图
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public boolean onItemRemove(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
        return true;
    }
}

```

* 在Main Activity中新建ItemTouchHelper对象，并绑定给recyclerview；

```java
ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new CustomItemTouchCallback(mAdapter));
itemTouchHelper.attachToRecyclerView(mRecyclerView);
```



### 47. AsynTask



### 48. Context



### 49. JetPack

JetPack主要包括4个方面：架构（Arichitecture）、界面（UI）、行为（Behavior）、基础（Foundation）

* 架构部分包括：DataBinding、Lifecycles、LiveData、Navigation、paging、Room、ViewModel、WorkManager

* 界面部分包括：Animation&Transitions、Auto,TV&Wear、Emoji、Fragment、Layout、Palette
* 基础部分包括： AppCompat、 Android KTX、 Multidex、Test
* 行为部分包括： Download Manager、Media&Playback、Permission、Notification、Sharing、Slices



### 50. LifeCycles

基于解耦的设计思路，我们希望普通组件（widget）不依赖于页面生命周期的回调方法也能及时收到Activity生	命周期变化的通知。为此Google提供LiftCycle作为解决方案。此外，LifeCycle在Service和Appllcation中也能大	显身手。



* LifeCycle解决问题的原理

  jetpack提供了两个类： LifecycleOwner（被观察者）、LifecycleObserver（观察者）。即通过观察者模式，实现对页面生命周期的监听。对应源码：

  ```java
  public class SupportActivity extends Activity implements LifecycleOwner, Component{
     ...
     private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
     
     public SupportActivity(){}
     
     //得到一个LifecycleRegistry对象
     public Lifecycle getLifecycle() {
         return this.mLifecycleRegistry;
     }
  }
  ```

* Lifecycle解决方案 

  a. 编写自定义组件类，实现LifecycleObserver接口。对组件中那些需要再页面生命周期发生变化时得到通知的方法，我们需要在这些方法上使用@ OnLifecycleEvent（Liftcycle.Event.ON_xxx）标签进行标识。这样当页面生命周期发生变化时，这些被标识过的方法便后被自动调用。

  **通过这种方式将Activity的生命周期同步给普通组件**
  
  ```java
  public class MyLocationListener implements LifecycleObserver{
      public MyLocationListener(Activity context, OnLocationChangedListener onLocationChangedListener){
        //初始化操作
          iniLocationManager();
      }
  }
  
  //当Activity执行onPause（）方法时，该方法会被自动调用
  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  public void stopGetLocation(){
      Log.d(TAG, "stopGetLocation")
  }
  
  //当地理位置发生变化时，通过该接口通知调用者
  public interface OnLocationChangeListener{
      void onChanged(double latitude, double longitude);
  }
  ```
  
  **然后在Activity中通过getLifecycle().addObserver()方法将观察者和被观察者绑定**
  
  ```java
  public class MainActivity extends AppCompatActivity{
      private MyLocationListener myLocationListener;
      
      @Override
      protected void onCreate(Bundle savedInstanceState){
          myLocationListener = new MyLocationListener(this, new MyLocationListener.OnLocationchangedListener(){
             @Override
             public void onChanged(double latitude, double longitude){
                 //展示收到的位置信息
             }
          });
      }
      //将观察者与被观察者绑定
      getLifecycle().addObserver(myLocationListener);
  }
  
  ```
  
  

### 51. LifeCycleService

拥有生命周期概念的组件除了Activity和Fragment，还有两个个非常重要的组件， 它就是Service 和 Application。

* Lifecycleservice使用方法

  a. 在app的build gradle文件中添加相关依赖。

  b. 创建类继承LifecycleService类

  ```java
  public class Myservice extends LifecycleService{
      private MyServiceObserver myServiceObserver;
      
      public Myservice(){
          myServiceObserver = new MyserviceOberver();
          
          //将观察者与被观察者绑定
          //在有生命周期的类页面调用并填入没有生命周期的类
          getLiftcycle().addObserver(myServiceObserver);
      }
  }
  ```

  c. 接着在MyServiceObserver类中实现LifecycleObserver接口，使用OnLifecycleEvent标签将service的生命周期同步给MyServiceObserver类

  ```java
  public class MyServiceObserver implements LifecycleObserver{
      private String TAG = this.getCLass().getName();
    
      //当Service的onCreate方法被调用时，该方法会被调用
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
      private void startGetLocation(){
          Log.d(TAG, "startGetLocation()");
      }
      
      //当Service的onDestory()方法被调用时，该方法会被调用
      @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY){
          private void stopGetLocation(){
              Log.d(TAG, "stopGetLocation()")
          }
      }
  }
  ```
  
  
  
  ### 52. ProcessLifecycleOwner
  
  a. 将观察者与被观察者绑定
  
  ```java
  public class MyApplication extends Application
  {
      @Override
      public void onCreate(){
          super.onCreate();
          ProcessLifecycleOwner.get.getLifecycle()
              					.addObserver(new ApplicationObserver());
          
      }
  }
  ```
  
  b. 监听类实现LifecycleObserver接口，监听Application的生命周期
  
  ```java
  public class ApplicationObserver implements LifecycleObserver{
      private String TAG = this.getClass().getName();
      
      //在应用程序的整个生命周期中只会被调用一次
      @OnLifecycleEvent(Lifecycle.Event.ON_START)
      public void onStart(){
          Log.d(TAG, "Lifecycle.Event.ON_START")
      }
      
      //当程序在前台出现时被调用
      @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
      
      //当程序退出到后台时被调用
      @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
      
      //当程序退出到后台时被调用
      @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
      
      //永远不会被调用，系统不会分发调用ON_DESTROY事件
      @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  }
  ```
  
  从以上案例可以看出，ProcessLifecycleOwner是针对整个应用程序（App）的监听，与Activity数量无关。



### 52. 深层链接DeepLink

定义：DeepLink是Navigation组件的特性

作用：通过DeepLink特性，开发者可以利用PendingIntent或一个真实的URL链接，直接跳转到应用程序中的某个页面（Activity/Fragment）

* PendingIntent

  当应用程序接收到某个通知推送，用户在点击通知后，可以通过PendingIntent来跳转到展示改通知内容的页面。

  a. 通过sendNotification（）方法想通知栏发送一条通知，模拟用户收到一条推送的情况；

  ```java
  private void sendNotification(){
      if(getActivity == null){
          return
      }
      if (Bundle.VERSION.SDK.INT >= Build.VERSION_CODES.O){
          int importance = NtificationManager.IMPORTANCE_DEFEAULT;
          NotificationChannel channel = new NotificationChannel(
          					CHANNEL_ID, "ChannelName", importance);
          Channel.setDescription("description");
          NotificationManager notificationManager = 
              getActivity().getSystemService(NotificationManager.class);
          notificationManager.createNotificationChannel(channel);
      }
  }
  
  NotificationCompat.Builder builder = new NotificationCompat
      	.Builder(getActivity(), CHANNEL_ID)
      	.setSmallIcon(R.drawable.ic_launcher_foreground)
      	.setContentTitle("DeepLinkDemo")
      	.setContentText("Hello World")
      	.setPriority(NotificationCompat.PRIORITY_DEFAULT)
      	.setContentIntent(getPendingIntent()) //设置PendingIntent
      	.setAutoCancel(true);
  
  NotificationManagerCompat notificationManager = 
      				NotificationManagerCompat.from(getActivity());
  notificationManager.notify(notificationId, builder.build());
  ```

  

* URL的方式

  当用户通过手机浏览器浏览网站上的某个页面时，可以在网页上放置一个类似于 “在应用内打开” 的按钮。如果用户的手机安装有对应应用程序，那么通过DeepLink就能打开相应的页面；如果没有安装，那么网站可以导航到应用程序的下载页面，从而引导用户安装应用程序。



### 53. Notification



* a. NotificationManager

  Class to notify the user of events that happen. This is how you tell the user that something has happened in the background.

  ```java
  //NotificationManager对象的创建 
  NotificationManager notificationManager = 
              getActivity().getSystemService(NotificationManager.class);
  ```

* b. 新建Notification对象

  ```java
  //使用Builder构造器来创建Notification对象
  //NotificationCompat类来兼容各个版本
  //构建Notification的内容
  Notification notification = new NotificationCompat
      	.Builder(MainActivity.this, channelId).build();
  		.setContentTitle("这是测试通知标题")  //设置标题
  .setContentText("这是测试通知内容") //设置内容
  .setWhen(System.currentTimeMillis())  //设置时间
  .setSmallIcon(R.mipmap.ic_launcher)  //设置小图标
  .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))   //设置大图标
  ```

  c. 调用notify()让通知显示出来（第一个参数是ID， 保证每个通知所指定的id都是不同的，第二个参数是notification对象）
  
  ```java
  manager.notify(id, notification);
  ```
  



# Java 基础知识

尚硅谷Java课程 - 目前学习的版本是Java 8

Java 9 时尝试吧JavaSE，JavaEE，JavaME合起来

Java语言提供类、接口和继承等原语，为了简单起见，只支持类之间的单继承，但支持接口的多继承、并支持类与接口之间的实现机制

Java语言是分布式的？

Java语言是安全的 - Java安全机制（类ClassLoader），如分配不同的名字空间以防替代本地的同名类、字节代码检查。

----

JDK： Java Developer Kit 

JRE： Java Runtime Environment - 包括JVM 和 Java程序所需的核心类库

编译格式 - javac 源文件名.java; 

java 类名.class (class文件是.java文件编译后得到的字节码文件，类名是.java文件里的main方法类)

一份.java文件只能有一个public类；

如果一个.java文件有多个类，编译后就会生成多份.class文件

## 第一阶段： 基础程序设计

基础程序设计可以分为：关键字、数据类型、运算符、流程控制和数组 五大部分

类名接口名等，要求没一个单词的首字母大写。形式：XxxYyyZzz

包名：要求所有单词都小写，形式：xxx.yyy.zzz

常量名：要求所有单词大写，每个单词间用_分割。形式XXX_YYY_ZZZ

### 变量

本质上是代表一块内存区域，数据是存储在JVM内存中

三要素：a. 数据类型； b. 变量名； c. 值

如何声明一个变量： 数据类型 数据名；（需要先声明，后使用）

如何给变量赋值： 变量名 = 变量值；

### 关键字



break: 用于结束所有循环结构的循环； 结论：结束一个循环有两种情况 a. 循环条件不满足； b. 遇到了break中断



continue: 只用于循环结构当中，作用是提前结束本次循环，继续下一次循环；



## 2.1 数据类型

### 2.1.1基本数据类型

**整形系列** 

byte - 1个字节 -128 到 127

short - 短整形， 2个字节 -32768 到 32767

int - 整形， 4个字节

long - 长整形， 8个字节



**小数类型**

float - 单精度浮点型，4个字节

double - 双精度浮点型，8个字节

* 浮点型的float和double在底层如何存储？ 因为计算机中只有二进制

  化繁为简 - 只要存 （1）正负号， （2）次方信息， （3）二进制的小数部分

  所以浮点类型的数据是不精确的，存不下的小数部分会被舍去。

  但float类型的4个字节能表示的数字范围比long类型的8个字节还要大，因为整数部分底层是通过指数来存储的。

  



**字符类型**  - char

转义字符格式: ' \ + 内容 ' 例如 n = 换行； b = 删除键back； t = 制表符； r=回车键；



**布尔类型** - boolean



* 引用数据类型



### 数据类型转换

* 自动类型转换

当从小容量数据类型转为大容量数据类型时，系统会自动完成转换

byte - short - int - long - float - double

char - int - long - float - double

* 强制类型转换

把存储范围大的类型的值赋值给存储范围小的类型变量时，需要强制类型转换



### 2.2.2 引用数据类型

类、接口、数组、枚举



### 运算符

a. 算术运算符 + - * /  %(取余) - 取余时只看除数的符号

b. 自增 ++ 与 自减 -- 

```java
//先取出P的值，放到一个操作数栈，然后完成i的自增，最后将数栈中的值赋值给j。
i++
int j = i++

// i先自增，再将i的值赋给j
int j = ++i
```



c. 赋值运算符 = ， = 号左边只能是一个变量；

扩展的赋值运算符： +=， -=， *=， /=, %=. (运算顺序：把右边的整个表达式先算完，才做最后的赋值操作)

```java
byte b1 = 1;
byte b2 =2;
b2 += b1; // 等价于 b2 = （byte）（b2+b1）

/*
第一步：先取J的值
第二步：i++
第三步：求和
第四步：乘
第五步：赋值，把乘积赋值给j
*/

int i = 1;
int j = 5;
j *= i++ + j; // i = 2, j = 30 ， 等式等价于 j = j * (i++ + j)

```

d. 比较运算符： >, <, >=, <=, ==, != instanceof 比较运算符的运算结果是boolean值

e. 逻辑运算符：逻辑与：&， 逻辑或|，逻辑非！，逻辑异或^， 短路与&&， 短路或||

```java
// & = 且， 两边true + true，运算结果才为true

// | = 或， 任意一边结果为true，运算结果就是true

// ！ = 非， 运算结果取反

// ^ 异或， 运算结果求不同， 比较两边结果的布尔值是否相同，相同的话是false，不同的话是true

// && 短语与，如果左边是false，右边就不运算了。其余效果跟&相同

// || 短路或， 如果左边是true，右边就不运算了。其余效果跟|相同
```

f. 条件运算符： 唯一的三元运算符

条件表达式 ？ 结果表达式1 ： 结果表达式2

如果条件表达式为true，运行结果表达式1， 否则运行结果表达式2.

g. 位运算符 - 左移：<<， 右移>>， 无符号右移>>>，  按位与&，  按位或|，  按位异或^， 按位取反~. 位运算符是基于二进制每一位的运算。

```java
/*运算规则： <<几位，就乘以2的几次方 -> 4 << 3 = 4*2的3次方，结果是32
右移几位，就除以2的几次方；
无符号右移 - 
&，|，^运算后，运算结果是1或者0
~
*/
```



## 2.3 流程控制

循环语句的三大结构 - 顺序结构、 分支结构、 循环结构。



### 2.3.1 顺序结构

（1） 准备一个数据的扫描仪（对象）

`java.util.Scanner input = new java.util.Scanner(System.in);`



### 2.3.2 分支结构 - if.. esle & switch.. case

判断字符串信息是否相等时，需要用方法 目标字符.equals(待比较字符变量)来判断；

a. 条件判断： if, if..else , if..else if..else (注意else if 条件语句的else 和 if之间是有空格的)

b. 选择结构： switch...case

```java
switch(表达式 或 变量){
	 case 常量值1：
         语句块1；
     case 常量值2：
         语句块2；
     ...
     //default是当常量值是case以外的情况时，运行语句块n+1
     default:
         语句块n+1；
         
}
```

执行特点： （1）入口： 当switch（）中的表达式值与某一个case后面的常量值“相等”，就会从这个case进入。一旦找到入口，就会顺序往下执行，直到遇到出口。（2）出口: 自然出口 - switch接口花括号， 中断出口 - break;

switch（）中的表达式的类型有要求： a. 允许4种基本数据类型： byte，short，int，char； b. 允许两种引用数据类型： 枚举、String



### 2.3.3 循环结构 - For & While &do.. while & foreach

* for 循环

for( 循环变量初始表达式; 循环条件表达式; 循环变量迭代表达式){

​	需要重复执行的语句。

}



* while循环 （先判断，后循环）

while (循环条件){

​	循环体语句；

​	迭代语句；

}

（1）先判断循环条件

（2）如果循环条件成立，那么就执行循环体语句。再回到（1）



**总结：** for 和 while的区别



* do.. while （先循环后判断）

do{

​	循环体语句块： 需要重复的代码

}while(循环条件);



* foreach循环

###  

  

### 2.4 数组 - Array

相同数据类型的	有限个	元素，按一定顺序排列的集合。以便统一管理他们，再用编号加以区分。

这个编号称为下标或索引、 组成数组的各个变量称为数组的元素、数组中的元素称为数组的长度。

**下标（Index）范围：[0， 长度-1]**

 **数组的长度： 数组名.length**

```java
int[] score = new int[7] //用score这个统一的名称，来管理7个int类型的元素；
score[0] = 98;
score[1] = 99;
...
score[6] = 90;
```



* 数组的定义和初始化、使用的方式二：

  a. 数组的声明不变

  b. 数组的初始化 - 静态初始化 & 动态初始化；

  ​	动态初始化： 数组类型 数组名 = new 数组类型[元素个数] 

  ​							`int[] score = new int[quanlity];`

  ​	如果没有手动赋值之前，数组的元素有默认值，当元素是基本数据类型时：

  ​		byte, short, int, long: 0

  ​		float, double: 0.0

  ​		char: \u0000

  ​		boolean: false

  ​	当元素是引用数据类型时： null;

  ​	如 letters = new char[26];

* 遍历数组 (用于数组赋值 & 查询数组的全部内容)

  for(int i = 0; i<Array.length; i++){

  ​	System.out.println(Array[i]);

  }

* 排序算法

  a. 在数组中找最大值/最小值

  ​	（1） 先假设第一个符合条件，然后与后面的元素一一比较，如果有更符合条件的，就更新。

  ​		`int max = array[0];`

  ​	（2） 一一比较

  ​		for (int i = 1; i< array.length; i++){

  ​			if(array[i] > max){

  ​				max = array[i]

  ​			}

  ​		}

  

* b. 求数组元素的累加和 及 平均值

  sum += array[i];

  sum / array.length;

  

* c. 数组的反转

  方式一： 定义一个与原来的数组长度相同的新数组，然后逆序复制元素，最后让新数组指向旧数组； `old_Array = new_Array` （缺点，浪费空间）

  方式二： 首尾交换 数组长度/2 的次数。借助第三方temp来交换；



* 数组的复制

  （1） 复制一个和原来一样的数组，长度和元素都一样

  （2） 复制一个比原来数组短的

  （3） 复制一个比原来数组长的

  

* 数组的查找

  两种情况： （1） 数组中的元素是无序的； （2） 数组中的元素是有序的；

  针对（1）的情况，用顺序查找， 针对（2）的情况，用二分法查找；



* 数组的排序

  （1） 冒泡排序：	通过相邻元素比较，如果相邻元素的顺序不符合要求，经过length - 1轮之后，实现最终的排序。每一轮排序仅能保证一个元素到达正确的位置。

  （2）直接选择排序

  
  
* 数组的维度

  二维数组：有行有列的表格，二维表

  声明数组语法格式：元素的数据类型[] [] 数组名;

  初始化二维数组： （1）确定行数； （2）确定每一行的列数； （3）确定元素的值；

  a. 静态初始化：

  二维数组名 = new 数据类型[] [] {{首行的值列表}， {第二行的值列表}， {第n行的值列表}};

  b. 动态初始化

  行数 - 把二维数组看成一个一维数组， 把一行看成一个元素 ： 二维数组名.length

  行下标的范围：（0， 二维数组总行数 - 1）；

  列数 - 二维数组名[行下标].length

  c. 如何表示某行某列的一个元素 - 二维数组名[行下标] [列下标]；

  d. 遍历代码：

  for ( int i = 0; i<行数; i++){

  ​	for( int j=0; j<该行的列数; j++){

  ​		System.out.print(二维数组名[i] [j])	

  ​	}

  }



* 数据结构



## 第二阶段： 面向对象编程

面向对象编程的五大概念： a. 类/对象； b. 类的结构； c. 三大特性； d. 接口； e. 设计模式

面向對象的基本特征：  封裝、繼承、多態

面向對象的特征：  封裝、繼承、多態、抽象

程序的最小单位是类/对象；

程序员关注的角度： 把复杂的业务逻辑/功能中先抽取出类，然后再考虑这个类你包含什么属性与功能

 类名：从第一个单词的首字母开始大写；

对象名/变量名： 从第二个单词的首字母开始大写；

類變量（常量），不用創建對象，直接用類名即可訪問。因為這類數值不會因對象的不同而變化，通常static 和 final會成對出現。



## 3.1 类/对象

一句描述中，一般名词是类，动词是类的具体行为，由对象来实现具体行为。

类的提取： 名词提取法；

类的设计： 类的成员的设计；



### 3.1.1 类的结构

类的五大成员：（1） 属性：数据特征、（2）方法：行为特征、功能特征（3）构造器：创建对象时用、（4）代码块：在类初始化和对象初始化时用的、（5）内部类：在某个大的事物中，包含了一个独立的小的事物类别，而且这个小的事物只为大的事物服务时；

```java
class Body{
    //属性
    double weight;
    char gender;
    
    //方法
    void eat(){}
    void sleep(){}
    
    //构造器
    Body(){
        
    }
    
    Body(double weight, char gender){
        this,weight = weight;
        this.gender = gender;
    }
    
    //代码块
    {
        System.out.println("非静态代码块");
    }
    
    static{
        System.out.println("静态代码块");
    }
    
    //内部类
    class Heart{
        public void beat(){
            System.out.println("砰砰跳");
        }
    }
    
}
```



属性的声明： 【修饰符】 数据类型 属性名；

属性的特点【成员变量】： （1） 属性有默认值，跟数组元素的默认值规则一样； （2）每一个对象的属性值是独立的；

创建对象：·Person p = new Person();

为对象的属性赋值： 对象名.属性名 = 值；【否则新对象没有任何属性】

方法： 代表一个独立的可复用的功能；声明的好处：

（1）代码的复用

（2）简化程序员的代码量，使用者不用关心方法内部如何实现，只要关注方法的功能、是否需要传参和是否有返回值等问题；

如何声明方法： 【修饰符】 返回值类型 方法名（形参列表）{ 方法体 }



如何调用方法： 在本类中，同一级别的直接调用。

在其他类中，需要通过“对象名.方法”调用； （1）创建对象； （2）通过对象去调用方法；

方法的返回值需要被变量接受，否则无意义。



### 3.1.3 形参

形式参数，用于定义方法的时候使用的参数，是用来接收调用者传递的参数的。

形参只有在方法被调用的时候，虚拟机才会分配内存单元，在方法调用结束之后便会释放所分配的内存单元。
因此,形参只在方法内部有效，所以针对引用对象的改动也无法影响到方法外。



### 3.1.4 实参

实际参数，用于调用时传递给方法的参数。实参在传递给别的方法之前是要被预先赋值的。
在 `值传递`调用过程中，只能把实参传递给形参，而不能把形参的值反向作用到实参上。在函数调用过程中，形参的值发生改变，而实参的值不会发生改变。
而在 `引用传递`调用的机制中，实际上是将实参引用的地址传递给了形参，所以任何发生在形参上的改变也会发生在实参变量上。



### 3.1.5 方法的参数传递机制

* 实参 传递给 形参

  （1） 形参是基本数据类型；

  ​	实参赋值给形参的是数据值，形参值的修改不影响实参值。

  （2） 形参是引用数据类型；



### 3.1.6 方法的重载与重写

在同一个类中，出现了两个或多个方法名称相同，但形参列表不同，称为方法的重载。

形参列表不同：形参的个数、数据类型不同。



当子类继承了父类的方法时，子类可以选择进行方法体的重写；

方法 = 方法签名（方法头） + 方法体

重写的标志： @Override

哪些方法不能被重写： a. final修饰的； b. static修饰的； c. private修饰的；d. 如果是跨包方法的话，缺省修饰符的也不能被重写。

为什么static修饰的方法不能被重写？



### 3.1.7 可变参数

如果形参列表中出现了“数据类型...	形参名”形式的形参，就是可变参数。（JDK1.5以后引入）

* 包含可变参数的方法的调用原则：

a.  可变参数的形参部分，可以传入0~n个对应类型的实参，也可以传入对应类型的数组对象；

b.  非可变参数的形参部分，按标准方式传参；



* 可变参数的声明原则：

a. 一个方法只能有一个可变参数

b. 可变参数必须是形参列表的最后一个。

例子： 声明一个方法，功能：可以求0~n个整数的累加和

```java
public static int sum(int... nums){
    int sum = 0;
    for (int i = 0; i<nums.length; i++){
        sum += nums[i];
    }
    return  sum;
}
```



### 3.1.8 变量的分类

* 成员变量、类变量 与 局部变量

a. 声明的位置不同，成员变量声明在类中方法外，局部变量声明在方法内、方法（）中的形参列表、代码块中；

b. 运行时在内存中的位置也不同。 成员变量是在堆中（栈中存引用地址，指向堆中的存储值），局部变量是在栈中（分虚拟机栈和本地方法栈）；类变量是储存在方法区中。

c. 初始化不同，成员变量如果没有初始化，有默认值。局部变量如果没有初始化会报错。

d. 生命周期不同： 局部变量的生命周期结束于归属的方法执行完毕时； 成员变量：随着栈中的引用结束而被垃圾回收器回收。类变量：随着类加载分配空间，随着类卸载被垃圾回收而回收。

e. 可用的修饰符不同： 成员变量有很多修复符，后续补充； 局部变量唯一的修饰符是final；

* 局部变量

a. 形参； b. 方法体中声明的变量； c. 代码块中声明的变量；

类变量是所有对象共享的

成员变量是所有对象之间独立的





### 3.1.9 对象数组

格式： 对象名[]	数组名 = new 对象名 [数组容量];

给对象数组元素赋值： 



### 3.1.10 面向对象的基本特征 - 封装

作用： a. 隐藏实现细节； b. 安全；

范围： a. 属性的封装； b. 方法的封装； c. 组件的封装；d. 系统的封装

实现： 靠权限修饰符来控制可见的范围；

权限修饰符： a. private； b. 缺省； c. protected； d. public；

| 修饰符    | 本类 | 本包 | 其他包子类 | 任意位置 |
| --------- | ---- | ---- | ---------- | -------- |
| private   | OK   | x    | x          | x        |
| 缺省      | OK   | OK   | x          | x        |
| protected | OK   | OK   | OK         | x        |
| public    | OK   | OK   | OK         | OK       |

类、属性、方法、构造器、内部类都可以加修饰符；

类&内部类： 缺省或public，如果类前面有public，那么必须与源文件名相同

属性、方法：四种都可以



如果类中属性的封装私有化了，我们会提供getter/setter方法访问属性

setter是用于在其他类中修改属性的值，getter是用于在其他类中获取属性的值；



### 3.1.11 类的成员 - 构造器

作用： a. 构造对象，创造对象；（本质是方法， 功能是将类实例化） b. 可以在创建对象的同时，给属性赋值。

特点： a. 所有类都有构造器； b. 如果类没有显示声明构造器，编译器会自动生成一个默认的构造器； c. 如果一个类成明了显示构造器，编译器不会自动生成一个构造器；d. 构造器的名称必须与类名相同；e. 构造器没有返回值类型，也不写void；f. 构造器可以重载；

语法结构： a. 【修饰符】 类名（）{}

b. 【修饰符】 类名（形参列表）{}



### 3.1.12 包

* 作用： a. 延长类名， 避免类的重名问题； 包.类名 （如：java.util.Scanner[Scanner类的全称], java.lang.String[String类全称]）；

  b. 用于组织管理众多的类；

  c. 用于控制访问权限；



* 声明格式： package 包名；【一个源文件只能有一句 & 必须在源文件的代码首行】

* 命名规范：a. 所有单词小写，每个单词用.分割

  b. 习惯上用公司的域名倒置 + 模块名

  如果类声明包名后，类的全称就已经改变了。此时可以使用全称写构造器方法 或 使用 import语句，直接使用原类名称写构造器方法；



* 导包格式： import 包.类名； 【导入单个类】

  ​					import 包.*； 【导入包对应文件夹下的多个类】



 如果出现了不同包，但是类名相同时，只能使用一个，其他的使用全称；



* 编译格式： `javac -d filename.java`



### 3.1.13 关键字 - this

意思： a. 在构造器中使用时，表示正在被创建的对象； b. 在方法中使用时，表示正在调用该方法的对象

用法一：this.属性

当局部变量与成员变量同名时，可在成员变量的前面加this.

用法二：this.方法 - 没有非用不可的时候

用法三：this() 或 this(实参列表)

表示调用本类的无参构造。必须在构造器的首行 或调用本类的有参构造



### 3.1.14 继承

作用：

特点：a. 子类继承父类时，除private修饰符外的属性和方法都会被继承；

b. 子类继承父类时，构造器是不能被继承的；

c. 子类继承父类试，在子类的构造器中一定要去调用父类的构造器； - 默认情况下， 调用的是父类的无参构造器；如果父类没有无参构造，那么在子类的构造器首行，必须手动调用父类的有参构造；例如： `super(a, b);`

```java
public class TestInherited extends Father{
    public TestInherited(a, b){
        super (c, d);
        this.a = a;
        this.b = b;
    }
}
```

d. java只支持单继承，一个java类只能有一个直接父类；

e. java类支持多层继承，即父类还可以有父类；

f. 子类可以扩展父类没有的属性和方法。



### 3.1.15 关键字 super

作用：从父类中查找，引用父类的xx

用法：super.属性 - 当子类声明了和父类相同的属性时，使用这个方式访问父类的同名属性； 

super.方法； 

super（）  -  调用父类的无参构造

或super（实参列表）  -  调用父类的有参构造



### 3.1.16 类的成员 - 代码块 （为面试）

* 非静态代码块

（1）. 声明的格式： 【修饰符】 class 类名{

{ 非静态代码块 ；}}

（2）. 执行时机：a. 非静态代码块中的代码在 每次创建对象 时候都执行； b. 比构造器方法的运行时机还要早；

（3）. 实例初始化过程： a. 先执行成员变量的显示赋值； b. 再执行非静态代码块； c. 最后执行构造器方法；



### 3.1.17 面向对象的基本特征 - 多态 Polymorphism

变量的引用形式：（1）. 本态引用： 左边的变量 跟 右边的对象 类型相同；

（2）. 多态引用： 左边的变量是父类；右边的对象是子类；

多态引用的特征： （1）. 执行的方法是子类重写的方法； （2）. 编译时按父类对象编译，执行时按子类对象执行；（3）. 属性没有多态，只有方法有多态的说法；

用途： 方法的动态绑定；



* 多态的应用

（1）. 多态数组 ： 数组元素是父类类型对象数组，实际存储的是子类类型的对象；

（2）. 多态参数： 形参是父类的类型，实参是子类的对象；

### 3.1.18 Instanceof	运算符

语法格式： 变量 instanceof 对象

结果： true or false；

返回true的情况： 变量中储存的是 类的对象 或 子类的对象时

```java
public class TestInstanceof{
    public static void main(String[] args){
        Man m = new Man();
        Woman w = new Woman();
        test(m);
        test(w);
    }
}

public static void test(Person p){
    if ( p instanceof Woman){
        Woman m = (Woman) p; //思考&测试如果没有这句会有什么问题
        p.shop();
    }else{
        Man m = (Man) p;	//思考&测试如果没有这句会有什么问题
        p.smpke();
    }
}
```



### 3.1.19 关键字 - 修饰符 final

作用： a. 当final修饰类时，类就无法被继承，称为太监类；

b. 修饰方法： 表示这个方法不能被重写

c. 修饰变量，表示这个变量的值不能被修改，既常量； 常量的命名所有单词都要大写。



### 3.1.20 关键字 - 修饰符 native

用于修饰方法。

语法格式：

【native】class 类{

​		【其他修饰符】 native 返回值类型 方法名（【形参列表】）

}

**native修饰的方法，看不到方法体**

native修饰的方法，不是用java语言实现的，而是调用了底层c/c++的代码，这些代码被编译为.dll文件



### 3.1.21 关键字 - 修饰符 static	

用于修饰方法、成员变量、内部类、代码块

* 当用于修饰方法时， 这个方法在其他类中可以用“类名.方法”进行调用， 也可以用“对象名.方法”进行调用，我们推荐用“类名.方法”。我们称静态方法为类方法，因为该方法不属于对象，属于类。
* 当用于修饰成员变量时，该成员变量所属类中的所有对象共享该成员变量；该成员变量的值储存在方法区。我们也称静态变量为类变量，因为变量不属于对象，属于类。用“类名.变量”进行访问。
* 静态代码块在类初始化（加载）时执行；
* 静态方法不能被重写；《静态方法在编译时就确定了，不需要在运行时动态绑定。》

什么是类初始化？ 什么是实例初始化，他们运行的代码有什么区别？

```java
/**
(1) 由Son son = new Son(); 方法觸發，先執行Father類的初始化方法<clinit>()： a. 類靜態變量顯示賦值。 b. 執行靜態代碼塊；System.out.println("(1) 父類的靜態代碼塊");
(2) 接著執行Son類的類初始化方法<clinit>()： a. 類靜態變量顯示賦值； b. 執行靜態代碼塊；
(3) 執行Father類的實例初始化方法<init>(): a. 非靜態成員變量的顯示賦值； b. 非靜態代碼塊執行；c. 調用父類的無參構造； 
(4) 執行Son類的實例初始化方法<init>()： a. 非靜態成員變量的顯示賦值； b. 非靜態代碼塊執行；c. 調用子類的無參構造； 

運行結果排序：142356
*/

public class TestEcer1{
    public static void main(String[] args){
        Son son = new Son();
    }
}

class Father{
    static{
        System.out.println("(1) 父類的靜態代碼塊");
    }
    
    {
        System.out.println("(2) 父類的非靜態代碼塊");
    }
    Father(){
        System.out.println("(3) 父類的無參構造");
    }
}

class Son extends Father{
    static{
        System.out.println("(4) 子類的靜態代碼塊");
    }
    {
        System.out.println("(5) 子類的非靜態代碼塊");
    }
    Son(){
        System.out.println("(6) 子類的無參構造");
    }
}
```

示例二：

```java
/**
(1) 由Zi zi = new Zi(); 方法觸發，先執行Fu類的初始化方法<clinit>()： a. 類靜態變量顯示賦值。 b. 執行靜態代碼塊；
即先執行getNum("(1)i"); 然後執行print("3. FU"); -> (1),(3) int i = 2;
(2) 再執行子類的初始化方法<clinit>()： a. 類靜態變量顯示賦值。 b. 執行靜態代碼塊；即先執行getNum("6. k");再執行print("8");
-> (6)

*/
public class TestExer2{
    public static void main(String[] args){
        Zi zi = new Zi();
    }
}

class Fu{
    private static int i = getNum("(1)i");
    private int j = getNum("(2)j");
    static{
        print("3. FU");
    }
    {
        print("4. FU");
    }
    Fu(){
        print("5");
    }
    public static void print(String str){
        System.out.println(str + "->" + i);
    }
    public static int getNum(String str){
        print(str);
        return ++i;
    }
}

class Zi extends Fu{
    private static int k = getNum("6. k");
    private int h = getNum("7. h");
    static{
        print("8");
    }
    {
        print("9");
    }
    Zi(){
        print("10");
    }
    public static void print(String str){
        print(str);
        public static void print(String str){
            System.out.println(str + "->" + k);
        }
        return ++k;
    }
    public static int getNum(String str){
        print(str);
        return ++l;
    }
}
```



### 3.1.22 java.util.Arrays

学习如何阅读jdk api

https://docs.oracle.com/javase/8/docs/api/

Object： 是所有引用数据类型的根父类，根据多态，Object类型的变量，形参可以接收任意一類数据类型对象；

 

### 3.1.23 修飾符 - abstract

作用： 當一個類在聲明時，類中的某個方法的實現不明確時，要在具體的子類中才能給出明確的實現，這類方法稱為抽象方法。 抽象方法沒有方法體。

抽象方法的語法格式：【其他修飾符】 abstract 返回值類型 方法名（）；

抽象類：如果一個類中包含抽象方法，那麼這個類必須是抽象類；

抽象類語法格式：【其他修飾符】 abstract class 抽象類名{}

抽象類的特點：a. 不能直接創建對象； 

b. 如果子類繼承抽象類，必須重新父類所有的抽象方法。否則子類也定義為抽象類

c. 抽象類也有構造器，但是這個構造器是為子類創建對象服務的，不為自己創建對象服務。

d.抽象父類和子類可以構成多態引用。

e. 抽象類中也可能沒有抽象方法 - 存在意義： 在父類進行多態子類管理是，不希望創建父類的對象，只創建子類的對象；

```java
//例如需要再圖形父類中求圖形面積時，由於不同圖形的面積公式不同，所以父類中無法給出正確的實現方法。
class Graphic{
    public abstract double getArea();
}
```

abstract修饰符不能和哪些修饰符一起使用：

类： 1. 和final不能一起修饰类；

方法： 2. 和final不能一起修饰方法、 和static不能一起修饰、和native不能一起修饰（有歧义，因为都没有方法体）、和private不能一起修饰（private不能被重写？）； 





## 3.2 JVM内存结构





### 3.2.1 JVM体系结构

* 类加载器（Class Loader）

作用：加载Class文件。 

类型：App类加载器 - 扩展类（EXC）加载器 - 启动类（BOOT）加载器



* 双亲委派机制

  a. 类加载器收到类加载的请求；

  b. 将这个请求向上委托给父类加载器去完成，一直向上委托，直到启动类加载器

  c. 启动加载器检查是否能够加载当前这个类，能加载就结束，使用当前的加载器。 否则抛出异常，通知子加载器进行加载；

  d. 重复步骤3

  



* 运行时（Runtime）数据区

Method Area (方法区)、 Heap（堆）、JVM Stacks（虚拟机栈）、PC Register（程序计数器）、Native Method Stacks（本地方法栈）

* 执行引擎（Execution Engine）

解释器（Interpreter）、即时编译器（JIT Compiler）、垃圾回收（GC）



### 3.2.2 程序计数器

作用：保存当前执行指令的地址，一旦指令执行，程序计数器将更新到下一条指令；保证程序能正常地按顺序指令



### 3.2.3 虚拟机栈（stack）

每个线程运行时所需要的内存空间，称为虚拟机栈

每个栈由多个栈帧（Frame）组成，对应着每次方法调用时所占用的内存

每个线程只能有一个活动栈帧，对应着当前正在执行的那个方法。

* 栈帧（Frame）

组成： 局部变量表、操作数栈、动态链接、方法返回地址





### 三大特性



## 3.3 设计模式

解决问题的套路、代码结构；

java中常用的设计模式有23种。

### 3.3.1 模板设计模式





### 3.3.2 单例设计模式

重要程度：五颗星， 需要脱稿写

单例定义： 某个类只能有唯一的一个实例对象。

* 如何实现单例（饿汉式）

形式一：`enum SingleEnum { INSTANCE }`

形式二： 构造器私有化， 用一个全局的静态的常量，来保存这个唯一的实例对象；

形式三： 构造器私有化， 用一个私有的静态的常量，来保存这个唯一的实例对象；；提供一个静态方法，来返回这个常量对象



* 如何实现单例（懒汉式）

形式一：

形式二：

```java
class Lazy{
    
    private Lazy(){
        
    }
    //利用静态内部类的特性： a 调用后才会被加载； b 类变量可以直接被访问 c 可以直接访问外部类方法
    private static class Inner{
        public static final Lazy INSTANCE = new Lazy(); //在内部类中创建外部类的唯一对象
    }
    
    private static Lazy getInstance(){
        return Inner.INSTANCE;
    }
    
}
```





### 3.3.3 工厂设计模式

重要程度：两颗星，需要会调用工厂类的方法



### 3.3.4 代理设计模式

重要程度：三颗星，需要能模仿



### 3.3.5 迭代器设计模式



### 3.3.6 装饰者设计模式



### 3.3.7 根父类 - Object

java中规定，如果一个类没有显示声明它的父类，那么默认这个类的父类就是java.lang.Object

定义：Object类是类层次的根类，每个类都使用Object作为超类。

如何理解根父类： （1）所有对象（包括数组）都实现这个类的方法。即Object类中所有声明的方法，所有引用数据类型（包括）数组中都有。

（2） 所有类的对象的实例化过程，都会调用Object的实例初始化方法

（3）所有对象都可以赋值给Object的变量



Object类中常用的方法：

```java
// 用于返回对象的信息，建议所有的子类重写
// 该方法返回一个字符串，该字符串由类名+@+调用对象的哈希码的无符号16进制表示。即getClass().getName()+'@'+Integer.toHexString(hashCode());
public String toString();

/*
返回调用方法的运行时类型（多态的编译时类型跟运行时类型不同）
*/
public final Class<?> getClass();

/*
当垃圾回收期确定不存在该对象的更多引用时，由 对象的垃圾回收器 调用此方法。子类重新 finalize 方法，以配置系统资源或执行其他清除。
功能： 回收对象；
特点： 每个对象， finalize（）只能被调用一次。 调用时机不确定。 由GC调用。
*/
protected void finalize();

//finalize方法测试代码
public class TestMethod3{
    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <=10; i++) {
            MyClass my = new MyClass();
        }
        
        System.gc();
        Thread.sleep(1000);
    }
}

Class MyClass{
    
    @Override
    protected void finalize() throws Throwable{
        System.out.println("Collect the garbage object - my");
    }
}

//返回对象的哈希码值，支持此方法是为了提高哈希码的性能
//理想状态下，每一个对象都应该有一个唯一的哈希码值

public int hashCode();

// 判断两个对象最终是否相等，但是效率比hashCode比较低，所以先用hashCode做初步判断。
public boolean equals(Object obj);


```



 ## 3.4 接口

接口 - 类的行为标准。

作用：管理类

生活中的例子： USB接口

a. 多提供几个USB接口，可以让很多的设备和电脑连接。

b. 对于其他设备的厂商也简单了，只要你这个设备有与电脑连接的需求，那么就可以吧连接口设计为USB

例如：电源接口 

a. 插座的设计

b. 电器的设计



java中：

为了限定某些类（这些类之间可能没有继承关系， 甚至都不是一个派系里面的）， 但是这些类都具有相同的行为特征。例如： Bird类、Plane类、Kite类，这个时候就可以设计接口，这个时候通过这个接口就可以同时管理这些类。



### 3.4.1 如何声明接口

【权限修饰符】 interface 接口名{

​	接口的成员列表；

}



* 接口的成员

a. 全局的静态的常量； `public static final` 因为规定了是常量所以写不写前面修饰符都是等价的。

b. 公共的抽象方法： `public abstract` 而且这两个单词从上面来看也是可以省略的。

​	因为标准只规定行为有什么行为标准、行为特征。实现细节开发者说了算

```java
public class TestInterfaceDefineAndUse{
    
    
}

interface Flyable{
    
}
```



### 3.4.2 如何实现接口

语法格式：

【修饰符】 class 实现类 implements 接口们	{



}

* 接口的特点

  a. 接口是标准，就是用来被遵守和实现的；

  b. 同抽象类，接口也是不能直接创建对象的；

  c. 接口类型的变量与实现类的对象构成多态引用；

  d. 一个类支持实现多个接口；

  e. 一个类如果继承类&实现接口时，那么继承在前，实现在后；

  f. 在java中，接口还可以继承接口；

```java
interface A{
    void a();
}

interface B{
    void b();
}

interface C extends A,B{
    void c();
}
```



### 3.4.3 java中常用的接口

* java.util.Comparator<T> 接口  - 定制比较，定制顺序

  抽象方法：int compare（T o1， T o2）

  这个接口时代表java中比较两个对象的标准，而且是一种“定制”比较的标准。

  这个接口中没有规定如何比较两个对象的大小，但是规定了如果认为o1大于o2，就返回正整数，小于的情况是负整数，等于的情况是0；

`public static void sort(Object[] a, Comparator c)` 第一个形参： 需要排序的数组， Object[] 说明可以是任意类型的对象数组。 第二个形参： 比较器对象 Comparator接口不能创建对象，只能传入实现类对象 `new xxx` 其中xxx是一个实现了Comparator的类。



* java.util.Comparable - 自然比较，自然顺序

  抽象方法： int compareTo（Object obj）-	this指代的对象与obj对象比较。大于返回正整数，小于返回负整数，等于返回0. 

这个方法可以让对象本身具备compare的能力；

```java
public class TestComparable{
    public static void main(String[] args){
        Student s1 = new Student("A", 24, 89);
        Student s2 = new Student("B", 23, 100);
        
        if(s1.compareTo(s2)>0){
            System.out.println("s1 > s2");
        }
    }
}

class Student implements Comparable{
    private String name;
    private int age;
    private int score;
    
    public Student(String name, int age, int score){
        this.name = name;
        this.age = age;
        this.score = score;
    }
    
    //this = 调用这个方法的对象。
    @Override
    public int compareTo(Object obj){
        
        Student other = (Student) obj;
        //把自然顺序定位按成绩升序排序
        if(this.score > other.socre){
            return 1;
        }esle if(this.score < other.score){
            return -1;
        }else{
            return 0;
        }
    }
    
    
    
}
```



* Array.sort 方法

  如果传入方法中比较的元素，在比较器（Comparator 或 Comparable）中比较后得到的返回值大于0，则两个元素位置互换。

  

* String类实现了Comparable的接口，通过字母的ASCII值的大小来比较大小。



### 3.4.5 JDK 1.8关于接口部分的新特性

a. JDK1.8后，比原来多了两种成员：静态方法和默认方法 `public static xxx` 和 `public default xxx`

b. 为什么java8要允许接口中定义静态方法？

c. 为什么要有抽象方法？

接口的默认方法 = 接口抽象方法默认的实现形式；

d.冲突问题

（1）当一个类同时实现了多个接口时，  这些接口中如果出现了方法签名相同的默认方法时，那么我们必须在这些实现类中做出选择。

选择一：保留其中一个，放弃另一个；

选择二：两者都不同，完全重写一个；

（2）当一个类继承了类并实现了接口，但继承的类和实现的接口中出现了方法签名相同的方法时，

选择一： 默认选择父类的方法调用

选择二：改选保留接口的 `x.super.method()`

选择三：完全重写；



## 3.5 内部类

定义：在一个类里面的类，就叫做内部类

分类：成员内部类和局部内部类。



### 3.5.1 成员内部类

（1）有static修饰的为静态成员内部类

（2）没有static修饰的为非静态成员内部类

- 什么情况下会用到成员内部类？【将内部类看做外部类的一个特殊属性】

（1）当描述一个事物时，发现他的内部还有一个完整的结构需要用一个类来描述

（2）并且发现这内部的结构独立存在是没有意义的，必须在这个外部类中才有意义。

（3）并且这个内部结构只为这个外部类服务；

* 特点

（1）各种类，只有成员内部类可以用static修饰符修饰；

（2）静态内部类中可以使用外部类的静态成员；

（3）静态内部类不会随着外部类的初始化一起初始化，而是要再使用到这个静态内部类时才会初始化

* 结论

（1）同级来说，静态的不能直接使用非静态的

（2）访问一个类的静态成员，用“类名.”即可

（3）内部类需要初始化，要在初始化的同时使用。



* 非静态内部类的特点：

（1）不能出现任何跟static有关的声明；

（2）在非静态内部类中，可以随意访问外部类的所有成员。

（3）在外部内静态成员中，不能使用非静态的成员内部类； 

（4）在外部类的runtime area中调用

```java
class outer{
    abstract class Inner{
        public abstract void test();
    }
}

class MySub extends Inner{
    MySub(Outer out){ //需要外部类对象， 才能调用非静态内部类的构造器或方法
        out.super();
    }
    
    @Override
    public void test(){
        System.out.println("");
    }
}
```





### 3.5.2 局部内部类

（1）有名字的局部内部类：简称局部内部类

（2）没有名字的局部内部类：简称匿名内部类

特点：

（1）局部内部类的修饰符，只能有abstract和final

（2）有作用域 - 方法内

（3） 如果局部内部类在静态方法中，那就不能使用外部类的非静态成员

（4） 局部内部类需要使用局部内部类方法的局部变量时，必须用final修饰符 - 原因：避免局部内部类对象被返回到外部类外面使用时，访问不到这个局部变量，所以加final将变量的位置挪到方法区。





### 3.5.3 匿名内部类

语法格式：

new 父类名（【实参列表】）{

​	类的成员列表

}

new 父接口名（）{

​	类的成员列表

}

特点：

（1）声明匿名内部类与创建它的对象是一起完成的，所以匿名内部类只有唯一的对象

（2）匿名内部类整体，既是类，也是对象；

```java
public class TestAnonymousInner{
    public static void main(String[] args){
        //要声明一个Object的子类，匿名子类
        //并在子类中声明一个方法public void test（）
        Object obj = new Object(){
            public void test(){
                System.out.println("hello, Anonymous Inner Class");
            }
        }
    }
}
```



## 3.6 枚举

作用： 把某个类型的对象，全部列出来。

当某个类型的对象是固定的，有限个数时，可以使用枚举；

如： 星期 Week类： 对象只有7个

性别 Gender 类， 对象只有2个

季节 Season类， 对象只有4个

* 构造器私有化的目的： 在类的外部无法随意地创建对象；

如何声明枚举类型：

【修饰符】 enum 枚举类型名{

​	常量对象列表;

​	其他成员列表

}

```java
enum Season{
    SPRINT("Spring"), SUMMER("Summer"), FALL("Fall"), WINTER("Winter");
    private String description;
}
```



* Enum类

Enum类中有一个唯一的构造器，且这个构造器不能手动调用。编译器编译时会在所有枚举类型的构造器的首行自动调用，并自动传入name（常量对象名）和ordinal（常量对象的顺序）的值。



* API中没有的方法，是编译器生成的方法

（1）. enum.values（）； 返回enum的常量对象的枚举类型数组



* 枚举类型实现接口

枚举类实现接口后，每个常量对象都可以直接调用抽象方法。



## 3.7 注解

一个完整的注解，由三部分组成： （1）声明； （2）使用； （3）读取；

系统中预定义的三个最基本的注解： （1）@Override； （2）@SuppressWarnings ； （3）@Deprecated ；

Override的作用： 检查这个方法是否符合重写要求

SuppressWarnings作用：抑制警告

Deprecated作用：标记某个方法、类、属性等已过时；



### 3.7.1 文档注释



@param

@return

@throws

@exception



```java
/**
*	@author Danny
*	@see	Reference
*	@since	JDK1.8
*	@Date	23rd Aug. 2020
*/

/**
*	App Entry Method of Java.
*	@param args String[] command line parameter
*/
public static void main(String[] args){
    
}
```

在程序中写好注释后，可以在编译器中调用javadoc.exe导出程序性说明文档。



### 3.7.2 JUnit注解 - 白盒测试

JUnit是有 Erich Gamma 和 Kent Beck 编写的一个回归测试框架（regression testing framework）。 供java开发人员便携单元测试用。多数java开发环境都已经集成了JUnit作为单元测试的工具。



@Test标记 - 拥有该标记的方法可以作为一个独立的测试单元

@Before - 拥有该标记的方法会在每一个拥有Test标记的方法执行前执行

@After - 拥有该标记的方法会在每一个拥有Test标记的方法执行后执行

@BeforeClass - 必须用于标记静态测试方法，会在类初始化阶段完成方法

@AfterClass - 必须用于标记静态测试方法，会在类初始化阶段完成方法

- Debug本地代码的步骤

（1）导入JUnit库

（2）创建JUnit类

```java
public class TestUnit{
	@Test
    public void test1(){
        System.out.println("hello junit");
    }
}
```

 要求：测试方法的访问权限必须全是public、参数必须无参； 



### 3.7.3 静态导入

语法格式： import static package.class.xxx

作用： 允许直接用常量名调用其他类的静态成员



## 3.8 异常

概述：在某些情况下，因为用户的一些原因，例如：配置问题，错误输入问题、内存空间不足等导致程序无法正常运行；

不是异常的情况： （1）语法错误； （2）逻辑错误；

* java如何处理异常？

（1）当程序运行到某一句时，发生了异常，程序会先停下来；

（2）程序会在这句代码处，查看原因，生成一个合理“异常对象”，然后“抛”出

（3）JVM会检测这句代码的外围，是否有try..catch结构，可以捕获它。如果可以捕获，那么程序在处理完异常后，继续运行，不会崩溃。如果不能捕获，程序会将这个异常继续抛给上级 - 即调用该方法的方法。如果上级能处理，程序从上级处理完的代码后继续运行。如果不能处理，一直往回报直到到达JVM

```java
public static void main(String[] args){
    
    try	{testInput();
      }	catch (Exception e){
        e.printStackTrace();
    }
}

public static void testInput(){
       try{
        System,out.print("Please input an integer");
        int num = input.nextInt();
    } catch (Exception e){
        System.out.println("Error Input");
    }
}
```





### 3.8.1 异常的类型体系结构

所有类的根父类是Object；

枚举的公共父类是Enum，根父类是Object

异常的公共父类是Throwable，根父类是Object



* java.lang.Throwable

（1）只有当对象是此类（或其子类之一）的实例时，才能通过java虚拟机或者java throw 语句抛出

Throwable有两个直接子类：Error 和 Exception



* Error

一般指严重错误，一般合理的应用不要试图去捕获它，如果出现了这个问题，要么需要升级程序、升级架构、升级硬件。例如报了一个OutOfMemoryError



* Exception

一般异常，合理的应用程序应该去捕获它

主要分为两大类： （1）运行时异常RuntimeException； 运行时异常不建议用try...catch，因为它发生频率太高，而且一般都很不应该发生的问题。例如：空指针异常、数组下标越界异常、类型转换异常等。

（2）编译时异常 - 除了EuntimeException异常及它的子类以为的都是（更常见）

例如：FileNotFoundException、 IOException



### 3.8.2 try...catch - 异常处理方式（unhandle exception）

语法格式：try{

​	可能发生异常的代码

} catch（异常类型1 异常对象名）{

​	处理这个异常的代码

}catch（异常类型2 异常对象名）{

​	处理这个异常的代码

}catch（异常类型3 异常对象名）{

​	处理这个异常的代码

}



* 异常对象的常用方法

（1）e.printStackTrace（）；

打印异常的详细信息，包括追踪信息，既这个异常对象一路经过了哪些方法

（2）e.getMessage（）；

返回异常对象中简单的错误信息

（3）打印异常/错误信息

System.err.println(xx) 



### 3.8.3 try...catch...finally

语法格式：try{

​	可能发生异常的代码

} catch（异常类型1 异常对象名）{

​	处理这个异常的代码

}catch（异常类型2 异常对象名）{

​	处理这个异常的代码

}catch（异常类型3 异常对象名）{

​	处理这个异常的代码

}

...

finally{

​	不过try中是否发生异常，也不管catch是否可以捕获异常，这里代码必须执行

}

应用： 一般用于释放资源、断开连接等代码；



### 3.8.4 try与return混用

（1）如果finally中有return，就从finallly块的return回去。（实际开发中一般不让写）

（2）如果finnally中没有return，那么先把try或catch中全部语句执行完，执行完后运行一下finally，然后回去结束当前方法。



### 3.8.5 throws - 异常处理方式 （unhandle exception）

异常处理的方式二：在当前方法中不处理，抛给调用者处理。

throws：告知被调用者，调用的方法可能会出现哪些异常，使得调用者可以明确知道应该catch什么异常。

throws 的语法格式：

【修饰符】 返回值类型 方法名（【形参列表】）throws 异常列表们{

}

说明：对异常列表的顺序没有要求；

```java
public static void main(String[] args){
	
	try{copy("1.txt", "2.txt");
	} catch (Exception e){
		e.printStackTrace();
	}
}


public static void copy(String srcFile, String destFile) throws FileNotFoundException{
	FileInputStream fis = new FileInputStream(srcFIle);
}
```



* 关于方法重写时，对throws抛出的异常的要求：

子类重写的方法抛出的异常类型必须小于等于父类被重写的方法抛出的异常类型。



### 3.8.6 throw

语法格式：throw 异常对象；

作用： 手动抛出程序员想抛的异常对象

可以用于抛出系统预定义异常或用户自定义异常。



### 3.8.7 自定义异常 - 类

要求： 必须继承Throwable或它的子类。实际开发中一般继承RuntimeException和Exception的较多

（2）建议大家保留父类的两种构造器的形式 - 无参构造 和 带给父类的message属性赋值的构造器



## 3.9 包装类

背景： java在发展过程中设计了很多API、新特性（泛型等），不支持基本数据类型，只支持对象。

所以为了支持8种基本数据类型，java为他们配上了包装类

基本类 				包装类

byte					  Byte

short					Short

int						 Integer

long					  Long

float					 Float

double				Double

char					 Character

boolean			  Boolean



### 3.9.1 装箱与拆箱

装箱： 把基本数据类型的数据 包装 称为包装类的对象

拆箱： 把包装类的对象 拆解 称为基本数据类型的数据

特点：

（1）自动装箱： 当把基本数据类型的值，赋值给包装类的变量时，就会自动装箱；

（2）当基本数据类型跟包装类对象比较时，包装类对象会自动拆箱，再用值跟基本类型比较

```
//装箱操作
int a = 10;
Integer obj = new Integer(a); //将a中的数据包装成一个对象，手动装箱例子；
Interger obj_auto = a; //自动装箱例子

//拆箱操作
Integer i = new Integer(10);
int a = i.intValue(); // 手动拆箱
int b = i; // 自动拆箱


```

（3）常量值： MAX_VALUE； MIN_VALUE

（4）转大小写： Character.toUpperCase（‘a’）； Character.toLowerCase（‘H’）



* 包装类对象的缓存问题

Byte,short,Integer,Long: 都有缓存对象 范围 -128 ~ 127

Float，Double没有缓存对象

Character有缓存对象，范围 0 ~ 127；

Boolean有缓存对象： ture，false

```java
public void test13(){
    Integer a = 1;
    Integer b = 1;
    System.out.println(a == b); //ture a == b 比较的是地址值，但a和b指向的是同一个缓存的常量对象地址。
    
    
    Integer c = 130;
    Integer d = 130;
    System.out.println(c == d); //false 比较的也是地址值，但是c和d由于超出可指向的缓存对象范围，所以需要在堆中新建Integer对象。
}
```



## 3.10 对象的关联关系

（1） 对象之间最弱的一种关联方式，是临时性的关联。代码中一般指由局部变量、函数参数、返回值建立的对于其他对象的调用关系

- 依赖

```java
// A类依赖了B，C，D，E类 
class A{
	public B method (C c, D d){
        E e = new E();
        ...
        B b = new B();

        return b;
    }
}
```



* 关联关系

是类与其他类属性的关联

* 聚合

* 组合

对象A包含对象B，对象B离开对象A后没有实际意义。

* 继承

类与类的继承关系，类与接口的实现关系；



## 3.11 第二阶段项目

### 3.11.1 开发《团队人员调度系统》需求

* 设计要求

（1）软件启动时，根据给定的数据创建公司部分成员列表（数据）

（2）根据菜单提示，基于现有的公司成员，组建一个开发团队以开发一个新的项目

（3）组建过程包括将成员插入到团队中，或从团队中删除某成员，还可以列出团队中现在成员的列表。

（4）开发团队成员要求：架构师、设计师和程序员

（5）软件采用单级菜单方式工作。当软件运行时，主界面显示公司成员（部分）的列表，例如：

-----------------------------------------------开发团队调度软件-----------------------------------------------------------------

ID 	Name 	Age 	Salary 	Posistion 	Status 	Bouns 	Stock 	Equipment

1	  Jane		23		6000		

2	  Fan		  31		20000	ARCHITECT	Free	40000	2000	Lenovo-Y5(6000.0)

...

---=------------------------------------------------------------------------------------------------------------------------------------

a. Team Member List b. Add Team Member c. Delete Team Member d. Exit. Please select(1-4):

(6) 当选择 “添加团队成员” 菜单时，将执行从列表中添加指定（通过ID）成员到开发团队的功能：

-------------------------------------------------------------添加成员------------------------------------------------------------------

Please input the employee you need to add to the team: 

Succeed!

Please press Enter key to continous...

(7)添加成功后，按回车键将重新显示主界面。

(8) 开发团队人员组成要求： 最多一名架构师、最多两名设计师、最多三名程序员；

(9) 失败信息包含以下几种：a. 成员已满，无法添加； b. 该成员不是开发人员，无法添加； c. 该成员已经是团队成员； d. 该成员正在休假，无法添加； e. 团队中只能有一名架构师； f. 团队中只能有两名设计师； g. 团队中只能有三名程序员

(10) 当选择“删除团队成员”菜单时，将执行从开发团队中删除指定（通过TeamID）成员的功能：

-------------------------------------------------------------删除成员------------------------------------------------------------------

Please input the employee you need to delete from the team: 

Are you sure to delete(Y/N): Y

Succeed!

Please press Enter key to continous...

删除成功后，按回车键将重新显示主界面；



需要在类中重新toString方法，以便打印类的对象信息；

### 3.11.2 需求分析

* 视图类

(1)  打印和显示全公司员工功能 void listAllEmployee(); - 调用管理类的getAll得到所有人，遍历打印。

(2) 添加团队成员 - 输入员工编号id， 对用员工管理类的getEmployeeById（id）得到一个员工对象。 然后调用团队成员管理类的addMember（emp），如果有异常，说明失败了，如果没异常，说明成功了。此时需要判断总人数，判断员工类型、判断员工状态、判断每一种的人数、分配团队编号、修改状态、正式加入到team数组中，总人数++

(3) 删除团队成员： 输入团队编号id，对用TeamService的removeByTId（tid），有异常，删除失败。没有异常，删除成功。先判断tid对不对，再修改被删除员工的状态，移动元素，最后置为null，总人数减少。

```java

```



* 全公司员工管理类

```java
Employee[] all;

public Emlpoyee[] getAll(){
    
}

public Employee getEmployeeById（int id）{
    
}
```



* 团队成员管理类

```java
Programmer[] team;
int total;

void addMember(Employee emp){
    
}

removeByTId(int tid){
    
}
```



* javabean实体类

1、所有属性为private
2、提供默认构造方法
3、提供getter和setter
4、实现serializable接口 //Optional

```java
Employee[] all;
all = new Employee[length];

for(int i = 0; i<all.length; i++){
    all[i] = new ?; 
}

```



* 包分析

bean包、service包（管理类）、util包、view类、exception类、test类





### 3.11.3 项目总结：

a. @Test注解怎么使用？

b. 增强For怎么使用？

https://blog.csdn.net/baidu_25310663/article/details/80222534

c. Enum类怎么使用？



d. 如何打印对象的文字信息？

在对象对应类的类中重写toString()方法。

e. 使用arraycopy方法移动数组元素 或 复制数组；



## 3.12 多线程

* java中如何去实现多线程？

线程是进程的其中一条执行路径，既一个进程至少有一个线程，那么main线程就是java进程的第一个线程（主线程）



### 3.12.1 如何开启线程

方法有两种： （1）继承Thread类； （2）实现Runable接口；

* 继承Thread类步骤

（1）编写线程类去继承java.lang.Thread类

（2）必须重写父类的public void run() {}

在run()中需要编写线程需要完成的任务

（3）创建线程类对象

（4）启动线程

```java
public class TestThread2{
    public static void main(String[] args){
        System.out.println("hello thread");
        
        MyThread myThread = new MyThread();
        my.run(); // 这个调用方式不是开启多线程，而是普通对象的调用方法；
        my.start(); //从父类Thread中继承的
        
        for(int i = 1; i <= 100; i++){
            System.out.println("main: " + i);
        }
    }
}

class MyThread extends Thread{
    @Override
    public void run(){
        // The task for thread i
        for(int 1 = 1; i <= 100; i++){
            System.out.println("New Thread" + i);
        }
    }
}
```



* 实现runnable接口

（1）编写线程类，实现java.lang.Runnable

（2）必须实现接口的抽闲方法：public void run()

（3）创建线程类对象

（4）启动线程： start()

但是start方法在Thread类中才有，说明我们要借用Thread类的对象

`Thread t = new Thread(myRunnable)`;

`t.start();`



### 3.12.2 线程相关概念

a. JavaSE： 多线程基础

b. JUC： 多线程高级

* 多线程相关概念：

程序： 当开发者需要实现某个功能，选择一种编程语言而编写的一组指令的集合

软件： 程序 + 程序运行锁需要的一些资源文件

进程： 程序的一次运行是一个进程，进程之间是独立的。操作系统在分配资源（如：内存）是，是以进程为单位。

线程： 进程中的其中一个任务的执行路径； 同一个进程中的多个线程之间是可以共享内存的。每个现成的有些内存是独立的（虚拟机栈、本地方法栈、程序计数器）。 现成是CPU调度的最小单位。

并行： 多个进程同时运行；

并发： 多个线程同时运行；



### 3.12.3 线程的生命周期

* New

线程类跟普通java类一样，使用前需要先new对象；

* 就绪

就绪状态的现成是具备被CPU调用的能力，也只有这个状态的线程才能被CPU调用；

`myThread.start();`

* 运行

运行状态就是该线程当前正在被CPU调度；

* 阻塞

`myThread.sleep()` `myThread.wait()` `join()加塞，被其他线程加塞` `没有锁，synchronized`

`suspend挂起` ，当线程遇到这5种情况时，线程对象进入阻塞状态： 

解除状态条件： sleep（）时间到 或 被interrupt（）方法唤醒、 notify（）、 加塞的线程结束、占用锁的线程释放了锁、resume（）；

* 死亡

当 a. 任务完成后、 b. 遇到了异常没有被正确处理、 c. 被其他线程stop结束 时，线程生命周期结束；



### 3.12.4 Thread类的常用API

* public void run()

子类必须重写，方法体也称为线程体。

* public void start()

线程必须用它

* public static void sleep 

线程对象休眠

* public String getName()

获取线程的名称

* public static Thread currentThread()

获取当前线程对象

* 线程提供有参构造，这样开发者可以在建线程时，自定义线程的名字

* setPriority()、getPriority()

设置线程的优先级，优先级高的被CPU调度的概率增加，不代表优先级低的没有机会。

* public void interrupt()

终止线程的休眠

* public void join()

`A.join();`将A线程对象加塞到B线程中，此时被加塞的B线程要等A线程执行完后才能继续执行。

* public void yield()



### 3.12.5 Thread & Runnable的区别



### 3.12.6 线程安全问题与线程同步

* 线程安全问题：

当多个线程使用“共享数据”时， 就会有线程安全问题。

当一个线程修改了“共享数据”，是会影响其他线程。



* 如何解决线程安全问题： 加锁 - （）

同步代码块（加锁）语法格式： synchronized（锁对象）{ 加锁代码块 }

锁对象，又称为监视器对象。同一时刻，某一段代码，只允许一个线程允许。这个锁就记录谁现在在运行，其他线程进不来

* 锁对象的选择：

（1）可以是任意类型的对象

（2）必须是不同线程都会用到的同一对象

（3）锁的代码的范围：锁一次任务



* 锁方法的语法格式： 【修饰符】 synchronized 返回值类型 方法名（【形参列表】） throws 异常列表 { 方法体 }

同步方法的锁对象，程序员无法选择； 

**如果是非静态方法的锁对象是this，静态方法的锁对象是当前类的class对象；** 



### 3.12.7 生产者与消费者问题

* 什么是生产者与消费者问题？

a 有两个或者多个线程； b 其中一个或一部分线程，生产“数据”， 称为生产者线程；c 另一部分线程，消耗“数据”，称为消费者线程；

这些数据放在一个“共享”或缓存的区域，那么就会出现： 

当“共享”区域终端的数据空了，消费者线程必须“停下来或者等待”生产者线程生产了新数据后，继续进行。

当“共享”区域终端的数据满了，生产者线程必须“停下来或者等待”消费者线程消耗了数据后，继续进行。

d 共享数据： 就会有线程安全问题，所以需要同步锁

e 共享区域大小固定： 有限的，就需要用到“协作”， 线程通信。



* Object类中相关的方法

a. wait（）方法，必须由锁对象（线程的监视器对象）来调用，作用是使当前线程进入等待状态；（同时释放同步锁）

b. notify（）方法，必须由锁对象（线程的监视器对象）来调用，作用是唤醒等待状态的线程，唤醒的只能是想要获取同一个锁对象的线程。 （但有可能唤醒了多个线程），而且线程被唤醒后，会从wait（）代码处往下执行； 【适用于生产者和消费者都分别只有一个对象的情况下】 

c. notify（）方法，基于notify方法，适用于生产者和消费者其中之一的对象超过一个的情况下。因为只有一个时，notify一个就等于notifyall。



## 3.13 String类



### 3.13.1 String类的特点

* 特点： a String类型不能被继承，因为String是由final修饰的；

  ​			b String类型的对象是不可变的，只要修改字符串，就会产生新对象；

  ​			c String对象不可变的特性，字符串有常量池，常量池中的字符串是共享的；

* 字符串常量池：

（1）JDK1.6 及以前的版本： 字符串常量池在方法区 【Oracle 官方虚拟机 HotSpot】

（2）JDK1.7： 字符串常量池在堆中

（3）JDK1.8： 常量池从堆中挪出，挪到一个“元空间 meta space”， 即类似于方法区



* String对象底层存储

JDK1.9之前， 底层是用char[] 存储

JDK1.9之后，底层是用byte[] 存储



* String对象不可变特性说明

（1）底层char[] 数组由final修饰，意味着这个数组不能变更等。

（2）char[] 数组是私有的，程序员无法直接操作char[] 数组，而且String类没有提供char[] 数组的修改方法；

（3）String类提供的所有方法，对字符串的修改都是返回一个新的字符串对象



### 3.13.2 字符串的比较

* == 比较对象地址

* equals 比较字符串的内容，严格区分大小写。
* equalsIgnoreCase 比较字符串的内容，不区分大小写。

* compareTo String类实现了Comparable接口，可比较类的对象大小 【自然排序】

* compareToIgnoreCase String类实现了Comparable接口，可忽略大小写比较类的对象大小 【自然排序】

* sort(string, new Compartor)  【定制排序】 使用此方法需要自己重新写Compartor或传入系统的Compartor。

* java.text.Collator, 使用此类可以为自然语言文本构建搜索和排序例程。但是Collator是抽象类，不能直接创建对象，它有一个直接子类RuleBaseCollator。 Collator内部提供getInstance静态方法，可以获取一个它的子类对象。

  `Arrays.sort(string, Collator.getInstance(Locale.CHINA))`



### 3.13.3 String对象的个数与拼接

* 字符串实际创建的字符对象个数问题



* 拼接结果的存储位置

（1） 常量 + 常量 储存在常量池；

（2） 变量 + 常量 储存在堆内存；

（3） 变量 + 变量 储存在堆内存；

（4） xx.intern() 在常量池



* 空字符串问题



```java
public class TestString02{
    public void test(){
        String s1; // 局部变量为初始化
        String s2 = null; // 局部变量初始化为null 空指针异常
        String s3 = ""; // 空字符串常量对象
        String s4 = new String(); // 空字符串对象
        String s5 = new String(""); // 两个对象， 一个是常量池中的，一个是堆中的。
    }
}
```



### 3.13.4 String类中常用的方法

方法系列一： 最常用的方法

* int length(): 返回字符串的长度
* boolean isEmpty（）： 判断是否是空字符串
* String toLowerCase（）： 使用默认语言环境的规则将此String中的所有字符都转换为小写。
* String toUpperCase（）： 使用默认语言环境的规则将此String中的所有字符都转换为大写。
* String trim（）： 返回字符串的副本，忽略字符串内容前、后空白。
* boolean euqals（Object obj）： 比较字符串的内容
* boolean equalsIgnoreCase（String anotherString）： 与euqals方法类似，忽略大小写
* String concat（String str）： 将指定的字符串连接到此字符串的结尾。等价于用+



方法系列二： 和char相关的方法

* char[] toCharArray()
* char charAt(index) 取出某个位置的字符
* String(Char[] arr)
* String(Char[] arr, int star, int total)



方法系列三： 和byte相关，或者说和编码与解码相关

* byte[] getBytes(): 得到String内容的编码值 【编码的方法，编给计算机设备使用】

编码： 对应ASCII码范围内（0 - 127），无论用什么编码方式，结果都是一样的，一个字符对应一个字节的编码值

* 



方法系列四：

* boolean startWith（） 方法， 检测字符串开头n位的字符
* boolean endWith（）方法，检测字符串末尾n位的字符



方法系列五：检索字符

* boolean contains（）方法： 检测字符串是否包含传入的字符
* int indexOf（）方法： 检测字符串是否包含字符，如果存在返回下标。不过不存在，返回-1
* int lastIndexOf（）方法： 如果存在，返回最后一的下标，如果不存在，返回-1



 方法系列六：截取字符

* String substring（int beginIndex）
* String substring（int beginIndex， int endIndex）



方法系列七： 匹配规则

* boolean matches（正则表达式） 告知此字符串是否匹配给定的正则表达式
* 正则表达式： 用于检测文本的格式 - 校验某个字符串是否符合xx规则 （不是java的语法，是独立于java的）



方法系列八：替换

* String replace（target，value）
* String replaceAll（String regex， String replacement）【支持正则】
* String replaceFirst（String regex, String replacement）【支持正则】



方法系列九： 拆分

* String[] split() 



方法系列十：String类的兄弟类

* StringBuffer



* StringBuilder： 默认char[]长度为16，如果不够扩容为旧长度的2倍+2  （线程不安全，但比StringBuffer快。建议单线程中使用。JDK1.5引入）
* StringBuffer： 线程安全的可变字符序列

方法： append（）：拼接、连接

insert（），在index位置插入参数字符。

delete(int start, int end): 删除【start end）位置的字符

deleteCharAt（int index）删除index位置的字

reverse(): 反转

setCharAt（int index， char ch）：替换【index】位置的字符为ch



### 3.14 Runtime

JVM运行时环境，类型用单例设计

totalMemory方法

freeMemory方法



## 3.15 日期时间的API



### 3.15.1 JDK1.8之前

* java.util.Date

getTime()方法， Date（）无参构造、Date（long ms）



* java.util.Calendar 【抽象类】
* 直接子类：GregorianCalendar

内部提供无参或有参getInstance（）获取对象，提供get方法获取日历相关值



* java.util.TimeZone

static TimeZone getTimeZone(String ID)



* java.text.DateFormat

java.text.SimpleDateFormat

```java
public void test(){
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日")；
}
```



### 3.15.2 JDK1.8及之后

旧的问题：

（1）偏移性

（2）对象的可变性问题

一个日期时间的对象，应该对应一个日期时间的点，不应该变化。即类似String类，对象日期时间的修改应该产生新对象，不应该修改原来的对象。

不可变对象：String类、包装类的对象

（3）格式化：旧版只支持Date

（4）日期时间API：不是线程安全的，不能处理闰秒



新版内容：

java.time：包含值对象的基础包

java.time.chrono：提供对不同的日历系统的访问

java.time.format： 格式化和解析时间和日期

java.time.temporal：包括底层框架和扩展特性

java.time.zone：包含时区支持的类



### 3.15.3 基本类型

LocalDate类：本地日期

LocalTime： 本地时间

LocalDateTime：本地日期时间

方法： .now()：获取现在的时间

.of() 传入时间

get() 方法， 例如getDayOfYear（）；获取xx值

plus

minusDay（）：传入天数

isLeapYear（）：是否是闰年



### 3.15.4 日期时间格式化 

作用：格式化传入的时间。

老版：SimpleDateFormat

新版：DateTimeFormatter类，类中提供了三种格式化方法

预定义的标准格式：ISO_DATE_TIME； ISO_DATE

本地化相关的格式：ofLocalizedDate（FormatStyle.MEDIUM）

自定义的格式：ofPattern（”yyyy-MM-dd hh：mm：ss“）

```java
public void test(){
    LocalDateTime now = LocalDateTime.now();
    DataTimeFormatter df = DateTimeFormatter.ISO_DATE_TIME;
    String str = df.format(now);
    System.out.println(str);
}
```



###  3.15.5 Math中的常用类

* java.lang.Math

Math.PI, Math.sqrt().Math.pow(),Math.max(),Math.min()

ceil() 方法： 进一法， 例如传2.6输出3.0

floor（）方法： 退一法 例如传2.6输出2.0

round（）方法： 四舍五入



* BigInteger 和 BigDecimal

用来存Long类型都存不下的数

```java
BigInteger big 1 = new BigInteger("1111111111111111111");
BigInteger big 2 = new BigInteger("1111111111111111111");
big1.add(big2);
```



## 3.16 物理结构与逻辑结构

逻辑结构：基于程序员使用角度分析

物理结构：基于程序员如何实现 逻辑结构 的底层结构分析

### 3.16.1 数据的储存

（1）變量； （2）數組； （3）集合

因為數組的長度是固定的，需要額外的變量來記錄數組的有效元個數。

對於程序員來說：如果要擴容、或要維護元素的個數，都需要大量的工作。



*  数据存储的物理结构

（1）连续的存储空间：数组

元素是相邻的，在内存中需要开辟连续的存储空间。

缺点：内存比较吃紧时，找一整块空间不好找

优点：访问速度比较快，因为有了首地址，根据下标直接就可以找到对应的元素。

（2）非连续的存储空间：链式

元素不一定是相邻的，在内存中不需要开辟连续的存储空间

缺点：访问速度较数组慢，要从头按链式结构的特性遍历。

优点：不要求空间连续。



### 3.16.2 链式表

* 线性链表：数组、链表、队列（数组或链表）、栈（数组或链表）
* 非线性链表： 树（二叉树）



* 单向链表结构

```java
// 结点 Node
class Node{
    
    Object data; // data in node
    Node next; //Position of next node
}
```

* 双向链表结构

```java
class Node{
    Object data;
    Node pre;
    Node next;
}
```

* 二叉树

```java
class Tree{
    Node parent;
    Object data;
    Node left;
    Node right;
}
```



### 3.16.3 手动实现单向链表

```java

```



### 3.16.4 手动实现动态数组

```

```



### 3.16.5 Collection集合

* java集合抽取的两大接口

（1） Collection： 规范单值集合的接口 {obj}

（2） Map： 规范对值集合的接口 {（kry，value）}

简介：Collection是根接口，没有直接的直接的实现类，有具体的子接口（List 和 Set）。

集合中不存基本数据类型，只能存对象。



* Collection的API

（1）添加 add（Object obj）， addAll（Collection c）

（2）int size（）： 获取有效元素的个数

（3）contains（Object o）： 判断o是否在当前集合中；

（4）containsAll（Collection c）： 判断c是否是当前集合的子集

（5）boolean isEmpty： 判断当前集合是否为空

（6）remove（Object obj）

（7）removeAll（Collection c）：删除当前集合与c集合的交集

（8）clear（）：清空所有

（9）retainAll（Collection<？> c）： 保留交集

（10）Object[] toArray（）： 把集合中的元素 用一个数组返回

```java
public class TestCollection{
    
    public void test2(){
        Collection c = new ArrayList();
        c.add(1);
        
        Collection c2 = new ArrayList();
        c.addAll(c2);
    }
}
```



* modcount ： 记录当前集合被添加或删除元素的次数。

防止多线程同时操作集合，所以modCount 与 校验值expectedModCount不一致时，就会报错。



### 3.16.6 Iterator 迭代器接口

java.util.Iterator接口： 这个接口的实现类在每一种集合类中，例如：ArrayList内部有一个内部类实现了Iterator接口。这里声明为内部类有两个原因：

（1）每一种集合的内部实现不同，意味着对迭代器的实现也是不同的。所以每一种集合都要单独定制迭代器

（2）内部类可以直接访问外部类的私有的属性、成员。



* Iterator的基本方法：

（1） boolean hasNext（）

（2） Object next（）

（3） void remove（）



* Collection系列集合的遍历

（1）Object[] toArray（）： 先返回数组，然后遍历数组

（2）迭代器设计模式

（3）增强for循环

每一个Collection系列的集合，内部都自带一个迭代器。

```java
public class TestIterator{
    public void test1(){
        Collection c = new ArrayList();
        c.add("1");
        c.add("2");
        c.add("3");
        
        Iterator iterator = c.iterator();
        while(iterator.hasNext()){
            String obj = (String) iterator.next();
            if(obj.startWith("2")){
                iterator.remove();
            }
        }
    }
}
```



### 3.16.7 增强For循环

语法格式：for（元素的数据类型 元素临时名称： 数组和集合名）{}

```java
public class TestEnhanceFor(){
    String[] arr = {"hello", "world"};
    for(String string: arr){
        print();
    }
}
```

* 什么样的集合或容器类型可以使用foreach循环？

凡是实现了java.lang.Iterable接口的集合或容器都支持foreach循环



Iterable实现例

```java
public class MyArrayList implements Iterable{
	
    @Override
    public Iterator iterator(){
        
        return new MyItr();
    }
    
    private class MyItr implements Iterator{
        private int cursor;
        
        @Override
        public boolean hasNext(){
            
            return curosr!=total;
        }
        
        @Override
        public Object next(){
            
            return data[cursor++];
        }
    }
}
```



### 3.16.8 List

List系列的集合：有序的，可重复的。

List系列的集合有： ArrayList（动态数组）、Vector（动态数组，向量类）、LinkedList（双向链表、双端队列、栈）、Stack（栈）



* List的API （List在Collection类方法的基础上扩展了以下方法）

（1）void add（int indes， Object element）在index位置插入element

（2）boolean addAll（int index，Collection c）：在index位置添加多个元素

（3）Object get（int index）：获取，返回index位置的元素

（4）Object set（int index, Object element）： 替换index位置的元素为element

（5）int indexOf（Object o） 在当前集合中查找o这个元素的下标，如果没有就返回-1，如果有多个就返回第一个目的元素的下标

（6）int lastIndexOf（Object o）： 在当前当前集合中查找o这个元素的下标，如果没有就返回-1，如果有多个就返回最后目的元素的下标

（7）List<E> subList（int fromIndex, int toIndex） ： 截取【fromIndex，toIndex）部分的子集

（8）ListIterator listIterator（）： 获取一种迭代器

ListIterator 是Iterator的子接口，它比Iterator增加了a. 从后往前遍历的方法，增加了遍历的同时添加和修改方法。



ListIterator 方法：

（1） boolean hasPrevious（）： 是否前面还有元素

（2） Object previous（）： 获取前面的元素

（3） void add（E e）：遍历的同时添加元素

（4） void set（E e）：遍历的同时替换元素

（5）int nextIndex（）：返回下一个元素的索引

（6） int previousIndex（）：返回前一个元素的索引

使用演示

```java
public void test(){
    List list = new ArrayList();
    list.add(1);
    list.add(2);
    list.add(3);
    
    //listIterator方法会返回一个ListIterator对象.
    //@param int cursor； 指定遍历开始的位置
    ListIterator listIterator = list.listIterator();
    while(listIterator.hasNext()){
        Object obj = listIterator.previous();
        print(obj);
    }
}
```



注：List系列的集合，如LinkedList，不建议使用和索引有关的方法进行操作。因为他们底层的物理结构不是数组，如果通过索引操作效率不高。因为为了获得index，需要先遍历集合。



### 3.16.9 Vector 动态数组

线程安全的动态数组

扩容算法： 扩容为原来的2倍，但容易造成空间浪费

遍历集合的方法：（1）增强for循环；（2）Iterator；（3）支持旧版的Enumeration迭代器

* 方法

（1） new Vector（）： 初始化长度为10的数组，默认增量是0

（2） synchronized boolean add（E e）：默认扩容为原来的两倍，也可以在构造器中传参手动传入增量。

（3） add（index， e）：在指定位置插入e

思路： a. 考虑扩容； b. 移动元素 c. 添加元素 d. 元素个数增加

（4） remove（Object obj）

（5） indexOf（）





### 3.16.10 ArrayList 动态数组

线程不安全的动态数组

扩容算法： 扩容为原来的1.5倍，但扩容较频繁

遍历集合的方法：（1）增强for循环；（2）Iterator；

（1）new ArrayList（）： JDK1.8版本初始化了一个长度为0的空数组； JDK1.7的版本初始化了一个长度为0的空数组； JDK1.6版本初始化长度为10的数组







### 3.16.11 Stack 栈类

先进后出（FILO）或后进先出（LIFO）：Last in fist out

Stack是Vector的子类，比Vector多了几个方法



（1） Object peek（）： 访问当前栈顶元素，但是不拿走栈顶栈顶元素。

（2） Object pop（）： 弹出栈顶元素 -> 先peek 后remove

（3） Object push（Object item）： 把元素压入栈顶，等价于add（item）





### 3.16.12 LinkedList 双向链表类

方法：

（1）new LinkedList（）：什么也没干，没有创建结点



内部有一个结点的类型：

```java
class Node{
    Object data;
    Node previous;
    Node next;
}

class LinkedList{
    Node first;
    Node last;
}
```

空链表的特征：`first == null && last == null`

只有一个结点的特征： `first == last`

第一个结点的特征: `first.previous == null`

**LinkedList可以被当做双向链表、栈、队列、双端队列等数据结构使用**

* 如何体现双向链表？

（1） E getFirst（）：

（2） E getLast（）

（3） boolean offerFirst（E e）：添加第一个

（4） boolean offerLast（E e）： 添加最后一个

（5） int indexOf（Objext o）： 从first开始找

（6） int lastIndexOf（Object o）： 从last开始找

（7） E get（int index）



* 如何体现栈

（1） E peek（）；

（2） E pop（）；

（3）void push（E e）；



* 如何体现队列

（1） offer（e）：插入队头

（2） poll（e）： 移走队头元素

（3） peek（）：检查队头元素

```java
public void test(){
    
    LinkedList list = new LinkedList();
    list.add("1");
    list.add("2");
    list.add("3");
    print(list.poll());
    
}
```



* 如何体现双端队列Deque类 （Since JDK1.6）



### 3.16.13 Set接口



* Set接口的特性：

Set系列的集合的元素是不能重复的。

如果按照元素的存储顺序来说，唯有LinkedHashSet可以保证按添加的顺序排列，其他都是不能保证的。

如果按照元素的大小顺序来说，唯有TreeSet可以保证按元素的大小顺序排列。

其中HashSet是完全无序的。

Set接口中没有增加方法，都是从Collection中继承的。



* Set的底层实现

（1） HashSet： 底层new了一个HashMap对象

（2） TreeSet： 底层new了一个TreeMap对象

...

添加到Set中的元素，是作为底层Map的key，value选用了一个Object类型的静态常量对象PRESENT。



* TreeSet

当使用TreeSet类对象存储对象时，被存储的对象必须实现Compartor 或 Comparable 接口 或 new 对象时传入Compartor接口定义TreeSet对象的排序规则。

当使用TreeSet类对象储存对象时，相同的元素被定义为大小“相等”的元素。同理，被存储的对象必须实现Compartor 或 Comparable 接口 或 new 对象时传入Compartor接口定义TreeSet对象的排序规则。

```java
public class TestSet{
    
    public void test(){
        
        /*	使用匿名内部类实现Comparator接口
        * 	TreeSet(Comparator<? super E> comparator)
	   * 	Constructs a new, empty tree set, sorted according to the specified comparator.
        */
        TreeSet set = new TreeSet(new Comparator(){
            @Override
            public int compare(Object o1, Object o2){
                Student s1 = (Student) o1;
                Student s2 = (Student) o2;
                return s1.getId() - s2.getId();
            }
        });

        set.add(new Student(3, "ZhangSan"));
        set.add(new Student(1, "Zhang"));
        set.add(new Student(2, "San"));
    }
    
}
```



* 如何保证元素不可重复？

（1） HashSet和LinkedHashSet做法：先比较hash值，如果不一样，说明一定不同，如果一样，再用euqals方法比较；



### 3.16.14 Map

Map中储存的是一组键值对，成映射关系。

Map接口没有继承Iterable接口，所以不支持For each循环进行遍历；

Map接口没有提供Iterator iterator（）方法返回迭代器对象；

所有Map的Key不能重复： HashMap、LinkedHashMap、Hashtable和Properties是依据key的hashCode和equals方法判断是否是相同key。 TreeMap：依据Key的大小，认为大小相同的Key是相同的。

如果Key重复了，后面的value会替换原来的value。

如果要让TreeMap的key排大小，要么key类型本身实现了java.lang.Comparable接口，要么在创建TreeMap时，传入一个java.util.Comparator接口的实现类对象



* Map类的API

put（Object key，Object value）； 

int size（）；

putAll（Map map）； 将另一个map中所有的映射关系都添加到当前map中

boolean containsKey（Object key）；

boolean containsValue（Object Value）；

get（Obejct key）； 获取key对应的value；

remove（Object key）：根据key删除一对映射关系，并返回key对应的Value

Set KeySet（）； 获取所有的key，可用于遍历Map。【所有的key组成一个Set集合，且不可重复】

Collection values（）； 获取所有的vaule。【因为value可重复】

Set entrySet（）； 获取所有的映射关系。此时吧一对映射关系（Key，Value）看成一个整体，是Entry类型的对象。

因为key不可重复，因此所有的组合是唯一的。所以所有的映射关系也是Set集合



* Map接口的实现类们

（1）HashMap； （2） HashTable； （3） TreeMap； （4） LinkedHashMap； （5） Properties



### 3.16.15 HashTable

特性：（1）旧版；（2）线程安全； （3） key和value不能为null

子类：Properties，不允许key和value是null，并且key和value累的都是String。

通常用于存储配置属性

而且为了可读性更好，还增加了两个方法：

（1） setProperty（key，value）

（2） String getProperty（key）

```java
public void test(){
    Properties pro = new Properties();
    pro.setProperty("User", "Danny");
    pro.setProperty("pwd", "123456");
    
    String user = pro.getProperty("user");
    String password = pro.getProperty("pwd");
}
```





### 3.16.16 HashMap 

HashMap特性：（1） 新版；（2）线程不安全的； （3） key和value可以为null； （4） 无序的；

子类： LinkedHashMap，比HashMap多维护了映射关系的添加顺序，但因为维护了添加顺序，效率比HashMap低。



### 3.16.17 TreeMap

TreeMap特性：按照Key排大小顺序；



### 3.16.18 JDK1.7 Map的底层实现

概述： 不同Key计算出来的数组index值i，Array【i】可能相同，当往Array【i】中存数据时，Array中的表只能存不同Key对应的Value值。

相同Key的对应的value值，新的值会将旧的值覆盖。

* Map的底层实现：

（1） 哈希表系列： 数组 + 链表 或  数组 + 链表 / 红黑树

* HashMap的底层实现： JDK1.7及之前：数组 + 链表； JDK 1.8：数组 + 链表 / 红黑树 

  【集数据结构的优点于一身】

数组的优点：访问速度快（根据下标定位到某个元素）

链表的优点： 因为不同Key的hashcode的值，计算得到的【index】是相同的，只能把【index】的多个映射关系用链表连接起来。

二叉树的优点：如果冲突太多，【index】下面的链表很长，查询速度会大幅降低。当链表长到一定程序时，就需要把链表变为二叉树，提高查询速度



* JDK1.7的HashMap底层实现： 数组+链表

（1） new HashMap（）

table数组初始化为一个长度为0的空数组

DEFAULT_INITIAL_CAPACITY：16

DEFAULT_LOAD_FACTOR：0.75： 默认加载因子，当数组元素个数大于threshold值时，数组扩容

threshold = capacity x load factor：临界值。

就算开发者在New的时候指定了capacity，这个capacity的要求是2的n次方





（2）put（）

若数组是空数组，会把数组初始化为长度16的Entry类型数组，threshold值为12，load factor为0.75

如果不是空数组，先判断key是否为null。 如果key不为空，调用函数 `int hash = hash(key)` 计算key的hash值。接下来要利用hash值来计算index。【因为hash值是要用来计算index的，所以hash值的分布需要够散，冲突才少。】

```java

public V put(K key, V value) {
    if(table == EMPTY_TABLE){
        inflateTable(threshold);
    }
    if(key == null){
        return putForNullKey(value);
    }
    
    //根据Key的hashcode用异或，无符号右移等各种运算，得到了一个int hash值。
    int hash = hash(key);
    //用key的hash值及table的长度来计算index i的值
    //因为table.length -1的值一直落在2的n次方范围内，所以按位与之后，i的范围是【0，table.length-1】
    int i = indexFor(hash, table.length);
    
    //链表套娃思路：先将旧的Object存到一个temp的Object里， 然后构造新Object时，将旧的Object作为参数传入，实现套娃储存
	//那么在访问Object时，就可以通过访问Object的属性来获得当前Object的数据，或旧Object的对象。
    //查找table【i】下面的链表中是否有映射关系的key是重复的，如果有重复的，就有新的value覆盖旧的value
    //table【i】中存储了多个（x，y） Object
    //检查（x，yn）是否有重复的x坐标值，如果有重复就用yn代替原来的y？
    for(Entry<K,V> e = table [i]; e != null; e = e.next){
        Object k;
        if(e.hash == hash && ((k = e.key) == key || key.euqals(k))){
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
        }
    }
    modCount++;
    addEntry(hash,key,value,i);
    return null;
}

//添加部分的核心代码
void addEntry(int hash, K key, V value, int bucketIndex){
    if((siee >= thershold) && (null != table[bucketIndex])){
        resize(2 * table.length);
        hash = (null != key) ? hash(key) : 0; // 重新计算hash
        bucketIndex = indexFor(hash, table.length); // 重新计算index
    }
    createEntry(hash, key, value, bucketIndex);
}

//链表套娃思路：先将旧的Object存到一个temp的Object里， 然后构造新Object时，将旧的Object作为参数传入。
//那么在访问Object对象时，可以访问当前Object对象的数据，也可以访问next属性获取旧Object对象实现访问。
//e 的值是 table[bucketIndex]， 类型是 Entry<K,V>
//然后table数组的index位置又存入了Entry<对象>
//table[bucketIndex] = new Entry<>(hash, key, value, e);
//其中e是用来存上面的table[bucketIndex]的
void createEntry(int hash, K key, V value, int bucketIndex){
    Entry<K,V> e = table[bucketIndex];
    table[bucketIndex] = new Entry<>(hash, key, value, e);
    size ++;
}

Entry(int h, K k, V v, Entry<K,V> n){
    value = v; // V value
    next = n;  // Entry<K,V> next
    key = k; // final K key
    hash = h; // int hash
}
```

如果Key为null时，hash为0，index也为0.



### 3.16.19 JDK1.8 Map的底层实现

几个变量和常量：

（1） DEFAULT_INITIAL_CAPACITY： 默认的初始容量为16

（2） MAXIMUM_CAPACITY: 最大容量 1<< 30

（3） DEFAULT_LOAD_FACTOR: 默认加载因子：0.75

（4） TREEIFY_THRESHOLD: 默认树化阈值8，当链表的长度达到这个值后，要考虑变为链表

（5） UNTREEIFY_THRESHOLD: 默认反树化阈值6，当树中的结点的个数达到这个阈值后，要考虑变为链表

（6）MIN_TREEIFY_CAPACITY：最小树化容量64

​		当单个的链表的结点个数达到8，并table的长度达到64，才会树化，

​		当单个的链表的结点个数达到8，单table的长度未达到64，会先扩容。

（7） Node<K, V>[] table： 数组。

（8） size：记录有效映射关系的对数，也是Entry对象的个数。

（9） threshold： 阈值，当size达到阈值时，考虑扩容。







## 第三阶段： Java新特性与API

Java 新特性包括：泛型、元注解、装箱/拆箱、枚举、可变参数、Lambda表达式、Stream API、 Date/Time API；



## 4.1 自定义注解和元注解



### 4.1.1 自定义注解

语法格式一： 

@元注解

【修饰符】 @interface 注解名{}

声明注解：

```JAVA
@Target(ElementType.TYPE) //表示MyAnnotationvee注解只能用在类、接口等上面
@Retention（RetentionPolicy.RUNTIME） //表示MyAnnotation这个注解可以直流到运行时，可以被反射读取到
@interface MyAnnotation{}
```

使用注解：

```JAVA
@MyAnnotation
class MyClass{}
```



语法格式二： 

@元注解

【修饰符】 @interface 注解名{

​	配置参数列表 //在读取注解时用到

​	或 配置参数 default 参数名

}

配置参数的数据类型要求： （1）8中基本数据类型、String、枚举、Class、Annotation、以上这些类型的数组

声明注解

```java
@interface YourAnnotation{
    String name();
}
```

使用注解

```
//如果注解的配置参数只有一个，并且名字是value，那么赋值时可以省略"value="
@YourAnnotation(name = "Test")
class YourClass{

}
```







### 4.1.2 元注解

作用： 注解	注解的注解（既加在注解上面的注解，叫元注解）

（1）@Target - 标记这个注解可以用于什么位置 `@Target（ElementType.METHOD）`、 `@Target（{ElementType.METHOD，ElementType.TYPE }）//表示方法可以用于注解方法和类型

（2）@RetentionPolicy - 标记这个注解的生命周期 - 生命周期由3个常量指定： TYPE， CLASS，RUNTIME

（3）@Documented - 标记某个注解是否可以被javadoc.exe读取到API；

（4）@Inherited - 标记这个注解是否可以被子类继承



### 4.2 泛型

Since JDK 1.5

Java 中的例子： 在设计集合这个容器的数据结构时，不确定程序员会用来装什么对象。即方法在设计时，形参的类型 & 形参的值都无法确定，所以设计出泛型，让调用者在合理范围内传入类型完成功能。

```java
public void test(){
    ArrayList list = new ArrayList();
    list.add("chang");
    list.add("li");
    list.add("wang");
    list.add(1);
    
    for (Object object : list){
        
    }
}
```

类似的设计 - 求两个形参的和的方法，在完成这个功能时，不确定参数的值，但限定了参数的类型， 所以函数设计者设计了形参，让调用者传入具体的值。

```java
public int sum(int a, int b){
    return a + b;
}
```



语法格式： <类型>

泛型的好处： 安全、避免类型的转换

应用场景：

（1）泛型类、泛型接口；

（2）泛型方法



### 4.2.1 泛型类、接口

* 语法格式：【修饰符】 class  类名《泛型形参列表》{}；
* 泛型实参必须是一个引用数据类型，不能是基本数据类型

`public interface Collection<E>; public class ArrayList<E>; public class HashMap<K,V>`

* 如何为泛型类和泛型接口指定泛型实参？

（1）创建泛型类的对象时：

`ArrayList<String> list = new ArrayList<String> ();`

此时： <String>是泛型的实参类型

（2）继承泛型类、泛型接口时可以指定实参：

（3）实现泛型接口时，可以指定泛型实参；





## 第四阶段： 应用程序开发

要学习的内容包括：JDBC、集合、 IO/NIO、类库、多线程、异常处理、反射、网络



