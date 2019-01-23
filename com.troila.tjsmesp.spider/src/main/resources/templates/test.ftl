<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript">
var arr_province = ["请选择省/城市","北京市","上海市","天津市","重庆市","深圳市","广东省"];
var arr_city = [
                ["请选择城市/地区"],
                ["东城区","西城区","朝阳区","宣武区","昌平区","大兴区","丰台区","海淀区"],
                ['宝山区','长宁区','丰贤区', '虹口区','黄浦区','青浦区','南汇区','徐汇区','卢湾区'],
                ['和平区', '河西区', '南开区', '河北区', '河东区', '红桥区', '塘古区', '开发区'],
                ['俞中区', '南岸区', '江北区', '沙坪坝区', '九龙坡区', '渝北区', '大渡口区', '北碚区'],
                ['福田区', '罗湖区', '盐田区', '宝安区', '龙岗区', '南山区', '深圳周边'],
                ['广州市','惠州市','汕头市','珠海市','佛山市','中山市','东莞市']
            ];
//二级联动初始化
function init()
{
    //获取name=province和name=city的元素对象
    var province = document.form1.province;
    var city = document.form1.city;
    //指定province中<option>标记的个数，添加多少个空的<option>标记
    var len = arr_province.length;
    province.length = len;
    //循环写入省份数据
    for(var i=0;i<len;i++)
    {
        province.options[i].text = arr_province[i];
        province.options[i].value = arr_province[i];
    }
    //更改省份的默认选择项
    var index = 3;
    province.selectedIndex = index;
    //********************************************************************
    //指定city中<option>标记的个数，添加多少个空的<option>标记
    var len = arr_city[index].length;
    city.length = len;
    //循环写入对应城市数据
    for(var i=0;i<len;i++)
    {
        city.options[i].text = arr_city[index][i];
        city.options[i].value = arr_city[index][i];
    }
}
//当省份下拉列表被修改，相应城市列表中的数据也要相应变化
function change_select(index)
{
    //获取name=city的元素对象
    var city = document.form1.city;
    //指定city中<option>标记的个数，添加多少个空的<option>标记
    var len = arr_city[index].length;
    city.length = len;
    //循环写入对应城市数据
    for(var i=0;i<len;i++)
    {
        city.options[i].text = arr_city[index][i];
        city.options[i].value = arr_city[index][i];
    }
}
</script>
</head>

<body onload="init()">
<form name="form1">
省份：<select name="province" onchange="change_select(this.selectedIndex)"></select>
城市：<select name="city"></select>
</form>
</body>
</html>