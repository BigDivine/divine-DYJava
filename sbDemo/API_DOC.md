# 接口文档

## 用户相关

1. 用户注册接口（POST）
> 请求地址：/userBean/register
>
> 请求参数：
>
>| 字段 | 说明 | 类型 | 备注 | 是否必填 |
>|----|----|----|----|----|
>|user_name|用户名|varchar(255)| |必填|
>|user_pass|用户密码|varchar(255)| |必填|
>
> 返回参数： 无
>
> 示例
>
> 入参：｛“userName”:"admin","userPass":"admin"｝
>
> 成功：｛“code”:0,"data":{},"msg":"注册成功"｝
>
> 失败： {“code”:1,"data":{},"msg":"注册失败"}
2. 用户登录接口（POST）
3. 用户修改密码接口（POST）

 