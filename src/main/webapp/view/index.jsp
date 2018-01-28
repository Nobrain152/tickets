<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" language="java"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AnyQuantour</title>
    <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css"/>
    <script src="../static/bootstrap/js/bootstrap.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="../static/css/base-style.css"/>

</head>
<body>

<div class="header">
    <div class="headerimage" style="">
        <nav>
            <div class="navbar navbar-inverse navbar-fixed-top">
                <div>
                    <div class="navbar-hedaer">
                        <a class="navbar-brand" href="#">AnyQuantour</a>
                    </div>
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">首页</a></li>
                        <li class=""><a href="analyze">分析</a></li>
                    </ul>
                    <div class="searchwrap" align="right">
                        <input class="searchbox" type="search" placeholder="搜索股票">
                        <a id="search_button" href="#"><img src="../static/images/icon/search.png" style="width: 20px;height:20px;padding:0;z-index: 0;position: relative;"></a>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</div>
<div class="content">
    <div class="container masking">
        <div class="row">
            <div class="col-md-2 clu">
                <div class="modulelistwrap">
                    <div class="list-group block">
                        <div class="list-group-item table-head columnleft">热门板块</div>
                        <a id="mod1" data-toggle="mod" class="list-group-item active columnleft">测试1</a>
                        <a id="mod2" data-toggle="mod" class="list-group-item columnleft">测试1</a>
                        <a id="mod3" data-toggle="mod" class="list-group-item columnleft">测试1</a>
                    </div>
                </div>
            </div>
            <div class="col-md-8 clu">
                <div class="stocklistwrap" align="center">
                    <table class="list-group stocktable" id="stocklist" align="center" style="width:100%" >

                        <tr class="list-group-item table-head tline">
                            <th class="column">股票</th>
                            <th class="column">前一交易日涨跌幅</th>
                            <th class="column">前一交易日开盘价</th>
                            <th class="column">前一交易日收盘价</th>
                        </tr>

                    </table>
                    <script type="text/javascript" language="JavaScript">
                        function initMod(){
                            document.getElementById("mod1").innerHTML="黄金交易";
                            document.getElementById("mod2").innerHTML="比特币";
                            document.getElementById("mod3").innerHTML="医疗卫生";
                            //TODO 要从数据拿到成交量最高的的三个板块然后依次填进去
                            $.ajax({

                            });
                            //set toggle
                        }
                        function fetchTable(modname){
                            //TODO 要从数据库拿到板块中最火的3支股票
                            //demo
                            var table=document.getElementById("stocklist").firstElementChild;
                            var childrens=table.childNodes;
                            for(var i=1;i<childrens.length;i++){
                                table.removeChild(childrens[i]);
                            }
                            var stocks=JSON.parse('[["深圳成指",0,0,0],["深圳成指", 0,0,0],["深圳成指", 0,0,0]]');
                           for(var i=0;i<3;i++) {
                               var line = document.createElement("tr");
                               var a = document.createElement("td");
                               var b = document.createElement("td");
                               var c = document.createElement("td");
                               var d = document.createElement("td");
                               a.className = "column";
                               b.className = "column";
                               c.className = "column";
                               d.className = "column";
                               a.innerHTML=stocks[i][0];
                               b.innerHTML=stocks[i][1];
                               c.innerHTML=stocks[i][2];
                               d.innerHTML=stocks[i][3];
                               line.appendChild(a);
                               line.appendChild(b);
                               line.appendChild(c);
                               line.appendChild(d);
                               line.className="list-group-item tline";
                               table.appendChild(line);
                           }
                        }
                        fetchTable("test");
                        initMod();
                    </script>
                </div>
            </div>
            <div class="col-md-2 clu">
                <div class="stockinfowrap">
                    <table class="list-group ">
                        <tr class="list-group-item table-head tline">
                            <th colspan="2" class="columnright">股票信息</th>
                        </tr>
                        <tr class="list-group-item tline">
                            <td class="columnright">股票代号</td>
                            <td class="columnright" id="stockid">123</td>
                        </tr>
                        <tr class="list-group-item tline">
                            <td class="columnright">昨日开盘价</td>
                            <td  class="columnright" id="open">1</td>
                        </tr>
                        <tr class="list-group-item tline">
                            <td class="columnright">昨日收盘价</td>
                            <td class="columnright" id="close">1</td>
                        </tr>
                        <tr class="list-group-item tline">
                            <td class="columnright">昨日最高价</td>
                            <td class="columnright" id="high">1</td>
                        </tr>
                        <tr class="list-group-item tline">
                            <td class="columnright" >昨日最低价</td>
                            <td class="columnright" id="low">1</td>
                        </tr>

                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12 clu">
                <div class="preserve">保留占位</div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12 clu">
                <div class="preserve">保留占位</div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12 clu">
                <div class="preserve">保留占位</div>
            </div>
        </div>
    </div>

</div>
<footer class="footer">
    <div class="container">
        <div class="row seperator"></div>
        <div class="row">
            <div class="col-sm-5 clu">
                <p>由AnyEight小组制作</p>
                <p>此网站为课程学习项目, 不允许用于商业用途.</p>
                <p>NJU Software Institute 2017.5</p>
            </div>
            <div class="col-sm-7"></div>
        </div>
    </div>
</footer>
</body>
</html>
