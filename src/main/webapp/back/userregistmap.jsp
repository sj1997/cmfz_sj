<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<script src="${pageContext.request.contextPath}/js/echarts.js"></script>
<script src="${pageContext.request.contextPath}/js/goeasy.js "></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/china.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    var goEasy = new GoEasy({
        appkey:"BC-bb534c8ed9c343f18b8776e3ce18f78b"
    });
    goEasy.subscribe({
        channel: "my",
        onMessage: function (message) {
            var parse = JSON.parse(message.content);
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '注册报表'
                },
                tooltip: {},
                legend: {
                    data:['人数']
                },
                xAxis: {
                    data: parse[0]
                },
                yAxis: {},
                series: [{
                    name: '人数',
                    type: 'bar',
                    data: parse[1]
                }]
                // 使用刚指定的配置项和数据显示图表。
            };
            myChart.setOption(option);


        }
    })
    $(function () {
        $.post("${pageContext.request.contextPath}/user/userr",function (date) {
            alert(date)
        },"json")
    })

</script>
<div class="page-header">
    <h1><center><font color = "#00008b">用户注册报表动态图</font></center></h1>
</div>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<center><div id="main" style="width: 600px;height:400px;"></div></center>