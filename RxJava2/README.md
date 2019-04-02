# RxJava2 Android

[sample](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples)
[RxJava2](https://github.com/ReactiveX/RxJava)
一个使用Java VM的可观察序列组成异步和基于事件的程序的库
[RxAndroid](https://github.com/ReactiveX/RxAndroid)
[RxAndroid操作符](https://www.jianshu.com/p/e19f8ed863b1)
[RxJava Github](https://github.com/Carson-Ho/RxJavaLearningMaterial)


[Android RxJava应用实例讲解：你该什么时候使用RxJava？](https://juejin.im/post/5a7b9e7cf265da4e7d602ef0)


## 使用步骤
1. 创建被观察者(Observable)&生产事件
2. 创建观察者(Observer)并定义响应事件行为
3. 通过订阅(subscribe)连接观察者和被观察者



### 创建被观察者(Observable)&生产事件
基本创建:create()
快速创建&发送事件:just(),fromArray(),fromIterable(),nerver(),empty(),error()
延迟创建:defer(),timer(),interval(),intervalRange(),range(),rangeLong()




### 创建观察者(Observer)
1. 实现Observer 接口

2. 采用Subscriber抽象类,Subscriber类:RxJava 内置的一个实现了 Observer 的抽象类，对 Observer 接口进行了扩展.Subscriber也是个接口？




