##codeMaker（Mybatis对象生成工具）
#####作者：高峰

<p style="font-size: 14px;"> 这个项目可以将mysql全库的表，转换成Mybatis代码（dto，mapper，sqlmap）</p>
<p style="font-size: 14px;"> 相比于mybatis generator，这个程序的好处就是方便改造，添加你自己想要的格式</p>
<p style="font-size: 14px;"> 目前这个项目已经有三处优化：生成字段备注、支持外挂备注；支持任意sql（多表联查）生成dto和resultMap</p>
<p style="font-size: 14px;"> 程序有两个入口</p>
<p style="font-size: 14px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支持任意sql生成实体的方法org.gaofeng.main.RunSingle</p>
<p style="font-size: 14px;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支持全库全表生成实体的方法org.gaofeng.main.RunBatch</p>
<p style="font-size: 14px;"> </p>
<p style="font-size: 14px;"> </p>
<p style="font-size: 14px;"> 想想如果你是新进入一家公司，或者你是一个懒人，而且你是一个面向对象开发的java程序员，或者你新架构一个项目。</p>
<p style="font-size: 14px;"> 在写sqlmap的resultMap，和Dto的时候你是有多么的痛苦。</p>
<p style="font-size: 14px;"> 你要记住这个字段怎么写，他的类型是什么，对应java的类型是什么，对应jdbcType的类型是什么</p>
<p style="font-size: 14px;"> 我是受够了</p>
<p style="font-size: 14px;"> 上网上找了一些工具，不是收费，就是生成的打不到预期，好不容易找到一个开源的。那代码写的实在是有水平，至少我看着费劲啊。</p>
<p style="font-size: 14px;"> 各种继承封装多态。你就生成一个文件，你整那么文艺干啥，只有一般人看不懂的才叫艺术么？</p>
<p style="font-size: 14px;"> 生成文件的格式分别在MakeDomain.java，MakeMapper.java，MakeSqlMap.java。想要修改就自己改吧。相信你只要做过java就能看懂。</p>
<p style="font-size: 14px;"> 为了方便配置，我使用一个类进行数据库类型-java类型-jdbc类型的关系进行了一个映射org.gaofeng.mysql.utils.MysqlUtils</p>
<p style="font-size: 14px;"> 一般不用修改，我这个是按照mybatis generator的规则写的，如果他的是正确的，我这个也差不多</p>

<p>使用说明：https://github.com/usertxl/codeMaker/wiki/%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E</p>
<p>程序介绍：https://github.com/usertxl/codeMaker/wiki/%E8%BF%99%E4%B8%AA%E4%B8%9C%E8%A5%BF%E8%83%BD%E5%B9%B2%E4%BB%80%E4%B9%88</p>
####已经支持mysql数据库，因本人不用oracle所以这是预留了接口，没有调试
