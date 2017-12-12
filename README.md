# springcloud示例项目

本项目是一个demo项目。主要用来规划模块结构和模块里面的目录结构。便于以后有一个统一的开发规范。

## XXXX-parent(project-parent) 
整个项目的父模块，主要用来定义各个工程pom文件公共的模块属性管理，每个模块都必须继承这个模块。
* properties 公共属性
* repositories 项目私有库地址
* pluginRepositories 项目插件私有库地址
* dependencyManagement 依赖版本管理
* pluginManagement 插件版本及相关公共配置管理
* profiles 公共的环境相关配置

## framework-api-support 
所有微服务的api模块的公共抽取
* 公共的模块依赖
* 公共异常：exceptions 目录下
* 公共异常的信息：messages 目录下
* 公共的常量：constants 目录下

## framework-server-support
所有微服务的server模块的公共抽取
* 公共的模块依赖
* 公共代码抽取

## framework-XXXX
独立的公共组件，需要用到的微服务各自独立引入

## eureka
eureka 服务代码

## service-XXXX
XXXX微服务模块的父模块

## service-XXXX-api
XXXX微服务的api模块
* 此模块以jar包的形式提供
* XXXX微服务的提供者模块要引用此jar包，并以此模块的api为微服务协议来开发
* XXXX微服务的调用者可引用此jar包，并通过继承api接口和注解@FeignClient来生成调用代理
***
目录说明：
* exceptions 微服务异常
* messages 微服务异常的信息
* constants 微服务的常量
* entity 微服务与数据库对应的entity(必须在数据库在有与之对应的表)
* dto 微服务向外提供的接口的相关dto(与entity的区别是不与数据库相关)
* provider 微服务向外提供的api，接口须定义RequestMapping，RequestParam

## service-XXXX-server
XXXX微服务的server模块。微服务的实现模块。
* XXXX微服务以此jar包启动
***
1.代码模块目录说明：
* aspect aop切面类所在目录
* consumer 调用别的微服务的代理代码所在目录，一般通过继承api接口和添加注解@FeignClient来生成调用代理
* controller 向外提供微服务的rest接口的实现代码所在目录
* filter 过滤器所在目录
* helper 一些帮助类所在的目录
* repository 数据库查询相关类全放这。对于mybatis，加@Mapper在接口上，相对应的mapper文件放在resources/mybatis/mapper下
* service 具体逻辑实现所在的目录，service不用定义接口
* sharding sharding-jdbc分库的一些相关代码
* webcontroller 如果此服服务有对外提供web接口，则相关Controller放在这，以便与controller目录区别
* BootStrap 启动类。
***
2.resources模块目录说明：
* mybatis/mapper mybatis的mapper文件存放路径
* profiles 各环境启动的配置，dev为默认profile。maven打包会将相应profile目录下的资源文件放到resources的根目录，具体实现查看pom.xml文件
* application.yml 启动配置文件








