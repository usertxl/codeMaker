##codeMaker
#####作者：高峰

这个项目可以将oracle和mysql 的sql语句转化成实体类
如果是新人,很少能有记住数据库字段类型的,对于这种新人,让他去编写实体类,他只能一个一个的去数据库对字段,费时费力
程序有两个入口
	支持任意sql生成实体的方法org.gaofeng.ui.Start.java
	支持全库全表生成实体的方法org.gaofeng.sqltodto.Main
####里程碑版已经完工,目前支持oracle 和mysql两种数据库,bug多多,请留言