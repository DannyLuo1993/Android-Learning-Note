## Android 基础知识

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

