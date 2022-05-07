## ChatManagement
一款综合的聊天管理插件.几乎可以配置你能想象到的一切.

## 功能特色
支持 `过滤器` , 不同组的过滤器.每组有自己的忽略权限   
支持 `格式组` , 不同组有自己的前后缀以及悬浮文字等, 有 `优先级` 之分
如果玩家拥有多个组的权限，会优先选用 优先级较高 的那个
如果优先级一样,则会选择这几个组在配置文件中 排在最前面 的那个
支持除默认外的两种聊天文字显示方式, `Actionbar` , `BossBar`       
BossBar 可配置 颜色 、 风格 和 显示时间      
支持 `悬浮文字` (仅默认的聊天框)     
支持 `PlaceHolderAPI` (前后缀,悬浮文字等),支持 `私聊` 自动化程度高.     
玩家`数据存储`, 可以设定玩家自己的`前后缀`以及`悬浮文字`
玩家可以`开启或关闭`聊天         
支持`SQLite`以及`mysql数据库存储`     
支持16进制的颜色代码: `1.16+`         
左下角聊天框不支持1.16在`保证悬浮文字可用`的情况下, `无法支持`16进制的颜色代码.         
如果你一定要使用16进制颜色代码，会`自动修正为普通颜色代码` &1,&2等     
聊天速度控制,可在`config.yml`配置     
完整的配置文件支持

功能特色(计划,未实现部分):     
快速`物品展示`: 替换文字#1~#8     
群组聊天         会增加相关 `PlaceholderAPI` 的占位符     
添加多个`箱子菜单`         
群组(`优先级最低,可能不添加`)        
过滤器列表(`针对管理员`)        
聊天格式组(`针对管理员`)   
设置玩家自己的聊天颜色     
快速`@`某人

## 指令及权限
`主指令: /chatm, /cm 或/chatmanagement`

/chatm pm <玩家名> - 无权限
* 使用后可以开启或关闭私聊,  指令后带上玩家名可快速将该玩家设置为私聊对象

/chatm chat - 无权限
* 切换你的聊天状态，关闭后无法看到其他玩家的聊天也无法发送聊天

/chatm converter <MYSQL/SQLITE> - chatmanagement.command.admin.converter
* 将数据转换到指定数据库,如果需要转换到MYSQL， 请提前配置好config.yml中的数据库并重载配置文件.

/chatm help <指令> - 无权限
* 查看指定指令的帮助信息

/chatm list <format/filter> - chatmanagement.command.admin.list
* 列出所有格式组或过滤器组

/chatm player <指定参数> - chatmanagement.command.admin.player
* 查询玩家信息或设置玩家的前后缀及前后缀的悬浮文字

/chatm reload <指定参数> - chatmanagement.command.admin.reload
* 重载指定配置文件

##开发者
