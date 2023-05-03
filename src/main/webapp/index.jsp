<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<head>
  <!-- Basic -->
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <!-- Mobile Metas -->
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <!-- Site Metas -->
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="author" content="" />

  <title>Lodge</title>

  <!-- bootstrap core css -->
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

  <!-- Custom styles for this template -->
  <link href="css/style.css" rel="stylesheet" />
  <!-- responsive style -->
  <link href="css/responsive.css" rel="stylesheet" />
</head>

<body class="sub_page">
  <div class="hero_area">
  	<%@include file="includes/menu.jsp" %>
  </div>

  <!-- item section -->

  <div class="item_section layout_padding2">
    <div class="container">
      <div class="item_container">
        <div class="box">
          <div class="price">
            <h6>
              Best PRICE
            </h6>
          </div>
          <div class="img-box">
            <img src="images/i-1.png" alt="">
          </div>
          <div class="name">
            <h5>
              Bracelet
            </h5>
          </div>
        </div>
        <div class="box">
          <div class="price">
            <h6>
              Best PRICE
            </h6>
          </div>
          <div class="img-box">
            <img src="images/i-2.png" alt="">
          </div>
          <div class="name">
            <h5>
              Ring
            </h5>
          </div>
        </div>
        <div class="box">
          <div class="price">
            <h6>
              Best PRICE
            </h6>
          </div>
          <div class="img-box">
            <img src="images/i-3.png" alt="">
          </div>
          <div class="name">
            <h5>
              Earings
            </h5>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- end item section -->

  <!-- about section -->

  <section class="about_section layout_padding2-top layout_padding-bottom">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <div class="detail-box">
            <div class="heading_container">
              <h2>
                About Jewellery Shop
              </h2>
            </div>
            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
              dolore magna aliqua. Ut enim ad minim veniam, quis nostrudLorem ipsum dolor sit amet, consectetur
              adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
              veniam, quis nostrud
            </p>
            <div>
              <a href="product.jsp">
                New Product
              </a>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="img-box">
          </div>
        </div>
      </div>
    </div>
  </section>

  <!-- end about section -->

  <!-- price section -->

  <section class="price_section layout_padding">
    <div class="container">
      <div class="heading_container">
        <h2>
          Our Jewellery Price
        </h2>
      </div>
      <div class="price_container">
        <div class="box">
          <div class="name">
            <h6>
              Diamond Ring
            </h6>
          </div>
          <div class="img-box">
            <img src="images/p-1.png" alt="">
          </div>
          <div class="detail-box">
            <h5>
              $<span>1000.00</span>
            </h5>
            <a href="">
              Buy Now
            </a>
          </div>
        </div>
        <div class="box">
          <div class="name">
            <h6>
              Diamond Ring
            </h6>
          </div>
          <div class="img-box">
            <img src="images/i-2.png" alt="">
          </div>
          <div class="detail-box">
            <h5>
              $<span>1000.00</span>
            </h5>
            <a href="">
              Buy Now
            </a>
          </div>
        </div>
        <div class="box">
          <div class="name">
            <h6>
              Diamond Ring
            </h6>
          </div>
          <div class="img-box">
            <img src="images/i-3.png" alt="">
          </div>
          <div class="detail-box">
            <h5>
              $<span>1000.00</span>
            </h5>
            <a href="">
              Buy Now
            </a>
          </div>
        </div>
      </div>
      <div class="d-flex justify-content-center">
        <a href="" class="price_btn">
          See More
        </a>
      </div>
    </div>
  </section>

  <!-- end price section -->

  <!-- ring section -->

  <section class="ring_section layout_padding">
    <div class="container">
      <div class="ring_container layout_padding2">
        <div class="row">
          <div class="col-md-5">
            <div class="detail-box">
              <h4>
                special
              </h4>
              <h2>
                Wedding Ring
              </h2>
              <a href="">
                Buy Now
              </a>
            </div>
          </div>
          <div class="col-md-7">
            <div class="img-box">
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

  <!-- end ring section -->

  <!-- client section -->

  <section class="client_section">
    <div class="container">
      <div class="heading_container">
        <h2>
          Testimonial
        </h2>
      </div>
      <h4 class="secondary_heading">
        What is says our customer
      </h4>
    </div>
  </section>

  <!-- end client section -->

  <!-- contact section -->

  <section class="contact_section layout_padding">
    <div class="container ">
      <div class="">
        <h2 class="">
          Contact Us
        </h2>
      </div>

    </div>
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <form action="">
            <div>
              <input type="text" placeholder="Name" />
            </div>
            <div>
              <input type="email" placeholder="Email" />
            </div>
            <div>
              <input type="text" placeholder="Phone" />
            </div>
            <div>
              <input type="text" class="message-box" placeholder="Message" />
            </div>
            <div class="d-flex ">
              <button>
                SEND
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>

  <!-- end contact section -->

  <!-- info and footer section -->
  <%@include file="includes/footer.jsp" %>

  <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.js"></script>
  <script type="text/javascript" src="js/custom.js"></script>
</body>

</html>