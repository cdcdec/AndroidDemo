
https://github.com/LRH1993/Eyepetizer-in-Kotlin

https://www.jianshu.com/p/4cb1a56acf9e

https://github.com/git-xuhao/KotlinMvp/tree/master/app/src/main/java/com/hazz/kotlinmvp

https://github.com/Walkud/JudyKotlinMvp

1. FragmentActivity

FragmentManager getSupportFragmentManager()

2. FragmentManager

List<Fragment> getFragments()

FragmentTransaction beginTransaction()

3. FragmentTransaction:

FragmentTransaction add(@IdRes int var1, @NonNull Fragment var2)
int commit()
FragmentTransaction show(@NonNull Fragment var1)
FragmentTransaction hide(@NonNull Fragment var1)

4. Fragment

public void setUserVisibleHint(boolean isVisibleToUser)

public boolean getUserVisibleHint()

public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)

public void onViewCreated(View view, Bundle savedInstanceState)


5. RecyclerView
public void addOnScrollListener(@NonNull RecyclerView.OnScrollListener listener)


6. Application
public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback)

 public interface ActivityLifecycleCallbacks {
        void onActivityCreated(Activity activity, Bundle savedInstanceState);
        void onActivityStarted(Activity activity);
        void onActivityResumed(Activity activity);
        void onActivityPaused(Activity activity);
        void onActivityStopped(Activity activity);
        void onActivitySaveInstanceState(Activity activity, Bundle outState);
        void onActivityDestroyed(Activity activity);
}


7. 可空类型和非空类型(Nullable types and Non-Null Types)

当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空


```
//表明toast的取值可以为null
var toast: Toast? = null

//Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type Toast?

```

8. kotlin
Property must be initialized or be abstract.  属性必须初始化或者是抽象的

is 运算符检测一个表达式是否某类型的一个实例，如果一个不可变的局部变量或属性已经判断出为某类型，那么检测后的分支中可以直接当作该类型使用，无需显式转换。
















