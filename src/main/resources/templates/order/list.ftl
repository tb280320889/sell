<html>

<head>
  <meta charset="utf-8">
  <title>seller product list</title>
  <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <div class="row clearfix">
    <div class="col-md-12 column">
      <table class="table table-bordered">
        <thead>
        <tr>
          <th> orderId</th>
          <th> buyerName</th>
          <th> phoneNo</th>
          <th> address</th>
          <th> priceAmount</th>
          <th> orderStatus</th>
          <th> payStatus</th>
          <th> createTime</th>
          <th colspan="2"> operation</th>
        </tr>
        </thead>
        <tbody>
        <#list orderDTOPage.content as orderDTO>
        <tr>
          <td>${orderDTO.orderId}</td>
          <td>${orderDTO.buyerName}</td>
          <td>${orderDTO.buyerPhone}</td>
          <td>${orderDTO.buyerAddress}</td>
          <td>${orderDTO.orderAmount}</td>
          <td>${orderDTO.getOrderStatusEnum().msg}</td>
          <td>${orderDTO.getPayStatusEnum().msg}</td>
          <td>${orderDTO.createTime}</td>
          <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">details</a></td>
          <td>
            <#if orderDTO.getOrderStatusEnum().msg ==  "new order">
              <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">cancel</a>
            </#if>
          </td>
        </tr>

        </#list>
        </tbody>
      </table>
      <ul class="pagination pull-right">
      <#if currentPage lte 1>
        <li class="disabled">
          <a href="#">Prev</a>
        </li>
      <#else>
        <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">${index}</a></li>
      </#if>
      <#list 1..orderDTOPage.getTotalPages() as index>
        <#if currentPage == index>
          <li class="disabled">
            <a href="#">${index}</a>
          </li>
        <#else >
          <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
        </#if>
      </#list>
      <#if currentPage gte orderDTOPage.getTotalPages()>
        <li class="disabled">
          <a href="#">Next</a>
        </li>
      <#else >
        <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">${index}</a></li>
      </#if>
      </ul>
    </div>
  </div>
</div>
</body>

</html>
