# HK2

使用HK2，需要先扫描加载绑定。  
  
## API 概览
  
### 简介
  
`HK2`是一个声明式的框架，使用注解`@Contract, @Service`去声明一个服务。此外，也可以使用API去控制服务和在`Services registry`中绑定可用的服务。  
  
### ServiceLocator
  
在`HK2`中最基础的服务就是`ServiceLocator`。它表示一个`registry`,并可以在其中寻找绑定在其中的服务以及服务的信息(`Descriptors`)。它自己本身也是一个注册在`registry`中的服务，并且是总是第一个绑定进去的服务。   
`ServiceLocator`在JVM中都一个独一的`locator ID`，可以通过`ServiceLocatorFactory`去**创建或寻找**一个`ServiceLocator`。`ServiceLocatorFactory`一般会使用默认的实现`ServiceLocatorGenerator`，在`META-INF/services`中指定。默认的实现可以通过改变不同的`META-INF/services`。  
一旦通过`ServiceLocatorFactory`新建一个`ServiceLocator`,它至少会包含以下三个服务：  
* 它本身(`ServiceLocator`)  
* 默认的`JSR-330`解析(`InjectionResolver`)  
* 一个用于配置更多服务的服务(`DynamicConfigurationService`)  

### 增加自己的服务  
  
上述的三个服务很难形成一个有用的系统。为了使它更有用，需要提供自己的服务。通过使用`DynamicConfigurationService`可以增加自己的服务。`DynamicConfigurationService`服务会自动添加到每个`ServiceLocator`。使用它可以很简单的寻找服务：  
```Java  
public void initialize() {
	ServiceLocatorFactory factory = ServiceLocatorFactory.getInstance();
	ServiceLocator locator = factory.create("jioong");
	DynamicConfigurationService dcs = locator.getService(DynamicConfigurationService.class);

	...
}
```  
  
然后通过`DynamicConfigurationService`去创建`DynamicConfiguration`实例。而`DynamicConfiguration`接口则提供一些方法去绑定自己的服务。  
为了`bind in`自己的服务首先需要为自己的服务创建一个`description`。该`description`可以提供一些关于服务的信息，比如**实现类的名字、或接口等其他信息**。一般，任何一个`Descriptor`的实现都可以使用。  
  
### BuilderHelper Binding EDSL
  
`EDSL`是`Embedded Domain Specific Language`，允许去创建指定领域的对象。  
```Java
BuilderHelper.link("com.github.jioong.ServiceImpl")
	.to("com.github.jioong.api.MyService")
	.in("org.glassfish.api.PerLookUp")
	.build();
```  
  
### DescriptorImpl
  
比起自己实现`Descriptor`,它已经提供一个实现`DescriptorImpl`。使用该方法可以方便的设置`Descriptor`的所有`field`。    
与上一节相同的效果：  
```Java
DescriptorImpl retVal = new DescriptorImpl();
retVal.setImplementation("com.github.jioong.ServiceImpl");
retVal.addAdvertisedContract("com.github.jioong.ServiceImpl");
retVal.addAdvertisedContract("com.github.jioong.api.MyService");
retVal.setScope("org.glassfish.api.PerLookUp");

return retVal;
```  
  
### 在ServiceLocator中绑定Descriptor
  
```Java
public void initialize() {
	ServiceLocatorFactory factory = ServiceLocatorFactory.getInstance();
	ServiceLocator locator = factory.create("jioong");
	DynamicConfigurationService dcs = locator.getService(DynamicConfigurationService.class);  
	DynamicConfiguration config = dcs.createDynamicConfiguration();
	config.bind(descriptor);
	config.commit();
}
```  
  
### 增加服务的简单方法
  
