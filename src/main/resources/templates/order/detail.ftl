<html>

<head>
  <meta charset="utf-8">
  <title>seller error notification</title>
  <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <div class="row clearfix">
    <div class="col-md-4 column">
      <table class="table table-bordered">
        <thead>
        <tr>
          <th> order id</th>
          <th> order price amount</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td> ${orderDTO.orderId}</td>
          <td> ${orderDTO.orderAmount}</td>
        </tr>
        </tbody>
      </table>
    </div>

  <#--order detail datum-->
    <div class="col-md-12 column">
      <table class="table table-bordered">
        <thead>
        <tr>
          <th> product id</th>
          <th> product name</th>
          <th> price</th>
          <th> quantity</th>
          <th> amount</th>
        </tr>
        </thead>
        <tbody>
        <#list orderDTO.orderDetailList as orderDetail>
        <tr>
          <td>${orderDetail.productId}</td>
          <td>${orderDetail.productName}</td>
          <td>${orderDetail.productPrice}</td>
          <td>${orderDetail.productQuantity}</td>
          <td>${orderDetail.productQuantity * orderDetail.productPrice}</td>
        </tr>

        </#list>
        </tbody>
      </table>

    </div>

    <div class="col-md-12 column">
    <#if orderDTO.getOrderStatusEnum().msg == "new order" >
      <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button"
         class="btn btn-default btn-primary">finish order</a>
      <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button"
         class="btn btn-default btn-danger">cancel order</a>
    </#if>
    </div>

</body>
</html>
