# 模拟TomcatOOM的代码
- 启动时增加参数  -Xms55m -Xmx55m
- 通过不断调用```curl --location --request GET 'http://localhost:8080/consume/mb/35'```和```curl --location --request GET 'http://localhost:8080/consume/mb/35'```请求来引起OOM