# HttpLoggingInterceptor

```
 private volatile Set<String> headersToRedact = Collections.emptySet();
//将敏感信息 过滤掉  不在log里面显示出来
  public void redactHeader(String name) {
    Set<String> newHeadersToRedact = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
    newHeadersToRedact.addAll(headersToRedact);
    newHeadersToRedact.add(name);
    headersToRedact = newHeadersToRedact;
  }
  
  private void logHeader(Headers headers, int i) {
    String value = headersToRedact.contains(headers.name(i)) ? "██" : headers.value(i);
    logger.log(headers.name(i) + ": " + value);
  }
```

## volatile
> `volatile`通常被比喻成”轻量级的`synchronized`“，也是Java并发编程中比较重要的一个关键字。和`synchronized`不同，`volatile`是一个变量修饰符，只能用来修饰变量。无法修饰方法及代码块等。`volatile`的用法比较简单，只需要在声明一个可能被多线程同时访问的变量时，使用`volatile`修饰就可以了。

1. volatile保证了变量的可见性(可见性是指当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。)

2. volatile保证了有序性(即程序执行的顺序按照代码的先后顺序执行)
3. volatile是不能保证原子性的

## Collections.emptySet() Collections.emptyList()等
这个方法主要目的就是返回一个不可变的列表，使用这个方法作为返回值就不需要再创建一个新对象，可以减少内存开销。并且返回一个size为0的List，调用者不需要校验返回值是否为null，所以建议使用这个方法返回可能为空的List。但是不能add,remove元素