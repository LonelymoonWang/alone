## 新建项目，构建工程目录
* config：全局配置
* system：全局的工具和依赖功能
* auth：权限认证模块
* module：业务模块
    - persitence：持久层
    - persitence/dao：持久层具体实现
    - domain：业务层，放置bean
    - controller：rest
    - service层：具体的service接口层
    - service.impl：service层具体实现层
    - api：具体业务公共访问层
    - util：模块内的工具层


目前项目已经搭建起来，能够进行Controller访问。

