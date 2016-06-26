# maven 聚合与继承
   
将软件划分为不同的模块，以得到更清晰的设计以及更高的重用性。  
`Maven`项目的**聚合**特性能够把项目的各个模块聚合在一起，而`Maven`的**继承**特性则能帮助抽取各个模块相同的依赖和配置，在**简化POM**的同事，还能促进各个模块的**一致性。**  
  
## 聚合
  
对于聚合模块来说，其打包方式`packaging`必须为`pom`，否则无法构建。  
用户可以通过一个打包方式为`pom`的`Maven`项目中声明任意数量的`module`元素来实现模块的聚合。这里的每个`module`的值都是一个当前`POM`的相对目录。  
`Maven`会首先解析聚合模块的`POM`、分析要构建的模块、并计算出一个反应堆构建顺序(Reactor Build Order)，然后根据这个顺序依次构建各个模块。反应堆是所有模块组成的一个构建结构。  
  
## 继承
  
在聚合模块下创建一个新的父模块子目录，然后在该子目录下创建一个除了聚合模块之外模块的父模块。  
它使用与其他模块一致的`groupId, version`，它的`packaging`为`pom`。  
**父模块只是为了消除配置的重复。**  
  
然后就可以在子模块中通过`parent`元素声明父模块，`parent`下的子元素`groupId, artifactId, version`指定了父模块的坐标，这三个元素是必须的。元素`relativePath`表示父模块`POM`的相对路径。  
当项目构建的时候，`Maven`会首先根据`relativePath`检查父`POM`,如果找不到，再从本地仓库查找。`relativePath`的默认值是`../pom.xml`。   
  
### 可继承的POM元素
  
* `groupId` 项目组ID，项目坐标的核心元素  
* `version` 项目版本，核心元素  
* `description` 项目的描述信息  
* `organization`  
* `inceptionYear` 项目的创始年份  
* `url` 项目的URL地址  
* `developers`  
* `contributors`  
* `distributionManagement` 项目的部署配置  
* `issueManagement` 项目的缺陷跟踪系统  
* `ciManagement`  
* `scm`  
* `mailingLists`  
* `properties` 自定义的`Maven`属性  
* `dependencies` 项目的依赖配置  
* `dependencyManagement` 项目的依赖管理配置  
* `repositories` 项目的仓库配置  
* `build` 包括项目的源码目录配置、输出目录配置、插件配置、插件管理配置等  
* `reporting` 包括项目的报告输出目录配置、报告插件配置等  
  
### 依赖管理
  
定义在`dependencies`元素中依赖会被所有的子模块继承，这样会导致让一些不需要的模块也被子模块继承过去。  
`dependencyManagement`元素既能让子模块继承到父模块的依赖配置，又能保证子模块依赖使用的灵活性。在`dependencyManagement`元素下的依赖声明不会引入实际的依赖，不过它能约束`dependencies`下的依赖的使用。  