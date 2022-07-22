var express = require('express')


// 90
var app = express();
// __dirname 指当前目录，这里会去找index.html
app.use(express.static(__dirname))
app.listen(90)


// 91
var app2 = express();
app2.get("/",(req,res) =>{
    // 1、解决跨域的第一方式，在想要访问资源的返回请求头中设置Access-Control-Allow-Origin的值，指定是否可以跨域访问
    // res.setHeader("Access-Control-Allow-Origin","*")
    
    // 2、jsonp
    var funcname = req.query.callback
    res.send(funcname + "('你好')")

})

app2.listen(91)