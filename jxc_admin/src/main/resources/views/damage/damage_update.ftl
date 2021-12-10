<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">


<form class="layui-form" style="width:80%;" name="test" >
    <input name="goodsId" type="hidden" value="${(goods.id)!}"/>
    <input name="state" type="hidden" value="1"/>
    <input name="userId" type="hidden" value="${(Session.SPRING_SECURITY_CONTEXT.authentication.principal.id)!'999'}"/>
    <input name="unit" type="hidden" value="${(goods.unit)!""}">
    <input name="typeId" type="hidden" value="${(goods.typeId)!"1"}" >
    <input name="price" type="hidden" value="${goods.purchasingPrice}">
    <fieldset class="layui-elem-field layui-field-title" >
        <legend style="font-size: 15px">商品信息</legend>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品名</label>
            <div class="layui-input-block">
                <input type="text" name="name" class="layui-input name"
                       readonly="readonly" id="name" value="${(goods.name)!}">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品编号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input code"
                       readonly="readonly" name="code" id="code" value="${(goods.code)!}">
            </div>
        </div>
    </div>
    <br/>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品型号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input sellingPrice"
                       readonly="readonly" name="model" id="model" value="${(goods.model)!}">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">当前库存</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input sellingPrice"
                       readonly="readonly" name="inventoryQuantity" id="inventoryQuantity" value="${(goods.inventoryQuantity)!}">
            </div>
        </div>
    </div>
    <br/>

    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">报损数量</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input inventoryQuantity"
                       name="num" id="num"  lay-verify="required" placeholder="请输入报损数量"
                       oninput="this.value = this.value.replace(/[^0-9]/g, '');" onkeyup="count()">
            </div>
        </div>
         <div class="layui-col-xs6">
              <label class="layui-form-label">报损总成本</label>
              <div class="layui-input-block">
                  <input type="text" class="layui-input sellingPrice"
                        readonly="readonly"  name="amountPaid" id="amountPaid"  >
              </div>
         </div>

    </div>
    <br/>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">报损号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input purchasingPrice"
                       name="damageNumber" id="damageNumber" value="" placeholder="BS201710270001">
            </div>
        </div>
        <div class="layui-col-xs6">
                <label class="layui-form-label">操作人</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input purchasingPrice"
                          readonly="readonly" name="userName" id="userName"  value="${(Session.SPRING_SECURITY_CONTEXT.authentication.principal.username)!'lzl'}" >
                </div>
        </div>
    </div>
        </div>
    </fieldset>
    <br/>

    <br/>

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="updateGoods">报损
            </button>
            <a class="layui-btn layui-btn-lg layui-btn-normal"  id="closeDlg" href="javascript:void(0)">取消</a>
        </div>
    </div>
</form>
<script type="text/javascript">
    function count(){
        var amount,price,money;
        amount=document.test.num.value;
        amount=parseFloat(amount);
        price=document.test.price.value;
        price=parseFloat(price);
        money=amount*price;
        document.test.amountPaid.value=money;

    }
</script>


<script type="text/javascript" src="${ctx.contextPath}/js/damage/damage.update.js"></script>
</body>
</html>