有几种简便的方法可以将`descriptor`添加到`service locator`中。这些方法被封装在`ServiceLocatorUtilities`类中。  
1. 如果已经有一个`service`类，并想要`HK2`自动分析该类并添加到一个`locator`中，则可以使用`addClasses`方法。  
2. 如果已经有一个`service`类的实例，并想要自动分析该`service`的类并添加到`locator`，则可以使用`addONEconstant`方法。  
3. 如果有一个`service`的`descriptor`并想要添加到`locator`，则可以使用`addONEdescriptor`方法。  

### 寻找服务
  
在`HK2`中有几种机制可用于去寻找`services`。最简单的方式是就是调用`ServiceLocator`对象的`getService()`方法，参数为服务的类，如：  
```java
Widget widget = locator.getService(Widget.class);
```  
传入的类型可以是任意的类实现或接口。如果`getService()`找不到对应的类，则返回`null`。如果有多于一个服务可以被找到，则会返回最好的。  
`services`会被排序，最大的`locator id`(为了优先找到子类中的服务，而不是父类中的服务)和最小的`service id`(更老的服务被优先找到)。因此，最好的服务实例就是`locator`最大而`service id`最小的服务。服务的排序在`Descriptor`中，并且可以在任何运行时改变。  
  
* 服务的`locator id`是系统分配给`Descriptor`的值，当它被绑定到一个`ServiceLocator`时，值则是`ServiceaLocator`的`id`值。  
* 服务的`service id`是系统分配给`Descriptor`的值，当它被绑定到一个`ServiceLocator`时，该值为一个单调递增的数值。  

#### 通过名字查找服务
  
`services`可以用多种方式修饰，但是最常见的方式是将服务于一个名字关联。如果有多个同类的服务，则可以通过名字来找到特定的服务：  
```java
public Widget getNamedWidget(String name) {
	return locator.getService(Widget.class, name);
}
```  
  
#### 通过修饰符查找服务
  
如果服务有`qualifiers`,则可以通过修饰来查找服务。  
有一个`qualifier`:  
```java
@Qualifier
@Retention(RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface Blue{
}
```  
一般来说，不会实现`Blue`,   
```java
public class BlueImpl extends AnnotationLiteral<Blue> implements Blue {
}
```  
现在，则可以使用`BlueImpl`去查找服务，  
```java
Widget widget = locator.getService(Widget.class, new BlueImpl());
```  
则会返回用`@Blue`修饰的`Widget`服务。  
  
#### 获得所有服务
  
可能需要获取所有实现一个`contract`的服务，则  
```java
List<Widget> widgetList = locator.getAllService(Widget.class);
```  
当使用如上调用时，则所有的`Widget`都会被类加载，会影响类加载的性能。可以考虑使用`getAllServiceHandles`或者`getDescriptors`方法。  
  
### 获取服务 descriptor
  
如果只是想要查找服务`descriptors`，而不是服务本身，则可以在`ServiceLocator`对象上调用`getDescriptor`或者`getBestDescriptor`方法。这两个方法都不会触发类加载动作，因此在类加载可能有问题的环境也是安全的。  
调用`getDescriptor`方法时，使用`Filter`可以决定返回各种类型的`Descriptors`。可以自己实现`Filter`,也可以使用`BuilderHelper`提供的实现。最常见的是使用`BuilderHelper`提供的`IndexedFilter`,  
```java
IndexedFilter filter = BuilderHelper.createContractFilter(Widget.class.getName());  
List<ActiveDescriptor<?>> widgetDescriptors = locator.getDescriptors(filter);  
```  
使用`IndexedFilter`可以节约查找的时间。  
  
### Unmanaged Creation, Injection and Lifecycle
  
1. 使用`create()`方法尝试去创造一个给定类的实例，使用`HK2`的依赖注入规则：  
```java
Widget widget = locator.create(Widget.class);
```  
2. 有一个对象，并想要`@Inject`域和初始化方法被调用，则使用  
```java
locator.inject(widget)
```  
但是所有的`postConstruct`方法不会被调用。则可以使用  
```java
locator.postConstruct(widget);  
locator.preDestroy(widget);
```  
