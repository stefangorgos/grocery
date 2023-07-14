<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="grocery.model.Comment"%>
<%@ page import="grocery.repository.CommentRepository"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>

<jsp:useBean id="commentRepositoryBean" class="grocery.repository.CommentRepository"></jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
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
	<div class="container">
		<table class="styled-table">
			<thead>
				<tr><td>#</td><td>Date</td><td>Produs</td><td>Text</td><td>Rating</td></tr>
			</thead>
			<tbody>
				<c:forEach items="${commentRepositoryBean.comments}" var="comment" varStatus="i">
					<tr><td style="text-align: right;">${i.index + 1}</td><td>${comment.date}</td><td>${comment.product.name}</td><td>${comment.text}</td><td style="text-align: right;">${comment.rating}</td></tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="d-flex justify-content-left">
			<a class="price_btn" href="comment.jsf">New Comment</a>
		</div>
	</div>
Total comments: <%=new CommentRepository().getComments().size() %>

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
      <div class="d-flex justify-content-left">
        <a class="price_btn" href="product.jsp">New Product</a>
      </div>
    </div>
  </section>

  <!-- end price section -->

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