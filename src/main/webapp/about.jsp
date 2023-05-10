<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
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
	
	  <title>Grocery</title>
	
	  <!-- slider stylesheet -->
	  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.1.3/assets/owl.carousel.min.css" />
	
	  <!-- bootstrap core css -->
	  <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
	
	  <!-- fonts style -->
	  <link href="https://fonts.googleapis.com/css?family=Baloo+Chettan|Poppins:400,600,700&display=swap" rel="stylesheet">
	  <!-- Custom styles for this template -->
	  <link href="css/style.css" rel="stylesheet" />
	  <!-- responsive style -->
	  <link href="css/responsive.css" rel="stylesheet" />
	</head>

<body class="sub_page">
  <div class="hero_area">
  	<%@include file="includes/menu.jsp" %>
  </div>

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
              <a href="">
                Read More
              </a>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="img-box">
            <img src="images/about-img.png" alt="">
          </div>
        </div>
      </div>
    </div>
  </section>

  <!-- end about section -->


  <!-- info section -->
  <section class="info_section ">
    <div class="container">
      <div class="info_container">
        <div class="row">
          <div class="col-md-3">
            <div class="info_logo">
              <a href="">
                <img src="images/logo.png" alt="">
                <span>
                  Lodge
                </span>
              </a>
            </div>
          </div>
          <div class="col-md-3">
            <div class="info_contact">
              <a href="">
                <img src="images/location.png" alt="">
                <span>
                  Address
                </span>
              </a>
            </div>
          </div>
          <div class="col-md-3">
            <div class="info_contact">
              <a href="">
                <img src="images/phone.png" alt="">
                <span>
                  +01 1234567890
                </span>
              </a>
            </div>
          </div>
          <div class="col-md-3">
            <div class="info_contact">
              <a href="">
                <img src="images/mail.png" alt="">
                <span>
                  demo@gmail.com
                </span>
              </a>
            </div>
          </div>
        </div>
        <div class="info_form">
          <div class="d-flex justify-content-center">
            <h5 class="info_heading">
              Newsletter
            </h5>
          </div>
          <form action="">
            <div class="email_box">
              <label for="email2">Enter Your Email</label>
              <input type="text" id="email2">
            </div>
            <div>
              <button>
                subscribe
              </button>
            </div>
          </form>
        </div>
        <div class="info_social">
          <div class="d-flex justify-content-center">
            <h5 class="info_heading">
              Follow Us
            </h5>
          </div>
        </div>
      </div>
    </div>
  </section>

  <!-- end info_section -->

  <!-- footer section -->
  <section class="container-fluid footer_section">
    <p>
      &copy; <span id="displayYear"></span> All Rights Reserved By
      <a href="https://html.design/">Free Html Templates</a>
    </p>
  </section>
  <!-- footer section -->

  <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.js"></script>
  <script type="text/javascript" src="js/custom.js"></script>

</body>

</html>