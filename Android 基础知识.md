## Android 基础知识

https://www.runoob.com/android/android-content-providers.html

分析源码时，首先是看数据结构（输入），其次是看算法，最后看输出；

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

* **NavHost**

  是一个容器，用来存放页面

* **Fragment**

  Navigation是在Fragment之间切换 

* **NavController**

  负责控制导航的逻辑。

* NavGraph



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
  
  https://developer.android.google.cn/reference/androidx/lifecycle/AndroidViewModel
  
  https://developer.android.google.cn/reference/androidx/lifecycle/package-summary?hl=en
  
  https://developer.android.google.cn/reference/androidx/lifecycle/ViewModelProvider.Factory
  
  
  

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





Dao

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





DataBase

```java
//继承父类Room database
//定义为抽象，因为实现的功能不用我们自己写，编译器会帮我们写。

@database(entities = (Word.class), version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase{
	
	//如果有多个Entity，需要写多个Dao来操作
	public abstract WordDao getWordDao();
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

