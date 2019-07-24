<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<script src="${pageContext.request.contextPath}/js/echarts.js"></script>
<script src="${pageContext.request.contextPath}/js/goeasy.js "></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/china.js"></script>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));
    var goEasy = new GoEasy({
        appkey:"BC-bb534c8ed9c343f18b8776e3ce18f78b"
    });
    goEasy.subscribe({
        channel: "my_channel",
        onMessage: function (message) {
            var parse = JSON.parse(message.content);
            var option = {
                title : {
                    text: '持明法洲用户分布图',
                    left: 'center'
                },
                tooltip : {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data:['男性','女性']
                },
                visualMap: {
                    min: 0,
                    max: 2500,
                    left: 'left',
                    top: 'bottom',
                    text:['高','低'],           // 文本，默认为数值文本
                    calculable : true
                },
                toolbox: {
                    show: true,
                    orient : 'vertical',
                    left: 'right',
                    top: 'center',
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                series : [
                    {
                        name: '男性',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data:parse[0]
                    },
                    {
                        name: '女性',
                        type: 'map',
                        mapType: 'china',
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data:parse[1]
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });
       $(function () {
          $.post("${pageContext.request.contextPath}/user/userfenbu",function () {
          },"json")
       })
</script>
<div class="page-header">
    <h1><center><font color = "#00008b">用户分布动态图</font></center></h1>
</div>
<center><div id="main" style="width: 700px;height:450px;"></div></center>