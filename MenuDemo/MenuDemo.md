# MenuDemo

#### 使用XML定义菜单

对于所有菜单类型，Android 提供了标准的 XML 格式来定义菜单项。要定义菜单，请在项目 res/menu/ 目录内创建一个 XML 文件，并使用以下元素构建菜单：

* menu:定义Menu，即菜单项的容器。menu元素必须是该文件的根节点，并且能够包含一个或多个item和 group元素.
* item:创建MenuItem,此元素表示菜单中的一项,可能包含嵌套的menu元素，以便创建子菜单。
* group:item元素的不可见容器(可选)。它支持您对菜单项进行分类，使其共享活动状态和可见性等属性。
##### item元素支持多个属性

* android:id:项目特有的资源ID，让应用能够在用户选择项目时识别该项目。
* android:icon:引用一个要用作项目图标的可绘制对象。
* android:title:引用一个要用作项目标题的字符串。
* android:showAsAction:指定此项应作为操作项目显示在应用栏中的时间和方式。



