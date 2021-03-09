# transportModel

 

全国交通咨询模拟



## 一、任务概述

### 1.1概述任务

现在有越来越多的人乘坐火车飞机出行，很多情况需要提前查询航班和铁路信息，如果仅靠人工查询，则工作量巨大且非常容易出错切很麻烦，就迫切的需要程序来辅助人来查询航班和铁路信息，因此，交通的工作人员需要一个方便可靠的系统来帮助他们完成辅助客人查询，以满足乘客不同的需求。例如：花费最少、耗时最少、中转次数最少等需求。于是，我便选择了全国交通咨询模拟系统作为我的数据结构课程设计内容。

### 1.2总体任务要求

系统设计要求：

1） 交通工作人员可以进行修改增加信息等操作。

2） 客户可以通过系统查询不同出行方式不同要求的合适方案。



## **二、需求分析**   

### 2.1总体需求概述

处于不同目的的旅客对交通工具有不同的要求。例如，因公出差的旅客希望在旅途中的时间尽可能地短，出门]旅游的旅客则期望旅费尽可能省,而老年旅客则要求中转次数最少。编织一个全国城市间的交通资讯程序,为旅客提供两种或三种最优决策的交通咨询。

### 2.2功能需求

(1) 提供对城市信息进行编辑(如添加或删除)的功能。

(2)城市之间有两种交通工具 :火车和飞机。提供对列车时刻表和飞机航班进 行编辑(增设或删除)的功能。

(3)提供两种最优决策 :最快到达和最省钱到达。全程只考虑一种交通工具。

(4)旅途中耗费的总时间应该包括中转 站的等候时间。

(5)咨询以用户和计算机的对话方式进行。由用户输入起始站、终点站、最优决策原则和交通工具。输出信息:最快需要多长时间才能到达或者最少需要多少旅费才能到达，并详细说明依次于何时乘坐哪一趟列车或那一次班机到何地。

### 2.3非功能需求

(1)对全国城市交通图和列车时刻表及飞机航班表进行编辑，应该提供文件形式输入和键盘输入两种方式。飞机航班表的信 息应包括:起始站的出发时间、终点站的到达时间和票价;列车时刻表则需根据交通图给出各个路段的详细信息,例如:对从北京到上海的火车,需给出北京至天津、天津至徐州及徐州至上海各段的出发时间、到达时间及票价等信息。

(2)以邻接表座交通图的存储结构,表示边的结构内除含有邻接点的信息外，还应包括交通工具、路程中耗费的时间和花费以及出发和到达的时间等多种属性。

(3)增加旅途中转次数最少的最优决策。

## 三、概要设计

### 3.1总体架构设计

管理者（工作人员）使用系统时可完成对全国城市交通图和列车时刻表及飞机航班表进行编辑或者删除，提供文件形式输入和键盘输入两种方式。

**使用者**用户输入起始站、终点站、最优决策原则和交通工具。输出信息:最快需要多长时间才能到达或者最少需要多少旅费才能到达，并详细说明依次于何时乘坐哪一趟列车或那一次班机到何地。

### 3.2关键流程设计

 

**（1）服务器端**

​                              ![image-20210308160652825](https://github.com/BigStrawberry0225/transportModel/blob/master/img-folder/image-1.png)

 

**（2）用户端**

 ![image-20210308160705934](https://github.com/BigStrawberry0225/transportModel/blob/master/img-folder/image-2.png)

**（3）数据库方法**

 ![image-20210308160710010](https://github.com/BigStrawberry0225/transportModel/blob/master/img-folder/image-3.png)

 

## 四、详细设计

### 4.1界面详细设计

（1）起始界面（登录界面）

 ![image-20210308160735248](https://github.com/BigStrawberry0225/transportModel/blob/master/img-folder/image-4.png)

该界面为系统的起始界面，起始界面为登录界面，输入用户名和密码点击登录按钮可进入系统功能界面。如果错误会告知你错误。

（2）注册界面

 ![image-20210308160748028](https://github.com/BigStrawberry0225/transportModel/blob/master/img-folder/image-5.png)

该界面为用户的注册界面，输入用户名密码注册可以向数据库中加入一个账号。

（3）管理员登录界面

 ![image-20210308160757241](https://github.com/BigStrawberry0225/transportModel/blob/master/img-folder/image-6.png)

该界面为管理员系统登录界面，输入管理员密钥，正确可以进入管理界面

（4）管理界面

 ![image-20210308160806967](https://github.com/BigStrawberry0225/transportModel/blob/master/img-folder/image-7.png)

该界面为系统的管理界面，可以对信息进行添加删除等，添加可以通过键盘或者文件两种形式进行

 

（5）查询界面

 ![image-20210308160822657](https://github.com/BigStrawberry0225/transportModel/blob/master/img-folder/image-8.png)

该界面为系统的用户查询界面输入查询的相关信息点击查询即可进行查询。

（6）查询结果界面

 ![image-20210308160832809](https://github.com/BigStrawberry0225/transportModel/blob/master/img-folder/image-9.png)

该界面为系统的查询结果界面，根据查询界面选择的查询条件返回合适的方案。

**（7）**数据库表设计

   ![image-20210308160852338](https://github.com/BigStrawberry0225/transportModel/blob/master/img-folder/image-10.png)

 ![image-20210308160900749](https://github.com/BigStrawberry0225/transportModel/blob/master/img-folder/image-11.png)

 ![image-20210308160904759](https://github.com/BigStrawberry0225/transportModel/blob/master/img-folder/image-12.png)



